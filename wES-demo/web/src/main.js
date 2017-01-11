import Vue from 'vue';
import Vuex from 'vuex';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-default/index.css';
import './assets/bootstrap/bootstrap-grid.css';
import './assets/common.css';
import lang from 'element-ui/lib/locale/lang/zh-CN';
import locale from 'element-ui/lib/locale';
import FastClick from 'fastclick';

import router from './router.js';
import App from './App.vue';

Vue.use(Vuex);
Vue.use(ElementUI);
locale.use(lang);

FastClick.attach(document.body);
/* eslint-disable no-new */

const store = new Vuex.Store({
	state: {},
	mutations: {
		upObj(state, name, value) {
			state[name] = value;
		}
	}
});

new Vue({
	router,
	store,
	el: '#app',
	template: '<App/>',
	components: {
		App
	}
});
