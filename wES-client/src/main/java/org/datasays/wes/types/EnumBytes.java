package org.datasays.wes.types;

//The unit in which to display byte values
//default: null
public enum EnumBytes {
	B("b"),
	K("k"),
	KB("kb"),
	M("m"),
	MB("mb"),
	G("g"),
	GB("gb"),
	T("t"),
	TB("tb"),
	P("p"),
	PB("pb");
	private String name;

	EnumBytes(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}