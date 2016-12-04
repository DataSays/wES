package org.datasays.wes.types;

//Search operation type
//default: null
public enum EnumSearchType {
	QUERY_THEN_FETCH("query_then_fetch"),
	DFS_QUERY_THEN_FETCH("dfs_query_then_fetch");
	private String name;

	EnumSearchType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}