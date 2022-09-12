<template>
  <vue-final-modal
    v-slot="{ close }"
    name="example"
    v-bind="$attrs"
    classes="modal-container"
    content-class="modal-content"
    transition="slide"
  >
    <button class="modal__close" @click="close">X</button>
    <!--
            [D] .modal-content에 size별 클래스 추가
            - small 400px: .small
            - default 500px: 기본(class없음)
            - large 600px: .large
            - larger 800px: .larger
         -->
    <div class="modal-content larger">
      <div class="box">
        <div class="inner">
          <!-- s.member-already -->
          <div class="offer-inquiry">
            <div class="h3-title">카테고리 순서이동</div>
            <div class="flex-box">
              <div class="select-box type-b" style="height:300px;overflow-y:scroll;">
                <table class="table">
                  <thead>
                    <tr>
                      <th style="background:#e4dedeb9" scope="col">
                        1LEVEL
                        <button type="button" @click="lvChange(1)" v-if="lv1 === true">
                          순서이동
                        </button>
                        <button type="button" @click="lvUpdate(1)" v-if="lv1 === false">
                          저장
                        </button>
                      </th>
                    </tr>
                  </thead>
                  <template v-if="lv1 === false">
                    <draggable v-model="stdCatgList" tag="tbody">
                      <transition-group>
                        <tr v-for="item in stdCatgList" class="isActive" :key="item">
                          <td>{{ item.stdCtgryKrnName }}</td>
                        </tr>
                      </transition-group>
                    </draggable>
                  </template>
                  <template v-else>
                    <tbody>
                      <tr v-for="item in stdCatgList" :key="item">
                        <td @click="fetchStdCatgData2(item.stdCtgryId)" style="cursor: pointer;">
                          {{ item.stdCtgryKrnName }}
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </table>
              </div>
              <div class="select-box type-b" style="height:300px;overflow-y:scroll;">
                <table class="table">
                  <thead>
                    <tr>
                      <th style="background:#e4dedeb9" scope="col">
                        2LEVEL
                        <button
                          type="button"
                          @click="lvChange(2)"
                          v-show="tab2show"
                          v-if="lv2 === true"
                        >
                          순서이동
                        </button>
                        <button type="button" @click="lvUpdate(2)" v-if="lv2 === false">
                          저장
                        </button>
                      </th>
                    </tr>
                  </thead>
                  <template v-if="lv2 === false">
                    <draggable v-model="stdCatgList2" tag="tbody">
                      <transition-group>
                        <tr v-for="item in stdCatgList2" class="isActive" :key="item">
                          <td>{{ item.stdCtgryKrnName }}</td>
                        </tr>
                      </transition-group>
                    </draggable>
                  </template>
                  <template v-else>
                    <tbody>
                      <tr v-for="item in stdCatgList2" :key="item">
                        <td @click="fetchStdCatgData3(item.stdCtgryId)" style="cursor: pointer;">
                          {{ item.stdCtgryKrnName }}
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </table>
              </div>
              <div class="select-box type-b" style="height:300px;overflow-y:scroll;">
                <table class="table">
                  <thead>
                    <tr>
                      <th style="background:#e4dedeb9" scope="col">
                        3LEVEL
                        <button
                          type="button"
                          @click="lvChange(3)"
                          v-show="tab3show"
                          v-if="lv3 === true"
                        >
                          순서이동
                        </button>
                        <button type="button" @click="lvUpdate(3)" v-if="lv3 === false">
                          저장
                        </button>
                      </th>
                    </tr>
                  </thead>
                  <template v-if="lv3 === false">
                    <draggable v-model="stdCatgList3" tag="tbody">
                      <transition-group>
                        <tr v-for="item in stdCatgList3" class="isActive" :key="item">
                          <td>{{ item.stdCtgryKrnName }}</td>
                        </tr>
                      </transition-group>
                    </draggable>
                  </template>
                  <template v-else>
                    <tbody>
                      <tr v-for="item in stdCatgList3" :key="item">
                        <td @click="fetchStdCatgData4(item.stdCtgryId)" style="cursor: pointer;">
                          {{ item.stdCtgryKrnName }}
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </table>
              </div>
              <div class="select-box type-b" style="height:300px;overflow-y:scroll;">
                <table class="table">
                  <tr>
                    <th style="background:#e4dedeb9" scope="col">
                      4LEVEL
                      <button
                        type="button"
                        @click="lvChange(4)"
                        v-show="tab4show"
                        v-if="lv4 === true"
                      >
                        순서이동
                      </button>
                      <button type="button" @click="lvUpdate(4)" v-if="lv4 === false">저장</button>
                    </th>
                  </tr>
                  <template v-if="lv4 === false">
                    <draggable v-model="stdCatgList4" tag="tbody">
                      <transition-group>
                        <tr v-for="item in stdCatgList4" class="isActive" :key="item">
                          <td>{{ item.stdCtgryKrnName }}</td>
                        </tr>
                      </transition-group>
                    </draggable>
                  </template>
                  <template v-else>
                    <tbody>
                      <tr v-for="item in stdCatgList4" :key="item">
                        <td>{{ item.stdCtgryKrnName }}</td>
                      </tr>
                    </tbody>
                  </template>
                </table>
              </div>
            </div>
          </div>
          <div class="btn-wrap">
            <button class="btn is_back" @click="close">닫기</button>
          </div>
          <!-- e.member-already -->
        </div>
      </div>
    </div>
  </vue-final-modal>
