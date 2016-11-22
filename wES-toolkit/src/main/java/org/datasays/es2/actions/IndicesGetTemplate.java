package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
**/
public class IndicesGetTemplate extends ARequestInfo{

	public IndicesGetTemplate(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean flatSettings: Return settings in flat format (default: false)**/
	public IndicesGetTemplate flatSettings(boolean flatSettings){
		addParams("flatSettings", flatSettings);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public IndicesGetTemplate masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public IndicesGetTemplate local(boolean local){
		addParams("local", local);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The comma separated names of the index templates**/
	private String name;
	public IndicesGetTemplate setParts(String name){
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_template/{name}
		if(name != null ){
			setUrl("_template", name);
			return super.parseUrl(method);
		}
		//=>/_template
		setUrl("_template");
		return super.parseUrl(method);

	}
}
