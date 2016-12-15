import common from '../components/common.js';

export default {
	get: function (self, index, type, id, callBack) {
		common.getAction('/static/data/esData.json',
			// common.getAction('',
			callBack, (error) => {
				common.errorMsg(error);
			});
	},
	getAllIndex: function (self, callBack) {
		common.getAction('/static/data/allSchemeData.json',
			// common.getAction('',
			callBack, (error) => {
				common.errorMsg(error);
			});
	},
	searchDoc: function (self, page, query, callBack) {
		common.postAction('/static/data/allEsData.json', {
				// common.getAction('',
				page: page,
				query: query
			},
			callBack, (error) => {
				common.errorMsg(error);
			});
	}
};
