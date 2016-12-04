package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-health.html
 **/
public class CatHealth extends RequestInfo {

	public CatHealth(String baseUrl) {
		super(baseUrl);
	}

	public CatHealth(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 **/
	public CatHealth format(String format) {
		addParams("format", format);
		return this;
	}

	/**
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 **/
	public CatHealth local(boolean local) {
		addParams("local", local);
		return this;
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public CatHealth masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: list h: Comma-separated list of column names to display
	 **/
	public CatHealth h(String h) {
		addParams("h", h);
		return this;
	}

	/**
	 * param: boolean help: Return help information
	 **/
	public CatHealth help(boolean help) {
		addParams("help", help);
		return this;
	}

	/**
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 **/
	public CatHealth s(String s) {
		addParams("s", s);
		return this;
	}

	/**
	 * param: boolean ts: Set to false to disable timestamping
	 **/
	public CatHealth ts(boolean ts) {
		addParams("ts", ts);
		return this;
	}

	/**
	 * param: boolean v: Verbose mode. Display column headers
	 **/
	public CatHealth v(boolean v) {
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

	public CatHealth setParts() {

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_cat/health
		setUrl("_cat", "health");
		return super.parseUrl(method);

	}
}
