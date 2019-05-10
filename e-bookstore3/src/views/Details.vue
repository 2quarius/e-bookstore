<template>
<div class="product_wrapper">
	<div class="product_main clearfix">
		<div class="my-syabout">
			<div class="am-container my-qcwbj">
				<hr data-am-widget="divider" class="am-divider am-divider-default"/>
				<div data-am-widget="intro" class="am-intro am-cf am-intro-default">
					<div class="am-g am-intro-bd">
						<div class="am-intro-left am-u-sm-5"><img :src="'static/img/'+url" alt="小娜"/></div>
						<div class="am-intro-right am-u-sm-7">
							<div data-am-widget="titlebar" class="am-titlebar am-titlebar-default">
								<nav class="am-titlebar-nav">
									<p class="my-title-l">name</p>
									<p class="my-title-r">书名</p>
									<p class="content" :data="name">{{name}}</p>
								</nav>
								<hr data-am-widget="divider" class="am-divider am-divider-default"/>
								<nav class="am-titlebar-nav">
									<p class="my-title-l">ISBN</p>
									<p class="my-title-r">图书编号</p>
									<p class="content" :data="isbn">{{isbn}}</p>
								</nav>
								<hr data-am-widget="divider" class="am-divider am-divider-default"/>
								<nav class="am-titlebar-nav">
									<p class="my-title-l">catagory</p>
									<p class="my-title-r">类别</p>
									<p class="content">{{subject}}</p>
								</nav>
							<hr data-am-widget="divider" class="am-divider am-divider-default"/>
							<nav class="am-titlebar-nav">
								<p class="my-title-l">description</p>
								<p class="my-title-r">详情</p>
								<p class="my-title-c">{{descript}}</p>
							</nav>
							<hr data-am-widget="divider" class="am-divider am-divider-default"/>
							<el-input-number size="medium" v-model="num" @change="handleChange" :min="1" :max="stores" label="描述文字"></el-input-number>
							<p class="money" v-bind="totalMoney">{{'¥'+totalMoney}}</p>
							<el-button type="success" round @click="add2cart">加入购物车</el-button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="product_content">
		<comment :comments="commentData" :bookId="id"></comment>
	</div>
</div>
</template>
<style>
@import "/lib/css/details.css";
</style>

<script>
import store from '../store.js'
import comment from '../components/Comment.vue'
export default {
		name: 'details',
		components: {
			comment
		},
		data(){
			return{
				id: this.$route.query.goodId,
				url: '',
				name: '',
				isbn: '',
				subject: '',
				descript: '',
				num: 1,
				price: '',
				totalMoney: '',
				stores: '',
				commentData: [],
			}
		},
		created(){
			
		},
		mounted(){
			this.fetchData();
		},
		methods: {
			fetchData(){
				this.loading = true;
				var self = this;
				var urlForInfo = "/storages/"+self.id;
				console.log(self.id);
				this.getRequest(urlForInfo).then((response)=>{
					self.$data.url = response.data.url;
					self.$data.name = response.data.name;
					self.$data.isbn = response.data.isbn;
					self.$data.subject = response.data.subject;
					self.$data.descript = response.data.descript;
					self.$data.price = response.data.price;
					self.$data.totalMoney = response.data.price;
					self.$data.stores = response.data.stores;
					console.log(self.$data);
					console.log(response.data);
				}).catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
				var urlForComment = "/book_comment/"+self.id;
				this.getRequest(urlForComment).then((response)=>{
					console.log(response.data.result.comments);
					this.commentData = response.data.result.comments;
				}).catch(error=>{
					JSON.stringify(error);
					console.log(error);
				});
			},
			handleChange:function(){
				this.totalMoney = this.num*this.price.toFixed(2);
			},
			add2cart: function(){
				if(store.state.user.username==null){
					this.$message({
						type: 'info',
						message: '请先登录'
					});
				}
				else{ 
					var self = this;
					var url = "/cart/"+store.state.user.username;
					this.postBodyRequest(url,{
						"goodid":self.id,
						"number":self.num
					}).then((response)=>{
						if(response.status!=200){
							alert("something wrong!");
						}
						else{
							this.$message({
								type: 'success',
								message: '已成功加入购物车'
							});
						}
				}).catch(error => {
						JSON.stringify(error);
						console.log(error);
				});
				}
			}
		}
}
</script>
