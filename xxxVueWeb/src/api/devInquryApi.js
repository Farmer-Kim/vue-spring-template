import axios from 'axios';

/* @&A 조회 */
export function devInquryList(params) {
    return axios({
      url: '/api/support/devInquryList',
      method: 'post',
      data: params
    });
  }

/* Q&A 상세 조회 */
export function devInquryView(params) {
  return axios({
    url: '/api/support/devInquryView',
    method: 'post',
    data: params
  });
}

/* Q&A 등록/수정 */
export function devInquryRegist(params) {
  return axios({
    url: '/api/support/devInquryRegist',
    method: 'post',
    data: params
  });
}

/* Q&A 삭제 */
export function devInquryDelete(params) {
  return axios({
    url: '/api/support/devInquryDelete',
    method: 'post',
    data: params
  });
}