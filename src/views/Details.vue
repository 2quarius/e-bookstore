<template>
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
								<p class="content">{{name}}</p>
							</nav>
							<hr data-am-widget="divider" class="am-divider am-divider-default"/>
							<nav class="am-titlebar-nav">
								<p class="my-title-l">ISBN</p>
								<p class="my-title-r">图书编号</p>
								<p class="content">{{isbn}}</p>
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
							<el-button type="success" round>加入购物车</el-button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<style>
@import "/lib/css/details.css";
</style>

<script>
export default {
		name: 'details',
		data(){
			return{
				id: this.$root.query.goodId,
				loading: false,
				url: 'good0.jpg',
						name: 'Java语言程序设计',
						isbn: '9787111506904',
						subject: '科技',
						descript: 'aniel Liang的经典著作，全面整合Java 8的特性，采用“基础优先，问题驱动”的教学方式，循序渐进地介绍了程序设计基础、解决问题的方法、面向对象程序设计、图形用户界面设计、异常处理',
						num: 1,
						price: 55,
						totalMoney: 55
			}
		},
		created(){
			this.fetchData();
		},
		methods: {
			fetchData(){
				this.loading = true;
				var self = this;
				var url = "http://localhost:8080/storages/"+self.$root.query.goodId;
				console.log(self.$root.query.goodId);
				this.axios.get(url,
				{
					headers: {
				"Access-Control-Allow-Credentials":true,
				"Access-Control-Allow-Origin":true,
					}
				}
				).then((response)=>{
					self.data = response.data
				}).catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
			},
			handleChange:function(){
				this.totalMoney = this.num*this.price;
			}
		}
}
</script>
