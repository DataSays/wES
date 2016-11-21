package org.datasays.es2;

import java.util.*;

import org.datasays.es2.vo.SearchQuery;
import org.datasays.es2.vo.WSearchResult;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WCfg;
import org.datasays.util.WJsonUtils;
import org.datasays.util.WPageIterator;
import org.datasays.util.http.HttpClientBuilder;
import org.datasays.util.http.RequestBuilder;
import org.datasays.util.http.RetrofitHelper;
import org.datasays.wes.client.EsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import org.datasays.util.collection.StrObjMap;

public class EsHelper2 extends RetrofitHelper {
    private static Logger LOG = LoggerFactory.getLogger(EsHelper2.class);
    protected EsService esService = null;
    private String server;
    private String user;
    private String pswd;

    public EsHelper2() {
        this(WCfg.getValue("ES.server"), WCfg.getValue("ES.user"), WCfg.getValue("ES.pswd"));
    }

    public EsHelper2(String server) {
        this(server, null, null);
    }

    public EsHelper2(String server, String user, String pswd) {
        this.server = server;
        this.user = user;
        this.pswd = pswd;

        HttpClientBuilder retrofitBuilder = new HttpClientBuilder();
        retrofitBuilder.addBaseAuth(user, pswd);
        if (WCfg.getBoolValue("ES.debug")) {
            retrofitBuilder.addLog(true);
        }

        init(retrofitBuilder, server);
        esService = create(EsService.class);
    }

    public Object getMapping(String index) throws Exception {
        return execute(esService.indices_get_mapping(index));
    }

    public Object getMapping(String index, String type) throws Exception {
        return execute(esService.indices_get_mapping(index, type));
    }

    public Object putMapping(String index, String type, Object mapping) throws Exception {
        return execute(esService.indices_put_mapping2(index, type, mapping));
    }

    public Object getIndex(String index) throws Exception {
        return execute(esService.indices_get(index));
    }

    public Object putIndexSettings(String index, Object settings) throws Exception {
        return execute(esService.indices_put_settings(index, settings));
    }

    public Object index(String index, String type, Object body) throws Exception {
        return execute(esService.index(index, type, body));
    }

    public Object index(String index, String type, String id, Object body) throws Exception {
        return execute(esService.index(index, type, id, body));
    }

    public Object get(String index, String type, String id) throws Exception {
        return get(esService.get(index, type, id));
    }

    public Object delIndex(String index) throws Exception {
        return execute(esService.indices_delete(index));
    }

    public Object createIndex(String index) throws Exception {
        return execute(esService.indices_create(index, new Object()));
    }

    public Object openIndex(String index) throws Exception {
        return execute(esService.indices_open(index, new Object()));
    }

    public Object closeIndex(String index) throws Exception {
        return execute(esService.indices_close(index, new Object()));
    }

    public Object syncedFlushIndex(String index) throws Exception {
        return execute(esService.indices_flush_synced(index));
    }

    public Object flushIndex(String index) throws Exception {
        return execute(esService.indices_flush(index));
    }

    public JsonObjGetter bulkUpdate(String index, String type, List<Object> allDoc, int retry_on_conflict) {
        StringBuffer actions = new StringBuffer();
        for (Object d : allDoc) {
            Map<?, ?> doc = (Map<?, ?>) d;
            StrObjMap json = new StrObjMap("index", new StrObjMap("_id", doc.get("id"), "_type", doc.get("type"), "_index", doc.get("index"), "_retry_on_conflict", retry_on_conflict));
            actions.append(WJsonUtils.toJson(json, false) + "\n");
            actions.append(WJsonUtils.toJson(doc, false) + "\n");
        }
        if (type != null) {
            return exec(esService.bulk(index, type, actions.toString()));
        } else {
            return exec(esService.bulk(index, actions.toString()));
        }
    }

    public <T> WSearchResult<T> searchObj(String index, String type, SearchQuery queryDSL, Class<T> cls) throws Exception {
        RequestBuilder rb = RequestBuilder.post(RequestBuilder.url(index, type, "_serach"), RequestBuilder.getJsonRequestBody(queryDSL));
        String result = fetchJson(rb);
        return WJsonUtils.toObject(result, WSearchResult.class, cls);
    }

    public <T> WPageIterator<T> search(String index, String type, SearchQuery queryDSL, Class<T> cls) {
        WPageIterator<T> pages = new WPageIterator<T>(queryDSL.getPage()) {
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
        return pages;
    }

    public <T> T fetchObj(RequestBuilder rb, Class<T> cls) throws Exception {
        String result = fetchJson(rb);
        return WJsonUtils.fromJson(result, cls);
    }

    public String fetchJson(RequestBuilder rb) throws Exception {
        rb.baseAuth(user, pswd);
        return fetchBody(rb.buid());
    }

    public Object addIndexAliases(String index, String alias) throws Exception {
        return execute(esService.indices_put_alias(index, alias, new Object()));
    }

    public Object delIndexAliases(String index, String alias) throws Exception {
        return execute(esService.indices_delete_alias(index, alias));
    }

    public boolean hasIndex(String index) throws Exception {
        return has(esService.indices_exists(index));
    }

    public boolean hasIndexAlias(String index) throws Exception {
        return has(esService.indices_exists_alias(index));
    }

    public Set<String> getIndexTypes(String index) throws Exception {
        JsonObjGetter result = exec(esService.indices_get_mapping(index));
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
    public void createIndex(String index, int number_of_shards, int number_of_replicas) {
        StrObjMap settings = new StrObjMap("settings", new StrObjMap("number_of_shards", number_of_shards, "number_of_replicas", number_of_replicas));
        exec(esService.indices_create(index, settings));
    }

    /**
     * 重建Index
     *
     * @param oldIndex
     * @param newIndex
     */
    public void reIndex(String oldIndex, String newIndex, boolean keepVersion) {
        StrObjMap param = new StrObjMap("source", new StrObjMap("index", oldIndex), "dest", new StrObjMap("index", newIndex));
        if (keepVersion) {
            param.putInto("version_type", "external", "dest");
        }
        JsonObjGetter result = exec(esService.reindex(param));
        if (result != null && !result.bool("timed_out") && (result.list("failures") == null || result.list("failures").size() <= 0)) {
            LOG.info("reIndex ok!" + result.toString());
        } else if (result != null) {
            LOG.warn(result.toString());
        }
    }

    public void handleError(Response<?> response) throws Exception {
        LOG.debug("code:" + response.code());
        LOG.debug("message:" + response.message());
        String errorJson = response.errorBody().string();
        LOG.error(errorJson);
        JsonObjGetter error = WJsonUtils.fromJson(errorJson);
        if (error.obj("error") != null) {
            error = error.obj("error");
            LOG.debug(error.str("type") + "\n" + error.str("reason"));
            throw new Exception(error.str("type") + "\n" + error.str("reason"));
        } else {
            throw new Exception(errorJson);
        }
    }
}
