<template lang="">
<!-- 컨텐츠 영역 -->
	<div id="contentWrap">
		<div id="topVisualWrap">
			<div class="topVisual">
				<p class="tit01">고객 중심 xx서비스 제공을 위한</p>
				<p class="tit02">인공지능 클라우드 XXX 플랫폼 <span></span></p>
			</div>
		</div>

		<!-- 컨텐츠영역 -->
		<div id="Content">
			<h2>
				<span>개발지원</span>
				<span @click="goList()"><b>Q&A</b></span>
			</h2>

			<div class="ListTbWrap mt-20">
				<table class="tView">
					<caption>Q&A 상세보기</caption>
					<colgroup>
						<col>
						<col style="width:18%">
						<col style="width:15%">
					</colgroup>
					<thead>
					<tr>
						<th class="alignL bolder">{{devInquryData.inqrySj}}</th>
						<th><span>등록일&nbsp;&nbsp;<b>{{devInquryData.creatDt}}</b></span></th>
						<th><span>첨부파일&nbsp;&nbsp;<a href="#" class="link_down2" v-show="devInquryData.fileSn"></a><span v-show="!devInquryData.fileSn">-</span></span></th>
					</tr>
					</thead>
				</table>
				<div class="view_wrap">
					{{devInquryData.inqryCn}}
				</div>
				<template v-if="devInquryData.answerAt != 'N'"> 
					<div class="view_wrap" style="border-top: 1px solid black;">
						<div style="margin-top:-30px;margin-left:-5px;font-weight:bold;">[ 답변내용 ]</div>
						<br>
						{{devInquryData.answerCn}}
					</div>
				</template>
				<table class="tView2 mt-40">
					<colgroup>
						<col style="width:12%">
						<col>
					</colgroup>
					<tbody>
					<tr>
						<th>이전글</th>
						<td>
							<template v-if="devInquryData.beforeInqrySn !== -1">
								<a href="#none" @click="goView(devInquryData.beforeInqrySn)">{{devInquryData.beforeInqrySj}}</a>
							</template>
							<template v-else>
								이전 글이 없습니다.	
							</template>
						</td>
					</tr>
					<tr>
						<th>다음글</th>
						<td>
							<template v-if="devInquryData.nextInqrySn !== -1">
								<a href="#none" @click="goView(devInquryData.nextInqrySn)">{{devInquryData.nextInqrySj}}</a>
							</template>
							<template v-else>
								다음 글이 없습니다.	
							</template>
						</td>
					</tr>
					</tbody>
				</table>
			</div><!-- //ListTbWrap -->

			<div class="btn_wrap">	
				<a href="#" class="btn04" @click="goList()">목 록</a>			
				<template v-if="devInquryData.answerAt != 'Y'"> 	
					<a href="#" class="btn03" @click="goRegist(devInquryData.inqrySn)">수 정</a>
				</template>
				<a href="#" class="btn03" @click="this.deletePopup = true">삭 제</a>
			</div>	
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
			
	<!-- 팝업-->
		<div class="card3_popup" style="display: block; top:160px;" v-show="this.deletePopup">
			<div class="popup_body">        
				<!-- 메시지 -->
				<div class="card_mody mt-20">
					<p class="c_tit">Q&A를 삭제하시겠습니까?</p>               
				</div>
			</div>
			<div class="btn_wrap_pop">
				<button type="button" class="btn_m01_50" @click="doDelete()">확인</button>
				<button type="button" class="btn_m02_50" @click="this.deletePopup = false">취소</button>
			</div>
			<div class="popup_close" @click="this.deletePopup = false">닫기</div>
		</div>

</template>
<script>
import { devInquryView, devInquryDelete } from "@/api/devInquryApi";

export default {
	data() {
		return {
			searchParam: {
                inqrySn: this.$route.query.inqrySn
            },
			devInquryData : {},
			deletePopup : false
		}
	},
	created() {
		this.getView();
	},
	methods: {
		getView() {
			devInquryView({
				inqrySn: this.searchParam.inqrySn
			}).then(response => {
				console.log("response.data.devInquryView", response.data);
				this.devInquryData = response.data.devInquryVO;
				console.log(this.devInquryData)
			}).catch(error => {
				this.goList();
			});
		},
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
		goRegist(inqrySn) {
			this.$router.push({
				path: "/support/devInquryRegist",
				query:{
                        status : 'U',
						inqrySn : inqrySn
                      }
			});
		},
		doDelete() {
			devInquryDelete({
				inqrySn: this.searchParam.inqrySn
			}).then(response => {
				console.log("response.data.devInquryView", response.data);
				this.goList();
			}).catch(error => {
				console.log(error);
				alert('Q&A 삭제에 실패하였습니다.\r\n관리자에게 문의하여주시기 바랍니다.')
			});
		}
	},
}
</script>
<style lang="">
	
</style>