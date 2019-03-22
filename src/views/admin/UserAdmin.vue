<template>
  <el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="ID"
      prop="id">
    </el-table-column>
    <el-table-column
      label="Name"
      prop="nickname">
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
        <el-switch
          style="display: block"
          v-model="scope.row.status"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-text="启用"
          inactive-text="禁用">
</el-switch>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    name: 'user-admin',
    data() {
      return {
        tableData: [],
        search: ''
      }
    },
    mounted: function(){
    var self = this;
    var url = "http://localhost:8080/users/";
    this.axios.get(url,
    {
      headers: {
        "Access-Control-Allow-Credentials":true,
        "Access-Control-Allow-Origin":true,
      }
    }
    ).then((response)=>{
        self.tableData=response.data;
        console.log(response.data);
        console.log(self.tableData[0].id);
      }).catch(error => {
      JSON.stringify(error);
        console.log(error);
    });
}
  }
</script>