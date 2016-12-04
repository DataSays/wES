package org.datasays.wes.types;

//Specific version type
//default: null
public enum EnumVersionType {
	INTERNAL("internal"),
	FORCE("force");
	private String name;

	EnumVersionType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}