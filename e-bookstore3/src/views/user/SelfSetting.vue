<template>
<el-container width="100px" height="500px">
<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="昵称" prop="name" required>
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="性别" prop="gender">
    <el-select style="float: left" v-model="ruleForm.gender" placeholder="请选择性别">
      <el-option label="小姐姐" value="lady"></el-option>
      <el-option label="小哥哥" value="gentleman"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="生日">
    <el-col :span="11">
      <el-form-item prop="date">
        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date" style="width: 100%;"></el-date-picker>
      </el-form-item>
    </el-col>
  </el-form-item>
  <el-form-item label="兴趣爱好" prop="type">
    <el-checkbox-group v-model="ruleForm.type">
      <el-checkbox label="经典文学" name="type"></el-checkbox>
      <el-checkbox label="科技读物" name="type"></el-checkbox>
      <el-checkbox label="育儿心经" name="type"></el-checkbox>
      <el-checkbox label="青春文学" name="type"></el-checkbox>
      <el-checkbox label="报纸期刊" name="type"></el-checkbox>
      <el-checkbox label="烹饪教程" name="type"></el-checkbox>
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
    name: 'self-setting',
    data() {
        return {
            ruleForm: {
                name: '',
                gender: '',
                date: '',
                type: []
            },
            rules: {
                name: [
                    {required: true, message: '请输入昵称', trigger: 'blur'},
                    {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
                ],
                gender: [
                    {required: true, message: '请选择性别', trigger: 'change'}
                ],
                date: [
                    {type: 'date', required: false, message: '请选择日期', trigger: 'change'}
                ],
                type: [
                    {type: 'array', required: true, message: '请至少选择一个爱好', trigger: 'change'}
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
