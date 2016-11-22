package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-scripting.html
**/
public class GetScript extends ARequestInfo{

	public GetScript(String baseUrl){
		super(baseUrl);
	}

	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**Script ID**/
	private String id;
	/**Script language**/
	private String lang;
	public GetScript setParts(String id,String lang){
		this.id=id;
		this.lang=lang;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
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
