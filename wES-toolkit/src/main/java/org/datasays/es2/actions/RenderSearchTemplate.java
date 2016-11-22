package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elasticsearch.org/guide/en/elasticsearch/reference/master/search-template.html
**/
public class RenderSearchTemplate extends ARequestInfo{

	public RenderSearchTemplate(String baseUrl){
		super(baseUrl);
	}

	/** body:The search definition template and its params**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The id of the stored search template**/
	private String id;
	public RenderSearchTemplate setParts(String id){
		this.id=id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_render/template/{id}
		if(id != null ){
			setUrl("_render", "template", id);
			return super.parseUrl(method);
		}
		//=>/_render/template
		setUrl("_render", "template");
		return super.parseUrl(method);

	}
}
