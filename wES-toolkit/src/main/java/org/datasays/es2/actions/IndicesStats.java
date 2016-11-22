package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-stats.html
**/
public class IndicesStats extends ARequestInfo{

	public IndicesStats(String baseUrl){
		super(baseUrl);
	}

	/** param: list completionFields: A comma-separated list of fields for `fielddata` and `suggest` index metric (supports wildcards)**/
	public IndicesStats completionFields(String completionFields){
		addParams("completionFields", completionFields);
		return this;
	}
	/** param: list fielddataFields: A comma-separated list of fields for `fielddata` index metric (supports wildcards)**/
	public IndicesStats fielddataFields(String fielddataFields){
		addParams("fielddataFields", fielddataFields);
		return this;
	}
	/** param: list fields: A comma-separated list of fields for `fielddata` and `completion` index metric (supports wildcards)**/
	public IndicesStats fields(String fields){
		addParams("fields", fields);
		return this;
	}
	/** param: list groups: A comma-separated list of search groups for `search` index metric**/
	public IndicesStats groups(String groups){
		addParams("groups", groups);
		return this;
	}
	/** param: boolean human: Whether to return time and byte values in human-readable format.**/
	public IndicesStats human(boolean human){
		addParams("human", human);
		return this;
	}
	/** param: enum level: Return stats aggregated at cluster, index or shard level**/
	public IndicesStats level(EnumLevel level){
		addParams("level", level);
		return this;
	}
	/** param: list types: A comma-separated list of document types for the `indexing` index metric**/
	public IndicesStats types(String types){
		addParams("types", types);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	/**Limit the information returned the specific metrics.**/
	private String metric;
	public IndicesStats setParts(String index,String metric){
		this.index=index;
		this.metric=metric;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_stats/{metric}
		if(index != null && metric != null ){
			setUrl(index, "_stats", metric);
			return super.parseUrl(method);
		}
		//=>/_stats/{metric}
		if(metric != null ){
			setUrl("_stats", metric);
			return super.parseUrl(method);
		}
		//=>/{index}/_stats
		if(index != null ){
			setUrl(index, "_stats");
			return super.parseUrl(method);
		}
		//=>/_stats
		setUrl("_stats");
		return super.parseUrl(method);

	}
}
