package org.datasays.wes.types;

//A health status ("green", "yellow", or "red" to filter only indices matching the specified health status
//default: null
public enum EnumHealth {
	GREEN("green"),
	YELLOW("yellow"),
	RED("red");
	private String name;
	EnumHealth(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}