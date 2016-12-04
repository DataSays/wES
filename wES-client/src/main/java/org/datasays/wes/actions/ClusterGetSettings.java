package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-update-settings.html
 **/
public class ClusterGetSettings extends RequestInfo {

	public ClusterGetSettings(String baseUrl) {
		super(baseUrl);
	}

	public ClusterGetSettings(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: boolean flatSettings: Return settings in flat format (default: false)
	 **/
	public ClusterGetSettings flatSettings(boolean flatSettings) {
		addParams("flatSettings", flatSettings);
		return this;
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public ClusterGetSettings masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: time timeout: Explicit operation timeout
	 **/
	public ClusterGetSettings timeout(long timeout) {
		addParams("timeout", timeout);
		return this;
	}

	/**
	 * param: boolean includeDefaults: Whether to return all default clusters setting.
	 **/
	public ClusterGetSettings includeDefaults(boolean includeDefaults) {
		addParams("includeDefaults", includeDefaults);
		return this;
	}

	/**
	 * body:null
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	public ClusterGetSettings setParts() {

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_cluster/settings
		setUrl("_cluster", "settings");
		return super.parseUrl(method);

	}
}
