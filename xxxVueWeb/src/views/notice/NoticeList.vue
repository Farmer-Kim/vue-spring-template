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
				<span @click="handleFilter()"><b>공지사항</b></span>
			</h2>
			<div class="topSearch">
				<select name="" class="tops_sel" v-model="searchParams.srchType">
					<option value="1">제목</option>
					<option value="2">내용</option>
				</select>
				<input type="text" name="" class="tops_inp" placeholder="검색어를 입력하세요" v-model="searchParams.srchWord" @keyup.enter="searchList()">
				<button type="button" class="topBtnSearch" @click="searchList()">검색</button>
			</div>

			<div class="ListTbWrap mt-20">
				<div class="tbHead">
					<div class="total">
						총<span>{{total}}</span>건 게시글
					</div>
					<div class="tbHeadR">
						<!--v-if로 관리자 여부에 따라 보이기 여부 변경-->
						<button type="button" onclick="" class="btn01" @click="goRegist()">글쓰기</button>
					</div>
				</div>

				<table class="tList">
					<caption>공지사항리스트</caption>
					<colgroup>
						<col style="width:8%">
						<col>
						<col style="width:10%">
						<col style="width:10%">
						<col style="width:10%">
						
					</colgroup>
					<thead>
					<tr>
						<th>순번</th>
						<th>제목</th>
						<th>조회수</th>
						<th>등록일</th>
						<th>첨부파일</th>
					</tr>
					</thead>
					<tbody>
						<template v-if="this.total > 0">
							<tr v-for="(list, index) in noticeList" :key="list.noticeSn">
								<td>{{list.noticeSn}}</td>
								<td class="alignL"><a href="#" class="ellipsis" @click="goView(list.noticeSn)">{{list.noticeSj}}</a></td>
								<td>{{list.noticeRdCnt}}</td>
								<td>{{list.creatDt}}</td>
								<td><a href="#" class="link_down" v-show="list.fileSn" @click="fileDownload(list.fileSn)">다운로드</a></td>
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
			
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>
<script>
import { noticeList } from "@/api/noticeApi";

export default {
    data() {
		return {
			searchParams : {
				srchWord : ""
				, srchType : "1"
				, page: 1
        		, perPageNum: 10
			},
			noticeList : [],
			total : 0,
		}
	},
	created() {
		this.handleFilter();
	},
	methods: {
		/* 공지사항 조회 */
		searchList() {
			noticeList(this.searchParams)
			.then(response => {
				console.log("response.data.noticeList", response.data);
				this.noticeList = response.data.noticeList;
				this.total = response.data.noticeListCnt;
				//this.total = response.data.totalCount;
			});
			//this.searchParams.searchStr = "";
		},
		handleFilter() {
			this.searchParams.page = 1;
			this.searchList(); // 목록 조회
		},
		/* 페이지당 갯수 변경 */
		changeRowCount(perPageNum) {
			this.searchParams.perPageNum = perPageNum;
			this.changePage(1);
		},
		changePage(page) {
			this.searchParams.page = page;
			this.searchList(); // 목록 조회
		},
		goView(noticeSn) {
			this.$router.push({
				path: "/notice/noticeView",
				query:{
                        noticeSn : noticeSn,
                      }
			});
		},
		goRegist() {
			this.$router.push({
				path: "/notice/noticeRegist",
				query:{
                        status : 'C',
						noticeSn : 0
                      }
			});
		},
		fileDownload(fileSn) {
			console.log('fileSn : ', fileSn);
		}
	},
}
</script>
<style lang="">
    
</style>