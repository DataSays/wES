<template>
	<div class="pageGrid">
		<slot name="queryForm"></slot>
		<slot name="resultGrid"></slot>
		<slot name="resultActions"></slot>
		<el-pagination small layout="prev, pager, next, total" :total="page.total" :current-page="page.pageNo" :page-size="page.size" style="float:right" @size-change="pageSizeChange" @current-change="pageChange">
		</el-pagination>
	</div>
</template>
<script>
export default {
	props: {},
	data() {
		return {
			page: {
				total: 0,
				pageNo: 1,
				size: 20
			},
			errToast: false,
			errMsg: ''
		};
	},
	methods: {
		updatePage(p) {
			this.page = p;
		},
		pageChange(newPageNo) {
			// console.log('pageChange:' + newPageNo);
			var self = this;
			self.page.pageNo = parseInt(newPageNo, 10);
			self.doQuery();
		},
		pageSizeChange(newPageSize) {
			// console.log('pageSizeChange:' + newPageSize);
			var self = this;
			self.page.size = parseInt(newPageSize, 10);
			self.doQuery();
		},
		doQuery() {
			this.$emit('doQuery');
		}
	}
};

</script>
<style lang="css">
.pageGrid {
	min-width: 900px;
	overflow: auto;
}

</style>
