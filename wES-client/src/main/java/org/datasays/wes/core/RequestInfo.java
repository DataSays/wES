package org.datasays.wes.core;

import okhttp3.Headers;
import okhttp3.HttpUrl;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by watano on 2016/11/21.
 */
public class RequestInfo {
	private Object _body;
	private Map<String, String> _params = new LinkedHashMap<>();
	private Headers.Builder _headers = new Headers.Builder();
	protected HttpUrl.Builder _url = null;
	protected String _baseUrl = null;

	public RequestInfo(String baseUrl) {
		setBaseUrl(baseUrl);
	}

	public RequestInfo(HttpUrl baseUrl) {
		setBaseUrl(baseUrl);
	}

	public void baseAuth(String user, String password) {
		if (user != null && user.trim().length() > 0 && password != null) {
			addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes()));
		}
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

	public Headers getHeaders() {
		return _headers.build();
	}

	public void addParams(String name, Object value) {
		_params.put(name, value.toString());
	}

	public void addHeader(String name, String value) {
		_headers.add(name, value);
	}

	public Object getBody() {
		return _body;
	}

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
