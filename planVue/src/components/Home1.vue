<template>
  <div class="homeBox">
    <el-container style="height: 100%" direction="vertical">
      <el-header id="header" style="height: 100px; background-color: rgba(211,240,243,0.41)">Header</el-header>
      <el-container>
        <el-aside width="300px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-openeds="['1']">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>导航一</template>
              <el-menu-item-group>
                <!--<template slot="title">分组一</template>-->
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-message"></i>导航二</template>
              <el-menu-item-group>
                <!--<template slot="title">分组二</template>-->
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-container>
          <el-header style="text-align: right; font-size: 20px; height: 30px; background-color: #ffe3d4">
            <el-dropdown>
              <i class="el-icon-setting" style="margin-right: 20px; font-size:20px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>查看</el-dropdown-item>
                <el-dropdown-item>新增</el-dropdown-item>
                <el-dropdown-item>删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <span>王小虎</span>
          </el-header>
          <el-main>
            <el-table :data="items">
              <el-table-column prop="date" label="日期" min-width="10px">
              </el-table-column>
              <el-table-column prop="name" label="姓名" min-width="10px">
              </el-table-column>
              <el-table-column prop="address" label="地址" min-width="50px">
              </el-table-column>
            </el-table>
            <el-pagination
              background
              layout="prev, pager, next, jumper, ->, total"
              :page-size="3"
              :total="items.length">
            </el-pagination>
          </el-main>
        </el-container>
      </el-container>
    </el-container>
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
        }]
      }
    }
  }
</script>

<style>
  /* 主要是添加这一段代码，我这是在另一个vue页面中添加的，我成它为 全局布局的vue*/
  html, body, #app, .homeBox, .el-container {
    /*设置内部填充为0，几个布局元素之间没有间距*/
    padding: 0px;
    /*外部间距也是如此设置*/
    margin: 0px;
    /*统一设置高度为100%*/
    height: 100%;
  }


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
