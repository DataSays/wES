package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-create-index.html
**/
public class IndicesCreate extends ARequestInfo{

	public IndicesCreate(String baseUrl){
		super(baseUrl);
	}

	/** param: string waitForActiveShards: Set the number of active shards to wait for before the operation returns.**/
	public IndicesCreate waitForActiveShards(String waitForActiveShards){
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public IndicesCreate timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** param: time masterTimeout: Specify timeout for connection to master**/
	public IndicesCreate masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: boolean updateAllTypes: Whether to update the mapping for all fields with the same name across all types or not**/
	public IndicesCreate updateAllTypes(boolean updateAllTypes){
		addParams("updateAllTypes", updateAllTypes);
		return this;
	}
	/** body:The configuration for the index (`settings` and `mappings`)**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The name of the index**/
	private String index;
	public IndicesCreate setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"PUT".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}
		if(index != null ){
			setUrl(index);
			return super.parseUrl(method);
		}

		return null;
	}
}
