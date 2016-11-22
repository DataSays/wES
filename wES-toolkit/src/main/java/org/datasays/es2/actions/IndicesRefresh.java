package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-refresh.html
**/
public class IndicesRefresh extends ARequestInfo{

	public IndicesRefresh(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public IndicesRefresh ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public IndicesRefresh allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public IndicesRefresh expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: boolean force: Force a refresh even if not required**/
	public IndicesRefresh force(boolean force){
		addParams("force", force);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	public IndicesRefresh setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method) && !"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_refresh
		if(index != null ){
			setUrl(index, "_refresh");
			return super.parseUrl(method);
		}
		//=>/_refresh
		setUrl("_refresh");
		return super.parseUrl(method);

	}
}
