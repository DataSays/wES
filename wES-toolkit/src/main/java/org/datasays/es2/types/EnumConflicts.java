package org.datasays.es2.types;

//What to do when the update by query hits version conflicts?
//default: abort
public enum EnumConflicts {
	ABORT("abort"),
	PROCEED("proceed");
	private String name;
	EnumConflicts(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}