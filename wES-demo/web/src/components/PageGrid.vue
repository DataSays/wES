<template>
	<div>
		<h2>{{queryTitle}}</h2>
		<el-row>
			<el-col :span="24">
				<slot name="queryForm"></slot>
			</el-col>
		</el-row>
		<h2>{{resultTitle}}</h2>
		<el-row>
			<el-col :span="24">
				<slot name="resultGrid"></slot>
				<el-pagination small layout="total, prev, pager, next" :total="page.total" :current-page="page.pageNo" :page-size="page.size"
						@size-change="pageSizeChange" @current-change="pageChange">
				</el-pagination>
			</el-col>
		</el-row>
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
    _c () {
    },
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
    pageChange (newPageNo) {
      // console.log('pageChange:' + newPageNo)
      var self = this
      self.page.pageNo = parseInt(newPageNo)
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
