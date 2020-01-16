<!--文章-->
<template>
  <el-container style="height: 100%;text-align: left">
    <el-aside width="50%" style="height: 100%;margin-left: 20%">
      <el-card :body-style="{ padding: '10px' }" v-for="(data,index) in articleList">
        <div @mouseenter="pEnter(index)"
             @mouseleave="pLeave()">

          <div style="margin-left: 2%">
            <el-link :underline="false" @click="router(data.id)">
              <h3 id="title">{{data.title}}</h3>
            </el-link>
            <!--<div style="right: 10%;width: 10%">-->
            <span style="margin-left: 80%; width: 5%" title="编辑" v-if="userName === data.userName && index === targetIndex">
              <el-link class="el-icon-edit-outline" :underline="false"></el-link>
            </span>
            <span style="width: 5%;margin-left: 1%;" title="删除" v-if="userName === data.userName && index === targetIndex">
             <el-link class="el-icon-delete" :underline="false"></el-link>
            </span>
            <!--</div>-->
          </div>
          <div style="margin-left: 2%; font-size: 14px;">
            {{bodyTran(data.content)}}
          </div>
          <!--<p style="overflow: hidden;text-overflow:ellipsis; white-space: nowrap;margin-left: 2%;width: 100%">-->
          <div
            style="overflow: hidden;text-overflow:ellipsis; white-space: nowrap;margin-left: 2%;width: 100%;margin-top: 10px">

              <span class="hidden-xs-only" style="width: 5%;text-align: center;">
                <img :src="data.icon" style="height: 30px; margin-bottom: -10px">
              </span>
            <span class="hidden-xs-only" style="width: auto;text-align: center;">
        &nbsp;{{data.name}}</span>

            <!--<span class="el-icon-time hidden-xs-only" style="width: auto">&nbsp;{{getTime(data.createTime)}}</span>-->
            <span class="el-icon-time hidden-xs-only" style="width: auto">&nbsp;{{data.createTime}}</span>

            <!--<span class="el-icon-chat-line-square hidden-xs-only" style="width: 10%">&nbsp;{{discussCount}}</span>-->


            <span class="hidden-xs-only" style="width: auto">
                <!--<span v-for="tag in tags" style="width: 100%">-->
                  <el-tag size="small" type="success" style="margin-left: 5px">{{data.tagName}}</el-tag>
                <!--</span>-->
              </span>
            <span class="el-icon-view hidden-xs-only"
                  style="right: -20%;position: relative;">
              <el-link @click="router(data.id)" :underline="false">
                <span style="color: #8a8a8a">
                   &nbsp;访问人数
                </span>
                <span style="color: #3399ea">
                  {{blogViews}}
                </span>
              </el-link>
              <span style="color: #8a8a8a">
                  |
                </span>
              <el-link @click="router(data.id)" :underline="false">
                <span style="color: #8a8a8a">
                  &nbsp;评论数
                </span>
                <span style="color: #3399ea">
                  {{data.replyCount}}
                </span>
              </el-link>
          </span>
            <!--</p>-->
          </div>
        </div>
      </el-card>
      <div class="block" style="text-align:right ">
        <el-pagination
          background
          @size-change="loadArticle"
          @current-change="loadArticle"
          :current-page.sync="current"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next"
          :total="this.total?this.total:0">
        </el-pagination>
      </div>
    </el-aside>
    <el-main>
      <statistical></statistical>
    </el-main>
  </el-container>
</template>

<script>
  import Statistical from '../sys/Statistical'

  export default {
    name: "article",
    components: {Statistical},
    props: [],
    data() {
      return {
        articleList: [],   //当前页数据
        targetIndex: -1,
        currentDate: new Date(),
        blogViews: 0,
        userName: this.$store.state.user.name,
        id: 1,
        current: 1,
        pageSize: 5,
        total: 0,
        tags: ['1', '2']
      }
    },
    mounted: function () {
      this.loadArticle();
    },
    methods: {
      loadArticle() {
        console.log(this.$store.state.user)
        const param = {
          pagination: {
            current: this.current,
            pageSize: this.pageSize
          },
          sorterList: []
        }
        this.postRequest('/article/list', param).then(resp => {
          console.log(resp.data.data, 1)
          this.articleList = resp.data.data.records;
          this.total = resp.data.data.total;
          if (resp && resp.status == 200) {
          }
        });
      },
      router(id) {
        scrollTo(0, 0);
        console.log(id)
        this.$router.push({ //路由跳转到文章详情
          path: '/article/' + id
        });
      },
      // 编辑
      update: function (id) {
        console.log(id)
        this.$router.push({ //路由跳转到文章新建
          path: '/article/update/' + id
        });
      },
      // 删除
      deleteArt: function (id) {
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.getRequest("/article/deleteArt?id=" + id).then(resp => {
            if (resp.status == 200) {
              this.loadArticle();
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      //时间格式化
      getTime: function (date) {
        return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDay() + 1);
      },
      pEnter: function (index) {
        // console.log(index)
        this.targetIndex = index
      },
      pLeave: function () {
        this.targetIndex = -1
      },
      bodyTran(centent) { //将数据库中带标签的博文转换为纯文本
        let body = centent;
        let cententHtml = centent.replace(/<[^>]+>/g, "");
        return cententHtml.substring(0, (cententHtml.length > 100) ? 100 : cententHtml.length) + ((cententHtml.length > 100) ? "......" : "");
        // var dd = body.replace(new RegExp('#', 'g'), "");
        // dd = dd.replace(new RegExp('!\\[.*\\]\\(.*\\)', 'g'), "[图片]");
        // dd = dd.replace(new RegExp('\\[.*\\]\\(.*\\)', 'g'), "[链接]");
        // dd = dd.replace(new RegExp('-', 'g'), "");
        // dd = dd.replace(new RegExp('>', 'g'), "");
        // dd = dd.replace(new RegExp('`', 'g'), "");
        // return dd;
      }
    }
  }
</script>

<style>

</style>
