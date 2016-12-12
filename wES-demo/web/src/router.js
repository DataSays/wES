import Vue from 'vue'
import Router from 'vue-router'
import Index from './views/Index'
import Hello from './views/Hello'
import Hello2 from './views/Hello2'
import EsDataExplorer from './views/EsDataExplorer'
Vue.use(Router)

const router = new Router({
  mode: 'hash',
  routes: [
    { path: '/index', component: Index },
    { path: '/hello', component: Hello },
    { path: '/hello2', component: Hello2 },
    { path: '/EsDataExplorer', component: EsDataExplorer },
    { path: '*', redirect: '/index' }
  ]
})

router.beforeEach((to, from, next) => {
  window.scrollTo(0, 0) // scroll to top
  next()
})
export default router
