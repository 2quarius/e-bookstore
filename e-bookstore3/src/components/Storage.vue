<template>
	<div>
	<el-input v-model="search" style="width: 60%" size="medium"
							placeholder="请输入书名" prefix-icon="el-icon-search"/>
	<el-table :data="tableData.filter(data=>!search || data.name.includes(search))" stripe style="width: 100%">
		<el-table-column type="expand">
			<template slot-scope="props">
				<el-form label-position="left" inline class="demo-table-expand">
					<el-from-item label="封面">
						<img :src="'/static/img/'+props.row.url" @click="selectGood(props.row)">
					</el-from-item>
					<br>
					<el-form-item label="商品名称">
						<span v-if="!props.row.editClicked"
							:class="{active:isActive}"
							@mouseover="changeColor()"
							@mouseleave="changeColor()"
						>{{ props.row.name }}</span>
						<el-input v-if="props.row.editClicked" v-model="props.row.name">{{props.row.name}}</el-input>
					</el-form-item>
					<el-form-item label="商品描述">
						<span v-if="!props.row.editClicked">{{ props.row.descript }}</span>
						<el-input type="textarea" autosize v-if="props.row.editClicked" v-model="props.row.descript">{{props.row.descript}}</el-input>
					</el-form-item>
					<el-form-item label="ISBN编号">
						<span v-if="!props.row.editClicked">{{ props.row.isbn }}</span>
						<el-input v-if="props.row.editClicked" v-model="props.row.isbn">{{props.row.isbn}}</el-input>
					</el-form-item>
					<el-form-item label="库存量">
						<span v-if="!props.row.editClicked">{{ props.row.stores }}</span>
						<el-input v-if="props.row.editClicked" v-model="props.row.stores">{{props.row.stores}}</el-input>
					</el-form-item>
					<el-form-item label="单价">
						<span v-if="!props.row.editClicked">{{ props.row.price }}</span>
						<el-input v-if="props.row.editClicked" v-model="props.row.price">{{props.row.price}}</el-input>
					</el-form-item>
					<el-form-item label="商品分类">
						<span>{{ props.row.subject }}</span>
						<el-input v-if="props.row.editClicked" v-model="props.row.subject">{{props.row.subject}}</el-input>
					</el-form-item>
					<template v-if="admin==='true'">
						<el-button v-if="!props.row.editClicked" size="mini" @click="handleEdit(props.$index, props.row)">Edit</el-button>
						<el-button v-if="props.row.editClicked" size="mini" @click="handleSubmit(props.$index, props.row)">Submit</el-button>
						<el-button size="mini" type="danger" @click="handleDelete(props.$index, props.row)">Delete</el-button>
						<el-button v-if="props.row.editClicked" size="mini" @click="handleCancel(props.$index, props.row)">Cancel</el-button>
					</template>
				</el-form>
			</template>
		</el-table-column>
		<el-table-column label="商品名称" prop="name"></el-table-column>
		<el-table-column label="ISBN编号" prop="isbn"></el-table-column>
		<el-table-column label="描述" prop="descript"></el-table-column>
	</el-table>
	<br/>
	<el-button v-if="admin==='true'" size="mini" type="primary" @click="handleAdd">Add</el-button>
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
			editClicked: false,
			search:'',
		};
	},
	methods: {
		handleEdit: function(index, row){//以下两个函数处理时间极长，不知何故
			row.editClicked = true;
		},
		handleCancel: function(index,row){
			var self = this;
			var url = "/storages/"+row.id;
			this.axios
			.getRequest(url)
			.then(response => {
				self.tableData[index]=response.data;
				console.log(response.data);
			})
			.catch(error => {
				JSON.stringify(error);
				console.log(error);
			});
			row.editClicked = false;
		},
		handleSubmit: function(index,row){
			if(row.name==null||row.descript==null||row.isbn==null||row.price==null||row.stores==null||row.subject)
			{
				this.$message({
					type: 'danger',
					message: '内容不能为空'
				});
			}
			else{
				// var url = "http://localhost:8080/set/storages/"+row.id;
				// var params = {
				// 	"name":row.name,
				// 	"descript":row.descript,
				// 	"isbn":row.isbn,
				// 	"price":row.price,
				// 	"stores":row.stores,
				// 	"subject":row.subject
				// };
				this.axios({
					method: 'put',
					url: "http://localhost:8080/set/storages/"+row.id,
					params:{
						"name":row.name,
						"descript":row.descript,
						"isbn":row.isbn,
						"price":row.price,
						"stores":row.stores,
						"subject":row.subject
					} 
				}).then(response => {
					if(response.status==200){
						this.$message({
							type: 'success',
							message: '修改成功!'
						});
					}
				}).catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
				row.editClicked = false;
			}
		},
		handleDelete: function(index,row){
			if(row.id==null){
				alert("此商品尚未入库,请提交入库申请后再试");
			}
			else{
			this.$confirm('确定删除此商品吗?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.axios({
						method: 'delete',
						url: "http://localhost:8080/delete/storages/"+row.id,
					}).then(response => {
						if(response.status==200){
							this.tableData.splice(index,1);
							this.$message({
								type: 'success',
								message: response.data.result
							});
						}
					}).catch(error => {
						JSON.stringify(error);
						console.log(error);
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});          
				});
			}
		},
		handleAdd: function(){
			this.axios({
				method: 'put',
				url: "http://localhost:8080/add/storages/",
			}).then(response => {
				if(response.status==200){
					console.log(response.data.id);
					var newItem = {
						"id": response.data.id,
						"name": "书籍名称",
						"isbn": "ISBN编号",
						"descript": "商品描述"
					}
					this.tableData.push(newItem);
					this.$message({
						type: 'success',
						message: '已新建商品列,快去编辑吧！'
					});
				}
			}).catch(error => {
				JSON.stringify(error);
				console.log(error);
			});
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
		var self = this;
		var url = "/storages";
		this.axios
			.getRequest(url)
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