package org.datasays.util.http;

import java.io.IOException;

import org.datasays.util.WJsonUtils;
import org.datasays.util.collection.StrMap;

import jodd.util.Base64;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

public class RequestBuilder {
	Request.Builder request = null;
	
	public RequestBuilder(Request oldRequest){
		request = oldRequest.newBuilder();
		//request.url(oldRequest.url());
		request.method(oldRequest.method(), oldRequest.body());
	}
	
	public RequestBuilder(String url){
		request = new Request.Builder(); 
		request.url(url);
	}

	public static String getBaseAuth(String user, String password) {
		String basic = "Basic " + Base64.encodeToString((user + ":" + password).getBytes());
		return basic;
	}

	public static String parseRequestBody(RequestBody body) {
		try {
			Buffer buff = new Buffer();
			body.writeTo(buff);
			return buff.readUtf8();
		} catch (IOException e) {
			return null;
		}
	}
	
	public static RequestBuilder get(String url){
		RequestBuilder rb = new RequestBuilder(url);
		rb.request.get();
		return rb;
	}
	
	public static RequestBuilder head(String url){
		RequestBuilder rb = new RequestBuilder(url);
		rb.request.head();
		return rb;
	}
	
	public static RequestBuilder post(String url, RequestBody body){
		RequestBuilder rb = new RequestBuilder(url);
		rb.request.post(body);
		return rb;
	}
	
	public static RequestBuilder put(String url, RequestBody body){
		RequestBuilder rb = new RequestBuilder(url);
		rb.request.put(body);
		return rb;
	}
	
	public static RequestBuilder delete(String url, RequestBody body){
		RequestBuilder rb = new RequestBuilder(url);
		if(body != null){
			rb.request.delete(body);			
		}else{
			rb.request.delete();	
		}
		return rb;
	}

	public static String url(String... paths){
		String url="";
		for(String path:paths){
			if(path != null && path.trim().length()>0){
				url += path+"/";
			}
		}
		if(url.endsWith("/")){
			url = url.substring(0, url.length()-1);
		}
		return url;
	}
	
	public static RequestBody getJsonRequestBody(Object json){
		return RequestBody.create(MediaType.parse("application/json"), WJsonUtils.toJson(json));
	} 
	
	public static RequestBody getFormRequestBody(StrMap map){
		FormBody.Builder body = new FormBody.Builder();
		for(String name:map.keySet()){
			body.add(name, map.get(name));
		}		
		return body.build();
	} 
	
	public RequestBuilder json(){
		request.header("Accept", "application/json");
		return this;
	}
	
	public RequestBuilder baseAuth(String user, String password){
		if(user != null && user.trim().length()>0){
			request.header("Authorization", getBaseAuth(user, password));			
		}
		return this;
	}
	
	public Request buid(){
		return request.build();
	}
}
