package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
**/
public class CatTasks extends ARequestInfo{

	public CatTasks(String baseUrl){
		super(baseUrl);
	}

	/** param: string format: a short version of the Accept header, e.g. json, yaml**/
	public CatTasks format(String format){
		addParams("format", format);
		return this;
	}
	/** param: list nodeId: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes**/
	public CatTasks nodeId(String nodeId){
		addParams("nodeId", nodeId);
		return this;
	}
	/** param: list actions: A comma-separated list of actions that should be returned. Leave empty to return all.**/
	public CatTasks actions(String actions){
		addParams("actions", actions);
		return this;
	}
	/** param: boolean detailed: Return detailed task information (default: false)**/
	public CatTasks detailed(boolean detailed){
		addParams("detailed", detailed);
		return this;
	}
	/** param: string parentNode: Return tasks with specified parent node.**/
	public CatTasks parentNode(String parentNode){
		addParams("parentNode", parentNode);
		return this;
	}
	/** param: number parentTask: Return tasks with specified parent task id. Set to -1 to return all.**/
	public CatTasks parentTask(Number parentTask){
		addParams("parentTask", parentTask);
		return this;
	}
	/** param: list h: Comma-separated list of column names to display**/
	public CatTasks h(String h){
		addParams("h", h);
		return this;
	}
	/** param: boolean help: Return help information**/
	public CatTasks help(boolean help){
		addParams("help", help);
		return this;
	}
	/** param: list s: Comma-separated list of column names or column aliases to sort by**/
	public CatTasks s(String s){
		addParams("s", s);
		return this;
	}
	/** param: boolean v: Verbose mode. Display column headers**/
	public CatTasks v(boolean v){
		addParams("v", v);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public CatTasks setParts(){

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/tasks
		setUrl("_cat", "tasks");
		return super.parseUrl(method);

	}
}
