<template>
  <!--<el-card id="bar">-->
  <div>
    <el-row>
      <el-menu
        :router="true"
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        style="width: 100%"
        @select="handleSelect"
        text-color="D3F0F3"
        active-text-color="#ffd04b">
        <el-menu-item index="1" route="/" style="margin-left: 0%">
          <img src="../assets/logo.png" style="height: 50px"/>
        </el-menu-item>
        <el-menu-item index="2" style="margin-left: 2%" route="/articleList">所有文章</el-menu-item>
        <el-menu-item index="3" style="margin-left: 2%" route="/introduction">简介</el-menu-item>
        <el-menu-item index="4" style="margin-left: 2%" v-if="user.username !=''" route="/userArticleList">我的文章
        </el-menu-item>

        <div style="width: 15%;float: left;margin: 10px 2% 0px 2%" class="hidden-xs-only">
          <el-input placeholder="搜索文章" v-model="searchTxt" suffix-icon="el-icon-search"
                    @keyup.enter.native="searchSubmit"/>
        </div>
        <el-menu-item class="hidden-xs-only" v-if="this.$store.state.user.username ==''" index=""
                      style="margin-left: 45%"
                      @click="loginFormVisible = true">
          登录
        </el-menu-item>
        <el-submenu index="20" v-if="user.username !=''" style="margin-left: 30%">
          <template slot="title">
            {{user.name}}
            <i><img v-if="user.icon!=''" :src="this.$store.state.user.icon"
                    style="width: 40px;height: 40px;margin-right: 5px;margin-left: 5px;border-radius: 40px"/></i>
          </template>
          <el-menu-item index="/manager">个人中心</el-menu-item>
          <!--<el-menu-item index="202">用户设置</el-menu-item>-->
          <el-menu-item index="203">退出登录</el-menu-item>
        </el-submenu>
        <el-dialog title="登录" :visible.sync="loginFormVisible" width="21%">
          <el-form :rules="rules" :model="loginForm" ref="loginForm" class="login-container" label-position="left"
                   label-width="0px" v-loading="loading">
            <!--<h3 class="login_title">登录</h3>-->
            <el-form-item prop="username">
              <el-input type="text" v-model="loginForm.username" prefixIcon="el-icon-user-solid"
                        placeholder="账号"></el-input>
              用户名
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="loginForm.password" :show-password="loginForm.loginShowPwd"
                        @keyup.enter.native="userLogin" prefixIcon="el-icon-lock"
                        placeholder="密码"></el-input>
              密码
            </el-form-item>
            <!--<el-checkbox class="login_remember" v-model="checked"-->
            <!--label-position="left">记住密码-->
            <!--</el-checkbox>-->
            <el-form-item style="width: 100%">
              <el-button type="primary" style="width: 100%" @click="userLogin" round>登录</el-button>
            </el-form-item>
            <el-form-item style="width: 100%">
              <el-button type="primary" style="width: 100%" @click="registerFormVisible = true" round>注册</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </el-menu>
    </el-row>
  </div>
  <!--</el-card>-->
</template>

<script>
  export default {
    name: "bar",
    data() {
      return {
        isLogin: false,
        loginFormVisible: false,  //登录框
        searchTxt: '',
        activeIndex: '1',
        loginForm: {
          username: 'admin',
          password: '123',
          loginShowPwd: true
        },
        rules: {
          username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          password: [{required: true, message: '请输入密码', trigger: 'blur'}]
        }
      }
    },
    computed: {
      user() {
        return this.$store.state.user;
      }
    },
    methods: {
      handleSelect: function (key, keyPath) {
        let num = Number(key)
        switch (num) {
          case 1:
            // alert(key + "************1")
            break;
          case 203:
            // alert(key + "************1")
            this.logout()
            break;
          default:
            break;
        }
      },
      searchSubmit: function () {
        if (this.searchTxt.length <= 0)
          return;
        alert(this.searchTxt)
      },
      //登录
      userLogin: function () {
        var _this = this;
        _this.postRequest('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp => {

          // _this.$store.commit('login', resp.data.data)
          // console.log(resp.data.data)
          // console.log(this.$store.state.user)
          _this.$message({
            message: '登录成功',
            type: 'success'
          });
          this.loginFormVisible = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            //存入用户信息
            _this.$store.commit('login', resp.data.data)
            console.log(this.$store.state.user);
            var path = _this.$route.query.redirect;
            _this.$router
              .replace({path: path == '/' || path == undefined ? '/manager' : path});
          }
        });
      },
      //退出登录
      logout: function () {
        this.postRequest('/user/logout').then(resp =>{

        })
        this.$store.commit('logout')
      }
    }
  }
</script>

<style>
  #bar {
    /*margin-top: auto;*/
    /*margin-bottom: 0px;*/
    height: 100px;
    text-align: center;
    display: flex;
    width: 100%;
    /*background-image: url("../assets/background.jpg");*/
  }

  /*.el-row {*/
  /*margin-bottom: 20px;*/
  /*&:last-child {margin-bottom: 0;}*/
  /*}*/
  /*.el-menu-demo {*/
  /*background-image: url("../assets/background.jpg");*/
  /*background-size: 100% 100%;*/

  /*}*/

  .el-menu-demo {
    margin-top: -60px;
    margin-left: -20px;
    margin-right: -20px;
    height: 60px;
    background-color: #f7fefe;
  }

</style>
