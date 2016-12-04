package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumExpandWildcards;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-upgrade.html
 **/
public class IndicesUpgrade extends RequestInfo {

	public IndicesUpgrade(String baseUrl) {
		super(baseUrl);
	}

	public IndicesUpgrade(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 **/
	public IndicesUpgrade allowNoIndices(boolean allowNoIndices) {
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}

	/**
	 * param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 **/
	public IndicesUpgrade expandWildcards(EnumExpandWildcards expandWildcards) {
		addParams("expandWildcards", expandWildcards);
		return this;
	}

	/**
	 * param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 **/
	public IndicesUpgrade ignoreUnavailable(boolean ignoreUnavailable) {
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}

	/**
	 * param: boolean waitForCompletion: Specify whether the request should block until the all segments are upgraded (default: false)
	 **/
	public IndicesUpgrade waitForCompletion(boolean waitForCompletion) {
		addParams("waitForCompletion", waitForCompletion);
		return this;
	}

	/**
	 * param: boolean onlyAncientSegments: If true, only ancient (an older Lucene major release) segments will be upgraded
	 **/
	public IndicesUpgrade onlyAncientSegments(boolean onlyAncientSegments) {
		addParams("onlyAncientSegments", onlyAncientSegments);
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
	 * A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	 **/
	private String index;

	public IndicesUpgrade setParts(String index) {
		this.index = index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"POST".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/{index}/_upgrade
		if (index != null) {
			setUrl(index, "_upgrade");
			return super.parseUrl(method);
		}
		//=>/_upgrade
		setUrl("_upgrade");
		return super.parseUrl(method);

	}
}
