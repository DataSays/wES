package org.datasays.util;

import java.util.List;
import java.util.Map;

public class JsonObjGetter {
	private Object obj;

	public JsonObjGetter(Object obj) {
		super();
		if (obj != null) {
			this.obj = obj;
		}
	}

	public Map<?, ?> map() {
		if (obj instanceof Map) {
			return (Map<?, ?>) obj;
		}
		return null;
	}

	public List<?> list() {
		if (obj instanceof List) {
			return (List<?>) obj;
		}
		return null;
	}

	public JsonObjGetter obj(Object key) {
		try {
			return new JsonObjGetter(map().get(key));
		} catch (Exception e) {
			return null;
		}
	}

	public String str(Object key) {
		try {
			return (map().get(key)).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public Number num(Object key) {
		try {
			return (Number) (map().get(key));
		} catch (Exception e) {
			return null;
		}
	}

	public Boolean bool(Object key) {
		try {
			return (Boolean) (map().get(key));
		} catch (Exception e) {
			return null;
		}
	}

	public Map<?, ?> map(Object key) {
		try {
			return (Map<?, ?>) (map().get(key));
		} catch (Exception e) {
			return null;
		}
	}

	public List<?> list(Object key) {
		try {
			return (List<?>) (map().get(key));
		} catch (Exception e) {
			return null;
		}
	}
}
