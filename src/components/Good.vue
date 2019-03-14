<template>
  <el-container style="height: 500px; border: 1px solid #eee"> 
    <el-aside width="200px" style="background-color: rgb(238,241,246)">
      <div class="thmbnail">
        <img alt="200x200" v-bind:src="'/static/img/'+url" />
      </div>
    </el-aside>
    <el-main>
      <template >
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="商品名称">
            <span>{{good_name}}</span>
          </el-form-item>
          <el-form-item label="商品描述">
            <span>{{good_desc}}</span>
          </el-form-item>
          <el-form-item label="ISBN编号">
            <span>{{good_isbn}}</span>
          </el-form-item>
          <el-form-item label="商品分类">
            <span>{{good_category}}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-main>
  </el-container>
</template>

<style>
@import "/lib/css/good.css";
</style>

<script>
export default {
	name: 'Good',
	props: {
		url: String,
		good_name: String,
		good_desc: String,
		good_isbn: String,
		good_category: String
	}
}
</script>
