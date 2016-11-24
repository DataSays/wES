package org.datasays.wes.vo;

import org.datasays.util.WJsonExclued;

import java.io.Serializable;

public class EsItem implements Serializable, IEsItem{
	private static final long serialVersionUID = 2352684084618744566L;
	@WJsonExclued
	private String id;
	@WJsonExclued
	private String index;
	@WJsonExclued
	private String type;

	public EsItem() {
		super();
	}

	public EsItem(String id) {
		this();
		this.id = id;
	}

	public EsItem(String index, String type) {
		this();
		if(index != null){
			this.index = index;
		}
		if(type != null){
			this.type = type;
		}
	}

	public EsItem(String index, String type, String id) {
		this(id);
		if(index != null){
			this.index = index;
		}
		if(type != null){
			this.type = type;
		}
	}

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
}
