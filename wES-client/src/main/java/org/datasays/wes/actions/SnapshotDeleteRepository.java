package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
 **/
public class SnapshotDeleteRepository extends RequestInfo {

	public SnapshotDeleteRepository(String baseUrl) {
		super(baseUrl);
	}

	public SnapshotDeleteRepository(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public SnapshotDeleteRepository masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: time timeout: Explicit operation timeout
	 **/
	public SnapshotDeleteRepository timeout(long timeout) {
		addParams("timeout", timeout);
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
	 * A comma-separated list of repository names
	 **/
	private String repository;

	public SnapshotDeleteRepository setParts(String repository) {
		this.repository = repository;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"DELETE".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_snapshot/{repository}
		if (repository != null) {
			setUrl("_snapshot", repository);
			return super.parseUrl(method);
		}

		return null;
	}
}
