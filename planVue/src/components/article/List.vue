<template>
  <div>
    <div style="text-align: right">
      <el-button  type="primary" size="small" plain style="margin-right: 5%" @click="createArticle()" >发布文章</el-button>
    </div>
    <div>
      <el-table :data="loginInfo" style="width: 90%; margin-left: 2%">
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
        <el-table-column
          prop="ip"
          label="IP"
          min-width="20px">
        </el-table-column>
        <el-table-column fixed="right" label="操作" min-width="10px">
          <template slot-scope="scope">
            <el-button @click="detail(scope.row.userId)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        @size-change="loadLoginInfoList"
        @current-change="loadLoginInfoList"
        :current-page.sync="current"
        :page-sizes="[5, 10, 15, 20]"
        :page-size.sync="pageSize"
        layout="total, sizes, prev, pager, next"
        :total="this.total?this.total:0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    name: "List",
    data() {
      return {
        loginInfo: [],
        current: 1,
        pageSize: 10,
        total: 0
      }
    },
    mounted() {
      this.loadLoginInfoList()
    },
    methods: {
      // 获取所有用户登录信息
      loadLoginInfoList: function () {
        this.loading = true;
        const param = {
          pagination: {
            current: this.current,
            pageSize: this.pageSize
          },
          sorterList: []
          // username: this.form.registerName,
          // password: this.form.registerPwd,
        }
        // let url = "/" + this.currentPage + "/" + this.pageSize;
        // console.log(url)
        this.postRequest('/user/loginInfoList', param).then(resp => {
          this.loginInfo = resp.data.data.records;
          this.total = resp.data.data.total;

          if (resp && resp.status == 200) {
          }
        });
      },
      // 新建
      createArticle: function (id) {
        console.log(id);
        this.$router.push({ //路由跳转到文章新建
          path: '/article/create'
        });
      }
    }
  }
</script>

<style scoped>

</style>
