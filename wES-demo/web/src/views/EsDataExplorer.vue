<template>
	<div>
		<h2>&nbsp;ES 数据浏览</h2>
		<mu-content-block>
			<mu-flexbox>
				<mu-flexbox-item>
					<mu-select-field v-model="query.currIndexId" label="Index" fullWidth>
						<mu-menu-item v-for="index in allIndex" :value="index" :title="index" />
					</mu-select-field>
				</mu-flexbox-item>
				<mu-flexbox-item>
					<mu-select-field v-model="query.currTypeId" label="Type" fullWidth>
						<mu-menu-item v-for="type in allType" :value="type" :title="type" />
					</mu-select-field>
				</mu-flexbox-item>
				<mu-flexbox-item>
					<mu-text-field label="搜索内容" v-model="query.queryText" fullWidth />
				</mu-flexbox-item>
				<mu-flexbox-item>
					<mu-raised-button label="查询" primary @click="search" />
				</mu-flexbox-item>
			</mu-flexbox>
		</mu-content-block>
		<h2>&nbsp;查询结果</h2>
		<mu-content-block>
			<mu-table>
				<mu-tr>
					<mu-th>Index</mu-th>
					<mu-th>Type</mu-th>
					<mu-th>Id</mu-th>
					<mu-th>Source</mu-th>
					<mu-th>Action</mu-th>
				</mu-tr>
				<mu-tr v-for="item in allData">
					<mu-td>{{item.index}}</mu-td>
					<mu-td>{{item.type}}</mu-td>
					<mu-td>{{item.id}}</mu-td>
					<mu-td>{{item.source}}</mu-td>
					<mu-td>
						<mu-float-button icon="edit" mini/>
						<mu-float-button icon="delete" mini secondary/>
					</mu-td>
				</mu-tr>
			</mu-table>
			<mu-divider/>
			<mu-pagination :total="page.total" :current="page.pageNo" :page-size="page.size" :show-size-changer="true" @pageChange="pageChange"
					@pageSizeChange="pageSizeChange">      
      <mu-toast v-if="errToast" :message="errMsg" @close="hideToast"/>
		</mu-content-block>
	</div>
</template>
<script>
import common from '../components/common.js'

export default {
  data () {
    return {
      query: {
        currIndexId: 'wes',
        currTypeId: 'TestDoc',
        queryText: ''
      },
      errToast: false,
      errMsg: '',
      page: {
        total: 500,
        pageNo: 1,
        size: 20
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
      return this.allSchemeData[this.currIndexId]
    }
  },
  methods: {
    showToast (msg) {
      this.errToast = true
      this.errMsg = msg
      if (this.toastTimer) clearTimeout(this.toastTimer)
      this.toastTimer = setTimeout(() => { this.errToast = false }, 2000)
    },
    hideToast () {
      this.errToast = false
      if (this.toastTimer) clearTimeout(this.toastTimer)
    },
    pageChange (newIndex) {
      var self = this
      self.page.pageNo = parseInt(newIndex)
    },
    pageSizeChange (newPageSize) {
      var self = this
      self.page.size = parseInt(newPageSize)
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
          self.showToast(error)
        })
    },
    search () {
      var self = this
      common.DEBUG = true
      common.postAction('/static/data/allEsData.json',
        {
          page: self.page,
          query: self.query
        },
        function (response) {
          self.page = response.data.page
          self.allData = response.data.data
        },
        function (error) {
          self.showToast(error)
        }
      )
    }
  }
}
</script>
