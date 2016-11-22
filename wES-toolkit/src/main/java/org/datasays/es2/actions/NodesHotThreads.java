package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-hot-threads.html
**/
public class NodesHotThreads extends ARequestInfo{

	public NodesHotThreads(String baseUrl){
		super(baseUrl);
	}

	/** param: time interval: The interval for the second sampling of threads**/
	public NodesHotThreads interval(long interval){
		addParams("interval", interval);
		return this;
	}
	/** param: number snapshots: Number of samples of thread stacktrace (default: 10)**/
	public NodesHotThreads snapshots(Number snapshots){
		addParams("snapshots", snapshots);
		return this;
	}
	/** param: number threads: Specify the number of threads to provide information for (default: 3)**/
	public NodesHotThreads threads(Number threads){
		addParams("threads", threads);
		return this;
	}
	/** param: boolean ignoreIdleThreads: Don't show threads that are in known-idle places, such as waiting on a socket select or pulling from an empty task queue (default: true)**/
	public NodesHotThreads ignoreIdleThreads(boolean ignoreIdleThreads){
		addParams("ignoreIdleThreads", ignoreIdleThreads);
		return this;
	}
	/** param: enum type: The type to sample (default: cpu)**/
	public NodesHotThreads type(EnumType type){
		addParams("type", type);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public NodesHotThreads timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes**/
	private String node_id;
	public NodesHotThreads setParts(String node_id){
		this.node_id=node_id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cluster/nodes/{node_id}/hot_threads
		if(node_id != null ){
			setUrl("_cluster", "nodes", node_id, "hot_threads");
			return super.parseUrl(method);
		}
		//=>/_cluster/nodes/{node_id}/hotthreads
		if(node_id != null ){
			setUrl("_cluster", "nodes", node_id, "hotthreads");
			return super.parseUrl(method);
		}
		//=>/_nodes/{node_id}/hot_threads
		if(node_id != null ){
			setUrl("_nodes", node_id, "hot_threads");
			return super.parseUrl(method);
		}
		//=>/_nodes/{node_id}/hotthreads
		if(node_id != null ){
			setUrl("_nodes", node_id, "hotthreads");
			return super.parseUrl(method);
		}
		//=>/_cluster/nodes/hot_threads
		setUrl("_cluster", "nodes", "hot_threads");
		return super.parseUrl(method);
		//=>/_cluster/nodes/hotthreads
		//=>/_nodes/hot_threads
		//=>/_nodes/hotthreads

	}
}
