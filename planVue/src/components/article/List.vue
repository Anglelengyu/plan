<template>
  <div>
    <div style="text-align: right">
      <el-button  type="primary" size="small" plain style="margin-right: 5%" @click="createArticle()" >发布文章</el-button>
    </div>
    <div>
      <el-table :data="articleList" style="width: 90%; margin-left: 2%">
        <el-table-column fixed prop="userName"
                         label="用户名"
                         min-width="10px">
        </el-table-column>
        <el-table-column
          prop="title"
          label="标题"
          min-width="10px">
        </el-table-column>
        <el-table-column
          prop="issue_date"
          label="自动发布时间"
          min-width="10px">
        </el-table-column>
        <el-table-column
          prop="ip"
          label="IP"
          min-width="20px">
        </el-table-column>
        <el-table-column fixed="right" label="操作" min-width="10px">
          <template slot-scope="scope">
            <el-button @click="detail(scope.row.id)" type="text" size="small">查看</el-button>
            <el-button @click="deleteArt(scope.row.id)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        @size-change="loadArticleList"
        @current-change="loadArticleList"
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
        articleList: [],
        current: 1,
        pageSize: 10,
        total: 0
      }
    },
    mounted() {
      this.loadArticleList()
    },
    methods: {
      // 获取所有文章信息
      loadArticleList: function () {
        const param = {
          pagination: {
            current: this.current,
            pageSize: this.pageSize
          },
          sorterList: []
        }
        this.postRequest('/article/list', param).then(resp => {
          this.articleList = resp.data.data.records;
          this.total = resp.data.data.total;
          if (resp && resp.status == 200) {
          }
        });
      },
      // 新建
      createArticle: function (id) {
        this.$router.push({ //路由跳转到文章新建
          path: '/article/create'
        });
      },
      // 详情
      detail: function (id) {
        console.log(id)
        this.$router.push({ //路由跳转到文章新建
          path: '/article/detail/' + id
        });
      },
      // 删除
      deleteArt: function (id) {
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

<style scoped>

</style>
