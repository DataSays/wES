package org.datasays.wes.types;

//Wait until all currently queued events with the given priority are processed
//default: null
public enum EnumWaitForEvents {
	IMMEDIATE("immediate"),
	URGENT("urgent"),
	HIGH("high"),
	NORMAL("normal"),
	LOW("low"),
	LANGUID("languid");
	private String name;
	EnumWaitForEvents(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}