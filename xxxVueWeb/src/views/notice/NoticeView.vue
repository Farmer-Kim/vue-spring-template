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
				<span>이용 가이드</span>
				<span @click="goList()"><b>공지사항</b></span>
			</h2>

			<div class="ListTbWrap mt-20">
				<table class="tView">
					<caption>공지사항 상세보기</caption>
					<colgroup>
						<col>
						<col style="width:12%">
						<col style="width:18%">
						<col style="width:15%">
					</colgroup>
					<thead>
					<tr>
						<th class="alignL bolder">{{noticeData.noticeSj}}</th>
						<th><span>조회수&nbsp;&nbsp;<b>{{noticeData.noticeRdCnt}}</b></span></th>
						<th><span>등록일&nbsp;&nbsp;<b>{{noticeData.creatDt}}</b></span></th>
						<th><span>첨부파일&nbsp;&nbsp;<a href="#" class="link_down2" v-show="noticeData.fileSn"></a><span v-show="!noticeData.fileSn">-</span></span></th>
					</tr>
					</thead>
				</table>
				<div class="view_wrap">
					{{noticeData.noticeCn}}
				</div>
				<table class="tView2 mt-40">
					<colgroup>
						<col style="width:12%">
						<col>
					</colgroup>
					<tbody>
					<tr>
						<th>이전글</th>
						<td>
							<template v-if="noticeData.beforeNoticeSn !== -1">
								<a href="#none" @click="goView(noticeData.beforeNoticeSn)">{{noticeData.beforeNoticeSj}}</a>
							</template>
							<template v-else>
								이전 글이 없습니다.	
							</template>
						</td>
					</tr>
					<tr>
						<th>다음글</th>
						<td>
							<template v-if="noticeData.nextNoticeSn !== -1">
								<a href="#none" @click="goView(noticeData.nextNoticeSn)">{{noticeData.nextNoticeSj}}</a>
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
				<a href="#" class="btn03" @click="goRegist(noticeData.noticeSn)">수 정</a>
				<a href="#" class="btn03" @click="this.deletePopup = true">삭 제</a>
			</div>	
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
			
	<!-- 팝업-->
		<div class="card3_popup" style="display: block; top:160px;" v-show="this.deletePopup">
			<div class="popup_body">        
				<!-- 메시지 -->
				<div class="card_mody mt-20">
					<p class="c_tit">공지사항을 삭제하시겠습니까?</p>               
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
import { noticeView, noticeDelete } from "@/api/noticeApi";

export default {
	data() {
		return {
			searchParam: {
                noticeSn: this.$route.query.noticeSn
            },
			noticeData : {},
			deletePopup : false
		}
	},
	created() {
		this.getView();
	},
	methods: {
		getView() {
			noticeView({
				noticeSn: this.searchParam.noticeSn
			}).then(response => {
				console.log("response.data.noticeView", response.data);
				this.noticeData = response.data.noticeVO;
				console.log(this.noticeData)
			}).catch(error => {
				this.goList();
			});
		},
		goList() {
			this.$router.push({
				path: "/notice/noticeList",
			});
		},
		goView(noticeSn) {
			this.$router.push({
				path: "/notice/noticeView",
				query:{
                        noticeSn:noticeSn,
                      }
			});
		},
		goRegist(noticeSn) {
			this.$router.push({
				path: "/notice/noticeRegist",
				query:{
                        status : 'U',
						noticeSn : noticeSn
                      }
			});
		},
		doDelete() {
			noticeDelete({
				noticeSn: this.searchParam.noticeSn
			}).then(response => {
				console.log("response.data.noticeView", response.data);
				this.goList();
			}).catch(error => {
				console.log(error);
				alert('공지사항 삭제에 실패하였습니다.\r\n관리자에게 문의하여주시기 바랍니다.')
			});
		}
	},
}
</script>
<style lang="">
	
</style>