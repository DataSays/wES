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
		//console.log(this.$route.params);
		return {
			data: {
				index: this.$route.params.index,
				type: this.$route.params.type,
				id: this.$route.params.id,
				sourceJson: ''
			}
		};
	},
	created: function () {},
	mounted() {
		this.fetchEsData();
	},
	computed: {},
	methods: {
		doEdit(event) {
			var sourceObj = JSON.parse(this.data.sourceJson);
			sourceObj.index = this.data.index;
			sourceObj.type = this.data.type;
			sourceObj.id = this.data.id;
			esAction.saveDoc(this, sourceObj, (response) => {
				this.$router.go(-1);
			});
		},
		doDelete(event) {
			common.confirmMsg(this, '确认删除这条记录?', () => {
				esAction.del(this, this.data.index, this.data.type, this.data.id, (response) => {
					this.$router.go(-1);
				});
			});
		},
		doBack() {
			this.$router.go(-1);
		},
		fetchEsData() {
			var self = this;
			const data = self.$data.data;
			esAction.get(self,
				data.index,
				data.type,
				data.id,
				function (response) {
					//self.data = response.data.data;
					self.data.sourceJson = JSON.stringify(response.data.data._source, 'true', 2);
				});
		}
	}
};

</script>
<style scoped>


</style>
