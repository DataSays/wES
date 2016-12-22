package org.datasays.wes.demo.es.actions.es.model;

import org.datasays.util.WPage;
import org.datasays.wes.core.JsonObj;

import java.io.Serializable;

/**
 * Created by watano on 2016/12/22.
 */
public class SearchQueryJS implements Serializable{
	private WPage page;
	private JsonObj query;

	public WPage getPage() {
		return page;
	}

	public void setPage(WPage page) {
		this.page = page;
	}

	public JsonObj getQuery() {
		return query;
	}

	public void setQuery(JsonObj query) {
		this.query = query;
	}
}
