package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-put-mapping.html
**/
public class IndicesPutMapping extends ARequestInfo{

	public IndicesPutMapping(String baseUrl){
		super(baseUrl);
	}

	/** param: time timeout: Explicit operation timeout**/
	public IndicesPutMapping timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** param: time masterTimeout: Specify timeout for connection to master**/
	public IndicesPutMapping masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public IndicesPutMapping ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public IndicesPutMapping allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public IndicesPutMapping expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: boolean updateAllTypes: Whether to update the mapping for all fields with the same name across all types or not**/
	public IndicesPutMapping updateAllTypes(boolean updateAllTypes){
		addParams("updateAllTypes", updateAllTypes);
		return this;
	}
	/** body*:The mapping definition**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names the mapping should be added to (supports wildcards); use `_all` or omit to add the mapping on all indices.**/
	private String index;
	/**The name of the document type**/
	private String type;
	public IndicesPutMapping setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"PUT".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_mapping/{type}
		if(index != null && type != null ){
			setUrl(index, "_mapping", type);
			return super.parseUrl(method);
		}
		//=>/{index}/_mappings/{type}
		if(index != null && type != null ){
			setUrl(index, "_mappings", type);
			return super.parseUrl(method);
		}
		//=>/{index}/{type}/_mapping
		if(index != null && type != null ){
			setUrl(index, type, "_mapping");
			return super.parseUrl(method);
		}
		//=>/{index}/{type}/_mappings
		if(index != null && type != null ){
			setUrl(index, type, "_mappings");
			return super.parseUrl(method);
		}
		//=>/_mapping/{type}
		if(type != null ){
			setUrl("_mapping", type);
			return super.parseUrl(method);
		}
		//=>/_mappings/{type}
		if(type != null ){
			setUrl("_mappings", type);
			return super.parseUrl(method);
		}

		return null;
	}
}
