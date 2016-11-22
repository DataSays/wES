package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
**/
public class IndicesExistsAlias extends ARequestInfo{

	public IndicesExistsAlias(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public IndicesExistsAlias ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public IndicesExistsAlias allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public IndicesExistsAlias expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public IndicesExistsAlias local(boolean local){
		addParams("local", local);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names to filter aliases**/
	private String index;
	/**A comma-separated list of alias names to return**/
	private String name;
	public IndicesExistsAlias setParts(String index,String name){
		this.index=index;
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"HEAD".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_alias/{name}
		if(index != null && name != null ){
			setUrl(index, "_alias", name);
			return super.parseUrl(method);
		}
		//=>/_alias/{name}
		if(name != null ){
			setUrl("_alias", name);
			return super.parseUrl(method);
		}
		//=>/{index}/_alias
		if(index != null ){
			setUrl(index, "_alias");
			return super.parseUrl(method);
		}

		return null;
	}
}
