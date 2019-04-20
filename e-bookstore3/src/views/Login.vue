<template>
  <div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="row clearfix">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-6 column">
          <form class="form-horizontal" role="form">
            <div class="form-group">
               <label for="inputEmail" class="col-sm-2 control-label">Username</label>
              <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail" ref="username"/>
              </div>
            </div>
            <div class="form-group">
               <label for="inputPassword" class="col-sm-2 control-label">Password</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" ref="password"/>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                   <label><input type="checkbox" />Remember me</label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                 <button type="submit" class="btn btn-default" @click="sign()">Sign in</button>
              </div>
            </div>
          </form>
        </div>
        <div class="col-md-4 column">
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'login',
  methods: {//密码需要加密，公钥私钥加密
    sign: function() {
      var username = this.$refs.username.value;
      var password = this.$refs.password.value;
      alert('Username:'+username);
      this.axios({
          method: 'post',
          url : "http://localhost:8080/login",
          params: {
            "username": username,
            "password": password
          },
      }).then((response)=>{
        console.log(response.data);
        if(response.data.result=="管理员登录成功"){
          var store = {"username":username,"role":"admin"};
          this.$store.commit('login',store);
          this.$router.push("/admin");
        }
        if(response.data.result=="登录成功"){
          store = {"username":username,"role":"user"};
          this.$store.commit('login',store);
          this.$router.push("/selfcenter");
        }
        else if(response.data.code == '-1'){
          alert("您的账户已被管理员禁用，请联系管理员");
        }
      }).catch(error => {
      JSON.stringify(error);
        console.log(error);
      });
    },
  }
}
</script>
