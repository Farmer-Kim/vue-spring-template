import {
  createRouter,
  createWebHistory
} from 'vue-router';
import Home from '../views/Home.vue';

/* 1. 이용가이드 */
/* 1-1. 서비스소개 */
import ServiceInfo from '../views/notice/ServiceInfo.vue';
/* 1-2. 공지사항 */
import NoticeList from '../views/notice/NoticeList.vue';
import NoticeView from '../views/notice/NoticeView.vue';
import NoticeRegist from '../views/notice/NoticeRegist.vue';

/* 2. 개발지원 */
/* 2-1. 개발지원 */
import DevSupport from '../views/support/DevSupport.vue';
/* 2-2. 공지사항 */
import DevGuideRegist from '../views/support/DevGuideRegist.vue';
import DevGuideList from '../views/support/DevGuideList.vue';
import DevGuideView from '../views/support/DevGuideView.vue';
/* 2-3. Q&A */
import DevInquryList from '../views/support/DevInquryList.vue';
import DevInquryView from '../views/support/DevInquryView.vue';
import DevInquryRegist from '../views/support/DevInquryRegist.vue';

/* 3. 이용신청 */
/* 3-0 로그인 페이지 */
import Login from '../views/user/Login.vue'

/* 3-1. 회원가입 */
import UserRegist from '../views/user/UserRegist.vue';
/* 3-2. 기관등록 */
import InsttRegist1 from '../views/manage/InsttRegist.vue';
/* 3-2. 기관등록 */
import InsttRegist from '../views/user/InsttRegist.vue';
/* 3-3 아이디 비번 찾기 */
import searchUserInfo from '../views/user/searchUserInfo.vue'

/* 4. 데이터관리 */
/* 4-1. 수집현황 */
import DataColct from '../views/data/DataColct.vue';
/* 4-2. 데이터추출 */
import DataExtrc from '../views/data/DataExtrc.vue';
/* 4-3. 추출이력 */
import DataExtrcLogList from '../views/data/DataExtrcLogList.vue';

/* 5. 서비스관리 */
/* 5-1. 회원정보 */
import UserInfoView from '../views/manage/UserInfoView.vue';
/* 5-2. 회원관리 */
import UserManageList from '../views/manage/UserManageList.vue';
/* 5-3. 기관관리 */
import InsttManageList from '../views/manage/InsttManageList.vue';


const routes = [{
    /*0.메인 화면 */
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import( /* webpackChunkName: "about" */ '../views/About.vue'),
  },
  /* 1-1. 서비스소개 */
  {
    path: '/notice/serviceInfo',
    name: 'ServiceInfo',
    component: ServiceInfo,
  },
  /* 1-2. 공지사항 */
  { //1-2-1. 공지사항 목록
    path: '/notice/noticeList',
    name: 'NoticeList',
    component: NoticeList,
  },
  { //1-2-2. 공지사항 뷰
    path: '/notice/noticeView',
    name: 'NoticeView',
    component: NoticeView,
  },
  { //1-2-3. 공지사항 등록
    path: '/notice/noticeRegist',
    name: 'NoticeRegist',
    component: NoticeRegist,
  },
  /* 2-1. 개발지원 */
  {
    path: '/support/devSupport',
    name: 'DevSupport',
    component: DevSupport,
  },
  /* 2-2. 개발자가이드 */
  { //2-2-1. 개발자가이드 등록
    path: '/support/devGuideRegist',
    name: 'DevGuideRegist',
    component: DevGuideRegist,
  },
  { //2-2-2. 개발자가이드 목록
    path: '/support/devGuideList',
    name: 'DevGuideList',
    component: DevGuideList,
  },
  { //2-2-3. 개발자가이드 상세
    path: '/support/devGuideView',
    name: 'DevGuideView',
    component: DevGuideView,
  },
  /* 2-3. Q&A */
  {
    path: '/support/devInquryList',
    name: 'DevInquryList',
    component: DevInquryList,
  },
  {
    path: '/support/devInquryView',
    name: 'DevInquryView',
    component: DevInquryView,
  },
  {
    path: '/support/devInquryRegist',
    name: 'DevInquryRegist',
    component: DevInquryRegist,
  },
    /* 3-0 로그인 페이지 */
    {
      path : '/user/Login',
      name : 'Login',
      component : Login,
    },
  /* 3-1. 회원가입 */
  {
    path: '/user/userRegist',
    name: 'UserRegist',
    component: UserRegist,
  },
    /* 아이디 비번 찾기 */
    {
      path : '/user/searchUserInfo',
      name : 'searchUserInfo',
      component : searchUserInfo,
  
    },
  /* 3-2. 기관등록 */
  {
    path: '/manage/insttRegist',
    name: 'InsttRegist1',
    component: InsttRegist1,
  },
   /* 3-2. 기관등록 */
  {
    path: '/user/insttRegist',
    name: 'InsttRegist',
    component: InsttRegist,
  },
  /* 4-1. 수집현황 */
  {
    path: '/data/dataColct',
    name: 'DataColct',
    component: DataColct,
  },
  /* 4-2. 데이터추출 */
  {
    path: '/data/dataExtrc',
    name: 'DataExtrc',
    component: DataExtrc,
  },
  /* 4-3. 추출이력 */
  {
    path: '/data/dataExtrcLogList',
    name: 'DataExtrcLogList',
    component: DataExtrcLogList,
  },
  /* 5-1. 회원정보 */
  {
    path: '/manage/userInfoView',
    name: 'UserInfoView',
    component: UserInfoView,
  },
  /* 5-2. 회원관리 */
  {
    path: '/manage/userManageList',
    name: 'UserManageList',
    component: UserManageList,
  },
  /* 5-3. 기관관리 */
  {
    path: '/manage/insttManageList',
    name: 'InsttManageList',
    component: InsttManageList,
  }
];

export const resetRouter = () =>{

}


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
