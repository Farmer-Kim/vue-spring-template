<template>
  <nav id="navi">
    <ul class="nav_list">
      <li>
        <router-link to="/"><i class="fa fa-home"></i>홈</router-link>
      </li>
      <li>
        <a href="#" class="toggle_menu" @click="Submnopen(1)">
          <i class="fa fa-list"></i>
          카테고리 관리
          <span class="addmenu" v-bind:class="{ on: subopen == '1' }"
            ><i class="fa fa-angle-down"></i
          ></span>
        </a>
        <ul class="submenu animated fadeShotRight fast03" v-bind:class="{ open: subopen == '1' }">
          <li>
            <router-link to="/search" class="sublink"
              ><i class="fa fa-file"></i>표준 카테고리</router-link
            >
          </li>
          <li>
            <router-link to="/search_api" class="sublink"
              ><i class="fa fa-file"></i>전시 카테고리</router-link
            >
          </li>
          <li>
            <router-link to="/search_api_all" class="sublink"
              ><i class="fa fa-file"></i>이벤트 카테고리</router-link
            >
          </li>
          <li>
            <router-link to="/search_api_component" class="sublink"
              ><i class="fa fa-file"></i>상품 항목 관리</router-link
            >
          </li>
          <li>
            <router-link to="/search_api_component" class="sublink"
              ><i class="fa fa-file"></i>키워드 라이브러리</router-link
            >
          </li>
          <li>
            <router-link to="/search_api_component" class="sublink"
              ><i class="fa fa-file"></i>HS CODE 관리</router-link
            >
          </li>
        </ul>
      </li>

      <li>
        <a href="#" class="toggle_menu" @click="Submnopen(2)">
          <i class="fa fa-list"></i>
          상품관리
          <span class="addmenu" v-bind:class="{ on: subopen == '2' }"
            ><i class="fa fa-angle-down"></i
          ></span>
        </a>
        <ul class="submenu animated fadeShotRight fast03" v-bind:class="{ open: subopen == '2' }">
          <li>
            <router-link to="/dspyCtgryList" class="sublink"
              ><i class="fa fa-file"></i>상품 등록 현황</router-link
            >
          </li>
          <li>
            <router-link to="/dspyCtgryregist" class="sublink"
              ><i class="fa fa-file"></i>삼품 검수 현황</router-link
            >
          </li>
        </ul>
      </li>
      <li>
        <a href="#" class="toggle_menu" @click="Submnopen(3)">
          <i class="fa fa-list"></i>
          플랫폼 관리
          <span class="addmenu" v-bind:class="{ on: subopen == '3' }"
            ><i class="fa fa-angle-down"></i
          ></span>
        </a>
        <ul class="submenu animated fadeShotUp fast03" v-bind:class="{ open: subopen == '3' }">
          <li>
            <router-link to="/cmmn_cd_mnt_list" class="sublink"
              ><i class="fa fa-file"></i>플랫폼 카테고리 관리</router-link
            >
          </li>
          <li>
            <router-link to="/cmmn_cd_mnt_list" class="sublink"
              ><i class="fa fa-file"></i>플랫폼 상품 등록 관리</router-link
            >
          </li>
          <li>
            <router-link to="/cmmn_cd_mnt_list" class="sublink"
              ><i class="fa fa-file"></i>플랫폼 상품 검수 관리</router-link
            >
          </li>
        </ul>
      </li>
      <li>
        <a href="#" class="toggle_menu" @click="Submnopen(4)">
          <i class="fa fa-list"></i>
          기타관리
          <span class="addmenu" v-bind:class="{ on: subopen == '4' }"
            ><i class="fa fa-angle-down"></i
          ></span>
        </a>
        <ul class="submenu animated fadeShotUp fast03" v-bind:class="{ open: subopen == '4' }">
          <li>
            <router-link to="/cmmn_cd_mnt_list" class="sublink"
              ><i class="fa fa-file"></i>오퍼 연계 관리</router-link
            >
          </li>
          <li>
            <router-link to="/cmmn_cd_mnt_list" class="sublink"
              ><i class="fa fa-file"></i>코드 관리</router-link
            >
          </li>
        </ul>
      </li>
      <li>
        <router-link to="/fileTest"><i class="fa fa-file"></i>파일테스트</router-link>
      </li>
      <li>
        <router-link to="/commCodeTest"><i class="fa fa-list"></i>공통코드 TEST</router-link>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "Navigator",
  data() {
    return {
      subopen: 0
    };
  },
  mounted() {},
  methods: {
    Submnopen: function(e) {
      const la = e;
      if (this.subopen == la) {
        this.subopen = 0;
      } else {
        this.subopen = e;
      }
    }
  }
};
</script>
