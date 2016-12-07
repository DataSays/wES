<template>
  <mu-drawer @hide="handleHide" @close="handleClose" :open="open" :docked="docked" class="app-drawer" :zDepth="1">
    <mu-appbar class="exmaples-nav-appbar" :zDepth="0">
      <a class="exmaples-appbar-title" @click="handleMenuChange('#/index')" href="#/index" style="display:inline-block;">wES-demo</a>
      <mu-badge content="0.5" class="exmaples-version" secondary/>
    </mu-appbar>
    <mu-divider/>
    <mu-list @change="handleMenuChange" :value="menuVal">
      <mu-list-item value="#/index" title="Index" />
      <mu-divider/>
      <mu-list-item title="Hello" toggleNested>
        <mu-list-item value="#/hello" slot="nested" :title="'hello'" />
        <mu-list-item value="#/hello2" slot="nested" :title="'hello2'" />
      </mu-list-item>
    </mu-list>
    <mu-divider/>
    <mu-sub-header>资源</mu-sub-header>
    <mu-list>
      <mu-list-item href="https://github.com/DataSays/wES" target="_blank" title="GitHub" />
      <mu-list-item href="http://vuejs.org/" target="_blank" title="Vue" />
      <mu-list-item href="https://material.google.com/" target="_blank" title="Material Design" />
      <mu-list-item href="http://www.material-ui.com/" target="_blank" title="Material-UI" />
    </mu-list>
  </mu-drawer>
</template>
<script>
import Vue from 'vue'

export default {
  props: {
    open: {
      type: Boolean,
      default: true
    },
    docked: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      menuVal: '#/'
    }
  },
  computed: {
    theme () {
      return Vue.config.theme
    }
  },
  methods: {
    handleClose () {
      this.$emit('close')
    },
    handleMenuChange (val) {
      this.menuVal = val
      if (this.docked) {
        window.location.hash = this.menuVal
      } else {
        this.changeHref = true
      }
      this.$emit('change', val)
    },
    handleHide () {
      if (!this.changeHref) return
      window.location.hash = this.menuVal
      this.changeHref = false
    }
  },
  mounted () {
    this.menuVal = window.location.hash
    window.addEventListener('hashchange', () => {
      this.menuVal = window.location.hash
    })
  }
}
</script>
<style lang="less">
  @import "../assets/styles/import.less";
  .exmaples-drawer {
    box-shadow: none;
    border-right: 1px solid @borderColor;
  }
  
  .exmaples-nav-appbar.mu-appbar {
    background-color: @dialogBackgroundColor;
    color: @secondaryTextColor;
  }
  
  .exmaples-appbar-title {
    color: @secondaryTextColor;
  }

</style>
