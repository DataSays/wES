package org.datasays.es2.types;

//Return indices stats aggregated at index, node or shard level
//default: node
public enum EnumLevel {
	INDICES("indices"),
	NODE("node"),
	SHARDS("shards");
	private String name;
	EnumLevel(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return this.name;
	}
}