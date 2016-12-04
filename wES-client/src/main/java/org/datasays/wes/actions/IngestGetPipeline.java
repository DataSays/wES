package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;

/**
 * documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
 **/
public class IngestGetPipeline extends RequestInfo {

	public IngestGetPipeline(String baseUrl) {
		super(baseUrl);
	}

	public IngestGetPipeline(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: time masterTimeout: Explicit operation timeout for connection to master node
	 **/
	public IngestGetPipeline masterTimeout(long masterTimeout) {
		addParams("masterTimeout", masterTimeout);
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
	 * Comma separated list of pipeline ids. Wildcards supported
	 **/
	private String id;

	public IngestGetPipeline setParts(String id) {
		this.id = id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/_ingest/pipeline/{id}
		if (id != null) {
			setUrl("_ingest", "pipeline", id);
			return super.parseUrl(method);
		}
		//=>/_ingest/pipeline
		setUrl("_ingest", "pipeline");
		return super.parseUrl(method);

	}
}
