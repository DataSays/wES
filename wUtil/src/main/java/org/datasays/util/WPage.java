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
		if (total == null) {
			return 1;
		}
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

	public int[] getPageItems() {
		int pTotal = getPageTotal();
		int pNo = getPageNo();
		if (pTotal <= 5) {
			return range(1, pTotal + 1);
		} else {
			if (pNo < 4) {
				return range(1, 6);
			} else if (pNo > 3 && pNo < pTotal - 1) {
				return range(pNo - 2, pNo + 3);
			} else {
				return range(pTotal - 4, pTotal + 1);
			}
		}
	}

	private static int[] range(int start, int end) {
		if (start > end) {
			end = start;
		}
		int[] lst = new int[end - start + 1];
		for (int i = 0; i < (end - start + 1); i++) {
			lst[i] = start + i;
		}
		return lst;
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
		if (total != null) {
			return "from=" + from + "/" + getPageIndex() + ", page=" + getPageNo() + "/" + getPageTotal() + ",pageSize=" + getSize() + ", total=" + getTotal();
		} else {
			return "from=" + from + "/" + getPageIndex() + ", page=" + getPageNo() + "/0,pageSize=" + getSize();
		}
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
