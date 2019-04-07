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
               <label for="inputEmail" class="col-sm-2 control-label" v-bind="signOrRegister">{{signOrRegister}}</label>
              <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail" ref="email"/>
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
                 <button type="submit" class="btn btn-default" @click="register()">Register</button>
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
  data() {
    return{
    signOrRegister: 'ID',
    }
  },
  methods: {//登录部分需要修改
    sign: function() {
      this.signOrRegister = 'ID';
      var userid = this.$refs.email.value;
      var password = this.$refs.password.value;
      alert('ID:'+userid);
      var url = "http://localhost:8080/users/"+userid;
      this.axios.get(url,
      {
        headers: {
        "Access-Control-Allow-Credentials":true,
        "Access-Control-Allow-Origin":true,
        }
      }).then((response)=>{
        console.log(response.data);
        //send password through internet is not safe, so i should package it with a kind of hash. and use it both in frontend and backend
        if(response.data.status==="1"&&response.data.password===password){
          this.$store.commit('login',response.data);
          this.$router.push("/selfcenter");
        }
        else if(response.data.status==='2'){
          this.$store.commit('login',response.data);
          this.$router.push("/admin");
        }
        else if(response.data.status==="0"){
          alert("your id is disabled");
        }
      }).catch(error => {
      JSON.stringify(error);
        console.log(error);
      });
    },
    register: function() {
      this.signOrRegister = 'Name';
      var usrname = this.$refs.email.value;
      var password = this.$refs.password.value;
      let s = new URLSearchParams;
      s.append("nickname",usrname);
      s.append("password",password);
      s.append("status",'1');
      // var url = "http://localhost:8080/users/";
      this.axios({
          method: 'post',
          url : "http://localhost:8080/users/",
          data: s
      }).then((response)=>{
        alert("your ID is: "+response.data.id+"\nPlease remember it as your qq number because we cannot remind you of that again")
      })
    }
  }
}
</script>
