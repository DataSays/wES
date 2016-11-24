package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;

/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-get.html
**/
public class Exists extends ARequestInfo{

	public Exists(String baseUrl){
		super(baseUrl);
	}
	public Exists(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: string parent: The ID of the parent document**/
	public Exists parent(String parent){
		addParams("parent", parent);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public Exists preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: boolean realtime: Specify whether to perform the operation in realtime or search mode**/
	public Exists realtime(boolean realtime){
		addParams("realtime", realtime);
		return this;
	}
	/** param: boolean refresh: Refresh the shard containing the document before performing the operation**/
	public Exists refresh(boolean refresh){
		addParams("refresh", refresh);
		return this;
	}
	/** param: string routing: Specific routing value**/
	public Exists routing(String routing){
		addParams("routing", routing);
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
	/**The type of the document (use `_all` to fetch the first document matching the ID across all types)**/
	private String type;
	public Exists setParts(String index,String type,String id){
		this.id=id;
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"HEAD".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/{id}
		if(id != null && index != null && type != null ){
			setUrl(index, type, id);
			return super.parseUrl(method);
		}

		return null;
	}
}
