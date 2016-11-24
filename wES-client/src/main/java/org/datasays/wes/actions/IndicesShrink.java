package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-shrink-index.html
**/
public class IndicesShrink extends ARequestInfo{

	public IndicesShrink(String baseUrl){
		super(baseUrl);
	}
	public IndicesShrink(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: time timeout: Explicit operation timeout**/
	public IndicesShrink timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** param: time masterTimeout: Specify timeout for connection to master**/
	public IndicesShrink masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: string waitForActiveShards: Set the number of active shards to wait for on the shrunken index before the operation returns.**/
	public IndicesShrink waitForActiveShards(String waitForActiveShards){
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}
	/** body:The configuration for the target index (`settings` and `aliases`)**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The name of the source index to shrink**/
	private String index;
	/**The name of the target index to shrink into**/
	private String target;
	public IndicesShrink setParts(String index,String target){
		this.index=index;
		this.target=target;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"PUT".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_shrink/{target}
		if(index != null && target != null ){
			setUrl(index, "_shrink", target);
			return super.parseUrl(method);
		}

		return null;
	}
}
