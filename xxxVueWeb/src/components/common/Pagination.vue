<template>
  <div class="tPages">
    <ul>
      <li class="prev02">
        <a href="javascript:;" @click="firstPage">
          <img :src="require(`@/assets/images/btn_first.png`)">
        </a>
      </li>
      <li class="prev">
        <a href="javascript:;" @click="prevPage">
          <img :src="require(`@/assets/images/btn_prev.png`)">
        </a>
      </li>
      <li v-for="i in range(startPage, endPage)" v-bind:key="i" :class="classActive(i)">
        <a href="javascript:;" @click="setPage(i)">
          <template v-if="i != nowPage" style="cursor: pointer;">{{ i }}</template>
          <template v-else-if="i == nowPage">{{ i }}</template>
        </a>
      </li>
      <li class="next">
        <a href="javascript:;" @click="nextPage">
          <img :src="require(`@/assets/images/btn_next.png`)">
        </a>
      </li>
      <li class="next02">
        <a href="javascript:;" @click="lastPage">
          <img :src="require(`@/assets/images/btn_last.png`)">
        </a>
      </li>
    </ul>
      
  </div>
</template>

<script>
export default {
  name: "Pagination",
  props: {
    totalCount: {
      type: Number,
      default: 0
    },
    rowCount: {
      type: Number,
      default: 10
    },
    pageLimit: {
      type: Number,
      default: 10
    },
    currPage: {
      type: Number,
      default: 1
    }
  },
  data() {
    return {
      startPage: 1,
      nowPage: 1,
      nowPageBlock: 1,
      prevRowCount: 0
    };
  },
  watch: {
    currPage: "currPageSet"
  },
  computed: {
    totalPage() {
      // 페이지 당 건 수 변경시 1페이지로 설정
      if (this.rowCount != this.prevRowCount) {
        this.prevRowCount = this.rowCount;
        this.setFirstPage(); // 1페이지로 설정
      }
      console.log(this.totalCount);
      return _.ceil(this.totalCount / this.rowCount);
    },
    endPage() {
      // 페이지 당 건 수 변경시 1페이지로 설정
      if (this.rowCount != this.prevRowCount) {
        this.prevRowCount = this.rowCount;
        this.setFirstPage(); // 1페이지로 설정
      }
      const check = this.startPage + this.pageLimit - 1;
      return check <= this.totalPage ? check : this.totalPage;
    }
  },
  methods: {
    range(start, end) {
      return _.range(start, end + 1);
    },
    setPage(page) {
      this.nowPage = page;
      this.changePage();
    },
    firstPage() {
      this.setFirstPage(); // 1페이지로 설정
      this.changePage();
    },
    prevPage() {
      if (this.nowPage > 1) {
        this.nowPage--;
      }

      if (this.nowPage < this.startPage) {
        this.nowPageBlock--;
        this.startPage = (this.nowPageBlock - 1) * this.pageLimit + 1;
      }

      this.changePage();
    },
    nextPage() {
      console.log(this.nowPage);
      console.log(this.totalPage);
      console.log(this.endPage);
      if (this.nowPage < this.totalPage) {
        this.nowPage++;
      }

      if (this.nowPage > this.endPage) {
        this.nowPageBlock++;

        this.startPage = this.nowPage;
      }

      this.changePage();
    },
    lastPage() {
      this.nowPage = this.totalPage;
      this.nowPageBlock = _.ceil(this.totalPage / this.pageLimit);
      this.startPage = (this.nowPageBlock - 1) * this.pageLimit + 1;

      this.changePage();
    },
    changePage() {
      this.$emit("change-page", this.nowPage);
    },
    classActive(i) {
      if (i === this.nowPage) {
        return "active";
      }
    },
    // 1페이지로 설정
    setFirstPage() {
      this.nowPage = 1;
      this.nowPageBlock = 1;
      this.startPage = 1;
    },
    currPageSet() {
      this.nowPage = this.currPage;
      if (this.nowPage === 1) {
        this.startPage = 1;
      }
    }
  }
};
</script>
