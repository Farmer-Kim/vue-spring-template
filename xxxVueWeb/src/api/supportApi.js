import axios from 'axios'





/* 개발자 가이드 등록 */
export function devGuideReg(params) {
  return axios({
    url: '/api/support/devGuideReg',
    method: 'post',
    data: params
  });
}
/* 개발자 가이드 목록 조회 */
export function devGuideList(params) {
  return axios({
    url: '/api/support/devGuideList',
    method: 'post',
    data: params
  });
}
/* 개발자 가이드 상세 조회 */
export function devGuideView(params) {
  return axios({
    url: '/api/support/devGuideView',
    method: 'post',
    data: params
  });
}

/* 개발자 가이드 삭제 */
export function devGuideDel(params) {
  return axios({
    url: '/api/support/devGuideDel',
    method: 'post',
    data: params
  });
}