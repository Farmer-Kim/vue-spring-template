import { VueCookieNext } from 'vue-cookie-next'



export function getToken(){
    const userToken = VueCookieNext.getCookie('token')
    return userToken
}


//클라이언트의 쿠키에 Token을 저장
export function setToken(token){
    VueCookieNext.setCookie('token', token, {secure : true, httpOnly : true})
}

export function removeToken(){
    VueCookieNext.removeCookie('token')

}






