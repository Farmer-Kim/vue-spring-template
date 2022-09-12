<template >
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
			<div class="main_top">
				<ul class="main_info">
					<li>
						<a href="">
						<div class="info_img"><img :src="require(`@/assets/images/main_img1.gif`)"></div>
						<p class="info_tit01">표준 플랫폼 소개</p>
						<p class="info_tit02">고객 이동 전주기 끊임없는 데이터 획득 및 환자중심적 통합 모니터링 </p>
						</a>
					</li>
					<li>
						<a href="">
						<div class="info_img"><img :src="require(`@/assets/images/main_img2.gif`)"></div>
						<p class="info_tit01">개발자 가이드</p>
						<p class="info_tit02">개발자 가이드. 클라이언트 접속. 같은 VPC 서브넷을 이용하는 인스턴스의 </p>
						</a>
					</li>
					<li>
						<a href="">
						<div class="info_img"><img :src="require(`@/assets/images/main_img3.gif`)"></div>
						<p class="info_tit01">이용신청</p>
						<p class="info_tit02">기술지원포털을 통해 개발 이용을 신청하시면 개발자 가이드, SDK , 데이터 추출등</p>
						</a>
					</li>
					<li>
						<a href="">
						<div class="info_img"><img :src="require(`@/assets/images/main_img4.gif`)"></div>
						<p class="info_tit01">다운로드</p>
						<p class="info_tit02">고객 이동 전주기 끊임없는 데이터 획득 및 고객중심적 통합 모니터링 및 AI  </p>
						</a>
					</li>
				</ul>
			</div><!-- //main_top -->
			<div class="noticeWrap">
				<div class="main_notice">
					<div class="notice_top">
						<span>공지사항</span>
						<a href="#">더보기</a>
					</div>
					<ul class="notice_list">
						<li><a href="">기능이용가이드를 참고하여 기능 개선 작업 안내 <span class="date">2021-08-25</span></a></li>
						<li><a href="">프로젝트 등록 관련 약관이 수정되 어  접속에러시 대처<span class="date">2021-08-25</span></a></li>  
						<li><a href="">Test api와 Sample을 제공해 쉽게 개발이 가능하도록<span class="date">2021-08-25</span></a></li>
						<li><a href="">기능이용가이드를 참고하여 기능 개선 작업 안내<span class="date">2021-08-25</span></a></li>
						<li><a href="">프로젝트 등록 관련 약관이 수정되 어  접속에러시 대처<span class="date">2021-08-25</span></a></li>
						<li><a href="">Test api와 Sample을 제공해 쉽게 개발이 가능하도록<span class="date">2021-08-25</span></a></li>
					</ul>
				</div><!-- //main_notice -->
				<div class="main_qna">
					<div class="qna_top">
						<span>질의응답</span>
						<a href="#">더보기</a>
					</div>
					<ul class="qna_list">
						<li><a href="">기능이용가이드를 참고하여 기능 개선 작업 안내 <span class="date">2021-08-25</span></a></li>
						<li><a href="">프로젝트 등록 관련 약관이 수정되 어  접속에러시 대처<span class="date">2021-08-25</span></a></li>  
						<li><a href="">Test api와 Sample을 제공해 쉽게 개발이 가능하도록<span class="date">2021-08-25</span></a></li>
						<li><a href="">기능이용가이드를 참고하여 기능 개선 작업 안내<span class="date">2021-08-25</span></a></li>
						<li><a href="">프로젝트 등록 관련 약관이 수정되 어  접속에러시 대처<span class="date">2021-08-25</span></a></li>
						<li><a href="">Test api와 Sample을 제공해 쉽게 개발이 가능하도록<span class="date">2021-08-25</span></a></li>
					</ul>
				</div><!-- //main_qna -->
			</div><!-- //noticeWrap -->
			<div class="main_banner">
				<ul>
					<li><a href="#"><img :src="require(`@/assets/images/main_bn1.gif`)" alt=""></a></li>
					<li><a href="#"><img :src="require(`@/assets/images/main_bn2.gif`)" alt=""></a></li>
					<li><a href="#"><img :src="require(`@/assets/images/main_bn3.gif`)" alt=""></a></li>
					<li><a href="#"><img :src="require(`@/assets/images/main_bn4.gif`)" alt=""></a></li>
					<li></li>
				</ul>
			</div><!-- //main_banner -->
			

		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>
<script>
export default {
    
}
</script>
<style lang="">
    
</style>