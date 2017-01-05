import common from '../assets/common.js';

export default {
	DEBUG: false,
	get: function (self, index, type, id, callBack) {
		let url = '/es/esData/';
		url += index.trim() + '/';
		url += type.trim() + '/';
		url += id.trim();
		if (this.DEBUG) {
			console.log(url);
			url = '/static/data/esData.json';
		}
		common.getAction(url,
			callBack, (error) => {
				common.errorMsg(self, error);
			});
	},
	del: function (self, index, type, id, callBack) {
		let url = '/es/esData/';
		url += index.trim() + '/';
		url += type.trim() + '/';
		url += id.trim();
		if (this.DEBUG) {
			console.log(url);
			url = '/static/data/esData.json';
		}
		common.delAction(url,
			callBack, (error) => {
				common.errorMsg(self, error);
			});
	},
	saveDoc: function (self, doc, callBack) {
		// console.log(doc);
		if (this.DEBUG) {
			common.getAction('/static/data/esData.json',
				callBack, (error) => {
					common.errorMsg(self, error);
				});
		} else {
			common.postAction('/es/saveEsData', doc,
				callBack, (error) => {
					common.errorMsg(self, error);
				});
		}
	},
	getAllIndex: function (self, callBack) {
		let url = '/es/allSchemeData';
		if (this.DEBUG) {
			url = '/static/data/allSchemeData.json';
		}
		common.getAction(url,
			callBack, (error) => {
				common.errorMsg(self, error);
			});
	},
	searchDoc: function (self, page, query, callBack) {
		if (this.DEBUG) {
			console.log(page, query);
			common.getAction('/static/data/allEsData.json',
				callBack, (error) => {
					common.errorMsg(self, error);
				});
		} else {
			//console.log(page, query);
			common.postAction('/es/searchEsData', {
					page: page,
					query: query
				},
				callBack, (error) => {
					common.errorMsg(self, error);
				});
		}
	}
};
