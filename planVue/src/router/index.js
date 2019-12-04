import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
// import Home from '@/components/Home'

Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // }
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/',
      name: 'home',
      component: resolve => require(['@/components/home'], resolve)
    },
    {
      path: '/introduction',
      name: 'introduction',
      component: resolve => require(['@/components/introduction'], resolve)
    },
    {
      path: '/articleList',
      name: 'article',
      component: resolve => require(['@/components/article/index'], resolve)
      // children:[
      //   {
      //     path: "",
      //     name: "qq-home",
      //     component: resolve => require(["@/views/template/home.vue"], resolve)
      //   }
      // ]
    }
  ]
  // mode:'history'   //去除#号
})
