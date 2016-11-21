package org.datasays.util.collection;

import java.util.LinkedHashMap;

public class StrObjMap extends LinkedHashMap<String, Object>{
	private static final long serialVersionUID = 7959843718678317851L;
	
	public StrObjMap() {
		super();
	}
	
	public StrObjMap(Object... allkv) {
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

	public void putWithoutNull(String key, Object value){
		if(value != null){
			put(key, value);
		}
	}

	public void put(String key, Object value, Object defaultValue) {
		if(value == null && defaultValue != null){
			put(key, defaultValue);
			return;
		}
		put(key, value);
	}
	
	public void putInto(String key, Object vlaue, String... parents){
		StrObjMap pMap = this;
		for(String parent:parents){
			Object pObj = pMap.get(parent);
			if(pObj != null && pObj instanceof StrObjMap){
				pMap = (StrObjMap) pObj;
			}
		}
		pMap.put(key, vlaue);
	}
}
