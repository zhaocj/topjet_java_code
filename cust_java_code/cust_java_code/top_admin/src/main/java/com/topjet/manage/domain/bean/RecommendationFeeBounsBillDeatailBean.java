
package com.topjet.manage.domain.bean;
/**
 * 推荐补贴订单明细bean
 */
public class RecommendationFeeBounsBillDeatailBean extends BaseTopPageBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	
	private String billId;//账单ID
	private String createTime;//统计时间
	private String recommendId;//推荐人id
	private String recommendedId;//被推荐人Id
	private String recommendMobile;//推荐人号码
	private String recommendName;//推荐人姓名
	private String recommendedMobile;//被推荐人号码
	private String recommendedName;//被推荐人姓名
	private String secondAuditUser;//审核人
	private String status;//是否发放
	private String amount;//金额
	private String billType;//补贴类型
	public String getRecommendMobile() {
		return recommendMobile;
	}
	public void setRecommendMobile(String recommendMobile) {
		this.recommendMobile = recommendMobile;
	}
	public String getRecommendName() {
		return recommendName;
	}
	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}
	public String getSecondAuditUser() {
		return secondAuditUser;
	}
	public void setSecondAuditUser(String secondAuditUser) {
		this.secondAuditUser = secondAuditUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public RecommendationFeeBounsBillDeatailBean(String billId, String createTime,
                                                 String recommendId, String recommendedId, String recommendMobile, String recommendName, String recommendedMobile, String recommendedName,
                                                 String secondAuditUser, String status, String amount) {
		super();
		this.billId = billId;
		this.createTime = createTime;
		this.recommendId = recommendId;
		this.recommendedId = recommendedId;
		this.recommendMobile = recommendMobile;
		this.recommendName = recommendName;
		this.recommendedMobile = recommendedMobile;
		this.recommendedName = recommendedName;
		this.secondAuditUser = secondAuditUser;
		this.status = status;
		this.amount = amount;
	}
	public RecommendationFeeBounsBillDeatailBean() {
		super();
	}


	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRecommendedMobile() {
		return recommendedMobile;
	}

	public void setRecommendedMobile(String recommendedMobile) {
		this.recommendedMobile = recommendedMobile;
	}

	public String getRecommendedName() {
		return recommendedName;
	}

	public void setRecommendedName(String recommendedName) {
		this.recommendedName = recommendedName;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public String getRecommendedId() {
		return recommendedId;
	}

	public void setRecommendedId(String recommendedId) {
		this.recommendedId = recommendedId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}
}

