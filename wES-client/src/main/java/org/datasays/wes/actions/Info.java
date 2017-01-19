package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/
public class Info extends RequestInfo{

	public Info(String baseUrl){
		super(baseUrl);
	}
	public Info(HttpUrl baseUrl){
		super(baseUrl);
	}

	// body:null
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
