package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-update.html
**/
public class Update extends ARequestInfo{

	public Update(String baseUrl){
		super(baseUrl);
	}

	/** param: string waitForActiveShards: Sets the number of shard copies that must be active before proceeding with the update operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)**/
	public Update waitForActiveShards(String waitForActiveShards){
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}
	/** param: list fields: A comma-separated list of fields to return in the response**/
	public Update fields(String fields){
		addParams("fields", fields);
		return this;
	}
	/** param: list source: True or false to return the _source field or not, or a list of fields to return**/
	public Update source(String source){
		addParams("source", source);
		return this;
	}
	/** param: list sourceExclude: A list of fields to exclude from the returned _source field**/
	public Update sourceExclude(String sourceExclude){
		addParams("sourceExclude", sourceExclude);
		return this;
	}
	/** param: list sourceInclude: A list of fields to extract and return from the _source field**/
	public Update sourceInclude(String sourceInclude){
		addParams("sourceInclude", sourceInclude);
		return this;
	}
	/** param: string lang: The script language (default: groovy)**/
	public Update lang(String lang){
		addParams("lang", lang);
		return this;
	}
	/** param: string parent: ID of the parent document. Is is only used for routing and when for the upsert request**/
	public Update parent(String parent){
		addParams("parent", parent);
		return this;
	}
	/** param: enum refresh: If `true` then refresh the effected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.**/
	public Update refresh(EnumRefresh refresh){
		addParams("refresh", refresh);
		return this;
	}
	/** param: number retryOnConflict: Specify how many times should the operation be retried when a conflict occurs (default: 0)**/
	public Update retryOnConflict(Number retryOnConflict){
		addParams("retryOnConflict", retryOnConflict);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public Update routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public Update timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** param: time timestamp: Explicit timestamp for the document**/
	public Update timestamp(long timestamp){
		addParams("timestamp", timestamp);
		return this;
	}
	/** param: time ttl: Expiration time for the document**/
	public Update ttl(long ttl){
		addParams("ttl", ttl);
		return this;
	}
	/** param: number version: Explicit version number for concurrency control**/
	public Update version(Number version){
		addParams("version", version);
		return this;
	}
	/** param: enum versionType: Specific version type**/
	public Update versionType(EnumVersionType versionType){
		addParams("versionType", versionType);
		return this;
	}
	/** body:The request definition using either `script` or partial `doc`**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**Document ID**/
	private String id;
	/**The name of the index**/
	private String index;
	/**The type of the document**/
	private String type;
	public Update setParts(String index,String type,String id){
		this.id=id;
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/{id}/_update
		if(id != null && index != null && type != null ){
			setUrl(index, type, id, "_update");
			return super.parseUrl(method);
		}

		return null;
	}
}
