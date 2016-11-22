package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-count.html
**/
public class Count extends ARequestInfo{

	public Count(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public Count ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public Count allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public Count expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: number minScore: Include only documents with a specific `_score` value in the result**/
	public Count minScore(Number minScore){
		addParams("minScore", minScore);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public Count preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public Count routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: string q: Query in the Lucene query string syntax**/
	public Count q(String q){
		addParams("q", q);
		return this;
	}
	/** param: string analyzer: The analyzer to use for the query string**/
	public Count analyzer(String analyzer){
		addParams("analyzer", analyzer);
		return this;
	}
	/** param: boolean analyzeWildcard: Specify whether wildcard and prefix queries should be analyzed (default: false)**/
	public Count analyzeWildcard(boolean analyzeWildcard){
		addParams("analyzeWildcard", analyzeWildcard);
		return this;
	}
	/** param: enum defaultOperator: The default operator for query string query (AND or OR)**/
	public Count defaultOperator(EnumDefaultOperator defaultOperator){
		addParams("defaultOperator", defaultOperator);
		return this;
	}
	/** param: string df: The field to use as default where no field prefix is given in the query string**/
	public Count df(String df){
		addParams("df", df);
		return this;
	}
	/** param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored**/
	public Count lenient(boolean lenient){
		addParams("lenient", lenient);
		return this;
	}
	/** body:A query to restrict the results specified with the Query DSL (optional)**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of indices to restrict the results**/
	private String index;
	/**A comma-separated list of types to restrict the results**/
	private String type;
	public Count setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"POST".equalsIgnoreCase(method) && !"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/_count
		if(index != null && type != null ){
			setUrl(index, type, "_count");
			return super.parseUrl(method);
		}
		//=>/{index}/_count
		if(index != null ){
			setUrl(index, "_count");
			return super.parseUrl(method);
		}
		//=>/_count
		setUrl("_count");
		return super.parseUrl(method);

	}
}
