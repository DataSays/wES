package org.datasays.es2.actions;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;
import org.datasays.es2.ARequestInfo;
import org.datasays.es2.types.*;
/**
* documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-percolate.html
**/
public class CountPercolate extends ARequestInfo{

	public CountPercolate(String baseUrl){
		super(baseUrl);
	}

	/** param: list routing: A comma-separated list of specific routing values**/
	public CountPercolate routing(String routing){
		addParams("routing", routing);
		return this;
	}
	/** param: string preference: Specify the node or shard the operation should be performed on (default: random)**/
	public CountPercolate preference(String preference){
		addParams("preference", preference);
		return this;
	}
	/** param: boolean ignoreUnavailable: Whether specified concrete indices should be ignored when unavailable (missing or closed)**/
	public CountPercolate ignoreUnavailable(boolean ignoreUnavailable){
		addParams("ignoreUnavailable", ignoreUnavailable);
		return this;
	}
	/** param: boolean allowNoIndices: Whether to ignore if a wildcard indices expression resolves into no concrete indices. (This includes `_all` string or when no indices have been specified)**/
	public CountPercolate allowNoIndices(boolean allowNoIndices){
		addParams("allowNoIndices", allowNoIndices);
		return this;
	}
	/** param: enum expandWildcards: Whether to expand wildcard expression to concrete indices that are open, closed or both.**/
	public CountPercolate expandWildcards(EnumExpandWildcards expandWildcards){
		addParams("expandWildcards", expandWildcards);
		return this;
	}
	/** param: string percolateIndex: The index to count percolate the document into. Defaults to index.**/
	public CountPercolate percolateIndex(String percolateIndex){
		addParams("percolateIndex", percolateIndex);
		return this;
	}
	/** param: string percolateType: The type to count percolate document into. Defaults to type.**/
	public CountPercolate percolateType(String percolateType){
		addParams("percolateType", percolateType);
		return this;
	}
	/** param: number version: Explicit version number for concurrency control**/
	public CountPercolate version(Number version){
		addParams("version", version);
		return this;
	}
	/** param: enum versionType: Specific version type**/
	public CountPercolate versionType(EnumVersionType versionType){
		addParams("versionType", versionType);
		return this;
	}
	/** body:The count percolator request definition using the percolate DSL**/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**The index of the document being count percolated.**/
	private String index;
	/**The type of the document being count percolated.**/
	private String type;
	/**Substitute the document in the request body with a document that is known by the specified id. On top of the id, the index and type parameter will be used to retrieve the document from within the cluster.**/
	private String id;
	public CountPercolate setParts(String index,String type,String id){
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
		//=>/{index}/{type}/{id}/_percolate/count
		if(index != null && type != null && id != null ){
			setUrl(index, type, id, "_percolate", "count");
			return super.parseUrl(method);
		}
		//=>/{index}/{type}/_percolate/count
		if(index != null && type != null ){
			setUrl(index, type, "_percolate", "count");
			return super.parseUrl(method);
		}

		return null;
	}
}