</template>

<script>
//import { levelStdCtgryList, levelStdCtgryUpdate } from "@/api/catgMng";
import { VueDraggableNext } from "vue-draggable-next";
export default {
  components: {
    draggable: VueDraggableNext
  },
  inheritAttrs: false,
  data() {
    return {
      lv1: true,
      lv2: true,
      lv3: true,
      lv4: true,
      dragging: false,
      tab2show: false,
      tab3show: false,
      tab4show: false,
      //카테고리 목록 파라미터
      stdCatgList: [],
      stdCatgList2: [],
      stdCatgList3: [],
      stdCatgList4: [],
      stdCatg1: "",
      stdCatg2: "",
      stdCatg3: "",
      stdCatg4: "",
      popupParam: { stdCtgryLevelVal: 1, listUp: [] }
    };
  },
  created() {
    this.fetchStdCatgData1();
  },
  methods: {
    lvChange(level) {
      if (level === 1) {
        this.lv1 = false;
      } else if (level === 2) {
        this.lv2 = false;
      } else if (level === 3) {
        this.lv3 = false;
      } else {
        this.lv4 = false;
      }
    },
    //레벨별 순서변경 업데이트
    lvUpdate(level) {
      if (level === 1) {
        this.lv1 = true;
        console.log("this.stdCatgList", this.stdCatgList);
        levelStdCtgryUpdate({
          stdCtgryList: this.stdCatgList
        }).then(response => {
          console.log("response.data.getStdCatgList", response.data.result1);
        });
      } else if (level === 2) {
        this.lv2 = true;
        console.log("this.stdCatgList2", this.stdCatgList2);
      } else if (level === 3) {
        this.lv3 = true;
        console.log("this.stdCatgList3", this.stdCatgList3);
      } else {
        this.lv4 = true;
        console.log("this.stdCatgList4", this.stdCatgList4);
      }
    },
    /* 표준카테고리 1레벨 조회 */
    fetchStdCatgData1() {
      levelStdCtgryList({
        stdCtgryLevelVal: 1
      }).then(response => {
        console.log("response.data.getStdCatgList", response.data.levelStdCtgryList);
        this.stdCatgList = response.data.levelStdCtgryList;
      });
    },
    /* 표준카테고리 2레벨 조회 */
    fetchStdCatgData2(stdCatg1) {
      // 선택된 데이터의 텍스트값 가져오기
      this.stdCatg1 = stdCatg1;
      levelStdCtgryList({
        upperStdCtgryId: this.stdCatg1,
        stdCtgryLevelVal: 2
      }).then(response => {
        this.stdCatgList2 = response.data.levelStdCtgryList;
        this.stdCatg2 = "";
        this.stdCatg3 = "";
        this.stdCatg4 = "";
        this.stdCatgList3 = [];
        this.stdCatgList4 = [];
        this.tab2show = this.stdCatgList2.length > 0 ? true : false;
      });
    },
    /* 표준카테고리 3레벨 조회 */
    fetchStdCatgData3(stdCatg2) {
      // 선택된 데이터의 텍스트값 가져오기
      this.stdCatg2 = stdCatg2;
      levelStdCtgryList({
        upperStdCtgryId: this.stdCatg2,
        stdCtgryLevelVal: 3
      }).then(response => {
        this.stdCatgList3 = response.data.levelStdCtgryList;
        this.stdCatg3 = "";
        this.stdCatg4 = "";
        this.stdCatgList4 = [];
        this.tab3show = this.stdCatgList3.length > 0 ? true : false;
      });
    },
    //표준카테고리 4레벨 조회
    fetchStdCatgData4(stdCatg3) {
      // 선택된 데이터의 텍스트값 가져오기
      this.stdCatg3 = stdCatg3;
      levelStdCtgryList({
        upperStdCtgryId: this.stdCatg3,
        stdCtgryLevelVal: 4
      }).then(response => {
        this.stdCatgList4 = response.data.levelStdCtgryList;
        this.tab4show = this.stdCatgList4.length > 0 ? true : false;
      });
    }
  }
};
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
.isActive {
  background-color: #d4e8f5;
}
</style>
