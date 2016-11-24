package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;

/**
* documentation: http://www.elastic.co/guide/
**/
public class Info extends ARequestInfo{

	public Info(String baseUrl){
		super(baseUrl);
	}
	public Info(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public Info setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/
		setUrl("");
		return super.parseUrl(method);

	}
}
