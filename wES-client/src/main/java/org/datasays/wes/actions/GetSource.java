package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-get.html
**/
public class GetSource extends ARequestInfo{

	public GetSource(String baseUrl){
		super(baseUrl);
	}
	public GetSource(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: string parent: The ID of the parent document**/
	public GetSource parent(String parent){
		addParams("parent", parent);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public GetSource preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: boolean realtime: Specify whether to perform the operation in realtime or search mode**/
	public GetSource realtime(boolean realtime){
		addParams("realtime", realtime);
		return this;
	}
	/** param: boolean refresh: Refresh the shard containing the document before performing the operation**/
	public GetSource refresh(boolean refresh){
		addParams("refresh", refresh);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public GetSource routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: list source: True or false to return the _source field or not, or a list of fields to return**/
	public GetSource source(String source){
		addParams("source", source);
		return this;
	}
	/** param: list sourceExclude: A list of fields to exclude from the returned _source field**/
	public GetSource sourceExclude(String sourceExclude){
		addParams("sourceExclude", sourceExclude);
		return this;
	}
	/** param: list sourceInclude: A list of fields to extract and return from the _source field**/
	public GetSource sourceInclude(String sourceInclude){
		addParams("sourceInclude", sourceInclude);
		return this;
	}
	/** param: number version: Explicit version number for concurrency control**/
	public GetSource version(Number version){
		addParams("version", version);
		return this;
	}
	/** param: enum versionType: Specific version type**/
	public GetSource versionType(EnumVersionType versionType){
		addParams("versionType", versionType);
		return this;
	}
	/** body:null**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The document ID**/
	private String id;
	/**The name of the index**/
	private String index;
	/**The type of the document; use `_all` to fetch the first document matching the ID across all types**/
	private String type;
	public GetSource setParts(String index,String type,String id){
		this.id=id;
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/{id}/_source
		if(id != null && index != null && type != null ){
			setUrl(index, type, id, "_source");
			return super.parseUrl(method);
		}

		return null;
	}
}
