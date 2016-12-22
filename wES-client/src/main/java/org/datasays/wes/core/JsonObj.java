package org.datasays.wes.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by watano on 2016/12/4.
 */
public class JsonObj extends LinkedHashMap<String, Object> {

	public JsonObj() {
		super();
	}

	public JsonObj(Object... allkv) {
		super();
		this.addAll(allkv);
	}

	public void addAll(Object... allkv) {
		if (allkv != null && allkv.length % 2 == 0) {
			for (int i = 0; i + 1 < allkv.length; i += 2) {
				put(allkv[i].toString(), allkv[i + 1]);
			}
		}
	}

	public void lstAdd(String key, Object... values) {
		Object v = get(key);
		Object[] allValue = values;
		if(allValue == null){
			allValue = new Object[]{};
		}
		if (v == null) {
			put(key, values);
		} else if (v instanceof Object[]) {
			Object[] vs = (Object[]) v;
			List<Object> all = new ArrayList<>();
			all.addAll(Arrays.asList(vs));
			for (Object value : values) {
				all.add(value);
			}
			put(key, all.toArray(new Object[]{}));
		} else {
			List<Object> all = new ArrayList<>();
			all.addAll(Arrays.asList(values));
			all.add(0, v);
			put(key, all.toArray(new Object[]{}));
		}
	}

	public void putWithoutNull(String key, Object value) {
		if (value != null) {
			put(key, value);
		}
	}

	public void put(String key, Object value, Object defaultValue) {
		if (value == null && defaultValue != null) {
			put(key, defaultValue);
			return;
		}
		put(key, value);
	}

	public void putInto(String key, Object vlaue, String... parents) {
		JsonObj pMap = this;
		for (String parent : parents) {
			Object pObj = pMap.get(parent);
			if (pObj != null && pObj instanceof JsonObj) {
				pMap = (JsonObj) pObj;
			}
		}
		pMap.put(key, vlaue);
	}

}
