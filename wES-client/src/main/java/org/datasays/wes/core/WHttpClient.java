package org.datasays.wes.core;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Map;

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

		public void setLogFlag(boolean logRequestBody, boolean logResponeBody, boolean logUrl){
				this.logRequestBody = logRequestBody;
				this.logResponeBody = logResponeBody;
				this.logUrl = logUrl;
		}

		public <T> T get(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
				try {
						return exec("GET", requestInfo, cls, genericCls);
				} catch (HttpException e) {
						if (!e.checkCode(404)) {
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
						if (!e.checkCode(404)) {
								throw e;
						}
				}
				return false;
		}

		public <T> T patch(IRequestInfo requestInfo, Class<T> cls, Type... genericCls) throws HttpException {
				return exec("PATCH", requestInfo, cls, genericCls);
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
				if (requestInfo.parseUrl(method) == null) {
						throw new HttpException(new IllegalArgumentException("Illegal Request Parts!"));
				}
				Request request = newRequest(method, requestInfo);
				Call call = client.newCall(request);
				Response respone = null;
				try {
						respone = call.execute();
						if (respone != null && respone.isSuccessful()) {
								String body = respone.body().string();
								if (logResponeBody) {
										LOG.info(body);
								}
								return convert.parse(body, cls, genericCls);
						} else if (respone != null && respone.body() != null) {
								LOG.debug(respone.body().string());
						}
				} catch (Exception e) {
						throw new HttpException(request, respone, e);
				}
				throw new HttpException(request, respone);
		}

		public Request newRequest(String method, IRequestInfo requestInfo) {
				Request.Builder request = new Request.Builder();
				String url = requestInfo.parseUrl(method);
				request.url(url);
				if (logUrl) {
						LOG.info(method + ":" + url);
				}
				Map<String, String> headers = requestInfo.getHeaders();
				if (headers != null) {
						for (String key : headers.keySet()) {
								request.addHeader(key, headers.get(key));
						}
				}
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

		public static String getBaseAuth(String user, String password) {
				return "Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes());
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
}
