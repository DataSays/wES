package org.datasays.wes.client;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.datasays.wes.core.HttpException;
import org.datasays.wes.core.IConvert;
import org.datasays.wes.core.IRequestInfo;
import org.datasays.wes.core.WHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * Created by watano on 2016/11/24.
 */
public class BaseEsHelper {
    private static Logger LOG = LoggerFactory.getLogger(BaseEsHelper.class);
    protected WHttpClient esClient = null;
    private String _server;
    protected HttpUrl server;
    protected String user;
    protected String pswd;

    public BaseEsHelper(String server, String user, String pswd) {
        this._server = server;
        if (server.trim().endsWith("/")) {
            this._server = server.trim().substring(0, server.trim().length() - 1);
        }
        this.server = HttpUrl.parse(this._server);
        this.user = user;
        this.pswd = pswd;
    }

    public void init(OkHttpClient client, IConvert convert, boolean logActions){
        esClient = new WHttpClient(client, convert);
        esClient.logRequestBody = logActions;
        esClient.logResponeBody = logActions;
        esClient.logUrl = logActions;
    }

    public <T> T get(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        try {
            return esClient.exec("GET", requestInfo, cls, genericCls);
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
        return esClient.exec("POST", requestInfo, cls, genericCls);
    }

    public <T> T head(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        return esClient.exec("HEAD", requestInfo, cls, genericCls);
    }

    public <T> T put(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
        return esClient.exec("PUT", requestInfo, cls, genericCls);
    }

    public boolean delete(IRequestInfo requestInfo) throws HttpException {
       try {
           esClient.exec("DELETE", requestInfo, Object.class);
           return true;
       }catch (HttpException e){
           if(!e.checkCode(404)){
               throw e;
           }
       }
       return false;
    }
}
