<template>
	<div>
		<el-input
			v-model="search" style="width:60%"
			size="mini"
			placeholder="输入关键字搜索"/>
	<el-table
		:data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"
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
			label="Property"
			align="left">
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
	<el-pagination
			@size-change="handleSizeChange"
			@current-change="handleCurrentChange"
			:current-page="currentPage"
			:page-sizes="[5, 10, 15, 20]"
			:page-size="pageSize"
			layout="total, sizes, prev, pager, next, jumper"
			:total="totalEntry">
		</el-pagination>
	</div>
</template>

<script>
	export default {
		name: 'user-admin',
		data() {
			return {
				tableData: [],
				search: '',
				currentPage: 1,
				pageSize: 5,
				totalEntry: ''
			}
		},
		mounted: function(){
			this.heplerFunc(0);
		},
		methods: {
			heplerFunc(index){
			var self = this;
			var url = "/get/users/";
			this.getRequest(url)
				.then((response)=>{
					self.totalEntry = response.data.length;
					var tmp = response.data.length>(index+self.pageSize)?new Array(self.pageSize):new Array(response.data.length-index);
					for(var i = index; i<response.data.length&& i<index+self.pageSize;i++)
					{
						tmp[i-index] = response.data[i];
					}
					self.tableData=tmp;
					console.log(self.tableData);
				}).catch(error => {
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
				var index = this.pageSize*(this.currentPage-1);
				this.heplerFunc(index);
			},
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