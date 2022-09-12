import axios from 'axios';

/* 데이터 엑셀 다운로드 */
export function dataCsvDnld(params) {
    return axios({
      url: '/api/data/dataCsvDnld',
      method: 'post',
      responseType: 'blob',
      data: params,
    });
  }