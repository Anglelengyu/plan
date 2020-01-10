<template>
  <div>
    <el-form ref="form"
             :rules="rules"
             :model="form"
             label-width="80px">
      <el-row :gutter="20">
        <el-col span="6">
          <el-form-item label="分类" prop="tag" label-position="left">
            <el-select style="width: 100%" clearable v-model="form.tag" placeholder="请选择">
              <el-option
                v-for="item in tags"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="发布时间">
            <el-date-picker type="date" placeholder="选择日期" v-model="form.issueDate" style="width: 100%;">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <!--</el-form>-->
      <!--<el-form ref="form" :model="form" label-width="auto">-->
      <el-form-item prop="title" label="标题">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <div v-html="form.content"
           style="height: 300px; text-align: left;white-space: pre-wrap"></div>
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
    <el-button v-on:click="saveHtml()">修改</el-button>
    <el-button href="javascript: void(0);" role="button" onclick="window.history.back()">取消</el-button>
  </div>
</template>

<script>
  export default {
    name: "Detail",
    data() {
      return {
        tags: [{
          id: '1',
          name: '黄金糕'
        }, {
          id: '2',
          name: '双皮奶'
        }, {
          id: '3',
          name: '蚵仔煎'
        }],
        rules: {
          tag: [
            {required: true, message: '标题', trigger: 'blur'}
          ],
          title: [
            {required: true, message: '标题', trigger: 'blur'}
          ]
        },
        form: {}
      }
    },
    mounted:function() {
      this.loadArticleDetail()
    },
    methods: {
      loadArticleDetail:function () {
        let articleId = this.$route.params.id;
        this.getRequest("/article/detail/" + articleId).then(resp=>{
          this.form = resp.data.data
        })
      }
    }
  }

</script>

<style>

</style>
