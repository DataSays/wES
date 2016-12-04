package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat.html
 **/
public class CatHelp extends RequestInfo {

	public CatHelp(String baseUrl) {
		super(baseUrl);
	}

	public CatHelp(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: boolean help: Return help information
	 **/
	public CatHelp help(boolean help) {
		addParams("help", help);
		return this;
	}

	/**
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 **/
	public CatHelp s(String s) {
		addParams("s", s);
		return this;
	}

	/**
	 * body:null
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public CatHelp setParts() {

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_cat
		setUrl("_cat");
		return super.parseUrl(method);

	}
}
