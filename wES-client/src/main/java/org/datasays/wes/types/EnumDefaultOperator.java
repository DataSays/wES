package org.datasays.wes.types;

//The default operator for query string query (AND or OR)
//default: OR
public enum EnumDefaultOperator {
	AND("AND"),
	OR("OR");
	private String name;
	EnumDefaultOperator(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}