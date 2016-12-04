package org.datasays.wes.types;

//Specify suggest mode
//default: missing
public enum EnumSuggestMode {
	MISSING("missing"),
	POPULAR("popular"),
	ALWAYS("always");
	private String name;

	EnumSuggestMode(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}