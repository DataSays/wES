package org.datasays.es2.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class WSearchResult<T> {
	private Integer took = null;
	private Boolean timed_out = null;
	
	@SerializedName("_shards")
	private Object shards = null;
	private WSearchResultHits<T> hits = null;
	
	public Integer getTotal(){
		if(hits != null){
			return hits.getTotal();
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getData(){
		if(hits != null && hits.getHits() != null){
			List<T> data = new ArrayList<T>();
			for(WEsDoc<T> d: hits.getHits()){
				T t = d.getSource();
				if(t instanceof Map<?,?>){
					((Map<Object,Object>)t).put("id", d.getId());
					((Map<Object,Object>)t).put("type", d.getType());
					((Map<Object,Object>)t).put("index", d.getIndex());
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
