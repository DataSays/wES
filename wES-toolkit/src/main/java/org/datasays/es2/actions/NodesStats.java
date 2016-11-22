package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-nodes-stats.html
**/
public class NodesStats extends ARequestInfo{

	public NodesStats(String baseUrl){
		super(baseUrl);
	}

	/** param: list completionFields: A comma-separated list of fields for `fielddata` and `suggest` index metric (supports wildcards)**/
	public NodesStats completionFields(String completionFields){
		addParams("completionFields", completionFields);
		return this;
	}
	/** param: list fielddataFields: A comma-separated list of fields for `fielddata` index metric (supports wildcards)**/
	public NodesStats fielddataFields(String fielddataFields){
		addParams("fielddataFields", fielddataFields);
		return this;
	}
	/** param: list fields: A comma-separated list of fields for `fielddata` and `completion` index metric (supports wildcards)**/
	public NodesStats fields(String fields){
		addParams("fields", fields);
		return this;
	}
	/** param: boolean groups: A comma-separated list of search groups for `search` index metric**/
	public NodesStats groups(boolean groups){
		addParams("groups", groups);
		return this;
	}
	/** param: boolean human: Whether to return time and byte values in human-readable format.**/
	public NodesStats human(boolean human){
		addParams("human", human);
		return this;
	}
	/** param: enum level: Return indices stats aggregated at index, node or shard level**/
	public NodesStats level(EnumLevel level){
		addParams("level", level);
		return this;
	}
	/** param: list types: A comma-separated list of document types for the `indexing` index metric**/
	public NodesStats types(String types){
		addParams("types", types);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public NodesStats timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**Limit the information returned to the specified metrics**/
	private String metric;
	/**Limit the information returned for `indices` metric to the specific index metrics. Isn't used if `indices` (or `all`) metric isn't specified.**/
	private String index_metric;
	/**A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes**/
	private String node_id;
	public NodesStats setParts(String metric,String index_metric,String node_id){
		this.metric=metric;
		this.index_metric=index_metric;
		this.node_id=node_id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_nodes/{node_id}/stats/{metric}/{index_metric}
		if(metric != null && index_metric != null && node_id != null ){
			setUrl("_nodes", node_id, "stats", metric, index_metric);
			return super.parseUrl(method);
		}
		//=>/_nodes/stats/{metric}/{index_metric}
		if(metric != null && index_metric != null ){
			setUrl("_nodes", "stats", metric, index_metric);
			return super.parseUrl(method);
		}
		//=>/_nodes/{node_id}/stats/{metric}
		if(metric != null && node_id != null ){
			setUrl("_nodes", node_id, "stats", metric);
			return super.parseUrl(method);
		}
		//=>/_nodes/stats/{metric}
		if(metric != null ){
			setUrl("_nodes", "stats", metric);
			return super.parseUrl(method);
		}
		//=>/_nodes/{node_id}/stats
		if(node_id != null ){
			setUrl("_nodes", node_id, "stats");
			return super.parseUrl(method);
		}
		//=>/_nodes/stats
		setUrl("_nodes", "stats");
		return super.parseUrl(method);

	}
}
