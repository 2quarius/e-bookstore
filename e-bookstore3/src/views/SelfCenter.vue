<template>
  <el-container style="height: 1000px; border: 1px solid #eee"> 
    <el-aside width="200px" height="1000px" style="background-color: rgb(238,241,246)">
      <div class="thmbnail">
        <img alt="200x200" src="/static/img/22.png" />
      </div>
      <el-menu :default-openeds="['1','2']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-setting"></i>设置</template>
          <el-menu-item-gropup>
            <template slot="title"></template>
            <el-menu-item index="1-1" @click="goto('/selfcenter/selfSetting')">账户设置</el-menu-item>
            <el-menu-item index="1-2" @click="goto('/selfcenter/dealSetting')">交易设置</el-menu-item>
          </el-menu-item-gropup>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-goods"></i>购物车</template>
          <el-menu-item-gropup>
            <el-menu-item index="2-1" @click="goto('/selfcenter/newCart')">我的购物车</el-menu-item>
          </el-menu-item-gropup>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-tickets"></i>订单</template>
          <el-menu-item-gropup>
            <el-menu-item index="3-1" @click="goto('/selfcenter/deals')">历史订单</el-menu-item>
            <el-menu-item index="3-2">已付款</el-menu-item>
            <el-menu-item index="3-3">已发货</el-menu-item>
            <el-menu-item index="3-4">已签收</el-menu-item>
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
  name: 'self-center',
  methods: {
    goto: function(dest){
      this.$router.push(dest);
    }
  }
}
</script>
