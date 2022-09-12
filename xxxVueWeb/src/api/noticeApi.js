import axios from 'axios'


/* 공지사항 등록/수정 */
export function noticeRegist(params) {
  return axios({
    url: '/api/notice/noticeRegist',
    method: 'post',
    data: params
  });
}
/* 공지사항 조회 */
export function noticeList(params) {
  return axios({
    url: '/api/notice/noticeList',
    method: 'post',
    data: params
  });
}
/* 공지사항 삭제 */
export function noticeDelete(params) {
  return axios({
    url: '/api/notice/noticeDelete',
    method: 'post',
    data: params
  });
}
/* 공지사항 상세보기 */
export function noticeView(params) {
  return axios({
    url: '/api/notice/noticeView',
    method: 'post',
    data: params
  });
}
