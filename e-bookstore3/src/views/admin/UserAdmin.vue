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
      prop="username">
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
    mounted: function(){
    var self = this;
    var url = "http://localhost:8080/get/users/";
    this.axios.get(url,
    {
      headers: {
        "Access-Control-Allow-Credentials":true,
        "Access-Control-Allow-Origin":true,
      },
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
      toggle: function(id,stats){
        this.axios({
          method: 'post',
          url : "http://localhost:8080/set/users/"+id,
          params:{"status":stats}
        }
        ).then((response)=>{
            if(response.status!=200){
              alert("something wrong!");
            }
            else{
              console.log("succeed!");
            }
        }).catch(error => {
            JSON.stringify(error);
            console.log(error);
        });
      }
    }
  }
</script>