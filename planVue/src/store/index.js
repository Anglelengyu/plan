import Vue from 'vue'
import Vuex from 'vuex'
// import '../lib/sockjs'
// import '../lib/stomp'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      id: window.localStorage.getItem('id') == null ? '未登录' : window.localStorage.getItem('id'),
      name: window.localStorage.getItem('name') == null ? '未登录' : window.localStorage.getItem('name'),
      icon: window.localStorage.getItem('icon') == null ? '' : window.localStorage.getItem('icon'),
      username: window.localStorage.getItem('username') == null ? '' : window.localStorage.getItem('username'),
      roles: window.localStorage.getItem('roles') == null ? '' : window.localStorage.getItem('roles')
    },
    routes: [],
    msgList: [],
    isDotMap: new Map(),
    currentFriend: {},
    stomp: null,
    nfDot: false
  },
  mutations: {
    //   initMenu(state, menus){
    //     state.routes = menus;
    //   },
    login(state, data) {
      // state.user = user;
      // console.log(state);
      // console.log(data);
      state.user = data
      window.localStorage.setItem('id', data.id);
      window.localStorage.setItem('name', data.name);

      //存储用户名
      // this.state.user.username = data.username;
      window.localStorage.setItem('username', data.username)

      //存储头像
      // this.state.user.icon = data.icon;
      window.localStorage.setItem('icon', data.icon)

      // this.state.user.roles = data.roles;
      window.localStorage.setItem('roles', data.roles)
    },
    logout(state) {
      window.localStorage.removeItem('name');
      this.state.user.name = '';
      window.localStorage.removeItem('username');
      this.state.user.username = '';
      window.localStorage.removeItem('icon');
      this.state.user.icon = '';
      window.localStorage.removeItem('roles');
      this.state.user.roles = '';
    }
  }
  //   toggleNFDot(state, newValue){
  //     state.nfDot = newValue;
  //   },
  //   updateMsgList(state, newMsgList){
  //     state.msgList = newMsgList;
  //   },
  //   updateCurrentFriend(state, newFriend){
  //     state.currentFriend = newFriend;
  //   },
  //   addValue2DotMap(state, key){
  //     state.isDotMap.set(key, "您有未读消息")
  //   },
  //   removeValueDotMap(state, key){
  //     state.isDotMap.delete(key);
  //   }
  // },
  // actions: {
  //   connect(context){
  //     context.state.stomp = Stomp.over(new SockJS("/ws/endpointChat"));
  //     context.state.stomp.connect({}, frame=> {
  //       context.state.stomp.subscribe("/user/queue/chat", message=> {
  //         var msg = JSON.parse(message.body);
  //         var oldMsg = window.localStorage.getItem(context.state.user.username + "#" + msg.from);
  //         if (oldMsg == null) {
  //           oldMsg = [];
  //           oldMsg.push(msg);
  //           window.localStorage.setItem(context.state.user.username + "#" + msg.from, JSON.stringify(oldMsg))
  //         } else {
  //           var oldMsgJson = JSON.parse(oldMsg);
  //           oldMsgJson.push(msg);
  //           window.localStorage.setItem(context.state.user.username + "#" + msg.from, JSON.stringify(oldMsgJson))
  //         }
  //         if (msg.from != context.state.currentFriend.username) {
  //           context.commit("addValue2DotMap", "isDot#" + context.state.user.username + "#" + msg.from);
  //         }
  //         //更新msgList
  //         var oldMsg2 = window.localStorage.getItem(context.state.user.username + "#" + context.state.currentFriend.username);
  //         if (oldMsg2 == null) {
  //           context.commit('updateMsgList', []);
  //         } else {
  //           context.commit('updateMsgList', JSON.parse(oldMsg2));
  //         }
  //       });
  //       context.state.stomp.subscribe("/topic/nf", message=> {
  //         context.commit('toggleNFDot', true);
  //       });
  //     }, failedMsg=> {
  //
  //     });
  //   }
  // }
});
