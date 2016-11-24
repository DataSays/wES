package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
**/
public class SnapshotDelete extends ARequestInfo{

	public SnapshotDelete(String baseUrl){
		super(baseUrl);
	}
	public SnapshotDelete(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public SnapshotDelete masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A repository name**/
	private String repository;
	/**A snapshot name**/
	private String snapshot;
	public SnapshotDelete setParts(String repository,String snapshot){
		this.repository=repository;
		this.snapshot=snapshot;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"DELETE".equalsIgnoreCase(method)){
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
