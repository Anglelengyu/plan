<template>
  <div class="homeBox" style="height: 100%">
    <!--<el-container style="height: 100%" direction="vertical">-->
    <!--<el-header id="header" style="height: 100px; background-color: rgba(211,240,243,0.41)">Header</el-header>-->
    <el-container>
      <el-aside width="20%" style="background-color: rgb(238, 241, 246)">
        <el-menu unique-opened router
                 :default-active="defaultUrl"
                 :default-openeds="['1']">
          <template>
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>用户管理(仅管理员可见)</template>
              <el-menu-item-group>
                <template slot="title"></template>
                <el-menu-item index="/user">用户列表</el-menu-item>
                <el-menu-item index="/loginInfo">登录统计</el-menu-item>
                <el-menu-item index="1-3">留言信息管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-message"></i>文章管理</template>
              <el-menu-item-group>
                <template slot="title"></template>
                <el-menu-item index="2-1">发布文章</el-menu-item>
                <el-menu-item index="/article">管理文章</el-menu-item>
                <el-menu-item index="2-3">添加分类</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </template>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <!--</el-container>-->
  </div>
</template>

<script>
  export default {
    name: "Home",
    data() {
      return {
        items: [{
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          },
          {
            date: '2016-05-02',
            name: '王大虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }],
        defaultUrl: '/userList'
      }
    },
    methods: {
      handleClick(row) {
        console.log(row);
      },
      getPath: function () {
        // this.defaultUrl = this.$route.path
        // alert(this.defaultUrl)
        // console.log(this.defaultUrl)
      }
    },
    mounted() {
      let href = window.location.href;
      // alert(href)
      // console.log(href)
      this.defaultUrl = '/' + href.split('/#')[1].split('/')[0];
    },
    watch: {
      '$route': 'getPath'
    }
  }
</script>

<style>
  /* 主要是添加这一段代码，我这是在另一个vue页面中添加的，我成它为 全局布局的vue*/
  /*html, body, #app, .homeBox, .el-container {*/
  /*!*设置内部填充为0，几个布局元素之间没有间距*!*/
  /*padding: 0px;*/
  /*!*外部间距也是如此设置*!*/
  /*margin: 0px;*/
  /*!*统一设置高度为100%*!*/
  /*height: auto;*/
  /*}*/


  /*.el-aside {*/
  /*background-color: #D3DCE6;*/
  /*color: #333;*/
  /*text-align: center;*/
  /*line-height: 600px;*/
  /*}*/

  /*.el-main {*/
  /*background-color: #E9EEF3;*/
  /*color: #333;*/
  /*text-align: center;*/
  /*line-height: 600px;*/
  /*}*/

</style>
