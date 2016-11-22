package org.datasays.util.http;

import org.datasays.util.JsonObjGetter;
import org.datasays.util.WJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper extends HttpHelper implements IErrorHandler {
	private static final Logger LOG = LoggerFactory.getLogger(RetrofitHelper.class);
	private Retrofit retrofit = null;

	public RetrofitHelper() {
		super(null);
	}

	public void init(HttpClientBuilder builder, String baseUrl) {
		this.client = builder.build();
		retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(WJsonUtils.getGsonBuilder().create())).build();
	}

	public <T> T create(final Class<T> service) {
		return retrofit.create(service);
	}

	public JsonObjGetter exec(Call<?> call) {
		try {
			return new JsonObjGetter(execute(call));
		} catch (Exception e) {
			handleError(e);
			return null;
		}
	}

	public <T> T execute(Call<T> call) throws Exception {
		try {
			Response<T> response = call.execute();
			if (response.isSuccessful()) {
				return response.body();
			} else {
				handleError(response);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	public <T> T get(Call<T> call) {
		try {
			Response<T> response = call.execute();
			if (response.isSuccessful()) {
				return response.body();
			} else if (response.code() == 404) {
				return null;
			} else {
				handleError(response);
			}
		} catch (Exception e) {
			handleError(e);
		}
		return null;
	}

	public <T> boolean has(Call<T> call) {
		try {
			Response<T> response = call.execute();
			if (response.isSuccessful()) {
				return true;
			} else if (response.code() == 404) {
				return false;
			} else {
				handleError(response);
				return false;
			}
		} catch (Exception e) {
			handleError(e);
		}
		return false;
	}

	@Override
	public void handleError(Response<?> response) throws Exception {
		LOG.debug("code:" + response.code());
		LOG.debug("message:" + response.message());
		String errorJson = response.errorBody().string();
		LOG.error(errorJson);
	}

	@Override
	public void handleError(Exception e) {
		LOG.error(e.getMessage(), e);
	}
}
