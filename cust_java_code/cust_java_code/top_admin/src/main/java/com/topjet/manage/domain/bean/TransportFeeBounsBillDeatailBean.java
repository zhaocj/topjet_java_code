
package com.topjet.manage.domain.bean;


/**
 * 中介费运费补贴bean
 */
public class TransportFeeBounsBillDeatailBean extends BaseTopPageBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String billId;//账单id
	private String createTime;//发放时间
	private String billType;//补贴类型
	private String orderId;//订单ID
	private String billNo;//支付编号
	private String serialNo;//运单号
	private String payeeId;//收款人ID
	private String driverId;//承运人ID
	private String ownerId;//发货人ID
	private String ownerName;//发货人姓名
	private String ownerMobile;//发货人手机
	private String driverName;//承运人姓名
	private String driverMobile;//承运人手机
	private String site;//出发地-目的地
	private String freightFee;//运费
	private String agencyFee;//中介费
	private String ownerAmount;//发货方补贴
	private String driverAmount;//承运方补贴
	private String thirdAuditUser;//三审人
	private String status;//是否发放


	public TransportFeeBounsBillDeatailBean() {
		super();
	}

	
	public TransportFeeBounsBillDeatailBean(String billId, String createTime, String billType, String orderId, String billNo, String serialNo,
                                            String payeeId, String driverId, String ownerId, String ownerName,
                                            String ownerMobile, String driverName, String driverMobile,
                                            String site, String freightFee, String agencyFee,
                                            String ownerAmount, String driverAmount, String thirdAuditUser,
                                            String status) {
		super();
		this.billId = billId;
		this.createTime = createTime;
		this.billType = billType;
		this.orderId = orderId;
		this.billNo = billNo;
		this.serialNo = serialNo;
		this.payeeId = payeeId;
		this.driverId = driverId;
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.ownerMobile = ownerMobile;
		this.driverName = driverName;
		this.driverMobile = driverMobile;
		this.site = site;
		this.freightFee = freightFee;
		this.agencyFee = agencyFee;
		this.ownerAmount = ownerAmount;
		this.driverAmount = driverAmount;
		this.thirdAuditUser = thirdAuditUser;
		this.status = status;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTransportFee() {
		return freightFee;
	}

	public void setTransportFee(String freightFee) {
		this.freightFee = freightFee;
	}

	public String getAgencyFee() {
		return agencyFee;
	}

	public void setAgencyFee(String agencyFee) {
		this.agencyFee = agencyFee;
	}

	public String getOwnerAmount() {
		return ownerAmount;
	}

	public void setOwnerAmount(String ownerAmount) {
		this.ownerAmount = ownerAmount;
	}

	public String getDriverAmount() {
		return driverAmount;
	}

	public void setDriverAmount(String driverAmount) {
		this.driverAmount = driverAmount;
	}

	public String getThirdAuditUser() {
		return thirdAuditUser;
	}

	public void setThirdAuditUser(String thirdAuditUser) {
		this.thirdAuditUser = thirdAuditUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}
}

