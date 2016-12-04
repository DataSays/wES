package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-alias.html
 **/
public class CatAliases extends RequestInfo {

	public CatAliases(String baseUrl) {
		super(baseUrl);
	}

	public CatAliases(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 **/
	public CatAliases format(String format) {
		addParams("format", format);
		return this;
	}

	/**
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 **/
	public CatAliases local(boolean local) {
		addParams("local", local);
		return this;
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public CatAliases masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: list h: Comma-separated list of column names to display
	 **/
	public CatAliases h(String h) {
		addParams("h", h);
		return this;
	}

	/**
	 * param: boolean help: Return help information
	 **/
	public CatAliases help(boolean help) {
		addParams("help", help);
		return this;
	}

	/**
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 **/
	public CatAliases s(String s) {
		addParams("s", s);
		return this;
	}

	/**
	 * param: boolean v: Verbose mode. Display column headers
	 **/
	public CatAliases v(boolean v) {
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

	/**
	 * A comma-separated list of alias names to return
	 **/
	private String name;

	public CatAliases setParts(String name) {
		this.name = name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_cat/aliases/{name}
		if (name != null) {
			setUrl("_cat", "aliases", name);
			return super.parseUrl(method);
		}
		//=>/_cat/aliases
		setUrl("_cat", "aliases");
		return super.parseUrl(method);

	}
}
