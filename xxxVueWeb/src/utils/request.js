import axios from 'axios'

const url = 'https://translation.googleapis.com/language/translate/v2?key=';
const apiKey = 'AIzaSyBMbVbfp0p2H3nzBgjjipMa2SjAWyJbW0c';

export default {
  /*
   * 2021.08.24 LEEMG
   * 구글번역API 호출
   * params [
   *   q: "kotra",    -- 내용
   *   source: "en",  -- 소스 언어
   *   target: "ko",  -- 타겟 언어
   *   format: "text" -- 디폴트
   * ]
   */
  async requestTranslateApi(params) {
    try {
      const response = await axios({
        url: url + apiKey,
        method: 'post',
        data: params
      });
      
      return response.data.data.translations[0].translatedText;
      
    } catch (error) {
  
    }
  }
}