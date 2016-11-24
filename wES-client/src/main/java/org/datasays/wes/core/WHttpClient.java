package org.datasays.wes.core;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * Created by watano on 2016/11/21.
 */
public class WHttpClient {
    private static final Logger LOG = LoggerFactory.getLogger(WHttpClient.class);
    private OkHttpClient client;
    private IConvert convert;
    public boolean logUrl = false;
    public boolean logRequestBody = false;
    public boolean logResponeBody = false;

    public WHttpClient(OkHttpClient client, IConvert convert) {
        this.client = client;
        this.convert = convert;
    }

    public <T> T get(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        try {
            return exec("GET", requestInfo, cls, genericCls);
        } catch (HttpException e) {
            if(!e.checkCode(404)){
                throw e;
            }
        }
        return null;
    }

    public boolean has(IRequestInfo requestInfo) throws HttpException {
        try {
            head(requestInfo, Object.class);
            return true;
        } catch (HttpException e) {
            if(!e.checkCode(404)){
                throw e;
            }
        }
        return false;
    }

    public <T> T post(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        return exec("POST", requestInfo, cls, genericCls);
    }

    public <T> T head(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        return exec("HEAD", requestInfo, cls, genericCls);
    }

    public <T> T put(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        return exec("PUT", requestInfo, cls, genericCls);
    }

    public <T> T delete(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        return exec("DELETE", requestInfo, cls, genericCls);
    }

    public <T> T exec(String method, IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        Request request = newRequest(method, requestInfo);
        Call call = client.newCall(request);
        Response respone = null;
        try {
            respone = call.execute();
            if (respone != null && respone.isSuccessful()) {
                String body = respone.body().string();
                if(logResponeBody){
                    LOG.info(body);
                }
                return convert.parse(body, cls, genericCls);
            } else if(respone != null && respone.body() != null){
                LOG.error(respone.body().string());
            }
        } catch (Exception e) {
            throw new HttpException(request, respone, e);
        }
        throw new HttpException(request, respone);
    }

    public Request newRequest(String method, IRequestInfo requestInfo){
        Request.Builder request = new Request.Builder();
        String url = requestInfo.parseUrl(method);
        request.url(url);
        if(logUrl){
            LOG.info(method+":"+url);
        }
        RequestBody body = null;
        if(requestInfo.getBody() != null){
            String bodyText = convert.toText(requestInfo.getBody());
            if(logRequestBody){
                LOG.info(bodyText);
            }
            body = RequestBody.create(MediaType.parse("application/json"), bodyText);
        }
        request.method(method, body);
        return request.build();
    }
}
