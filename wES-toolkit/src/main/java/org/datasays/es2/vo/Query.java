package org.datasays.es2.vo;

import org.datasays.util.collection.StrObjMap;

/**
 * Created by watano on 2016/11/19.
 */
public class Query extends StrObjMap{

    public Query() {
    }

    public Query(Object... allkv) {
        super(allkv);
    }

    public static Query MatchAll() {
        Query q = new Query("match_all", new StrObjMap());
        return q;
    }

    public static Query MatchNone() {
        Query q = new Query("match_none", new StrObjMap());
        return q;
    }

    public void match(String message) {
        put("match", new StrObjMap("message", message));
    }

    public Query match2(String query) {
        put("match", new StrObjMap("message", new StrObjMap("query", query)));
        return this;
    }

    public void match_phrase(String message) {
        put("match_phrase", new StrObjMap("message", message));
    }

    public Query match_phrase2(String query) {
        put("match_phrase", new StrObjMap("message", new StrObjMap("query", query)));
        return this;
    }

    public void match_phrase_prefix(String message) {
        put("match_phrase_prefix", new StrObjMap("message", message));
    }

    public Query match_phrase_prefix2(String query) {
        put("match_phrase_prefix", new StrObjMap("message", new StrObjMap("query", query)));
        return this;
    }
}
