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
				<el-menu default-active="2" class="el-menu-vertical-demo" @select="sidebarSelect" theme="dark">
					<el-submenu index="test">
						<template slot="title"><i class="el-icon-message"></i>Test</template>
						<el-menu-item index="/home">Home</el-menu-item>
						<el-menu-item-group title="Hello">
							<el-menu-item index="/hello"><i class="el-icon-message"></i>hello1</el-menu-item>
							<el-menu-item index="/hello2"><i class="el-icon-message"></i>hello2</el-menu-item>
						</el-menu-item-group>
					</el-submenu>
					<el-menu-item index="/esDataExplorer"><i class="el-icon-menu"></i>ESDataExplorer</el-menu-item>
					<el-menu-item index="2"><i class="el-icon-menu"></i>Navigator Two</el-menu-item>
					<el-menu-item index="3"><i class="el-icon-setting"></i>Navigator Three</el-menu-item>
				</el-menu>
			</div>
		</div>
		<div class="content-wrapper" v-bind:class="{'sidebar-opened':open}" v-bind:style="{width:contentWidth}">
			<router-view></router-view>
		</div>
	</div>
</template>
<script>
export default {
  data () {
    const desktop = isDesktop()
    return {
      open: desktop,
      title: ''
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
    }
	},
  mounted () {
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
    _c () {
    },
		toggleAppDrawer () {
			this.open = !this.open
		},
		sidebarSelect (key, keyPath) {
			// console.log(key)
      this.$router.push(key)
      this.setTitle()
		},
    setTitle () {
      let path = window.location.hash
      if (path && path.length > 1) path = path.substring(1)
      for (let i = 0; i < this.routes.length; i++) {
        var route = this.routes[i]
        if (route.path === path) {
          this.title = route.name
          return
        }
      }
    }
  },
  destroyed () {
    window.removeEventListener('resize', this.handleResize)
  },
  components: {
  }
}

function isDesktop () {
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
	}
	
	.divider {
		margin: 0;
		height: 1px;
		border: none;
		background-color: rgba(0, 0, 0, .12);
		width: 100%;
	}

</style>
