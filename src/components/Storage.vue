<template>
	<el-table :data="tableData" style="width: 100%">
		<el-table-column type="expand">
			<template slot-scope="props">
				<el-form label-position="left" inline class="demo-table-expand">
					<el-from-item label="封面">
						<img :src="'/static/img/'+props.row.url" @click="goto('/details')">
					</el-from-item>
					<br>
					<el-form-item label="商品名称">
						<span
							:class="{active:isActive}"
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
					<template v-if="admin==='true'">
						<el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
						<el-button
							size="mini"
							type="danger"
							@click="handleDelete(scope.$index, scope.row)"
						>Delete</el-button>
					</template>
				</el-form>
			</template>
		</el-table-column>
		<el-table-column label="商品名称" prop="name"></el-table-column>
		<el-table-column label="ISBN编号" prop="isbn"></el-table-column>
		<el-table-column label="描述" prop="descript"></el-table-column>
	</el-table>
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
			tableData: [
					{
							url: 'good0.jpg',
							name: 'java',
							descript: "123",
							isbn: "321",
							stores: 20,
							price: "¥50",
							subject: "科技"
					},
					{
							url: 'good1.jpg',
							name: 'java',
							descript: "123",
							isbn: "321",
							stores: 20,
							price: "¥50",
							subject: "科技"
					}
			]
		};
	},
	methods: {
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
		var self = this;
		var url = "http://localhost:8080/storages";
		this.axios
			.get(url, {
				headers: {
					"Access-Control-Allow-Credentials": true,
					"Access-Control-Allow-Origin": true
				}
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
};
</script>