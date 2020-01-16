<template>
  <div>
    <div style="text-align: left;margin-left: 80px;margin-bottom: 20px">
      <el-button href="javascript: void(0);" class="el-icon-back" role="button" onclick="window.history.back()">返回
      </el-button>
    </div>
    <el-form ref="form"
             :model="form"
             label-width="80px">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form-item label="分类"
                        prop="tag"
                        label-position="left">
            <el-input v-model="form.tagName"
                      :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="发布时间">
            <el-input v-model="form.issueDate"
                      :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <!--</el-form>-->
      <!--<el-form ref="form" :model="form" label-width="auto">-->
      <el-form-item prop="title" label="标题">
        <el-input v-model="form.title"
                  :disabled="true"></el-input>
      </el-form-item>
      <div v-html="form.content"
           style="height: auto; text-align: left; margin-left: 80px; white-space: pre-wrap; border-left:2px dashed #969c97;border-right:2px dashed #969c97"></div>
      <div style=" text-align: left; margin-left: 80px;">
        <div class="commentBox" style="min-height:250px;overflow-y:auto;max-height:300px;">
          <h3>评论</h3>
          <p v-if="comment.length==0">暂无评论，我来发表第一篇评论！</p>
          <div v-else>
            <div class="comment" v-for="(item,index) in comment" v-bind:index="index"
                 @mouseenter="pEnter(item.id)"
                 @mouseleave="pLeave()">
              <b>{{item.responder}}<span>{{item.time}}</span>
                <span style="width: 5%;margin-left: 1%;"
                      v-if="user.id == item.userId && choiceReplyId == item.id"
                      title="删除" @click="deleteReply(item.id)">
             <el-link class="el-icon-delete" :underline="false"></el-link>
            </span></b>
              <p @click="changeCommenter(item.responder,index)">{{item.content}}</p>
              <div v-if="item.reply.length > 0">
                <div class="reply" v-for="reply in item.reply"
                     @mouseenter="pEnter(reply.id)"
                     @mouseleave="pLeave()">
                  <b>{{reply.responder}}&nbsp;&nbsp;回复&nbsp;&nbsp;{{reply.reviewers}}<span>{{reply.time}}</span>
                    <span style="width: 5%;margin-left: 1%;"
                          v-if="user.id == reply.userId && choiceReplyId == reply.id"
                          title="删除" @click="deleteReply(reply.id)">
             <el-link class="el-icon-delete" :underline="false"></el-link>
            </span></b>
                  <p @click="changeCommenter(reply.responder,index)">{{reply.content}}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="commentBox">
          <h3>发表评论</h3>
          <b v-if="type">你回复&nbsp;{{oldComment}}</b>
          <textarea name="" value="请填写评论内容" v-model="commentText"></textarea>
          <button class="btn" @click="addComment">发表</button>
          <button class="btn" @click="canelComment">取消</button>
        </div>
      </div>
      <!--<el-input type="html" v-model="form.content"></el-input>-->
    </el-form>
    <!--<div style=" height: 600px;-->
    <!--background-color: #fff;">-->
    <!--<quill-editor-->
    <!--style="height: 90%"-->
    <!--v-model="content"-->
    <!--ref="myQuillEditor"-->
    <!--:options="editorOption"-->
    <!--@blur="onEditorBlur($event)" @focus="onEditorFocus($event)"-->
    <!--@change="onEditorChange($event)">-->
    <!--</quill-editor>-->
    <!--</div>-->
  </div>
</template>

