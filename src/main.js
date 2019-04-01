import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import {getRequest} from './utils/api'
import { postRequest } from './utils/api'
import {deleteRequest} from './utils/api'
import {putRequest} from './utils/api'
import store from './store'

Vue.config.productionTip = false
Vue.use(ElementUI,axios);

Vue.prototype.axios = axios;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;

router.beforeEach((to,from,next)=>{
  if(to.meta.requireAuth){
    if(store.state.user){
      next();
    }else{
      next({
        path: '/login',
      });
    }
  }else{
    next();
  }
})
new Vue({
  router,
  store,
  axios,
  render: h => h(App)
}).$mount('#app')
