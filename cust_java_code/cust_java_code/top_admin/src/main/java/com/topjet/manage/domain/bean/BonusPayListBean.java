
package com.topjet.manage.domain.bean;

/**
 * @author        :  zhengjm（silence）
 * @Create Date   :  2016年10月17日
 * @function     补贴发放list
 */
public class BonusPayListBean {
	private String billType;//补贴类型（合计）
	private String countId;//补贴定单数
	private String sumAmt;//补贴总额（元）
	private String sumAmtOk;//已发放金额
	private String countIdOk;//已发放定单数
	private String countIdNo;//未发放定单数
	private String sumAmtNo;//未发放金额
	
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
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}


}

