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
				<span>데이터 관리</span>
				<span><b>데이터 추출</b></span>
			</h2>
			<h3>데이터 추출</h3>
			<div class="filter_area">	
				<div class="filter_row">
					<label for="" class="filter_title">기간</label>
					<input type="text" name="dettask_dettask_start_dt" id="" class="date" readonly="">
					<span class="mt-5 mr-5">~</span>
					<input type="text" name="dettask_dettask_end_dt" id="" class="date" readonly="">	
				</div>
				<div class="filter_row mt-20">
					<label for="" class="filter_title">추출데이터 <span class="font_s">(다중선택가능)</span></label>
					<input type="checkbox" name="" id="result01"><label for="result01"><span></span>환자정보</label>
					<input type="checkbox" name="" id="result02"><label for="result02" class="ml-20"><span></span>PMS</label>
					<input type="checkbox" name="" id="result03"><label for="result03" class="ml-20"><span></span>수액</label>
					<input type="checkbox" name="" id="result04"><label for="result04" class="ml-20"><span></span>약물</label>
					<input type="checkbox" name="" id="result05"><label for="result05" class="ml-20"><span></span>체온</label>					
				</div>

				<button type="button" onclick="" class="topBtnSearch2">추출시작</button>
			</div>

			

			<!-- 팝업-->
			<div class="card3_popup" style="display: block; top:160px;" v-show="readyModeal">
				<div class="popup_body">        
					<!-- 메시지 -->
					<div class="card_mody mt-20">
						<p class="c_tit">데이터 추출이 시작되면<br>취소할 수 없습니다.<br>추출을 시작하시겠습니까?</p>               
					</div>
				</div>
				<div class="btn_wrap_pop">
					<button class="btn_m01_50">확인</button>
					<button class="btn_m02_50">취소</button>
				</div>
				<div class="popup_close">닫기</div>
			</div>
			<!-- 팝업 끝-->

			<!-- 팝업-->
			<div class="card3_popup" style="display: block; top:160px;" v-show="loadingModeal">
				<div class="popup_body">        
					<!-- 메시지 -->
					<div class="card_mody mt-20">
						<p class="c_tit">데이터 추출이 시작되었습니다.<br>진행 상황은 ‘추출 이력’ 메뉴에서<br>확인 가능합니다.</p>               
					</div>
				</div>
				<div class="btn_wrap_pop">
					<button class="btn_m01 blue">확인</button>
				</div>
				<div class="popup_close">닫기</div>
			</div>
			<!-- 팝업 끝-->
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>

<script>
import { devGuideList } from "@/api/dataApi";

export default {
    data(){
		
	},
	methods :{
	/* 엑셀 다운로드 */
    excelDown(type) {
      if (this.total === 0) {
        alert("검색내용이 없습니다");
        return;
      }
      dataCsvDnld(this.searchParams).then(response => {
        if (_.isEqual(response.status, 200)) {
          const url = window.URL.createObjectURL(
            new Blob([response.data], {
              type: response.headers["content-type"]
            })
          );
          const link = document.createElement("a");
          const filename = decodeURI(response.headers["content-disposition"].split("filename=")[1]);
          link.href = url;
          link.setAttribute("download", filename);
          document.body.appendChild(link);
          link.click();
        }
      });
    },
	}
}
</script>
<style lang="">
    
</style>