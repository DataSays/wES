package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/
 **/
public class Ping extends RequestInfo {

	public Ping(String baseUrl) {
		super(baseUrl);
	}

	public Ping(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * body:null
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public Ping setParts() {

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"HEAD".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/
		setUrl("");
		return super.parseUrl(method);

	}
}
