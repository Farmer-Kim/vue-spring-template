import axios from 'axios'

/* 기관관리 조회 */
export function insttManageList(params) {
  return axios({
    url: '/api/manage/insttManageList',
    method: 'post',
    data: params
  });
}

/* 기관 등록 */
export function insttRegist(params) {
  return axios({
    url: '/api/manage/insttRegist',
    method: 'post',
    data: params
  });
}