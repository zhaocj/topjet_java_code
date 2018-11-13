package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.UserInfoModel;

import java.math.BigDecimal;
import java.util.Date;

public class UserInfoBean extends UserInfoModel {

	private static final long serialVersionUID = -5968134243027739835L;

	private String validtePHoto;

	private String userStatusOlder;//用户身份

	private String auditIDReason;//身份认证原因备注

	private String businessLineFisrt1;


	private BigDecimal credit;

	private String resident1;

	private String resident2;

	private String resident3;
	private Integer identityType;//'身份类型）：0、非企业用户（默认）1、物流公司 2、发货商家',
	//private String telephone;

	private String businessLineFisrt2;

	private String businessLineFisrt3;

	private String businessLineSecond1;

	private String businessLineSecond2;

	private String businessLineSecond3;


	private String businessLineThird1;

	private String businessLineThird2;

	private String businessLineThird3;

	private String businessLineForth1;

	private String businessLineForth2;

	private String businessLineForth3;


	private String headPhotoUrl;//原图片  把urltobe附到 url中
	private String headPhotoUrlTobe;//新图片
	private String headPhotoType;
	private String headPhotoBase64Url;


	//身份证照片
	private String idNoPhotoUrl;
	private String idNoPhotoUrlTobe;
	private String idNoPhotoType;
	private String idNoPhotoBase64Url;


	//身份证反面照片
	private String idNoNegativePhotoUrl;
	private String idNoNegativePhotoUrlTobe;
	private String idNoNegativePhotoType;
	private String idNoNegativePhotoBase64Url;


	//驾照
	private String dirverLicenceUrl;
	private String dirverLicenceUrlTobe;
	private String dirverLicenceUrlTobe1;
	private String dirverLicenceType;


   //营业执照
	private String businessPhotoUrl;
	private String businessPhotoUrlTobe;
	private String businessPhotoUrlTobe1;
	private String businessPhotoType;
	private String businesPhotoBase64Url;



    //门店图片
	private String  shopFrontPhotoUrl;
	private String shopFrontPhotoUrlTobe;
	private String shopFrontPhotoUrlTobe1;
	private String shopFrontType;
	private String shopFrontPhotoBase64Url;


	/*
     * 运营证
     */
	private  String operatingPermitsPhotoType;
	private  String operatingPermitsPhotoUrl;
	private  String operatingPermitsPhotoUrlTobe;
	private  String operatingPermitsPhotoUrlTobe1;
	private  String operatingPermitsPhotoVersion;
	private  Integer operatingPermitsPhotoId;

	/**
	 * 名片
	 */

	private String businessCardUrl;

	private String businessCardUrlTobe;

	private String businessCardUrlTobe1;

	private String businessCardType;

	private Integer businessCardVersion;

	private Integer businessCardId;

	/**
	 * 机打发票
	 */

	private String bigOrdersUrl;

	private String bigOrdersUrlTobe;

	private String bigOrdersUrlTobe1;

	private String bigOrdersType;

	private Integer bigOrdersVersion;

	private Integer bigOrdersId;





	private String shopBusinessPhotoType;

	private String walletAccount;

	private BigDecimal walletBalance;

	private Integer userInfoVersion;

	private Integer truckInfoVersion;

	private Integer photoInfoHeadVersion;
	private Integer userDataUpdateVersion;//userDataUpdateid
	private Integer photoInfoIdNoNegativeVersion;//身份证反面
	private Integer photoInfoIdNoVersion;

	private Integer photoInfoDrivingVersion;

	private Integer shopFrontVersion;

	private Integer businessPhotoVersion;

	private Integer photoCount;

	private Integer truckId;

	private Integer driverPhotoId;

	private Integer  shopFrontPHotoId;

	private Integer  businessPhotoId;

	private Integer isUpload;
	private Integer age;

	private String telephone;


	//修改手机号码
	private String oldMobile;
	private String newMobile;



	private String headStatus;

	private Integer businessAddressCityId;

	private String businessAddress;

	private Integer registeredCityId;

	private Integer residentCityId;

	private String reditRemark;
	private Integer userType;
	private Integer version;
	private Integer memberStatus;//会员冻结状态
	private Integer businessLineFisrtId;//路线1
	private Integer businessLineSecondId;//路线2
	private Integer businessLineThirdId;//路线3
	private Integer businessLineForthId;//路线4

	//审核人
	private String auditName;
	//审核时间
	private Date auditCreateTime;

