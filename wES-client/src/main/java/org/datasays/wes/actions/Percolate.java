package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.ARequestInfo;
import org.datasays.wes.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
**/
public class Percolate extends ARequestInfo{

	public Percolate(String baseUrl){
		super(baseUrl);
	}
	public Percolate(HttpUrl baseUrl){
		super(baseUrl);
	}

	/** param: list routing: A comma-separated list of specific routing values**/
	public Percolate routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public Percolate preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public Percolate ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public Percolate allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public Percolate expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: string percolateIndex: The index to percolate the document into. Defaults to index.**/
	public Percolate percolateIndex(String percolateIndex){
		addParams("percolateIndex", percolateIndex);
		return this;
	}
	/** param: string percolateType: The type to percolate document into. Defaults to type.**/
	public Percolate percolateType(String percolateType){
		addParams("percolateType", percolateType);
		return this;
	}
	/** param: string percolateRouting: The routing value to use when percolating the existing document.**/
	public Percolate percolateRouting(String percolateRouting){
		addParams("percolateRouting", percolateRouting);
		return this;
	}
	/** param: string percolatePreference: Which shard to prefer when executing the percolate request.**/
	public Percolate percolatePreference(String percolatePreference){
		addParams("percolatePreference", percolatePreference);
		return this;
	}
	/** param: enum percolateFormat: Return an array of matching query IDs instead of objects**/
	public Percolate percolateFormat(EnumPercolateFormat percolateFormat){
		addParams("percolateFormat", percolateFormat);
		return this;
	}
	/** param: number version: Explicit version number for concurrency control**/
	public Percolate version(Number version){
		addParams("version", version);
		return this;
	}
	/** param: enum versionType: Specific version type**/
	public Percolate versionType(EnumVersionType versionType){
		addParams("versionType", versionType);
		return this;
	}
	/** body:The percolator request definition using the percolate DSL**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The index of the document being percolated.**/
	private String index;
	/**The type of the document being percolated.**/
	private String type;
	/**Substitute the document in the request body with a document that is known by the specified id. On top of the id, the index and type parameter will be used to retrieve the document from within the cluster.**/
	private String id;
	public Percolate setParts(String index,String type,String id){
		this.index=index;
		this.type=type;
		this.id=id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if(!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)){
			throw new IllegalArgumentException("Unsupported method:"+method);
		}
		//=>/{index}/{type}/{id}/_percolate
		if(index != null && type != null && id != null ){
			setUrl(index, type, id, "_percolate");
			return super.parseUrl(method);
		}
		//=>/{index}/{type}/_percolate
		if(index != null && type != null ){
			setUrl(index, type, "_percolate");
			return super.parseUrl(method);
		}

		return null;
	}
}
