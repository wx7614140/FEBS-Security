package cc.mrbird.common.domain;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class QueryRequest implements Serializable {

	private static final long serialVersionUID = -4869594085374385813L;

	private int pageSize;
	private int pageNum;
	private String sortColumn;
	private String order;
	private String alias;
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public String getAlias() {
		return "".equals(alias)?"":alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("pageSize", pageSize)
				.add("pageNum", pageNum)
				.add("sortColumn", sortColumn)
				.add("order", order)
				.add("alias", alias)
				.toString();
	}



}
