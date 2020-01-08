<template>
  <div>
    <!--<h1>用户詳情</h1>-->
    <!--<p>id是{{this.$route.params.id}}</p>-->
    <el-form :model="detailForm" :rules="rules" ref="detailForm" label-width="100px" class="demo-detailForm">
      <el-row :gutter="20">
        <el-col :span="7">
          <el-form-item label="用户名称" prop="username">
            <el-input v-model="detailForm.username"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="出生日期" prop="birthDate">
            <el-date-picker type="date" placeholder="选择日期" v-model="detailForm.birthDate"
                            style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="性别" prop="gender" style="width: 100%">
            <el-select v-model="detailForm.gender" clearable placeholder="请选择性别">
              <el-option label="男" :value= "1" ></el-option>
              <el-option label="女" :value= "2" ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <!--</el-row>-->
        <!--<el-row :gutter="20">-->
        <el-col :span="7">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="detailForm.email"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="联系电话" prop="telephone">
            <el-input v-model="detailForm.telephone"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="注册时间" prop="registerDate">
            <el-date-picker type="date" placeholder="选择日期" v-model="detailForm.registerDate"
                            style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col span="7">
          <el-form-item label="住址" prop="address">
            <el-input v-model="detailForm.address"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="备注" prop="remark">
        <el-input type="textarea" v-model="detailForm.remark" style="width: 50%"></el-input>
      </el-form-item>
      <!--<el-form-item>-->
      <!--<el-button type="primary" @click="submitForm('detailForm')">修改</el-button>-->
      <!--<el-button @click="resetForm('detailForm')">取消</el-button>-->
      <!--</el-form-item>-->
    </el-form>
    <div style="text-align: left; margin-left: 2%;font-size: 14px; font-weight: 700;">登录记录</div>
    <el-table :data="loginInfo" style="width: 90%; margin-left: 2%" height="300">
      <el-table-column fixed prop="loginTime"
                       label="登录时间"
                       min-width="10px">
      </el-table-column>
      <el-table-column
        prop="loginTime"
        label="登录时间"
        min-width="10px">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        min-width="10px">
      </el-table-column>
      <!--<el-table-column-->
      <!--prop="city"-->
      <!--label="市区"-->
      <!--min-width="10px">-->
      <!--</el-table-column>-->
      <el-table-column
        prop="ip"
        label="IP"
        min-width="20px">
      </el-table-column>
      <!--<el-table-column-->
      <!--prop="zip"-->
      <!--label="邮编"-->
      <!--min-width="10px">-->
      <!--</el-table-column>-->
    </el-table>
    <div>
      <el-button type="primary" @click="submitForm(detailForm)">修改</el-button>
      <el-button href="javascript: void(0);" role="button" onclick="window.history.back()">取消</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    name: "detail",
    data() {
      return {
        detailForm: {},
        loginInfo: [],
        rules: {
          username: [
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
          ],
          birthDate: [
            {type: 'date', required: true, message: '请选择出生日期', trigger: 'blur'}
          ],
          gender: [
            {required: true, message: '请选择性别', trigger: 'blur'}
          ],
          email: [
            {required: true, message: '请输入邮箱', trigger: 'blur'}
          ],
          telephone: [
            {required: true, message: '请输入联系电话', trigger: 'blur'}
          ],
          // registerDate: [
          //   {required: true, message: '请选择活动资源', trigger: 'change'}
          // ],
          // remark: [
          //   {required: true, message: '请填写活动形式', trigger: 'blur'}
          // ]
        }
      };
    },
    mounted: function () {
      this.loadDetail();
      this.loadLoginInfo();
    },
    methods: {
      // 详情信息
      loadDetail: function () {
        let userId = this.$route.params.id;
        this.getRequest("/user/detail/" + userId).then(resp => {
          this.detailForm = resp.data.data;
        })
      },
      // 登录信息
      loadLoginInfo: function () {
        let userId = this.$route.params.id;
        this.getRequest("/user/loginInfo/" + userId).then(resp => {
          this.loginInfo = resp.data.data;
        })
      },
      submitForm(formName) {
        const updateForm = {
          id: this.detailForm.id,
          name: this.detailForm.name,
          birthDate: this.detailForm.birthDate,
          gender: this.detailForm.gender,
          email: this.detailForm.email,
          telephone: this.detailForm.telephone,
          registerDate: this.detailForm.registerDate,
          remark: this.detailForm.remark,
          address: this.detailForm.address
        }
        console.log(updateForm)
        this.postRequest("/user/update", updateForm).then(resp => {
          // window.location.reload();
          this.loadDetail();
          this.loadLoginInfo();
        })
        // this.$refs[formName].validate((valid) => {
        //   if (valid) {
        //     alert('submit!');
        //   } else {
        //     console.log('error submit!!');
        //     return false;
        //   }
        // });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style>
  .demo-detailForm {
    text-align: left;
  }

</style>
