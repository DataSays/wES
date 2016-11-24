package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
**/
public class TasksGet extends ARequestInfo{

	public TasksGet(String baseUrl){
		super(baseUrl);
	}
	public TasksGet(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: boolean waitForCompletion: Wait for the matching tasks to complete (default: false)**/
	public TasksGet waitForCompletion(boolean waitForCompletion){
		addParams("waitForCompletion", waitForCompletion);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**Return the task with specified id (node_id:task_number)**/
	private String task_id;
	public TasksGet setParts(String task_id){
		this.task_id=task_id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_tasks/{task_id}
		if(task_id != null ){
			setUrl("_tasks", task_id);
			return super.parseUrl(method);
		}

		return null;
	}
}
