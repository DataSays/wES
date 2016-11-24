package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-pending.html
**/
public class ClusterPendingTasks extends ARequestInfo{

	public ClusterPendingTasks(String baseUrl){
		super(baseUrl);
	}
	public ClusterPendingTasks(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public ClusterPendingTasks local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: time masterTimeout: Specify timeout for connection to master**/
	public ClusterPendingTasks masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public ClusterPendingTasks setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cluster/pending_tasks
		setUrl("_cluster", "pending_tasks");
		return super.parseUrl(method);

	}
}
