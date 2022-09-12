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
				<span>이용신청</span>
				<span><b>회원 가입</b></span>
			</h2>			

			<div class="ListTbWrap mt-20">
				<table class="tList">
					<caption>회원가입폼</caption>
					<colgroup>
						<col style="width:15%">
						<col>					
					</colgroup>
					<tbody>
					<tr>
						<td class="PalignL">아이디<span class="t_red">*</span> </td>
						<td class="PalignL">
                            <input type="text" id="inp_id"  v-model="inputParam.userId" class="regi" placeholder="abc@abc.com" > 
                            <button type="button" @click="idCheck" class="btn02">중복확인</button>
                         </td> 
					</tr>
                    <tr>
						<td class="PalignL"> 비밀번호<span class="t_red">*</span> </td>
						<td class="PalignL">
                            <input type="password" id="inp_id" v-model="inputParam.userPassword" class="pw" >
                            <p class="font_s t_red">[특수문자 +영문 소문자 + 숫자] 조합 8글자 이상 입력해 주세요
                            1234와 같은 연속문자는 사용할 수 없습니다.</p> 
                        </td>
					</tr>
                    <tr>
						<td class="PalignL"> 비밀번호 확인<span class="t_red">*</span> </td>
						<td class="PalignL">
                            <input type="password" id="inp_id" class="pw" v-model="userPasswordCheck" @blur="pwCheck" >
                            <span class="font_s t_blue" v-show="equalShow">일치합니다 </span>
                             <span class="font_s t_red" v-show="notEqualShow">불일치합니다 </span>
                        </td>
					</tr>
                    <tr>
						<td class="PalignL"> 이름<span class="t_red">*</span> </td>
						<td class="PalignL">
                            <input type="text" id="inp_id" class="regi" v-model="inputParam.userNm" ></td>
					</tr>
                    <tr>
						<td class="PalignL"> 이메일<span class="t_red">*</span> </td>
						<td class="PalignL">
                            <input type="text" id="inp_id" class="regi" v-model="inputParam.emailAdres" >
                            <span class="font_s t_red">아이디와 동일한 이메일 주소는 사용할 수 없습니다 </span></td>
					</tr>
                    <tr>
						<td class="PalignL"> 연락처<span class="t_red">*</span> </td>
						<td class="PalignL">
                            <input type="text" id="inp_id" class="phone" v-model="mobile1"> -
                            <input type="text" id="inp_id" class="phone" v-model="mobile2"> -
                            <input type="text" id="inp_id" class="phone" v-model="mobile3"> 
                        </td>
					</tr>
                    <tr>
						<td class="PalignL"> 회원구분<span class="t_red">*</span> </td>
						<td class="PalignL">

                            <input type="radio" id="" checked  name="radio1">
                            <label for="" ><span></span>일반회원</label>

                            <input type="radio" id="" name="radio1">
                            <label for="" class="ml-20"><span></span>관리자</label>
                            
                            <input type="radio" id="" name="radio1">
                            <label for="" class="ml-20"><span></span>개발자</label>
                        </td>
					</tr>
                    <tr>
						<td class="PalignL"> 소속기관</td>
						<td class="PalignL"> 
                            <select name="" class="tops_sel">
                                <option value="" selected="">선택</option>
                                <option value=""></option>
                            </select>
                        </td>
					</tr>
					</tbody>
				</table>
			</div>
            <div class="btn_wrap">	
				<a href="#none" class="btn03">취 소</a>
				<a href="#none" class="btn04" @click="getRegist">가 입</a>
			</div>
			
			<!-- 팝업 - 아이디 체크 -->
			<div class="card3_popup" style="top:160px;" v-show="idCheckShow">
                <div class="popup_body">
                    <!-- 아이디오류 -->
                    <div class="card_mody mt-20">
                        <p class="c_tit">사용가능한 아이디 입니다.</p>               
                    </div>
                </div>
                <button class="btn_m01 blue">확인</button>
                <div class="popup_close">닫기</div>
            </div>
			<!-- 팝업 끝-->
			<!-- 팝업 - 아이디 중복일 때 -->
			<div class="card3_popup" style="top:160px;" v-show="idEqualShow">
                <div class="popup_body">
        
                    <!-- 아이디오류 -->
                    <div class="card_mody mt-20">
                        <p class="c_tit">중복된 ID가 있습니다.</p>               
                    </div>
                </div>
                <button class="btn_m01 blue">확인</button>
                <div class="popup_close">닫기</div>
            </div>
			<!-- 팝업 끝-->
			<!-- 팝업 - 벨리데이트 체크 -->
			<div class="card3_popup" style="top:160px;" v-show="validShow">
                <div class="popup_body">
                    <div class="card_mody mt-20">
                        <p class="c_tit">필수 항목을 입력해 주세요.</p>               
                    </div>
                </div>
                <button class="btn_m01 blue">확인</button>
                <div class="popup_close">닫기</div>
            </div>
			<!-- 팝업 끝-->
			<!-- 팝업 -관리자 벨리데이트 체크 -->
			<div class="card3_popup" style="top:160px;" v-show="adminValidShow">
                <div class="popup_body">
                    <!-- 아이디오류 -->
                    <div class="card_mody mt-20">
                        <p class="c_tit">관리자 / AI개발자의 경우<br>
						소속기관은 필수항목입니다.<br>
						기관 등록이 완료되어야<br>
						회원 가입 신청이 가능합니다.</p>               
                    </div>
                </div>
                <button class="btn_m01 blue">확인</button>
                <div class="popup_close">닫기</div>
            </div>
			<!-- 팝업 끝-->
			<!-- 팝업 - 가입 완료 -->
			<div class="card3_popup" style="top:160px;" v-show="regShow">
                <div class="popup_body">
                    <div class="card_mody mt-20">
                        <p class="c_tit">가입이 완료되었습니다.<br>로그인 화면으로 이동합니다.</p>               
                    </div>
                </div>
                <button class="btn_m01 blue">확인</button>
                <div class="popup_close">닫기</div>
            </div>
			<!-- 팝업 끝-->
			<!-- 팝업-->
			<div class="card3_popup" style="top:160px;" v-show="appShow">
                <div class="popup_body">
                    <!-- 아이디오류 -->
                    <div class="card_mody mt-20">
                        <p class="c_tit">신청이 완료되었습니다.<br>사이트 관리자의 승인 이후<br>로그인이 가능합니다.</p>               
                    </div>
                </div>
                <button class="btn_m01 blue">확인</button>
                <div class="popup_close">닫기</div>
            </div>
			<!-- 팝업 끝-->
		</div><!-- //Content -->        
	</div><!-- //contentWrap -->
