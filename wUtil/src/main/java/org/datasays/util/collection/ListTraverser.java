package org.datasays.util.collection;

import java.util.List;

public class ListTraverser {
	private List<?> list = null;
	public Object input = null;

	public ListTraverser(List<?> l) {
		list = l;
		input = null;
	}

	public ListTraverser(List<?> l, Object obj) {
		list = l;
		input = obj;
	}

	public List<?> getList() {
		return list;
	}

	public Object getInput() {
		return input;
	}

	public void doAction(Object key) {
		System.err.println(key);
	}

	public Object getResult() {
		return null;
	}
}
