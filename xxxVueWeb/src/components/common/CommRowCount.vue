<template>
  <select v-model="dRowCount" @change="changeRowCount">
    <option v-for="item in rowCountList" :key="item" :value="item"> {{ item }}개씩 보기 </option>
  </select>
</template>

<script>
export default {
  name: "CommRowCount",
  props: {
    rowCount: {
      type: Number,
      default: 20
    },
    customRowCountList: {
      type: Array,
      default: undefined
    }
  },
  data() {
    return {
      dRowCount: this.rowCount,
      rowCountList: [10, 20, 50, 100]
    };
  },
  methods: {
    changeRowCount() {
      this.$emit("change-row-count", this.dRowCount);
    }
  }
};
</script>
