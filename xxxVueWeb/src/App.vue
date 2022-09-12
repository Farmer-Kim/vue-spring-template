<template>

      <div id="Wrap">

        <!-- 헤더 관리 영역 -->
        <Headers />
        <!-- 헤더 관리 영역 -->

        <!-- 컨텐츠 관리 영역 -->
        <router-view :key="$route.fullPath"/>
        <!-- 컨텐츠 관리 영역 -->

      </div>

  <!-- 푸터 관리 영역 -->
  <!-- <Footers /> -->
  <!-- 푸터 관리 영역 -->
  
</template>

<script>
import Headers from "@/views/include/head.vue";
import Footers from "@/views/include/footer.vue";


export default {
  components: {
    Headers,
    Footers
  },
  data() {
    return {
    };
  },
  methods: {
  }
};
</script>
<style>
@import url("../src/assets/css/style.css");
</style>