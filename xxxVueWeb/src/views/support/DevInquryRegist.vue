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
				<span>개발지원</span>
				<span @click="goList()"><b>Q&A</b></span>
			</h2>
			
			<div class="ListTbWrap mt-20">
				<div style="margin-left:5px;margin-bottom:5px;"><input type="checkbox" name="" id="result01" :checked="devInquryData.secretAt == 'Y'" v-model="devInquryData.secretAt"><label for="result01"><span></span>비밀글</label></div>
				<table class="tList">
					<caption>Q&A수정</caption>
					<colgroup>
						<col style="width:15%">
						<col>					
					</colgroup>
					<tbody>
					<tr>
						<td class="PalignL">제목<span class="t_red">*</span></td>
						<td class="PalignL"><input type="text" id="" class="w100p" placeholder="제목을 입력하세요" v-model="devInquryData.inqrySj" ref="inqrySn"> </td> 
					</tr>
                    <tr>
						<td class="PalignL">내용<span class="t_red">*</span></td>
						<td class="PalignL"> 
                            <textarea class="w100p" rows="15" placeholder="내용을 입력하세요" v-model="devInquryData.inqryCn" ref="inqryCn"></textarea>
                        </td>
					</tr>
                    <tr>
						<td class="PalignL">첨부파일</td>
						<td class="PalignL"> 
							<template v-if="devInquryData.isNew === 'C'">
								<input type="file" name="" @change="doFileChange">
							</template>
							<template v-else>
								<input type="file" name="" @change="doFileChange">
								<a href="#" class="attatch_file ml-10">{{devInquryData.fileNm}}</a>
								<button type="button" class="btnFileDel" :hidden="isNew">삭제</button>
							</template>	
                        </td>
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
	<div class="card3_popup" style="display: block; top:160px;" v-show="this.finishPopupShow">
		<div class="popup_body">        
			<!-- 메시지 -->
			<div class="card_mody mt-20">
				<p class="c_tit">-Q&A가 수정되었습니다.</p>               
			</div>
		</div>
		<div class="btn_wrap_pop">
			<button class="btn_m01 blue">확인</button>
		</div>
		<div class="popup_close" @click="this.finishPopupShow = false">닫기</div>
	</div>
	<!-- 팝업 끝-->

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
import { devInquryRegist, devInquryView } from "@/api/devInquryApi";

export default {
    data() {
        return {
			finishPopupShow : false,
			validationPopupShow : false,
			isNew : false,
			isNewText : '등 록',
			devInquryData : {}
        }
    },
	created() {
		if(this.$route.query.status != 'C') {
			this.isNew = false;
			this.isNewText = '수 정';
			this.getView();
		} else {
			this.isNew = true;
			this.isNewText = '등 록';
		}
	},
	methods : {
		goList() {
			this.$router.push({
				path: "/support/devInquryList",
			});
		},
		goView(inqrySn) {
			this.$router.push({
				path: "/support/devInquryView",
				query:{
                        inqrySn:inqrySn,
                      }
			});
		},
		getView() {
			devInquryView({
				inqrySn: this.$route.query.inqrySn
			}).then(response => {
				console.log("response.data.devInquryView", response.data);
				this.devInquryData = response.data.devInquryVO;
				console.log(this.devInquryData)
			}).catch(error => {
				this.goList();
			});
		},
		doRegist() {
			console.log(this.devInquryData.inqurySj);
			if(this.devInquryData.inqrySj == undefined || this.devInquryData.inqrySj == '') {
				this.validationPopupShow = true;
				this.$refs.inqrySj.focus();
				return false;
			}
			if(this.devInquryData.inqryCn == undefined || this.devInquryData.inqryCn == '') {
				this.validationPopupShow = true;
				this.$refs.inqryCn.focus();
				return false;
			}

			if (!confirm("Q&A를 " + (this.isNew === true ? "등록" : "수정") + "하시겠습니까?")) return false;

			devInquryRegist(this.devInquryData
			).then(response => {
				console.log("response.data.devInquryRegist", response.data);
				alert("Q&A가 " + (this.isNew === true ? "등록" : "수정") + "되었습니다.");
				this.goView(response.data.resultSn);
			}).catch(error => {
				alert("Q&A " + (this.isNew === true ? "등록이" : "수정이") + " 실패하였습니다.\r\n관리자에게 문의하여주시기 바랍니다.");
				this.goList();
			});
		},
		doFileChange(e) {
		  
		}
	},
}
</script>
