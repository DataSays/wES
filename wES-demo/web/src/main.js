import Vue from 'vue'
import MuseUI from 'muse-ui'
import './assets/font-icons/style.css'
import 'muse-ui/dist/muse-ui.css'
import router from './router'
import FastClick from 'fastclick'
import App from './App'

Vue.use(MuseUI)
FastClick.attach(document.body)
/* eslint-disable no-new */
new Vue({
  router,
  el: '#app',
  template: '<App/>',
  components: { App }
})
