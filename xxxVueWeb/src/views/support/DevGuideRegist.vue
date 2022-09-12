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
				<table class="tList">
					<template v-if="this.$route.query.saveType != 'update' ">
					<caption>개발자 가이드 등록</caption>
					</template>
					<template v-else >
					<caption>개발자 가이드 수정</caption>
					</template>
					<colgroup>
						<col style="width:15%">
						<col>					
					</colgroup>
					<tbody>
					<tr>
						<td class="PalignL">제목<span class="t_red">*</span></td>
						<td class="PalignL"><input type="text" id="" class="w100p" v-model="inputParam.devlopGuideSj" placeholder="제목을 입력하세요" > </td> 
					</tr>
                    <tr>
						<td class="PalignL"> 버전<span class="t_red">*</span></td>
						<td class="PalignL"> 
                            <input type="text" id="" placeholder="" v-model="inputParam.devlopGuideVerValue" >
                        </td>
					</tr>
					 <tr>
						<td class="PalignL"> 내용<span class="t_red">*</span></td>
						<td class="PalignL"> 
							<textarea v-model="inputParam.devlopGuideCn"
							style="margin: 0px; width: 765px; height: 328px;"></textarea>
                            <!-- <editor/> -->
                        </td>
					</tr>
					<!-- ToBe -->
                    <!-- <tr>
						<td class="PalignL"> 첨부파일</td>
						<td class="PalignL"> 
							<ul class="down_list">
								<li><a href="#">첨부파일1.jpg</a> <button type="button" onclick="" class="btnFileDel">삭제</button></li>
								<li><a href="#">첨부파일1.jpg</a> <button type="button" onclick="" class="btnFileDel">삭제</button></li>
								<li><a href="#">첨부파일1.jpg</a> <button type="button" onclick="" class="btnFileDel">삭제</button></li>
							</ul>
                        </td>
					</tr> -->
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">	
				<template v-if="this.$route.query.saveType != 'update' ">
					<a href="#" @click="goList" class="btn03">취소</a>
					<a href="#" @click="getRegist" class="btn04">등록</a>		
				</template>
				<template v-else >
					<a href="#" @click="goView" class="btn03">취소</a>
					<a href="#" @click="getRegist" class="btn04">수정</a>	
				</template>
			</div>	

			<!-- 등록 팝업 -->
			<div class="card3_popup" style="display: block; top:160px;" v-show="regModal" >
				<div class="popup_body">        
					<!-- 메시지 -->
					<div class="card_mody mt-20">
						<p class="c_tit">SDK가 등록되었습니다.</p>               
					</div>
				</div>
				<div class="btn_wrap_pop">
					<button @click="goView" class="btn_m01 blue">확인</button>
				</div>
				<div @click="goView" class="popup_close">닫기</div>
			</div>
			<!-- 팝업 끝-->

			<!-- 수정 팝업 -->
			<div class="card3_popup" style="display: block; top:160px;" v-show="modModal" >
				<div class="popup_body">        
					<!-- 메시지 -->
					<div class="card_mody mt-20">
						<p class="c_tit">SDK가 수정되었습니다.</p>               
					</div>
				</div>
				<div class="btn_wrap_pop">
					<button @click="goView" class="btn_m01 blue">확인</button>
				</div>
				<div @click="goView" class="popup_close">닫기</div>
			</div>
			<!-- 팝업 끝-->

			<!-- 팝업-->
			<div class="card3_popup" style="display: block; top:160px;" v-show="validModal" >
				<div class="popup_body">        
					<!-- 메시지 -->
					<div class="card_mody mt-20">
						<p class="c_tit">필수항목을 입력해 주세요.</p>               
					</div>
				</div>
				<div class="btn_wrap_pop">
					<button @click="validModal = false" class="btn_m01 blue">확인</button>
				</div>
				<div @click="validModal = false" class="popup_close">닫기</div>
			</div>
			<!-- 팝업 끝-->
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>

