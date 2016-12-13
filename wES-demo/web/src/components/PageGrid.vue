<template>
	<div>
		<h2>&nbsp;{{queryTitle}}</h2>
		<mu-toast v-if="errToast" :message="errMsg" @close="hideErrorMsg" />
		<mu-content-block>
			<slot name="queryForm"></slot>
		</mu-content-block>
		<h2>&nbsp;{{resultTitle}}</h2>
		<mu-content-block>
			<slot name="resultGrid"></slot>
			<mu-divider/>
			<mu-pagination :total="page.total" :current="page.pageNo" :page-size="20" default-page-size.number="20" :show-size-changer="true"
					@pageChange="pageChange" @pageSizeChange="pageSizeChange" />
		</mu-content-block>
	</div>
</template>
<script>
export default {
  props: {
    queryTitle: {
      type: String,
      required: true
    },
    resultTitle: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      page: {
        total: 0,
        pageNo: 1,
        size: 20
      },
      errToast: false,
      errMsg: ''
    }
  },
  methods: {
    updatePage (p) {
      this.page = p
    },
    showErrorMsg (msg) {
      // console.log('error msg:' + msg)
      this.errToast = true
      this.errMsg = msg
      if (this.toastTimer) clearTimeout(this.toastTimer)
      this.toastTimer = setTimeout(() => { this.errToast = false }, 20000)
    },
    hideErrorMsg () {
      this.errToast = false
      if (this.toastTimer) clearTimeout(this.toastTimer)
    },
    pageChange (newIndex) {
      // console.log('pageChange:' + newIndex)
      var self = this
      self.page.pageNo = parseInt(newIndex)
      self.doQuery()
    },
    pageSizeChange (newPageSize) {
      // console.log('pageSizeChange:' + newPageSize)
      var self = this
      self.page.size = parseInt(newPageSize)
      self.doQuery()
    },
    doQuery () {
      this.$emit('doQuery')
    }
  }
}
</script>
