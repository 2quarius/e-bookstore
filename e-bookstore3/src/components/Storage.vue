<template>
	<div>
	<el-input v-model="search" style="margin:20px;width: 50%" size="medium"
							placeholder="请输入书名" prefix-icon="el-icon-search" clearable/>
	<el-table :data="tableData.filter(data=>!search || data.name.includes(search))" stripe style="width: 100%">
		<el-table-column type="expand">
			<template slot-scope="props">
				<el-form label-position="left" inline class="demo-table-expand">
					<el-from-item label="封面">
						<img style="width:200px;height:200px" :src="'/static/img/'+props.row.url" @click="selectGood(props.row)">
					</el-from-item>
					<br>
					<el-form-item label="商品名称">
						<span :class="{active:isActive}"
							@mouseover="changeColor()"
							@mouseleave="changeColor()"
						>{{ props.row.name }}</span>
					</el-form-item>
					<el-form-item label="商品描述">
						<span>{{ props.row.descript }}</span>
					</el-form-item>
					<el-form-item label="ISBN编号">
						<span>{{ props.row.isbn }}</span>
					</el-form-item>
					<el-form-item label="库存量">
						<span>{{ props.row.stores }}</span>
					</el-form-item>
					<el-form-item label="单价">
						<span>{{ props.row.price }}</span>
					</el-form-item>
					<el-form-item label="商品分类">
						<span>{{ props.row.subject }}</span>
					</el-form-item>
				</el-form>
			</template>
		</el-table-column>
		<el-table-column label="商品名称" prop="name"></el-table-column>
		<el-table-column label="ISBN编号" prop="isbn"></el-table-column>
		<el-table-column label="单价" prop="price"></el-table-column>
	</el-table>
	<br/>
	<el-button style="float:right;" icon="el-icon-edit" v-if="admin==='true'" size="mini" type="primary" @click="handleAdd">Add</el-button>
	<el-pagination style="margin:10px;width:70%"
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

<style>
@import "/lib/css/storage.css";
</style>

<script>
export default {
	name: "Storage",
	data() {
		return {
			isActive: false,
			tableData: [],
			search:'',
			currentPage: 1,
			pageSize: 5,
			totalEntry: '',
		};
	},
	methods: {
		heplerFunc(index){
			var self = this;
			var url = "/storages/"+this.pageSize;
			this.postParamRequest(url,{
				"index": index,
			})
			.then(response => {
				self.tableData=response.data.result.data;
				self.totalEntry = response.data.result.entries;
				console.log(response.data);
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
			var index = this.pageSize*(this.currentPage-1);
			this.heplerFunc(index);
		},
		selectGood:function(argc){
			this.$router.push({
				path: '/details',
				query: {goodId: argc.id}
			})
			console.log(argc.id);
		},
		goto: function(dest) {
			this.$router.push(dest);
		},
		changeColor: function() {
			this.isActive = !this.isActive;
		}
	},
	props: {
		admin: String
	},
	mounted: function() {
		this.handleCurrentChange(1);
	}
};
</script>