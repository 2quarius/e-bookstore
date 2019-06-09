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
				<el-button size="mini" @click="editFormVisible = true;">Edit</el-button>
				<el-button size="mini" type="danger" @click="handleDelete(props.$index, props.row)">Delete</el-button>
								<el-dialog title="书籍信息" :visible.sync="editFormVisible">
										<el-form>
												<el-form-item label="书籍名称" :label-width="formLabelWidth">
														<el-input v-model="props.row.name" autocomplete="off"></el-input>
												</el-form-item>
												<el-form-item label="ISBN编号" :label-width="formLabelWidth">
														<el-input v-model="props.row.isbn" :label-width="formLabelWidth"></el-input>
												</el-form-item>
												<el-form-item label="库存量" :label-width="formLabelWidth">
														<el-input v-model="props.row.stores" :label-width="formLabelWidth"></el-input>
												</el-form-item>
												<el-form-item label="单价" :label-width="formLabelWidth">
														<el-input v-model="props.row.price" :label-width="formLabelWidth"></el-input>
												</el-form-item>
												<el-form-item label="商品分类" :label-width="formLabelWidth">
							<el-select v-model="props.row.subject" placeholder="请选择合适类别">
								<el-option v-for="item in options" :key="item.label" :label="item.label" :value="item.label">
								</el-option>
							</el-select>
												</el-form-item>
												<el-form-item label="商品描述" :label-width="formLabelWidth">
														<el-input type="textarea" autosize v-model="props.row.descript" :label-width="formLabelWidth"></el-input>
												</el-form-item>
										</el-form>
										<div slot="footer" class="dialog-footer">
												<el-button @click="editFormVisible = false;handleCancel(props.$index, props.row)">取 消</el-button>
												<el-button type="primary" @click="editFormVisible = false;handleSubmit(props.$index, props.row)">确 定</el-button>
										</div>
								</el-dialog>
			</template>
		</el-table-column>
		<el-table-column label="商品名称" prop="name"></el-table-column>
		<el-table-column label="ISBN编号" prop="isbn"></el-table-column>
		<el-table-column label="单价" prop="price"></el-table-column>
	</el-table>
	<br/>
	<el-button style="float:right;" icon="el-icon-edit" size="mini" type="primary" @click="addFormVisible = true;">Add</el-button>
	<el-dialog title="书籍信息" :visible.sync="addFormVisible">
				<el-form>
			<el-form-item label="书籍封面" :label-width="formLabelWidth">
				<el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" auto-upload>
					<i class="el-icon-upload"></i>
					<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
					<div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
				</el-upload>
			</el-form-item>
					<el-form-item label="书籍名称" :label-width="formLabelWidth">
						<el-input v-model="newItem.name" autocomplete="off"></el-input>
			</el-form-item>
						<el-form-item label="ISBN编号" :label-width="formLabelWidth">
								<el-input v-model="newItem.isbn" :label-width="formLabelWidth"></el-input>
						</el-form-item>
						<el-form-item label="库存量" :label-width="formLabelWidth">
								<el-input v-model="newItem.stores" :label-width="formLabelWidth"></el-input>
						</el-form-item>
						<el-form-item label="单价" :label-width="formLabelWidth">
								<el-input v-model="newItem.price" :label-width="formLabelWidth"></el-input>
						</el-form-item>
						<el-form-item label="商品分类" :label-width="formLabelWidth">
				<el-select v-model="newItem.subject" placeholder="请选择合适类别">
					<el-option v-for="item in options" :key="item.label" :label="item.label" :value="item.label">
					</el-option>
				</el-select>
						</el-form-item>
						<el-form-item label="商品描述" :label-width="formLabelWidth">
								<el-input type="textarea" autosize v-model="newItem.descript" :label-width="formLabelWidth"></el-input>
						</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
						<el-button @click="addFormVisible = false;handleCancel()">取 消</el-button>
						<el-button type="primary" @click="addFormVisible = false;handleSubmit()">确 定</el-button>
				</div>
		</el-dialog>
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
	name: "good-admin",
	data() {
		return {
			isActive: false,
			tableData: [],
			search:'',
			currentPage: 1,
			pageSize: 5,
			totalEntry: '',
			editFormVisible: false,
			addFormVisible: false,
			formLabelWidth: '120px',
			options: [{
				value: '选项1',
				label: '科技'
				}, {
				value: '选项2',
				label: '文学'
				}, {
				value: '选项3',
				label: '生活'
			}],
			newItem: {
				name: '',
				isbn: '',
				stores: '',
				price: '',
				subject: '',
				descript: '',
			}
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
		handleCancel: function(index,row){
			if(index&&row){
				var self = this;
				var url = "/storages/"+row.id;
				this.getRequest(url)
				.then(response => {
					self.tableData[index]=response.data;
					console.log(response.data);
				})
				.catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
			}
			else{
				this.newItem = {
					name: '',
					isbn: '',
					stores: '',
					price: '',
					subject: '',
					descript: '',
				}
			}
		},
		handleSubmit: function(index,row){
			if(index&&row){
				if(row.name==''||row.descript==''||row.isbn==''||row.price==''||row.stores==''||row.subject=='')
				{
					this.$message({
						type: 'danger',
						message: '内容不能为空'
					});
				}
				else if(length(row.isbn)<13||length(row.isbn>15)){
					this.$message({
						type: 'danger',
						message: 'ISBN不合法'
					});
				}
				else{
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
				}
			}
			else{
				var self = this;
				var url = "/add/storages/";
				this.postBodyRequest(url,{
					"name":self.newItem.name,
					"descript":self.newItem.descript,
					"isbn":self.newItem.isbn,
					"price":self.newItem.price,
					"stores":self.newItem.stores,
					"subject":self.newItem.subject
				}).then((response)=>{
					if(response.status==200){
						self.tableData = response.data.result.data;
						console.log(response.data);
						this.$message({
							type: 'success',
							message: '添加成功!'
						});
					}
				}).catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
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