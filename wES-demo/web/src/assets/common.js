import axios from 'axios';

export default {
	DEBUG: false,
	_doOk: function (response, callBack, errBack) {
		if (callBack) {
			if (response.data && response.data.code === 1) {
				callBack(response);
			} else {
				errBack(response);
			}
		} else {
			console.error(response);
		}
	},
	_doError: function (error, errBack) {
		if (errBack) {
			errBack(error);
		} else {
			console.error(error);
		}
	},
	getAction: function (url, callBack, errBack) {
		if (this.DEBUG) {
			console.log(url);
		}
		axios.get(url)
			.then((response) => {
				this._doOk(response, callBack, errBack);
			})
			.catch((error) => {
				this._doError(error);
			});
	},
	delAction: function (url, callBack, errBack) {
		if (this.DEBUG) {
			console.log(url);
		}
		axios.delete(url)
			.then((response) => {
				this._doOk(response, callBack, errBack);
			})
			.catch((error) => {
				this._doError(error);
			});
	},
	headAction: function (url, callBack, errBack) {
		if (this.DEBUG) {
			console.log(url);
		}
		axios.head(url)
			.then((response) => {
				this._doOk(response, callBack, errBack);
			})
			.catch((error) => {
				this._doError(error);
			});
	},
	patchAction: function (url, data, callBack, errBack) {
		if (this.DEBUG) {
			console.log(url);
		}
		axios.patch(url, data)
			.then((response) => {
				this._doOk(response, callBack, errBack);
			})
			.catch((error) => {
				this._doError(error);
			});
	},
	postAction: function (url, data, callBack, errBack) {
		if (this.DEBUG) {
			console.log(url);
			console.log(JSON.stringify(data));
		}
		axios.post(url, data)
			.then((response) => {
				this._doOk(response, callBack, errBack);
			})
			.catch((error) => {
				this._doError(error);
			});
	},
	putAction: function (url, data, callBack, errBack) {
		if (this.DEBUG) {
			console.log(url);
			console.log(JSON.stringify(data));
		}
		axios.post(url, data)
			.then((response) => {
				this._doOk(response, callBack, errBack);
			})
			.catch((error) => {
				this._doError(error);
			});
	},
	errorMsg(that, msg) {
		that.$message({
			type: 'error',
			showClose: true,
			message: msg
		});
	},
	confirmMsg(that, msg, fnOk) {
		that.$confirm(msg, 'Warning', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			})
			.then(fnOk)
			.catch(() => {

			});
	}
};
