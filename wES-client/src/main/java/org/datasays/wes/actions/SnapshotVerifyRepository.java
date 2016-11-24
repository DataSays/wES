package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
**/
public class SnapshotVerifyRepository extends ARequestInfo{

	public SnapshotVerifyRepository(String baseUrl){
		super(baseUrl);
	}
	public SnapshotVerifyRepository(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public SnapshotVerifyRepository masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: time timeout: Explicit operation timeout**/
	public SnapshotVerifyRepository timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A repository name**/
	private String repository;
	public SnapshotVerifyRepository setParts(String repository){
		this.repository=repository;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_snapshot/{repository}/_verify
		if(repository != null ){
			setUrl("_snapshot", repository, "_verify");
			return super.parseUrl(method);
		}

		return null;
	}
}
