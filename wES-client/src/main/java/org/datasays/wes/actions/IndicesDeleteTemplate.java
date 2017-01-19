package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/indices-templates.html
public class IndicesDeleteTemplate extends RequestInfo{

	public IndicesDeleteTemplate(String baseUrl){
		super(baseUrl);
	}
	public IndicesDeleteTemplate(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time timeout: Explicit operation timeout
	public IndicesDeleteTemplate timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// param: time masterTimeout: Specify timeout for connection to master
	public IndicesDeleteTemplate masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//The name of the template
	private String name;
	public IndicesDeleteTemplate setParts(String name){
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"DELETE".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_template/{name}
		if(name != null ){
			setUrl("_template", name);
			return super.parseUrl(method);
		}

		return null;
	}
}
