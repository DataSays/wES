package org.datasays.wes;

import okhttp3.OkHttpClient;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WJsonUtils;
import org.datasays.util.WPageIterator;
import org.datasays.wes.actions.*;
import org.datasays.wes.client.EsHelper;
import org.datasays.wes.core.HttpException;
import org.datasays.wes.core.IConvert;
import org.datasays.wes.core.JsonObj;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.toolkit.WGsonConvert;
import org.datasays.wes.vo.DeleteByQueryResult;
import org.datasays.wes.vo.IEsItem;
import org.datasays.wes.vo.Query;
import org.datasays.wes.vo.SearchQuery;
import org.datasays.wes.vo.WEsDoc;
import org.datasays.wes.vo.WSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EsBaseService {
	private static Logger LOG = LoggerFactory.getLogger(EsBaseService.class);
	protected EsHelper esHelper;

	public EsBaseService() {
	}

	public void init(String server, OkHttpClient client, IConvert convert) {
		esHelper = new EsHelper();
		if (client == null) {
			client = new OkHttpClient.Builder().build();
		}
		if (convert == null) {
			convert = new WGsonConvert();
		}
		esHelper.init(server, client, convert);
		esHelper.setLogFlag(LOG.isDebugEnabled(), LOG.isDebugEnabled(), LOG.isDebugEnabled());
	}

	public Object getMapping(String index) throws Exception {
		return getMapping(index, null);
	}

	public Object getMapping(String index, String type) throws Exception {
		IndicesGetMapping action = esHelper.indicesGetMapping(index, type);
		return esHelper.get(action, Object.class);
	}

	public Object putMapping(String index, String type, Object mapping) throws Exception {
		IndicesPutMapping action = esHelper.indicesPutMapping(index, type);
		action.setBody(mapping);
		return esHelper.put(action, Object.class);
	}

	public Object getIndex(String index) throws Exception {
		IndicesGet action = esHelper.indicesGet(index, null);
		return esHelper.get(action, Object.class);
	}

	public Object putIndexSettings(String index, Object settings) throws Exception {
		IndicesPutSettings action = esHelper.indicesPutSettings(index);
		action.setBody(settings);
		return esHelper.post(action, Object.class);
	}

	public Object index(String index, String type, Object body) throws Exception {
		Index action = esHelper.index(index, type, null);
		action.setBody(body);
		return esHelper.post(action, Object.class);
	}

	public Object index(String index, String type, String id, Object body) throws Exception {
		Index action = esHelper.index(index, type, id);
		action.setBody(body);
		return esHelper.post(action, Object.class);
	}

	public Object get(String index, String type, String id) throws Exception {
		Get action = esHelper.get(index, type, id);
		return esHelper.get(action, Object.class);
	}

	public boolean delIndex(String index) throws Exception {
		IndicesDelete action = esHelper.indicesDelete(index);
		return esHelper.delete(action);
	}

	public Object openIndex(String index) throws Exception {
		IndicesOpen action = esHelper.indicesOpen(index);
		return esHelper.post(action, Object.class);
	}

	public Object closeIndex(String index) throws Exception {
		IndicesClose action = esHelper.indicesClose(index);
		return esHelper.post(action, Object.class);
	}

	public Object syncedFlushIndex(String index) throws Exception {
		IndicesFlushSynced action = esHelper.indicesFlushSynced(index);
		return esHelper.post(action, Object.class);
	}

	public Object flushIndex(String index) throws Exception {
		IndicesFlush action = esHelper.indicesFlush(index);
		return esHelper.post(action, Object.class);
	}

	public JsonObjGetter bulkUpdate(String index, String type, List<Object> allDoc, int retry_on_conflict) throws HttpException {
		StringBuffer actions = new StringBuffer();
		for (Object d : allDoc) {
			Map<?, ?> doc = (Map<?, ?>) d;
			JsonObj json = new JsonObj("index", new JsonObj("_id", doc.get("id"), "_type", doc.get("type"), "_index", doc.get("index"), "_retry_on_conflict", retry_on_conflict));
			actions.append(WJsonUtils.toJson(json, false) + "\n");
			actions.append(WJsonUtils.toJson(doc, false) + "\n");
		}
		Bulk action = esHelper.bulk(index, type);
		action.setBody(actions.toString());
		return new JsonObjGetter(esHelper.post(action, Object.class));
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String index, String type, String id, Class<T> cls) throws Exception {
		Get action = esHelper.get(index, type, id);
		WEsDoc<T> resultDoc = (WEsDoc<T>) esHelper.get(action, WEsDoc.class, cls);
		if (resultDoc != null && resultDoc.getFound()) {
			return resultDoc.getSource();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> WSearchResult<T> searchObj(String index, String type, SearchQuery queryDSL, Class<T> cls) throws HttpException {
		Search action = esHelper.search(index, type);
		action.setBody(queryDSL);
		return (WSearchResult<T>) esHelper.post(action, WSearchResult.class, cls);
	}

	public <T> WPageIterator<T> search(String index, String type, SearchQuery queryDSL, Class<T> cls) {
		WPageIterator<T> pageIterator = new WPageIterator<T>(queryDSL.getPage()) {
			public void doSearch() {
				try {
					queryDSL.setPage(this.getPage());
					WSearchResult<T> result = searchObj(index, type, queryDSL, cls);
					update(result.getData(), result.getTotal());
				} catch (HttpException e) {
					LOG.error(e.toText());
					LOG.error(e.getMessage(), e);
				}
			}
		};
		return pageIterator;
	}

	public Object indicesPutAlias(String index, String alias) throws Exception {
		IndicesPutAlias action = esHelper.indicesPutAlias(index, alias);
		return esHelper.post(action, Object.class);
	}

	public boolean indicesDeleteAlias(String index, String alias) throws Exception {
		IndicesDeleteAlias action = esHelper.indicesDeleteAlias(index, alias);
		return esHelper.delete(action);
	}

	public boolean hasIndex(String index) throws Exception {
		IndicesExists action = esHelper.indicesExists(index);
		return esHelper.has(action);
	}

	public boolean hasIndexAlias(String index, String name) throws Exception {
		IndicesExistsAlias action = esHelper.indicesExistsAlias(index, name);
		return esHelper.has(action);
	}

	public Set<String> getIndexTypes(String index) throws Exception {
		JsonObjGetter result = new JsonObjGetter(getMapping(index));
		Set<String> types = new HashSet<String>();
		for (Object o : result.obj(index).map("mappings").keySet()) {
			types.add(o.toString());
		}
		return types;
	}

	public Set<String> getAllIndex() throws Exception {
		RequestInfo action = esHelper.indicesStats(null, null);
		JsonObjGetter stats = new JsonObjGetter(esHelper.get(action, Object.class));
		Map<String, Object> indices = (Map<String, Object>) stats.map("indices");
		Set<String> allIndex = new HashSet<String>();
		if (indices != null) {
			allIndex.addAll(indices.keySet());
		}
		return allIndex;
	}

	public Object createIndex(String index, int number_of_shards, int number_of_replicas) throws HttpException {
		JsonObj settings = new JsonObj("settings", new JsonObj("number_of_shards", number_of_shards, "number_of_replicas", number_of_replicas));
		IndicesCreate action = esHelper.indicesCreate(index);
		action.setBody(settings);
		return esHelper.put(action, Object.class);
	}

	public void reIndex(String oldIndex, String newIndex, boolean keepVersion) throws HttpException {
		JsonObj param = new JsonObj("source", new JsonObj("index", oldIndex), "dest", new JsonObj("index", newIndex));
		if (keepVersion) {
			param.putInto("version_type", "external", "dest");
		}
		Reindex action = esHelper.reindex();
		action.setBody(param);
		JsonObjGetter result = new JsonObjGetter(esHelper.post(action, Object.class));
		if (result != null && !result.bool("timed_out") && (result.list("failures") == null || result.list("failures").size() <= 0)) {
			LOG.info("reIndex ok!" + result.toString());
		} else if (result != null) {
			LOG.warn(result.toString());
		}
	}

	public boolean delete(String index, String type, String id) throws HttpException {
		Delete action = esHelper.delete(index, type, id);
		return esHelper.delete(action);
	}

	public DeleteByQueryResult deleteByQuery(String index, String type, Query query) throws HttpException {
		DeleteByQuery action = esHelper.deleteByQuery(index, type);
		action.setBody(new JsonObj("query", query));
		return esHelper.post(action, DeleteByQueryResult.class);
	}

	public <T extends IEsItem> T save(T doc) throws Exception {
		if (doc == null) {
			return null;
		} else {
			return index(doc);
		}
	}

	public <T extends IEsItem> T index(T doc) throws Exception {
		Index action = esHelper.index(doc.getIndex(), doc.getType(), doc.getId());
		action.setBody(doc);
		WEsDoc<?> resultDoc = esHelper.post(action, WEsDoc.class, Object.class);
		if (resultDoc != null && resultDoc.getId() != null) {
			doc.setId(resultDoc.getId());
		}
		return doc;
	}

	public <T extends IEsItem> T get(T doc) throws Exception {
		if (doc != null) {
			T doc2 = get(doc.getIndex(), doc.getType(), doc.getId(), (Class<T>) doc.getClass());
			if (doc2 != null) {
				doc2.setIndex(doc.getIndex());
				doc2.setType(doc.getType());
				doc2.setId(doc.getId());
				return doc2;
			}
		}
		return null;
	}

	public boolean delete(IEsItem doc) throws HttpException {
		Delete action = esHelper.delete(doc.getIndex(), doc.getType(), doc.getId());
		return esHelper.delete(action);
	}
}
