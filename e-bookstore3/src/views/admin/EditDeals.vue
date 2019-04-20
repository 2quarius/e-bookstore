<template>
	<div>
		<el-steps :active="active" finish-status="success" simple style="margin-top: 20px">
			<el-step title="确认订单" ></el-step>
			<el-step title="确认发货" ></el-step>
			<el-step title="完成交易" ></el-step>
		</el-steps>
		<el-table v-if="active==0||active==2" :data="tableData" style="width: 100%;margin-bottom: 20px;" row-key="id">
			<el-table-column prop="id" label="订单号" sortable width="180">
			</el-table-column>
			<el-table-column prop="date" label="日期" sortable width="180">
			</el-table-column>
			<el-table-column prop="name" label="商品名称" sortable width="180">
			</el-table-column>
			<el-table-column prop="number" label="商品数量" sortable width="180">
			</el-table-column>
		</el-table>
		<el-table v-if="active==1" :data="tableData" style="width: 100%;margin-bottom: 20px;" row-key="id">
			<el-table-column prop="id" label="订单号" sortable width="180">
			</el-table-column>
			<el-table-column prop="date" label="日期" sortable width="180">
			</el-table-column>
			<el-table-column prop="name" label="商品名称" sortable width="180">
			</el-table-column>
			<el-table-column prop="number" label="商品数量" sortable width="180">
			</el-table-column>
		</el-table>
		<el-button style="margin-top:12px" @click="prev" v-if="active==1||active==2">上一步</el-button>
		<el-button style="margin-top:12px" @click="next" v-if="active==1||active==0">下一步</el-button>
		<el-button style="margin-top:12px" @click="submit" v-if="active==2">提交</el-button>
	</div>
</template>

<script>
export default {
	name: 'editdeals',
	data(){
		return {
			active: 0,
			tableData: [],
		}
	},
	methods: {
		prev(){
			if(this.active--<0){
				this.active = 0;
			}
		},
		next(){
			if(this.active++>2){
				this.active = 0;
			}
		},
		submit(){
			this.$message({
				type: 'success',
				message: '完成交易!'
			});
		}
	},
	mounted: function(){
		var self = this;
    var url = "http://localhost:8080/deals";
    this.axios.get(url,
    {
      headers: {
        "Access-Control-Allow-Credentials":true,
        "Access-Control-Allow-Origin":true,
      },
    }
    ).then((response)=>{
				var tmp = new Array();
				for(var i = 0; i < response.data.length; i++)
				{
					var list = new Array(response.data[i].goodnameToNumber.length);
					var j = 0;
					for(var item in response.data[i].goodnameToNumber)
					{
						list[j] = {
							id: response.data[i].id,
							date: response.data[i].date,
							name: item,
							number: response.data[i].goodnameToNumber[item]
						}
						tmp.push(list[j]);
						j++;
					}
				}
				self.tableData = tmp
        console.log(self.tableData);
      }).catch(error => {
      JSON.stringify(error);
        console.log(error);
    });
	}
}
</script>
