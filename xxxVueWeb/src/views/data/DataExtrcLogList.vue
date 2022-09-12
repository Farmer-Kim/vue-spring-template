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
				<span><b>추출이력</b></span>
			</h2>

			<div class="topSearch">
				<select name="" class="tops_sel">
					<option value="" selected>선택</option>
					<option value="">추출 시작 일시</option>
					<option value="">상태</option>
					<option value="">담당자ID</option>
					<option value="">상태</option>
				</select>
				<input type="text" name="" class="tops_inp" placeholder="검색어를 입력하세요">
				<button type="button" onclick="" class="topBtnSearch">검색</button>
			</div>


			<div class="ListTbWrap mt-20">

				<table class="tList">
					<caption>추출이력 리스트</caption>
					<colgroup>
						<col style="width:8%">
						<col>
						<col style="width:10%">
						<col style="width:10%">
						<col style="width:12%">
						<col style="width:10%">
						<col style="width:10%">
						<col style="width:10%">
						<col style="width:10%">						
					</colgroup>
					<thead>
					<tr>
						<th>순번</th>
						<th>데이터 검색옵션</th>
						<th>추출시작일시</th>
						<th>추출종료일시</th>
						<th>파일 접근 경로</th>
						<th>파일명</th>
						<th>소속기관</th>
						<th>담당자ID</th>
						<th>상태</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td colspan="9" class="noData">데이터가 존재하지 않습니다.</td>
					</tr>
					<tr>
						<td>10</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-1">진행중</label></td>
					</tr>
					<tr>
						<td>9</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-2">성공</label></td>
					</tr>
					<tr>
						<td>8</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-3">실패</label></td>
					</tr>
					<tr>
						<td>7</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-1">진행중</label></td>
					</tr>
					<tr>
						<td>6</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-1">진행중</label></td>
					</tr>
					<tr>
						<td>5</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-1">진행중</label></td>
					</tr>
					<tr>
						<td>4</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-1">진행중</label></td>
					</tr>
					<tr>
						<td>3</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-1">진행중</label></td>
					</tr>
					<tr>
						<td>2</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-1">진행중</label></td>
					</tr>
					<tr>
						<td>1</td>
						<td></td>
						<td>2021-09-03</td>
						<td>2021-09-03</td>
						<td>cccccc</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td>dddddd</td>
						<td><label class="lb03-1">진행중</label></td>
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
			
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>
<script>
export default {
    
}
</script>
<style lang="">
    
</style>