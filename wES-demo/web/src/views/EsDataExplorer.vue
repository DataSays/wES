<template>
	<page-grid ref="pageGrid1" v-on:doQuery="search">
		<el-form ref="queryForm" :model="query" label-width="120px" slot="queryForm">
			<el-col :span="6">
				<el-form-item label="Index" :required="true">
					<el-select v-model="query.currIndexId" @change="changeIndex">
						<el-option v-for="item in allIndex" :label="item" :value="item">
						</el-option>
					</el-select>
				</el-form-item>
			</el-col>
			<el-col :span="6">
				<el-form-item label="Type" :required="true">
					<el-select v-model="query.currTypeId">
						<el-option v-for="item in allType" :label="item" :value="item">
						</el-option>
					</el-select>
				</el-form-item>
			</el-col>
			<el-col :span="6">
				<el-form-item label="搜索内容">
					<el-input v-model="query.queryText"></el-input>
				</el-form-item>
			</el-col>
			<el-col :span="6">
				<el-form-item>
					<el-button type="primary" icon="search" @click="search">查询</el-button>
				</el-form-item>
			</el-col>
		</el-form>
		<el-table :data="allData" stripe border highlight-current-row resizable slot="resultGrid" @selection-change="handleSelectionChange">
			<el-table-column type="selection" width="50">
			</el-table-column>
			<el-table-column prop="_index" label="Index" width="150">
			</el-table-column>
			<el-table-column prop="_type" label="Type" width="150">
			</el-table-column>
			<el-table-column prop="_id" label="Id" width="220">
			</el-table-column>
			<el-table-column label="Source" :formatter="formatSource">
			</el-table-column>
			<el-table-column inline-template label="Action" align="center" width="130">
				<el-button-group>
					<el-button size="small" type="success" icon="edit" @click="doEdit($index, row)"></el-button>
					<el-button size="small" type="warning" icon="delete" @click="doDelete($index, row)"></el-button>
				</el-button-group>
			</el-table-column>
		</el-table>
		<span slot="resultActions">
			<el-button type="primary" icon="delete" size="small" @click="batchDelete" :disabled="allSelectedDocs.length<1">批量删除</el-button>
		</span>
	</page-grid>
</template>
<script>
import common from '../assets/common.js';
import esAction from '../actions/esActions.js';
import PageGrid from '../components/PageGrid.vue';

export default {
	components: {
		'page-grid': PageGrid
	},
	data() {
		return {
			query: common.getState(this, 'EsDataExplorerQuery', {
				currIndexId: 'wes',
				currTypeId: 'TestDoc',
				queryText: ''
			}),
			allSelectedDocs: [
      ],
			allData: [
      ],
			allSchemeData: {}
		};
	},
	created: function () {
		this.fetchAllSchemeData();
	},
	computed: {
		allIndex: function () {
			var allIndex = [];
			for (var k in this.allSchemeData) {
				allIndex.push(k);
			}
			return allIndex;
		},
		allType: function () {
			return this.allSchemeData[this.query.currIndexId];
		}
	},
	methods: {
		changeIndex(index) {
			this.query.currTypeId = this.allType[0];
		},
		doEdit(index, el) {
			//console.log(this, index, el);
			this.$router.push('/esDataEdit/' + el._index + '/' + el._type + '/' + el._id);
		},
		doDelete(index, el) {
			var self = this;
			common.confirmMsg(self, '确认删除这条记录?', () => {
				esAction.del(self, el._index, el._type, el._id, (response) => {
					self.search();
					common.msg(self, '删除成功!');
				});
			});
		},
		batchDelete() {
			if (this.allSelectedDocs.length > 0) {
				common.confirmMsg(this, '确认删除选中记录?', () => {
					console.log(this.allSelectedDocs);
				});
			}
		},
		handleSelectionChange(val) {
			this.allSelectedDocs = val;
		},
		formatSource(row, col) {
			return JSON.stringify(row._source);
		},
		fetchAllSchemeData() {
			var self = this;
			esAction.getAllIndex(self,
				function (response) {
					self.allSchemeData = response.data.data;
					self.search();
				},
				function (error) {
					common.errorMsg(self, error);
				});
		},
		search() {
			var self = this;
			common.upState(self, 'EsDataExplorerQuery', self.query);
			esAction.searchDoc(self, self.$refs.pageGrid1.page, self.query,
				function (response) {
					self.$refs.pageGrid1.updatePage(response.data.data.page);
					self.allData = response.data.data.data;
				},
				function (error) {
					common.errorMsg(self, error);
				}
			);
		}
	}
};

</script>
<style scoped>


</style>
