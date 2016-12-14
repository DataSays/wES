import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import './assets/common.css'
import lang from 'element-ui/lib/locale/lang/zh-CN'
import locale from 'element-ui/lib/locale'

import router from './router'
import App from './App'

Vue.use(ElementUI)
locale.use(lang)
	/* eslint-disable no-new */
new Vue({
	router,
	el: '#app',
	template: '<App/>',
	components: {
		App
	}
})
