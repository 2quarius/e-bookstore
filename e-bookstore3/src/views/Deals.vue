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
						:disabled="disabled"
						allow-half=true
						v-model="scope.row.value"
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
	export default {
		name: 'deals',
		data() {
			return {
				tableData: [{
					date: '2016-05-02',
					name: 'Java程序设计',
					value: false,
				},{
					date: '2016-05-02',
					name: 'Java程序设计',
					value: false
				}
				],
				search: '',
				disabled: false,
				reRate: true
			}
		},
		methods:{
			submit: function(){
				for(var i = 0;i<this.tableData.length;i++)
				{
					console.log(this.tableData[i].value);
				}
				this.disabled = true;
				this.reRate = false;
				console.log(this.store.data.username);

			},
			rechoose: function(){
				this.disabled = false;
				this.reRate = true;
			}
		},
		mounted:function(){
			var self = this;
			console.log("mounted");
			var url = "http://localhost:8080/deals/1";
			this.axios
			.get(url, {
				headers: {
					"Access-Control-Allow-Credentials": true,
					"Access-Control-Allow-Origin": true
				},
				withCredentials: true
			})
			.then(response => {
				self.tableData=response.data;
				console.log(response.data);
				console.log("get response");
			})
			.catch(error => {
				JSON.stringify(error);
				console.log(error);
			});
		}
	}
</script>