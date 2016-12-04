package org.datasays.wes.types;

//Whether to expand wildcard expression to concrete indices that are open, closed or both.
//default: open
public enum EnumExpandWildcards {
	OPEN("open"),
	CLOSED("closed"),
	NONE("none"),
	ALL("all");
	private String name;

	EnumExpandWildcards(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}