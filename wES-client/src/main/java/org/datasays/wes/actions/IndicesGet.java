package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumExpandWildcards;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-index.html
 **/
public class IndicesGet extends RequestInfo {

	public IndicesGet(String baseUrl) {
		super(baseUrl);
	}

	public IndicesGet(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 **/
	public IndicesGet local(boolean local) {
		addParams("local", local);
		return this;
	}

	/**
	 * param: boolean ignoreUnavailable: Ignore unavailable indexes (default: false)
	 **/
	public IndicesGet ignoreUnavailable(boolean ignoreUnavailable) {
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}

	/**
	 * param: boolean allowNoIndices: Ignore if a wildcard expression resolves to no concrete indices (default: false)
	 **/
	public IndicesGet allowNoIndices(boolean allowNoIndices) {
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}

	/**
	 * param: enum expandWildcards: Whether wildcard expressions should get expanded to open or closed indices (default: open)
	 **/
	public IndicesGet expandWildcards(EnumExpandWildcards expandWildcards) {
		addParams("expandWildcards", expandWildcards);
		return this;
	}

	/**
	 * param: boolean flatSettings: Return settings in flat format (default: false)
	 **/
	public IndicesGet flatSettings(boolean flatSettings) {
		addParams("flatSettings", flatSettings);
		return this;
	}

	/**
	 * param: boolean human: Whether to return version and creation date values in human-readable format.
	 **/
	public IndicesGet human(boolean human) {
		addParams("human", human);
		return this;
	}

	/**
	 * param: boolean includeDefaults: Whether to return all default setting for each of the indices.
	 **/
	public IndicesGet includeDefaults(boolean includeDefaults) {
		addParams("includeDefaults", includeDefaults);
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
	 * A comma-separated list of index names
	 **/
	private String index;
	/**
	 * A comma-separated list of features
	 **/
	private String feature;

	public IndicesGet setParts(String index, String feature) {
		this.index = index;
		this.feature = feature;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/{index}/{feature}
		if (index != null && feature != null) {
			setUrl(index, feature);
			return super.parseUrl(method);
		}
		//=>/{index}
		if (index != null) {
			setUrl(index);
			return super.parseUrl(method);
		}

		return null;
	}
}