	//备注
	private String remark;

	private BigDecimal  businessAddressLongitude;//经营地经度
	private BigDecimal  businessAddressLatitude;//经营地纬度
	private String creditCode;//信用代码
	private String internalRemark;//内部备注
	private String userPass;//用户密码


	/**
	 * 经营地址
	 *
	 */
	private String businessAddressCity1;
	private String businessAddressCity2;
	private String businessAddressCity3;


	/**
	 * 承运数
	 */
	private Integer driverOrderCount;

	/**
	 * 订单数
	 */
	private Integer ownerOrderCount;

	/**
	 * 撤销订单数量
	 */
	private Integer calcelOwnerOrderCount;

	/**
	 * 车辆数
	 */
	private Integer truckCount;

	/**
	 * 被评价数
	 */
	private Integer commentedCount;

	/**
	 * 评价数
	 */
	private Integer commentCount;
	/**
	 * 推荐我的用户的手机号
	 */
	private String recommendUserMobile;

	/**
	 * 推荐用户数
	 */
	private Integer recommendCount;

	/**
	 * 熟车数
	 */
	private Integer ownerTruckCount;

	/**
	 * 注册城市
	 */
	private String residentCity;

	/**
	 * 审核状态
	 */
	private String auditResult;

	/**
	 * 审核信息
	 */
	private String	auditResultMsg;

	private String companyNameOlder;//原企业名称
	private String resident1Older;
	private String resident2Older;
	private String resident3Older;
	private BigDecimal ownerCommentLevel;
	private BigDecimal driverCommentLevel;
	private Integer auditUserId;



	public BigDecimal getBusinessAddressLongitude() {
		return businessAddressLongitude;
	}

	public void setBusinessAddressLongitude(BigDecimal businessAddressLongitude) {
		this.businessAddressLongitude = businessAddressLongitude;
	}

	public BigDecimal getBusinessAddressLatitude() {
		return businessAddressLatitude;
	}

