package com.topjet.manage.domain.bean;

/**
 * 
 * @author Administrator
 *
 */
public class SysMenuTreeBean {
	
	private String text;//节点名称
	private String id;//节点id
	private String pid;//父级id
	private String isCheck;//是否选中
	private String isShow;

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public SysMenuTreeBean(String text, String id, String pid, String isCheck) {
		super();
		this.text = text;
		this.id = id;
		this.pid = pid;
		this.isCheck = isCheck;
	}
	public SysMenuTreeBean() {
		super();
	}

}
