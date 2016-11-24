package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-templates.html
**/
public class CatTemplates extends ARequestInfo{

	public CatTemplates(String baseUrl){
		super(baseUrl);
	}
	public CatTemplates(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: string format: a short version of the Accept header, e.g. json, yaml**/
	public CatTemplates format(String format){
		addParams("format", format);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public CatTemplates local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public CatTemplates masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: list h: Comma-separated list of column names to display**/
	public CatTemplates h(String h){
		addParams("h", h);
		return this;
	}
	/** param: boolean help: Return help information**/
	public CatTemplates help(boolean help){
		addParams("help", help);
		return this;
	}
	/** param: list s: Comma-separated list of column names or column aliases to sort by**/
	public CatTemplates s(String s){
		addParams("s", s);
		return this;
	}
	/** param: boolean v: Verbose mode. Display column headers**/
	public CatTemplates v(boolean v){
		addParams("v", v);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A pattern that returned template names must match**/
	private String name;
	public CatTemplates setParts(String name){
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/templates/{name}
		if(name != null ){
			setUrl("_cat", "templates", name);
			return super.parseUrl(method);
		}
		//=>/_cat/templates
		setUrl("_cat", "templates");
		return super.parseUrl(method);

	}
}
