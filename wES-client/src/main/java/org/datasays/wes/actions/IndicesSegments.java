package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/indices-segments.html
public class IndicesSegments extends RequestInfo{

	public IndicesSegments(String baseUrl){
		super(baseUrl);
	}
	public IndicesSegments(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	public IndicesSegments ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	// param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	public IndicesSegments allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	// param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	public IndicesSegments expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	// param: boolean human: Whether to return time and byte values in human-readable format.
	public IndicesSegments human(boolean human){
		addParams("human", human);
		return this;
	}
	// param: boolean verbose: Includes detailed memory usage by Lucene.
	public IndicesSegments verbose(boolean verbose){
		addParams("verbose", verbose);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	private String index;
	public IndicesSegments setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_segments
		if(index != null ){
			setUrl(index, "_segments");
			return super.parseUrl(method);
		}
		//=>/_segments
		setUrl("_segments");
		return super.parseUrl(method);

	}
}
