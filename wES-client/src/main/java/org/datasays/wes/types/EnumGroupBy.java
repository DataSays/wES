package org.datasays.wes.types;

//Group tasks by nodes or parent/child relationships
//default: nodes
public enum EnumGroupBy {
	NODES("nodes"),
	PARENTS("parents");
	private String name;
	EnumGroupBy(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}