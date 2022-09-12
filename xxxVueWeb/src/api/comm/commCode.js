import axios from 'axios'


export function getCodeList(params) {
  return axios({
    url: '/api/commCodeList',
    method: 'post',
    data: params
  })
}

export function commCodeTestRegist(params) {
  return axios({
    url: '/api/commCodeTestRegist',
    method: 'post',
    data: params
  })
}

export function apiTestList(params) {

  return axios({
    headers: {
      'Content-Type': 'application/json'
    },
    url: '/api/apiTestList2',
    method: 'post',
    data: params
  })
}
