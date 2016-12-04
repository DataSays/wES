package org.datasays.wes.actions;

import okhttp3.HttpUrl;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.types.EnumOpType;
import org.datasays.wes.types.EnumRefresh;
import org.datasays.wes.types.EnumVersionType;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/docs-index_.html
 **/
public class Index extends RequestInfo {

	public Index(String baseUrl) {
		super(baseUrl);
	}

	public Index(HttpUrl baseUrl) {
		super(baseUrl);
	}

	/**
	 * param: string waitForActiveShards: Sets the number of shard copies that must be active before proceeding with the index operation. Defaults to 1, meaning the primary shard only. Set to `all` for all shard copies, otherwise set to any non-negative value less than or equal to the total number of copies for the shard (number of replicas + 1)
	 **/
	public Index waitForActiveShards(String waitForActiveShards) {
		addParams("waitForActiveShards", waitForActiveShards);
		return this;
	}

	/**
	 * param: enum opType: Explicit operation type
	 **/
	public Index opType(EnumOpType opType) {
		addParams("opType", opType);
		return this;
	}

	/**
	 * param: string parent: ID of the parent document
	 **/
	public Index parent(String parent) {
		addParams("parent", parent);
		return this;
	}

	/**
	 * param: enum refresh: If `true` then refresh the affected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.
	 **/
	public Index refresh(EnumRefresh refresh) {
		addParams("refresh", refresh);
		return this;
	}

	/**
	 * param: string routing: Specific routing value
	 **/
	public Index routing(String routing) {
		addParams("routing", routing);
		return this;
	}

	/**
	 * param: time timeout: Explicit operation timeout
	 **/
	public Index timeout(long timeout) {
		addParams("timeout", timeout);
		return this;
	}

	/**
	 * param: time timestamp: Explicit timestamp for the document
	 **/
	public Index timestamp(long timestamp) {
		addParams("timestamp", timestamp);
		return this;
	}

	/**
	 * param: time ttl: Expiration time for the document
	 **/
	public Index ttl(long ttl) {
		addParams("ttl", ttl);
		return this;
	}

	/**
	 * param: number version: Explicit version number for concurrency control
	 **/
	public Index version(Number version) {
		addParams("version", version);
		return this;
	}

	/**
	 * param: enum versionType: Specific version type
	 **/
	public Index versionType(EnumVersionType versionType) {
		addParams("versionType", versionType);
		return this;
	}

	/**
	 * param: string pipeline: The pipeline id to preprocess incoming documents with
	 **/
	public Index pipeline(String pipeline) {
		addParams("pipeline", pipeline);
		return this;
	}

	/**
	 * body*:The document
	 **/
	@Override
	public void setBody(Object body) {
		super.setBody(body);
	}

	/**
	 * Document ID
	 **/
	private String id;
	/**
	 * The name of the index
	 **/
	private String index;
	/**
	 * The type of the document
	 **/
	private String type;

	public Index setParts(String index, String type, String id) {
		this.id = id;
		this.index = index;
		this.type = type;

		return this;
	}

	@Override
	public String parseUrl(String method) {
		if (!"POST".equalsIgnoreCase(method) && !"PUT".equalsIgnoreCase(method)) {
			throw new IllegalArgumentException("Unsupported method:" + method);
		}
		//=>/{index}/{type}/{id}
		if (id != null && index != null && type != null) {
			setUrl(index, type, id);
			return super.parseUrl(method);
		}
		//=>/{index}/{type}
		if (index != null && type != null) {
			setUrl(index, type);
			return super.parseUrl(method);
		}

		return null;
	}
}
