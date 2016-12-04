package org.datasays.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public abstract class WPageIterator<T> implements Iterator<T> {
	private static final Logger LOG = LoggerFactory.getLogger(WPageIterator.class);
	private WPage page;
	private List<T> data = null;
	private int dataIndex = 0;

	public WPageIterator() {
		super();
	}

	public WPageIterator(WPage page) {
		super();
		reset(page);
	}

	public WPage getPage() {
		return page;
	}

	public void reset(WPage page) {
		this.page = page;
		_search();
	}

	@Override
	public boolean hasNext() {
		LOG.debug(page.toText());
		return page != null && page.getTotal() != null && page.getTotal() > 0 && page.getFrom() + 1 <= page.getTotal();
	}

	@Override
	public T next() {
		if (dataIndex + 1 > data.size()) {
			_search();
		}
		T d = data.get(dataIndex++);
		page.nextItem();
		return d;
	}

	public void update(List<T> data, int total) {
		this.data = data;
		this.page.setTotal(total);
	}

	private void _search() {
		doSearch();
		dataIndex = 0;
	}

	public abstract void doSearch();
}
