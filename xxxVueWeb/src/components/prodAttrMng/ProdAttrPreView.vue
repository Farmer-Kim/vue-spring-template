<template>
    <vue-final-modal
        v-slot="{ close }"
        v-bind="$attrs"
        classes="modal-container"
        content-class="modal-content"
        transition="slide"
    >
    <button class="modal__close" @click="close">X</button>

    <div class="modal-content larger">
        <div class="box">
            <div class="inner">
                <!-- s.member-already -->
                <div class="offer-inquiry">
                    <div class="h3-title">상품항목 미리보기</div>

                    <div class="btn-wrap" style="margin-bottom:20px; justify-content: flex-end;">
                        <button class="btn is_point btn-search" @click="validate">저장 시 점검사항</button>
                    </div>
                    <div class="table-wrap type-b">
                        <table class="table">
                            <caption>
                            caption area
                            </caption>
                            <colgroup>
                                <col style="width: 10%" />
                                <col />
                            </colgroup>
                            <tbody>

                            <template v-for="(list, index) in prodAttrList" :key="list.prodAttrId">
                                    <tr>
                                        <th scope="row" class="h-center">
                                            <template v-if="list.prodAttrGroupYn === 'Y'">
                                                {{list.prodAttrGroupNm}}
                                            </template>
                                            <template v-else>
                                                {{list.attrDetailList[0].attrKorNm}}
                                            </template>
                                        </th>
                                        <td>
                                            <div class="group-search">
                                                <template v-for="(detailList, index2) in list.attrDetailList" :key="detailList.prodAttrDetailId">
                                                    <template v-if="detailList.dataType === 'text'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <div class="input-box">
                                                            <input type="text" v-model="detailList.inputVal" :ref="'ref_'+list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2" class="input" >
                                                        </div>
                                                    </template>
                                                    <template v-if="detailList.dataType === 'textarea'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <div class="textarea-box">
                                                            <textarea class="textarea" v-model="detailList.inputVal" :ref="'ref_'+list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2" placeholder=""></textarea>
                                                        </div>
                                                    </template>
                                                    <template v-if="detailList.dataType === 'number'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <div class="input-box">
                                                            <input type="text" v-model="detailList.inputVal" :ref="'ref_'+list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2" class="input" @keyup="inputCheckNumber($event)">
                                                        </div>
                                                    </template>
                                                    <template v-if="detailList.dataType === 'date'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <div class="input-box">
                                                            <input type="date" v-model="detailList.inputVal" :ref="'ref_'+list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2" class="input" >
                                                        </div>
                                                    </template>
                                                    <template v-if="detailList.dataType === 'link'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <div class="input-box">
                                                            <input type="text" v-model="detailList.inputVal" :ref="'ref_'+list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2" class="input" >
                                                        </div>
                                                    </template>
                                                    <template v-if="detailList.dataType === 'select'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <template v-if="detailList.codeSe === 'N' && detailList.attrDetailSubList.length > 0">
                                                            <div class="select-wrap">
                                                                <select class="select" v-model="detailList.attrDetailSubList.inputSelectVal" :ref="'ref_'+list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2">
                                                                    <template v-for="(detailSubList, index3) in detailList.attrDetailSubList" :key="detailSubList.prodAttrDetailNo">
                                                                        <option :value="detailSubList.prodAttrDetailNo">{{detailSubList.attrDetailKorNm}}</option>
                                                                    </template>
                                                                </select>
                                                            </div>
                                                        </template>
                                                    </template>
                                                    <template v-if="detailList.dataType === 'radio'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <template v-if="detailList.codeSe === 'N' && detailList.attrDetailSubList.length > 0">
                                                            <div class="checks-wrap">
                                                                <template v-for="(detailSubList, index3) in detailList.attrDetailSubList" :key="detailSubList.prodAttrDetailNo">
                                                                    <div class="checks">
                                                                        <input type="radio" v-model="detailList.attrDetailSubList.inputRadioVal" :ref="'ref_'+list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2" :id="list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2+'_'+index3" :value="detailSubList.prodAttrDetailNo">
                                                                        <label :for="list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2+'_'+index3">{{detailSubList.attrDetailKorNm}}</label>
                                                                    </div>
                                                                </template>
                                                            </div>
                                                        </template>
                                                    </template>
                                                    <template v-if="detailList.dataType === 'check'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <template v-if="detailList.codeSe === 'N' && detailList.attrDetailSubList.length > 0">
                                                            <div class="checks-wrap">
                                                                <template v-for="(detailSubList, index3) in detailList.attrDetailSubList" :key="detailSubList.prodAttrDetailNo">
                                                                    <div class="checks">
                                                                        <input type="checkbox" v-model="detailList.attrDetailSubList.inputCheckVal" :ref="'ref_'+list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2" :id="list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2+'_'+index3" :value="detailSubList.prodAttrDetailNo">
                                                                        <label :for="list.prodAttrId+'_'+detailList.prodAttrDetailId+'_'+index2+'_'+index3">{{detailSubList.attrDetailKorNm}}</label>
                                                                    </div>
                                                                </template>
                                                            </div>
                                                        </template>
                                                    </template>
                                                    <template v-if="detailList.dataType === 'file'">
                                                        <span v-if="list.prodAttrGroupYn === 'Y' && index2 === 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <span v-else-if="index2 > 0">
                                                            {{detailList.attrKorNm}}&nbsp;
                                                        </span>
                                                        <comm-file
                                                            :ref="'file'"
                                                            :fileAllowTypeConfig="fileAllowTypeConfig.img"
                                                            :fileDeleteType="'1'"
                                                            :limitFileSize=1024*1024*10
                                                        >
                                                        </comm-file>
                                                    </template>
                                                </template>
                                            </div>
                                        </td>
                                    </tr>
                                </template>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </vue-final-modal>
