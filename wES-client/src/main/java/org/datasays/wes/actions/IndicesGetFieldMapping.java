package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumExpandWildcards;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-field-mapping.html
 **/
public class IndicesGetFieldMapping extends RequestInfo {

	public IndicesGetFieldMapping(String baseUrl) {
		super(baseUrl);
	}

	public IndicesGetFieldMapping(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: boolean includeDefaults: Whether the default mapping values should be returned as well
	 **/
	public IndicesGetFieldMapping includeDefaults(boolean includeDefaults) {
		addParams("includeDefaults", includeDefaults);
		return this;
	}

	/**
	 * param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 **/
	public IndicesGetFieldMapping ignoreUnavailable(boolean ignoreUnavailable) {
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}

	/**
	 * param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 **/
	public IndicesGetFieldMapping allowNoIndices(boolean allowNoIndices) {
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}

	/**
	 * param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 **/
	public IndicesGetFieldMapping expandWildcards(EnumExpandWildcards expandWildcards) {
		addParams("expandWildcards", expandWildcards);
		return this;
	}

	/**
	 * param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	 **/
	public IndicesGetFieldMapping local(boolean local) {
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
	 * A comma-separated list of index names
	 **/
	private String index;
	/**
	 * A comma-separated list of document types
	 **/
	private String type;
	/**
	 * A comma-separated list of fields
	 **/
	private String fields;

	public IndicesGetFieldMapping setParts(String index, String type, String fields) {
		this.index = index;
		this.type = type;
		this.fields = fields;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/{index}/_mapping/{type}/field/{fields}
		if (index != null && type != null && fields != null) {
			setUrl(index, "_mapping", type, "field", fields);
			return super.parseUrl(method);
		}
		//=>/_mapping/{type}/field/{fields}
		if (type != null && fields != null) {
			setUrl("_mapping", type, "field", fields);
			return super.parseUrl(method);
		}
		//=>/{index}/_mapping/field/{fields}
		if (index != null && fields != null) {
			setUrl(index, "_mapping", "field", fields);
			return super.parseUrl(method);
		}
		//=>/_mapping/field/{fields}
		if (fields != null) {
			setUrl("_mapping", "field", fields);
			return super.parseUrl(method);
		}

		return null;
	}
}
