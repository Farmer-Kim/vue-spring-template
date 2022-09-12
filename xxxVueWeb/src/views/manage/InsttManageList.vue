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
				<span>서비스관리</span>
				<span><b>기관관리</b></span>
			</h2>
			<div class="topSearch">
				<select name="" class="tops_sel" v-model="searchParams.srchType">
					<option value="1">기관명</option>
					<option value="2">유형코드</option>
					<option value="3">대표전화</option>
					<option value="4">주소</option>
				</select>
				<input type="text" name="" class="tops_inp" placeholder="검색어를 입력하세요" v-model="searchParams.srchWord" @keyup.enter="searchList()">
				<button type="button" onclick="" class="topBtnSearch" @click="searchList()">검색</button>
			</div>
            <div class="tbHead">                
                <div class="tbHeadR">
                    <button type="button" onclick="" class="btn01" @click="goRegist()">등록</button>
                </div>
            </div>       
			<div class="ListTbWrap">
				<table class="tListm">
					<caption>회원목록</caption>
					<colgroup>
						<col style="width:5%">
						<col style="width:15%">
						<col style="width:10%">
						<col style="width:15%">
                        <col/>
                        <col style="width:10%">						
					</colgroup>
					<thead>
					<tr>
						<th>순번</th>
						<th>기관명</th>
						<th>유형코드</th>
						<th>대표전화</th>
						<th>주소</th>
                        <th>등록일</th>
					</tr>
					</thead>
					<tbody>
					<template v-if="this.total > 0">
						<tr v-for="(list, index) in insttManageList" :key="list.insttSn">
							<td>{{list.insttSn}}</td>
							<td>{{list.insttNm}}</td>
							<td>{{list.insttTyCode}}</td>						
							<td>{{list.reprsntTelno}}</td>
							<td class="alignL">{{list.insttAdres}}</td>
							<td>{{list.creatDt}}</td>                        
						</tr>
					</template>
					<template v-else>
						<tr>
							<td colspan="10" class="noData">데이터가 존재하지 않습니다.</td>
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
import { insttManageList } from "@/api/insttApi";

export default {
        data() {
		return {
			searchParams : {
				srchWord : ""
				, srchType : "1"
				, page: 1
        		, perPageNum: 10
			},
			insttManageList : [],
			total : 0,
		}
	},
	created() {
		this.handleFilter();
	},
	methods: {
	/* 기관관리 조회 */
	searchList() {
		insttManageList(this.searchParams)
		.then(response => {
			console.log("response.data.insttManageList", response.data);
			this.insttManageList = response.data.insttManageList;
			this.total = response.data.insttManageListCnt;
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
		goRegist() {
			this.$router.push({
				path: "/manage/insttRegist",
				query:{
                        status : 'C'
                      }
			});
		},
	},
}
</script>
<style lang="">
    
</style>