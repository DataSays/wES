package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/modules-snapshots.html
public class SnapshotCreate extends RequestInfo{

	public SnapshotCreate(String baseUrl){
		super(baseUrl);
	}
	public SnapshotCreate(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time masterTimeout: Explicit operation timeout for connection to master node
	public SnapshotCreate masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: boolean waitForCompletion: Should this request wait until the operation has completed before returning
	public SnapshotCreate waitForCompletion(boolean waitForCompletion){
		addParams("waitForCompletion", waitForCompletion);
		return this;
	}
	// body:The snapshot definition
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A repository name
	private String repository;
	//A snapshot name
	private String snapshot;
	public SnapshotCreate setParts(String repository,String snapshot){
		this.repository=repository;
		this.snapshot=snapshot;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"PUT".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_snapshot/{repository}/{snapshot}
		if(repository != null && snapshot != null ){
			setUrl("_snapshot", repository, snapshot);
			return super.parseUrl(method);
		}

		return null;
	}
}
