import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import Shop from './views/Shop.vue'
import SelfCenter from './views/SelfCenter.vue'
import SelfSetting from './views/user/SelfSetting.vue'
import DealSetting from './views/user/DealSetting.vue'
import newCart from './views/newCart.vue'
import Admin from './views/Admin.vue'
import UserDeals from './views/user/UserDeals.vue'
import Details from './views/Details.vue'
import UserAdmin from './views/admin/UserAdmin.vue'
import GoodAdmin from './views/admin/GoodAdmin.vue'
import EditDeals from './views/admin/EditDeals.vue'
import Statistic from './views/admin/Statistic.vue'
import UserSatistic from './views/admin/UserStatistic.vue'
import TimeStatistic from './views/user/TimeStatistic.vue'

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login  
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/shop',
      name: 'shop',
      component: Shop
    },
    {
      path: '/selfcenter',
      name: 'self-center',
      meta: {
        requireAuth: true,
      },
      component: SelfCenter,
      children: [
        {
          path: 'selfSetting',
          name: 'self-setting',
          component: SelfSetting
        },
        {
          path: 'dealSetting',
          name: 'deal-setting',
          component: DealSetting
        },
        {
          path: 'newCart',
          name: 'newCart',
          component: newCart
        },
        {
          path: 'userdeals',
          name: 'userdeals',
          component: UserDeals
        },
        {
          path: 'time-statistic',
          name: 'time-statistic',
          component: TimeStatistic
        }
      ]
    },
    {
      path: '/admin',
      name: 'admin',
      meta: {
        requireAuth: true,
        requireMore: true
      },
      component: Admin,
      children: [
        {
          path: 'storage',
          name: 'good-admin',
          component: GoodAdmin
        },
        {
          path: 'users',
          name: 'user-admin',
          component: UserAdmin
        },
        {
          path: 'editdeals',
          name: 'editdelas',
          component: EditDeals
        },
        {
          path: 'statistic',
          name: 'statistic',
          component: Statistic
        },
        {
          path: 'user-statistic',
          name: 'user-statistic',
          component: UserSatistic
        }
      ]
    },
    {
      path: '/details',
      name: 'details',
      component: Details
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})