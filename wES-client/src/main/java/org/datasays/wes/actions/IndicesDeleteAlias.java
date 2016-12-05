package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
public class IndicesDeleteAlias extends RequestInfo{

	public IndicesDeleteAlias(String baseUrl){
		super(baseUrl);
	}
	public IndicesDeleteAlias(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time timeout: Explicit timestamp for the document
	public IndicesDeleteAlias timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// param: time masterTimeout: Specify timeout for connection to master
	public IndicesDeleteAlias masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names (supports wildcards); use `_all` for all indices
	private String index;
	//A comma-separated list of aliases to delete (supports wildcards); use `_all` to delete all aliases for the specified indices.
	private String name;
	public IndicesDeleteAlias setParts(String index,String name){
		this.index=index;
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"DELETE".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_alias/{name}
		if(index != null && name != null ){
			setUrl(index, "_alias", name);
			return super.parseUrl(method);
		}
		//=>/{index}/_aliases/{name}
		if(index != null && name != null ){
			setUrl(index, "_aliases", name);
			return super.parseUrl(method);
		}

		return null;
	}
}
