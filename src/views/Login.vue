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
               <label for="inputEmail" class="col-sm-2 control-label">Email</label>
              <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail" ref="Email"/>
              </div>
            </div>
            <div class="form-group">
               <label for="inputPassword" class="col-sm-2 control-label">Password</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" ref="Password"/>
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
                 <button type="submit" class="btn btn-default" @click="msg()">Sign in</button>
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
  methods: {
    msg: function() {
      var username = this.$refs.Email.value;
      alert('Email:'+username);
    }
  }
}
</script>
