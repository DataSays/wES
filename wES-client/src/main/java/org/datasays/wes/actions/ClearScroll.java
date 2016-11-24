package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-request-scroll.html
**/
public class ClearScroll extends ARequestInfo{

	public ClearScroll(String baseUrl){
		super(baseUrl);
	}
	public ClearScroll(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** body:A comma-separated list of scroll IDs to clear if none was specified via the scroll_id parameter**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of scroll IDs to clear**/
	private String scroll_id;
	public ClearScroll setParts(String scroll_id){
		this.scroll_id=scroll_id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"DELETE".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_search/scroll/{scroll_id}
		if(scroll_id != null ){
			setUrl("_search", "scroll", scroll_id);
			return super.parseUrl(method);
		}
		//=>/_search/scroll
		setUrl("_search", "scroll");
		return super.parseUrl(method);

	}
}
