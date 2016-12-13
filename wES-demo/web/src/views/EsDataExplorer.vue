<template>
	<page-grid ref="pageGrid1" query-title="ES 数据浏览" result-title="查询结果" v-on:doQuery="search">
		<mu-flexbox slot="queryForm">
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
				<mu-raised-button label="查询2" primary @click="search" />
			</mu-flexbox-item>
		</mu-flexbox>

    <mu-table slot="resultGrid">
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
      return this.allSchemeData[this.currIndexId]
    }
  },
  methods: {
    fetchAllSchemeData () {
      var self = this
      common.DEBUG = true
      common.getAction('/static/data/allSchemeData.json',
        function (response) {
          self.allSchemeData = response.data.data
          // self.search()
          // self.showErrorMsg(response.data.message + '111')
        },
        function (error) {
          self.showErrorMsg(error)
        })
    },
    showErrorMsg (error) {
      // console.log(error)
      this.$refs.pageGrid1.showErrorMsg(error)
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
          // self.showErrorMsg(response.data.message)
        },
        function (error) {
          self.showErrorMsg(error)
        }
      )
    }
  }
}
</script>
