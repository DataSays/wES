package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-nodeattrs.html
 **/
public class CatNodeattrs extends RequestInfo {

	public CatNodeattrs(String baseUrl) {
		super(baseUrl);
	}

	public CatNodeattrs(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 **/
	public CatNodeattrs format(String format) {
		addParams("format", format);
		return this;
	}

	/**
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 **/
	public CatNodeattrs local(boolean local) {
		addParams("local", local);
		return this;
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public CatNodeattrs masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: list h: Comma-separated list of column names to display
	 **/
	public CatNodeattrs h(String h) {
		addParams("h", h);
		return this;
	}

	/**
	 * param: boolean help: Return help information
	 **/
	public CatNodeattrs help(boolean help) {
		addParams("help", help);
		return this;
	}

	/**
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 **/
	public CatNodeattrs s(String s) {
		addParams("s", s);
		return this;
	}

	/**
	 * param: boolean v: Verbose mode. Display column headers
	 **/
	public CatNodeattrs v(boolean v) {
		addParams("v", v);
		return this;
	}

	/**
	 * body:null
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public CatNodeattrs setParts() {

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_cat/nodeattrs
		setUrl("_cat", "nodeattrs");
		return super.parseUrl(method);

	}
}
