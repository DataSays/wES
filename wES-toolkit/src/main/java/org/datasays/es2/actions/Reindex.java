package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-reindex.html
**/
public class Reindex extends ARequestInfo{

	public Reindex(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean refresh: Should the effected indexes be refreshed?**/
	public Reindex refresh(boolean refresh){
		addParams("refresh", refresh);
		return this;
	}
	/** param: time timeout: Time each individual bulk request should wait for shards that are unavailable.**/
	public Reindex timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** param: string waitForActiveShards: Sets the number of shard copies that must be active before proceeding with the reindex operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)**/
	public Reindex waitForActiveShards(String waitForActiveShards){
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}
	/** param: boolean waitForCompletion: Should the request should block until the reindex is complete.**/
	public Reindex waitForCompletion(boolean waitForCompletion){
		addParams("waitForCompletion", waitForCompletion);
		return this;
	}
	/** param: number requestsPerSecond: The throttle to set on this request in sub-requests per second. -1 means no throttle.**/
	public Reindex requestsPerSecond(Number requestsPerSecond){
		addParams("requestsPerSecond", requestsPerSecond);
		return this;
	}
	/** param: integer slices: The number of slices this task should be divided into. Defaults to 1 meaning the task isn't sliced into subtasks.**/
	public Reindex slices(Integer slices){
		addParams("slices", slices);
		return this;
	}
	/** body*:The search definition using the Query DSL and the prototype for the index request.**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public Reindex setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_reindex
		setUrl("_reindex");
		return super.parseUrl(method);

	}
}
