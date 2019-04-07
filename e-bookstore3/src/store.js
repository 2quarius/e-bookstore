import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);
export default new Vuex.Store({
    state: {
        user: {
            id: window.localStorage.getItem('id'||'[]')==null?'未登录':JSON.parse(window.localStorage.getItem('id'||'[]')).id,
            username: window.localStorage.getItem('nick'||'[]')==null?'':JSON.parse(window.localStorage.getItem('user'||'[]')).username,
        },
    },
    mutations: {
        login(state,user){
            state.user = user;
            window.localStorage.setItem('user',JSON.stringify(user));
        },
        logout(state){
            window.localStorage.removeItem(state.user);
        }
    }
})
