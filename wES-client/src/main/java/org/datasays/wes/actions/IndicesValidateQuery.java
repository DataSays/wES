package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumDefaultOperator;
import org.datasays.wes.types.EnumExpandWildcards;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-validate.html
 **/
public class IndicesValidateQuery extends RequestInfo {

	public IndicesValidateQuery(String baseUrl) {
		super(baseUrl);
	}

	public IndicesValidateQuery(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: boolean explain: Return detailed information about the error
	 **/
	public IndicesValidateQuery explain(boolean explain) {
		addParams("explain", explain);
		return this;
	}

	/**
	 * param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	 **/
	public IndicesValidateQuery ignoreUnavailable(boolean ignoreUnavailable) {
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}

	/**
	 * param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	 **/
	public IndicesValidateQuery allowNoIndices(boolean allowNoIndices) {
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}

	/**
	 * param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	 **/
	public IndicesValidateQuery expandWildcards(EnumExpandWildcards expandWildcards) {
		addParams("expandWildcards", expandWildcards);
		return this;
	}

	/**
	 * param: string q: Query in the Lucene query string syntax
	 **/
	public IndicesValidateQuery q(String q) {
		addParams("q", q);
		return this;
	}

	/**
	 * param: string analyzer: The analyzer to use for the query string
	 **/
	public IndicesValidateQuery analyzer(String analyzer) {
		addParams("analyzer", analyzer);
		return this;
	}

	/**
	 * param: boolean analyzeWildcard: Specify whether wildcard and prefix queries should be analyzed (default: false)
	 **/
	public IndicesValidateQuery analyzeWildcard(boolean analyzeWildcard) {
		addParams("analyzeWildcard", analyzeWildcard);
		return this;
	}

	/**
	 * param: enum defaultOperator: The default operator for query string query (AND or OR)
	 **/
	public IndicesValidateQuery defaultOperator(EnumDefaultOperator defaultOperator) {
		addParams("defaultOperator", defaultOperator);
		return this;
	}

	/**
	 * param: string df: The field to use as default where no field prefix is given in the query string
	 **/
	public IndicesValidateQuery df(String df) {
		addParams("df", df);
		return this;
	}

	/**
	 * param: boolean lenient: Specify whether format-based query failures (such as providing text to a numeric field) should be ignored
	 **/
	public IndicesValidateQuery lenient(boolean lenient) {
		addParams("lenient", lenient);
		return this;
	}

	/**
	 * param: boolean rewrite: Provide a more detailed explanation showing the actual Lucene query that will be executed.
	 **/
	public IndicesValidateQuery rewrite(boolean rewrite) {
		addParams("rewrite", rewrite);
		return this;
	}

	/**
	 * body:The query definition specified with the Query DSL
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**
	 * A comma-separated list of index names to restrict the operation; use `_all` or empty string to perform the operation on all indices
	 **/
	private String index;
	/**
	 * A comma-separated list of document types to restrict the operation; leave empty to perform the operation on all types
	 **/
	private String type;

	public IndicesValidateQuery setParts(String index, String type) {
		this.index = index;
		this.type = type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/{index}/{type}/_validate/query
		if (index != null && type != null) {
			setUrl(index, type, "_validate", "query");
			return super.parseUrl(method);
		}
		//=>/{index}/_validate/query
		if (index != null) {
			setUrl(index, "_validate", "query");
			return super.parseUrl(method);
		}
		//=>/_validate/query
		setUrl("_validate", "query");
		return super.parseUrl(method);

	}
}
