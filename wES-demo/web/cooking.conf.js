var path = require('path');
var cooking = require('cooking');

cooking.set({
	entry: {
		app: ['babel-polyfill', './src/main.js']
	},
	dist: '../src/main/resources/public/',
	template: './index.html',

	devServer: {
		port: 8100,
		publicPath: '/',
		proxy: {
			'/es/': {
				target: 'http://127.0.0.1:8080',
				changeOrigin: true,
				pathRewrite: {
					//'^/api': ''
				}
			}
		}
	},

	// production
	clean: true,
	hash: true,
	sourceMap: false,
	minimize: true,
	chunk: true, // see https://cookingjs.github.io/zh-cn/configuration.html#chunk
	postcss: [
		// require('...')
	],
	publicPath: '/',
	assetsPath: 'static',
	urlLoaderLimit: 10000,
	static: true,
	extractCSS: '[name].[contenthash:7].css',
	alias: {
		'vue$': 'vue/dist/vue.common.js',
		'src': path.join(__dirname, 'src')
	},
	extends: ['vue2', 'lint', 'less', 'autoprefixer']
});

module.exports = cooking.resolve();
