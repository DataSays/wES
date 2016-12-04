package org.datasays.util.collection;

import java.util.Map;

public class MapTraverser {
	private Map<?, ?> map = null;
	protected Object input = null;

	public MapTraverser(Map<?, ?> m) {
		map = m;
	}

	public MapTraverser(Map<?, ?> m, Object obj) {
		map = m;
		input = obj;
	}

	public Map<?, ?> getMap() {
		return map;
	}

	public Object getInput() {
		return input;
	}

	public void doAction(Object key, Object value) {
		System.err.println(key + "--" + value);
	}

	public Object getResult() {
		return input;
	}
	//	public MapTraverser setInput(Object input) {
	//		this.input = input;
	//		return this;
	//	}
}
