package org.datasays.wes.core;

import okhttp3.HttpUrl;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by watano on 2016/11/21.
 */
public class ARequestInfo implements IRequestInfo {
		private Object _body;
		private Map<String, String> _params = new LinkedHashMap<>();
		private Map<String, String> _headers = new LinkedHashMap<>();
		protected HttpUrl.Builder _url = null;
		protected String _baseUrl = null;

		public ARequestInfo(String baseUrl) {
				setBaseUrl(baseUrl);
		}

		public ARequestInfo(HttpUrl baseUrl) {
				setBaseUrl(baseUrl);
		}

		public void setBody(Object body) {
				this._body = body;
		}

		public void setBaseUrl(String baseUrl) {
				setBaseUrl(HttpUrl.parse(baseUrl));
		}

		public void setBaseUrl(HttpUrl baseUrl) {
				_url = baseUrl.newBuilder();
				_baseUrl = baseUrl.url().toString();
		}

		public void setUrl(String... paths) {
				setBaseUrl(_baseUrl);
				for (String path : paths) {
						_url.addPathSegment(path);
				}
		}

		public Map<String, String> getHeaders() {
				return _headers;
		}

		public void addParams(String name, Object value) {
				_params.put(name, value.toString());
		}

		public void addHeader(String name, String value) {
				_headers.put(name, value);
		}

		@Override
		public Object getBody() {
				return _body;
		}

		@Override
		public String parseUrl(String method) {
				for (String key : _params.keySet()) {
						String value = _params.get(key);
						if (key != null && value != null) {
								_url.addQueryParameter(key, value);
						}
				}
				return _url.build().toString();
		}
}
