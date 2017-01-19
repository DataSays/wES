package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/indices-shards-stores.html
public class IndicesShardStores extends RequestInfo{

	public IndicesShardStores(String baseUrl){
		super(baseUrl);
	}
	public IndicesShardStores(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: list status: A comma-separated list of statuses used to filter on shards to get store information for
	public IndicesShardStores status(String status){
		addParams("status", status);
		return this;
	}
	// param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	public IndicesShardStores ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	// param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	public IndicesShardStores allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	// param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	public IndicesShardStores expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	private String index;
	public IndicesShardStores setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_shard_stores
		if(index != null ){
			setUrl(index, "_shard_stores");
			return super.parseUrl(method);
		}
		//=>/_shard_stores
		setUrl("_shard_stores");
		return super.parseUrl(method);

	}
}
