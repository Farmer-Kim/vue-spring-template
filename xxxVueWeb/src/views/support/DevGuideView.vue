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
				<span><b>개발자 가이드</b></span>
			</h2>
			<div class="topSearch">
				<select name="" class="tops_sel">
					<option value="" selected>선택</option>
					<option value=""></option>
				</select>
				<input type="text" name="" class="tops_inp" placeholder="검색어를 입력하세요">
				<button type="button" onclick="" class="topBtnSearch">검색</button>
			</div>

			<div class="ListTbWrap mt-20">
				<table class="tView">
					<caption>공지사항 상세보기</caption>
					<colgroup>
						<col>
						<col style="width:12%">
						<col style="width:18%">
						<col style="width:20%">
					</colgroup>
					<thead>
					<tr>
						<th class="alignL bolder">{{devGuideVo.devlopGuideSj}}</th>
						<th><span>버전<b>{{devGuideVo.devlopGuideVerValue}}</b></span></th>
						<th><span>등록일 <b>{{devGuideVo.creatDt}}</b></span></th>
						<th><span>등록자ID <b>{{devGuideVo.crtrSn}}</b></span></th>
					</tr>
					<!-- ToBe -->
					<!-- <tr>
						<th colspan="4" class="alignL"><span>첨부파일</span> <a href="#" class="attatch_file_list">체온_디바이스_개발자가이드_v1.5.pdf</a> <a href="#" class="attatch_file_list">체온_디바이스_개발자가이드_v1.5.pdf</a>
						</th>
					</tr> -->
					</thead>
				</table>
				<div class="view_wrap">
                    {{devGuideVo.devlopGuideCn}}
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
							<template v-if="devGuideVo.beforeDevGuideSn !== 1">
								<a href="#none" @click="goView(devGuideVo.beforeDevGuideSn)">{{devGuideVo.beforeDevGuideSj}}</a>
							</template>
							<template v-else>
								이전 글이 없습니다.	
							</template>
						</td>
                    </tr>
					<tr>
					    <th>다음글</th>
						<td>
							<template v-if="devGuideVo.nextDevGuideSn !== -1">
								<a href="#none" @click="goView(devGuideVo.nextDevGuideSn)">{{devGuideVo.nextDevGuideSj}}</a>
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
				<button type="button" class="btn04" @click="goList" >목 록</button>
				<button type="button" class="btn03"  @click="goRegist" >수 정</button>				
				<button type="button" class="btn03" @click="delModal = true">삭 제</button>
			</div>	
			<!-- 팝업-->
			<div class="card3_popup" style="display: block; top:160px;" v-show="delModal">
				<div class="popup_body">        
					<!-- 메시지 -->
					<div class="card_mody mt-20">
						<p class="c_tit">SDK와 개발자가이드를 삭제하시겠습니까?</p>               
					</div>
				</div>
				<div class="btn_wrap_pop">
					<button class="btn_m01_50" @click="getDelete">확인</button>
					<button class="btn_m02_50" @click="delModal = false">취소</button>
				</div>
				<div class="popup_close" @click="delModal = false">닫기</div>
			</div>
			<!-- 팝업 끝-->
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>

<script>
import { devGuideView, devGuideDel } from "@/api/supportApi";

const devlopGuideForm = {
  dspyCtgryCd: "",
  ctgryDp: "",
  engName: "",
  engPath: "",
  useYn: "",
  dcCn: ""
};

export default {
	data() {
		return {
            delModal:false,
			searchParam: {
                devlopGuideSn: this.$route.query.devlopGuideSn
            },
			devGuideVo : Object.assign({}, devlopGuideForm),
			deletePopup : false
		}
	},
	created() {
		this.getView();
	},
	methods: {
        /*상세 조회 */
		getView() {
			devGuideView({
				devlopGuideSn: this.searchParam.devlopGuideSn
			}).then(response => {
                if(response.status == 200){
                    console.log("response.data.devGuideVo", response.data.vo);
                    this.devGuideVo = response.data.vo;
                }
			}).catch(error => {
			});
		},
        /*개발자 가이드 삭제 */
        getDelete(){
            devGuideDel({
				devlopGuideSn: this.searchParam.devlopGuideSn
			}).then(response => {
               if (response.data.code == 200) {
                	this.goList();
                }
			}).catch(error => {
			});
        },
        goList(){
            this.$router.push({
                path: "/support/DevGuideList"
			});
        },
        /* 이전글, 다음글 이동 */
		goView(devlopGuideSn) {
			this.$router.push({
                path: "/support/DevGuideView",
				query:{
                        devlopGuideSn:devlopGuideSn,
                      }
			});
		},
         /* 수정 페이지 이동 */
        goRegist(){
            this.$router.push({
                path: "/support/DevGuideRegist",
				query:{
                        devlopGuideSn : this.$route.query.devlopGuideSn,
                        saveType : 'update'
                      }
			});
        }
	},
}
</script>

<style>

</style>