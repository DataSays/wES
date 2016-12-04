package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
 **/
public class SnapshotStatus extends RequestInfo {

	public SnapshotStatus(String baseUrl) {
		super(baseUrl);
	}

	public SnapshotStatus(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public SnapshotStatus masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: boolean ignoreUnavailable: Whether to ignore unavailable snapshots, defaults to false which means a SnapshotMissingException is thrown
	 **/
	public SnapshotStatus ignoreUnavailable(boolean ignoreUnavailable) {
		addParams("ignoreUnavailable", ignoreUnavailable);
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
	 * A repository name
	 **/
	private String repository;
	/**
	 * A comma-separated list of snapshot names
	 **/
	private String snapshot;

	public SnapshotStatus setParts(String repository, String snapshot) {
		this.repository = repository;
		this.snapshot = snapshot;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_snapshot/{repository}/{snapshot}/_status
		if (repository != null && snapshot != null) {
			setUrl("_snapshot", repository, snapshot, "_status");
			return super.parseUrl(method);
		}
		//=>/_snapshot/{repository}/_status
		if (repository != null) {
			setUrl("_snapshot", repository, "_status");
			return super.parseUrl(method);
		}
		//=>/_snapshot/_status
		setUrl("_snapshot", "_status");
		return super.parseUrl(method);

	}
}
