package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-allocation.html
public class CatAllocation extends RequestInfo{

	public CatAllocation(String baseUrl){
		super(baseUrl);
	}
	public CatAllocation(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: string format: a short version of the Accept header, e.g. json, yaml
	public CatAllocation format(String format){
		addParams("format", format);
		return this;
	}
	// param: enum bytes: The unit in which to display byte values
	public CatAllocation bytes(EnumBytes bytes){
		addParams("bytes", bytes);
		return this;
	}
	// param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	public CatAllocation local(boolean local){
		addParams("local", local);
		return this;
	}
	// param: time masterTimeout: Explicit operation timeout for connection to master node
	public CatAllocation masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: list h: Comma-separated list of column names to display
	public CatAllocation h(String h){
		addParams("h", h);
		return this;
	}
	// param: boolean help: Return help information
	public CatAllocation help(boolean help){
		addParams("help", help);
		return this;
	}
	// param: list s: Comma-separated list of column names or column aliases to sort by
	public CatAllocation s(String s){
		addParams("s", s);
		return this;
	}
	// param: boolean v: Verbose mode. Display column headers
	public CatAllocation v(boolean v){
		addParams("v", v);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of node IDs or names to limit the returned information
	private String node_id;
	public CatAllocation setParts(String node_id){
		this.node_id=node_id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/allocation/{node_id}
		if(node_id != null ){
			setUrl("_cat", "allocation", node_id);
			return super.parseUrl(method);
		}
		//=>/_cat/allocation
		setUrl("_cat", "allocation");
		return super.parseUrl(method);

	}
}