</template>

<script>
import { getProdAttrPreView } from "@/api/prodAttrMng";
import { getByte, checkOnlyNumber, getOnlyNumber, checkPrimeNumber, getPrimeNumber } from "@/utils/common";

export default {
    props: {
        prodAttrId: {
			type: String,
			default: ""
		},
        prodAttrIdList: {
			type: Array,
			default: []
		},
    },
    watch: {
        prodAttrId: "getProdAttrPreViewList",
        prodAttrIdList: "getProdAttrPreViewList",
    },
    data() {
        return {
            popupParam: {
                searchProdAttrId: this.prodAttrId,
                searchProdAttrIdList: this.prodAttrIdList,
            },
            prodAttrList: [],
            fileAllowTypeConfig: {img:'gif,jpg,jpeg,png,bpm,pdf,xls,xlsx,ppt,pptx'},
            fileAllowTypeImgConfig: {img:'gif,jpg,jpeg,png,bpm'},
        }
    },
    created() {

    },
    methods: {
        getProdAttrPreViewList(){
            console.log('this.prodAttrIdList : ', this.prodAttrIdList)
            this.popupParam.searchProdAttrIdList = this.prodAttrIdList

            getProdAttrPreView(this.popupParam)
            .then(response => {
                this.prodAttrList = response.data.prodAttrList

                console.log('result prodAttrList : ', this.prodAttrList)

                this.setCheckAttrInit()
            })
            .catch(error => {})
        },
        inputCheckNumber(event){
            console.log('event : ', event.target.value)
            const value = event.target.value

            if(checkOnlyNumber(value) === false){
                event.target.value = getOnlyNumber(value)
            }
        },
        setCheckAttrInit(){
            console.log('setCheckAttrInit call')
            // checkbox setting
            for (let index = 0; index < this.prodAttrList.length; index++) {
                for (let index2 = 0; index2 < this.prodAttrList[index].attrDetailList.length; index2++) {
                    const detail = this.prodAttrList[index].attrDetailList[index2]
                    const dataType = detail.dataType

                    if(dataType === 'check'){
                        this.prodAttrList[index].attrDetailList[index2].attrDetailSubList.inputCheckVal = []
                    }
                }
            }
        },
        validate(){
            console.log('validate call')

            let result = true

            for (let index = 0; index < this.prodAttrList.length; index++) {
                const element = this.prodAttrList[index]
                const essntlYn = element.essntlYn
                console.log('element : ', element)
                console.log('essntlYn : ', essntlYn)

                const attrDetailList = element.attrDetailList
                for (let index2 = 0; index2 < attrDetailList.length; index2++) {
                    const detail = attrDetailList[index2]
                    const dataType = detail.dataType
                    const attrKorNm = detail.attrKorNm
                    const maxSize = detail.maxSize
                    const refId = 'ref_'+element.prodAttrId+"_"+detail.prodAttrDetailId+"_"+index2

                    console.log('dataType : ', dataType)
                    console.log('maxSize : ', maxSize)
                    console.log('refId :', refId)

                    if(dataType === 'text' || dataType === 'textarea' || dataType === 'number' || dataType === 'date' || dataType === 'link'){
                        const inputVal = detail.inputVal
                        const inputValByte = getByte(detail.inputVal)
                        console.log('inputVal : ', inputVal)
                        console.log('inputVal byte : ', inputValByte)

                        if(essntlYn === 'Y'){
                            if(typeof inputVal === 'undefined' || inputVal === null || inputVal === ''){
                                alert(attrKorNm + '(은)는 필수 입력입니다.')
                                this.$refs[refId].focus()
                                result = false
                                return result
                            } else {
                                if(inputValByte > maxSize){
                                    alert(attrKorNm + '(은)는 최대 ' + maxSize + '(byte)까지 입력 가능합니다. 현재 ' + inputValByte + '(byte)')
                                    this.$refs[refId].focus()
                                    result = false
                                    return result
                                }
                            }
                        } else {
                            if(inputValByte > maxSize){
                                alert(attrKorNm + '(은)는 최대 ' + maxSize + '(byte)까지 입력 가능합니다. 현재 ' + inputValByte + '(byte)')
                                this.$refs[refId].focus()
                                result = false
                                return result
                            }
                        }

                    } else if(dataType === 'select'){
                        const inputSelectVal = detail.attrDetailSubList.inputSelectVal
                        console.log('inputSelectVal : ', inputSelectVal)

                        if(essntlYn === 'Y'){
                            if(typeof inputSelectVal === 'undefined' || inputSelectVal === null || inputSelectVal === ''){
                                alert(attrKorNm + '(은)는 필수 입력입니다.')
                                this.$refs[refId].focus()
                                result = false
                                return result
                            }
                        }

                    } else if(dataType === 'radio'){
                        const inputRadioVal = detail.attrDetailSubList.inputRadioVal
                        console.log('inputRadioVal : ', inputRadioVal)

                        if(essntlYn === 'Y'){
                            if(typeof inputRadioVal === 'undefined' || inputRadioVal === null || inputRadioVal === ''){
                                alert(attrKorNm + '(은)는 필수 입력입니다.')
                                this.$refs[refId].focus()
                                result = false
                                return result
                            }
                        }

                    } else if(dataType === 'check'){
                        const inputCheckVal = detail.attrDetailSubList.inputCheckVal
                        let inputCheckLen = 0
                        if(typeof inputCheckVal !== 'undefined' && inputCheckVal !== null){
                            inputCheckLen = detail.attrDetailSubList.inputCheckVal.length
                        }
                        console.log('inputCheckVal : ', inputCheckVal)
                        console.log('inputCheckLen : ', inputCheckLen)

                        if(essntlYn === 'Y'){
                            if(typeof inputCheckVal === 'undefined' || inputCheckVal === null || inputCheckVal === '' || inputCheckLen === 0){
                                alert(attrKorNm + '(은)는 필수 입력입니다.')
                                this.$refs[refId].focus()
                                result = false
                                return result
                            }
                        }

                    } else if(dataType === 'file'){

                    }

                }
            }

            if(result === true){
                alert('정상입니다.')
            }
        },
    },
}
</script>

<style>
.slide-enter-active,
.slide-leave-active {
  transition: all 1s;
}

.slide-enter-from,
.slide-leave-to {
  transform: translateY(100vh);
}

.modal-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal-content {
  position: relative;
  display: flex;
  flex-direction: column;
  margin: 0 1rem;
  padding: 1rem;
  border: 1px solid #e2e8f0;
  border-radius: 0.25rem;
  background: #fff;
}
.modal__title {
  margin: 0 2rem 0 0;
  font-size: 1.5rem;
  font-weight: 700;
}
.modal__close {
  position: absolute;
  top: 0.5rem;
  right: 0.5rem;
}
</style>

<style scoped>
.dark-mode div::v-deep(.modal-content) {
  border-color: #2d3748;
  background-color: #1a202c;
}
</style>
