<template>
  <!-- 파일 다운로드 -->
  <div v-for="(list, index) in fileList" :key="list">
    <span @click="fileDownload(list.atachId, index + 1)" style="cursor:pointer;margin-right:5px">
      {{ list.fileName }}
    </span>
  </div>
</template>

<script>
//import { download, fileInfoList } from "@/api/catgMng";
export default {
  name: "comm-file-download",
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
    attachId: "getAtachId"
  },
  data() {
    return {
      prevImage: "",
      fileAllowType: this.fileInfo.fileAllowType,
      searchParams: {
        atachId: this.$props.attachId,
        atachSeq: 1
      },
      fileList: []
    };
  },
  created() {
    this.prevImage = _.isUndefined(this.fileInfo.flpth)
      ? ""
      : this.fileDomainUrl + this.fileInfo.flpth;
    this.getFileInfoView();
  },
  methods: {
    // 파일 Object 리턴
    getFileInfo() {
      return this.$refs.file.files;
    },
    // 파일 Object 셋팅
    setFileInfo(arrFileObj) {
      arrFileObj.push(this.$refs.file.files);
    },
    initFileInfo() {
      this.$refs.file.value = " ";
      this.prevImage = "";
      this.fileSize = 0;
    },
    //파일 정보 조회
    getFileInfoView() {
      console.log("this.attachId!! ", this.searchParams.atachId);
      fileInfoList({ atachId: this.searchParams.atachId })
        .then(response => {
          console.log("asd", response.data.fileInfoList);
          this.fileList = response.data.fileInfoList;
        })
        .catch(error => {});
    },
    getAtachId() {
      this.searchParams.atachId = this.$props.attachId;
    },
    //파일 다운로드
    fileDownload(a, b) {
      this.searchParams.atachId = a;
      this.searchParams.atachSeq = b;

      download(this.searchParams)
        .then(response => {
          const url = window.URL.createObjectURL(
            new Blob([response.data], {
              type: response.headers["content-type"]
            })
          );
          //다운로드 링크 생성
          const link = document.createElement("a");
          const filename = decodeURI(response.headers["content-disposition"].split("filename=")[1]);

          link.href = url;
          link.setAttribute("download", filename.replace(";", ""));
          document.body.appendChild(link);
          link.click();
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>
