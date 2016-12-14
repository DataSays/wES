import axios from 'axios'

export default {
	DEBUG: false,
	getAction: function (url, callBack, errBack) {
		if (this.DEBUG) {
			console.log(url)
		}
		axios.get(url)
			.then(function (response) {
				if (callBack) {
					if (response.data && response.data.code === 1) {
						callBack(response)
					} else {
						errBack(response)
					}
				} else {
					console.error(response)
				}
			})
			.catch(function (error) {
				if (errBack) {
					errBack(error)
				} else {
					console.error(error)
				}
			})
	},
	postAction: function (url, data, callBack, errBack) {
		if (this.DEBUG) {
			console.log(url)
			console.log(JSON.stringify(data))
		}
		// axios.post(url, data)
		axios.get(url)
			.then(function (response) {
				if (callBack) {
					if (response.data && response.data.code === 1) {
						callBack(response)
					} else {
						errBack(response)
					}
				} else {
					console.error(response)
				}
			})
			.catch(function (error) {
				if (errBack) {
					errBack(error)
				} else {
					console.error(error)
				}
			})
	},
	confirmMsg (that, msg, fnOk) {
		that.$confirm(msg, 'Warning', {
			confirmButtonText: '确定',
			cancelButtonText: '取消',
			type: 'warning'
		}).then(fnOk).catch(() => {

		})
	}
}
