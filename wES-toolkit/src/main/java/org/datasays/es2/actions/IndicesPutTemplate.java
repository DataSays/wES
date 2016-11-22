package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-templates.html
**/
public class IndicesPutTemplate extends ARequestInfo{

	public IndicesPutTemplate(String baseUrl){
		super(baseUrl);
	}

	/** param: number order: The order for this template when merging multiple matching ones (higher numbers are merged later, overriding the lower numbers)**/
	public IndicesPutTemplate order(Number order){
		addParams("order", order);
		return this;
	}
	/** param: boolean create: Whether the index template should only be added if new or can also replace an existing one**/
	public IndicesPutTemplate create(boolean create){
		addParams("create", create);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public IndicesPutTemplate timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** param: time masterTimeout: Specify timeout for connection to master**/
	public IndicesPutTemplate masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: boolean flatSettings: Return settings in flat format (default: false)**/
	public IndicesPutTemplate flatSettings(boolean flatSettings){
		addParams("flatSettings", flatSettings);
		return this;
	}
	/** body*:The template definition**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The name of the template**/
	private String name;
	public IndicesPutTemplate setParts(String name){
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"PUT".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
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
