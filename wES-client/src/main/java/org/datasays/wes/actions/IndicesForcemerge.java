package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-forcemerge.html
public class IndicesForcemerge extends RequestInfo{

	public IndicesForcemerge(String baseUrl){
		super(baseUrl);
	}
	public IndicesForcemerge(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: boolean flush: Specify whether the index should be flushed after performing the operation (default: true)
	public IndicesForcemerge flush(boolean flush){
		addParams("flush", flush);
		return this;
	}
	// param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	public IndicesForcemerge ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	// param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	public IndicesForcemerge allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	// param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	public IndicesForcemerge expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	// param: number maxNumSegments: The number of segments the index should be merged into (default: dynamic)
	public IndicesForcemerge maxNumSegments(Number maxNumSegments){
		addParams("maxNumSegments", maxNumSegments);
		return this;
	}
	// param: boolean onlyExpungeDeletes: Specify whether the operation should only expunge deleted documents
	public IndicesForcemerge onlyExpungeDeletes(boolean onlyExpungeDeletes){
		addParams("onlyExpungeDeletes", onlyExpungeDeletes);
		return this;
	}
	// param: boolean waitForMerge: Specify whether the request should block until the merge process is finished (default: true)
	public IndicesForcemerge waitForMerge(boolean waitForMerge){
		addParams("waitForMerge", waitForMerge);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	private String index;
	public IndicesForcemerge setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_forcemerge
		if(index != null ){
			setUrl(index, "_forcemerge");
			return super.parseUrl(method);
		}
		//=>/_forcemerge
		setUrl("_forcemerge");
		return super.parseUrl(method);

	}
}
