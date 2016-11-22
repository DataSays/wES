package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-mapping.html
**/
public class IndicesGetMapping extends ARequestInfo{

	public IndicesGetMapping(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public IndicesGetMapping ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public IndicesGetMapping allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public IndicesGetMapping expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public IndicesGetMapping local(boolean local){
		addParams("local", local);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names**/
	private String index;
	/**A comma-separated list of document types**/
	private String type;
	public IndicesGetMapping setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_mapping/{type}
		if(index != null && type != null ){
			setUrl(index, "_mapping", type);
			return super.parseUrl(method);
		}
		//=>/_mapping/{type}
		if(type != null ){
			setUrl("_mapping", type);
			return super.parseUrl(method);
		}
		//=>/{index}/_mapping
		if(index != null ){
			setUrl(index, "_mapping");
			return super.parseUrl(method);
		}
		//=>/_mapping
		setUrl("_mapping");
		return super.parseUrl(method);

	}
}
