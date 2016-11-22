package org.datasays.es2;

import okhttp3.HttpUrl;
import org.datasays.util.collection.StrMap;
import org.datasays.util.http.IRequestInfo;

/**
 * Created by watano on 2016/11/21.
 */
public abstract  class ARequestInfo extends EsItem implements IRequestInfo {
    private Object _body;
    private StrMap _params = new StrMap();
    protected HttpUrl.Builder _url = null;

    public ARequestInfo(String baseUrl, String user, String pswd){
        setBaseUrl(baseUrl);
    }

    protected void setBody(Object body) {
        this._body = body;
    }

    public void setBaseUrl(String baseUrl){
        _url = HttpUrl.parse(baseUrl).newBuilder();
    }

    protected void setUrl(String... paths){
        for(String path:paths){
            _url.addPathSegment(path);
        }
    }

    protected void addParams(String... params){
        _params.addAll(params);
    }

    @Override
    public Object getBody() {
        return _body;
    }

    @Override
    public String parseUrl(String method) {
        for(String key:_params.keySet()){
            String value = _params.get(key);
            if(key != null && value != null){
                _url.addQueryParameter(key, value);
            }
        }
        return _url.build().toString();
    }
}
