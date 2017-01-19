package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/docs-bulk.html
public class Bulk extends RequestInfo{

	public Bulk(String baseUrl){
		super(baseUrl);
	}
	public Bulk(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: string waitForActiveShards: Sets the number of shard copies that must be active before proceeding with the bulk operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	public Bulk waitForActiveShards(String waitForActiveShards){
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}
	// param: enum refresh: If `true` then refresh the effected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.
	public Bulk refresh(EnumRefresh refresh){
		addParams("refresh", refresh);
		return this;
	}
	// param: string routing: Specific routing value
	public Bulk routing(String routing){
		addParams("routing", routing);
		return this;
	}
	// param: time timeout: Explicit operation timeout
	public Bulk timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// param: string type: Default document type for items which don't provide one
	public Bulk type(String type){
		addParams("type", type);
		return this;
	}
	// param: list fields: Default comma-separated list of fields to return in the response for updates, can be overridden on each sub-request
	public Bulk fields(String fields){
		addParams("fields", fields);
		return this;
	}
	// param: list source: True or false to return the _source field or not, or default list of fields to return, can be overridden on each sub-request
	public Bulk source(String source){
		addParams("source", source);
		return this;
	}
	// param: list sourceExclude: Default list of fields to exclude from the returned _source field, can be overridden on each sub-request
	public Bulk sourceExclude(String sourceExclude){
		addParams("sourceExclude", sourceExclude);
		return this;
	}
	// param: list sourceInclude: Default list of fields to extract and return from the _source field, can be overridden on each sub-request
	public Bulk sourceInclude(String sourceInclude){
		addParams("sourceInclude", sourceInclude);
		return this;
	}
	// param: string pipeline: The pipeline id to preprocess incoming documents with
	public Bulk pipeline(String pipeline){
		addParams("pipeline", pipeline);
		return this;
	}
	// body*:The operation definition and data (action-data pairs), separated by newlines
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//Default index for items which don't provide one
	private String index;
	//Default document type for items which don't provide one
	private String type;
	public Bulk setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method) && !"PUT".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/_bulk
		if(index != null && type != null ){
			setUrl(index, type, "_bulk");
			return super.parseUrl(method);
		}
		//=>/{index}/_bulk
		if(index != null ){
			setUrl(index, "_bulk");
			return super.parseUrl(method);
		}
		//=>/_bulk
		setUrl("_bulk");
		return super.parseUrl(method);

	}
}