<script>
  export default {
    name: "Detail",
    data() {
      return {
        form: {},
        commentText: "",
        commenter: "session",   //评论人
        type: 0,                //0为评论作者1为评论别人的评论2为评论别人的别人
        oldComment: null,
        chosedIndex: -1,        //被选中的评论的index
        comment: [],
        choiceReplyId: null // 选中的评论
      }
    },
    mounted: function () {
      this.loadArticleDetail();
      this.loadArticleReply();
    },
    computed: {
      user() {
        return this.$store.state.user;
      }
    },
    methods: {
      loadArticleDetail: function () {
        let articleId = this.$route.params.articleId;
        console.log(articleId)
        this.getRequest("/article/detail/" + articleId).then(resp => {
          this.form = resp.data.data
        })
      },
      loadArticleReply: function () {
        let articleId = this.$route.params.articleId;
        this.getRequest("/reply/getReply?articleId=" + articleId).then(resp => {
          console.log(resp.data.data)
          this.comment = resp.data.data
        })
      },
      addCommentText: function (data) {
        let now = this.getTime();
        console.log(this.type)
        if (this.type == 0) {
          this.comment = {
            name: this.$store.state.user.name,
            time: now,
            content: data,
            reply: []
          }
          //服务器端变
        } else if (this.type == 1) {
          this.comment[this.chosedIndex].reply.push({
            responder: this.$store.state.user.name,
            reviewers: this.comment[this.chosedIndex] ? this.comment[this.chosedIndex].name : null,
            time: now,
            content: data
          });
          this.type = 0;
        }
        this.commentText = "";
        // 发送后端请求 存储评论
        const param = {
          articleId: this.$route.params.articleId,
          replyPid: this.comment[this.chosedIndex] ? this.comment[this.chosedIndex].id : null,
          userId: this.$store.state.user.id,
          responder: this.$store.state.user.name,
          time: now,
          content: data
        }
        console.log(param, 1)
        this.postRequest("/reply/create", param).then(resp => {
          if (resp.status == 200 && resp.data.code == 200) {
            this.loadArticleReply();
            this.$confirm('评论成功!', '提示', {
              type: 'info'
            })
          }
        });
      },
      changCommmer: function (name, index) {
        this.oldComment = name;
        this.chosedIndex = index;
        this.type = 1;
      },
      canelCommit: function () {
        this.type = 0;
      },
      changeCommenter: function (name, index) {
        this.changCommmer(name, index);
      },
      addComment: function () {
        // 判断是否登录
        if (!this.$store.state.user.username) {
          this.$confirm('请先登录再进行评论', '提示', {
            type: 'warning'
          })
        } else {
          this.addCommentText(this.commentText);
        }
      },
      canelComment: function () {
        this.commentText = "";
      },
      //格式化时间
      getTime: function () {
        var now = new Date();
        console.log(now)
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var day = now.getDate();
        var hours = now.getHours();
        var minutes = now.getMinutes();
        var seconds = now.getSeconds();
        month = month < 10 ? "0" + month : month;
        day = day < 10 ? "0" + day : day;
        hours = hours < 10 ? "0" + hours : hours;
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
        return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
      },
      pEnter: function (replyId) {
        console.log(replyId,1)
        console.log(this.user.id,2)
        this.choiceReplyId = replyId
      },
      pLeave: function () {
        this.choiceReplyId = null
      },
      // 删除
      deleteReply: function (id) {
        this.$confirm('此操作将永久删除该评论, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.getRequest("/reply/delete?id=" + id).then(resp => {
            console.log(resp.data)
            if (resp.status == 200 && resp.data.code == 200) {
              this.loadArticleReply();
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
    }
  }

</script>

<style>
  .comment {
    margin-bottom: 15px;
  }

  .comment + .comment, .reply + .reply {
    border-top: 1px solid #ccc;
  }

  .comment b {
    color: #01553D;
    font-size: 16px;
    display: block;
    margin: 5px 0;
  }

  .comment b span {
    color: #333;
    font-size: 14px;
    margin-left: 20px;
  }

  .comment p {
    font-size: 16px;
    color: #333;
  }

  .commentBox textarea {
    overflow: auto;
    width: 100%;
    height: 95px;
    outline: none;
    resize: none;
  }

  .commentBox button {
    float: right;
    margin-left: 20px;
    padding: 5px 30px;
    background: #01553D;
    border-radius: 5px;
    color: #fff;
    font-size: 16px;
  }

  .commentBox button:hover {
    color: #fff;
    background: #047857
  }

  .reply {
    margin: 15px 0 15px 50px;
  }

  .articleText {
    font-size: 16px;
    margin: 20px;
  }

  .articleText p {
    text-indent: 2em;
  }

  .commentBox {
    margin: 20px;
  }

  .commentBox h3 {
    color: #634322;
    background: #e9e5df;
    padding: 8px 15px;
    text-align: left;
    font-size: 16px;
  }

  .noComment {
    display: none;
  }
</style>
