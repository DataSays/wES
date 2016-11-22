package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-shards.html
**/
public class SearchShards extends ARequestInfo{

	public SearchShards(String baseUrl){
		super(baseUrl);
	}

	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public SearchShards preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public SearchShards routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public SearchShards local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public SearchShards ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public SearchShards allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public SearchShards expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	/**A comma-separated list of document types to search; leave empty to perform the operation on all types**/
	private String type;
	public SearchShards setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/_search_shards
		if(index != null && type != null ){
			setUrl(index, type, "_search_shards");
			return super.parseUrl(method);
		}
		//=>/{index}/_search_shards
		if(index != null ){
			setUrl(index, "_search_shards");
			return super.parseUrl(method);
		}
		//=>/_search_shards
		setUrl("_search_shards");
		return super.parseUrl(method);

	}
}
