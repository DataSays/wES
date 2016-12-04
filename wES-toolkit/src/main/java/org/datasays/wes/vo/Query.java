package org.datasays.wes.vo;

import org.datasays.util.collection.StrObjMap;

/**
 * Created by watano on 2016/11/19.
 */
public class Query extends StrObjMap {
	public static final String term = "term";
	public static final String wildcard = "wildcard";
	public static final String prefix = "prefix";
	public static final String fuzzy = "fuzzy";
	public static final String range = "range";
	public static final String query_string = "query_string";
	public static final String text = "text";
	public static final String missing = "missing";

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

	public static Query bool(StrObjMap must, StrObjMap must_not, StrObjMap should) {
		if (must == null) {
			must = new StrObjMap();
		}
		if (must_not == null) {
			must_not = new StrObjMap();
		}
		if (should == null) {
			should = new StrObjMap();
		}
		return new Query("bool", new StrObjMap("must", must, "must_not", must_not, "should", should));
	}

	private static StrObjMap q(String type, String field, String value) {
		return new StrObjMap(type, new StrObjMap(field, value));
	}

	public static StrObjMap term(String field, String value) {
		return q(term, field, value);
	}

	public static StrObjMap wildcard(String field, String value) {
		return q(wildcard, field, value);
	}

	public static StrObjMap prefix(String field, String value) {
		return q(prefix, field, value);
	}

	public static StrObjMap fuzzy(String field, String value) {
		return q(fuzzy, field, value);
	}

	public static StrObjMap range(String field, String value) {
		return q(range, field, value);
	}

	public static StrObjMap query_string(String field, String value) {
		return q(query_string, field, value);
	}

	public static StrObjMap text(String field, String value) {
		return q(text, field, value);
	}

	public static StrObjMap missing(String field, String value) {
		return q(missing, field, value);
	}
}
