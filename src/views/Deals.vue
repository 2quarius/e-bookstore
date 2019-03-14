<template>
  <div>
  <el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Date"
      prop="date">
    </el-table-column>
    <el-table-column
      label="Name"
      prop="name">
    </el-table-column>
    <el-table-column
      align="right">
      <template slot="header">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <div class="block">
        <span class="demonstration"></span>
        <el-rate
            v-model="scope.row.value"
            :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
        </el-rate>
        </div>
      </template>
    </el-table-column>
  </el-table>
	<div style="margin-top: 20px"> 
    <el-button size="small" type="primary">确定</el-button>
  </div>
  </div>
</template>

<script>
  export default {
    name: 'deals',
    data() {
      return {
        tableData: [{
          date: '2016-05-02',
          name: 'Java程序设计',
					address: '上海市普陀区金沙江路 1518 弄',
					value: true
				},{
          date: '2016-05-02',
          name: 'Java程序设计',
					address: '上海市普陀区金沙江路 1518 弄',
					value: true
				}
				],
        search: '',
        // value: true
      }
    }
  }
</script>