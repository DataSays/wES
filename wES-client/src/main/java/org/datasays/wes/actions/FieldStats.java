package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-field-stats.html
**/
public class FieldStats extends ARequestInfo{

	public FieldStats(String baseUrl){
		super(baseUrl);
	}
	public FieldStats(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: list fields: A comma-separated list of fields for to get field statistics for (min value, max value, and more)**/
	public FieldStats fields(String fields){
		addParams("fields", fields);
		return this;
	}
	/** param: enum level: Defines if field stats should be returned on a per index level or on a cluster wide level**/
	public FieldStats level(EnumLevel level){
		addParams("level", level);
		return this;
	}
	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public FieldStats ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public FieldStats allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public FieldStats expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** body:Field json objects containing the name and optionally a range to filter out indices result, that have results outside the defined bounds**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	public FieldStats setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_field_stats
		if(index != null ){
			setUrl(index, "_field_stats");
			return super.parseUrl(method);
		}
		//=>/_field_stats
		setUrl("_field_stats");
		return super.parseUrl(method);

	}
}
