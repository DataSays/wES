package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
public class SnapshotCreateRepository extends RequestInfo{

	public SnapshotCreateRepository(String baseUrl){
		super(baseUrl);
	}
	public SnapshotCreateRepository(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time masterTimeout: Explicit operation timeout for connection to master node
	public SnapshotCreateRepository masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: time timeout: Explicit operation timeout
	public SnapshotCreateRepository timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// param: boolean verify: Whether to verify the repository after creation
	public SnapshotCreateRepository verify(boolean verify){
		addParams("verify", verify);
		return this;
	}
	// body*:The repository definition
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A repository name
	private String repository;
	public SnapshotCreateRepository setParts(String repository){
		this.repository=repository;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"PUT".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_snapshot/{repository}
		if(repository != null ){
			setUrl("_snapshot", repository);
			return super.parseUrl(method);
		}

		return null;
	}
}
