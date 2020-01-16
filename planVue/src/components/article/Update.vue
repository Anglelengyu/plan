<template>
  <div>
    <el-form ref="form"
             :rules="rules"
             :model="form"
             label-width="80px">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form-item label="分类" prop="tagId" label-position="left">
            <el-select style="width: 100%" @change="onSelectedDrug($event)" clearable v-model="form.tagId" placeholder="请选择">
              <el-option
                v-for="item in tags"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
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
    </el-form>
    <div style=" height: 600px;
  background-color: #fff;">
      <quill-editor
        style="height: 90%"
        v-model="form.content"
        ref="myQuillEditor"
        :options="editorOption"
        @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
        @change="onEditorChange($event)">
      </quill-editor>
    </div>
    <el-button v-on:click="saveHtml()" class="el-icon-edit">修改</el-button>
    <el-button href="javascript: void(0);" role="button" class="el-icon-back" onclick="window.history.back()">取消
    </el-button>
  </div>
</template>

<script>
  const toolbarOptions = [
    ["bold", "italic", "underline", "strike"], // toggled buttons
    [{header: 1}, {header: 2}], // custom button values
    [{list: "ordered"}, {list: "bullet"}],
    [{indent: "-1"}, {indent: "+1"}], // outdent/indent
    [{direction: "rtl"}], // text direction
    ["code-block"],
    [{size: ["small", false, "large", "huge"]}], // custom dropdown
    [{header: [1, 2, 3, 4, 5, 6, false]}],
    [{color: []}, {background: []}], // dropdown with defaults from theme
    [{font: []}],
    [{align: []}],
    ["image"],
    ["link"],
    ["blockquote"],
    ["clean"]
  ];
  const titleConfig = [
    {Choice: ".ql-bold", title: "加粗"},
    {Choice: ".ql-italic", title: "斜体"},
    {Choice: ".ql-underline", title: "下划线"},
    {Choice: ".ql-header", title: "段落格式"},
    {Choice: ".ql-strike", title: "删除线"},
    {Choice: ".ql-blockquote", title: "块引用"},
    {Choice: ".ql-code", title: "插入代码"},
    {Choice: ".ql-code-block", title: "插入代码段"},
    {Choice: ".ql-font", title: "字体"},
    {Choice: ".ql-size", title: "字体大小"},
    {Choice: '.ql-list[value="ordered"]', title: "编号列表"},
    {Choice: '.ql-list[value="bullet"]', title: "项目列表"},
    {Choice: ".ql-direction", title: "文本方向"},
    {Choice: '.ql-header[value="1"]', title: "h1"},
    {Choice: '.ql-header[value="2"]', title: "h2"},
    {Choice: ".ql-align", title: "对齐方式"},
    {Choice: ".ql-color", title: "字体颜色"},
    {Choice: ".ql-background", title: "背景颜色"},
    {Choice: ".ql-image", title: "图像"},
    {Choice: ".ql-video", title: "视频"},
    {Choice: ".ql-link", title: "添加链接"},
    {Choice: ".ql-formula", title: "插入公式"},
    {Choice: ".ql-clean", title: "清除字体格式"},
    {Choice: '.ql-script[value="sub"]', title: "下标"},
    {Choice: '.ql-script[value="super"]', title: "上标"},
    {Choice: '.ql-indent[value="-1"]', title: "向左缩进"},
    {Choice: '.ql-indent[value="+1"]', title: "向右缩进"},
    {Choice: ".ql-header .ql-picker-label", title: "标题大小"},
    {Choice: '.ql-header .ql-picker-item[data-value="1"]', title: "标题一"},
    {Choice: '.ql-header .ql-picker-item[data-value="2"]', title: "标题二"},
    {Choice: '.ql-header .ql-picker-item[data-value="3"]', title: "标题三"},
    {Choice: '.ql-header .ql-picker-item[data-value="4"]', title: "标题四"},
    {Choice: '.ql-header .ql-picker-item[data-value="5"]', title: "标题五"},
    {Choice: '.ql-header .ql-picker-item[data-value="6"]', title: "标题六"},
    {Choice: ".ql-header .ql-picker-item:last-child", title: "标准"},
    {Choice: '.ql-size .ql-picker-item[data-value="small"]', title: "小号"},
    {Choice: '.ql-size .ql-picker-item[data-value="large"]', title: "大号"},
    {Choice: '.ql-size .ql-picker-item[data-value="huge"]', title: "超大号"},
    {Choice: ".ql-size .ql-picker-item:nth-child(2)", title: "标准"},
    {Choice: ".ql-align .ql-picker-item:first-child", title: "居左对齐"},
    {
      Choice: '.ql-align .ql-picker-item[data-value="center"]',
      title: "居中对齐"
    },
    {
      Choice: '.ql-align .ql-picker-item[data-value="right"]',
      title: "居右对齐"
    },
    {
      Choice: '.ql-align .ql-picker-item[data-value="justify"]',
      title: "两端对齐"
    }
  ];
  export default {
    name: "Update",
    data() {
      return {
        form: {},
        rules: {
          tagId: [
            {required: true, message: '请选择文章分类', trigger: 'change'}
          ],
          title: [
            {required: true, message: '请填写标题', trigger: 'blur'}
          ]
        },
        tags: {},
        editorOption: {
          modules: {
            toolbar: toolbarOptions
          }
        }
      }
    },
    mounted: function () {
      this.loadArticleDetail();
      this.loadPulldownTags();
      //初始化编辑器文字提示
      autotip: {
        document.getElementsByClassName("ql-editor")[0].dataset.placeholder = "";
        for (let item of titleConfig) {
          let tip = document.querySelector(".quill-editor " + item.Choice);
          if (!tip) continue;
          tip.setAttribute("title", item.title);
        }
      }
    },
    methods: {
      // 加载tag
      loadPulldownTags: function () {
        this.getRequest("/tag/pulldownTags").then(resp => {
          // if (resp.data.)
          if (resp.data.code == 200) {
            // 填充下拉数据
            console.log(resp.data)
            this.tags = resp.data.data
          }
        });
      },
      loadArticleDetail: function () {
        let articleId = this.$route.params.id;
        this.getRequest("/article/detail/" + articleId).then(resp => {
          this.form = resp.data.data
        })
      },
      onEditorReady(editor) { // 准备编辑器
      },
      onEditorBlur() {
      }, // 失去焦点事件
      onEditorFocus() {
      }, // 获得焦点事件
      onEditorChange() {
      }, // 内容改变事件
      saveHtml: function (event) {
        // alert(this.content);
        // 获取下拉的数据
        this.onSelectedDrug(this.form.tagId);
        const param = {
          id: this.form.id,
          tagId: this.form.tagId,
          tagName: this.form.tagName,
          issueDate: this.form.issueDate,
          title: this.form.title,
          content: this.form.content,
          issueStatus: 1
        }
        console.log(param)
        // TODO 保存文章
        this.postRequest("/article/update", param).then(resp => {
          // if (resp.data.)
          if (resp.data.code == 200) {
            this.$router.push({ //路由跳转到文章列表
              path: '/article'
            });
          }
        });
      },
      saveHtmlNote: function (event) {
        // alert(this.content);
        const param = {
          id: this.form.id,
          tagId: this.form.tagName.id,
          tagName: this.form.tagName.name,
          issueDate: this.form.issueDate,
          title: this.form.title,
          content: this.content,
          issueStatus: 0
        }
        console.log(param)
      },
      // 下拉所有数据
      onSelectedDrug(e){
        console.log(e)
        let obj = {};
        obj = this.tags.find((item)=>{//这里的userList就是上面遍历的数据源
          return item.id === e;//筛选出匹配数据
        });
        // this.form.tagName = obj
        console.log(this.form.tagName)
        // console.log(obj.id);//获取的 name
        // console.log(e);//获取的 id

      }
    }
  }
</script>

<style scoped>

</style>
