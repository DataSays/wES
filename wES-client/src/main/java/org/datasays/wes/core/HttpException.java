package org.datasays.wes.core;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

import java.io.IOException;

/**
 * Created by watano on 2016/11/21.
 */
public class HttpException extends Exception{
    private Request request;
    private Response respone;

    public HttpException(Throwable cause) {
        super(cause);
    }

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
            try {
                return respone.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String toText(){
        StringBuffer sb = new StringBuffer();
        if(request != null){
            sb.append(request.method());
            sb.append(":");
            sb.append(request.url().toString());
            sb.append("\n");
		        RequestBody body = request.body();
            if(body != null){
                try{
                		if("application/octet-stream".equalsIgnoreCase(body.contentType().type())){
		                }else{
				                Buffer buff = new Buffer();
				                request.body().writeTo(buff);
				                sb.append(buff.readUtf8());
				                sb.append("\n");
		                }
                }catch (Exception e){
                }
            }
        }
        if(respone != null){
            if(respone.code() != 200){
                sb.append("respone code:");
                sb.append(respone.code());
                sb.append("\n");
            }
            String body = getResponeBody();
            if(body != null){
                sb.append("respone body:\n");
                sb.append(body);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
