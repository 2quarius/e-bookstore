<template>
<el-container>
  <el-header>
    <el-menu router="true"
  :default-active="$route.path"
  class="el-menu-demo"
  mode="horizontal"
  @select="handleSelect"
  background-color="#545c64"
  text-color="#fff"
  active-text-color="#ffd04b">
  <el-menu-item index="/">首页</el-menu-item>
  <el-menu-item index="/shop">书库</el-menu-item>
  <el-submenu index="/login">
    <template slot="title">登录/注册</template>
    <el-menu-item index="/login">登录</el-menu-item>
    <el-menu-item index="/register">注册</el-menu-item>
  </el-submenu>
  <el-menu-item index="active" @click="store">个人中心</el-menu-item>
  <el-menu-item index="/" @click="logout" style="float:right;">退出</el-menu-item>
</el-menu>
  </el-header>
  <el-main>
    <router-view/>
  </el-main>
  <el-footer>
    <el-alert
    title="@Copyright: sixplus"
    type="info"
    description="Contact: sixplus@sjtu.edu.cn"
    >
  </el-alert>
  </el-footer>
</el-container>
</template>


<script>
export default {
  data() {
      return {
        active: false,
      };
    },
    methods: {
      logout: function(){
      if(this.$store.state.user!=null){
        this.axios({
          method: 'get',
          url: 'http://localhost:8080/logout'
        }).then((response)=>{
          alert(response.data.result);
        }).catch(error => {
          JSON.stringify(error);
          console.log(error);
        });        
        this.$store.commit('logout');
      }
    },
      store(){
        this.active = this.$router.path;
        if(this.$store.state.user==null){
          this.$message({
					  type: 'info',
					  message: '请先登录'
				  });
          this.$router.push('/login');
        }
        else if(this.$store.state.user.role=="admin"){
          this.$router.push('/admin');
        }
        else if(this.$store.state.user.role=="user"){
          this.$router.push('/selfcenter');
        }
      }
    }
}
</script>
