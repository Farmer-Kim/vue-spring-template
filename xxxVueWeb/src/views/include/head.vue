<template>
	<!-- 헤더영역 -->
	<header>
		<div id="headerWrap">
			<h1 >
				<a href="#">
					<router-link to="/" >
						<span></span>
					</router-link>
					</a>
			</h1>
			<div class="topLink">
				<a href="#" class="topLink">
						<div v-if="loginState === false">
							<router-link to="/user/Login">
								<span>로그인</span>
							</router-link>
						</div>
						<div v-else-if="loginState === true">
							<span @click="Logout">로그아웃</span>
						</div>
				</a>
			</div>
			<div class="topNav">
				<ul class="topNavList">
					<li>
						<a href="#"><span>이용가이드</span></a>
						<div class="subNavi sub01" style="display: none;">
							<ul class="deps1">
								<li> 
									<router-link to="/notice/serviceInfo" >
									서비스 소개
									</router-link>
								</li>
								<li> 
									<router-link to="/notice/noticeList" >
									공지 사항
									</router-link>
								</li>
							</ul>
						</div><!-- sub_navigation -->
					</li>
					<li>
						<a href="#"><span>개발지원</span></a>
						<div class="subNavi sub02" style="display: none;">
							<ul class="deps1">
								<li> 
									<router-link to="/support/devSupport" >
									개발지원
									</router-link>
								</li>
								<li> 
									<router-link to="/support/devGuideList" >
									개발자가이드
									</router-link>
								</li>
								<li> 
									<router-link to="/support/devInquryList" >
									Q&A
									</router-link>
								</li>
							</ul>
						</div><!-- sub_navigation -->
					</li>
					<li>
						<a href="#"><span>이용신청</span></a>
						<div class="subNavi sub03" style="display: none;">
							<ul class="deps1">
								<li> 
									<router-link to="/user/userRegist" >
									회원가입
									</router-link>
								</li>
								<li> 
									<router-link to="/user/insttRegist" >
									기관등록
									</router-link>
								</li>
							</ul>
						</div><!-- sub_navigation -->
					</li>
					<li>
						<a href="#"><span>데이터관리</span></a>
						<div class="subNavi sub04" style="display: none;">
							<ul class="deps1">
								<li> 
									<router-link to="/data/dataColct" >
									수집현황
									</router-link>
								</li>
								<li> 
									<router-link to="/data/dataExtrc" >
									데이터추출
									</router-link>
								</li>
								<li> 
									<router-link to="/data/dataExtrcLogList" >
									추출이력
									</router-link>
								</li>
							</ul>
						</div><!-- sub_navigation -->
					</li>
					<li>
						<a href="#"><span>서비스관리</span></a>
						<div class="subNavi sub05" style="display: none;">
							<ul class="deps1">
								<li> 
									<router-link to="/manage/userInfoView" >
									회원정보
									</router-link>
								</li>
								<li> 
									<router-link to="/manage/userManageList" >
									회원관리
									</router-link>
								</li>
								<li> 
									<router-link to="/manage/insttManageList" >
									기관관리
									</router-link>
								</li>
							</ul>
						</div><!-- sub_navigation -->
					</li>
				</ul><!-- //depth1 -->
			</div><!-- //topNav -->
		</div><!-- //headerWrap -->
	</header>
	<!--// 헤더영역 -->
</template>

<script>
import {VueCookieNext} from 'vue-cookie-next';
import {mapActions} from 'vuex';

const  userStore = 'userStore';

export default {
	data(){
		return {
			loginState : false,
		}
	},
	mounted(){
		if(VueCookieNext.getCookie('token') != undefined){
			this.loginState = true
		}
		else{
			this.loginState = false;
		}
	},
	methods :{
		...mapActions(userStore, ['logout']),
		Logout(){
			const ok = confirm('로그아웃 하시겠습니까')
			if(ok){
				this.logout();
			}
		}
	}
}
</script>