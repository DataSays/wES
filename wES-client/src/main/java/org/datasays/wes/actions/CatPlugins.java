package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-plugins.html
public class CatPlugins extends RequestInfo{

	public CatPlugins(String baseUrl){
		super(baseUrl);
	}
	public CatPlugins(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: string format: a short version of the Accept header, e.g. json, yaml
	public CatPlugins format(String format){
		addParams("format", format);
		return this;
	}
	// param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	public CatPlugins local(boolean local){
		addParams("local", local);
		return this;
	}
	// param: time masterTimeout: Explicit operation timeout for connection to master node
	public CatPlugins masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: list h: Comma-separated list of column names to display
	public CatPlugins h(String h){
		addParams("h", h);
		return this;
	}
	// param: boolean help: Return help information
	public CatPlugins help(boolean help){
		addParams("help", help);
		return this;
	}
	// param: list s: Comma-separated list of column names or column aliases to sort by
	public CatPlugins s(String s){
		addParams("s", s);
		return this;
	}
	// param: boolean v: Verbose mode. Display column headers
	public CatPlugins v(boolean v){
		addParams("v", v);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public CatPlugins setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/plugins
		setUrl("_cat", "plugins");
		return super.parseUrl(method);

	}
}
