package org.datasays.wes;

import okhttp3.HttpUrl;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by watano on 2016/11/21.
 */
public abstract  class ARequestInfo implements IRequestInfo {
    private Object _body;
    private Map<String, String> _params = new LinkedHashMap<>();
    protected HttpUrl.Builder _url = null;

    public ARequestInfo(String baseUrl){
        setBaseUrl(baseUrl);
    }

    public void setBody(Object body) {
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

    protected void addParams(String name, Object value){
        _params.put(name, value.toString());
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
