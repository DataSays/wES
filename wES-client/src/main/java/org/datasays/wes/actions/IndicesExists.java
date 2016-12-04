package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumExpandWildcards;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-exists.html
 **/
public class IndicesExists extends RequestInfo {

	public IndicesExists(String baseUrl) {
		super(baseUrl);
	}

	public IndicesExists(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 **/
	public IndicesExists expandWildcards(EnumExpandWildcards expandWildcards) {
		addParams("expandWildcards", expandWildcards);
		return this;
	}

	/**
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 **/
	public IndicesExists local(boolean local) {
		addParams("local", local);
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
	 * A comma-separated list of indices to check
	 **/
	private String index;

	public IndicesExists setParts(String index) {
		this.index = index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"HEAD".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/{index}
		if (index != null) {
			setUrl(index);
			return super.parseUrl(method);
		}

		return null;
	}
}
