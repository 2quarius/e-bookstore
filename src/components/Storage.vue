<template>
	<el-table
		:data="tableData"
		style="width: 100%">
		<el-table-column type="expand">
			<template slot-scope="props">
				<el-form label-position="left" inline class="demo-table-expand">
					<el-from-item label="封面">
			<img src="/static/img/good0.jpg" @click="goto('/details')"/>
					</el-from-item>
					<br />
					<el-form-item label="商品名称">
						<span :class="{active:isActive}" @mouseover="changeColor()" @mouseleave="changeColor()">{{ props.row.name }}</span>
					</el-form-item>
					<el-form-item label="商品描述">
						<span>{{ props.row.desc }}</span>
					</el-form-item>
					<el-form-item label="ISBN编号">
						<span>{{ props.row.ISBN }}</span>
					</el-form-item>
					<el-form-item label="库存量">
						<span>{{ props.row.storage }}</span>
					</el-form-item>
					<el-form-item label="单价">
						<span>{{ props.row.price }}</span>
					</el-form-item>
					<el-form-item label="商品分类">
						<span>{{ props.row.category }}</span>
					</el-form-item>
					<el-form-item label="店铺 ID">
						<span>{{ props.row.shopId }}</span>
					</el-form-item>
					<el-form-item label="店铺地址">
						<span>{{ props.row.address }}</span>
					</el-form-item>
					<template v-if="admin==='true'">
		<el-button  
				size="mini"
				@click="handleEdit(scope.$index, scope.row)">Edit</el-button>
		<el-button 
				size="mini"
				type="danger"
				@click="handleDelete(scope.$index, scope.row)">Delete</el-button>
					</template>
				</el-form>
			</template>
		</el-table-column>
		<el-table-column
			label="商品名称"
			prop="name">
		</el-table-column>
		<el-table-column
			label="ISBN编号"
			prop="ISBN">
		</el-table-column>
		<el-table-column
			label="描述"
			prop="desc">
		</el-table-column>
	</el-table>
</template>

<style>
@import '/lib/css/storage.css';
</style>

<script>
	export default {
	name: 'Storage',
	data() {
			return {
				isActive: false,
				tableData: [{
					index: 0,
					name: 'Java语言程序设计',
					desc: 'Daniel Liang的经典著作，全面整合Java 8的特性，采用“基础优先，问题驱动”的教学方式，循序渐进地介绍了程序设计基础、解决问题的方法、面向对象程序设计、图形用户界面设计、异常处理',
					ISBN: '9787111506904',
					storage: '20',
					price: '¥55',
					category: '科技',
					shopId: '10333',
					address: '上海市普陀区真北路'
				}, {
					name: 'Java语言程序设计',
					desc: 'Daniel Liang的经典著作，全面整合Java 8的特性，采用“基础优先，问题驱动”的教学方式，循序渐进地介绍了程序设计基础、解决问题的方法、面向对象程序设计、图形用户界面设计、异常处理',
					ISBN: '9787111506904',
					storage: '20',
					price: '¥55',
					category: '科技',
					shopId: '10333',
					address: '上海市普陀区真北路'
				}, {
					name: 'Java语言程序设计',
					desc: 'Daniel Liang的经典著作，全面整合Java 8的特性，采用“基础优先，问题驱动”的教学方式，循序渐进地介绍了程序设计基础、解决问题的方法、面向对象程序设计、图形用户界面设计、异常处理',
					ISBN: '9787111506904',
					storage: '20',
					price: '¥55',
					category: '科技',
					shopId: '10333',
					address: '上海市普陀区真北路'
				}, {
					name: 'Java语言程序设计',
					desc: 'Daniel Liang的经典著作，全面整合Java 8的特性，采用“基础优先，问题驱动”的教学方式，循序渐进地介绍了程序设计基础、解决问题的方法、面向对象程序设计、图形用户界面设计、异常处理',
					ISBN: '9787111506904',
					storage: '20',
					price: '¥55',
					category: '科技',
					shopId: '10333',
					address: '上海市普陀区真北路'
				}]
			}
		},
		methods: {
			goto: function(dest){
				this.$router.push(dest);
			},
			changeColor:function(){
				this.isActive = !this.isActive;
			}
		},
		props: {
			admin: String
		}
	}
</script>