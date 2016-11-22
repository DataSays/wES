package org.datasays.es2;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;

/**
 * Created by watano on 2016/11/21.
 */
public class SearchRequest extends ARequestInfo {
    //param: string analyzer: The analyzer to use for the query string
    public SearchRequest analyzer(String analyzer){
        addParams("analyzer", analyzer);
        return this;
    }

    public SearchRequest(String baseUrl, String user, String pswd) {
        super(baseUrl, user, pswd);
    }

    @Override
    public String parseUrl(String method) {
        if (getIndex() != null) {
            if (getType() != null) {
                if (getId() != null) {
                    setUrl(getIndex(), getType(), getId(), "_search");
                } else {
                    setUrl(getIndex(), getType(), "_search");
                }
            } else {
                setUrl(getIndex(), "_search");
            }
        } else {
            setUrl("_search");
        }
        return super.parseUrl(method);
    }
}
