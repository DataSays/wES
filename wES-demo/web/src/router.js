import Vue from 'vue';
import Router from 'vue-router';
import Index from './views/Index.vue';
import Hello from './views/Hello.vue';
import Hello2 from './views/Hello2.vue';
import EsDataExplorer from './views/EsDataExplorer.vue';
import EsDataEdit from './views/EsDataEdit.vue';
Vue.use(Router);

const router = new Router({
	mode: 'hash',
	routes: [
		{ path: '/index', name: '首页', component: Index },
		{ path: '/hello', name: 'Hello', component: Hello },
		{ path: '/hello2', name: 'Hello2', component: Hello2 },
		{ path: '/esDataExplorer', name: 'ES数据浏览', component: EsDataExplorer },
		{ path: '/esDataEdit/:index/:type/:id', name: 'ES数据编辑', component: EsDataEdit },
		{ path: '*', redirect: '/index' }
	]
});

router.beforeEach((to, from, next) => {
	window.scrollTo(0, 0);
	next();
});
export default router;
