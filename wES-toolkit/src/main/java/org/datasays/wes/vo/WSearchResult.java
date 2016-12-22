package org.datasays.wes.vo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WSearchResult<T> {
	private Integer took = null;
	private Boolean timed_out = null;

	@SerializedName("_shards")
	private Object shards = null;
	private WSearchResultHits<T> hits = null;

	public Integer getTotal() {
		if (hits != null) {
			return hits.getTotal();
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public List<T> getData() {
		if (hits != null && hits.getHits() != null) {
			List<T> data = new ArrayList<T>();
			for (WEsDoc<T> d : hits.getHits()) {
				T t = d.getSource();
				if (t instanceof Map<?, ?>) {
					Map<Object, Object> obj = new HashMap<>();
					obj.put("_id", d.getId());
					obj.put("_type", d.getType());
					obj.put("_index", d.getIndex());
					obj.put("_source", d.getSource());
					t = (T) obj;
				}else if(t instanceof EsItem){
					((EsItem) t).setId(d.getId());
					((EsItem) t).setType(d.getType());
					((EsItem) t).setIndex(d.getIndex());
				}
				data.add(t);
			}
			return data;
		}
		return null;
	}

	public Integer getTook() {
		return took;
	}

	public void setTook(Integer took) {
		this.took = took;
	}

	public Boolean getTimed_out() {
		return timed_out;
	}

	public void setTimed_out(Boolean timed_out) {
		this.timed_out = timed_out;
	}

	public Object getShards() {
		return shards;
	}

	public void setShards(Object shards) {
		this.shards = shards;
	}

	public WSearchResultHits<T> getHits() {
		return hits;
	}

	public void setHits(WSearchResultHits<T> hits) {
		this.hits = hits;
	}
}
