package org.datasays.wes.types;

//If `true` then refresh the effected shards to make this operation visible to search, if `wait_for` then wait for a refresh to make this operation visible to search, if `false` (the default) then do nothing with refreshes.
//default: null
public enum EnumRefresh {
	TRUE("true"),
	FALSE("false"),
	WAIT_FOR("wait_for");
	private String name;
	EnumRefresh(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}