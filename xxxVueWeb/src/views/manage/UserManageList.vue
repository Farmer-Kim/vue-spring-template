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
				<span><b>회원관리</b></span>
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
				<table class="tListm">
					<caption>회원목록</caption>
					<colgroup>
						<col style="width:5%">
						<col style="width:10%">
						<col style="width:5%">
						<col style="width:15%">
                        <col style="width:15%">
                        <col style="width:10%">
                        <col style="width:10%">
                        <col style="width:5%">
                        <col style="width:5%">
                        <col style="width:15%">						
					</colgroup>
					<thead>
					<tr>
						<th>순번</th>
						<th>ID</th>
						<th>이름</th>
						<th>이메일</th>
						<th>연락처</th>
                        <th>소속기관</th>
                        <th>가입일</th>
                        <th>회원구분</th>
                        <th>승인여부</th>
                        <th>비고</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td colspan="10" class="noData">데이터가 존재하지 않습니다.</td>
					</tr>
					<tr>
						<td>10</td>
						<td class="alignL">abc@abc.co.kr</td>
						<td>홍길동</td>
						<td>kimsy019@naver.com</td>
						<td>010-1234-5678</td>
                        <td>xxxxx</td>
                        <td>2021-10-10</td>
                        <td>일반회원</td>
                        <td>승인</td>
                        <td><button type="button" onclick="" class="btn02_o">승인</button> <button type="button" onclick="" class="btn05_x">반려</button></td>
					</tr>
                    <tr>
						<td>10</td>
						<td class="alignL">abc@abc.co.kr</td>
						<td>홍길동</td>
						<td>kimsy019@naver.com</td>
						<td>010-1234-5678</td>
                        <td>xxxx</td>
                        <td>2021-10-10</td>
                        <td>일반회원</td>
                        <td>승인</td>
                        <td><button type="button" onclick="" class="btn02_o">승인</button> <button type="button" onclick="" class="btn05_x">반려</button></td>
					</tr>
                    <tr>
						<td>10</td>
						<td class="alignL">abc@abc.co.kr</td>
						<td>홍길동</td>
						<td>kimsy019@naver.com</td>
						<td>010-1234-5678</td>
                        <td>xxxx</td>
                        <td>2021-10-10</td>
                        <td>일반회원</td>
                        <td>승인</td>
                        <td><button type="button" onclick="" class="btn06">승인</button></td>
					</tr>
                    <tr>
						<td>10</td>
						<td class="alignL">abc@abc.co.kr</td>
						<td>홍길동</td>
						<td>kimsy019@naver.com</td>
						<td>010-1234-5678</td>
                        <td>디케이아이테크놀로지</td>
                        <td>2021-10-10</td>
                        <td>일반회원</td>
                        <td>승인</td>
                        <td>회원정보불일치</td>
					</tr>
					</tbody>
				</table>
				
				
				<div class="tPages">
					<ul>
						<li class="prev02"><a href="#"><img src="images/btn_first.png"></a></li>
						<li class="prev"><a href="#"><img src="images/btn_prev.png"></a></li>
						<li class="active">1</li><li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">6</a></li>
						<li><a href="#">7</a></li>
						<li><a href="#">8</a></li>
						<li><a href="#">9</a></li>
						<li><a href="#">10</a></li>
						<li class="next"><a href="#"><img src="images/btn_next.png"></a></li>
						<li class="next02"><a href="#"><img src="images/btn_last.png"></a></li>
					</ul>
				</div>
			</div>
             

			<!-- 팝업-->
			<div class="card3_popup" style="display: ; top:160px;">
                <div class="popup_header">상태 변경</div>
                <div class="popup_body">        
                    <!-- 메시지 -->
                    <div class="card_mody">
                        <p class="c_tit">승인하시겠습니까?</p>               
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
			<div class="card3_popup" style="display: ; top:160px;">
                <div class="popup_header">상태 변경</div>
                <div class="popup_body">        
                    <!-- 메시지 -->
                    <div class="card_mody">
                        <p class="c_tit">반려사유를 입력해주세요</p>               
                        <input type="text" name="" class="input_card_name" placeholder="반려사유">
                    </div>
                </div>
				<div class="btn_wrap_pop">
					<button class="btn_m01_50">확인</button>
					<button class="btn_m02_50">취소</button>
				</div>
                <div class="popup_close">닫기</div>
            </div>
			<!-- 팝업 끝-->
			
			
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>
<script>
export default {
	
}
</script>
<style lang="">
	
</style>