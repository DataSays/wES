package org.datasays.es2.types;

//Return an array of matching query IDs instead of objects
//default: null
public enum EnumPercolateFormat {
	IDS("ids");
	private String name;
	EnumPercolateFormat(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}