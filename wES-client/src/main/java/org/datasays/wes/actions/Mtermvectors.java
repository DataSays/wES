package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.*;
// documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-multi-termvectors.html
public class Mtermvectors extends RequestInfo{

	public Mtermvectors(String baseUrl){
		super(baseUrl);
	}
	public Mtermvectors(HttpUrl baseUrl){
		super(baseUrl);
	}

	// param: list ids: A comma-separated list of documents ids. You must define ids as parameter or set "ids" or "docs" in the request body
	public Mtermvectors ids(String ids){
		addParams("ids", ids);
		return this;
	}
	// param: boolean termStatistics: Specifies if total term frequency and document frequency should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors termStatistics(boolean termStatistics){
		addParams("termStatistics", termStatistics);
		return this;
	}
	// param: boolean fieldStatistics: Specifies if document count, sum of document frequencies and sum of total term frequencies should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors fieldStatistics(boolean fieldStatistics){
		addParams("fieldStatistics", fieldStatistics);
		return this;
	}
	// param: list fields: A comma-separated list of fields to return. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors fields(String fields){
		addParams("fields", fields);
		return this;
	}
	// param: boolean offsets: Specifies if term offsets should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors offsets(boolean offsets){
		addParams("offsets", offsets);
		return this;
	}
	// param: boolean positions: Specifies if term positions should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors positions(boolean positions){
		addParams("positions", positions);
		return this;
	}
	// param: boolean payloads: Specifies if term payloads should be returned. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors payloads(boolean payloads){
		addParams("payloads", payloads);
		return this;
	}
	// param: string preference: Specify the node or shard the operation should be performed on (default: random) .Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors preference(String preference){
		addParams("preference", preference);
		return this;
	}
	// param: string routing: Specific routing value. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors routing(String routing){
		addParams("routing", routing);
		return this;
	}
	// param: string parent: Parent id of documents. Applies to all returned documents unless otherwise specified in body "params" or "docs".
	public Mtermvectors parent(String parent){
		addParams("parent", parent);
		return this;
	}
	// param: boolean realtime: Specifies if requests are real-time as opposed to near-real-time (default: true).
	public Mtermvectors realtime(boolean realtime){
		addParams("realtime", realtime);
		return this;
	}
	// param: number version: Explicit version number for concurrency control
	public Mtermvectors version(Number version){
		addParams("version", version);
		return this;
	}
	// param: enum versionType: Specific version type
	public Mtermvectors versionType(EnumVersionType versionType){
		addParams("versionType", versionType);
		return this;
	}
	// body:Define ids, documents, parameters or a list of parameters per document here. You must at least provide a list of document ids. See documentation.
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	//The index in which the document resides.
	private String index;
	//The type of the document.
	private String type;
	public Mtermvectors setParts(String index,String type){
		this.index=index;
		this.type=type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/_mtermvectors
		if(index != null && type != null ){
			setUrl(index, type, "_mtermvectors");
			return super.parseUrl(method);
		}
		//=>/{index}/_mtermvectors
		if(index != null ){
			setUrl(index, "_mtermvectors");
			return super.parseUrl(method);
		}
		//=>/_mtermvectors
		setUrl("_mtermvectors");
		return super.parseUrl(method);

	}
}
