<template>
	<el-form ref="form" :model="data" label-width="80px">
		<el-form-item label="Index">
			<el-input v-model="data.index" :disabled="true"></el-input>
		</el-form-item>
		<el-form-item label="Type">
			<el-input v-model="data.type" :disabled="true"></el-input>
		</el-form-item>
		<el-form-item label="Id">
			<el-input v-model="data.id" :disabled="true"></el-input>
		</el-form-item>
		<el-form-item label="Source" required>
			<el-input type="textarea" v-model="data.sourceJson" :autosize="{ minRows: 2, maxRows: 40}"></el-input>
		</el-form-item>
		<el-form-item>
			<el-button type="primary" icon="edit" @click="doEdit">更新</el-button>
			<el-button type="warning" icon="delete" @click="doDelete">删除</el-button>
			<el-button @click="doBack">取消</el-button>
		</el-form-item>
	</el-form>
</template>
<script>
import common from '../assets/common.js';
import esAction from '../actions/esActions.js';

export default {
	components: {},
	data() {
		return {
			data: {
				index: this.$route.params.index,
				type: this.$route.params.type,
				id: this.$route.params.id,
				source: {}
			}
		};
	},
	created: function () {
		this.fetchEsData();
	},
	mounted() {
		//console.log(this);
	},
	computed: {},
	methods: {
		_c() {},
		doEdit(index, el) {
			console.log(index, el);
		},
		doDelete(index, el) {
			common.confirmMsg(this, '确认删除这条记录?', () => {
				console.log(index, el);
			});
		},
		doBack() {
			this.$router.go(-1);
		},
		fetchEsData() {
			var self = this;
			esAction.get(self, self.index, self.type, self.id,
				function (response) {
					self.data = response.data.data;
					self.data.sourceJson = JSON.stringify(self.data.source, 'true', 2);
				});
		}
	}
};

</script>
<style scoped>


</style>
