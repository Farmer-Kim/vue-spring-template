
import { login, logout} from '@/api/user'
import {getToken, setToken, removeToken} from '@/utils/token'
import { resetRouter } from '@/router'


const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: ''
  }
}

const state = getDefaultState()

const mutations = {
  //리셋
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  //토큰 저장하기
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  //이름 설정
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

/**
 * Promise의 목적
 * 기존 javascript에서 비동기 처리를 순차적으로 처리할 때
 * 콜백(Call back) 형식으로 처리하면서 예외 처리가 어렵다는 단점이 있었다.
 * 이 부분을 보완하기 위해서 promise 를 사용
 * 비동거 처리를 성공했을 때는 resolve를, 실패 했을 때는
 * reject를 실행하도록 해서 사용
 */

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo;
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        
        
        console.log("user login")

        //const { data } = response.data
        //console.log("data.token =======>" + response.data.token)
        //console.log("config.data =======>" + response.config.data)

        commit('SET_TOKEN', response.data.token)
        setToken(response.data.token)


        if(state.token != undefined){

          resolve(location.href = "http://localhost:8080/")
        }
        else{
          alert("아이디 혹은 비밀번호가 틀립니다.")
        }

      }).catch(error => {
        reject(error)
      })
    })
  },


  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      
      getInfo(state.token).then(response => {
        const data = {
          name : response.data.name,
          avatar :{

            authority : response.data.principal.authorities[0].authority,
            accountNonExpired : response.data.principal.accountNonExpired,
            accountNonLocked : response.data.principal.accountNonLocked,
            credentialsNonExpired : response.data.principal.credentialsNonExpired,
            enabled : response.data.principal.enabled
          }
          
        }
        if (!data) {
          return reject('Verification failed, please Login again.')
        }
        
        const { name, avatar } = data
        
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)

        console.log(state.name)
        console.log(state.token)
        console.log(state.avatar)

        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve(location.href="http://localhost:8080/")
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

