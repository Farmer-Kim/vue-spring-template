import axios from 'axios'


/* 표준 카테고리 관리 등록/수정 */
export function stdCtgryInfoRegist(params) {
  return axios({
    url: '/api/stdCtgry/stdCtgryInfoRegist',
    method: 'post',
    data: params
  });
}
/* 표준 카테고리 관리 조회 */
export function stdCtgryInfoList(params) {
  return axios({
    url: '/api/stdCtgry/stdCtgryInfoList',
    method: 'post',
    data: params
  });
}
/* 표준 카테고리 관리 패스 조회 */
export function stdCtgryPathView(params) {
  return axios({
    url: '/api/stdCtgry/stdCtgryPathView',
    method: 'post',
    data: params
  });
}
/* 레벨별 표준 카테고리 목록 조회 */
export function levelStdCtgryList(params) {
  return axios({
    url: '/api/stdCtgry/levelStdCtgryList',
    method: 'post',
    data: params
  });
}
/* 레벨별 표준 카테고리 목록 조회 */
export function levelStdCtgryUpdate(params) {
  return axios({
    url: '/api/stdCtgry/levelStdCtgryUpdate',
    method: 'post',
    data: params
  });
}
/* 전시 카테고리 관리 등록/수정 */
export function dspyCtgryInfoRegist(params) {
  return axios({
    url: '/api/ctgryMng/dspyCtgryInfoRegist',
    method: 'post',
    data: params
  });
}
/* 전시 카테고리 관리 조회 */
export function dspyCtgryInfoList(params) {
  return axios({
    url: '/api/ctgryMng/dspyCtgryInfoList',
    method: 'post',
    data: params
  });
}

/******************************* 파일테스트*********************************************/
/* 전시 카테고리 관리 조회 */
export function fileTest(formData) {
  return axios({
    url: '/api/ctgryMng/fileTest',
    method: 'post',
    data: formData,
  });
}
/* 전시 카테고리 관리 조회 */
export function download(params) {
  return axios({
    url: '/api/file/download',
    responseType: 'blob',
    data: params,
    method: 'post',
  });
}
/* 전시 카테고리 관리 조회 */
export function stdCtgryView(params) {
  return axios({
    url: '/api/file/stdCtgryView',
    method: 'post',
    data: params
  });
}
/* 전시 카테고리 관리 조회 */
export function fileInfoList(params) {
  return axios({
    url: '/api/file/fileInfoList',
    method: 'post',
    data: params
  });
}
/* 전시 카테고리 관리 조회 */
export function fileListDelete(params) {
  return axios({
    url: '/api/file/fileListDelete',
    method: 'post',
    data: params
  });
}

/* IOMT Server 연동 테스트 */
export function apiTestList(params) {
  return axios({
    headers: {
      'Content-Type': 'application/json'
    },
    url: '/api/apiTestList',
    method: 'post',
    data: params
  })
}
