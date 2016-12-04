package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
 **/
public class IndicesUpdateAliases extends RequestInfo {

	public IndicesUpdateAliases(String baseUrl) {
		super(baseUrl);
	}

	public IndicesUpdateAliases(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: time timeout: Request timeout
	 **/
	public IndicesUpdateAliases timeout(long timeout) {
		addParams("timeout", timeout);
		return this;
	}

	/**
	 * param: time masterTimeout: Specify timeout for connection to master
	 **/
	public IndicesUpdateAliases masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * body*:The definition of `actions` to perform
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public IndicesUpdateAliases setParts() {

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"POST".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_aliases
		setUrl("_aliases");
		return super.parseUrl(method);

	}
}
