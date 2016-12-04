package org.datasays.wes.core;

import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by watano on 2016/11/24.
 */
public class BaseEsHelper extends WHttpClient {
	private static Logger LOG = LoggerFactory.getLogger(BaseEsHelper.class);
	protected HttpUrl server;

	public BaseEsHelper(String server) {
		super();
		if (server.trim().endsWith("/")) {
			server = server.trim().substring(0, server.trim().length() - 1);
		}
		this.server = HttpUrl.parse(server);
	}
}
