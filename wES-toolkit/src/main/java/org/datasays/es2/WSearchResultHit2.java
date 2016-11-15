package org.datasays.es2;

import java.util.List;
import java.util.Map;

import org.datasays.util.JsonObjGetter;

public class WSearchResultHit2 extends EsItem {
	private static final long			serialVersionUID	= 5401725296406907684L;
	private JsonObjGetter				source;
	private JsonObjGetter				explanation;
	private Map<String, List<String>>	highlight;
	private List<String>				sort;
	private Double						score;

	public WSearchResultHit2(JsonObjGetter source) {
		this(source, null);
	}

	public WSearchResultHit2(JsonObjGetter source, JsonObjGetter explanation) {
		this(source, explanation, null, null);
	}

	public WSearchResultHit2(JsonObjGetter source, JsonObjGetter explanation, Map<String, List<String>> highlight, List<String> sort) {
		this(source, explanation, highlight, sort, null, null, null, null);
	}

	public WSearchResultHit2(JsonObjGetter source, JsonObjGetter explanation, Map<String, List<String>> highlight, List<String> sort, String id, String index, String type, Double score) {
		super(index, type, id);
		if (source == null) {
			this.source = null;
		} else {
			this.source = source;
		}
		if (explanation == null) {
			this.explanation = null;
		} else {
			this.explanation = explanation;
		}
		this.highlight = highlight;
		this.sort = sort;
		this.score = score;
	}

	public JsonObjGetter getSource() {
		return source;
	}

	public void setSource(JsonObjGetter source) {
		this.source = source;
	}

	public JsonObjGetter getExplanation() {
		return explanation;
	}

	public void setExplanation(JsonObjGetter explanation) {
		this.explanation = explanation;
	}

	public Map<String, List<String>> getHighlight() {
		return highlight;
	}

	public void setHighlight(Map<String, List<String>> highlight) {
		this.highlight = highlight;
	}

	public List<String> getSort() {
		return sort;
	}

	public void setSort(List<String> sort) {
		this.sort = sort;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public String getJson() {
		String json = super.getJson();
		if (json == null) {
			json = source.toString();
		}
		return json;
	}
}
