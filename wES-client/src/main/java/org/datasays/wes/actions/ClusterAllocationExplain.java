package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-allocation-explain.html
**/
public class ClusterAllocationExplain extends ARequestInfo{

	public ClusterAllocationExplain(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean includeYesDecisions: Return 'YES' decisions in explanation (default: false)**/
	public ClusterAllocationExplain includeYesDecisions(boolean includeYesDecisions){
		addParams("includeYesDecisions", includeYesDecisions);
		return this;
	}
	/** param: boolean includeDiskInfo: Return information about disk usage and shard sizes (default: false)**/
	public ClusterAllocationExplain includeDiskInfo(boolean includeDiskInfo){
		addParams("includeDiskInfo", includeDiskInfo);
		return this;
	}
	/** body:The index, shard, and primary flag to explain. Empty means 'explain the first unassigned shard'**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public ClusterAllocationExplain setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cluster/allocation/explain
		setUrl("_cluster", "allocation", "explain");
		return super.parseUrl(method);

	}
}
