package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cluster-state.html
**/
public class ClusterState extends ARequestInfo{

	public ClusterState(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public ClusterState local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: time masterTimeout: Specify timeout for connection to master**/
	public ClusterState masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	/** param: boolean flatSettings: Return settings in flat format (default: false)**/
	public ClusterState flatSettings(boolean flatSettings){
		addParams("flatSettings", flatSettings);
		return this;
	}
	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public ClusterState ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public ClusterState allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public ClusterState expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	/**Limit the information returned to the specified metrics**/
	private String metric;
	public ClusterState setParts(String index,String metric){
		this.index=index;
		this.metric=metric;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cluster/state/{metric}/{index}
		if(index != null && metric != null ){
			setUrl("_cluster", "state", metric, index);
			return super.parseUrl(method);
		}
		//=>/_cluster/state/{metric}
		if(metric != null ){
			setUrl("_cluster", "state", metric);
			return super.parseUrl(method);
		}
		//=>/_cluster/state
		setUrl("_cluster", "state");
		return super.parseUrl(method);

	}
}
