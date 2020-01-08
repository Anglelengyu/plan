<template>
  <div>
    <!--<Upload-->
    <!--v-show="false"-->
    <!--id="interest-editor"-->
    <!--ref="upload"-->
    <!--:headers="headers"-->
    <!--action="/article/upload/picture"-->
    <!--name="picture"-->
    <!--:show-upload-list="false"-->
    <!--:before-upload="handleBeforeUpload"-->
    <!--:on-success="handleSuccess"-->
    <!--:on-format-error="handleFormatError"-->
    <!--:format="['jpg','jpeg','png']"-->
    <!--:max-size="1024"-->
    <!--:on-exceeded-size="handleMaxSize">-->
    <!--<Button icon="ios-cloud-upload-outline">上传图片</Button>-->
    <!--</Upload>-->
    <quill-editor
      v-model="content"
      ref="myQuillEditor"
      :options="editorOption"
      @blur="onEditorBlur($event)"
      @focus="onEditorFocus($event)"
      @change="onEditorChange($event)">
    </quill-editor>
  </div>
</template>

<script>
  // 工具栏配置
  const toolbarOptions = [
    ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
    ['blockquote', 'code-block'],

    [{'header': 1}, {'header': 2}],               // custom button values
    [{'list': 'ordered'}, {'list': 'bullet'}],
    [{'script': 'sub'}, {'script': 'super'}],      // superscript/subscript
    [{'indent': '-1'}, {'indent': '+1'}],          // outdent/indent
    [{'direction': 'rtl'}],                         // text direction

    [{'size': ['small', false, 'large', 'huge']}],  // custom dropdown
    [{'header': [1, 2, 3, 4, 5, 6, false]}],

    [{'color': []}, {'background': []}],          // dropdown with defaults from theme
    [{'font': []}],
    [{'align': []}],
    ['link', 'image'],
    ['clean']                                         // remove formatting button
  ]
  export default {
    name: "editor",
    data() {
      return {
        editorOption: {
          modules: {
            toolbar: {
              container: toolbarOptions  // 工具栏
            }
          }
        }
      }
    },
    props: {
      defaultMsg: {
        type: String
      },
      config: {
        type: Object
      }
    },
    mounted() {
      const _this = this;
      this.editor = editor.getEditor('editor', this.config); // 初始化UE
      this.editor.addListener("ready", function () {
        _this.editor.setContent(_this.defaultMsg) // 确保UE加载完成后，放入内容。
      });
    },
    methods: {
      handleSuccess(res, file) {
        // 获取富文本组件实例
        let quill = this.$refs.myQuillEditor.quill;
        // 获取光标所在位置
        let length = quill.getSelection().index;
        // 插入图片  res.info为服务器返回的图片地址
        quill.insertEmbed(length, 'image', res.data);
        // 调整光标到最后
        //quill.setSelection(length + 1)
      },
      handleBeforeUpload() {
        this.$refs.upload.fileList.splice(0, this.$refs.upload.fileList.length);
        return true;
      },
      handleFormatError(file) {
        this.$message.warning({
          title: '图片格式不对',
          desc: '图片格式只能为jpg,jpeg,png'
        });
      },
      handleMaxSize(file) {
        this.$message.warning({
          title: '图片太大',
          desc: '上传图片最大为1M,请优化后在上传。可使用https://zhitu.isux.us/网站优化'
        });
      },
      onEditorBlur() {//失去焦点事件
        this.$emit('editor-change', this.content);
      },
      onEditorFocus() {//获得焦点事件
        this.$emit('editor-change', this.content);
      },
      onEditorChange() {//内容改变事件
        this.$emit('editor-change', this.content);
      }
    },
    destroyed() {
      this.editor.destroy()
    }
  }
</script>

<style>

</style>