</template>
<script>
export default {
    data() {
		return {
            //모달 show
            idCheckShow : false,
            idEqualShow : false,
            validShow : false,
            adminValidShow : false,
            regShow : false,
            appShow : false,
            //show
            equalShow:false,
            notEqualShow:false,
            //파라미터
            userPasswordCheck:"",
            mobile1:"",
            mobile2:"",
            mobile3:"",
			inputParam: {
                userId: "",
                userPassword :"",
                userNm:"",
                emailAdres:"",
                userMbtlnum:""
            },
		}
	},
	created() {
	},
	methods: {
        /* 아이디 체크 */
        idCheck(){

        },
        /*패스워드 체크*/
        pwCheck(){
            if(this.inputParam.userPassword != this.userPasswordCheck ){
                this.notEqualShow = true;
                this.equalShow = false;
            }else{
                this.notEqualShow = false;
                this.equalShow = true;
            }
        },
        /* 저장*/
        getInsert() {
        //validate
        if(this.inputParam.devlopGuideSj == "" 
        || this.inputParam.devlopGuideVerValue == "" 
        || this.inputParam.devlopGuideCn == ""){
            this.validModal = true;
            return false;
        }
        userReg(this.inputParam)
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
        }
	}
}
</script>
<style lang="">
    
</style>