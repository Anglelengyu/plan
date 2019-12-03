<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-menu-item index="1" style="margin-left: 2%">
      <img src="../images/logo.jpg" style="height: 50px"/>
    </el-menu-item>
    <el-submenu index="2" style="margin-left: 2%">
      <template slot="title">首页</template>
      <el-menu-item index="2-1">选项1</el-menu-item>
      <el-menu-item index="2-2">选项2</el-menu-item>
      <el-menu-item index="2-3">选项3</el-menu-item>
    </el-submenu>
    <el-menu-item index="3" style="margin-left: 2%">简介</el-menu-item>

    <div style="width: 15%;float: left;margin: 10px 2% 0px 2%" class="hidden-xs-only">
      <el-input placeholder="搜索博客" v-model="searchTxt" suffix-icon="el-icon-search"
                @keyup.enter.native="searchSubmit"/>
    </div>
    <el-menu-item class="hidden-xs-only" v-if="!isLogin" index="" style="margin-left:80%"
                  @click="loginFormVisible = true">
      <el-button type="text">登录</el-button>
    </el-menu-item>
    <el-submenu index="20" v-if="isLogin"  style="margin-left: 45%">
      <template slot="title">
        <img src="../images/logo.jpg" style="height: 50px"/> &ensp;用户名</template>
      <el-menu-item index="20-1">个人中心</el-menu-item>
      <el-menu-item index="20-2">用户设置</el-menu-item>
    </el-submenu>

  </el-menu>
</template>

<script>
  export default {
    name: "bar",
    data(){
      return{
        isLogin:true,
        searchTxt:'',
        activeIndex:'1'
      }
    },
    methods: {
      handleSelect: function (key, keyPath) {
        alert(key + "************" + keyPath)
      },
      searchSubmit:function () {
        console.log(12)
        if (this.searchTxt.length <= 0)
          return;
        alert(this.searchTxt)
      }
    }
  }
</script>

<style>
  /*.el-row {*/
  /*margin-bottom: 20px;*/
  /*&:last-child {margin-bottom: 0;}*/
  /*}*/
  .el-menu-demo {
    margin-top: -60px;
    margin-left: -10px;
    margin-right: -10px;
    height: 60px;
    background-color: #909399;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 60px;
  }

  .login1 {
    margin-right: 10px;
    background-color: aqua;
    width: 60px;
    /*height: 60px;*/
  }
</style>
