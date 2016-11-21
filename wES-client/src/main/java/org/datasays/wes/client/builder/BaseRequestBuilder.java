package org.datasays.wes.client.builder;

import okhttp3.*;

import java.util.Map;

/**
 * Created by watano on 2016/11/19.
 */
public class BaseRequestBuilder {
    private String method;
    protected RequestBody body;
    protected HttpUrl.Builder _url = new HttpUrl.Builder();
    protected Request.Builder _builder = new Request.Builder();

    public void get(){
        _builder.get();
    }
    public void put(String json){
        _builder.put(jsonRequestBody(json));
    }
    public void post(String json){
        _builder.post(jsonRequestBody(json));
    }
    public void delete(){
        _builder.delete();
    }
    public void head(){
        _builder.head();
    }

    public void setBaseUrl(String baseUrl){
        _url.addPathSegment(baseUrl);
    }

    public void param(String name, String value){
        _url.addQueryParameter(name, value);
    }

    public Request build(){
        return _builder.url(_url.build()).method(method, body).build();
    }

    public static RequestBody jsonRequestBody(String json){
        return RequestBody.create(MediaType.parse("application/json"), json);
    }

    public static RequestBody formRequestBody(Map<String, String> map){
        FormBody.Builder body = new FormBody.Builder();
        for(String name:map.keySet()){
            body.add(name, map.get(name));
        }
        return body.build();
    }
}
