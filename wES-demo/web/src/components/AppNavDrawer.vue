<template>
  <mu-drawer @hide="handleHide" @close="handleClose" :open="open" :docked="docked" class="app-drawer" :zDepth="1">
    <mu-appbar class="exmaples-nav-appbar" :zDepth="0">
      <a class="exmaples-appbar-title" @click="handleMenuChange('#/index')" href="#/index" style="display:inline-block;">wES-demo</a>
      <mu-badge content="0.5" class="exmaples-version" secondary/>
    </mu-appbar>
    <mu-divider/>
    <mu-list @change="handleMenuChange" :value="menuVal">
      <mu-list-item :title="'getStarted'" toggleNested>
        <mu-list-item value="#/index" slot="nested" :title="'index'" />
        <mu-list-item value="#/hello" slot="nested" :title="'hello'" />
        <mu-list-item value="#/hello2" slot="nested" :title="'hello2'" />
      </mu-list-item>
      <mu-list-item :title="'customization'" toggleNested>
        <mu-list-item slot="nested" value="#/theme" :title="'theme'" />
        <mu-list-item slot="nested" value="#/colors" :title="'color'" />
      </mu-list-item>
    </mu-list>
    <mu-divider/>
  </mu-drawer>
</template>
<script>
// import Vue from 'vue'
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
