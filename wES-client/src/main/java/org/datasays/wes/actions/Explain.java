package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-explain.html
**/
public class Explain extends ARequestInfo{

	public Explain(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean analyzeWildcard: Specify whether wildcards and prefix queries in the query string query should be analyzed (default: false)**/
	public Explain analyzeWildcard(boolean analyzeWildcard){
		addParams("analyzeWildcard", analyzeWildcard);
		return this;
	}
	/** param: string analyzer: The analyzer for the query string query**/
	public Explain analyzer(String analyzer){
		addParams("analyzer", analyzer);
		return this;
	}
	/** param: enum defaultOperator: The default operator for query string query (AND or OR)**/
	public Explain defaultOperator(EnumDefaultOperator defaultOperator){
		addParams("defaultOperator", defaultOperator);
		return this;
	}
	/** param: string df: The default field for query string query (default: _all)**/
	public Explain df(String df){
		addParams("df", df);
		return this;
	}
	/** param: list storedFields: A comma-separated list of stored fields to return in the response**/
	public Explain storedFields(String storedFields){
		addParams("storedFields", storedFields);
		return this;
	}
	/** param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored**/
	public Explain lenient(boolean lenient){
		addParams("lenient", lenient);
		return this;
	}
	/** param: string parent: The ID of the parent document**/
	public Explain parent(String parent){
		addParams("parent", parent);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public Explain preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: string q: Query in the Lucene query string syntax**/
	public Explain q(String q){
		addParams("q", q);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public Explain routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: list source: True or false to return the _source field or not, or a list of fields to return**/
	public Explain source(String source){
		addParams("source", source);
		return this;
	}
	/** param: list sourceExclude: A list of fields to exclude from the returned _source field**/
	public Explain sourceExclude(String sourceExclude){
		addParams("sourceExclude", sourceExclude);
		return this;
	}
	/** param: list sourceInclude: A list of fields to extract and return from the _source field**/
	public Explain sourceInclude(String sourceInclude){
		addParams("sourceInclude", sourceInclude);
		return this;
	}
	/** body:The query definition using the Query DSL**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The document ID**/
	private String id;
	/**The name of the index**/
	private String index;
	/**The type of the document**/
	private String type;
	public Explain setParts(String index,String type,String id){
		this.id=id;
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/{id}/_explain
		if(id != null && index != null && type != null ){
			setUrl(index, type, id, "_explain");
			return super.parseUrl(method);
		}

		return null;
	}
}
