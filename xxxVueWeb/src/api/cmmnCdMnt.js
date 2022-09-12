import axios from 'axios'

export function cmmnCdList(params) {
  return axios({
    url: '/api/cmmnCdMnt/cmmnCdList',
    method: 'post',
    data: params
  })
}

export function cmmnCdInfoReg(params) {
  return axios({
    url: '/api/cmmnCdMnt/cmmnCdInfo',
    method: 'post',
    data: params
  })
}

export function test1(params) {
  return axios({
    url: '/api/stdCtgry/stdCtgryPathView',
    method: 'post',
    data: params
  });
}
