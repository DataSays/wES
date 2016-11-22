package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-reroute.html
**/
public class ClusterReroute extends ARequestInfo{

	public ClusterReroute(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean dryRun: Simulate the operation only and return the resulting state**/
	public ClusterReroute dryRun(boolean dryRun){
		addParams("dryRun", dryRun);
		return this;
	}
	/** param: boolean explain: Return an explanation of why the commands can or cannot be executed**/
	public ClusterReroute explain(boolean explain){
		addParams("explain", explain);
		return this;
	}
	/** param: boolean retryFailed: Retries allocation of shards that are blocked due to too many subsequent allocation failures**/
	public ClusterReroute retryFailed(boolean retryFailed){
		addParams("retryFailed", retryFailed);
		return this;
	}
	/** param: list metric: Limit the information returned to the specified metrics. Defaults to all but metadata**/
	public ClusterReroute metric(String metric){
		addParams("metric", metric);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public ClusterReroute masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public ClusterReroute timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** body:The definition of `commands` to perform (`move`, `cancel`, `allocate`)**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public ClusterReroute setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cluster/reroute
		setUrl("_cluster", "reroute");
		return super.parseUrl(method);

	}
}
