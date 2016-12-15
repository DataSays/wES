<template>
	<div class="pageGrid">
		<el-row>
			<el-col :span="24">
				<slot name="queryForm"></slot>
			</el-col>
		</el-row>
		<div><b>{{resultTitle}}</b></div>
		<el-row>
			<el-col :span="24">
				<slot name="resultGrid"></slot>
			</el-col>
			<el-col :span="24">
				<slot name="resultActions"></slot>
				<el-pagination small layout="prev, pager, next, total" :total="page.total" :current-page="page.pageNo" :page-size="page.size" style="float:right" @size-change="pageSizeChange" @current-change="pageChange">
				</el-pagination>
			</el-col>
		</el-row>
	</div>
</template>
<script>
export default {
	props: {
		resultTitle: {
			type: String,
			required: true
		}
	},
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
		_c() {},
		updatePage(p) {
			this.page = p;
		},
		pageChange(newPageNo) {
			// console.log('pageChange:' + newPageNo);
			var self = this;
			self.page.pageNo = parseInt(newPageNo);
			self.doQuery();
		},
		pageSizeChange(newPageSize) {
			// console.log('pageSizeChange:' + newPageSize);
			var self = this;
			self.page.size = parseInt(newPageSize);
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
	min-width: 500px;
}

</style>
