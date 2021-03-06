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
        },
        {
          path: "/article",
          name: "article",
          component: resolve => require(["@/components/article/Article"], resolve),
          children: [
            {
              path: "/",
              name: "articleList",
              component: resolve => require(["@/components/article/List"], resolve)
            },
            {
              path: "/article/create",
              name: "articleCreate",
              component: resolve => require(["@/components/article/Create"], resolve)
            },
            {
              path: "/article/detail/:id",
              name: "articleDetail",
              component: resolve => require(["@/components/article/Detail"], resolve)
            },
            {
              path: "/article/update/:id",
              name: "articleUpdate",
              component: resolve => require(["@/components/article/Update"], resolve)
            }
          ]
        },
        {
          path: "/tag",
          name: "tag",
          component: resolve => require(["@/components/tag/Tag"], resolve),
          children: [
            {
              path: "/",
              name: "tagList",
              component: resolve => require(["@/components/tag/List"], resolve)
            },
            // {
            //   path: "/tag/create",
            //   name: "tagCreate",
            //   component: resolve => require(["@/components/tag/Create"], resolve)
            // },
            // {
            //   path: "/tag/detail/:id",
            //   name: "tagDetail",
            //   component: resolve => require(["@/components/tag/Detail"], resolve)
            // }
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
      path: '/introduction',
      name: 'introduction',
      component: resolve => require(['@/components/Introduction'], resolve)
    },
    {
      path: '/articleList',
      name: 'articleList',
      component: resolve => require(['@/components/home/Index'], resolve)
    },
    {
      path: '/userArticleList',
      name: 'userArticleList',
      component: resolve => require(['@/components/home/UserArtList'], resolve)
    },
    {
      path: "/article/:articleId",
      name: "article",
      component: resolve => require(["@/components/home/NoUserDetail"], resolve)
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
