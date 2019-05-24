<template>
  <el-carousel :interval="4000" type="card" height="300px">
    <el-carousel-item v-for="item in images" :key="item.url">
      <img alt="" :src="'/static/img/'+item.url" style="height:300px"/>
    </el-carousel-item>
  </el-carousel>
</template>
<style>
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>

<script>
export default {
  name: 'home',
  data(){
    return {
      images: [
        {url: '1.jpg'},
        {url: '2.jpg'},
        {url: '3.jpg'},
        {url: '20.jpg'},
        {url: '21.jpg'},
        {url: '22.jpg'}
      ],
    }
  }
}
</script>

