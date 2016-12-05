package org.datasays.wes.vo;


import org.datasays.wes.core.JsonObj;

/**
 * Created by watano on 2016/11/19.
 */
public class Query extends JsonObj {
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
		Query q = new Query("match_all", new JsonObj());
		return q;
	}

	public static Query MatchNone() {
		Query q = new Query("match_none", new JsonObj());
		return q;
	}

	public void match(String message) {
		put("match", new JsonObj("message", message));
	}

	public Query match2(String query) {
		put("match", new JsonObj("message", new JsonObj("query", query)));
		return this;
	}

	public void match_phrase(String message) {
		put("match_phrase", new JsonObj("message", message));
	}

	public Query match_phrase2(String query) {
		put("match_phrase", new JsonObj("message", new JsonObj("query", query)));
		return this;
	}

	public void match_phrase_prefix(String message) {
		put("match_phrase_prefix", new JsonObj("message", message));
	}

	public Query match_phrase_prefix2(String query) {
		put("match_phrase_prefix", new JsonObj("message", new JsonObj("query", query)));
		return this;
	}

	public static Query bool(JsonObj must, JsonObj must_not, JsonObj should) {
		if (must == null) {
			must = new JsonObj();
		}
		if (must_not == null) {
			must_not = new JsonObj();
		}
		if (should == null) {
			should = new JsonObj();
		}
		return new Query("bool", new JsonObj("must", must, "must_not", must_not, "should", should));
	}

	private static JsonObj q(String type, String field, String value) {
		return new JsonObj(type, new JsonObj(field, value));
	}

	public static JsonObj term(String field, String value) {
		return q(term, field, value);
	}

	public static JsonObj wildcard(String field, String value) {
		return q(wildcard, field, value);
	}

	public static JsonObj prefix(String field, String value) {
		return q(prefix, field, value);
	}

	public static JsonObj fuzzy(String field, String value) {
		return q(fuzzy, field, value);
	}

	public static JsonObj range(String field, String value) {
		return q(range, field, value);
	}

	public static JsonObj query_string(String field, String value) {
		return q(query_string, field, value);
	}

	public static JsonObj text(String field, String value) {
		return q(text, field, value);
	}

	public static JsonObj missing(String field, String value) {
		return q(missing, field, value);
	}
}
