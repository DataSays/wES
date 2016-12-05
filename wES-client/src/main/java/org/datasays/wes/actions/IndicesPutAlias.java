package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-aliases.html
public class IndicesPutAlias extends RequestInfo{

	public IndicesPutAlias(String baseUrl){
		super(baseUrl);
	}
	public IndicesPutAlias(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time timeout: Explicit timestamp for the document
	public IndicesPutAlias timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// param: time masterTimeout: Specify timeout for connection to master
	public IndicesPutAlias masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// body:The settings for the alias, such as `routing` or `filter`
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names the alias should point to (supports wildcards); use `_all` to perform the operation on all indices.
	private String index;
	//The name of the alias to be created or updated
	private String name;
	public IndicesPutAlias setParts(String index,String name){
		this.index=index;
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"PUT".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
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
