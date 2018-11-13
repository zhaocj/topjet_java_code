
package com.topjet.manage.domain.bean;

import java.io.Serializable;

/**
 * @Filename      :  TopPage.java
 * @Package       :  com.topjet.admin.sys.utils
 * @version       :  1.0
 * @Description   :  后台管理框架1.0版本
 * @Copyright     :  Copyright (c)2015-2015
 * @Company       :  上海拓景信息科技有限公司  http://www.566560.com
 * @author        :  唐永梦（silence）
 * @Create Date   :  2015年9月7日
 * @Modified By   :  唐永梦（silence）
 * @Modified Date :  2015年9月7日
 * 拓景后台管理分页base类
 */
public class BaseTopPageBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer page=1;//当前页数
	private String mysqlQueryLimit;//mysql分页语句拼接
	private Integer rows=10;//每页展示条数
	private Integer pageStart;//数据记录起始值（及limit pageStart,page）

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPageStart() {
		pageStart= (this.page-1)*this.rows;
		return pageStart;
	}
	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}
	public String getMysqlQueryLimit() {
		mysqlQueryLimit=" limit "+getPageStart()+","+getRows();
		return mysqlQueryLimit;
	}
	public void setMysqlQueryLimit(String mysqlQueryLimit) {
		this.mysqlQueryLimit = mysqlQueryLimit;
	}
}

