package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-count.html
public class CatCount extends RequestInfo{

	public CatCount(String baseUrl){
		super(baseUrl);
	}
	public CatCount(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: string format: a short version of the Accept header, e.g. json, yaml
	public CatCount format(String format){
		addParams("format", format);
		return this;
	}
	// param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	public CatCount local(boolean local){
		addParams("local", local);
		return this;
	}
	// param: time masterTimeout: Explicit operation timeout for connection to master node
	public CatCount masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: list h: Comma-separated list of column names to display
	public CatCount h(String h){
		addParams("h", h);
		return this;
	}
	// param: boolean help: Return help information
	public CatCount help(boolean help){
		addParams("help", help);
		return this;
	}
	// param: list s: Comma-separated list of column names or column aliases to sort by
	public CatCount s(String s){
		addParams("s", s);
		return this;
	}
	// param: boolean v: Verbose mode. Display column headers
	public CatCount v(boolean v){
		addParams("v", v);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names to limit the returned information
	private String index;
	public CatCount setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/count/{index}
		if(index != null ){
			setUrl("_cat", "count", index);
			return super.parseUrl(method);
		}
		//=>/_cat/count
		setUrl("_cat", "count");
		return super.parseUrl(method);

	}
}
