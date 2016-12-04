package org.datasays.wes.core;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by watano on 2016/11/21.
 */
public class WHttpClient {
	private static final Logger LOG = LoggerFactory.getLogger(WHttpClient.class);
	private OkHttpClient client;
	private IConvert convert;
	private String user = null;
	private String password = null;
	public boolean logUrl = false;
	public boolean logRequestBody = false;
	public boolean logResponeBody = false;

	public WHttpClient() {

	}

	public WHttpClient(OkHttpClient client, IConvert convert) {
		this();
		init(client, convert);
	}

	public WHttpClient baseAuth(String user, String password) {
		this.user = user;
		this.password = password;
		return this;
	}

	public void init(OkHttpClient client, IConvert convert) {
		this.client = client;
		this.convert = convert;
	}

	public void setLogFlag(boolean logRequestBody, boolean logResponeBody, boolean logUrl) {
		this.logRequestBody = logRequestBody;
		this.logResponeBody = logResponeBody;
		this.logUrl = logUrl;
	}

	public <T> T get(RequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
		try {
			return exec("GET", requestInfo, cls, genericCls);
		} catch (HttpException e) {
			if (!e.checkCode(404)) {
				throw e;
			}
		}
		return null;
	}

	public boolean has(RequestInfo requestInfo) throws HttpException {
		try {
			head(requestInfo, Object.class);
			return true;
		} catch (HttpException e) {
			if (!e.checkCode(404)) {
				throw e;
			}
		}
		return false;
	}

	public boolean delete(RequestInfo requestInfo) throws HttpException {
		try {
			delete(requestInfo, Object.class);
			return true;
		} catch (HttpException e) {
			if (!e.checkCode(404)) {
				throw e;
			}
		}
		return false;
	}

	public <T> T patch(RequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
		return exec("PATCH", requestInfo, cls, genericCls);
	}

	public <T> T post(RequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
		return exec("POST", requestInfo, cls, genericCls);
	}

	public <T> T head(RequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
		return exec("HEAD", requestInfo, cls, genericCls);
	}

	public <T> T put(RequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
		return exec("PUT", requestInfo, cls, genericCls);
	}

	public <T> T delete(RequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
		return exec("DELETE", requestInfo, cls, genericCls);
	}

	public <T> T exec(String method, RequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
		if (requestInfo.parseUrl(method) == null) {
			throw new HttpException("Illegal Request Parts!");
		}
		Request request = newRequest(method, requestInfo);
		Call call = client.newCall(request);
		Response respone = null;
		String body = null;
		try {
			respone = call.execute();
			if (respone != null) {
				body = getRequestBody(respone);
				if (body != null && logResponeBody) {
					LOG.info(body);
				}
				if (respone.isSuccessful()) {
					if (body != null) {
						return convert.parse(body, cls, genericCls);
					} else {
						return null;
					}
				} else {
					respone.message();
				}
			}
		} catch (Exception e) {
			throw new HttpException(request, respone, e).setResponeBody(body);
		}
		throw new HttpException(request, respone).setResponeBody(body);
	}

	public Request newRequest(String method, RequestInfo requestInfo) {
		Request.Builder request = new Request.Builder();
		String url = requestInfo.parseUrl(method);
		request.url(url);
		if (logUrl) {
			LOG.info(method + ":" + url);
		}
		requestInfo.baseAuth(user, password);
		request.headers(requestInfo.getHeaders());
		RequestBody body = null;
		Object b = requestInfo.getBody();
		if (b != null && b instanceof File) {
			body = getFileRequestBody((File) b);
		} else if (b != null) {
			String bodyText = convert.toText(b);
			if (logRequestBody) {
				LOG.info(bodyText);
			}
			body = getJsonRequestBody(bodyText);
		}
		request.method(method, body);
		return request.build();
	}

	public static RequestBody getJsonRequestBody(String json) {
		return RequestBody.create(MediaType.parse("application/json"), json);
	}

	public static RequestBody getFileRequestBody(File file) {
		return RequestBody.create(MediaType.parse("application/octet-stream"), file);
	}

	public static RequestBody getFormRequestBody(JsonObj map) {
		FormBody.Builder body = new FormBody.Builder();
		for (String name : map.keySet()) {
			body.add(name, map.get(name).toString());
		}
		return body.build();
	}

	public static String getRequestBody(Response respone) {
		if (respone != null) {
			ResponseBody rb = respone.body();
			if (rb != null) {
				try {
					return rb.string();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
}
