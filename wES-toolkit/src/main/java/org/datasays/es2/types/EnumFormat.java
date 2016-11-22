package org.datasays.es2.types;

//Format of the output
//default: detailed
public enum EnumFormat {
	DETAILED("detailed"),
	TEXT("text");
	private String name;
	EnumFormat(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}