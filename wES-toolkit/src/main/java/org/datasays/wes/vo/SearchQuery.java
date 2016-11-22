package org.datasays.wes.vo;

import org.datasays.util.WJsonExclued;
import org.datasays.util.WPage;
import org.datasays.util.collection.StrObjMap;

public class SearchQuery {
    private Integer from = null;
    private Integer size = null;
    private Query query = null;
    private Sort[] sort = null;
    private StrObjMap aggs = null;

    @WJsonExclued
    private WPage page;

    public SearchQuery() {
    }

    public SearchQuery(WPage page) {
        this();
        setPage(page);
    }

    public static SearchQuery MatchAll() {
        SearchQuery sq = new SearchQuery(new WPage());
        sq.query = Query.MatchAll();
        return sq;
    }

    public static SearchQuery MatchNone() {
        SearchQuery sq = new SearchQuery(new WPage());
        sq.query = Query.MatchNone();
        return sq;
    }

    public void setPage(WPage page) {
        from = page.getFrom();
        size = page.getSize();
        this.page = page;
    }

    public WPage getPage() {
        return page;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Sort[] getSort() {
        return sort;
    }

    public void setSort(Sort[] sort) {
        this.sort = sort;
    }

    public StrObjMap getAggs() {
        return aggs;
    }

    public void setAggs(StrObjMap aggs) {
        this.aggs = aggs;
    }
}
