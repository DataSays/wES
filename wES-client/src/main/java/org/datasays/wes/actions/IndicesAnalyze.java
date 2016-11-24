package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/indices-analyze.html
**/
public class IndicesAnalyze extends ARequestInfo{

	public IndicesAnalyze(String baseUrl){
		super(baseUrl);
	}
	public IndicesAnalyze(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: string index: The name of the index to scope the operation**/
	public IndicesAnalyze index(String index){
		addParams("index", index);
		return this;
	}
	/** param: boolean preferLocal: With `true`, specify that a local shard should be used if available, with `false`, use a random shard (default: true)**/
	public IndicesAnalyze preferLocal(boolean preferLocal){
		addParams("preferLocal", preferLocal);
		return this;
	}
	/** param: enum format: Format of the output**/
	public IndicesAnalyze format(EnumFormat format){
		addParams("format", format);
		return this;
	}
	/** body:Define analyzer/tokenizer parameters and the text on which the analysis should be performed**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The name of the index to scope the operation**/
	private String index;
	public IndicesAnalyze setParts(String index){
		this.index=index;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/_analyze
		if(index != null ){
			setUrl(index, "_analyze");
			return super.parseUrl(method);
		}
		//=>/_analyze
		setUrl("_analyze");
		return super.parseUrl(method);

	}
}
