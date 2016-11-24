package org.datasays.wes;

import java.util.*;

import org.datasays.util.http.HttpClientBuilder;
import org.datasays.wes.actions.*;
import org.datasays.wes.client.BaseEsHelper;
import org.datasays.wes.core.HttpException;
import org.datasays.wes.toolkit.WGsonConvert;
import org.datasays.wes.vo.SearchQuery;
import org.datasays.wes.vo.WEsDoc;
import org.datasays.wes.vo.WSearchResult;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WCfg;
import org.datasays.util.WJsonUtils;
import org.datasays.util.WPageIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.datasays.util.collection.StrObjMap;

public class EsHelper2 extends BaseEsHelper {
    private static Logger LOG = LoggerFactory.getLogger(EsHelper2.class);

    public EsHelper2() {
        this(WCfg.getValue("ES.server"), WCfg.getValue("ES.user"), WCfg.getValue("ES.pswd"));
    }

    public EsHelper2(String server) {
        this(server, null, null);
    }

    public EsHelper2(String server, String user, String pswd) {
        super(server, user, pswd);
        HttpClientBuilder cbulder = new HttpClientBuilder();
        cbulder.addBaseAuth(user, pswd);
        init(cbulder.build(), new WGsonConvert(), LOG.isInfoEnabled());
    }

    public Object getMapping(String index) throws Exception {
        return getMapping(index, null);
    }

    public Object getMapping(String index, String type) throws Exception {
        IndicesGetMapping action = new IndicesGetMapping(server).setParts(index, type);
        return get(action, Object.class);
    }

    public Object putMapping(String index, String type, Object mapping) throws Exception {
        IndicesPutMapping action = new IndicesPutMapping(server).setParts(index, type);
        action.setBody(mapping);
        return put(action, Object.class);
    }

    public Object getIndex(String index) throws Exception {
        IndicesGet action = new IndicesGet(server).setParts(index, null);
        return get(action, Object.class);
    }

    public Object putIndexSettings(String index, Object settings) throws Exception {
        IndicesPutSettings action = new IndicesPutSettings(server).setParts(index);
        action.setBody(settings);
        return post(action, Object.class);
    }

    public Object index(String index, String type, Object body) throws Exception {
        Index action = new Index(server).setParts(index, type, null);
        action.setBody(body);
        return post(action, Object.class);
    }

    public Object index(String index, String type, String id, Object body) throws Exception {
        Index action = new Index(server).setParts(index, type, id);
        action.setBody(body);
        return post(action, Object.class);
    }

    public Object get(String index, String type, String id) throws Exception {
        Get action = new Get(server).setParts(index, type, id);
        return get(action, Object.class);
    }

    public Object delIndex(String index) throws Exception {
        IndicesDelete action = new IndicesDelete(server).setParts(index);
        return delete(action, Object.class);
    }

    public Object openIndex(String index) throws Exception {
        IndicesOpen action = new IndicesOpen(server).setParts(index);
        return post(action, Object.class);
    }

    public Object closeIndex(String index) throws Exception {
        IndicesClose action = new IndicesClose(server).setParts(index);
        return post(action, Object.class);
    }

    public Object syncedFlushIndex(String index) throws Exception {
        IndicesFlushSynced action = new IndicesFlushSynced(server).setParts(index);
        return post(action, Object.class);
    }

    public Object flushIndex(String index) throws Exception {
        IndicesFlush action = new IndicesFlush(server).setParts(index);
        return post(action, Object.class);
    }

