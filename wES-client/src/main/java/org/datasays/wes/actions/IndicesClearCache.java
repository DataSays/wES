package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-clearcache.html
**/
public class IndicesClearCache extends ARequestInfo{

	public IndicesClearCache(String baseUrl){
		super(baseUrl);
	}
	public IndicesClearCache(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: boolean fieldData: Clear field data**/
	public IndicesClearCache fieldData(boolean fieldData){
		addParams("fieldData", fieldData);
		return this;
	}
	/** param: boolean fielddata: Clear field data**/
	public IndicesClearCache fielddata(boolean fielddata){
		addParams("fielddata", fielddata);
		return this;
	}
	/** param: list fields: A comma-separated list of fields to clear when using the `field_data` parameter (default: all)**/
	public IndicesClearCache fields(String fields){
		addParams("fields", fields);
		return this;
	}
	/** param: boolean query: Clear query caches**/
	public IndicesClearCache query(boolean query){
		addParams("query", query);
		return this;
	}
	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public IndicesClearCache ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public IndicesClearCache allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public IndicesClearCache expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: list index: A comma-separated list of index name to limit the operation**/
	public IndicesClearCache index(String index){
		addParams("index", index);
		return this;
	}
	/** param: boolean recycler: Clear the recycler cache**/
	public IndicesClearCache recycler(boolean recycler){
		addParams("recycler", recycler);
		return this;
	}
	/** param: boolean request: Clear request cache**/
	public IndicesClearCache request(boolean request){
		addParams("request", request);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index name to limit the operation**/
	private String index;
	public IndicesClearCache setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method) && !"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_cache/clear
		if(index != null ){
			setUrl(index, "_cache", "clear");
			return super.parseUrl(method);
		}
		//=>/_cache/clear
		setUrl("_cache", "clear");
		return super.parseUrl(method);

	}
}
