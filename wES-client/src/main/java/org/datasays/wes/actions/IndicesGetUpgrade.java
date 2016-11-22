package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-upgrade.html
**/
public class IndicesGetUpgrade extends ARequestInfo{

	public IndicesGetUpgrade(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public IndicesGetUpgrade ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public IndicesGetUpgrade allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public IndicesGetUpgrade expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: boolean human: Whether to return time and byte values in human-readable format.**/
	public IndicesGetUpgrade human(boolean human){
		addParams("human", human);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	public IndicesGetUpgrade setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_upgrade
		if(index != null ){
			setUrl(index, "_upgrade");
			return super.parseUrl(method);
		}
		//=>/_upgrade
		setUrl("_upgrade");
		return super.parseUrl(method);

	}
}
