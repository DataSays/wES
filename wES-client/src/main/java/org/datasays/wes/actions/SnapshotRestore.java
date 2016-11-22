package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/modules-snapshots.html
**/
public class SnapshotRestore extends ARequestInfo{

	public SnapshotRestore(String baseUrl){
		super(baseUrl);
	}

	/** param: time masterTimeout: Explicit operation timeout for connection to master node**/
	public SnapshotRestore masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: boolean waitForCompletion: Should this request wait until the operation has completed before returning**/
	public SnapshotRestore waitForCompletion(boolean waitForCompletion){
		addParams("waitForCompletion", waitForCompletion);
		return this;
	}
	/** body:Details of what to restore**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A repository name**/
	private String repository;
	/**A snapshot name**/
	private String snapshot;
	public SnapshotRestore setParts(String repository,String snapshot){
		this.repository=repository;
		this.snapshot=snapshot;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_snapshot/{repository}/{snapshot}/_restore
		if(repository != null && snapshot != null ){
			setUrl("_snapshot", repository, snapshot, "_restore");
			return super.parseUrl(method);
		}

		return null;
	}
}
