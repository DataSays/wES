package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumSize;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-thread-pool.html
 **/
public class CatThreadPool extends RequestInfo {

	public CatThreadPool(String baseUrl) {
		super(baseUrl);
	}

	public CatThreadPool(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: string format: a short version of the Accept header, e.g. json, yaml
	 **/
	public CatThreadPool format(String format) {
		addParams("format", format);
		return this;
	}

	/**
	 * param: enum size: The multiplier in which to display values
	 **/
	public CatThreadPool size(EnumSize size) {
		addParams("size", size);
		return this;
	}

	/**
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 **/
	public CatThreadPool local(boolean local) {
		addParams("local", local);
		return this;
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public CatThreadPool masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: list h: Comma-separated list of column names to display
	 **/
	public CatThreadPool h(String h) {
		addParams("h", h);
		return this;
	}

	/**
	 * param: boolean help: Return help information
	 **/
	public CatThreadPool help(boolean help) {
		addParams("help", help);
		return this;
	}

	/**
	 * param: list s: Comma-separated list of column names or column aliases to sort by
	 **/
	public CatThreadPool s(String s) {
		addParams("s", s);
		return this;
	}

	/**
	 * param: boolean v: Verbose mode. Display column headers
	 **/
	public CatThreadPool v(boolean v) {
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
	 * A comma-separated list of regular-expressions to filter the thread pools in the output
	 **/
	private String thread_pool_patterns;

	public CatThreadPool setParts(String thread_pool_patterns) {
		this.thread_pool_patterns = thread_pool_patterns;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_cat/thread_pool/{thread_pool_patterns}
		if (thread_pool_patterns != null) {
			setUrl("_cat", "thread_pool", thread_pool_patterns);
			return super.parseUrl(method);
		}
		//=>/_cat/thread_pool
		setUrl("_cat", "thread_pool");
		return super.parseUrl(method);

	}
}
