<template>
	<!-- 컨텐츠 영역 -->
	<div id="contentWrap">
		<div id="topVisualWrap">
			<div class="topVisual">
				<p class="tit01">환자 중심 의료서비스 제공을 위한</p>
				<p class="tit02">인공지능 클라우드 WiFi6 기반 표준 의료 IoMT 플랫폼 <span>지원 포털</span></p>
			</div>
		</div>

		<!-- 컨텐츠영역 -->
		<div id="Content">
			<h2>
				<span>서비스 관리</span>
				<span @click="goList()"><b>기관관리</b></span>
			</h2>

			<div class="ListTbWrap mt-20">
				<table class="tList">
					<caption>공지사항수정</caption>
					<colgroup>
						<col style="width:15%">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<td class="PalignL">기관 명<span class="t_red">*</span></td>
							<td class="PalignL"><input type="text" id="" style="width:300px;" v-model="insttData.insttNm" ref="insttNm"></td>
						</tr>
						<tr>
							<td class="PalignL">기관 유형코드<span class="t_red">*</span></td>
							<td class="PalignL"><input type="text" v-model="insttData.insttTyCode" ref="insttTyCode"></td>
						</tr>
						<tr>
							<td class="PalignL">대표 전화 번호<span class="t_red">*</span></td>
							<td class="PalignL"><input type="text" style="width:200px;" placeholder="대표 전화 번호를 입력하세요." v-model="insttData.reprsntTelno" ref="reprsntTelno"></td>
						</tr>
						<tr>
							<td class="PalignL">기관 주소<span class="t_red">*</span></td>
							<td class="PalignL"><input type="text" disabled="disabled" v-model="addr1" style="width:350px;"><button style="margin-left:30px;" type="button" class="btn01" @click="showApi">주소 검색</button></td>
						</tr>
						<tr>
							<td class="PalignL">기관 상세 주소</td>
							<td class="PalignL"><input type="text" v-model="insttData.insttDetailAdres"></td>
						</tr>
						<tr>
							<td class="PalignL">기관 우편 번호<span class="t_red">*</span></td>
							<td class="PalignL"><input type="text" disabled="disabled" v-model="zip"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<a href="#" class="btn04" @click="doRegist()">{{this.isNewText}}</a>
				<a href="#" class="btn03" @click="goList()">취 소</a>
			</div>

		</div><!-- //Content -->
	</div><!-- //contentWrap -->

	<!-- 팝업-->
	<div class="card3_popup" style="display: block; top:160px;" v-show="this.validationPopupShow">
		<div class="popup_body">
			<!-- 메시지 -->
			<div class="card_mody mt-20">
				<p class="c_tit">필수항목을 입력해 주세요.</p>
			</div>
		</div>
		<div class="btn_wrap_pop">
			<button class="btn_m01 blue" @click="this.validationPopupShow = false">확인</button>
		</div>
		<div class="popup_close" @click="this.validationPopupShow = false">닫기</div>
	</div>
	<!-- 팝업 끝-->
</template>

<script>
	import { insttRegist } from "@/api/insttApi";
	export default {
		name: 'daumMap', data() { return { zip: '', addr1: '', isNewText: '등 록', insttData: {}, validationPopupShow: false} },
		methods: {
			showApi() {
				new window.daum.Postcode({
					oncomplete: (data) => {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분. 
						// 도로명 주소의 노출 규칙에 따라 주소를 조합한다. 
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다. 
						let fullRoadAddr = data.roadAddress;
						// 도로명 주소 변수 
						let extraRoadAddr = ''; 
						// 도로명 조합형 주소 변수 
						// 법정동명이 있을 경우 추가한다. (법정리는 제외) 
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다. 
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) { extraRoadAddr += data.bname; }
						// 건물명이 있고, 공동주택일 경우 추가한다. 
						if (data.buildingName !== '' && data.apartment === 'Y') { extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName); }
						// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다. 
						if (extraRoadAddr !== '') { extraRoadAddr = ' (' + extraRoadAddr + ')'; }
						// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다. 
						if (fullRoadAddr !== '') { fullRoadAddr += extraRoadAddr; }
						// 우편번호와 주소 정보를 해당 필드에 넣는다. 
						this.zip = data.zonecode; 
						//5자리 새우편번호 사용 
						this.addr1 = fullRoadAddr;
					}
				}).open()
			}
			,
			goList() {
				this.$router.push({
					path: "/manage/insttManageList",
				});
			},
			doRegist() {
				console.log(this.insttData.insttNm);
				if (this.insttData.insttNm == undefined || this.insttData.insttNm == '') {
					this.validationPopupShow = true;
					this.$refs.insttNm.focus();
					return false;
				}
				if (this.insttData.insttTyCode == undefined || this.insttData.insttTyCode == '') {
					this.validationPopupShow = true;
					this.$refs.insttTyCode.focus();
					return false;
				}

				if (this.insttData.reprsntTelno == undefined || this.insttData.reprsntTelno == '') {
					this.validationPopupShow = true;
					this.$refs.reprsntTelno.focus();
					return false;
				}

				if (this.addr1 == undefined || this.addr1 == '') {
					this.validationPopupShow = true;
					//this.$refs.addr1.focus();
					return false;
				}

				if (this.zip == undefined || this.zip == '') {
					this.validationPopupShow = true;
					//this.$refs.zip.focus();
					return false;
				}

				if (!confirm("기관을 등록 하시겠습니까?")) return false;
				this.insttData.insttAdres = this.addr1;
				this.insttData.insttZip = this.zip;
				insttRegist(this.insttData
				).then(response => {
					if(response.data.code==200) {
						console.log("response.data.insttRegist", response.data);
						alert("기관이 등록되었습니다.");
						this.goList();
					} else {
						alert("기관등록에 실패하였습니다.\r\n관리자에게 문의하여주시기 바랍니다.");
						this.goList();
					}

				})
			},
		}
	}
</script>