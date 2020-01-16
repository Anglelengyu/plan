<template>
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
    <!--<el-main>-->
      <!--<router-view></router-view>-->
    <!--</el-main>-->
    <el-main>
      <!--<el-table :data="items"-->
                <!--:default-sort = "{prop: 'date', order: 'descending'}"-->
                <!--@sort-change="changeTableSort">-->
      <el-table :data="items">
        <el-table-column prop="name" label="用户名" sortable="custom" min-width="10px">
        </el-table-column>
        <el-table-column prop="username" label="登录名" min-width="10px">
        </el-table-column>
        <el-table-column prop="telephone" label="联系电话" min-width="10px">
        </el-table-column>
        <el-table-column prop="address" label="地址" min-width="30px">
        </el-table-column>
        <el-table-column prop="remark" label="备注" min-width="30px">
        </el-table-column>
        <el-table-column fixed="right" label="操作" min-width="10px">
          <template slot-scope="scope">
            <el-button @click="detail(scope.row.id)" type="text" size="small">查看</el-button>
            <el-button @click="deleteUser(scope.row.id)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        @size-change="loadUserList"
        @current-change="loadUserList"
        :current-page.sync="current"
        :page-sizes="[5, 10, 15, 20]"
        :page-size.sync="pageSize"
        layout="total, sizes, prev, pager, next"
        :total="this.total?this.total:0">
      </el-pagination>
    </el-main>
  </el-container>
</template>

<script>

  export default {
    name: "AllUserList",
    data() {
      return {
        current: 1,
        pageSize: 5,
        total: 0,
        items: []
      }
    },
    mounted: function () {
      this.loadUserList();
    },
    methods:{
      // 获取所有用户
      loadUserList: function(){
        const param = {
          pagination: {
            current: this.current,
            pageSize: this.pageSize
          },
          sorterList:[]
          // username: this.form.registerName,
          // password: this.form.registerPwd,
        }
        // let url = "/" + this.currentPage + "/" + this.pageSize;
        // console.log(url)
        this.postRequest('/user/list',param).then(resp => {
          this.items = resp.data.data.records;
          this.total = resp.data.data.total;

          console.log(resp.data.data);
          console.log(this.items);
          console.log(this.total);
          if (resp && resp.status == 200) {
            // var data = resp.data;
            // _this.$store.commit('login', data.obj);
            // var path = _this.$route.query.redirect;
            // _this.$router
            //   .replace({path: path == '/' || path == undefined ? '/home' : path});
          }
        });
      },
      detail: function (id) {
        console.log(id);
        this.$router.push({ //路由跳转到文章详情
          path: '/user/detail/' + id
        });
      },
      deleteUser: function (id) {
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // TODO 调用删除接口
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    }
  }
</script>

<style>
  .userList {
    height: 100%;
  }

</style>
