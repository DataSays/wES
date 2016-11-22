package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-master.html
**/
public class CatMaster extends ARequestInfo{

	public CatMaster(String baseUrl){
		super(baseUrl);
	}

	/** param: string format: a short version of the Accept header, e.g. json, yaml**/
	public CatMaster format(String format){
		addParams("format", format);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public CatMaster local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public CatMaster masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: list h: Comma-separated list of column names to display**/
	public CatMaster h(String h){
		addParams("h", h);
		return this;
	}
	/** param: boolean help: Return help information**/
	public CatMaster help(boolean help){
		addParams("help", help);
		return this;
	}
	/** param: list s: Comma-separated list of column names or column aliases to sort by**/
	public CatMaster s(String s){
		addParams("s", s);
		return this;
	}
	/** param: boolean v: Verbose mode. Display column headers**/
	public CatMaster v(boolean v){
		addParams("v", v);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public CatMaster setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/master
		setUrl("_cat", "master");
		return super.parseUrl(method);

	}
}
