package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/tasks.html
 **/
public class TasksCancel extends RequestInfo {

	public TasksCancel(String baseUrl) {
		super(baseUrl);
	}

	public TasksCancel(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: list nodeId: A comma-separated list of node IDs or names to limit the returned information; use `_local` to return information from the node you're connecting to, leave empty to get information from all nodes
	 **/
	public TasksCancel nodeId(String nodeId) {
		addParams("nodeId", nodeId);
		return this;
	}

	/**
	 * param: list actions: A comma-separated list of actions that should be cancelled. Leave empty to cancel all.
	 **/
	public TasksCancel actions(String actions) {
		addParams("actions", actions);
		return this;
	}

	/**
	 * param: string parentNode: Cancel tasks with specified parent node.
	 **/
	public TasksCancel parentNode(String parentNode) {
		addParams("parentNode", parentNode);
		return this;
	}

	/**
	 * param: string parentTask: Cancel tasks with specified parent task id (node_id:task_number). Set to -1 to cancel all.
	 **/
	public TasksCancel parentTask(String parentTask) {
		addParams("parentTask", parentTask);
		return this;
	}

	/**
	 * body:null
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**
	 * Cancel the task with specified task id (node_id:task_number)
	 **/
	private String task_id;

	public TasksCancel setParts(String task_id) {
		this.task_id = task_id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"POST".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_tasks/{task_id}/_cancel
		if (task_id != null) {
			setUrl("_tasks", task_id, "_cancel");
			return super.parseUrl(method);
		}
		//=>/_tasks/_cancel
		setUrl("_tasks", "_cancel");
		return super.parseUrl(method);

	}
}
