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
				<span><b>로그인</b></span>
			</h2>
			<div class="login_box">
				<div class="login_area_wrap">	
					<div class="login_area">
						<div class="login_inp id">
							<label for="inp_id">아이디</label>
							<span class="input_txt">
								<input type="text" id="inp_id" class="ime_en" v-model="username">
							</span>
						</div>

						<div class="login_inp">
							<label for="inp_pw">패스워드</label>
							<span class="input_txt">
								<input type="password" id="inp_pw" v-model="password">
							</span>
						</div>
											
						<a href="javascript:void(0)" @click="doLogin"  class="btn_orange login_btn">로그인</a> 
									
						<div class="btm_cont"> 
								<div class="link_area">
									<router-link to="searchUserInfo"> 
										<a class="list_link">아이디/비번찾기</a>
									</router-link>
									<a href="" class="list_link">회원가입</a>
								</div>
						</div>

					</div>
				</div>				
			</div>
		</div><!-- //Content -->      





	</div><!-- //contentWrap -->
</template>

<script>
import {mapActions, mapState} from 'vuex'

const userStore = 'userStore';

export default {
	name : "LoginPage",
	data(){
		return{
				username : '',
				password : ''
		}
	},
	

	methods :{
		...mapActions(userStore, ['login', 'getInfo']),
		doLogin() {
			const userInfo = {
				username : this.username,
				password : this.password
			}
			this.login(userInfo)
		},
		
	}
}


</script>
<style lang="">
    
</style>