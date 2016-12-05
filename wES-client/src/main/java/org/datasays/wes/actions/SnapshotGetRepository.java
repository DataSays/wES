package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
public class SnapshotGetRepository extends RequestInfo{

	public SnapshotGetRepository(String baseUrl){
		super(baseUrl);
	}
	public SnapshotGetRepository(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time masterTimeout: Explicit operation timeout for connection to master node
	public SnapshotGetRepository masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: boolean local: Return local information, do not retrieve the state from master node (default: false)
	public SnapshotGetRepository local(boolean local){
		addParams("local", local);
		return this;
	}
	// body:null
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of repository names
	private String repository;
	public SnapshotGetRepository setParts(String repository){
		this.repository=repository;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_snapshot/{repository}
		if(repository != null ){
			setUrl("_snapshot", repository);
			return super.parseUrl(method);
		}
		//=>/_snapshot
		setUrl("_snapshot");
		return super.parseUrl(method);

	}
}
