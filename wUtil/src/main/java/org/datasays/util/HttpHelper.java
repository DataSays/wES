package org.datasays.util;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.util.Base64;
import jodd.util.StringUtil;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class HttpHelper {
	private static final Logger LOG = LoggerFactory.getLogger(HttpHelper.class);

	public static OkHttpClient.Builder setProxy(OkHttpClient.Builder client, String proxyHost, int proxyPort) {
		//proxy
		if (StringUtil.isNotBlank(proxyHost)) {
			SocketAddress address = new InetSocketAddress(proxyHost, proxyPort);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
			LOG.info(proxy.toString());
			client.proxy(proxy);
			client.retryOnConnectionFailure(true);
		}
		return client;
	}

	public static OkHttpClient.Builder create() {
		OkHttpClient.Builder client = new OkHttpClient.Builder();
		//log
		if (LOG.isInfoEnabled()) {
			client.addNetworkInterceptor(new Interceptor() {
				@Override
				public Response intercept(Chain chain) throws IOException {
					Request original = chain.request();
					LOG.info(original.method() + " " + original.url().toString());
					if (LOG.isDebugEnabled() && original.body() != null) {
						LOG.debug(getRequestBody(original.body()));
					}
					return chain.proceed(original);
				}
			});
		}
		//cache
		try {
			client.cache(new Cache(new File("./caches"), 100000));
		} catch (Exception e) {
			LOG.error("Could not create http cache", e);
		}
		return client;
	}

	public static String getRequestBody(RequestBody body) {
		try {
			Buffer buff = new Buffer();
			body.writeTo(buff);
			return buff.readUtf8();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public static Response get(String url) throws IOException {
		return get(create().build(), url);
	}

	public static Response get(OkHttpClient client, String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response;
	}

	public static String getBaseAuth(String user, String password) {
		String basic = "Basic " + Base64.encodeToString((user + ":" + password).getBytes());
		return basic;
	}

	public static Response json4BaseAuth(String user, String password, Request.Builder request) throws IOException {
		request.header("Authorization", getBaseAuth(user, password));
		return json(request);
	}

	public static Response json4OAuth1a(String apiKey, String apiSecret, String token, Request.Builder request) throws IOException {
		String authorization = "";
		//FIXME 

		request.header("Authorization", authorization);
		return json(request);
	}

	public static Response json(Request.Builder request) throws IOException {
		request.header("Accept", "application/json");
		Response response = create().build().newCall(request.build()).execute();
		return response;
	}

	public static JsonObjGetter fetchJson(Request.Builder request) throws Exception {
		Response respone = HttpHelper.json(request);
		if (respone != null && respone.isSuccessful()) {
			String body = respone.body().string();
			LOG.info(body);
			return new JsonObjGetter(WJsonUtils.fromJson(body, Object.class));
		} else {
			LOG.error(respone.body().string());
		}
		return null;
	}

	public static Map<String, String> headers(Headers headers) {
		Map<String, String> hs = new HashMap<>();
		if (headers != null) {
			for (String key : headers.names()) {
				hs.put(key, headers.get(key));
			}
		}
		return hs;
	}
}
