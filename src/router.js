import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Shop from './views/Shop.vue'
import SelfCenter from './views/SelfCenter.vue'
import SelfSetting from './views/user/SelfSetting.vue'
import DealSetting from './views/user/DealSetting.vue'
import Cart from './views/Cart.vue'
import Admin from './views/Admin.vue'
import Deals from './views/Deals.vue'
import Details from './views/Details.vue'
import UserAdmin from './views/admin/UserAdmin.vue'
import GoodAdmin from './views/admin/GoodAdmin.vue'

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
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      // component: () => import(/* webpackChunkName: "about" */ './views/Login.vue')
    },
    {
      path: '/shop',
      name: 'shop',
      component: Shop
    },
    {
      path: '/selfcenter',
      name: 'self-center',
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
          path: 'cart',
          name: 'cart',
          component: Cart
        },
        {
          path: 'deals',
          name: 'deals',
          component: Deals
        }
      ]
    },
    {
      path: '/admin',
      name: 'admin',
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
