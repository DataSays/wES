package org.datasays.util.http;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by watano on 2016/11/21.
 */
public class HttpException extends Exception{
    private Request request;
    private Response respone;

    public HttpException(Request request, Response respone, Throwable cause) {
        super(cause);
        this.request = request;
        this.respone = respone;
    }

    public HttpException(Request request, Response respone) {
        super();
        this.request = request;
        this.respone = respone;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getRespone() {
        return respone;
    }

    public void setRespone(Response respone) {
        this.respone = respone;
    }

    public boolean checkCode(int code){
            if(respone != null && respone.code() == code){
                return true;
            }
            return false;
    }

    public String getResponeBody(){
        if(respone != null && respone.body() != null){
            return respone.body().toString();
        }
        return null;
    }
}
