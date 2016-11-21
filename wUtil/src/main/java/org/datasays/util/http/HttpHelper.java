package org.datasays.util.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.datasays.util.JsonObjGetter;
import org.datasays.util.WJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {
	private static final Logger LOG = LoggerFactory.getLogger(HttpHelper.class);
	protected OkHttpClient client;

	public HttpHelper(OkHttpClient client) {
		this.client = client;
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

	public Response exec(Request request) throws IOException {
		Response response = client.newCall(request).execute();
		return response;
	}
	
	public String fetchBody(Request request) throws Exception {
		Response respone = exec(request);
		if (respone != null && respone.isSuccessful()) {
			return respone.body().string();
		} else {
			LOG.error(respone.body().string());
		}
		return null;		
	}

	public JsonObjGetter fetchJson(Request request) throws Exception {
		return WJsonUtils.fromJson(fetchBody(request));
	}
}
