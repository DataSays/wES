package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
 **/
public class IngestPutPipeline extends RequestInfo {

	public IngestPutPipeline(String baseUrl) {
		super(baseUrl);
	}

	public IngestPutPipeline(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public IngestPutPipeline masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
		return this;
	}

	/**
	 * param: time timeout: Explicit operation timeout
	 **/
	public IngestPutPipeline timeout(long timeout) {
		addParams("timeout", timeout);
		return this;
	}

	/**
	 * body*:The ingest definition
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**
	 * Pipeline ID
	 **/
	private String id;

	public IngestPutPipeline setParts(String id) {
		this.id = id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"PUT".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_ingest/pipeline/{id}
		if (id != null) {
			setUrl("_ingest", "pipeline", id);
			return super.parseUrl(method);
		}

		return null;
	}
}
