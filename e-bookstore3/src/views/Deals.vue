<template>
	<div>
	<el-table
		:data="tableData.filter(data => !search || data.goodname.toLowerCase().includes(search.toLowerCase()))"
		style="width: 100%">
		<el-table-column
			label="ID"
			prop="id">
		</el-table-column>
		<el-table-column
			label="Date"
			prop="date">
		</el-table-column>
		<el-table-column
			label="Name"
			prop="goodname">
		</el-table-column>
    <el-table-column
			label="Number"
			prop="number">
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
						:disabled="disabled"
						allow-half=true
						v-model="scope.row.rate"
						:colors="['#99A9BF', '#F7BA2A', '#FF9900']">
				</el-rate>
				</div>
			</template>
		</el-table-column>
	</el-table>
	<div style="margin-top: 20px"> 
		<el-button v-if="reRate" size="small" type="primary" @click="submit">确定</el-button>
		<el-button v-if="!reRate" size="small" type="info" @click="rechoose">重置</el-button>
	</div>
	</div>
</template>

<script>
import store from '../store';
	export default {
		name: 'deals',
		data() {
			return {
				tableData: [],
				search: '',
				disabled: false,
				reRate: true
			}
		},
		methods:{
			submit: function(){
				var ids = null;
				var rates = null;
				for(var i = 0;i<this.tableData.length;i++)
				{
					if(ids!=null){
						ids = ids + ',' +this.tableData[i].id;
					}
					else {
						ids = this.tableData[i].id;
					}
					if(rates!=null){
						rates = rates + ',' +this.tableData[i].rate;
					}
					else {
						rates = this.tableData[i].rate;
					}
				}
				this.axios({
					method: 'post',
					url: "http://localhost:8080/deals/rate",
					headers: {
						"Access-Control-Allow-Credentials": true,
						"Access-Control-Allow-Origin": true
					},
					params:{
						"idlist":ids,
						"ratelist":rates
					} 
				})
				.then(response => {
					console.log(response.data);
					console.log("get response");
				})
				.catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
				this.disabled = true;
				this.reRate = false;

			},
			rechoose: function(){
				this.disabled = false;
				this.reRate = true;
			}
		},
		mounted:function(){
			var self = this;
			console.log("mounted");
			console.log(store.state.user.username);
			var url = "http://localhost:8080/deals/"+store.state.user.username;
			console.log(url);
			this.axios
			.get(url, {
				headers: {
					"Access-Control-Allow-Credentials": true,
					"Access-Control-Allow-Origin": true
				},
			})
			.then(response => {
				self.tableData=response.data;
				console.log(response.data);
			})
			.catch(error => {
				JSON.stringify(error);
				console.log(error);
			});
		}
	}
</script>