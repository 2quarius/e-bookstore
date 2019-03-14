<template>
<el-container width="100px" height="500px">
<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="联系方式" prop="cellphone" required>
    <el-input v-model="ruleForm.cellphone"></el-input>
  </el-form-item>
  <el-form-item label="详细地址" prop="dest">
    <el-input type="textarea" v-model="ruleForm.dest"></el-input>
  </el-form-item>
  <el-form-item label="即时配送" prop="delivery">
    <el-switch v-model="ruleForm.delivery"></el-switch>
  </el-form-item>
  <el-form-item label="支付方式" prop="type">
    <el-checkbox-group v-model="ruleForm.type">
      <el-checkbox label="支付宝" name="type"></el-checkbox>
      <el-checkbox label="微信" name="type"></el-checkbox>
      <el-checkbox label="银联" name="type"></el-checkbox>
      <el-checkbox label="货到付款" name="type"></el-checkbox>
    </el-checkbox-group>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
</el-container>
</template>

<script>
export default {
    name: 'deal-setting',
    data() {
        return {
            ruleForm: {
                cellphone: '',
                dest: '',
                delivery: false,
                type: []
            },
            rules: {
                cellphone: [
                    {required: true, message: '请输入联系方式', trigger: 'blur'},
                    {min: 11, max: 11, message: '长度在 11 个字符', trigger: 'blur'}
                ],
                dest: [
                    {required: true, message: '请输入详细地址1', trigger: 'blur'}
                ],
                type: [
                    {type: 'array', required: true, message: '请至少选择一种支付方式', trigger: 'change'}
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if(valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
</script>
