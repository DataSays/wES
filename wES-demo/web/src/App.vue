<template>
	<div class="wrapper">
		<div class="appBar" v-bind:class="{'sidebar-opened':open}" v-bind:style="{width:contentWidth}">
			<div class="left">
				<el-button icon="menu" type="primary" class="navBtn" @click="toggleAppDrawer"></el-button>
			</div>
			<div class="appbar-title">
				<span>{{title}}</span>
			</div>
			<div class="right">
			</div>
		</div>
		<div class="app-drawer" v-show="open">
			<a href="#/index" class="brand"><b>wES-demo</b></a>
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
		<div class="content-wrapper" v-bind:class="{'sidebar-opened':open}" v-bind:style="{width:contentWidth}">
			<router-view></router-view>
		</div>
	</div>
	</template>
	<script>
	import common from './components/common.js'
	export default {
		data() {
			const desktop = isDesktop()
			return {
				open: desktop,
				title: '',
				allMenus: []
			}
		},
		computed: {
			contentWidth: function () {
				if (this.open) {
					var width = window.innerWidth - 256
					if (width > 100) {
						return width + 'px'
					}
				}
				return '100%'
			},
			currentMenu: function () {
				let path = window.location.hash
				if (path && path.length > 1) {
					path = path.substring(1)
					return path
				}
				return ''
			}
		},
		created: function () {
			this.fetchAllMenus()
		},
		mounted() {
			this.routes = this.$router.options.routes
			this.setTitle()
			this.handleResize = () => {
				console.log('resize')
			}
			window.addEventListener('resize', this.handleResize)
			window.addEventListener('hashchange', () => {
				this.setTitle()
			})
		},
		methods: {
			_c() {},
			toggleAppDrawer() {
				this.open = !this.open
			},
			sidebarSelect(key, keyPath) {
				// console.log(key)
				this.$router.push(key)
				this.setTitle()
			},
			setTitle() {
				let path = window.location.hash
				if (path && path.length > 1) path = path.substring(1)
				for (let i = 0; i < this.routes.length; i++) {
					var route = this.routes[i]
					if (route.path === path) {
						this.title = route.name
						return
					}
				}
			},
			fetchAllMenus() {
				var self = this
				common.DEBUG = true
				common.getAction('/static/data/allMenus.json', (response) => {
					self.allMenus = response.data.menus
				}, (error) => {
					common.errorMsg(self, error)
				})
			}
		},
		destroyed() {
			window.removeEventListener('resize', this.handleResize)
		},
		components: {}
	}

	function isDesktop() {
		return window.innerWidth > 993
	}

	</script>
	<style lang="css">
	.appBar {
		position: fixed;
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
		position: fixed;
		top: 0;
		bottom: 0;
		left: 0;
		z-index: 200;
		overflow: auto;
		background-color: #1F2D3D;
		box-sizing: border-box;
	}

	.app-drawer .brand {
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
		float: left;
		color: #fff;
		text-decoration: none;
	}

	.app-drawer .navMenu {
		top: 50px;
		position: fixed;
		width: 256px;
		height: 100%;
	}

	.wrapper {
		min-height: 100%;
		position: relative;
		overflow: hidden;
		background-color: #ecf0f5;
	}

	.content-wrapper {
		top: 50px;
		min-height: 890px;
		position: fixed;
		width: 85%;
		padding: 10px;
	}

	.divider {
		margin: 0;
		height: 1px;
		border: none;
		background-color: rgba(0, 0, 0, .12);
		width: 100%;
	}

	</style>
