<template>
  <el-table
		ref="multipleTable"
    :data="tableData"
		tooltip-effect="dark"
    style="width: 100%"
		@selection-change="handleSelectionChange()">
    <el-table-column
      type="selection"
      label="全选"
      width="180">
    </el-table-column>
    <el-table-column
      prop="description"
      label="商品信息"
      width="350">
			<img alt="30x30" src="/static/img/22.png"/>
			<template slot-scope="scope">{{scope.row.description}}</template>
    </el-table-column>
    <el-table-column
      prop="price"
      label="单价（元）"
			width="120">
    </el-table-column>
		<el-table-column
			prop="num"
      label="数量"
			width="150">
			<template slot-scope="scope">
				<div>
				<el-input-number size="mini" v-model="scope.row.num" :min="1"></el-input-number>
				</div>
			</template>
    </el-table-column>
		<el-table-column
      prop="money"
      label="金额（元）"
			width="120">
    </el-table-column>
		<el-table-column
      label="操作"
			width="180"
			show-overflow-tooltip>
			<template slot-scope="scope">
				<el-button size="mini" @click="handleEdit(scope.$index,scope.row)">移入收藏夹</el-button>
				<el-button size="mini" type="danger" @click="handleDelete(scope.$index,scope.row)">删除</el-button>
			</template>
    </el-table-column>
    </el-table>
</template>

<script>
import Good from '@/components/Good.vue'
export default {
		name: 'cart',
		components: Good,
		data() {
			return {
				tableData: [{
					num: 1,
					description: 'description',
					price: 10.21,
					money: this.num*this.price
				},{
					num: 1,
					description: 'description',
					price: '30.21',
					money: '30.21'
				}],
				multipleSelection: []
			}
		},
		methods: {
			toggleSelection(rows){
				if(rows) {
					rows.forEach(row=>{
						this.$refs.multpleTable.toggleSelection(row);
					});
				} else{
					this.$refs.multpleTable.clearSelection();
				}
			},
			handleSelectionChange(val) {
				this.multipleSelection=val;
			},
			handleChange(value) {
				console.log(value);
			},
			handleEdit(index,row){
				console.log(index,row);
			},
			handleDelete(index,row){
				console.log(index,row);
			}
		}
};
</script>