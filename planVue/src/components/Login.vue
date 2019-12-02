<template>
  <div>
    <el-form :model="loginForm" :rules="rules" ref="loginForm" class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
      <h3 class="login_title">登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username"
                  placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password"
                  placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox class="login_remember" v-model="checked"
                   label-position="left">记住密码
      </el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%" @click="submitClick" round>登录</el-button>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%" @click="registerFormVisible = true" round>注册</el-button>
      </el-form-item>
    </el-form>
    <Content :style="{margin: '80px 0 40px 0'}">
      <router-view></router-view>
    </Content>
    <el-dialog title="注册" :visible.sync="registerFormVisible" width="30%">

      <el-form :rules="rules" :model="form">
        <el-form-item :label-width="formLabelWidth" prop="registerName">
          <el-input v-model="form.registerName" placeholder="用户名" prefixIcon="el-icon-user-solid"/>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="registerPwd">
          <el-input v-model="form.registerPwd" placeholder="密码" :show-password="form.loginShowPwd"
                    prefixIcon="el-icon-lock"/>
        </el-form-item>

        <el-form-item :label-width="formLabelWidth">
          <el-input v-model="form.registerConfirmPwd" placeholder="确认密码" :show-password="form.loginShowPwd"
                    prefixIcon="el-icon-bell"/>
        </el-form-item>


        <!--<el-form-item :label-width="formLabelWidth">-->
        <!--<el-input v-model="form.registerInviteCode" placeholder="邀请码" prefixIcon="el-icon-s-promotion"/>-->
        <!--</el-form-item>-->
        <el-form-item :label-width="formLabelWidth">
          <el-input v-model="form.name" placeholder="真实姓名" prefixIcon="el-icon-s-custom"/>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth">
          <el-input v-model="form.telephone" placeholder="地址" prefixIcon="el-icon-s-home"/>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="telephone">
          <el-input v-model="form.telephone" placeholder="联系电话" prefixIcon="el-icon-phone"/>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth">
          <el-input v-model="form.registerMail" placeholder="邮箱" prefixIcon="el-icon-message"/>
        </el-form-item>
        <!--<el-form-item :label-width="formLabelWidth">-->
        <!--<el-input v-model="form.registerMailCode" placeholder="邮箱验证码" prefixIcon="el-icon-key"/>-->
        <!--</el-form-item>-->
        <!--<el-button @click="sendMail">-->
        <!--发送验证码&nbsp;&nbsp;-->
        <!--<i class="el-icon-coffee-cup" v-if="!sendMailFlag"/>-->
        <!--<i class="el-icon-loading" v-if="sendMailFlag"/>-->
        <!--</el-button>-->
        <el-button @click="userRegister">注册&nbsp;&nbsp;<i class="el-icon-check"></i></el-button>
      </el-form>
    </el-dialog>
    <div>
      <router-link to="/home">Home</router-link>
    </div>
  </div>
</template>
<script>

  import bottom from "@/components/bottom"

  export default {
    comments: {bottom},
    data() {
      return {
        registerFormVisible: false,//注册框
        form: { //表单
          loginName: '',
          loginPwd: '',
          loginShowPwd: true, //登录是否显示密码
          name: '',
          registerName: '',//注册名称
          registerPwd: '',
          registerConfirmPwd: '',
          registerMail: '',
          telephone: '',
          address: ''
          // registerMailCode: ''
          // registerInviteCode: ''
        },
        formLabelWidth: '0px',
        rules: {
          username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          password: [{required: true, message: '请输入密码', trigger: 'blur'}],
          registerName: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          registerPwd: [{required: true, message: '请输入密码', trigger: 'blur'}, {
            validator: (rule, value, callback) => {
              if (value.length < 6) {
                callback(new Error('密码应不少于6个字符'))
              } else if (!/^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,20}/.test(value)) {
                callback(new Error('密码必须由数字和字母组成'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }],
          telephone: [{
            validator: (rule, value, callback) => {
              if (value.trim() === '') {
                callback(new Error('请输入手机号码'))
              } else if (!(/^1[3456789]\d{9}$/.test(value))) {
                callback(new Error('您输入手机号码不正确'))
              } else {
                callback()
              }
            },
            trigger: 'change'
          }]
        },
        checked: true,
        loginForm: {
          username: 'admin',
          password: '123'
        },
        loading: false
      }
    },
    methods: {
      //登录
      submitClick: function () {
        var _this = this;
        this.loading = true;
        this.postRequest('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp => {
          _this.loading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.$store.commit('login', data.obj);
            var path = _this.$route.query.redirect;
            _this.$router
              .replace({path: path == '/' || path == undefined ? '/home' : path});
          }
        });
      },
      //注册
      userRegister: function () {
        var _this = this;
        this.loading = true;
        this.postRequest('/register', {
          name: this.form.name,
          username: this.form.registerName,
          password: this.form.registerPwd,
          telephone: this.form.telephone,
          address: this.form.address
        }).then(resp => {
          _this.loading = false;
          if (resp && resp.status == 200) {
            _this.registerFormVisible = false;
            var data = resp.data;
            var path = _this.$route.query.redirect;
            _this.$router
              .replace({path: '/'});
          }
        });
      }
    }
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
