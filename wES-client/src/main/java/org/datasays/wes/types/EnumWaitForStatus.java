package org.datasays.wes.types;

//Wait until cluster is in a specific state
//default: null
public enum EnumWaitForStatus {
	GREEN("green"),
	YELLOW("yellow"),
	RED("red");
	private String name;
	EnumWaitForStatus(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}