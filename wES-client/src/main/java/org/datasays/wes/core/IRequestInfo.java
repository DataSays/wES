package org.datasays.wes.core;

import java.util.Map;

/**
 * Created by watano on 2016/11/21.
 */
public interface IRequestInfo {
		public Object getBody();

		public Map<String, String> getHeaders();

		public String parseUrl(String method);
}
