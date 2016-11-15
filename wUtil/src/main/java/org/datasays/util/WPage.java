package org.datasays.util;

public class WPage {
	private int from = 0; //开始索引
	private int size = 20; //每页数量
	private Integer total = null; //总数

	public void setPage(WPage p) {
		from = p.getFrom();
		size = p.getSize();
	}

	public void nextItem() {
		from++;
	}

	public void preItem() {
		from--;
	}

	//当前页码,从1开始
	public int getPageNo() {
		return calcPageNo(from);
	}

	public int calcPageNo(int index) {
		int pageNo = (index - index % size) / size + 1;
		int pageTotal = getPageTotal();
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageNo > pageTotal) {
			pageNo = pageTotal;
		}
		return pageNo;
	}

	//总页数
	public int getPageTotal() {
		return (total - total % size) / size + 1;
	}

	public void setPageNo(int pageNo) {
		if (pageNo > 0 && (pageNo - 1) * size < total) {
			setFrom((pageNo - 1) * size);
		} else if (pageNo > 0 && total > 0 && (pageNo - 1) * size - 1 > total) {
			setFrom(total - 1);
		} else {
			setFrom(0);
		}
	}

	public String toText() {
		return "from=" + from + "/" + getPageIndex() + ", page=" + getPageNo() + "/" + getPageTotal() + ",pageSize=" + getSize() + (getTotal() != null ? ", total=" + getTotal() : "");
	}

	public int getPageIndex() {
		return from % size;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		if (from < 0) {
			from = 0;
		}
		if (total != null && total > 0 && from >= total) {
			from = total - 1;
		}
		this.from = from;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
