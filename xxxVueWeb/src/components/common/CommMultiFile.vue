<template>
  <div>
    <!-- 파일 태그 시작 (멀티파일) -->
    <div class="row" v-for="(item, index) in fileInfo" :key="item.fileIndex">
      <span>
        <input
          v-if="item.fileAllowType == 'img'"
          class="file-input"
          type="file"
          :ref="'file'"
          name="file"
          accept="image/*"
          @change="fileSelect(index)"
        />
        <input
          v-else
          class="file-input"
          type="file"
          :ref="'file'"
          name="file"
          @change="fileSelect(index)"
        />
        <i
          v-if="index == 0"
          class="btn btn-grid phase1"
          style="cursor:pointer"
          @click="fileAdd(index)"
          >추가</i
        >
        <i
          class="btn btn-grid phase2"
          @click="fileRemove(index)"
          :style="index > 0 ? 'cursor:pointer' : 'cursor:pointer;display:none'"
          >제거</i
        >
      </span>
    </div>
    <div class="row">
      <div
        class="thumbnail"
        :style="'height:' + filePreviewHeight + 'px'"
        v-for="(item, index) in fileInfo"
        :key="item"
      >
        <template v-if="item.fileAllowType == 'img' && prevImage[index]">
          <!-- 미리보기 -->
          <img
            :src="prevImage[index]"
            :height="filePreviewHeight + 'px'"
            @click="fileDownload(index)"
          />
          <button
            class="sp-conts-thumb-del"
            @click="fileDelete(index)"
            style="cursor:pointer"
            v-if="fileDeleteType != '0'"
          ></button>
        </template>
      </div>
      <!-- 파일 다운로드 -->
      <div v-for="(item, index) in fileInfo" :key="item">
        <template v-if="item.fileAllowType != 'img' && item.orgnFlnm && item.fileAction != 'D'">
          <span @click="fileDownload(index)" style="cursor:pointer;margin-right:5px">{{
            fileInfo[index].orgnFlnm
          }}</span>
          <button
            ref="thumbDel"
            class="sp-conts-thumb-del"
            style="cursor:pointer"
            @click="fileDelete(index)"
            v-if="fileDeleteType != '0'"
          ></button>
        </template>
      </div>
    </div>
    <!-- 파일 태그 종료 (멀티파일) -->
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "comm-multi-file",
  components: {},
  props: {
    fileInfo: {
      type: Array,
      default: []
    },
    fileGroup: {
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
    }
  },
  watch: {
    fileInfo: "setData",
    fileGroup: "setGroupData"
  },
  data() {
    return {
      prevImage: []
    };
  },
  created() {
    for (let i = 0; i < this.fileInfo.length; i++) {
      if (this.fileInfo[i].fileAllowType == "img") {
        this.prevImage.push(
          _.isUndefined(this.fileInfo[i].flpth) ? "" : this.fileDomainUrl + this.fileInfo[i].flpth
        ); /* 미리보기에 보여줄 path*/
      }
    }
  },
  methods: {
    // 파일 공통 함수 시작 (단일파일)
    ...mapActions("common", ["commDownFile"]),
    setData() {
      for (let i = 0; i < this.fileInfo.length; i++) {
        if (
          this.fileInfo[i].fileAllowType == "img" &&
          (_.isUndefined(this.$refs.file[i]) || this.$refs.file[i].value == "")
        ) {
          this.prevImage[i] =
            _.isUndefined(this.fileInfo[i].flpth) || this.fileInfo[i].flpth == ""
              ? ""
              : this.fileDomainUrl + this.fileInfo[i].flpth; /* 미리보기에 보여줄 path*/
        }
      }
    },
    setGroupData() {
      for (let i = 0; i < this.fileInfo.length; i++) {
        this.fileInfo[i].fileGroup = this.fileGroup;
      }
    },
    //파일 삭제 이벤트 (단일파일)
    fileDelete(index) {
      if (this.fileInfo[index].orgnFlnm != "") {
        if (this.fileInfo[index].fileAllowType == "img") {
          this.prevImage.splice(index, 1, ""); // 미리보기 초기화
        }
        this.$refs.file[index].value = "";
        this.fileInfo[index].fileAction = this.fileInfo[index].fileDeleteType != "2" ? "D" : "DX";
        this.fileInfo[index].flpth = "";
        //if(fileDeleteInfo != ""){
        //	this.$emit( fileDeleteInfo, this.fileInfo ); // 삭제시 콜백 함수 호출
        //}
      } else {
        // 임시 파일인 경우
        this.prevImage.splice(index, 1, "");
        this.$refs.file[index].value = "";
      }
    },
    //파일 다운로드 (단일파일)
    fileDownload(index) {
      if (!_.isUndefined(this.fileInfo[index].flnm)) {
        // 등록된 파일만 다운로드 가능
        let param = {
          orgnFlnm: this.fileInfo[index].orgnFlnm,
          flnm: this.fileInfo[index].flnm,
          fileGroup: this.fileGroup
        };
        this.commDownFile(param);
      }
    },
    //파일 체인지 이벤트(이미지 전용) (단일파일)
    fileSelect(index) {
      this.fileInfo[index].fileAction =
        _.isUndefined(this.fileInfo[index].fileNo) || this.fileInfo[index].fileNo == 0 ? "I" : "U";

      let fileLen = this.$refs.file[index].files.length;
      let splitFileLength = 0;
      let ext = "";
      if (fileLen > 0) {
        // 파일명에 마침표 있는경우
        splitFileLength = this.$refs.file[index].files[0].name.slice(".").split(".").length - 1;
        ext = this.$refs.file[index].files[0].name
          .slice(".")
          .split(".")
          [splitFileLength].toLowerCase();
      }
      if (this.$refs.file[index].value != "") {
        const file = this.$refs.file[index].files;
        if (!_.includes(this.fileAllowTypeConfig.split(","), ext)) {
          this.$refs.file[index].value = "";
          this.prevImage.splice(index, 1, "");
          alert(this.fileAllowTypeConfig + "  파일만 업로드 할 수 있습니다.");
        } else if (file[0].size > 1024 * 1024 * 10) {
          this.$refs.file[index].value = "";
          this.prevImage.splice(index, 1, "");
          alert("최대 10M를 초과 할 수 없습니다.파일용량을 확인 해주세요.");
        } else {
          if (this.fileInfo[index].fileAllowType == "img") {
            // 이미지타입인 경우에만 preview 활성화
            var reader = new FileReader();

            reader.onload = e => {
              this.prevImage.splice(index, 1, e.target.result);
            };
            reader.readAsDataURL(this.$refs.file[index].files[0]);
          }
        }
      } else {
        this.prevImage.splice(index, 1, "");
      }
    },
    getFileInfo() {
      return this.$refs.file;
    },
    setFileInfo(arrFileObj) {
      for (let i = 0; i < this.$refs.file.length; i++) {
        arrFileObj.push(this.$refs.file[i].files);
      }
    },
    fileAdd() {
      if (this.fileInfo.length > this.fileLimitCnt) {
        alert(this.fileLimitCnt + "까지 파일 추가 가능합니다.");
        return;
      }

      this.fileInfo.push({
        fileIndex: this.fileInfo[this.fileInfo.length - 1].fileIndex + 1,
        fileAction: "I",
        fileGroup: this.fileInfo[0].fileGroup,
        fileTargClsCd: this.fileInfo[0].fileTargClsCd,
        fileAllowType: this.fileInfo[0].fileAllowType
      });
    },
    fileRemove(index) {
      this.fileInfo.splice(index, 1);
      this.prevImage.splice(index, 1);
      //this.prevImage.splice( index, 1 );
    }
  }
};
</script>
