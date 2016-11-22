package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-recovery.html
**/
public class CatRecovery extends ARequestInfo{

	public CatRecovery(String baseUrl){
		super(baseUrl);
	}

	/** param: string format: a short version of the Accept header, e.g. json, yaml**/
	public CatRecovery format(String format){
		addParams("format", format);
		return this;
	}
	/** param: enum bytes: The unit in which to display byte values**/
	public CatRecovery bytes(EnumBytes bytes){
		addParams("bytes", bytes);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public CatRecovery masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: list h: Comma-separated list of column names to display**/
	public CatRecovery h(String h){
		addParams("h", h);
		return this;
	}
	/** param: boolean help: Return help information**/
	public CatRecovery help(boolean help){
		addParams("help", help);
		return this;
	}
	/** param: list s: Comma-separated list of column names or column aliases to sort by**/
	public CatRecovery s(String s){
		addParams("s", s);
		return this;
	}
	/** param: boolean v: Verbose mode. Display column headers**/
	public CatRecovery v(boolean v){
		addParams("v", v);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names to limit the returned information**/
	private String index;
	public CatRecovery setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/recovery/{index}
		if(index != null ){
			setUrl("_cat", "recovery", index);
			return super.parseUrl(method);
		}
		//=>/_cat/recovery
		setUrl("_cat", "recovery");
		return super.parseUrl(method);

	}
}
