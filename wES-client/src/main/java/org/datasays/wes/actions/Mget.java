package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-multi-get.html
**/
public class Mget extends ARequestInfo{

	public Mget(String baseUrl){
		super(baseUrl);
	}
	public Mget(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: list storedFields: A comma-separated list of stored fields to return in the response**/
	public Mget storedFields(String storedFields){
		addParams("storedFields", storedFields);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public Mget preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: boolean realtime: Specify whether to perform the operation in realtime or search mode**/
	public Mget realtime(boolean realtime){
		addParams("realtime", realtime);
		return this;
	}
	/** param: boolean refresh: Refresh the shard containing the document before performing the operation**/
	public Mget refresh(boolean refresh){
		addParams("refresh", refresh);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public Mget routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: list source: True or false to return the _source field or not, or a list of fields to return**/
	public Mget source(String source){
		addParams("source", source);
		return this;
	}
	/** param: list sourceExclude: A list of fields to exclude from the returned _source field**/
	public Mget sourceExclude(String sourceExclude){
		addParams("sourceExclude", sourceExclude);
		return this;
	}
	/** param: list sourceInclude: A list of fields to extract and return from the _source field**/
	public Mget sourceInclude(String sourceInclude){
		addParams("sourceInclude", sourceInclude);
		return this;
	}
	/** body*:Document identifiers; can be either `docs` (containing full document information) or `ids` (when index and type is provided in the URL.**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The name of the index**/
	private String index;
	/**The type of the document**/
	private String type;
	public Mget setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/_mget
		if(index != null && type != null ){
			setUrl(index, type, "_mget");
			return super.parseUrl(method);
		}
		//=>/{index}/_mget
		if(index != null ){
			setUrl(index, "_mget");
			return super.parseUrl(method);
		}
		//=>/_mget
		setUrl("_mget");
		return super.parseUrl(method);

	}
}
