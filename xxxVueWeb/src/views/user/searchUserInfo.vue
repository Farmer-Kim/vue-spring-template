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
				<span>로그인</span>
				<span><b>계정찾기</b></span>
			</h2>	
			<div class="login_box2">
				<div class="login_area_wrap2">	
                    
					<div class="login_area">
                        <span>가입시 입력한 이름과 이메일 주소를 입력하세요.</span>
						<div class="login_inp id">
							<label for="inp_id">이름</label>
							<span class="input_txt">
								<input type="text" id="inp_id" class="ime_en" v-model="nickname">
							</span>
						</div>

						<div class="login_inp">
							<label for="inp_pw">이메일</label>
							<span class="input_txt">
								<input type="text" id="inp_pw" v-model="userEmail">
							</span>
						</div>

					</div>
				</div>				
			</div>
            <div class="btn_wrap">	
				<a href="javascript:void(0)" @click="searchIdByUserInfo" class="btn03">아이디 찾기</a>
				<a href="javascript:void(0)" @click="searchPwByUserInfo" class="btn04">비밀번호 찾기</a>
			</div>
			<div class="card3_popup" v-bind:class="{'active': isActive, 'nonActive':nonActive}">
                <div class="popup_header">아이디 찾기</div>
                <div class="popup_body">
        
                    <!-- 아이디오류 -->
                    <div class="card_mody">
                        <p class="c_tit">입력하신 메일 주소로
							ID(or 새로운 비밀번호)를 발송했습니다. <br>
							메일이 수신되지 않았을 경우
							스팸 메일함을 확인해 주세요.
							</p>               
                    </div>
                </div>
        
                <button class="btn_m01 blue" @click="closeBtn">확인</button>
        
                <div class="popup_close" @click="closeBtn">닫기</div>
            </div>
		</div><!-- //Content -->
	</div><!-- //contentWrap -->
</template>

<script>
import {searchId, searchPw} from '../../api/user';


export default {
	name : "searchIdOrPassword",
	data(){
		return{
			nickname:"",
			userEmail : "",
			isActive : false,
			nonActive : true
		}
	},
	methods :{
		searchIdByUserInfo(){
			const userInfo = {
				nickname : this.nickname,
				userEmail : this.userEmail
			}
			searchId(userInfo).then(res => {
				console.log(res)
				if(res.data == true){
					this.isActive = true;
					this.nonActive = false;
				}
				else{
					alert("이름과 이메일을 다시 확인해주세요.")
				}
			})
		},
		searchPwByUserInfo(){
			const userInfo ={
				nickname : this.nickname,
				userEmail : this.userEmail
			}
			searchPw(userInfo).then(res =>{
				if(res.data == true){
					this.isActive = true;
					this.nonActive = false;
				}
				else{
					alert("이름과 이메일을 다시 확인해주세요.")
				}
			})
		},

		closeBtn(){
			this.isActive = false;
			this.nonActive = true;
		}
	}
}
</script>

<style>


.active{
	display: block; 
	top:160px;
}
.nonActive{
	display: none;
	top:160px;
}

</style>