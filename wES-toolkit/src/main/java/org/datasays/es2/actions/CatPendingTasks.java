package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-pending-tasks.html
**/
public class CatPendingTasks extends ARequestInfo{

	public CatPendingTasks(String baseUrl){
		super(baseUrl);
	}

	/** param: string format: a short version of the Accept header, e.g. json, yaml**/
	public CatPendingTasks format(String format){
		addParams("format", format);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public CatPendingTasks local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public CatPendingTasks masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: list h: Comma-separated list of column names to display**/
	public CatPendingTasks h(String h){
		addParams("h", h);
		return this;
	}
	/** param: boolean help: Return help information**/
	public CatPendingTasks help(boolean help){
		addParams("help", help);
		return this;
	}
	/** param: list s: Comma-separated list of column names or column aliases to sort by**/
	public CatPendingTasks s(String s){
		addParams("s", s);
		return this;
	}
	/** param: boolean v: Verbose mode. Display column headers**/
	public CatPendingTasks v(boolean v){
		addParams("v", v);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public CatPendingTasks setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/pending_tasks
		setUrl("_cat", "pending_tasks");
		return super.parseUrl(method);

	}
}
