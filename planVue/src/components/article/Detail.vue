<template>
  <div>
    <h1>详情</h1>
    <p>id是{{this.$route.params.articleId}}</p>
    <editor>***</editor>
  </div>
</template>

<script>
  import Editor from './Editor'
  export default {
    name: "Detail",
    components:{Editor},
    data() {
      return {
        articleId: -1
      }
    }
  }

</script>

<style>

</style>
