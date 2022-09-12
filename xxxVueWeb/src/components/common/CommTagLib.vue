<template>
  <span>
    <!-- type : date -->
    <div v-if="type == 'date'">
      <date-picker
        valueType="format"
        :lang="lang"
        :format="datePickerFormat"
        :type="datePickerType"
        v-model="stDt"
        ref="commStDt"
        @change="changeDate($event)"
      />
      ~
      <date-picker
        valueType="format"
        :lang="lang"
        :format="datePickerFormat"
        :type="datePickerType"
        v-model="endDt"
        ref="commEndDt"
        @change="changeDate($event)"
      />
    </div>

    <div v-if="type == 'dateByDay'" style="display: inline;">
      <date-picker
        valueType="format"
        :lang="lang"
        :format="datePickerFormat"
        :type="datePickerType"
        v-model="stDt"
        ref="commStDt"
        @change="changeDate($event)"
      />
      ~
      <date-picker
        valueType="format"
        :lang="lang"
        :format="datePickerFormat"
        :type="datePickerType"
        v-model="endDt"
        ref="commEndDt"
        @change="changeDate($event)"
      />

      <button
        type="button"
        class="btn"
        style="margin-left: 10px;"
        :class="day === 7 ? 'color1' : 'color2'"
        @click="btnDayChange(7)"
      >
        1주일전
      </button>
      <button
        type="button"
        class="btn"
        :class="day === 1 ? 'color1' : 'color2'"
        @click="btnDayChange(1)"
      >
        어제
      </button>
      <button
        type="button"
        class="btn"
        :class="day === 0 ? 'color1' : 'color2'"
        @click="btnDayChange(0)"
      >
        오늘
      </button>
    </div>
  </span>
</template>

<script>
import { mapActions } from "vuex";

export default {
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
    grpCd: {
      // 공통코드 grp_cd
      type: String,
      default: ""
    },
    defaultRow: {
      // 기본 로우 사용여부
      type: Boolean,
      default: true
    },
    defaultRowNm: {
      // 기본 로우 사용여부
      type: String,
      default: "선택"
    },
    exclKey: {
      type: Array,
      default: function() {
        return [];
      }
    },
    isDisabled: {
      type: Boolean,
      default: false
    },
    isReadonly: {
      type: Boolean,
      default: false
    },
    value: {}
  },
  data() {
    return {
      baseList: null, // 가공전 LIST DATA
      dataList: null, // 가공후 LIST DATA
      selectValue: "", // SELECT BOX 선택 값
      radioValue: "", // RADIO 선택 값
      checkboxAllValue: false,
      checkboxValue: [] // CHECK BOX 선택 값
    };
  },

  watch: {
    list: "dataListSet",
    value: "valueSet",
    exclKey: "exclKeySet"
  },

  created() {
    if (!_.isEmpty(this.$props.grpCd)) {
      // grp_cd로 공통코드 데이터 셋팅

      this.fetchCommCdAppls({
        // 공통코드 조회
        searchDerivCdGrpNo: this.grpCd
      }).then(response => {
        if (_.isEqual(response.status, 200)) {
          this.baseList = response.data.data;
          this.commListSet();
        }
      });
    } else if (this.$props.list.length > 0) {
      // 넘겨준 list 데이터로 셋팅
      this.dataListSet();
    }

    if (!_.isUndefined(this.$props.value)) {
      // 선택된 값 셋팅
      this.valueSet();
    }
  },

  methods: {
    ...mapActions("commCdMnge", ["fetchCommCdAppls"]),

    /* baselist를 datalist로 가공해서 넣어준다.*/
    commListSet() {
      let commKey = this.dataKey;
      let commValue = this.dataValue;
      let emptyArr = [];
      _.forEach(this.baseList, function(value, key) {
        emptyArr.push({
          commKey: _.get(value, commKey),
          commValue: _.get(value, commValue)
        });
      });
      this.dataList = emptyArr;
      this.exclKeySet();
    },

    /* list data setting*/
    dataListSet() {
      this.baseList = this.list;
      this.commListSet();
    },

    exclKeySet() {
      try {
        if (this.exclKey) {
          this.dataList = this.baseList
            .filter(baseObj => !this.exclKey.some(str => str === baseObj[this.dataKey]))
            .map(baseObj => {
              return {
                commKey: baseObj[this.dataKey],
                commValue: baseObj[this.dataValue]
              };
            });
        }
      } catch (e) {
        if (console) {
          console.log(e.message);
        }
      }
    },

    /* common value change set */
    valueSet() {
      if (_.isEqual(this.type, "select")) {
        this.selectValue = this.value;
      } else if (_.isEqual(this.type, "radio")) {
        this.radioValue = this.value;
      } else if (_.isEqual(this.type, "noAllCheckbox")) {
        this.checkboxValue = this.value;
      } else if (_.isEqual(this.type, "checkbox")) {
        if (_.isNull(this.value)) {
          this.checkboxAllValue = false;
          this.checkboxValue = [];
        } else if (_.isEqual(this.value, []) || _.isEmpty(this.value)) {
          // value가 없는경우 전체체크 false
          this.checkboxAllValue = false;
          this.checkboxValue = [];
        } else if (
          !_.isEmpty(this.dataList) &&
          _.isEqual(this.checkboxValue.length, this.dataList.length)
        ) {
          this.checkboxValue = this.value;
          this.checkboxAllValue = true;
        } else {
          // 정상적인 데이터
          this.checkboxValue = this.value;
        }
      }
    },

    /* selectbox change event */
    changeSelect(e) {
      this.$emit("change-select", this.selectValue);
    },

    /* 전체 checkbox click event */
    allClickCheckbox(e) {
      if (e.target.checked) {
        // 전체 체크일 때 (true)
        let emptyArr = [];
        let commKey = this.dataKey;
        if (this.exclKey) {
          // 제외키값이 존재한다면 가공된 데이터로 조회
          _.forEach(this.dataList, function(value, key) {
            emptyArr.push(_.get(value, "commKey"));
          });
        } else {
          _.forEach(this.baseList, function(value, key) {
            emptyArr.push(_.get(value, commKey));
          });
        }
        this.checkboxValue = emptyArr;
      } else {
        // 전체 체크아닐 때 (false)
        this.checkboxValue = [];
      }
      this.$emit("change-checkbox", this.checkboxValue, !this.checkboxAllValue ? "Y" : "N");
    },

    /* checkbox change event */
    changeCheckbox(e) {
      // 전체 체크박스 사용
      if (_.isEqual("checkbox", this.type)) {
        if (_.isEqual(this.checkboxValue.length, this.dataList.length)) {
          this.checkboxAllValue = true;
        } else {
          this.checkboxAllValue = false;
        }
      }
      this.$emit("change-checkbox", this.checkboxValue, this.checkboxAllValue ? "Y" : "N");
    },

    /* radio change event */
    changeRadio(e) {
      this.$emit("change-radio", this.radioValue);
    }
  }
};
</script>
