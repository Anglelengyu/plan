// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import store from './store/index'
import 'element-ui/lib/theme-chalk/index.css'
import {postRequest} from './utils/api'
import {getRequest} from './utils/api'
import VueQuillEditor from 'vue-quill-editor'
// import 'quill/dist/quill.core.css'
// import 'quill/dist/quill.snow.css'
// import 'quill/dist/quill.bubble.css'

Vue.prototype.postRequest = postRequest;
Vue.prototype.getRequest = getRequest;
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(VueQuillEditor);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
