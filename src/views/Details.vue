<template>
<div>
  <el-row>
  </el-row>
  <el-row>
  <el-col :span="3">
    <div class="grid-content bg-purple-dark">
      <img src="/static/img/good0.jpg" float="left"/>
    </div>
  </el-col>
  <el-col :span="18">
  <el-row>
  <el-col :span="3"><div class="row-bg"></div></el-col>
  <el-col :span="3"><div class="grid-content bg-purple">书名</div></el-col>
  <el-col :span="15"><div class="grid-content bg-purple-light">Java语言程序设计</div></el-col>
</el-row>
<el-row>
  <el-col :span="3"><div class="row-bg"></div></el-col>
  <el-col :span="3"><div class="grid-content bg-purple">ISBN</div></el-col>
  <el-col :span="15"><div class="grid-content bg-purple-light">9787111506904</div></el-col>
</el-row>
<el-row>
  <el-col :span="3"><div class="row-bg"></div></el-col>
  <el-col :span="3"><div class="grid-content bg-purple">类别</div></el-col>
  <el-col :span="15"><div class="grid-content bg-purple-light">科技</div></el-col>
</el-row> 
<el-row>
  <el-col :span="3"><div class="row-bg"></div></el-col>
  <el-col :span="3"><div class="grid-content bg-purple">详情</div></el-col>
  <el-col :span="15"><div class="grid-content bg-purple-light">Daniel Liang的经典著作，全面整合Java 8的特性，采用“基础优先，问题驱动”的教学方式，循序渐进地介绍了程序设计基础、解决问题的方法、面向对象程序设计、图形用户界面设计、异常处理</div></el-col>
</el-row>
<el-row>
  <el-col :span="3"></el-col>
  <el-col :span="5">
  <el-input-number value=1 size="medium" min="1"></el-input-number>
  </el-col>
  <el-col :span="3">
  <el-button type="primary" icon="el-icon-sold-out" circle></el-button>
  </el-col>
</el-row>
  </el-col>
</el-row>
</div>
</template>

<style>
@import "/lib/css/details.css";
</style>

<script>
export default {
    name: 'details'
}
</script>
