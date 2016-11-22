package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
**/
public class Mpercolate extends ARequestInfo{

	public Mpercolate(String baseUrl){
		super(baseUrl);
	}

	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public Mpercolate ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public Mpercolate allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public Mpercolate expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** body*:The percolate request definitions (header & body pair), separated by newlines**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The index of the document being count percolated to use as default**/
	private String index;
	/**The type of the document being percolated to use as default.**/
	private String type;
	public Mpercolate setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/_mpercolate
		if(index != null && type != null ){
			setUrl(index, type, "_mpercolate");
			return super.parseUrl(method);
		}
		//=>/{index}/_mpercolate
		if(index != null ){
			setUrl(index, "_mpercolate");
			return super.parseUrl(method);
		}
		//=>/_mpercolate
		setUrl("_mpercolate");
		return super.parseUrl(method);

	}
}
