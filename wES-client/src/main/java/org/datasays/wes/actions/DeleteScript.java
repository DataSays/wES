package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-scripting.html
public class DeleteScript extends RequestInfo{

	public DeleteScript(String baseUrl){
		super(baseUrl);
	}
	public DeleteScript(HttpUrl baseUrl){
		super(baseUrl);
	}

	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//Script ID
	private String id;
	//Script language
	private String lang;
	public DeleteScript setParts(String id,String lang){
		this.id=id;
		this.lang=lang;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"DELETE".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_scripts/{lang}/{id}
		if(id != null && lang != null ){
			setUrl("_scripts", lang, id);
			return super.parseUrl(method);
		}

		return null;
	}
}
