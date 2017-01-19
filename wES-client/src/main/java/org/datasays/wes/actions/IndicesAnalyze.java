package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: https://www.elastic.co/guide/en/elasticsearch/reference/5.x/indices-analyze.html
public class IndicesAnalyze extends RequestInfo{

	public IndicesAnalyze(String baseUrl){
		super(baseUrl);
	}
	public IndicesAnalyze(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: string analyzer: The name of the analyzer to use
	public IndicesAnalyze analyzer(String analyzer){
		addParams("analyzer", analyzer);
		return this;
	}
	// param: list charFilter: A comma-separated list of character filters to use for the analysis
	public IndicesAnalyze charFilter(String charFilter){
		addParams("charFilter", charFilter);
		return this;
	}
	// param: string field: Use the analyzer configured for this field (instead of passing the analyzer name)
	public IndicesAnalyze field(String field){
		addParams("field", field);
		return this;
	}
	// param: list filter: A comma-separated list of filters to use for the analysis
	public IndicesAnalyze filter(String filter){
		addParams("filter", filter);
		return this;
	}
	// param: string index: The name of the index to scope the operation
	public IndicesAnalyze index(String index){
		addParams("index", index);
		return this;
	}
	// param: boolean preferLocal: With `true`, specify that a local shard should be used if available, with `false`, use a random shard (default: true)
	public IndicesAnalyze preferLocal(boolean preferLocal){
		addParams("preferLocal", preferLocal);
		return this;
	}
	// param: list text: The text on which the analysis should be performed (when request body is not used)
	public IndicesAnalyze text(String text){
		addParams("text", text);
		return this;
	}
	// param: string tokenizer: The name of the tokenizer to use for the analysis
	public IndicesAnalyze tokenizer(String tokenizer){
		addParams("tokenizer", tokenizer);
		return this;
	}
	// param: boolean explain: With `true`, outputs more advanced details. (default: false)
	public IndicesAnalyze explain(boolean explain){
		addParams("explain", explain);
		return this;
	}
	// param: list attributes: A comma-separated list of token attributes to output, this parameter works only with `explain=true`
	public IndicesAnalyze attributes(String attributes){
		addParams("attributes", attributes);
		return this;
	}
	// param: enum format: Format of the output
	public IndicesAnalyze format(EnumFormat format){
		addParams("format", format);
		return this;
	}
	// body:The text on which the analysis should be performed
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//The name of the index to scope the operation
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
