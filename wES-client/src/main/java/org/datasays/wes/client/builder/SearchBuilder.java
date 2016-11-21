package org.datasays.wes.client.builder;

/**
 * documentation: http://www.elastic.co/guide/en/elasticsearch/reference/master/search-search.html
 * body:The search definition using the Query DSL
 * @param list index: A comma-separated list of index names to search; use `_all` or empty string to perform the operation on all indices
 * @param list type: A comma-separated list of document types to search; leave empty to perform the operation on all types
 */
public class SearchBuilder extends BaseRequestBuilder {
    public SearchBuilder(String index, String type) {
        super();
        String url;
        if(index != null && index.trim().length()>0){
            if(type != null && type.trim().length()>0){
                url = index + "/" + type + "_search";
            }else{
                url = index + "_search";
            }
        }else{
            url = "_search";
        }
        setBaseUrl(url);
    }

    /**
     * @param analyzer:string The analyzer to use for the query string
     * @return
     */
    public SearchBuilder analyzer(String analyzer){
        param("analyzer", analyzer);
        return this;
    }
}
