package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumVersionType;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-termvectors.html
 **/
public class Termvectors extends RequestInfo {

	public Termvectors(String baseUrl) {
		super(baseUrl);
	}

	public Termvectors(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: boolean termStatistics: Specifies if total term frequency and document frequency should be returned.
	 **/
	public Termvectors termStatistics(boolean termStatistics) {
		addParams("termStatistics", termStatistics);
		return this;
	}

	/**
	 * param: boolean fieldStatistics: Specifies if document count, sum of document frequencies and sum of total term frequencies should be returned.
	 **/
	public Termvectors fieldStatistics(boolean fieldStatistics) {
		addParams("fieldStatistics", fieldStatistics);
		return this;
	}

	/**
	 * param: list fields: A comma-separated list of fields to return.
	 **/
	public Termvectors fields(String fields) {
		addParams("fields", fields);
		return this;
	}

	/**
	 * param: boolean offsets: Specifies if term offsets should be returned.
	 **/
	public Termvectors offsets(boolean offsets) {
		addParams("offsets", offsets);
		return this;
	}

	/**
	 * param: boolean positions: Specifies if term positions should be returned.
	 **/
	public Termvectors positions(boolean positions) {
		addParams("positions", positions);
		return this;
	}

	/**
	 * param: boolean payloads: Specifies if term payloads should be returned.
	 **/
	public Termvectors payloads(boolean payloads) {
		addParams("payloads", payloads);
		return this;
	}

	/**
	 * param: string preference: Specify the node or shard the operation should be performed on (default: random).
	 **/
	public Termvectors preference(String preference) {
		addParams("preference", preference);
		return this;
	}

	/**
	 * param: string routing: Specific routing value.
	 **/
	public Termvectors routing(String routing) {
		addParams("routing", routing);
		return this;
	}

	/**
	 * param: string parent: Parent id of documents.
	 **/
	public Termvectors parent(String parent) {
		addParams("parent", parent);
		return this;
	}

	/**
	 * param: boolean realtime: Specifies if request is real-time as opposed to near-real-time (default: true).
	 **/
	public Termvectors realtime(boolean realtime) {
		addParams("realtime", realtime);
		return this;
	}

	/**
	 * param: number version: Explicit version number for concurrency control
	 **/
	public Termvectors version(Number version) {
		addParams("version", version);
		return this;
	}

	/**
	 * param: enum versionType: Specific version type
	 **/
	public Termvectors versionType(EnumVersionType versionType) {
		addParams("versionType", versionType);
		return this;
	}

	/**
	 * body:Define parameters and or supply a document to get termvectors for. See documentation.
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**
	 * The index in which the document resides.
	 **/
	private String index;
	/**
	 * The type of the document.
	 **/
	private String type;
	/**
	 * The id of the document, when not specified a doc param should be supplied.
	 **/
	private String id;

	public Termvectors setParts(String index, String type, String id) {
		this.index = index;
		this.type = type;
		this.id = id;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/{index}/{type}/{id}/_termvectors
		if (index != null && type != null && id != null) {
			setUrl(index, type, id, "_termvectors");
			return super.parseUrl(method);
		}
		//=>/{index}/{type}/_termvectors
		if (index != null && type != null) {
			setUrl(index, type, "_termvectors");
			return super.parseUrl(method);
		}

		return null;
	}
}
