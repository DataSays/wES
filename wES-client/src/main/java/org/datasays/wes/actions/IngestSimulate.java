package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/plugins/master/ingest.html
public class IngestSimulate extends RequestInfo{

	public IngestSimulate(String baseUrl){
		super(baseUrl);
	}
	public IngestSimulate(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: boolean verbose: Verbose mode. Display data output for each processor in executed pipeline
	public IngestSimulate verbose(boolean verbose){
		addParams("verbose", verbose);
		return this;
	}
	// body*:The simulate definition
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//Pipeline ID
	private String id;
	public IngestSimulate setParts(String id){
		this.id=id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_ingest/pipeline/{id}/_simulate
		if(id != null ){
			setUrl("_ingest", "pipeline", id, "_simulate");
			return super.parseUrl(method);
		}
		//=>/_ingest/pipeline/_simulate
		setUrl("_ingest", "pipeline", "_simulate");
		return super.parseUrl(method);

	}
}
