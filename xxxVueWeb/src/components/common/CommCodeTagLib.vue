<template>
    <div style="display: inline;">
        <!-- type : select -->
        <template v-if="type === 'select'">
            <span
                class="sp-conts-select"
                style="float:left;"
            >
                <select
                    @change="changeSelect($event)"
                    v-model="selectValue"
                    ref="commSelect"
                >
                    <option
                        v-if="defaultRow"
                        value=""
                    >
                        {{defaultRowNm}}
                    </option>
                    <option
                        v-for="item in dataList"
                        :key="item.codeId"
                        :value="item.codeId"
                    >
                        {{item.codeNm}}
                    </option>
                </select>
            </span>
        </template>

        <!-- type : radio -->
        <template v-if="type === 'radio'">
            <div class="checks-wrap">
                <template v-for="(item, idx) in dataList" :key="item.codeId">
                    <div class="checks" >
                        <input type="radio" v-model="radioValue" :value="item.codeId" :disabled="isDisabled" :readonly="isReadonly" :id="'radio_'+item.codeId" ref="commRadio" @change="changeRadio($event)" >
                        <label :for="'radio_'+item.codeId">{{item.codeNm}}</label>
                    </div>
                </template>
            </div>
        </template>

        <!-- type : checkbox -->
        <template v-if="type === 'checkbox'">
            <div class="checks-wrap">
                <template v-for="(item, idx) in dataList" :key="item.codeId">
                    <div class="checks" >
                        <input type="checkbox" v-model="checkboxValue" :value="item.codeId" :disabled="isDisabled" :readonly="isReadonly" :id="'check_'+item.codeId" ref="commCheck" @change="changeCheckbox($event)" >
                        <label :for="'check_'+item.codeId">{{item.codeNm}}</label>
                    </div>
                </template>
            </div>
        </template>

    </div>
</template>

<script>
import { getCodeList } from "@/api/comm/commCode";

export default {
    name: 'CommCode',
    props: {
        type: { // 타입(select , checkbox, radio)
			type: String,
			default: ""
		},
		list: { // 공통코드가 아닌 list 데이터
			type: Array,
			default: function() {
				return []
			}
		},
        dataKey: { // list 에서 key로 사용
			type: String,
			default: "codeId"
		},
		dataValue: { // list 에서 value 로 사용(화면노출용)
			type: String,
			default: "codeNm"
		},
		groupId: { // 공통코드 groupId
			type: String,
			default: ""
		},
		defaultRow: { // 기본 로우 사용여부
			type: Boolean,
			default: true
		},
		defaultRowNm: { // 기본 로우 사용여부
			type: String,
			default: "선택"
		},
		exceptKey: {
			type: Array,
			default: function() {
				return []
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
		value: {

        },
    },
    created() {
        console.log('this.type : ', this.type)
        console.log('this.list : ', this.list.length)
        console.log('this.dataKey : ', this.dataKey)
        console.log('this.dataValue : ', this.dataValue)
        console.log('this.defaultRow : ', this.defaultRow)
        console.log('this.defaultRowNm : ', this.defaultRowNm)
        console.log('this.groupId : ', this.groupId)
        console.log('this.value : ', this.value)

        if ( !_.isEmpty( this.groupId ) ) {
            this.getCommCodeList()
        }
        else if(this.list.length > 0){
            this.dataListSet()
        }

    },
    data() {
		return {
            searchParam: {
                searchGroupId: this.groupId,
            },
            baseList: null, // 가공전 LIST DATA
			dataList: null, // 가공후 LIST DATA
			selectValue: "", // SELECT BOX 선택 값
			radioValue: "", // RADIO 선택 값
			checkboxAllValue: false,
			checkboxValue: [], // CHECK BOX 선택 값
        }
    },
    watch: {
        groupId: "groupIdSet",
        list: "dataListSet",
    },
    methods: {
        getCommCodeList(){
            console.log('getCommCodeList call')
            console.log('this.searchParam.searchGroupId : ', this.searchParam.searchGroupId)

            getCodeList(this.searchParam)
            .then(response => {
                let result = response.data.code
                this.baseList = response.data.commCodeList

                if(result === 200){
                    this.exceptKeySet()
                    this.valueSet()
                } else {
                    alert('조회 중 오류가 발생했습니다.')
                }
            })
            .catch(error => {})
        },
        dataListSet(){
            this.baseList = this.list
            this.exceptKeySet()
            this.valueSet()
        },
        valueSet(){
            console.log('valueSet call : ', this.value)

            if(typeof this.value !== 'undefined' && this.value !== null){
                if ( _.isEqual( this.type, "select" ) ) {
                    this.selectValue = this.value
                } else if ( _.isEqual( this.type, "radio" ) ) {
                    this.radioValue = this.value
                } else if ( _.isEqual( this.type, "checkbox" ) ) {
                    if ( _.isNull( this.value ) ) {
                        this.checkboxValue = []
                    } else if ( _.isEqual( this.value, [] ) || _.isEmpty( this.value ) ) {
                        this.checkboxValue = []
                    } else if ( !_.isEmpty( this.dataList ) && _.isEqual( this.checkboxValue.length, this.dataList.length ) ) {
                        this.checkboxValue = this.value
                    } else {
                        this.checkboxValue = this.value
                    }
                }
            }
        },
        changeSelect( e ) {
			this.$emit( 'change-select', this.selectValue );
		},
        changeCheckbox( e ) {
			this.$emit( 'change-checkbox', this.checkboxValue );
		},
        changeRadio( e ) {
			this.$emit( 'change-radio', this.radioValue );
		},
        groupIdSet() {
            console.log('groupIdSet call')

            this.searchParam.searchGroupId = this.groupId
            this.getCommCodeList()
        },
        exceptKeySet(){
            console.log('exceptKeySet call : ', this.exceptKey)

            if ( this.exceptKey.length > 0 ) {
                console.log('this.exceptKey.length : ', this.exceptKey.length)

                this.dataList = this.baseList
                    .filter( baseObj => !this.exceptKey
                        .some( str => str === baseObj[ this.dataKey ] ) )
                    .map( baseObj => {
                        return {
                            'codeId': baseObj[ this.dataKey ],
                            'codeNm': baseObj[ this.dataValue ]
                        }
                    } );
            } else {
                this.dataList = this.baseList
            }
        },
    },
}
</script>
