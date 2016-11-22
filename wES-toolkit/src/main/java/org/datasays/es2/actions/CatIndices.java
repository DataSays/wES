package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-indices.html
**/
public class CatIndices extends ARequestInfo{

	public CatIndices(String baseUrl){
		super(baseUrl);
	}

	/** param: string format: a short version of the Accept header, e.g. json, yaml**/
	public CatIndices format(String format){
		addParams("format", format);
		return this;
	}
	/** param: enum bytes: The unit in which to display byte values**/
	public CatIndices bytes(EnumBytes bytes){
		addParams("bytes", bytes);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public CatIndices local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public CatIndices masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: list h: Comma-separated list of column names to display**/
	public CatIndices h(String h){
		addParams("h", h);
		return this;
	}
	/** param: enum health: A health status ("green", "yellow", or "red" to filter only indices matching the specified health status**/
	public CatIndices health(EnumHealth health){
		addParams("health", health);
		return this;
	}
	/** param: boolean help: Return help information**/
	public CatIndices help(boolean help){
		addParams("help", help);
		return this;
	}
	/** param: boolean pri: Set to true to return stats only for primary shards**/
	public CatIndices pri(boolean pri){
		addParams("pri", pri);
		return this;
	}
	/** param: list s: Comma-separated list of column names or column aliases to sort by**/
	public CatIndices s(String s){
		addParams("s", s);
		return this;
	}
	/** param: boolean v: Verbose mode. Display column headers**/
	public CatIndices v(boolean v){
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
	public CatIndices setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/indices/{index}
		if(index != null ){
			setUrl("_cat", "indices", index);
			return super.parseUrl(method);
		}
		//=>/_cat/indices
		setUrl("_cat", "indices");
		return super.parseUrl(method);

	}
}
