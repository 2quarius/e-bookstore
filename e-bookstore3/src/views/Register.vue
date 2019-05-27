<template>
	<el-form label-position="top" :model="ruleForm" style="width:80%;padding:50px" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
	<el-form-item prop="email" label="邮箱" >
	<el-input v-model="ruleForm.email"></el-input>
	</el-form-item>
	<el-form-item prop="username" label="用户名">
	<el-input v-model="ruleForm.username"></el-input>
	</el-form-item>
	<el-form-item label="密码" prop="pass">
	<el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
	</el-form-item>
	<el-form-item label="确认密码" prop="checkPass">
	<el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
	</el-form-item>
	<el-form-item>
	<el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
	<el-button @click="resetForm('ruleForm')">重置</el-button>
	</el-form-item>
</el-form>
</template>

<script>
	export default {
		name:'register',
		data() {
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
		var validatePass2 = (rule, value, callback) => {
		if (value === '') {
			callback(new Error('请再次输入密码'));
		} else if (value !== this.ruleForm.pass) {
			callback(new Error('两次输入密码不一致!'));
		} else {
			callback();
		}
		};
		return {
		ruleForm: {
			pass: '',
			checkPass: '',
			email: '',
			username: ''
		},
		rules: {
			pass: [
				{ validator: validatePass, trigger: 'blur' }
			],
			checkPass: [
				{ validator: validatePass2, trigger: 'blur' }
			],
			email: [
				{ required: true, message: '请输入邮箱地址', trigger: 'blur' },
				{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
			],
			username: [
				{ required: true, message: '请输入用户名', trigger: 'blur' },
			]
		}
		};
	},
	methods: {
		submitForm(formName) {
		this.$refs[formName].validate((valid) => {
			if (valid) {
				var username = this.ruleForm.username;
				var password = this.ruleForm.pass;
				this.axios({
					method: 'post',
					url : "http://localhost:8080/users/",
					params: {
						"username": username,
						"password": password,
						"status": "1"
					}
				}).then((response)=>{
					this.$message({
						type: 'success',
						message: response.data.result
					});
					this.$router.push('/login');
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
		resetForm(formName) {
		this.$refs[formName].resetFields();
		},
	}
	}
</script>