package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-nodes.html
 **/
public class CatNodes extends RequestInfo {

	public CatNodes(String baseUrl) {
		super(baseUrl);
	}

	public CatNodes(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 **/
	public CatNodes format(String format) {
		addParams("format", format);
		return this;
	}

	/**
	 * param: boolean fullId: Return the full node ID instead of the shortened version (default: false)
	 **/
	public CatNodes fullId(boolean fullId) {
		addParams("fullId", fullId);
		return this;
	}

	/**
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 **/
	public CatNodes local(boolean local) {
		addParams("local", local);
		return this;
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public CatNodes masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: list h: Comma-separated list of column names to display
	 **/
	public CatNodes h(String h) {
		addParams("h", h);
		return this;
	}

	/**
	 * param: boolean help: Return help information
	 **/
	public CatNodes help(boolean help) {
		addParams("help", help);
		return this;
	}

	/**
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 **/
	public CatNodes s(String s) {
		addParams("s", s);
		return this;
	}

	/**
	 * param: boolean v: Verbose mode. Display column headers
	 **/
	public CatNodes v(boolean v) {
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

	public CatNodes setParts() {

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_cat/nodes
		setUrl("_cat", "nodes");
		return super.parseUrl(method);

	}
}
