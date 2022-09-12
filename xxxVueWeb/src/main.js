import {createApp} from 'vue';

import App from './App.vue';
import router from './router';
import { store } from './store/index'

//Vue Cookie
import {VueCookieNext} from 'vue-cookie-next'


//공통 모달
import VueFinalModal from 'vue-final-modal';
// 공통 lodash
import _ from 'lodash';
// 자주 쓰는 공통 컴포넌트
import Pagination from '@/components/common/Pagination.vue'; //페이징
import CommRowCount from '@/components/common/CommRowCount.vue'; //페이징 limit 변경
import CommTagLib from '@/components/common/CommTagLib.vue';
import CommDateTagLib from '@/components/common/CommDateTagLib.vue';
import CommFile from '@/components/common/CommFile.vue';
import CommFileDownload from '@/components/common/CommFileDownload.vue';
import CommMultiFile from '@/components/common/CommMultiFile.vue';
import CommCodeTagLib from '@/components/common/CommCodeTagLib.vue';
import Tiptap from "@/components/tiptap/Editor.vue";//에디터

// 공통 상수
import CONSTS from '@/utils/constants.js';

const app = createApp(App);
// global constants
app.config.globalProperties.$const = {
  ...CONSTS
};

app.config.globalProperties.$filters = {
  currency(value) {
    let num = new Number(value);
    return num.toFixed(0).replace(/(\d)(?=(\d{ 3})+(?:\.\d+)?$)/g, "$1,");
  },
  getRowNum(rowNum, a, b) {
    return (rowNum + ((a - 1) * b)) + 1;
  },
};

app.config.globalProperties.$cookies;

app.use(router);
app.use(store)
app.use(VueFinalModal());
app.use(VueCookieNext,{
  expire : "7d",
  path : "/",
  domain : "",
  secure : "",
  sameSite : "None"
})



app.component('pagination', Pagination);
app.component('comm-row-count', CommRowCount);
app.component('comm-date-tag-lib', CommDateTagLib);
app.component('comm-tag-lib', CommTagLib);
app.component('comm-file', CommFile);
app.component('comm-multi-file', CommMultiFile);
app.component('editor', Tiptap)
app.component('comm-file-download', CommFileDownload);
app.component('comm-code-tag-lib', CommCodeTagLib);


app.mount('#app');
