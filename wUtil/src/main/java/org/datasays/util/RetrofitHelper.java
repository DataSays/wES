package org.datasays.util;

import java.io.IOException;

import jodd.util.StringUtil;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
	public static Retrofit.Builder create(String baseUrl) {
		return create(baseUrl, null, null);
	}

	public static Retrofit.Builder create(String baseUrl, String user, String password) {
		OkHttpClient.Builder client = HttpHelper.create();
		if (StringUtil.isNotBlank(user)) {
			client.addInterceptor(baseAuth(user, password));
		}
		Retrofit.Builder retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(client.build());
		return retrofit;
	}

	public static Interceptor baseAuth(String user, String password) {
		return new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request original = chain.request();
				Request.Builder requestBuilder = original.newBuilder().header("Authorization", HttpHelper.getBaseAuth(user, password)).header("Accept", "application/json").method(original.method(), original.body());
				return chain.proceed(requestBuilder.build());
			}
		};
	}
}
