<template>
	<div class="wrapper">
		<header class="main-header">
			<a href="/index" class="brand"><b>wES-demo</b></a>
			<nav class="navbar" role="navigation">
				<el-button icon="menu" type="primary" class="navBtn"></el-button>
				<el-menu mode="horizontal" @select="handleSelect" class="mainMenuBar" theme="dark">
					<el-menu-item index="/index">Index</el-menu-item>
					<el-submenu>
						<template slot="title">Hello</template>
						<el-menu-item index="/hello">hello</el-menu-item>
						<el-menu-item index="/hello2" divided>hello2</el-menu-item>
					</el-submenu>
					<el-menu-item index="/esDataExplorer">ESDataExplorer</el-menu-item>
				</el-menu>
			</nav>
		</header>
		<aside class="main-sidebar">
			<el-menu default-active="2" class="el-menu-vertical-demo" @open="sidebarOpen" @close="sidebarClose" theme="dark">
				<el-submenu index="1">
					<template slot="title"><i class="el-icon-message"></i>Navigator One</template>
					<el-menu-item-group title="Group One">
						<el-menu-item index="1-1"><i class="el-icon-message"></i>item one</el-menu-item>
						<el-menu-item index="1-2">item one</el-menu-item>
					</el-menu-item-group>
					<el-menu-item-group title="Group Two">
						<el-menu-item index="1-3">item three</el-menu-item>
					</el-menu-item-group>
				</el-submenu>
				<el-menu-item index="2"><i class="el-icon-menu"></i>Navigator Two</el-menu-item>
				<el-menu-item index="3"><i class="el-icon-setting"></i>Navigator Three</el-menu-item>
			</el-menu>
		</aside>
		<el-row class="content-wrapper sidebar-opened">
			<router-view></router-view>
		</el-row>
		<footer class="main-footer sidebar-opened">
			<el-row>
				<el-col :span="20">
					<b>Copyright © 2016 <a href="https://github.com/DataSays">DataSays</a>.</b> All rights reserved.
				</el-col>
				<el-col :span="4" class="mainVersion" :xs="{span:0, offset:0}"><b>Version</b> 1.0</el-col>
			</el-row>
		</footer>
	</div>
</template>
<script>
export default {
  data () {
    const desktop = isDesktop()
    return {
      open: desktop,
      docked: desktop,
      desktop: desktop,
      title: ''
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
    handleSelect (key, keyPath) {
      this.$router.push(key)
      this.setTitle()
    },
		sidebarOpen (key, keyPath) {
			console.log(key)
		},
		sidebarClose (key, keyPath) {
			console.log(key)
		},
    setTitle () {
      let path = window.location.hash
      if (path && path.length > 1) path = path.substring(1)
      for (let i = 0; i < this.routes.length; i++) {
        var route = this.routes[i]
        if (route.path === path) {
          this.title = path.substring(1) || ''
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
	.main-header {
		position: relative;
		/* max-height: 100px; */
		z-index: 1030;
		background-color: #324057;
		color: #ffffff;
	}
	
	.main-header a {
		color: #ffffff;
		text-decoration: none;
	}
	
	.main-header .brand {
		background-color: #1F2D3D;
		text-align: center;
		height: 60px;
		font-size: 20px;
		line-height: 60px;
		font-weight: 300;
		overflow: hidden;
		width: 230px;
		box-sizing: border-box;
		display: block;
		float: left;
	}
	
	.main-header>.navbar {
		margin-bottom: 0;
		border: none;
		min-height: 60px;
		border-radius: 0;
		position: relative;
	}
	
	.main-header .navBtn {
		float: left;
		height: 60px;
		background-color: #324057;
		border-color: #324057;
	}
	
	.main-header .navBtn:hover {
		background-color: #1F2D3D;
		border-color: #1F2D3D;
	}
	
	.main-header .mainMenuBar {
		float: left;
		height: 60px;
	}
	
	.main-header .mainMenuBar .el-menu {
		top: 60px;
	}
	
	.main-sidebar {
		background-color: #475669;
		position: absolute;
		top: 0;
		left: 0;
		padding-top: 50px;
		min-height: 100%;
		width: 230px;
		z-index: 810;
	}
	
	.main-footer {
		padding-left: 240px;
		background: #fff;
		border-top: 1px solid #d2d6de;
		font-size: 14px;
		height: 50px;
		line-height: 50px;
	}
	
	.mainVersion {
		text-align: right;
		padding-right: 10px;
	}
	
	.wrapper {
		min-height: 100%;
		position: relative;
		overflow: hidden;
		background-color: #ecf0f5;
	}
	
	.content-wrapper {
		min-height: 780px;
	}
	
	.sidebar-opened {
		padding-left: 230px;
	}

</style>
