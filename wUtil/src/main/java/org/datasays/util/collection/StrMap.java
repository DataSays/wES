package org.datasays.util.collection;

import jodd.util.StringUtil;

import java.util.LinkedHashMap;

public class StrMap extends LinkedHashMap<String, String> {
	private static final long serialVersionUID = -2822103329782602109L;

	public StrMap() {
		super();
	}

	public StrMap(String... allkv) {
		super();
		this.addAll(allkv);
	}

	public void addAll(String... allkv) {
		if (allkv != null && allkv.length % 2 == 0) {
			for (int i = 0; i + 1 < allkv.length; i += 2) {
				put(allkv[i], allkv[i + 1]);
			}
		}
	}

	public void putWithoutNull(String key, String value) {
		if (value != null) {
			put(key, value);
		}
	}

	public void put(String key, String value, String defaultValue) {
		if (value == null && defaultValue != null) {
			put(key, defaultValue);
			return;
		}
		put(key, value);
	}

	public String eval(String text) {
		String out = text;
		for (String key : keySet()) {
			out = StringUtil.replace(out, "${" + key + "}", get(key));
		}
		return out;
	}
}
