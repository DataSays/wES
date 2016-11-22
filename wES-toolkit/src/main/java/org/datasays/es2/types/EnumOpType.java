package org.datasays.es2.types;

//Explicit operation type
//default: index
public enum EnumOpType {
	INDEX("index"),
	CREATE("create");
	private String name;
	EnumOpType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}