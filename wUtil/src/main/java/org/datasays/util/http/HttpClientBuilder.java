package org.datasays.util.http;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClientBuilder {
	private static final Logger LOG = LoggerFactory.getLogger(HttpClientBuilder.class);
	private OkHttpClient.Builder clientBuilder;

	public HttpClientBuilder() {
		clientBuilder = new OkHttpClient.Builder();
	}

	public void addLog(boolean logRequestBody) {
		clientBuilder.addNetworkInterceptor(new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request original = chain.request();
				LOG.info(original.method() + " " + original.url().toString());
				if (logRequestBody && original.body() != null) {
					LOG.info(RequestBuilder.parseRequestBody(original.body()));
				}
				return chain.proceed(original);
			}
		});
	}

	public void addBaseAuth(String user, String password) {
		if (user != null && user.trim().length() > 0) {
			clientBuilder.addInterceptor(new Interceptor() {
				@Override
				public Response intercept(Chain chain) throws IOException {
					Request original = chain.request();
					return chain.proceed(new RequestBuilder(original).baseAuth(user, password).json().buid());
				}
			});
		} else {
			LOG.warn("user is empty!");
		}
	}

	public void addCache(String directory, long maxSize) {
		if (directory != null) {
			try {
				clientBuilder.cache(new Cache(new File(directory), maxSize));
			} catch (Exception e) {
				LOG.error("Could not create http cache", e);
			}
		} else {
			LOG.warn("cache directory is empty!");
		}
	}

	public void setProxy(String proxyHost, int proxyPort, String user, String password) {
		if (proxyHost != null && proxyHost.trim().length() > 0) {
			SocketAddress address = new InetSocketAddress(proxyHost, proxyPort);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
			LOG.debug(proxy.toString());
			clientBuilder.proxy(proxy);
			clientBuilder.retryOnConnectionFailure(true);
		} else {
			LOG.warn("proxyHost is empty!");
		}
	}
	
	public OkHttpClient build(){
		return clientBuilder.build();
	}
}
