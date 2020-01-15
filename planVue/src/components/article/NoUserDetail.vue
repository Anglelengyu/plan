<template>
  <div>
    <div style="text-align: left;margin-left: 80px;margin-bottom: 20px">
      <el-button href="javascript: void(0);" class="el-icon-back" role="button" onclick="window.history.back()">返回
      </el-button>
    </div>
    <el-form ref="form"
             :rules="rules"
             :model="form"
             label-width="80px">
      <el-row :gutter="20">
        <el-col span="6">
          <el-form-item label="分类"
                        prop="tag"
                        label-position="left">
            <el-input v-model="form.tagName"
                      :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col span="6">
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
           style="height: auto; text-align: left;white-space: pre-wrap; border-left:2px dashed #969c97;border-right:2px dashed #969c97"></div>
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
        form: {}
      }
    },
    mounted: function () {
      this.loadArticleDetail()
    },
    methods: {
      loadArticleDetail: function () {
        let articleId = this.$route.params.articleId;
        console.log(articleId)
        this.getRequest("/article/detail/" + articleId).then(resp => {
          this.form = resp.data.data
        })
      }
    }
  }

</script>

<style>

</style>
