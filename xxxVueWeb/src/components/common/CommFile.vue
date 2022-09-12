<template>
  <div>
    <!-- 파일 태그 시작 (단일파일) -->
    <div class="row">
      <span>
        <template v-if="fileAllowType == 'img'">
          <input
            class="file-input"
            type="file"
            ref="file"
            name="file"
            accept="image/*"
            @change="fileSelect()"
          />
          <template v-if="fileSize > 0">
            <span>[{{ fileSize }} byte] </span>
            <button class="sp-conts-thumb-del" @click="fileDelete()">삭제</button>
          </template>
        </template>
        <template v-else>
          <input class="file-input" type="file" ref="file" name="file" @change="fileSelect()" />
          <template v-if="fileSize > 0">
            <span>[{{ fileSize }} byte] </span>
            <button class="sp-conts-thumb-del" @click="fileDelete()">삭제</button>
          </template>
        </template>
      </span>
    </div>
    <div class="row">
      <!-- 미리보기 -->
      <div
        class="thumbnail"
        :style="'height:' + filePreviewHeight + 'px'"
        v-if="fileAllowType == 'img' && prevImage && filePreviewHeight != 0 && preViewYn == 'Y'"
      >
        <img :src="prevImage" :style="'height:' + filePreviewHeight + 'px'" />
        <!-- 파일삭제 기능(단일파일) -->
        <button class="sp-conts-thumb-del" @click="fileDelete()" v-if="fileDeleteType != '0'">
          Del
        </button>
      </div>
      <!-- 파일 다운로드 -->
      <div v-for="(list, index) in fileList" :key="list">
        <span style="margin-right:5px">
          {{ list.fileName }}
        </span>
        <!-- 파일삭제 기능(단일파일) -->
        <button
          ref="asd"
          class="sp-conts-thumb-del"
          @click="getFileDelete(list.atachId, index + 1)"
          v-if="fileDeleteType != '0'"
        >
          Del
        </button>
      </div>
    </div>
    <!-- 파일 태그 종료 (단일파일) -->
  </div>
</template>

<script>
//import { fileInfoList, fileListDelete } from "@/api/catgMng";
export default {
  name: "comm-file",
  components: {},
  props: {
    fileInfo: {
      type: Object,
      default: {}
    },
    attachId: {
      type: String,
      default: ""
    },
    filePreviewHeight: {
      type: Number,
      default: 60 // 미리보기 높이값
    },
    fileDomainUrl: {
      type: String,
      default: ""
    },
    fileDeleteType: {
      type: String,
      default: "0" // 0(삭제불가), 1(물리삭제 포함), 2(물리 삭제 제외)
    },
    fileAllowTypeConfig: {
      type: String,
      default: ""
    },
    fileLimitCnt: {
      type: Number,
      default: 10
    },
    fileDeleteInfo: {
      type: String,
      default: ""
    },
    limitFileSize: {
      type: Number,
      default: 1024 * 1024 * 10 // 10M
    },
    preViewYn: {
      type: String,
      default: "Y"
    }
  },
  watch: {
    fileInfo: "setData",
    attachId: "getFileInfoView"
  },
  data() {
    return {
      prevImage: "",
      fileAllowType: this.fileInfo.fileAllowType,
      fileSize: ""
    };
  },
  created() {
    this.prevImage = _.isUndefined(this.fileInfo.flpth)
      ? ""
      : this.fileDomainUrl + this.fileInfo.flpth;
  },
  mounted() {
    this.getFileInfoView();
  },
  methods: {
    // 파일 공통 함수 시작 (단일파일)
    setData() {
      this.prevImage = _.isUndefined(this.fileInfo.flpth)
        ? ""
        : this.fileDomainUrl + this.fileInfo.flpth;
    },
    //파일 삭제 이벤트 (단일파일)
    fileDelete() {
      this.fileSize = 0;
      if (this.fileInfo.orgnFlnm != "") {
        if (this.fileAllowType == "img") {
          this.prevImage = ""; // 미리보기 초기화
          this.$emit("callback", "");
        }
        this.$refs.file.value = "";
        this.fileInfo.fileAction = this.fileDeleteType != "2" ? "D" : "DX";

        //if(fileDeleteInfo != ""){
        //	this.$emit( fileDeleteInfo, this.fileInfo ); // 삭제시 콜백 함수 호출
        //}
      } else {
        // 임시 파일인 경우
        this.prevImage = "";
        this.$refs.file.value = "";
      }
    },
    getFileInfoView() {
      console.log("this.attachId22 ", this.attachId);
      fileInfoList({ atachId: this.attachId })
        .then(response => {
          this.fileList = response.data.fileInfoList;
          console.log("asd", this.fileList);
        })
        .catch(error => {});
    },
    //파일삭제
    getFileDelete(a, b) {
      fileListDelete({ atachId: a, atachSeq: b })
        .then(response => {
          console.log("asd", 11);
        })
        .catch(error => {});
    },
    //파일 체인지 이벤트(이미지 전용) (단일파일)
    fileSelect() {
      this.fileInfo.fileAction =
        _.isUndefined(this.fileInfo.fileNo) || this.fileInfo.fileNo == 0 ? "I" : "U";

      let fileLen = this.$refs.file.files.length;
      let splitFileLength = 0;
      let ext = "";
      if (fileLen > 0) {
        // 파일명에 마침표 있는경우
        splitFileLength = this.$refs.file.files[0].name.slice(".").split(".").length - 1;
        ext = this.$refs.file.files[0].name
          .slice(".")
          .split(".")
          [splitFileLength].toLowerCase();
      }

      if (this.$refs.file.value != "") {
        const file = this.$refs.file.files[0];

        if (!_.includes(this.fileAllowTypeConfig.split(","), ext)) {
          this.$refs.file.value = "";
          this.prevImage = "";
          this.fileSize = 0;
          alert(this.fileAllowTypeConfig + "  파일만 업로드 할 수 있습니다.");
        } else if (file.size > this.limitFileSize) {
          this.$refs.file.value = "";
          this.prevImage = "";
          this.fileSize = 0;
          alert("최대 10M를 초과 할 수 없습니다.파일용량을 확인 해주세요.");
        } else {
          this.fileSize = file.size;

          if (this.fileAllowType == "img") {
            // 이미지타입인 경우에만 preview 활성화
            var reader = new FileReader();

            reader.onload = e => {
              this.prevImage = e.target.result;
              this.$emit("callback", this.prevImage);
            };
            reader.readAsDataURL(this.$refs.file.files[0]);
          } else if (this.fileAllowType == "upload") {
            this.$emit("callback", this.$refs.file.files[0]);
          } else {
            this.$emit("callback", this.$refs.file.files[0].name);
          }
        }
      } else {
        this.prevImage = "";
        this.fileSize = 0;
        if (this.fileAllowType == "img") {
          this.$emit("callback", "");
        }
      }
    },
    // 파일 Object 리턴
    getFileInfo() {
      return this.$refs.file.files;
    },
    // 파일 Object 셋팅
    setFileInfo(arrFileObj) {
      arrFileObj.push(this.$refs.file.files);
    },
    initFileInfo() {
      this.$refs.file.value = "";
      this.prevImage = "";
      this.fileSize = 0;
    }
  }
};
</script>
