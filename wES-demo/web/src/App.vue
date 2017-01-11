<template>
	<div class="wrapper">
		<div class="main-header">
			<div class="brandDiv" v-show="open">
				<a href="#/index" class="brand"><b>wES-demo</b></a>
			</div>
			<div class="appBar">
				<div class="left">
					<el-button icon="menu" type="primary" class="navBtn" @click="toggleAppDrawer"></el-button>
				</div>
				<div class="appbar-title">
					<span>{{title}}</span>
				</div>
				<div class="right">
					<el-button icon="more" type="primary"></el-button>
				</div>
			</div>
		</div>
		<aside class="main-sidebar" v-show="open">
			<div class="sidebar">
				<el-menu class="el-menu-vertical-demo" @select="sidebarSelect" theme="dark" :default-active="currentMenu">
					<template v-for="menu in allMenus">
						<el-menu-item v-if="menu.type === 1" :index="menu.path"><i v-if="menu.icon != ''" :class="'el-icon-'+menu.icon"></i>{{menu.name}}</el-menu-item>
						<el-submenu v-if="menu.type === 10" :index="menu.name">
							<template slot="title"><i v-if="menu.icon != ''" :class="'el-icon-'+menu.icon"></i>{{menu.name}}</template>
							<template v-for="submenu1 in menu.items">
								<el-menu-item v-if="submenu1.type === 1" :index="submenu1.path"><i v-if="submenu1.icon != ''" :class="'el-icon-'+submenu1.icon"></i>{{submenu1.name}}</el-menu-item>
								<el-menu-item-group v-if="submenu1.type === 20" :title="submenu1.name">
									<template v-for="submenu2 in submenu1.items">
										<el-menu-item v-if="submenu2.type === 1" :index="submenu2.path"><i v-if="submenu2.icon != ''" :class="'el-icon-'+submenu2.icon"></i>{{submenu2.name}}</el-menu-item>
								</el-menu-item-group>
								</template>
						</el-submenu>
						</template>
				</el-menu>
			</div>
		</aside>
		<div class="content-wrapper" v-bind:class="{sidebarOpen: open, sidebarClose: !open}">
			<div class="container-fluid">
				<router-view></router-view>
			</div>
		</div>
	</div>
	</template>
	<script>
	import common from './assets/common.js';

	export default {
		data() {
			const desktop = isDesktop();
			return {
				open: desktop,
				title: '',
				allMenus: []
			};
		},
		computed: {
			currentMenu: function () {
				try {
					return this.$route.path;
				} catch (e) {
					return '';
				}
			}
		},
		created: function () {
			this.fetchAllMenus();
		},
		mounted() {
			this.routes = this.$router.options.routes;
			this.title = this.$route.name;
			window.addEventListener('resize', this.resize);
			this.$router.beforeEach((to, from, next) => {
				this.title = to.name;
				next();
			});
		},
		methods: {
			resize() {
				const fullWidth = window.innerWidth;
				if (fullWidth < 768) {
					this.open = false;
				} else {
					this.open = true;
				}
			},
			toggleAppDrawer() {
				this.open = !this.open;
			},
			sidebarSelect(key, keyPath) {
				// console.log(key);
				this.$router.push(key);
			},
			fetchAllMenus() {
				var self = this;
				common.getAction('/static/data/allMenus.json', (response) => {
					self.allMenus = response.data.menus;
				}, (error) => {
					common.errorMsg(self, error);
				});
			}
		},
		destroyed() {
			window.removeEventListener('resize', this.resize);
		},
		components: {}
	};

	function isDesktop() {
		return window.innerWidth > 993;
	}

	</script>
	<style lang="less">
	@import 'assets/app.less';

	</style>
