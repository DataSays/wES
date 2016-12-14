<template>
	<page-grid ref="pageGrid1" query-title="ES 数据浏览" result-title="查询结果" v-on:doQuery="search">
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
			<el-col :span="6" :pull="1">
				<el-form-item>
					<el-button type="primary" icon="search" @click="search">查询</el-button>
				</el-form-item>
			</el-col>
		</el-form>
		<el-table :data="allData" stripe border highlight-current-row resizable slot="resultGrid">
			<el-table-column prop="index" label="Index" width="150">
			</el-table-column>
			<el-table-column prop="type" label="Type" width="150">
			</el-table-column>
			<el-table-column prop="id" label="Id" width="220">
			</el-table-column>
			<el-table-column prop="source" label="Source" :formatter="formatSource">
			</el-table-column>
			<el-table-column inline-template label="Action" align="center" width="130">
				<el-button-group>
					<el-button size="small" type="success" icon="edit" @click="doEdit($index, row)"></el-button>
					<el-button size="small" type="info" icon="view" @click="doView($index, row)"></el-button>
					<el-button size="small" type="warning" icon="delete" @click="doDelete($index, row)"></el-button>
				</el-button-group>
			</el-table-column>
		</el-table>
	</page-grid>
</template>
<script>
import common from '../components/common.js'
import PageGrid from '../components/PageGrid'

export default {
  components: {
    'page-grid': PageGrid
  },
  data () {
    return {
      query: {
        currIndexId: 'wes',
        currTypeId: 'TestDoc',
        queryText: ''
      },
      allData: [
      ],
      allSchemeData: {
      }
    }
  },
  created: function () {
    this.fetchAllSchemeData()
  },
  computed: {
    allIndex: function () {
      var allIndex = []
      for (var k in this.allSchemeData) {
        allIndex.push(k)
      }
      return allIndex
    },
    allType: function () {
      return this.allSchemeData[this.query.currIndexId]
    }
  },
  methods: {
    _c () {
    },
    changeIndex (index) {
      this.query.currTypeId = this.allType[0]
    },
    doEdit (index, el) {
      console.log(index, el)
    },
    doView (index, el) {
      console.log(index, el)
    },
    doDelete (index, el) {
      common.confirmMsg(this, '确认删除这条记录?', () => {
        console.log(index, el)
      })
    },
    formatSource (row, col) {
      return JSON.stringify(row.source)
    },
    fetchAllSchemeData () {
      var self = this
      common.DEBUG = true
      common.getAction('/static/data/allSchemeData.json',
        function (response) {
          self.allSchemeData = response.data.data
          self.search()
        },
        function (error) {
          self.showErrorMsg(error)
        })
    },
    showErrorMsg (error) {
      this.$refs.pageGrid1.showErrorMsg(error)
      this.$message({
        type: 'error',
        showClose: true,
        message: error
      })
    },
    search () {
      var self = this
      common.DEBUG = true
      common.postAction('/static/data/allEsData.json',
        {
          page: self.$refs.pageGrid1.page,
          query: self.query
        },
        function (response) {
          self.$refs.pageGrid1.updatePage(response.data.page)
          self.allData = response.data.data
        },
        function (error) {
          self.showErrorMsg(error)
        }
      )
    }
  }
}
</script>
<style scoped>
	.col15 {
		width: 15%;
	}
	
	.col40 {
		width: 40%;
	}
	
	.pageGrid {
		min-width: 800px;
	}

</style>
