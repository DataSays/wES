package org.datasays.wes.types;

//The type to sample (default: cpu)
//default: null
public enum EnumType {
	CPU("cpu"),
	WAIT("wait"),
	BLOCK("block");
	private String name;

	EnumType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}