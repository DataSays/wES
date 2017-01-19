package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/search-search.html
public class Search extends RequestInfo{

	public Search(String baseUrl){
		super(baseUrl);
	}
	public Search(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: string analyzer: The analyzer to use for the query string
	public Search analyzer(String analyzer){
		addParams("analyzer", analyzer);
		return this;
	}
	// param: boolean analyzeWildcard: Specify whether wildcard and prefix queries should be analyzed (default: false)
	public Search analyzeWildcard(boolean analyzeWildcard){
		addParams("analyzeWildcard", analyzeWildcard);
		return this;
	}
	// param: enum defaultOperator: The default operator for query string query (AND or OR)
	public Search defaultOperator(EnumDefaultOperator defaultOperator){
		addParams("defaultOperator", defaultOperator);
		return this;
	}
	// param: string df: The field to use as default where no field prefix is given in the query string
	public Search df(String df){
		addParams("df", df);
		return this;
	}
	// param: boolean explain: Specify whether to return detailed information about score computation as part of a hit
	public Search explain(boolean explain){
		addParams("explain", explain);
		return this;
	}
	// param: list storedFields: A comma-separated list of stored fields to return as part of a hit
	public Search storedFields(String storedFields){
		addParams("storedFields", storedFields);
		return this;
	}
	// param: list docvalueFields: A comma-separated list of fields to return as the docvalue representation of a field for each hit
	public Search docvalueFields(String docvalueFields){
		addParams("docvalueFields", docvalueFields);
		return this;
	}
	// param: list fielddataFields: A comma-separated list of fields to return as the docvalue representation of a field for each hit
	public Search fielddataFields(String fielddataFields){
		addParams("fielddataFields", fielddataFields);
		return this;
	}
	// param: number from: Starting offset (default: 0)
	public Search from(Number from){
		addParams("from", from);
		return this;
	}
	// param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	public Search ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	// param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	public Search allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	// param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	public Search expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	// param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored
	public Search lenient(boolean lenient){
		addParams("lenient", lenient);
		return this;
	}
	// param: string preference: Specify the node or shard the operation should be performed on (default: random)
	public Search preference(String preference){
		addParams("preference", preference);
		return this;
	}
	// param: string q: Query in the Lucene query string syntax
	public Search q(String q){
		addParams("q", q);
		return this;
	}
	// param: list routing: A comma-separated list of specific routing values
	public Search routing(String routing){
		addParams("routing", routing);
		return this;
	}
	// param: time scroll: Specify how long a consistent view of the index should be maintained for scrolled search
	public Search scroll(long scroll){
		addParams("scroll", scroll);
		return this;
	}
	// param: enum searchType: Search operation type
	public Search searchType(EnumSearchType searchType){
		addParams("searchType", searchType);
		return this;
	}
	// param: number size: Number of hits to return (default: 10)
	public Search size(Number size){
		addParams("size", size);
		return this;
	}
	// param: list sort: A comma-separated list of <field>:<direction> pairs
	public Search sort(String sort){
		addParams("sort", sort);
		return this;
	}
	// param: list source: True or false to return the _source field or not, or a list of fields to return
	public Search source(String source){
		addParams("source", source);
		return this;
	}
	// param: list sourceExclude: A list of fields to exclude from the returned _source field
	public Search sourceExclude(String sourceExclude){
		addParams("sourceExclude", sourceExclude);
		return this;
	}
	// param: list sourceInclude: A list of fields to extract and return from the _source field
	public Search sourceInclude(String sourceInclude){
		addParams("sourceInclude", sourceInclude);
		return this;
	}
	// param: number terminateAfter: The maximum number of documents to collect for each shard, upon reaching which the query execution will terminate early.
	public Search terminateAfter(Number terminateAfter){
		addParams("terminateAfter", terminateAfter);
		return this;
	}
	// param: list stats: Specific 'tag' of the request for logging and statistical purposes
	public Search stats(String stats){
		addParams("stats", stats);
		return this;
	}
	// param: string suggestField: Specify which field to use for suggestions
	public Search suggestField(String suggestField){
		addParams("suggestField", suggestField);
		return this;
	}
	// param: enum suggestMode: Specify suggest mode
	public Search suggestMode(EnumSuggestMode suggestMode){
		addParams("suggestMode", suggestMode);
		return this;
	}
	// param: number suggestSize: How many suggestions to return in response
	public Search suggestSize(Number suggestSize){
		addParams("suggestSize", suggestSize);
		return this;
	}
	// param: string suggestText: The source text for which the suggestions should be returned
	public Search suggestText(String suggestText){
		addParams("suggestText", suggestText);
		return this;
	}
	// param: time timeout: Explicit operation timeout
	public Search timeout(long timeout){
		addParams("timeout", timeout);
		return this;
	}
	// param: boolean trackScores: Whether to calculate and return scores even if they are not used for sorting
	public Search trackScores(boolean trackScores){
		addParams("trackScores", trackScores);
		return this;
	}
	// param: boolean version: Specify whether to return document version as part of a hit
	public Search version(boolean version){
		addParams("version", version);
		return this;
	}
	// param: boolean requestCache: Specify if request cache should be used for this request or not, defaults to index level setting
	public Search requestCache(boolean requestCache){
		addParams("requestCache", requestCache);
		return this;
	}
	// body:The search definition using the Query DSL
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices
	private String index;
	//A comma-separated list of document types to search; leave empty to perform the operation on all types
	private String type;
	public Search setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/_search
		if(index != null && type != null ){
			setUrl(index, type, "_search");
			return super.parseUrl(method);
		}
		//=>/{index}/_search
		if(index != null ){
			setUrl(index, "_search");
			return super.parseUrl(method);
		}
		//=>/_search
		setUrl("_search");
		return super.parseUrl(method);

	}
}
