package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-template.html
**/
public class DeleteTemplate extends ARequestInfo{

	public DeleteTemplate(String baseUrl){
		super(baseUrl);
	}

	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**Template ID**/
	private String id;
	public DeleteTemplate setParts(String id){
		this.id=id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"DELETE".equalsIgnoreCase(method)){
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
