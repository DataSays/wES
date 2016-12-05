package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-fielddata.html
public class CatFielddata extends RequestInfo{

	public CatFielddata(String baseUrl){
		super(baseUrl);
	}
	public CatFielddata(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: string format: a short version of the Accept header, e.g. json, yaml
	public CatFielddata format(String format){
		addParams("format", format);
		return this;
	}
	// param: enum bytes: The unit in which to display byte values
	public CatFielddata bytes(EnumBytes bytes){
		addParams("bytes", bytes);
		return this;
	}
	// param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	public CatFielddata local(boolean local){
		addParams("local", local);
		return this;
	}
	// param: time masterTimeout: Explicit operation timeout for connection to master node
	public CatFielddata masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: list h: Comma-separated list of column names to display
	public CatFielddata h(String h){
		addParams("h", h);
		return this;
	}
	// param: boolean help: Return help information
	public CatFielddata help(boolean help){
		addParams("help", help);
		return this;
	}
	// param: list s: Comma-separated list of column names or column aliases to sort by
	public CatFielddata s(String s){
		addParams("s", s);
		return this;
	}
	// param: boolean v: Verbose mode. Display column headers
	public CatFielddata v(boolean v){
		addParams("v", v);
		return this;
	}
	// param: list fields: A comma-separated list of fields to return in the output
	public CatFielddata fields(String fields){
		addParams("fields", fields);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of fields to return the fielddata size
	private String fields;
	public CatFielddata setParts(String fields){
		this.fields=fields;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/fielddata/{fields}
		if(fields != null ){
			setUrl("_cat", "fielddata", fields);
			return super.parseUrl(method);
		}
		//=>/_cat/fielddata
		setUrl("_cat", "fielddata");
		return super.parseUrl(method);

	}
}
