import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
// import Home from '@/components/Home'

Vue.use(Router)
/**
 * 重写路由的push方法
 */
// const routerPush = Router.prototype.push
// Router.prototype.push = function push(location) {
//   return routerPush.call(this, location).catch(error=> error)
// }

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
      path: '/manager',
      name: 'Manager',
      component: resolve => require(['@/components/Manager'], resolve),
      children: [
        {
          path: "/user",
          name: "user",
          component: resolve => require(["@/components/user/User"], resolve),
          children: [
            {
              path: "/",
              name: "userList",
              component: resolve => require(["@/components/user/AllUserList"], resolve)
            },
            {
              path: "/user/detail/:id",
              name: "detail",
              component: resolve => require(["@/components/user/Detail"], resolve)
            }
          ]
        },
        {
          path: "/loginInfo",
          name: "loginInfo",
          component: resolve => require(["@/components/user/LoginInfo"], resolve),
          children: [
            {
              path: "/",
              name: "loginList",
              component: resolve => require(["@/components/user/LoginInfoList"], resolve)
            }
          ]
        }
      ]
    },
    {
      path: '/',
      name: 'home',
      component: resolve => require(['@/components/Home'], resolve)
    },
    {
      path: '/int0roduction',
      name: 'introduction',
      component: resolve => require(['@/components/introduction'], resolve)
    },
    {
      path: '/articleList',
      name: 'articleList',
      component: resolve => require(['@/components/article/Index'], resolve)
    },
    {
      path: "/article/:articleId",
      name: "article",
      component: resolve => require(["@/components/article/Detail"], resolve)
    },
    {
      path: "/editor",
      name: "editor",
      component: resolve => require(["@/components/article/Editor"], resolve)
    }
    // {
    //   path: "/user",
    //   name: "user",
    //   children:[
    //     // {
    //     //   path: "/userList",
    //     //   name: "userList",
    //     //   component: resolve => require(["@/components/user/AllUserList"], resolve)
    //     // }
    //   ]
    // },
    // {
    //   path: "/userList1",
    //   name: "userList1",
    //   component: resolve => require(["@/components/user/AllUserList"], resolve)
    // }
  ]
  // mode:'history'   //去除#号
})
