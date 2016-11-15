package org.datasays.util.text;

import java.util.Hashtable;

public class StrMap extends Hashtable<String, String> {
	private static final long serialVersionUID = -2822103329782602109L;

	public void addAll(String... allkv) {
		if (allkv != null && allkv.length % 2 == 0) {
			for (int i = 0; i + 1 < allkv.length; i += 2) {
				put(allkv[i], allkv[i + 1]);
			}
		}
	}

	public void put(String key, String value, String defaultValue) {
		if(value == null && defaultValue != null){
			put(key, defaultValue);
			return;
		}
		put(key, value);
	}
}