    public JsonObjGetter bulkUpdate(String index, String type, List<Object> allDoc, int retry_on_conflict) throws HttpException {
        StringBuffer actions = new StringBuffer();
        for (Object d : allDoc) {
            Map<?, ?> doc = (Map<?, ?>) d;
            StrObjMap json = new StrObjMap("index", new StrObjMap("_id", doc.get("id"), "_type", doc.get("type"), "_index", doc.get("index"), "_retry_on_conflict", retry_on_conflict));
            actions.append(WJsonUtils.toJson(json, false) + "\n");
            actions.append(WJsonUtils.toJson(doc, false) + "\n");
        }
        Bulk action = new Bulk(server).setParts(index, type);
        action.setBody(actions.toString());
        return new JsonObjGetter(post(action, Object.class));
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String index, String type, String id, Class<T> cls) throws Exception {
        Get getAction = new Get(server).setParts(index, type, id);
        WEsDoc<T> resultDoc = (WEsDoc<T>) get(getAction, WEsDoc.class, cls);
        if (resultDoc != null && resultDoc.getFound()) {
            return resultDoc.getSource();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> WSearchResult<T> searchObj(String index, String type, SearchQuery queryDSL, Class<T> cls) throws Exception {
        Search searchRequest = new Search(server).setParts(index, type);
        searchRequest.setBody(queryDSL);
        return (WSearchResult<T>) post(searchRequest, WSearchResult.class, cls);
    }

    public <T> WPageIterator<T> search(String index, String type, SearchQuery queryDSL, Class<T> cls) {
        WPageIterator<T> pageIterator = new WPageIterator<T>(queryDSL.getPage()) {
            public void doSearch() {
                try {
                    queryDSL.setPage(this.getPage());
                    WSearchResult<T> result = searchObj(index, type, queryDSL, cls);
                    update(result.getData(), result.getTotal());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return pageIterator;
    }

    public Object indicesPutAlias(String index, String alias) throws Exception {
        IndicesPutAlias action = new IndicesPutAlias(server).setParts(index, alias);
        return post(action, Object.class);
    }

    public Object indicesDeleteAlias(String index, String alias) throws Exception {
        IndicesDeleteAlias action = new IndicesDeleteAlias(server).setParts(index, alias);
        return delete(action, Object.class);
    }

    public boolean hasIndex(String index) throws Exception {
        IndicesExists action = new IndicesExists(server).setParts(index);
        return has(action);
    }

    public boolean hasIndexAlias(String index, String name) throws Exception {
        IndicesExistsAlias action = new IndicesExistsAlias(server).setParts(index, name);
        return has(action);
    }

    public Set<String> getIndexTypes(String index) throws Exception {
        JsonObjGetter result = new JsonObjGetter(getMapping(index));
        Set<String> types = new HashSet<String>();
        for (Object o : result.obj(index).map("mappings").keySet()) {
            types.add(o.toString());
        }
        return types;
    }

    /**
     * 创建index
     *
     * @param index
     * @param number_of_shards
     * @param number_of_replicas
     */
    public Object createIndex(String index, int number_of_shards, int number_of_replicas) throws HttpException{
        StrObjMap settings = new StrObjMap("settings", new StrObjMap("number_of_shards", number_of_shards, "number_of_replicas", number_of_replicas));
        IndicesCreate action = new IndicesCreate(server).setParts(index);
        action.setBody(settings);
        return put(action, Object.class);
    }

    /**
     * 重建Index
     *
     * @param oldIndex
     * @param newIndex
     */
    public void reIndex(String oldIndex, String newIndex, boolean keepVersion) throws HttpException {
        StrObjMap param = new StrObjMap("source", new StrObjMap("index", oldIndex), "dest", new StrObjMap("index", newIndex));
        if (keepVersion) {
            param.putInto("version_type", "external", "dest");
        }
        Reindex action = new Reindex(server).setParts();
        action.setBody(param);
        JsonObjGetter result = new JsonObjGetter(post(action, Object.class));
        if (result != null && !result.bool("timed_out") && (result.list("failures") == null || result.list("failures").size() <= 0)) {
            LOG.info("reIndex ok!" + result.toString());
        } else if (result != null) {
            LOG.warn(result.toString());
        }
    }

    public JsonObjGetter delete(String index, String type, String id) throws HttpException {
        Delete action = new Delete(server).setParts(index, type, id);
        return new JsonObjGetter(delete(action, Object.class));
    }

    public JsonObjGetter deleteByQuery(String index, String type, Object query) throws HttpException {
        DeleteByQuery action = new DeleteByQuery(server).setParts(index, type);
        action.setBody(query);
        return new JsonObjGetter(delete(action, Object.class));
    }

    public <T extends EsItem> T save(T doc) throws Exception {
        if (doc == null) {
            return null;
        } else {
            return index(doc);
        }
    }

    public <T extends EsItem> T index(T doc) throws Exception {
        Index action = new Index(server).setParts(doc.getIndex(), doc.getType(), doc.getId());
        action.setBody(doc);
        WEsDoc<?> resultDoc = post(action, WEsDoc.class, Object.class);
        if (resultDoc != null && resultDoc.getId() != null) {
            doc.setId(resultDoc.getId());
        }
        return doc;
    }

    public <T extends EsItem> T get(T doc) throws Exception {
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

    public JsonObjGetter delete(EsItem doc) throws HttpException {
        Delete action = new Delete(server).setParts(doc.getIndex(), doc.getType(), doc.getId());
        return new JsonObjGetter(delete(action, Object.class));
    }
}
