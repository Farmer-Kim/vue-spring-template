import axios from 'axios';


//object형태로 옴
export function login(params){
    return axios({
        url: 'http://localhost:8888/api/user/login',
        method : 'post',
        data : params
    });
}

export function logout(token){
    return axios({
        url : 'http://localhost:8888/api/user/logout',
        method : 'post',
        headers : {"Authorization" : "Bearer " + token},
        data : token
    })
}



/**
 * 아이디 비밀번호 찾기
 */
export function searchId(params){

    return axios({
        url : "http://localhost:8888/api/user/searchId",
        method : 'post',
        data : params
    })
}

export function searchPw(params){

    return axios({
        url : "http://localhost:8888/api/user/searchPw",
        method : 'post',
        data : params
    })
}
