<template>
	<div>
	<el-table
		:data="tableData.filter(data => !search || data.goodname.toLowerCase().includes(search.toLowerCase()))"
		style="width: 100%;">
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
import store from '../../store';
	export default {
		name: 'userdeals',
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
					url: "http://localhost:8080/deals/user/rate",
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
			var url = "http://localhost:8080/deals/user/"+store.state.user.username;
			console.log(url);
			this.axios
			.get(url, {
				headers: {
					"Access-Control-Allow-Credentials": true,
					"Access-Control-Allow-Origin": true
				},
			})
			.then(response => {
				var tmp = new Array(response.data.length);
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
						j++;
					}
					tmp[i] = {
						id: response.data[i].id,
						date: response.data[i].date,
						rate: response.data[i].rate,
						children:list
					}
				}
				self.tableData = tmp
				console.log(response.data);
			})
			.catch(error => {
				JSON.stringify(error);
				console.log(error);
			});
		}
	}
</script>