<template>
<div> 
<el-table :data="tableData" border style="width: 100%" @selection-change="selected"> 
<el-table-column type="selection" width="50"> </el-table-column>
<el-table-column label="商品名称" width="680"> 
<template slot-scope="scope"> 
<div style="margin-left: 50px">
<img :src="'/static/img/'+scope.row.url"/>
<span style="font-size: 18px;padding-left: 200px;">{{scope.row.name}}</span> 
</div>
</template> 
</el-table-column>
<el-table-column label="单价" width="150" prop="price"> </el-table-column> 
<el-table-column label="数量" width="200">
<template slot-scope="scope"> 
<div> 
<el-input v-model="scope.row.number" @change="handleInput(scope.row)"> 
<el-button slot="prepend" @click="del(scope.row)" size="mini">
<i class="el-icon-minus"></i>
</el-button>
<el-button slot="append" @click="add(scope.row)" size="mini">
<i class="el-icon-plus"></i>
</el-button>
</el-input>
</div> 
</template> 
</el-table-column> 
<el-table-column label="小计" width="150" prop="goodTotal"> </el-table-column> 
<el-table-column label="操作">
<template slot-scope="scope"> 
<el-button type="danger" @click="handleDelete(scope.$index)"> 删除
<i class="el-icon-delete2 el-icon--right"></i>
</el-button>
</template> 
</el-table-column>
</el-table> <br> 
<el-button type="primary" style="float: right">提交订单</el-button>
<el-button type="info" style="float: right">{{"商品总额：¥" + moneyTotal}}</el-button>
</div>
</template>

<script>
export default {
  name: 'newCart',
  data() {
    return {
tableData: [],
 moneyTotal:0, 
multipleSelection:[],
 } 
},
methods: {
  handleDelete(index) { 
this.$confirm('确定删除该商品？', '提示', { 
confirmButtonText: '确定', 
cancelButtonText: '取消', 
type: 'warning' 
}).then(() => { 
//删除数组中指定的元素 
 this.tableData.splice(index,1);
 this.$message({ type: 'success', message: '删除成功!' }); 
}).catch(() => { 
this.$message({ type: 'info', message: '已取消删除' });
 });
 }, 
handleInput:function(value){
 if(null==value.number || value.number==""){
 value.number=1;
 }
 value.goodTotal=(value.number*value.price).toFixed(2);
//保留两位小数 //增加商品数量也需要重新计算商品总价 
this.selected(this.multipleSelection); 
}, 
add:function(addGood){ 
//输入框输入值变化时会变为字符串格式返回到js
 //此处要用v-model，实现双向数据绑定 
if(typeof addGood.number=='string'){ 
addGood.number=parseInt(addGood.number);
 }
addGood.number+=1; 
this.handleInput(addGood.number);
}, 
del:function(delGood){ 
if(typeof delGood.number=='string'){ 
delGood.number=parseInt(delGood.number); 
}
 if(delGood.number>1){
 delGood.number-=1; 
} 
this.handleInput(delGood.number);
}, 
//返回的参数为选中行对应的对象 
selected:function(selection){ 
this.multipleSelection=selection;
 this.moneyTotal=0;
 //此处不支持forEach循环，只能用原始方法了
 for(var i=0;i<selection.length;i++){
 //判断返回的值是否是字符串
 if(typeof selection[i].goodTotal=='string'){
 selection[i].goodTotal=parseInt(selection[i].goodTotal); 
}
this.moneyTotal+=selection[i].goodTotal;
 }}
},
mounted: function(){
    var self = this;
    var url = "http://localhost:8080/storages/";
    this.axios.get(url,
    {
      headers: {
        "Access-Control-Allow-Credentials":true,
        "Access-Control-Allow-Origin":true,
      }
    }
    ).then((response)=>{
        self.tableData = response.data;
        for(var i = 0;i < self.tableData.length; i++){
          self.tableData[i].number = 1;
          self.tableData[i].goodTotal = self.tableData[i].price;
          console.log(self.tableData[i].number);
        }
    }).catch(error => {
      JSON.stringify(error);
        console.log(error);
    });
}
}
</script>

