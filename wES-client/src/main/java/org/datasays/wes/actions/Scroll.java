package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/search-request-scroll.html
public class Scroll extends RequestInfo{

	public Scroll(String baseUrl){
		super(baseUrl);
	}
	public Scroll(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: time scroll: Specify how long a consistent view of the index should be maintained for scrolled search
	public Scroll scroll(long scroll){
		addParams("scroll", scroll);
		return this;
	}
	// param: string scrollId: The scroll ID for scrolled search
	public Scroll scrollId(String scrollId){
		addParams("scrollId", scrollId);
		return this;
	}
	// body:The scroll ID if not passed by URL or query parameter.
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//The scroll ID
	private String scroll_id;
	public Scroll setParts(String scroll_id){
		this.scroll_id=scroll_id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
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
