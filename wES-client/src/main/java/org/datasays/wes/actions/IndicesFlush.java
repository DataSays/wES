package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-flush.html
public class IndicesFlush extends RequestInfo{

	public IndicesFlush(String baseUrl){
		super(baseUrl);
	}
	public IndicesFlush(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: boolean force: Whether a flush should be forced even if it is not necessarily needed ie. if no changes will be committed to the index. This is useful if transaction log IDs should be incremented even if no uncommitted changes are present. (This setting can be considered as internal)
	public IndicesFlush force(boolean force){
		addParams("force", force);
		return this;
	}
	// param: boolean waitIfOngoing: If set to true the flush operation will block until the flush can be executed if another flush operation is already executing. The default is true. If set to false the flush will be skipped iff if another flush operation is already running.
	public IndicesFlush waitIfOngoing(boolean waitIfOngoing){
		addParams("waitIfOngoing", waitIfOngoing);
		return this;
	}
	// param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	public IndicesFlush ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	// param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	public IndicesFlush allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	// param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	public IndicesFlush expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names; use `_all` or empty string for all indices
	private String index;
	public IndicesFlush setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method) && !"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_flush
		if(index != null ){
			setUrl(index, "_flush");
			return super.parseUrl(method);
		}
		//=>/_flush
		setUrl("_flush");
		return super.parseUrl(method);

	}
}