<script>
import {  devGuideView, devGuideReg } from "@/api/supportApi";

let insSn=0;
let upSn=0;
export default {
    data() {
        return {
            //모달 show 파라미터
            regModal : false,
            modModal : false,
            validModal : false,
			//등록 파라미터
			inputParam: {
				dateType: this.$route.query.saveType ,
				devlopGuideSj :"",
				devlopGuideVerValue : "",
				devlopGuideCn : ""
			}
        }
    },
	created() {
		if (this.$route.query.saveType == "update") {
		this.getView();
		}
	},
	methods: {
    /*목록으로 이동 */
    goList() {
      this.$router.push({
        path: "/support/devGuideList"
      });
    },
    /* 저장 or 수정 */
    getRegist() {
      //validate
	  if(this.inputParam.dateType != 'insert'){
		  	this.inputParam.devlopGuideSn =  this.$route.query.devlopGuideSn;
	  }
	  if(this.inputParam.devlopGuideSj == "" 
	  || this.inputParam.devlopGuideVerValue == "" 
	  || this.inputParam.devlopGuideCn == ""){
		this.validModal = true;
		return false;
	  }
	  devGuideReg(this.inputParam)
		.then(response => {
			console.log("code : ", response.data.code);
			if (response.data.code == 200) {
				insSn = response.data.NewDevlopGuideSn;
				this.regModal=true;
			} else {
			alert("등록 실패하였습니다");
			}
		})
		.catch(error => {
			console.log("error", error);
			alert("등록 실패하였습니다");
		});
    //   //업데이트시 기존 파일 삭제를 위한 작업
    //   console.log("this.inputParam", this.inputParam.fileId);
    //   if (this.$route.query.saveType == "update") {
    //     //파일삭제 API 호출 (단일파일)
    //     fileListDelete({ atachId: this.inputParam.atachId, atachSeq: 1 })
    //       .then(response => {
    //         console.log("삭제성공");
    //       })
    //       .catch(error => {});
    //   }
    //   //파일업로드를 위한 파라미터 세팅
    //   this.formData = new FormData();
    //   let arrFileObj = new Array();
    //   arrFileObj.push(this.$refs.file.getFileInfo());
    //   for (var i = 0; i < arrFileObj.length; i++) {
    //     this.formData.append("uploadfile", arrFileObj[0][i]);
    //   }
    //   //파일업로드 API 호출 (단일파일)
    //   fileTest(this.formData)
    //     .then(response => {
    //       //파일 업로드 완료후 DB 처리
    //       if (response.status == 200) {
    //         devGuideReg(this.inputParam)
    //           .then(response => {
    //             console.log("code : ", response.data.code);
    //             if (response.data.code == 200) {
    //               alert("등록 완료되었습니다");
    //             } else {
    //               alert("등록 실패하였습니다");
    //             }
    //           })
    //           .catch(error => {
    //             console.log("error", error);
    //             alert("등록 실패하였습니다");
    //           });
    //       }
    //     })
    //     .catch(error => {
    //       console.log(error);
    //     });
    },
	/* 상세 조회 이동 */
	goView(){
		this.validModal = false;
		 if(this.inputParam.dateType != 'insert'){
		  	insSn =  this.$route.query.devlopGuideSn;
	 	 }
		this.$router.push({
			path: "/support/DevGuideView",
			query:{
				devlopGuideSn :insSn
			}
		});
	},
    /* 상세 조회 */
    getView() {
    	devGuideView({
			devlopGuideSn: this.$route.query.devlopGuideSn
			}).then(response => {
                if(response.status == 200){
                    console.log("response.data.devGuideVo", response.data.vo);
                    this.inputParam = response.data.vo;
					this.inputParam.dateType = this.$route.query.saveType;
                }
			}).catch(error => {
			});
    }
  }
}
</script>
