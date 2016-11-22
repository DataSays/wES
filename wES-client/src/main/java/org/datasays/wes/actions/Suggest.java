package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-suggesters.html
**/
public class Suggest extends ARequestInfo{

	public Suggest(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public Suggest ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public Suggest allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public Suggest expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public Suggest preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public Suggest routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** body*:The request definition**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names to restrict the operation; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	public Suggest setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method) && !"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_suggest
		if(index != null ){
			setUrl(index, "_suggest");
			return super.parseUrl(method);
		}
		//=>/_suggest
		setUrl("_suggest");
		return super.parseUrl(method);

	}
}
