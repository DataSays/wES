package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-open-close.html
public class IndicesOpen extends RequestInfo{

	public IndicesOpen(String baseUrl){
		super(baseUrl);
	}
	public IndicesOpen(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time timeout: Explicit operation timeout
	public IndicesOpen timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// param: time masterTimeout: Specify timeout for connection to master
	public IndicesOpen masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	public IndicesOpen ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	// param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	public IndicesOpen allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	// param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	public IndicesOpen expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma separated list of indices to open
	private String index;
	public IndicesOpen setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_open
		if(index != null ){
			setUrl(index, "_open");
			return super.parseUrl(method);
		}

		return null;
	}
}
