package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-snapshots.html
**/
public class CatSnapshots extends ARequestInfo{

	public CatSnapshots(String baseUrl){
		super(baseUrl);
	}
	public CatSnapshots(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: string format: a short version of the Accept header, e.g. json, yaml**/
	public CatSnapshots format(String format){
		addParams("format", format);
		return this;
	}
	/** param: boolean ignoreUnavailable: Set to true to ignore unavailable snapshots**/
	public CatSnapshots ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public CatSnapshots masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: list h: Comma-separated list of column names to display**/
	public CatSnapshots h(String h){
		addParams("h", h);
		return this;
	}
	/** param: boolean help: Return help information**/
	public CatSnapshots help(boolean help){
		addParams("help", help);
		return this;
	}
	/** param: list s: Comma-separated list of column names or column aliases to sort by**/
	public CatSnapshots s(String s){
		addParams("s", s);
		return this;
	}
	/** param: boolean v: Verbose mode. Display column headers**/
	public CatSnapshots v(boolean v){
		addParams("v", v);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**Name of repository from which to fetch the snapshot information**/
	private String repository;
	public CatSnapshots setParts(String repository){
		this.repository=repository;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/snapshots/{repository}
		if(repository != null ){
			setUrl("_cat", "snapshots", repository);
			return super.parseUrl(method);
		}
		//=>/_cat/snapshots
		setUrl("_cat", "snapshots");
		return super.parseUrl(method);

	}
}
