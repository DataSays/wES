<template>
	<div id="app">
		<el-row>
			<el-col :span="24">
				<el-menu default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect">
					<el-menu-item index="/index">Index</el-menu-item>
					<el-submenu index="2">
						<template slot="title">Hello</template>
						<el-menu-item index="/hello">hello</el-menu-item>
						<el-menu-item index="/hello2">hello2</el-menu-item>
					</el-submenu>
					<el-menu-item index="/esDataExplorer">ESDataExplorer</el-menu-item>
				</el-menu>
			</el-col>
		</el-row>
		<el-row>
			<router-view></router-view>
		</el-row>
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
<style lang="less">


</style>
