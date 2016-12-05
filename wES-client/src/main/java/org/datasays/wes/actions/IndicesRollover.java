package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-rollover-index.html
public class IndicesRollover extends RequestInfo{

	public IndicesRollover(String baseUrl){
		super(baseUrl);
	}
	public IndicesRollover(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time timeout: Explicit operation timeout
	public IndicesRollover timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// param: boolean dryRun: If set to true the rollover action will only be validated but not actually performed even if a condition matches. The default is false
	public IndicesRollover dryRun(boolean dryRun){
		addParams("dryRun", dryRun);
		return this;
	}
	// param: time masterTimeout: Specify timeout for connection to master
	public IndicesRollover masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: string waitForActiveShards: Set the number of active shards to wait for on the newly created rollover index before the operation returns.
	public IndicesRollover waitForActiveShards(String waitForActiveShards){
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}
	// body:The conditions that needs to be met for executing rollover
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//The name of the alias to rollover
	private String alias;
	//The name of the rollover index
	private String new_index;
	public IndicesRollover setParts(String alias,String new_index){
		this.alias=alias;
		this.new_index=new_index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{alias}/_rollover/{new_index}
		if(alias != null && new_index != null ){
			setUrl(alias, "_rollover", new_index);
			return super.parseUrl(method);
		}
		//=>/{alias}/_rollover
		if(alias != null ){
			setUrl(alias, "_rollover");
			return super.parseUrl(method);
		}

		return null;
	}
}
