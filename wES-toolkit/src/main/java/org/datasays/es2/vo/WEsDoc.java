package org.datasays.es2.vo;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class WEsDoc<T> implements Serializable{
	private static final long serialVersionUID = -5797682781982671388L;
	@SerializedName("_id")
	private String id;
	@SerializedName("_index")
	private String index;
	@SerializedName("_type")
	private String type;
	@SerializedName("_version")
	private String version;
	@SerializedName("_source")
	private T source;
	@SerializedName("_score")
	private Double score = null;
	private Boolean found=false;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public T getSource() {
		return source;
	}
	public void setSource(T source) {
		this.source = source;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}

	public Boolean getFound() {
		return found;
	}

	public void setFound(Boolean found) {
		this.found = found;
	}
}
