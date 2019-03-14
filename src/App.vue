<template>
  <div id="app">
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
           <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">e-bookstore</a>
        </div>
        
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="active">
              <router-link to="/">主页</router-link>
            </li>
            <li>
              <router-link to='/login'>购物车</router-link>
            </li>
            <li class="dropdown">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown">登录/注册<strong class="caret"></strong></a>
              <ul class="dropdown-menu">
                <li>
                  <router-link to='/selfcenter'>个人中心</router-link>
                </li>
                <li>
                   <router-link to='/login'>购物车</router-link>
                </li>
                <li>
                  <router-link to='/login'>个人设置</router-link>
                </li>
                <li class="divider">
                </li>
                <li>
                  <router-link to='/login'>注册新账号</router-link>
                </li>
                <li class="divider">
                </li>
                <li>
                  <router-link to='/login'>注销</router-link>
                </li>
              </ul>
            </li>
          </ul>
          <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input type="text" class="form-control" />
            </div> <button type="submit" class="btn btn-default">查找</button>
          </form>
          <ul class="nav navbar-nav navbar-right">
            <li>
               <router-link to='/login'>购物车</router-link>
            </li>
            <li class="dropdown">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown">登录/注册<strong class="caret"></strong></a>
              <ul class="dropdown-menu">
                <li>
                  <router-link to='/admin'>个人中心</router-link>
                </li>
                <li>
                  <router-link to='/login'>个人设置</router-link>
                </li>
                <li>
                  <router-link to='/login'>注册新账号</router-link>
                </li>
                <li class="divider">
                </li>
                <li>
                  <router-link to='/login'>注销</router-link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        
      </nav>
    <router-view/>
  </div>
</template>

<style>
@import "/lib/css/app.css";
</style>
