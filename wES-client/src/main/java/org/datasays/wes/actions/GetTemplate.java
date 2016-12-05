package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-template.html
public class GetTemplate extends RequestInfo{

	public GetTemplate(String baseUrl){
		super(baseUrl);
	}
	public GetTemplate(HttpUrl baseUrl){
		super(baseUrl);
	}

	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//Template ID
	private String id;
	public GetTemplate setParts(String id){
		this.id=id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_search/template/{id}
		if(id != null ){
			setUrl("_search", "template", id);
			return super.parseUrl(method);
		}

		return null;
	}
}
