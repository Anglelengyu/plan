<template>
  <div>
    <h1>详情</h1>
    <p>id是{{this.$route.params.articleId}}</p>
  </div>
</template>

<script>
  export default {
    name: "Detail",
    data() {
      return {
        articleId: -1
      }
    }
  }

</script>

<style>

</style>
