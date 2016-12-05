package org.datasays.wes.types;

//The multiplier in which to display values
//default: null
public enum EnumSize {
	v(""),
	K("k"),
	M("m"),
	G("g"),
	T("t"),
	P("p");
	private String name;
	EnumSize(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}