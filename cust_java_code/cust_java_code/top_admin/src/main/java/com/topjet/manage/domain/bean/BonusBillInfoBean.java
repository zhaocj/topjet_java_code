
package com.topjet.manage.domain.bean;


/**
 * @Filename      :  BillInfoBean.java
 * @Package       :  com.topjet.domain.bean
 * @version       :  1.0
 * @Description   :  后台管理框架1.0版本
 * @Copyright     :  Copyright (c)2015-2015
 * @Company       :  上海拓景信息科技有限公司  http://www.566560.com
 * @author        :  唐永梦（silence）
 * @Create Date   :  2015年9月4日
 * @Modified By   :  唐永梦（silence）
 * @Modified Date :  2015年9月4日
 * 补贴发放分类bean
 */
public class BonusBillInfoBean extends BaseTopPageBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	private String createTime;//补贴日期
	private String billType;//补贴类型
	private String countId;//补贴定单数
	private String sumAmt;//补贴总额（元）
	private String sumAmtOk;//已发放金额
	private String countIdOk;//已发放定单数
	private String countIdNo;//未发放定单数
	private String sumAmtNo;//未发放金额
	private String status;//是否发放

	public BonusBillInfoBean() {
		super();
	}
	public BonusBillInfoBean(String createTime, String billType, String countId,
							 String sumAmt, String sumAmtOk, String countIdOk, String countIdNo,
							 String sumAmtNo, String status) {
		super();
		this.createTime = createTime;
		this.billType = billType;
		this.countId = countId;
		this.sumAmt = sumAmt;
		this.sumAmtOk = sumAmtOk;
		this.countIdOk = countIdOk;
		this.countIdNo = countIdNo;
		this.sumAmtNo = sumAmtNo;
		this.status = status;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getCountId() {
		return countId;
	}
	public void setCountId(String countId) {
		this.countId = countId;
	}
	public String getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getSumAmtOk() {
		return sumAmtOk;
	}
	public void setSumAmtOk(String sumAmtOk) {
		this.sumAmtOk = sumAmtOk;
	}
	public String getCountIdOk() {
		return countIdOk;
	}
	public void setCountIdOk(String countIdOk) {
		this.countIdOk = countIdOk;
	}
	public String getCountIdNo() {
		return countIdNo;
	}
	public void setCountIdNo(String countIdNo) {
		this.countIdNo = countIdNo;
	}
	public String getSumAmtNo() {
		return sumAmtNo;
	}
	public void setSumAmtNo(String sumAmtNo) {
		this.sumAmtNo = sumAmtNo;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}