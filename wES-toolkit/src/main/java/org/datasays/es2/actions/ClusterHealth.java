package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-health.html
**/
public class ClusterHealth extends ARequestInfo{

	public ClusterHealth(String baseUrl){
		super(baseUrl);
	}

	/** param: enum level: Specify the level of detail for returned information**/
	public ClusterHealth level(EnumLevel level){
		addParams("level", level);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public ClusterHealth local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public ClusterHealth masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public ClusterHealth timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** param: string waitForActiveShards: Wait until the specified number of shards is active**/
	public ClusterHealth waitForActiveShards(String waitForActiveShards){
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}
	/** param: string waitForNodes: Wait until the specified number of nodes is available**/
	public ClusterHealth waitForNodes(String waitForNodes){
		addParams("waitForNodes", waitForNodes);
		return this;
	}
	/** param: enum waitForEvents: Wait until all currently queued events with the given priority are processed**/
	public ClusterHealth waitForEvents(EnumWaitForEvents waitForEvents){
		addParams("waitForEvents", waitForEvents);
		return this;
	}
	/** param: boolean waitForNoRelocatingShards: Whether to wait until there are no relocating shards in the cluster**/
	public ClusterHealth waitForNoRelocatingShards(boolean waitForNoRelocatingShards){
		addParams("waitForNoRelocatingShards", waitForNoRelocatingShards);
		return this;
	}
	/** param: enum waitForStatus: Wait until cluster is in a specific state**/
	public ClusterHealth waitForStatus(EnumWaitForStatus waitForStatus){
		addParams("waitForStatus", waitForStatus);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**Limit the information returned to a specific index**/
	private String index;
	public ClusterHealth setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cluster/health/{index}
		if(index != null ){
			setUrl("_cluster", "health", index);
			return super.parseUrl(method);
		}
		//=>/_cluster/health
		setUrl("_cluster", "health");
		return super.parseUrl(method);

	}
}
