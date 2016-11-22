package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/current/search-template.html
**/
public class SearchTemplate extends ARequestInfo{

	public SearchTemplate(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public SearchTemplate ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public SearchTemplate allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public SearchTemplate expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public SearchTemplate preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: list routing: A comma-separated list of specific routing values**/
	public SearchTemplate routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: time scroll: Specify how long a consistent view of the index should be maintained for scrolled search**/
	public SearchTemplate scroll(long scroll){
		addParams("scroll", scroll);
		return this;
	}
	/** param: enum searchType: Search operation type**/
	public SearchTemplate searchType(EnumSearchType searchType){
		addParams("searchType", searchType);
		return this;
	}
	/** param: boolean explain: Specify whether to return detailed information about score computation as part of a hit**/
	public SearchTemplate explain(boolean explain){
		addParams("explain", explain);
		return this;
	}
	/** param: boolean profile: Specify whether to profile the query execution**/
	public SearchTemplate profile(boolean profile){
		addParams("profile", profile);
		return this;
	}
	/** body:The search definition template and its params**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	/**A comma-separated list of document types to search; leave empty to perform the operation on all types**/
	private String type;
	public SearchTemplate setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/_search/template
		if(index != null && type != null ){
			setUrl(index, type, "_search", "template");
			return super.parseUrl(method);
		}
		//=>/{index}/_search/template
		if(index != null ){
			setUrl(index, "_search", "template");
			return super.parseUrl(method);
		}
		//=>/_search/template
		setUrl("_search", "template");
		return super.parseUrl(method);

	}
}
