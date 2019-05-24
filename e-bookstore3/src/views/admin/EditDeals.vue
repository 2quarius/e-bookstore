<template>
	<div>
		<el-steps :active="active" finish-status="success" simple style="margin-top: 20px">
			<el-step title="确认订单" ></el-step>
			<el-step title="确认发货" ></el-step>
			<el-step title="完成交易" ></el-step>
		</el-steps>
		<el-table v-if="active==0||active==1||active==2" :data="tableData" style="width: 100%;margin-bottom: 20px;" row-key="id">
		<el-table-column type="expand">
			<template slot-scope="props">
				<el-table :data="props.row.children" style="width: 100%;margin-bottom: 20px;" row-key="id">
					<el-table-column prop="id" label="订单号" sortable width="180">
					</el-table-column>
					<el-table-column prop="date" label="日期" sortable width="180">
					</el-table-column>
					<el-table-column prop="name" label="商品名称" sortable width="180">
					</el-table-column>
					<el-table-column prop="number" label="商品数量" sortable width="180">
					</el-table-column>
				</el-table>
			</template>
		</el-table-column>
		<el-table-column
			label="ID"
			prop="id">
		</el-table-column>
		<el-table-column
			label="Date"
			prop="date">
		</el-table-column>
	</el-table>
		<el-pagination
			@size-change="handleSizeChange"
			@current-change="handleCurrentChange"
			:current-page="currentPage"
			:page-sizes="[5, 10, 15, 20]"
			:page-size="pageSize"
			layout="total, sizes, prev, pager, next, jumper"
			:total="totalEntry">
		</el-pagination>
		<el-button style="margin-top:12px;float:right" @click="prev" v-if="active==1||active==2">上一步</el-button>
		<el-button style="margin-top:12px;float:right" @click="next" v-if="active==1||active==0">下一步</el-button>
		<el-button style="margin-top:12px;float:right" @click="submit" v-if="active==2">提交</el-button>
	</div>
</template>

<script>
export default {
	name: 'editdeals',
	data(){
		return {
			active: 0,
			tableData: [],
			currentPage: 1,
			pageSize: 5,
			totalEntry: 8,
		}
	},
	methods: {
		heplerFunc(index){
			var self = this;
			var url = "/deals";
			this.getRequest(url)
				.then(response => {
					self.totalEntry = response.data.length;
					var tmp = response.data.length>(index+self.pageSize)?new Array(self.pageSize):new Array(response.data.length-index);
					for(var i = index; i < response.data.length && i < index + self.pageSize; i++)
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
							j++;
						}
						tmp[i-index] = {
							id: response.data[i].id,
							date: response.data[i].date,
							rate: response.data[i].rate,
							children:list
						}
					}
					self.tableData = tmp
				})
				.catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
		},
		handleSizeChange(val) {
			this.pageSize = val;
			var index = this.pageSize*(this.currentPage-1);
			this.heplerFunc(index);
		},
		handleCurrentChange(val) {
			this.currentPage = val;
			console.log(this.currentPage);
			var index = this.pageSize*(this.currentPage-1);
			console.log(index);
			this.heplerFunc(index);
		},
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
		this.heplerFunc(0);
		// var self = this;
		// var url = "/deals";
		// this.getRequest(url)
		// 	.then(response => {
		// 		var tmp = new Array(response.data.length);
		// 		for(var i = 0; i < response.data.length; i++)
		// 		{
		// 			var list = new Array(response.data[i].goodnameToNumber.length);
		// 			var j = 0;
		// 			for(var item in response.data[i].goodnameToNumber)
		// 			{
		// 				list[j] = {
		// 					id: response.data[i].id,
		// 					date: response.data[i].date,
		// 					name: item,
		// 					number: response.data[i].goodnameToNumber[item]
		// 				}
		// 				j++;
		// 			}
		// 			tmp[i] = {
		// 				id: response.data[i].id,
		// 				date: response.data[i].date,
		// 				rate: response.data[i].rate,
		// 				children:list
		// 			}
		// 		}
		// 		self.tableData = tmp
		// 		console.log(response.data);
		// 	})
		// 	.catch(error => {
		// 		JSON.stringify(error);
		// 		console.log(error);
		// 	});
	}
}
</script>
