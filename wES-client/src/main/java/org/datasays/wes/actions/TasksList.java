package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumGroupBy;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
 **/
public class TasksList extends RequestInfo {

	public TasksList(String baseUrl) {
		super(baseUrl);
	}

	public TasksList(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: list nodeId: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 **/
	public TasksList nodeId(String nodeId) {
		addParams("nodeId", nodeId);
		return this;
	}

	/**
	 * param: list actions: A comma-separated list of actions that should be returned. Leave empty to return all.
	 **/
	public TasksList actions(String actions) {
		addParams("actions", actions);
		return this;
	}

	/**
	 * param: boolean detailed: Return detailed task information (default: false)
	 **/
	public TasksList detailed(boolean detailed) {
		addParams("detailed", detailed);
		return this;
	}

	/**
	 * param: string parentNode: Return tasks with specified parent node.
	 **/
	public TasksList parentNode(String parentNode) {
		addParams("parentNode", parentNode);
		return this;
	}

	/**
	 * param: string parentTask: Return tasks with specified parent task id (node_id:task_number). Set to -1 to return all.
	 **/
	public TasksList parentTask(String parentTask) {
		addParams("parentTask", parentTask);
		return this;
	}

	/**
	 * param: boolean waitForCompletion: Wait for the matching tasks to complete (default: false)
	 **/
	public TasksList waitForCompletion(boolean waitForCompletion) {
		addParams("waitForCompletion", waitForCompletion);
		return this;
	}

	/**
	 * param: enum groupBy: Group tasks by nodes or parent/child relationships
	 **/
	public TasksList groupBy(EnumGroupBy groupBy) {
		addParams("groupBy", groupBy);
		return this;
	}

	/**
	 * body:null
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public TasksList setParts() {

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_tasks
		setUrl("_tasks");
		return super.parseUrl(method);

	}
}
