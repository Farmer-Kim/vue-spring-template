/**
 * 날짜 관련 Util 함수
 */
import _, {
  isNil,
  size,
  replace
} from 'lodash';

import * as moment from 'moment';
import Constants from '@/utils/constants.js';

moment.locale('ko')

export default {

  /**
   * 해당 날짜에 증가수 만큼 일 계산
   * @param {*} dateParam		날짜(ex: 20190101)
   * @param {*} addCnt		증가수
   * @param {*} dateFormat	날짜 포맷(ex: YYYY-MM-DD)
   * @return 계산된 날짜
   */
  fnAddDay(dateParam, addCnt, dateFormat) {
    return this.fnAddDate(dateParam, addCnt, dateFormat, "days");
  },

  /**
   * 해당 날짜에 증가수 만큼 월 계산
   * @param {*} dateParam		날짜(ex: 20190101)
   * @param {*} addCnt		증가수
   * @param {*} dateFormat	날짜 포맷(ex: YYYY-MM-DD)
   * @return 계산된 날짜
   */
  fnAddMonth(dateParam, addCnt, dateFormat) {
    return this.fnAddDate(dateParam, addCnt, dateFormat, "month");
  },

  /**
   * 해당 날짜에 증가수 만큼 년 계산
   * @param {*} dateParam		날짜(ex: 20190101)
   * @param {*} addCnt		증가수
   * @param {*} dateFormat	날짜 포맷(ex: YYYY-MM-DD)
   * @return 계산된 날짜
   */
  fnAddYear(dateParam, addCnt, dateFormat) {
    return this.fnAddDate(dateParam, addCnt, dateFormat, "year");
  },

  /**
   * 날짜형식변경(20010101 - 2001/01/01)
   * @param {*} dateParam		날짜값 (ex: 20190101)
   * @param {*} addDayCnt		증가값
   * @param {*} dateFormat	날짜 포맷(ex: YYYY-MM-DD)
   * @param {*} addGbn		년/월/일 구분
   */
  fnAddDate(dateParam, addCnt, dateFormat, addGbn) {
    let dateVal = _.replace(_.replace(dateParam, /-/g, ""), /\//g, "");
    if (_.isNil(dateVal)) {
      // 날짜 데이터가 없는 경우
      return '';
    }
    if (_.size(dateVal) > 8) {
      // 날짜 데이터가 8자리 이상인 경우
      return '';
    }
    if (_.isNil(dateFormat)) {
      // 날짜 데이터가 없는 경우
      dateFormat = Constants.DATE_FORMAT;
    }
    if (_.isNil(addCnt)) {
      // 계산할 날짜 없는 경우 0으로 초기화
      addCnt = 0;
    }
    if (_.isNil(addGbn)) {
      // 구분이 없는 경우 일수로 초기화
      addGbn = "days";
    }

    return moment(dateVal, 'yyyyMMdd').add(addCnt, addGbn).format(dateFormat);
  },

  /**
   * 오늘날짜 조회
   * @param {*} dateFormat	날짜 포맷(ex: YYYY-MM-DD)
   * @return 오늘날짜
   */
  fnToday(dateFormat) {
    if (_.isNil(dateFormat)) {
      // 날짜 데이터가 없는 경우
      dateFormat = Constants.DATE_FORMAT;
    }

    return moment().format(dateFormat);
  },

  /**
   * 기준월의 시작일, 종료일 조회
   * @param {*} dateFormat	날짜 포맷(ex: YYYY-MM-DD)
   * @return json 형태로 시작일, 종료일 리턴(startDate, endDate)
   */
  fnMonthDateRange(dateParam, dateFormat) {
    let dateVal = _.replace(_.replace(dateParam, /-/g, ""), /\//g, "");
    if (_.isNil(dateVal)) {
      // 날짜 데이터가 없는 경우
      return '';
    }
    if (_.size(dateVal) > 8) {
      // 날짜 데이터가 8자리 이상인 경우
      return '';
    }
    if (_.isNil(dateFormat)) {
      // 날짜 데이터가 없는 경우
      dateFormat = Constants.DATE_FORMAT;
    }

    // 년월 추출
    let year = moment(dateVal, 'yyyyMMdd').format("yyyy");
    let month = moment(dateVal, 'yyyyMMdd').format("MM");

    var startDate = moment([year, month]).add(-1, "month"); // 시작일 추출
    var endDate = moment(startDate).endOf('month'); // 종료일 추출

    // format적용해서 리턴
    return {
      startDate: startDate.format(dateFormat),
      endDate: endDate.format(dateFormat)
    };
  },

  /**
   * 시작일/종료일 기간 일수 조회
   * @param {*} startDate	시작일(ex: 20190101)
   * @param {*} endDate	종료일(ex: 20190131)
   * @return 오늘날짜
   */
  fnDatePeriodCnt(startDate, endDate) {
    let startDateVal = _.replace(_.replace(startDate, /-/g, ""), /\//g, "");
    let endDateVal = _.replace(_.replace(endDate, /-/g, ""), /\//g, "");

    if (_.isNil(startDateVal) || _.isNil(endDateVal)) {
      // 날짜 데이터가 없는 경우
      alert("날짜를 확인1 해주세요.");
      return '';
    }

    if (_.size(startDateVal) > 8 || _.size(endDateVal) > 8) {
      // 날짜 데이터가 8자리 이상인 경우
      alert("!!!!!!!!!!" + _.size(startDateVal));
      alert("@@@@@@@@@@" + _.size(endDateVal));
      alert("날짜를 확인2 해주세요.");
      return '';
    }

    let periodCnt = moment(startDateVal, "yyyyMMdd").diff(endDateVal, "days") * -1;
    return periodCnt + (periodCnt >= 0 ? 1 : -1);
  },

  /**
   * 오늘에서 이전 날짜 조회
   * @param {*} minusCnt	7일전,7주전,7개월전,7년전(ex: 7)
   * @param {*} dateType	이전 날짜 단위(ex: 일:'days',주:'weeks',월:'months',년:'years')
   * @param {*} dateFormat 날짜 포맷(ex: YYYY-MM-DD)
   * @return 이전날짜
   */
  fnSetTerm(minusCnt, dateType, dateFormat) {
    if (_.isNil(minusCnt)) {
      // 구하고 싶은 이전 날짜가 없는 경우
      minusCnt = 7;
    }

    if (_.isNil(dateType)) {
      // 이전 날짜 단위가 없는 경우
      dateType = 'd';
    }

    if (_.isNil(dateFormat)) {
      // 날짜 데이터가 없는 경우
      dateFormat = Constants.DATE_FORMAT;
    }

    return moment().subtract(minusCnt, dateType).format(dateFormat);
  }
}
