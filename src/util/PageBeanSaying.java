package util;

import java.util.List;

public class PageBeanSaying<T> {
	private int pageIndex;// 当前页号
	private int pageSize;// 每页的数据条数
	private int count;// 数据总条数
	private int pageCount;// 一共的页数
	private int start;// 起始数据位置
	private int end;// 结束
	private List<T> list = null;
	
	public void init() {
		/*
		 * 根据count和pageSize 计算总页数pageCount
		 */
		
		if(count % pageSize == 0)
			pageCount = count / pageSize;
		else
			pageCount = count / pageSize + 1;
				
		// 判断页数和当前页数
		if (pageIndex > pageCount) {
			pageIndex = pageCount;
		}
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		// 根据当前页计算起始和结束 的 记录号
		this.start = (pageIndex - 1) * pageSize ;
		this.end = pageIndex * pageSize;
	}
	
	public PageBeanSaying(int pageIndex, int count, int pageSize) {
		super();
		this.pageIndex = pageIndex;
		this.count = count;
		this.pageSize = pageSize;
	}

	public PageBeanSaying(int pageIndex, int count, int pageSize, List<T> list) {
		super();
		this.pageIndex = pageIndex;
		this.count = count;
		this.pageSize = pageSize;
		this.list = list;
	}

	public PageBeanSaying() {
		super();
	}

	@Override
	public String toString() {
		return "PageBeanSaying [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", count=" + count + ", pageCount="
				+ pageCount + ", start=" + start + ", end=" + end + ", list=" + list + "]";
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	

}
