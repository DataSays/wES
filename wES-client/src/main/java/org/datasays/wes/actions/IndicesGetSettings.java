package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.IRequestInfo;
import org.datasays.wes.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-get-settings.html
**/
public class IndicesGetSettings extends ARequestInfo{

	public IndicesGetSettings(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public IndicesGetSettings ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public IndicesGetSettings allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public IndicesGetSettings expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: boolean flatSettings: Return settings in flat format (default: false)**/
	public IndicesGetSettings flatSettings(boolean flatSettings){
		addParams("flatSettings", flatSettings);
		return this;
	}
	/** param: boolean local: Return local information, do not retrieve the state from master node (default: false)**/
	public IndicesGetSettings local(boolean local){
		addParams("local", local);
		return this;
	}
	/** param: boolean human: Whether to return version and creation date values in human-readable format.**/
	public IndicesGetSettings human(boolean human){
		addParams("human", human);
		return this;
	}
	/** param: boolean includeDefaults: Whether to return all default setting for each of the indices.**/
	public IndicesGetSettings includeDefaults(boolean includeDefaults){
		addParams("includeDefaults", includeDefaults);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names; use `_all` or empty string to perform the operation on all indices**/
	private String index;
	/**The name of the settings that should be included**/
	private String name;
	public IndicesGetSettings setParts(String index,String name){
		this.index=index;
		this.name=name;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_settings/{name}
		if(index != null && name != null ){
			setUrl(index, "_settings", name);
			return super.parseUrl(method);
		}
		//=>/_settings/{name}
		if(name != null ){
			setUrl("_settings", name);
			return super.parseUrl(method);
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
