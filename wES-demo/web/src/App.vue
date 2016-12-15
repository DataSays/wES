<template>
	<div class="wrapper">
		<div class="toolBar">
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
		<div class="content-wrapper">
			<div class="app-drawer" v-show="open">
				<div class="navMenu">
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
			</div>
			<div class="content-body">
				<router-view></router-view>
			</div>
		</div>
	</div>
	</template>
	<script>
	import common from './components/common.js';

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
			this.$router.beforeEach((to, from, next) => {
				this.title = to.name;
				next();
			});
		},
		methods: {
			_c() {},
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
		destroyed() {},
		components: {}
	};

	function isDesktop() {
		return window.innerWidth > 993;
	}

	</script>
	<style lang="css">
	.appBar {
		width: 100%;
		height: 50px;
		line-height: 50px;
		background-color: #20A0FF;
	}

	.appBar .left,
	.appBar .appbar-title {
		float: left;
		color: #fff;
	}

	.appBar .right {
		float: right;
	}

	.appBar .navBtn {
		float: left;
		height: 50px;
		background-color: #20A0FF;
		border-color: #20A0FF;
		border-radius: 0px;
	}

	.appBar .navBtn:hover {
		background-color: #1D8CE0;
		border-color: #1D8CE0;
	}

	.sidebar-opened {
		left: 256px;
	}

	.app-drawer {
		width: 256px;
		z-index: 200;
		overflow: auto;
		background-color: #1F2D3D;
		box-sizing: border-box;
		height: 100%;
		min-height: 980px;
		float: left;
		_margin-right: -3px;
	}

	.brand {
		background-color: #1D8CE0;
		text-align: center;
		height: 50px;
		font-size: 20px;
		line-height: 50px;
		font-weight: 300;
		overflow: hidden;
		width: 256px;
		box-sizing: border-box;
		display: block;
		color: #fff;
		text-decoration: none;
	}

	.navMenu {
		width: 256px;
		height: 100%;
	}

	.wrapper {
		width: 100%;
		overflow: hidden;
	}

	.content-wrapper {
		width: 100%;
	}

	.content-body {
		padding: 10px;
		float: left;
	}

	.toolBar {
		width: 100%;
		height: 50px;
	}

	.brandDiv {
		float: left;
		width: 256px;
		_margin-right: -3px;
	}

	</style>
