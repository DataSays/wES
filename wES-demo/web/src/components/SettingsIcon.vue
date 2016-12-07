<template>
  <mu-icon-menu icon="settings" :anchorOrigin="{'vertical':'bottom','horizontal':'right'}" :targetOrigin="{'vertical':'top','horizontal':'right'}">
    <mu-sub-header>主题</mu-sub-header>
    <mu-divider />
    <mu-menu-item title="Light" value="light" />
    <mu-menu-item title="Dark" value="dart" />
    <mu-menu-item title="Carbon" value="carbon" />
    <mu-menu-item title="Teal" value="teal" />
  </mu-icon-menu>
</template>
<script>
import light from '!raw!muse-ui/dist/theme-default.css'
import dark from '!raw!muse-ui/dist/theme-dark.css'
import carbon from '!raw!muse-ui/dist/theme-carbon.css'
import teal from '!raw!muse-ui/dist/theme-teal.css'
export default {
  data () {
    return {
      theme: 'light',
      themes: {
        light,
        dark,
        carbon,
        teal
      }
    }
  },
  changeTheme (theme) {
    this.theme = theme
    const styleEl = this.getThemeStyle()
    styleEl.innerHTML = this.themes[theme] || ''
  },
  getThemeStyle () {
    const themeId = 'muse-theme'
    let styleEl = document.getElementById(themeId)
    if (styleEl) return styleEl
    styleEl = document.createElement('style')
    styleEl.id = themeId
    document.body.appendChild(styleEl)
    return styleEl
  }
}
</script>
