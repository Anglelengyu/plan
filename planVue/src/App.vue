<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <router-view/>
    <bottom/>
  </div>
</template>

<script>
  import bottom from "@/components/bottom"
  import Bottom from "./components/Bottom";

  export default {
    components: {Bottom},
    name: 'App'
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #fa93ee;
    margin-top: 60px;
  }
</style>
