package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
**/
public class IndicesExistsTemplate extends ARequestInfo{

	public IndicesExistsTemplate(String baseUrl){
		super(baseUrl);
	}

	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public IndicesExistsTemplate masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public IndicesExistsTemplate local(boolean local){
		addParams("local", local);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The name of the template**/
	private String name;
	public IndicesExistsTemplate setParts(String name){
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"HEAD".equalsIgnoreCase(method)){
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
