package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/indices-update-settings.html
public class IndicesPutSettings extends RequestInfo{

	public IndicesPutSettings(String baseUrl){
		super(baseUrl);
	}
	public IndicesPutSettings(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time masterTimeout: Specify timeout for connection to master
	public IndicesPutSettings masterTimeout(long masterTimeout){
		addParams("masterTimeout", masterTimeout);
		return this;
	}
	// param: boolean preserveExisting: Whether to update existing settings. If set to `true` existing settings on an index remain unchanged, the default is `false`
	public IndicesPutSettings preserveExisting(boolean preserveExisting){
		addParams("preserveExisting", preserveExisting);
		return this;
	}
	// param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)
	public IndicesPutSettings ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	// param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)
	public IndicesPutSettings allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	// param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.
	public IndicesPutSettings expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	// param: boolean flatSettings: Return settings in flat format (default: false)
	public IndicesPutSettings flatSettings(boolean flatSettings){
		addParams("flatSettings", flatSettings);
		return this;
	}
	// body*:The index settings to be updated
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices
	private String index;
	public IndicesPutSettings setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"PUT".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_settings
		if(index != null ){
			setUrl(index, "_settings");
			return super.parseUrl(method);
		}
		//=>/_settings
		setUrl("_settings");
		return super.parseUrl(method);

	}
}
