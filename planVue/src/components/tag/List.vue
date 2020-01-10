<template>
  <div>
    <div style="text-align: right">
      <el-button type="primary" size="small" plain style="margin-right: 5%" @click="tagVisible = true">新建标签</el-button>
    </div>
    <div>
      <el-table :data="tagList" style="width: 90%; margin-left: 2%">
        <el-table-column fixed prop="id"
                         label="id"
                         min-width="10px">
        </el-table-column>
        <el-table-column
          prop="name"
          label="标签名"
          min-width="10px">
        </el-table-column>
        <el-table-column
          prop="remark"
          label="备注"
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
            <el-button @click="changeStatus(scope.row.id,scope.row.status)" type="text" size="small">{{scope.row.status
              ===1? "禁用":"启用"}}
            </el-button>
            <el-button @click="deleteTag(scope.row.id)" type="text" size="small">删除</el-button>
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
    <el-dialog title="创建标签" :visible.sync="tagVisible" width="21%">
      <el-form :rules="rules" :model="tagForm" ref="tagForm" class="login-container" label-position="left"
               label-width="0px" v-loading="loading">
        <el-form-item prop="name">
          <el-input type="text" v-model="tagForm.name"
                    placeholder="标签名称"></el-input>
        </el-form-item>
        <el-form-item prop="remark">
          <el-input type="textarea"
                    :rows="2"
                    v-model="tagForm.remark"
                    placeholder="备注"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" size="small" plain style="margin-right: 5%" @click="create(tagForm)" round>保存
      </el-button>
      <el-button type="primary" size="small" plain style="margin-right: 5%" @click="tagVisible = false" round>取消
      </el-button>
    </el-dialog>
    <el-dialog title="标签详情" :visible.sync="tagUpdateVisible" width="21%">
      <el-form :rules="rules" :model="tagUpdateForm" ref="tagForm" class="login-container" label-position="left"
               label-width="0px" v-loading="loading">
        <el-form-item prop="name">
          <el-input type="text" v-model="tagUpdateForm.name"
                    placeholder="标签名称"></el-input>
        </el-form-item>
        <el-form-item prop="remark">
          <el-input type="textarea"
                    :rows="2"
                    v-model="tagUpdateForm.remark"
                    placeholder="备注"></el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" size="small" plain style="margin-right: 5%" @click="update(tagUpdateForm)" round>保存
      </el-button>
      <el-button type="primary" size="small" plain style="margin-right: 5%" @click="tagUpdateVisible = false" round>取消
      </el-button>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "List",
    data() {
      return {
        tagList: [],
        tagForm: {},
        tagUpdateForm: {},
        current: 1,
        pageSize: 10,
        total: 0,
        tagVisible: false,
        tagUpdateVisible: false,
        rules: {
          name: [{required: true, message: '请输入活动名称', trigger: 'blur'}]
        }
      }
    },
    mounted() {
      this.loadArticleList()
    },
    methods: {
      // 获取所有信息
      loadArticleList: function () {
        const param = {
          pagination: {
            current: this.current,
            pageSize: this.pageSize
          },
          sorterList: []
        }
        this.postRequest('/tag/list', param).then(resp => {
          this.tagList = resp.data.data.records;
          this.total = resp.data.data.total;
          if (resp && resp.status == 200) {
          }
        });
      },
      // 新建
      create: function (id) {
        const param = {
          name: this.tagForm.name,
          remark: this.tagForm.remark
        }
        this.postRequest("/tag/create", param).then(resp => {
          console.log(resp.data)
          if (resp.data.code == 200) {
            this.$message({
              message: '新建成功',
              type: 'success'
            });
            this.tagVisible = false;
            this.loadArticleList();
          }
        })
      },
      // 修改
      update: function (id) {
        const param = {
          id: this.tagUpdateForm.id,
          name: this.tagUpdateForm.name,
          remark: this.tagUpdateForm.remark
        }
        this.postRequest("/tag/update", param).then(resp => {
          console.log(resp.data)
          if (resp.data.code == 200) {
            this.$message({
              message: '新建成功',
              type: 'success'
            });
            this.tagUpdateVisible = false;
            this.loadArticleList();
          }
        })
      },
      // 详情
      detail: function (id) {
        console.log(id)
        this.getRequest("/tag/detail/" + id).then(resp => {
          console.log(resp.data)
          if (resp.data.code == 200) {
            this.tagUpdateVisible = true;
            this.tagUpdateForm = resp.data.data;
          }
        });
      },
      // 禁用启用
      changeStatus: function (id, status) {
        console.log(id)
        this.getRequest("/tag/changeStatus?dataStatus=&status=" + (status === 1 ? 0 : 1) + "&id=" + id).then(resp => {
          console.log(resp.data)
          if (resp.data.code == 200) {
            this.$message({
              type: 'success',
              message: (status === 1 ? "禁用" : "启用") + '成功!'
            });
            this.loadArticleList();
          }
        });
      },
      // 删除
      deleteTag: function (id) {
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // TODO 调用删除接口
          this.getRequest("/tag/changeStatus?status=&dataStatus=" + 0 + "&id=" + id).then(resp => {
            if (resp.data.code == 200) {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }
            this.loadArticleList();
          })
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

</style>
