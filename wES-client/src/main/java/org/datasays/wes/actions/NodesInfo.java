package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-info.html
**/
public class NodesInfo extends ARequestInfo{

	public NodesInfo(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean flatSettings: Return settings in flat format (default: false)**/
	public NodesInfo flatSettings(boolean flatSettings){
		addParams("flatSettings", flatSettings);
		return this;
	}
	/** param: boolean human: Whether to return time and byte values in human-readable format.**/
	public NodesInfo human(boolean human){
		addParams("human", human);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public NodesInfo timeout(long timeout){
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
	/**A comma-separated list of metrics you wish returned. Leave empty to return all.**/
	private String metric;
	public NodesInfo setParts(String node_id,String metric){
		this.node_id=node_id;
		this.metric=metric;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_nodes/{node_id}/{metric}
		if(node_id != null && metric != null ){
			setUrl("_nodes", node_id, metric);
			return super.parseUrl(method);
		}
		//=>/_nodes/{metric}
		if(metric != null ){
			setUrl("_nodes", metric);
			return super.parseUrl(method);
		}
		//=>/_nodes/{node_id}
		if(node_id != null ){
			setUrl("_nodes", node_id);
			return super.parseUrl(method);
		}
		//=>/_nodes
		setUrl("_nodes");
		return super.parseUrl(method);

	}
}