	public void setBusinessAddressLatitude(BigDecimal businessAddressLatitude) {
		this.businessAddressLatitude = businessAddressLatitude;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getInternalRemark() {
		return internalRemark;
	}

	public void setInternalRemark(String internalRemark) {
		this.internalRemark = internalRemark;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public String getOldMobile() {
		return oldMobile;
	}

	public void setOldMobile(String oldMobile) {
		this.oldMobile = oldMobile;
	}

	public String getNewMobile() {
		return newMobile;
	}

	public void setNewMobile(String newMobile) {
		this.newMobile = newMobile;
	}

	public Integer getBusinessLineFisrtId() {
		return businessLineFisrtId;
	}

	public void setBusinessLineFisrtId(Integer businessLineFisrtId) {
		this.businessLineFisrtId = businessLineFisrtId;
	}

	public Integer getBusinessLineSecondId() {
		return businessLineSecondId;
	}

	public void setBusinessLineSecondId(Integer businessLineSecondId) {
		this.businessLineSecondId = businessLineSecondId;
	}

	public Integer getBusinessLineThirdId() {
		return businessLineThirdId;
	}

	public void setBusinessLineThirdId(Integer businessLineThirdId) {
		this.businessLineThirdId = businessLineThirdId;
	}

	public Integer getBusinessLineForthId() {
		return businessLineForthId;
	}

	public void setBusinessLineForthId(Integer businessLineForthId) {
		this.businessLineForthId = businessLineForthId;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Integer getUserType() {
		return userType;
	}

	@Override
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getResidentCityId() {
		return residentCityId;
	}

	public void setResidentCityId(Integer residentCityId) {
		this.residentCityId = residentCityId;
	}

	public Integer getRegisteredCityId() {
		return registeredCityId;
	}

	public void setRegisteredCityId(Integer registeredCityId) {
		this.registeredCityId = registeredCityId;
	}

	public String getHeadStatus() {
		return headStatus;
	}

	public void setHeadStatus(String headStatus) {
		this.headStatus = headStatus;
	}



	public Date getAuditCreateTime() {
		return auditCreateTime;
	}

	public void setAuditCreateTime(Date auditCreateTime) {
		this.auditCreateTime = auditCreateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	public Integer getAge() {
		return age;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public void setAge(Integer age) {
		this.age = age;
	}



	public BigDecimal getDriverCommentLevel() {
		return driverCommentLevel;
	}

	public void setDriverCommentLevel(BigDecimal driverCommentLevel) {
		this.driverCommentLevel = driverCommentLevel;
	}

	public Integer getUserDataUpdateVersion() {
		return userDataUpdateVersion;
	}

	public void setUserDataUpdateVersion(Integer userDataUpdateVersion) {
		this.userDataUpdateVersion = userDataUpdateVersion;
	}


	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}



	public Integer getPhotoInfoIdNoNegativeVersion() {
		return photoInfoIdNoNegativeVersion;
	}

	public void setPhotoInfoIdNoNegativeVersion(Integer photoInfoIdNoNegativeVersion) {
		this.photoInfoIdNoNegativeVersion = photoInfoIdNoNegativeVersion;
	}

	public String getIdNoNegativePhotoUrl() {
		return idNoNegativePhotoUrl;
	}

	public void setIdNoNegativePhotoUrl(String idNoNegativePhotoUrl) {
		this.idNoNegativePhotoUrl = idNoNegativePhotoUrl;
	}

	public String getIdNoNegativePhotoUrlTobe() {
		return idNoNegativePhotoUrlTobe;
	}

	public void setIdNoNegativePhotoUrlTobe(String idNoNegativePhotoUrlTobe) {
		this.idNoNegativePhotoUrlTobe = idNoNegativePhotoUrlTobe;
	}

	public String getIdNoNegativePhotoBase64Url() {
		return idNoNegativePhotoBase64Url;
	}

	public void setIdNoNegativePhotoBase64Url(String idNoNegativePhotoBase64Url) {
		this.idNoNegativePhotoBase64Url = idNoNegativePhotoBase64Url;
	}

	public String getIdNoNegativePhotoType() {
		return idNoNegativePhotoType;
	}

	public void setIdNoNegativePhotoType(String idNoNegativePhotoType) {
		this.idNoNegativePhotoType = idNoNegativePhotoType;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getAuditResultMsg() {
		return auditResultMsg;
	}

	public void setAuditResultMsg(String auditResultMsg) {
		this.auditResultMsg = auditResultMsg;
	}

	public BigDecimal getOwnerCommentLevel() {
		return ownerCommentLevel;
	}

	public void setOwnerCommentLevel(BigDecimal ownerCommentLevel) {
		this.ownerCommentLevel = ownerCommentLevel;
	}

	public String getResidentCity() {
		return residentCity;
	}

	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}


	public Integer getOwnerTruckCount() {
		return ownerTruckCount;
	}

	public void setOwnerTruckCount(Integer ownerTruckCount) {
		this.ownerTruckCount = ownerTruckCount;
	}

	public Integer getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(Integer recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getRecommendUserMobile() {
		return recommendUserMobile;
	}

	public void setRecommendUserMobile(String recommendUserMobile) {
		this.recommendUserMobile = recommendUserMobile;
	}

	public Integer getCommentedCount() {
		return commentedCount;
	}

	public void setCommentedCount(Integer commentedCount) {
		this.commentedCount = commentedCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getDriverOrderCount() {
		return driverOrderCount;
	}

	public void setDriverOrderCount(Integer driverOrderCount) {
		this.driverOrderCount = driverOrderCount;
	}

	public Integer getOwnerOrderCount() {
		return ownerOrderCount;
	}

	public void setOwnerOrderCount(Integer ownerOrderCount) {
		this.ownerOrderCount = ownerOrderCount;
	}

	public Integer getCalcelOwnerOrderCount() {
		return calcelOwnerOrderCount;
	}

	public void setCalcelOwnerOrderCount(Integer calcelOwnerOrderCount) {
		this.calcelOwnerOrderCount = calcelOwnerOrderCount;
	}

	public Integer getTruckCount() {
		return truckCount;
	}

	public void setTruckCount(Integer truckCount) {
		this.truckCount = truckCount;
	}

	public String getOperatingPermitsPhotoType() {
		return operatingPermitsPhotoType;
	}

	public void setOperatingPermitsPhotoType(String operatingPermitsPhotoType) {
		this.operatingPermitsPhotoType = operatingPermitsPhotoType;
	}

	public String getOperatingPermitsPhotoUrl() {
		return operatingPermitsPhotoUrl;
	}

	public void setOperatingPermitsPhotoUrl(String operatingPermitsPhotoUrl) {
		this.operatingPermitsPhotoUrl = operatingPermitsPhotoUrl;
	}

	public String getOperatingPermitsPhotoUrlTobe() {
		return operatingPermitsPhotoUrlTobe;
	}

	public void setOperatingPermitsPhotoUrlTobe(String operatingPermitsPhotoUrlTobe) {
		this.operatingPermitsPhotoUrlTobe = operatingPermitsPhotoUrlTobe;
	}

	public String getOperatingPermitsPhotoVersion() {
		return operatingPermitsPhotoVersion;
	}

	public void setOperatingPermitsPhotoVersion(String operatingPermitsPhotoVersion) {
		this.operatingPermitsPhotoVersion = operatingPermitsPhotoVersion;
	}

	public Integer getOperatingPermitsPhotoId() {
		return operatingPermitsPhotoId;
	}

	public void setOperatingPermitsPhotoId(Integer operatingPermitsPhotoId) {
		this.operatingPermitsPhotoId = operatingPermitsPhotoId;
	}

	public Integer getShopFrontVersion() {
		return shopFrontVersion;
	}

	public void setShopFrontVersion(Integer shopFrontVersion) {
		this.shopFrontVersion = shopFrontVersion;
	}

	public Integer getBusinessPhotoVersion() {
		return businessPhotoVersion;
	}

	public void setBusinessPhotoVersion(Integer businessPhotoVersion) {
		this.businessPhotoVersion = businessPhotoVersion;
	}

	public String getValidtePHoto() {
		return validtePHoto;
	}

	public void setValidtePHoto(String validtePHoto) {
		this.validtePHoto = validtePHoto;
	}

	public String getBusinessLineForth1() {
		return businessLineForth1;
	}

	public void setBusinessLineForth1(String businessLineForth1) {
		this.businessLineForth1 = businessLineForth1;
	}

	public String getBusinessLineForth2() {
		return businessLineForth2;
	}

	public void setBusinessLineForth2(String businessLineForth2) {
		this.businessLineForth2 = businessLineForth2;
	}

	public Integer getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(Integer isUpload) {
		this.isUpload = isUpload;
	}

	public Integer getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(Integer photoCount) {
		this.photoCount = photoCount;
	}

	public String getShopFrontType() {
		return shopFrontType;
	}

	public void setShopFrontType(String shopFrontType) {
		this.shopFrontType = shopFrontType;
	}

	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBusinessLineFisrt1() {
		return businessLineFisrt1;
	}

	public void setBusinessLineFisrt1(String businessLineFisrt1) {
		this.businessLineFisrt1 = businessLineFisrt1;
	}

	public String getBusinessLineFisrt2() {
		return businessLineFisrt2;
	}

	public String getHeadPhotoType() {
		return headPhotoType;
	}

	public void setHeadPhotoType(String headPhotoType) {
		this.headPhotoType = headPhotoType;
	}

	public String getDirverLicenceType() {
		return dirverLicenceType;
	}

	public String getWalletAccount() {
		return walletAccount;
	}

	public void setWalletAccount(String walletAccount) {
		this.walletAccount = walletAccount;
	}

	public Integer getShopFrontPHotoId() {
		return shopFrontPHotoId;
	}

	public void setShopFrontPHotoId(Integer shopFrontPHotoId) {
		this.shopFrontPHotoId = shopFrontPHotoId;
	}

	public Integer getBusinessPhotoId() {
		return businessPhotoId;
	}

	public void setBusinessPhotoId(Integer businessPhotoId) {
		this.businessPhotoId = businessPhotoId;
	}

	public String getBusinessPhotoUrl() {
		return businessPhotoUrl;
	}

	public void setBusinessPhotoUrl(String businessPhotoUrl) {
		this.businessPhotoUrl = businessPhotoUrl;
	}

	public String getShopFrontPhotoUrl() {
		return shopFrontPhotoUrl;
	}

	public void setShopFrontPhotoUrl(String shopFrontPhotoUrl) {
		this.shopFrontPhotoUrl = shopFrontPhotoUrl;
	}

	public String getBusinessPhotoUrlTobe() {
		return businessPhotoUrlTobe;
	}

	public void setBusinessPhotoUrlTobe(String businessPhotoUrlTobe) {
		this.businessPhotoUrlTobe = businessPhotoUrlTobe;
	}

	public String getShopFrontPhotoUrlTobe() {
		return shopFrontPhotoUrlTobe;
	}

	public void setShopFrontPhotoUrlTobe(String shopFrontPhotoUrlTobe) {
		this.shopFrontPhotoUrlTobe = shopFrontPhotoUrlTobe;
	}

	public String getBusinessPhotoType() {
		return businessPhotoType;
	}

	public void setBusinessPhotoType(String businessPhotoType) {
		this.businessPhotoType = businessPhotoType;
	}

	public String getShopBusinessPhotoType() {
		return shopBusinessPhotoType;
	}

	public void setShopBusinessPhotoType(String shopBusinessPhotoType) {
		this.shopBusinessPhotoType = shopBusinessPhotoType;
	}

	public String getBusinesPhotoBase64Url() {
		return businesPhotoBase64Url;
	}

	public void setBusinesPhotoBase64Url(String businesPhotoBase64Url) {
		this.businesPhotoBase64Url = businesPhotoBase64Url;
	}

	public String getShopFrontPhotoBase64Url() {
		return shopFrontPhotoBase64Url;
	}

	public void setShopFrontPhotoBase64Url(String shopFrontPhotoBase64Url) {
		this.shopFrontPhotoBase64Url = shopFrontPhotoBase64Url;
	}

	public BigDecimal getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(BigDecimal walletBalance) {
		this.walletBalance = walletBalance;
	}

	public void setDirverLicenceType(String dirverLicenceType) {
		this.dirverLicenceType = dirverLicenceType;
	}

	public String getIdNoPhotoType() {
		return idNoPhotoType;
	}

	public void setIdNoPhotoType(String idNoPhotoType) {
		this.idNoPhotoType = idNoPhotoType;
	}

	public void setBusinessLineFisrt2(String businessLineFisrt2) {
		this.businessLineFisrt2 = businessLineFisrt2;
	}

	public String getBusinessLineSecond1() {
		return businessLineSecond1;
	}

	public void setBusinessLineSecond1(String businessLineSecond1) {
		this.businessLineSecond1 = businessLineSecond1;
	}

	public String getBusinessLineSecond2() {
		return businessLineSecond2;
	}

	public void setBusinessLineSecond2(String businessLineSecond2) {
		this.businessLineSecond2 = businessLineSecond2;
	}

	public String getBusinessLineThird1() {
		return businessLineThird1;
	}

	public void setBusinessLineThird1(String businessLineThird1) {
		this.businessLineThird1 = businessLineThird1;
	}

	public String getBusinessLineThird2() {
		return businessLineThird2;
	}

	public void setBusinessLineThird2(String businessLineThird2) {
		this.businessLineThird2 = businessLineThird2;
	}

	public String getIdNoPhotoUrl() {
		return idNoPhotoUrl;
	}

	public void setIdNoPhotoUrl(String idNoPhotoUrl) {
		this.idNoPhotoUrl = idNoPhotoUrl;
	}

	public String getDirverLicenceUrl() {
		return dirverLicenceUrl;
	}

	public void setDirverLicenceUrl(String dirverLicenceUrl) {
		this.dirverLicenceUrl = dirverLicenceUrl;
	}

	public String getHeadPhotoUrl() {
		return headPhotoUrl;
	}

	public void setHeadPhotoUrl(String headPhotoUrl) {
		this.headPhotoUrl = headPhotoUrl;
	}

	public String getIdNoPhotoUrlTobe() {
		return idNoPhotoUrlTobe;
	}

	public void setIdNoPhotoUrlTobe(String idNoPhotoUrlTobe) {
		this.idNoPhotoUrlTobe = idNoPhotoUrlTobe;
	}

	public String getDirverLicenceUrlTobe() {
		return dirverLicenceUrlTobe;
	}

	public void setDirverLicenceUrlTobe(String dirverLicenceUrlTobe) {
		this.dirverLicenceUrlTobe = dirverLicenceUrlTobe;
	}

	public String getHeadPhotoUrlTobe() {
		return headPhotoUrlTobe;
	}

	public String getHeadPhotoBase64Url() {
		return headPhotoBase64Url;
	}

	public void setHeadPhotoBase64Url(String headPhotoBase64Url) {
		this.headPhotoBase64Url = headPhotoBase64Url;
	}

	public String getIdNoPhotoBase64Url() {
		return idNoPhotoBase64Url;
	}

	public void setIdNoPhotoBase64Url(String idNoPhotoBase64Url) {
		this.idNoPhotoBase64Url = idNoPhotoBase64Url;
	}

	public void setHeadPhotoUrlTobe(String headPhotoUrlTobe) {
		this.headPhotoUrlTobe = headPhotoUrlTobe;
	}

	public Integer getUserInfoVersion() {
		return userInfoVersion;
	}

	public void setUserInfoVersion(Integer userInfoVersion) {
		this.userInfoVersion = userInfoVersion;
	}

	public Integer getTruckInfoVersion() {
		return truckInfoVersion;
	}

	public void setTruckInfoVersion(Integer truckInfoVersion) {
		this.truckInfoVersion = truckInfoVersion;
	}

	public Integer getPhotoInfoHeadVersion() {
		return photoInfoHeadVersion;
	}

	public void setPhotoInfoHeadVersion(Integer photoInfoHeadVersion) {
		this.photoInfoHeadVersion = photoInfoHeadVersion;
	}

	public Integer getPhotoInfoIdNoVersion() {
		return photoInfoIdNoVersion;
	}

	public void setPhotoInfoIdNoVersion(Integer photoInfoIdNoVersion) {
		this.photoInfoIdNoVersion = photoInfoIdNoVersion;
	}

	public Integer getPhotoInfoDrivingVersion() {
		return photoInfoDrivingVersion;
	}

	public void setPhotoInfoDrivingVersion(Integer photoInfoDrivingVersion) {
		this.photoInfoDrivingVersion = photoInfoDrivingVersion;
	}

	public Integer getTruckId() {
		return truckId;
	}

	public void setTruckId(Integer truckId) {
		this.truckId = truckId;
	}

	public Integer getDriverPhotoId() {
		return driverPhotoId;
	}

	public void setDriverPhotoId(Integer driverPhotoId) {
		this.driverPhotoId = driverPhotoId;
	}

	public String getBusinessLineFisrt3() {
		return businessLineFisrt3;
	}

	public void setBusinessLineFisrt3(String businessLineFisrt3) {
		this.businessLineFisrt3 = businessLineFisrt3;
	}

	public String getBusinessLineSecond3() {
		return businessLineSecond3;
	}

	public void setBusinessLineSecond3(String businessLineSecond3) {
		this.businessLineSecond3 = businessLineSecond3;
	}

	public String getBusinessLineThird3() {
		return businessLineThird3;
	}

	public void setBusinessLineThird3(String businessLineThird3) {
		this.businessLineThird3 = businessLineThird3;
	}

	public String getBusinessLineForth3() {
		return businessLineForth3;
	}

	public void setBusinessLineForth3(String businessLineForth3) {
		this.businessLineForth3 = businessLineForth3;
	}

	public String getBusinessAddressCity1() {
		return businessAddressCity1;
	}

	public void setBusinessAddressCity1(String businessAddressCity1) {
		this.businessAddressCity1 = businessAddressCity1;
	}

	public String getBusinessAddressCity2() {
		return businessAddressCity2;
	}

	public void setBusinessAddressCity2(String businessAddressCity2) {
		this.businessAddressCity2 = businessAddressCity2;
	}

	public String getBusinessAddressCity3() {
		return businessAddressCity3;
	}

	public void setBusinessAddressCity3(String businessAddressCity3) {
		this.businessAddressCity3 = businessAddressCity3;
	}

	public String getCompanyNameOlder() {
		return companyNameOlder;
	}

	public void setCompanyNameOlder(String companyNameOlder) {
		this.companyNameOlder = companyNameOlder;
	}

	public String getResident1Older() {
		return resident1Older;
	}

	public void setResident1Older(String resident1Older) {
		this.resident1Older = resident1Older;
	}

	public String getResident2Older() {
		return resident2Older;
	}

	public void setResident2Older(String resident2Older) {
		this.resident2Older = resident2Older;
	}

	public String getResident3Older() {
		return resident3Older;
	}

	public void setResident3Older(String resident3Older) {
		this.resident3Older = resident3Older;
	}

	public String getAuditIDReason() {
		return auditIDReason;
	}

	public void setAuditIDReason(String auditIDReason) {
		this.auditIDReason = auditIDReason;
	}

	public String getUserStatusOlder() {
		return userStatusOlder;
	}

	public void setUserStatusOlder(String userStatusOlder) {
		this.userStatusOlder = userStatusOlder;
	}

	public String getDirverLicenceUrlTobe1() {
		return dirverLicenceUrlTobe1;
	}

	public void setDirverLicenceUrlTobe1(String dirverLicenceUrlTobe1) {
		this.dirverLicenceUrlTobe1 = dirverLicenceUrlTobe1;
	}

	public String getBusinessPhotoUrlTobe1() {
		return businessPhotoUrlTobe1;
	}

	public void setBusinessPhotoUrlTobe1(String businessPhotoUrlTobe1) {
		this.businessPhotoUrlTobe1 = businessPhotoUrlTobe1;
	}

	public String getShopFrontPhotoUrlTobe1() {
		return shopFrontPhotoUrlTobe1;
	}

	public void setShopFrontPhotoUrlTobe1(String shopFrontPhotoUrlTobe1) {
		this.shopFrontPhotoUrlTobe1 = shopFrontPhotoUrlTobe1;
	}

	public String getOperatingPermitsPhotoUrlTobe1() {
		return operatingPermitsPhotoUrlTobe1;
	}

	public void setOperatingPermitsPhotoUrlTobe1(String operatingPermitsPhotoUrlTobe1) {
		this.operatingPermitsPhotoUrlTobe1 = operatingPermitsPhotoUrlTobe1;
	}

	public String getBusinessCardUrl() {
		return businessCardUrl;
	}

	public void setBusinessCardUrl(String businessCardUrl) {
		this.businessCardUrl = businessCardUrl;
	}

	public String getBusinessCardUrlTobe() {
		return businessCardUrlTobe;
	}

	public void setBusinessCardUrlTobe(String businessCardUrlTobe) {
		this.businessCardUrlTobe = businessCardUrlTobe;
	}

	public String getBusinessCardUrlTobe1() {
		return businessCardUrlTobe1;
	}

	public void setBusinessCardUrlTobe1(String businessCardUrlTobe1) {
		this.businessCardUrlTobe1 = businessCardUrlTobe1;
	}

	public String getBusinessCardType() {
		return businessCardType;
	}

	public void setBusinessCardType(String businessCardType) {
		this.businessCardType = businessCardType;
	}

	public Integer getBusinessCardVersion() {
		return businessCardVersion;
	}

	public void setBusinessCardVersion(Integer businessCardVersion) {
		this.businessCardVersion = businessCardVersion;
	}

	public Integer getBusinessCardId() {
		return businessCardId;
	}

	public void setBusinessCardId(Integer businessCardId) {
		this.businessCardId = businessCardId;
	}

	public String getBigOrdersUrl() {
		return bigOrdersUrl;
	}

	public void setBigOrdersUrl(String bigOrdersUrl) {
		this.bigOrdersUrl = bigOrdersUrl;
	}

	public String getBigOrdersUrlTobe() {
		return bigOrdersUrlTobe;
	}

	public void setBigOrdersUrlTobe(String bigOrdersUrlTobe) {
		this.bigOrdersUrlTobe = bigOrdersUrlTobe;
	}

	public String getBigOrdersUrlTobe1() {
		return bigOrdersUrlTobe1;
	}

	public void setBigOrdersUrlTobe1(String bigOrdersUrlTobe1) {
		this.bigOrdersUrlTobe1 = bigOrdersUrlTobe1;
	}

	public String getBigOrdersType() {
		return bigOrdersType;
	}

	public void setBigOrdersType(String bigOrdersType) {
		this.bigOrdersType = bigOrdersType;
	}

	public Integer getBigOrdersVersion() {
		return bigOrdersVersion;
	}

	public void setBigOrdersVersion(Integer bigOrdersVersion) {
		this.bigOrdersVersion = bigOrdersVersion;
	}

	public Integer getBigOrdersId() {
		return bigOrdersId;
	}

	public void setBigOrdersId(Integer bigOrdersId) {
		this.bigOrdersId = bigOrdersId;
	}


	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getResident1() {
		return resident1;
	}

	public void setResident1(String resident1) {
		this.resident1 = resident1;
	}

	public String getResident2() {
		return resident2;
	}

	public void setResident2(String resident2) {
		this.resident2 = resident2;
	}

	public String getResident3() {
		return resident3;
	}

	public void setResident3(String resident3) {
		this.resident3 = resident3;
	}

	public Integer getBusinessAddressCityId() {
		return businessAddressCityId;
	}

	public void setBusinessAddressCityId(Integer businessAddressCityId) {
		this.businessAddressCityId = businessAddressCityId;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getReditRemark() {
		return reditRemark;
	}

	public void setReditRemark(String reditRemark) {
		this.reditRemark = reditRemark;
	}
}
