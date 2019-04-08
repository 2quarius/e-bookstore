import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);
export default new Vuex.Store({
    state: {
        user: {
            username: window.localStorage.getItem('username'||'[]')==null?'':JSON.parse(window.localStorage.getItem('username'||'[]')).username,
            role: window.localStorage.getItem('role'||'[]')==null?'':JSON.parse(window.localStorage.getItem('role'||'[]')).role,
        },
    },
    mutations: {
        login(state,user){
            state.user = user;
            window.localStorage.setItem('user',JSON.stringify(user));
            console.log(state.user.role);
            console.log("stored");
        },
        logout(state){
            var num = new Array();
            window.localStorage.setItem('user',JSON.stringify(num));//不能被清除！！weird
            console.log(state.user);
        }
    }
})
