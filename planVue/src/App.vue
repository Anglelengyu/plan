<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <bar/>
    <router-view/>
    <bottom/>
  </div>
</template>

<script>
  import Bar from "./components/Bar";
  import Bottom from "./components/Bottom";

  export default {
    components: {Bar,Bottom},
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
