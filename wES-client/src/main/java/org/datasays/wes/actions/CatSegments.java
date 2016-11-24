package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/cat-segments.html
**/
public class CatSegments extends ARequestInfo{

	public CatSegments(String baseUrl){
		super(baseUrl);
	}
	public CatSegments(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: string format: a short version of the Accept header, e.g. json, yaml**/
	public CatSegments format(String format){
		addParams("format", format);
		return this;
	}
	/** param: list h: Comma-separated list of column names to display**/
	public CatSegments h(String h){
		addParams("h", h);
		return this;
	}
	/** param: boolean help: Return help information**/
	public CatSegments help(boolean help){
		addParams("help", help);
		return this;
	}
	/** param: list s: Comma-separated list of column names or column aliases to sort by**/
	public CatSegments s(String s){
		addParams("s", s);
		return this;
	}
	/** param: boolean v: Verbose mode. Display column headers**/
	public CatSegments v(boolean v){
		addParams("v", v);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**A comma-separated list of index names to limit the returned information**/
	private String index;
	public CatSegments setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/_cat/segments/{index}
		if(index != null ){
			setUrl("_cat", "segments", index);
			return super.parseUrl(method);
		}
		//=>/_cat/segments
		setUrl("_cat", "segments");
		return super.parseUrl(method);

	}
}
