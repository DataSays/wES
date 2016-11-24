package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-delete.html
**/
public class Delete extends ARequestInfo{

	public Delete(String baseUrl){
		super(baseUrl);
	}
	public Delete(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: string waitForActiveShards: Sets the number of shard copies that must be active before proceeding with the delete operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)**/
	public Delete waitForActiveShards(String waitForActiveShards){
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}
	/** param: string parent: ID of parent document**/
	public Delete parent(String parent){
		addParams("parent", parent);
		return this;
	}
	/** param: enum refresh: If `true` then refresh the effected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.**/
	public Delete refresh(EnumRefresh refresh){
		addParams("refresh", refresh);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public Delete routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public Delete timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** param: number version: Explicit version number for concurrency control**/
	public Delete version(Number version){
		addParams("version", version);
		return this;
	}
	/** param: enum versionType: Specific version type**/
	public Delete versionType(EnumVersionType versionType){
		addParams("versionType", versionType);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The document ID**/
	private String id;
	/**The name of the index**/
	private String index;
	/**The type of the document**/
	private String type;
	public Delete setParts(String index,String type,String id){
		this.id=id;
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"DELETE".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/{id}
		if(id != null && index != null && type != null ){
			setUrl(index, type, id);
			return super.parseUrl(method);
		}

		return null;
	}
}
