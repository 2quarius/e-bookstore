<template>
  <el-table
    :data="tableData.filter(data => !search || data.nickname.toLowerCase().includes(search.toLowerCase()))"
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
          v-if="scope.row.status!='2'"
          v-model="scope.row.status"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-text="启用"
          active-value="1"
          inactive-text="禁用"
          inactive-value="0"
          @change="toggle(scope.row.id,scope.row.status)">
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
    mounted: function(){//TODO:获取所有用户部分需要修改
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
        console.log(self.tableData);
      }).catch(error => {
      JSON.stringify(error);
        console.log(error);
    });
    },
    methods: {
      toggle: function(id,stats){//【TODO】禁用用户部分需要修改
        console.log(stats);
        let s = new URLSearchParams;
        s.append("status",stats);
        this.axios({
          method: 'post',
          url : "http://localhost:8080/users/"+id,
          data: s
        }
        ).then((response)=>{
            console.log(response.data.id);
            console.log("success");
        }).catch(error => {
            JSON.stringify(error);
            console.log(error);
        });
      }
    }
  }
</script>