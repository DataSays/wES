package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-reindex.html
**/
public class ReindexRethrottle extends ARequestInfo{

	public ReindexRethrottle(String baseUrl){
		super(baseUrl);
	}

	/** param: number requestsPerSecond: The throttle to set on this request in floating sub-requests per second. -1 means set no throttle.**/
	public ReindexRethrottle requestsPerSecond(Number requestsPerSecond){
		addParams("requestsPerSecond", requestsPerSecond);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The task id to rethrottle**/
	private String task_id;
	public ReindexRethrottle setParts(String task_id){
		this.task_id=task_id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_delete_by_query/{task_id}/_rethrottle
		if(task_id != null ){
			setUrl("_delete_by_query", task_id, "_rethrottle");
			return super.parseUrl(method);
		}
		//=>/_reindex/{task_id}/_rethrottle
		if(task_id != null ){
			setUrl("_reindex", task_id, "_rethrottle");
			return super.parseUrl(method);
		}
		//=>/_update_by_query/{task_id}/_rethrottle
		if(task_id != null ){
			setUrl("_update_by_query", task_id, "_rethrottle");
			return super.parseUrl(method);
		}

		return null;
	}
}
