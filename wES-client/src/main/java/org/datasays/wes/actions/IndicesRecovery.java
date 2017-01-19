package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/indices-recovery.html
public class IndicesRecovery extends RequestInfo{

	public IndicesRecovery(String baseUrl){
		super(baseUrl);
	}
	public IndicesRecovery(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: boolean detailed: Whether to display detailed information about shard recovery
	public IndicesRecovery detailed(boolean detailed){
		addParams("detailed", detailed);
		return this;
	}
	// param: boolean activeOnly: Display only those recoveries that are currently on-going
	public IndicesRecovery activeOnly(boolean activeOnly){
		addParams("activeOnly", activeOnly);
		return this;
	}
	// param: boolean human: Whether to return time and byte values in human-readable format.
	public IndicesRecovery human(boolean human){
		addParams("human", human);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	private String index;
	public IndicesRecovery setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_recovery
		if(index != null ){
			setUrl(index, "_recovery");
			return super.parseUrl(method);
		}
		//=>/_recovery
		setUrl("_recovery");
		return super.parseUrl(method);

	}
}
