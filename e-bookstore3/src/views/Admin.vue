<template>
  <el-container style="height: 500px; border: 1px solid #eee"> 
    <el-aside width="200px" style="background-color: rgb(238,241,246)">
      <div class="thmbnail">
        <img alt="200x200" src="/static/img/22.png" />
      </div>
      <el-menu :default-openeds="['1','2']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-setting"></i>管理</template>
          <el-menu-item-gropup>
            <template slot="title"></template>
            <el-menu-item index="1-1" @click="goto('/admin/storage')">书籍管理</el-menu-item>
            <el-menu-item index="1-2" @click="goto('/admin/users')">用户管理</el-menu-item>
          </el-menu-item-gropup>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-goods"></i>购物车</template>
          <el-menu-item-gropup>
            <el-menu-item index="2-1">我的购物车</el-menu-item>
          </el-menu-item-gropup>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-tickets"></i>订单</template>
          <el-menu-item-gropup>
            <el-menu-item index="3-1">订单管理</el-menu-item>
            <el-menu-item index="3-2">销量统计</el-menu-item>
          </el-menu-item-gropup>
        </el-submenu>
      </el-menu> 
    </el-aside>
    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'admin',
  methods: {
    goto: function(dest){
      this.$router.push(dest);
    }
  }
}
</script>