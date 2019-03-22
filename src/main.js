import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
Vue.use(ElementUI,axios);
Vue.prototype.axios = axios;
new Vue({
  router,
  axios,
  render: h => h(App)
}).$mount('#app')
