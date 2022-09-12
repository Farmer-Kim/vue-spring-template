<template>
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
				<span><b>Q&A</b></span>
			</h2>
			<div class="topSearch">
				<select name="" class="tops_sel" v-model="searchParams.srchType">
					<option value="1">제목</option>
					<option value="2">내용</option>
					<option value="3">등록자</option>
				</select>
				<input type="text" name="" class="tops_inp" placeholder="검색어를 입력하세요" v-model="searchParams.srchWord" @keyup.enter="searchList()">
				<button type="button" onclick="" class="topBtnSearch" @click="searchList()">검색</button>
			</div>

			<div class="ListTbWrap mt-20">
				<div class="tbHead">
					<div class="total">
						총<span>{{total}}</span>건 게시글
					</div>
					<div class="tbHeadR">
						<button type="button" onclick="" class="btn01" @click="goRegist()">글쓰기</button>
					</div>
				</div>

				<table class="tList">
					<caption>개발자 가이드 리스트</caption>
					<colgroup>
						<col style="width:8%">
						<col>
						<col style="width:10%">
						<col style="width:15%">
						<col style="width:10%">
					</colgroup>
					<thead>
					<tr>
						<th>순번</th>
						<th>제목</th>
						<th>등록자</th>
						<th>등록일</th>
						<th>답변여부</th>
					</tr>
					</thead>
					<tbody>
					<template v-if="this.total > 0">
						<tr v-for="(list) in devInquryList" :key="list.inqrySn">
							<td>{{list.inqryIndex}}</td>
							<td class="alignL"><a href="#" :class="{'ellipsis' : list.secretAt=='N', 'ellipsis lock' : list.secretAt=='Y'}" @click="goView(list.inqrySn)">{{list.inqrySj}}</a></td>
							<td>{{list.userId}}</td>						
							<td>{{list.creatDt}}</td>
							<td>
								<label class="lb01" v-if="list.answerAt=='N'">대기</label>
								<label class="lb01" v-else-if="list.answerAt!='N'">완료</label>
							</td>                       
						</tr>
					</template>
					<template v-else>
						<tr>
							<td colspan="5" class="noData">데이터가 존재하지 않습니다.</td>
						</tr>
					</template>
					</tbody>
				</table>
				
				<!-- 페이징 -->
				<pagination
					v-show="total > 0"
					ref="pagination"
					:total-count="total"
					:row-count="this.searchParams.perPageNum"
					:curr-page="this.searchParams.page"
					@change-page="changePage"
				/>
			</div>

			<!-- 팝업-->
			<div class="card3_popup" style="display: block; top:160px;" v-show="this.viewDeletePopup">
				<div class="popup_body">        
					<!-- 메시지 -->
					<div class="card_mody mt-20">
						<p class="c_tit">작성자만 열람 가능합니다.</p>               
					</div>
				</div>
				<div class="btn_wrap_pop">
					<button class="btn_m01 blue" @click="this.viewDeletePopup = false">확인</button>
				</div>
				<div class="popup_close" @click="this.viewDeletePopup = false">닫기</div>
			</div>
			<!-- 팝업 끝-->

			<!-- 팝업-->
			<div class="card3_popup" style="display: block; top:160px;" v-show="this.loginDeletePopup">
				<div class="popup_body">        
					<!-- 메시지 -->
					<div class="card_mody mt-20">
						<p class="c_tit">로그인 후 이용 가능합니다.</p>               
					</div>
				</div>
				<div class="btn_wrap_pop">
					<button class="btn_m01_50" @click="this.loginDeletePopup = false">확인</button>
					<button class="btn_m02_50" @click="this.loginDeletePopup = false">취소</button>
				</div>
				<div class="popup_close" @click="this.loginDeletePopup = false">닫기</div>
			</div>
			<!-- 팝업 끝-->
			
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>
<script>
import {devInquryList} from "@/api/devInquryApi";
export default {
    data(){
		return {
				searchParams : {
					srchWord : ""
					, srchType : "1"
					, page: 1
					, perPageNum: 10
				},
				devInquryList : [],
				total : 0,
				loginDeletePopup:false, viewDeletePopup:false}

	},
	created() {
		this.handleFilter();
	},
	methods:{
		searchList() {
			devInquryList(this.searchParams)
			.then(response => {
				console.log("response.data.devInquryList", response.data);
				this.devInquryList = response.data.devInquryList;
				this.total = response.data.devInquryListCnt;
			});
		},
		handleFilter() {
			this.searchParams.page = 1;
			this.searchList(); // 목록 조회
		},
		goView(inqrySn) {
			this.$router.push({
				path: "/support/devInquryView",
				query:{
                        inqrySn : inqrySn,
                      }
			});
		},
		goRegist() {
			this.$router.push({
				path: "/support/devInquryRegist",
				query:{
                        status : 'C',
						noticeSn : 0
                      }
			});
		},
	}
}
</script>
<style lang="">
    
</style>