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
				<span><b>개발자 가이드</b></span>
			</h2>
			<div class="topSearch">
				<select name="" class="tops_sel">
					<option value="" selected>선택</option>
					<option value=""></option>
				</select>
				<input type="text" name="" class="tops_inp" placeholder="검색어를 입력하세요">
				<button type="button" @onclick="handleFilter" class="topBtnSearch" >검색</button>
			</div>

			<div class="ListTbWrap mt-20">
				<div class="tbHead">
					<div class="total">
						총<span>{{total}}</span>건 게시글
					</div>
					<div class="tbHeadR">
						<button type="button" @click="goRegist" class="btn01">등록</button>
					</div>
				</div>

				<table class="tList">
					<caption>개발자 가이드 리스트</caption>
					<colgroup>
						<col style="width:8%">
						<col>
						<col style="width:10%">
						<col style="width:10%">
						<col style="width:15%">
						
					</colgroup>
					<thead>
					<tr>
						<th>순번</th>
						<th>제목</th>
						<th>버전</th>
						<th>등록일</th>
						<th>등록자ID</th>
					</tr>
					</thead>
					<tbody>
				
					<template v-if="total>0">
						<tr v-for="item in devGuideList" :key="item">
							<td>{{item.devlopGuideSn}}</td>
							<td class="alignL"><a href="#" class="ellipsis" @click="goView(item.devlopGuideSn)">{{item.devlopGuideSj}}</a></td>
							<td>{{item.devlopGuideVerValue}}</td>
							<td>{{item.creatDt}}</td>
							<td>{{item.crtrSn}}</td>
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
				<!-- e: .pagination -->
			</div>
			
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>

<script>
import { devGuideList } from "@/api/supportApi";

export default {
  data() {
    return {
      devGuideList: [],
      searchParams: {
        srchWord: "",
        page: 1,
        perPageNum: 10
      },
      total: 0,
    };
  },
  created() {
	  this.handleFilter();
  },
  methods: {
    handleFilter() {
      this.searchParams.page = 1;
      this.getList(); // 목록 조회
    },
    changePage(page) {
      this.searchParams.page = page;
      this.getList(); // 목록 조회
    },
    /* 개발자 가이드 검색 */
    getList() {
      devGuideList(this.searchParams)
        .then(response => {
          console.log(" this.devGuideList ==>" + JSON.stringify(response.data.devGuideList));
          this.devGuideList = response.data.devGuideList;
          this.total = response.data.devGuideListCnt;
        })
        .catch(error => {
          console.log("devGuideList error ==> ", error);
        });
    },
	/* 등록 페이지로 이동 */
    goRegist() {
      this.$router.push({
        path: "/support/DevGuideRegist",
        query: {
          saveType: 'insert'
        }
      });
    },
	/* 상세 페이지로 이동 */
    goView(devlopGuideSn) {
      this.$router.push({
        path: "/support/DevGuideView",
        query: {
          devlopGuideSn: devlopGuideSn
        }
      });
    }
  }
};
</script>
