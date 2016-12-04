package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumSearchType;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/current/search-multi-search.html
 **/
public class MsearchTemplate extends RequestInfo {

	public MsearchTemplate(String baseUrl) {
		super(baseUrl);
	}

	public MsearchTemplate(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: enum searchType: Search operation type
	 **/
	public MsearchTemplate searchType(EnumSearchType searchType) {
		addParams("searchType", searchType);
		return this;
	}

	/**
	 * body*:The request definitions (metadata-search request definition pairs), separated by newlines
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**
	 * A comma-separated list of index names to use as default
	 **/
	private String index;
	/**
	 * A comma-separated list of document types to use as default
	 **/
	private String type;

	public MsearchTemplate setParts(String index, String type) {
		this.index = index;
		this.type = type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/{index}/{type}/_msearch/template
		if (index != null && type != null) {
			setUrl(index, type, "_msearch", "template");
			return super.parseUrl(method);
		}
		//=>/{index}/_msearch/template
		if (index != null) {
			setUrl(index, "_msearch", "template");
			return super.parseUrl(method);
		}
		//=>/_msearch/template
		setUrl("_msearch", "template");
		return super.parseUrl(method);

	}
}
