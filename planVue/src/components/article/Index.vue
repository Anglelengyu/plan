<!--文章-->
<template>
  <el-container style="height: 100%;text-align: left">
    <el-aside width="50%" style="height: 100%;margin-left: 20%">
      <el-card :body-style="{ padding: '10px' }" v-for="(data,index) in 4">
        <div @mouseenter="pEnter(Index)"
             @mouseleave="pLeave()">
          <div style="position: absolute;right: 10%;width: 10%">
          <span style="width: 5%" title="编辑" v-if="Index === targetIndex">
            <el-link class="el-icon-edit-outline" :underline="false"></el-link>
          </span>
            <span style="width: 5%;margin-left: 1%;" title="删除" v-if="Index === targetIndex">
           <el-link class="el-icon-delete" :underline="false"></el-link>
          </span>
          </div>
          <div style="margin-left: 2%">
            <el-link :underline="false" @click="router(Index)">
              <h3 id="title">title</h3>
            </el-link>
          </div>
          <div style="margin-left: 2%; font-size: 14px;">
            {{bodyTran(Index)}}
          </div>
          <!--<p style="overflow: hidden;text-overflow:ellipsis; white-space: nowrap;margin-left: 2%;width: 100%">-->
          <div style="overflow: hidden;text-overflow:ellipsis; white-space: nowrap;margin-left: 2%;width: 100%;margin-top: 10px">

              <span class="hidden-xs-only" style="width: 5%;text-align: center;">
                <img src="../../assets/qq.png" style="height: 30px; margin-bottom: -10px">
              </span>
            <span class="el-icon-user-solid hidden-xs-only" style="width: 10%;text-align: center;">
        &nbsp;{{name}}</span>

            <span class="el-icon-time hidden-xs-only" style="width: 20%">&nbsp;{{getTime(currentDate)}}</span>

            <!--<span class="el-icon-chat-line-square hidden-xs-only" style="width: 10%">&nbsp;{{discussCount}}</span>-->


            <span class="hidden-xs-only" style="width: 40%">
                <span v-for="tag in tags" style="width: 100%">
                  <el-tag size="small" type="success" style="margin-left: 5px">{{tag}}</el-tag>
                </span>
              </span>
            <span class="el-icon-view hidden-xs-only"
                  style="right: -20%;position: relative;" >
              <el-link @click="router(Index)" :underline="false">
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
              <el-link @click="router(Index)" :underline="false">
                <span style="color: #8a8a8a">
                  &nbsp;评论数
                </span>
                <span style="color: #3399ea">
                  {{discussCount}}
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
          :current-page.sync="currentPage"
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
        discussCount: 0,
        name: '作者', //this.$store.state.user.username,
        id: 1,
        currentPage: 1,
        pageSize: 5,
        total: 20,
        tags: ['1', '2']
      }
    },
    mounted: function () {
      this.loadArticle();
    },
    methods: {
      loadArticle() {
        this.loading = true;
        let url = "/" + this.currentPage + "/" + this.pageSize;
        console.log(url)
        this.getRequest('/articleList' + url).then(resp => {
          this.loading = false;
          this.articleList = resp.data.data;
          this.total = resp.data.total;
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.$store.commit('login', data.obj);
            var path = _this.$route.query.redirect;
            _this.$router
              .replace({path: path == '/' || path == undefined ? '/home' : path});
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
      //时间格式化
      getTime: function (date) {
        return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDay() + 1);
      },
      pEnter: function (index) {
        // console.log(index)
        this.targetIndex = Index
      },
      pLeave: function () {
        this.targetIndex = -1
      },
      bodyTran() { //将数据库中带标签的博文转换为纯文本
        let body = "# 注意事项↵异步调用需要拿到代理之后的对象或者注入的对象才有效，直接调用方法并不能实现异步调用↵# pom.xml↵``` <parent>↵        <groupId>org.springframework.boot</groupId>↵        <artifactId>spring"
        var dd = body.replace(new RegExp('#', 'g'), "");
        dd = dd.replace(new RegExp('!\\[.*\\]\\(.*\\)', 'g'), "[图片]");
        dd = dd.replace(new RegExp('\\[.*\\]\\(.*\\)', 'g'), "[链接]");
        dd = dd.replace(new RegExp('-', 'g'), "");
        dd = dd.replace(new RegExp('>', 'g'), "");
        dd = dd.replace(new RegExp('`', 'g'), "");
        return dd;
      }
    }
  }
</script>

<style>

</style>
