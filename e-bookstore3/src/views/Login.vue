<template>
	<el-form label-position="top" style="width:80%;padding:50px" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
	<el-form-item prop="username" label="用户名">
		<el-input v-model="ruleForm.username"></el-input>
	</el-form-item>
	<el-form-item label="密码" prop="password">
		<el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
	</el-form-item>
	<el-form-item>
		<el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
	</el-form-item>
</el-form>
</template>

<script>
export default {
	name: 'login',
	data(){
		var validatePass = (rule, value, callback) => {
		if (value === '') {
			callback(new Error('请输入密码'));
		} else {
			if (this.ruleForm.checkPass !== '') {
			this.$refs.ruleForm.validateField('checkPass');
			}
			callback();
		}
		};
		return {
		ruleForm: {
			password: '',
			username: ''
		},
		rules: {
			password: [
				{ validator: validatePass, trigger: 'blur' }
			],
			username: [
				{ required: true, message: '请输入用户名', trigger: 'blur' },
			]
		}
		};
	},
	methods: {//密码需要加密，公钥私钥加密
		// sign: function() {
		//   var username = this.$refs.username.value;
		//   var password = this.$refs.password.value;
		//   console.log(username);
		//   alert('Username:'+username);
		//   this.postParamRequest('/login',{
		//     "username": username,
		//     "password": password
		//   }).then((response)=>{
		//     console.log(response.data);
		//     if(response.data.result=="管理员登录成功"){
		//       var store = {"username":username,"role":"admin"};
		//       this.$store.commit('login',store);
		//       this.$router.push("/admin");
		//     }
		//     if(response.data.result=="登录成功"){
		//       store = {"username":username,"role":"user"};
		//       this.$store.commit('login',store);
		//       this.$router.push("/selfcenter/newCart");
		//     }
		//     else if(response.data.code == '-1'){
		//       alert("您的账户已被管理员禁用，请联系管理员");
		//     }
		//   }).catch(error => {
		//   JSON.stringify(error);
		//     console.log(error);
		//   });
		// },
		submitForm(formName) {
		this.$refs[formName].validate((valid) => {
			if (valid) {
				var username = this.ruleForm.username;
				var password = this.ruleForm.password;
				alert('Username:'+username);
			this.postParamRequest('/login',{
				"username": username,
				"password": password
			}).then((response)=>{
				console.log(response.data);
				if(response.data.result=="管理员登录成功"){
					var store = {"username":username,"role":"admin"};
					this.$store.commit('login',store);
					this.$router.push("/admin");
				}
				if(response.data.result=="登录成功"){
					store = {"username":username,"role":"user"};
					this.$store.commit('login',store);
					this.$router.push("/selfcenter/newCart");
				}
				else if(response.data.code == '-1'){
					alert("您的账户已被管理员禁用，请联系管理员");
				}
			}).catch(error => {
			JSON.stringify(error);
				console.log(error);
			});
			} else {
			console.log('error submit!!');
			return false;
			}
		});
		},
	}
}
</script>
