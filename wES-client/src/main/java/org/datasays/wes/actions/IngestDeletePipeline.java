package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/5.x/ingest.html
public class IngestDeletePipeline extends RequestInfo{

	public IngestDeletePipeline(String baseUrl){
		super(baseUrl);
	}
	public IngestDeletePipeline(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time masterTimeout: Explicit operation timeout for connection to master node
	public IngestDeletePipeline masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: time timeout: Explicit operation timeout
	public IngestDeletePipeline timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//Pipeline ID
	private String id;
	public IngestDeletePipeline setParts(String id){
		this.id=id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"DELETE".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_ingest/pipeline/{id}
		if(id != null ){
			setUrl("_ingest", "pipeline", id);
			return super.parseUrl(method);
		}

		return null;
	}
}
