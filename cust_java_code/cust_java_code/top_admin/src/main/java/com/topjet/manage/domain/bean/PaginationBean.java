package com.topjet.manage.domain.bean;

public class PaginationBean {
	private Object rows;
	private Object footer;
	private int total;
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getFooter() {
		return footer;
	}
	public void setFooter(Object footer) {
		this.footer = footer;
	}

}
