import { createStore } from 'vuex';

import userStore from './modules/user'

export const store = createStore({
  modules: {
    userStore : userStore
  },
});
