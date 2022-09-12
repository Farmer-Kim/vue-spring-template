<script setup>
import Datepicker from "vue3-datepicker";
</script>
<template>
  <span>
    <!-- type : date -->
    <div v-if="type == 'date'">
      <datepicker
        :locale="lang"
        :inputFormat="datePickerFormat"
        v-model="stDt"
        @change="changeDate($event)"
      />
      ~
      <datepicker
        :locale="lang"
        :inputFormat="datePickerFormat"
        v-model="endDt"
        @change="changeDate($event)"
      />
    </div>
  </span>
</template>

<script>
import { ko } from "date-fns/locale";
import DateUtil from "@/utils/date-util.js";

export default {
  components: {
    datepicker: Datepicker
  },
  name: "CommType",
  props: {
    type: {
      // 타입(select , checkbox, radio)
      type: String,
      default: ""
    },
    list: {
      // 공통코드가 아닌 list 데이터
      type: Array,
      default: function() {
        return [];
      }
    },
    dataKey: {
      // list 에서 key로 사용
      type: String,
      default: "cd"
    },
    dataValue: {
      // list 에서 value 로 사용(화면노출용)
      type: String,
      default: "derivCdNm"
    },
    defaultDateType: {
      type: Number,
      default: -1
    },
    stDtValue: {
      type: String,
      default: ""
    },
    endDtValue: {
      type: String,
      default: ""
    },
    datePickerFormat: {
      type: String,
      default: "yyyy-MM-dd"
    },
    datePickerType: {
      type: String,
      default: "date"
    },
    value: {}
  },
  data() {
    return {
      lang: ko,
      day: -1, // date : 검색기간
      stDt: "", // date : 시작일
      endDt: "", // date : 종료일
      stDtHour: "00",
      stDtMin: "00",
      endDtHour: "00",
      endDtMin: "00",
      baseList: null, // 가공전 LIST DATA
      dataList: null
    };
  },
  watch: {
    stDt: "stDtSet",
    endDt: "endDtSet",
    stDtHour: "stDtHourSet",
    stDtMin: "stDtMinSet",
    endDtHour: "endDtHourSet",
    endDtMin: "endDtMinSet",
    defaultDateType: "dateTypeSet",
    stDtValue: "stDtValueSet",
    endDtValue: "endDtValueSet"
  },
  created() {
    if (!_.isUndefined(this.$props.defaultDateType)) {
      // 선택된 값 셋팅
      if (this.$props.defaultDateType > -1) {
        this.dateTypeSet();
        this.changeDateRadio();
      }
    }

    if (!_.isUndefined(this.$props.stDtValue) && !_.isEmpty(this.$props.stDtValue)) {
      // 선택된 값 셋팅
      this.stDtValueSet();
    }
    if (!_.isUndefined(this.$props.endDtValue) && !_.isEmpty(this.$props.endDtValue)) {
      // 선택된 값 셋팅
      this.endDtValueSet();
    }
  },

  methods: {
    /* 기본 날짜 설정 셋팅 */
    dateTypeSet() {
      this.day = this.defaultDateType;
    },
    /* 검색 시작일 셋팅 */
    stDtSet() {
      this.$emit(
        "change-date",
        this.type === "dateByTime"
          ? this.stDt
            ? `${this.stDt}${this.stDtHour ? ` ${this.stDtHour}` : ""}${
                this.stDtMin ? `:${this.stDtMin}` : ""
              }`
            : ""
          : this.stDt,
        "",
        "st"
      );
    },
    endDtSet() {
      this.$emit(
        "change-date",
        "",
        this.type === "dateByTime"
          ? this.endDt
            ? `${this.endDt}${this.endDtHour ? ` ${this.endDtHour}` : ""}${
                this.endDtMin ? `:${this.endDtMin}` : ""
              }`
            : ""
          : this.endDt,
        "dt"
      );
    },
    stDtHourSet() {
      this.$emit(
        "change-date",
        this.stDtHour
          ? `${this.stDt}${this.stDtHour ? ` ${this.stDtHour}` : ""}${
              this.stDtMin ? `:${this.stDtMin}` : ""
            }`
          : "",
        "",
        "st"
      );
    },
    stDtMinSet() {
      this.$emit(
        "change-date",
        this.stDtMin
          ? `${this.stDt}${this.stDtHour ? ` ${this.stDtHour}` : ""}${
              this.stDtMin ? `:${this.stDtMin}` : ""
            }`
          : "",
        "",
        "st"
      );
    },
    endDtHourSet() {
      this.$emit(
        "change-date",
        "",
        this.endDtHour
          ? `${this.endDt}${this.endDtHour ? ` ${this.endDtHour}` : ""}${
              this.endDtMin ? `:${this.endDtMin}` : ""
            }`
          : "",
        "dt"
      );
    },
    endDtMinSet() {
      this.$emit(
        "change-date",
        "",
        this.endDtMin
          ? `${this.endDt}${this.endDtHour ? ` ${this.endDtHour}` : ""}${
              this.endDtMin ? `:${this.endDtMin}` : ""
            }`
          : "",
        "dt"
      );
    },

    /* 시작일 셋팅 후 계산 */
    stDtValueSet() {
      this.stDt = this.stDtValue;
      this.changeDate();
      alert("this.stDt" + this.stDt);
      alert("this.endDt" + this.endDt);
      if (!_.isNil(this.stDt) && !_.isNil(this.endDt)) {
        if (DateUtil.fnDatePeriodCnt(this.stDt, this.endDt) - 1 < 0) {
          alert("시작일을 종료일보다 이전 날짜로 선택해주세요.");
          this.stDt = this.endDt;
        }
      }
    },

    /* 종료일 셋팅 후 계산 */
    endDtValueSet() {
      this.endDt = this.endDtValue;
      this.changeDate();
      if (!_.isNil(this.stDt) && !_.isNil(this.endDt)) {
        if (DateUtil.fnDatePeriodCnt(this.stDt, this.endDt) - 1 < 0) {
          alert("시작일을 종료일보다 이전 날짜로 선택해주세요.");
          this.endDt = this.stDt;
        }
      }
    },

    /* st, dt change event */
    changeDate() {
      if (_.isEqual(DateUtil.fnToday(), this.endDt)) {
        this.day = DateUtil.fnDatePeriodCnt(this.stDt, this.endDt) - 1;
      } else {
        this.day = -1;
      }
    },

    /* date radio change event */
    changeDateRadio(e) {
      this.endDt = DateUtil.fnToday();
      this.stDt = DateUtil.fnAddDay(DateUtil.fnToday(), -this.day, "YYYY-MM-DD");
    },

    padDt(idx) {
      return String(idx - 1).length === 1 ? `0${idx - 1}` : idx - 1;
    },

    btnDayChange(nDay) {
      this.day = nDay;
      this.changeDateRadio();
    }
  }
};
</script>
