
package com.topjet.manage.domain.bean;
/**
 * @Modified By   :  zhengjm（silence）
 * @Modified Date :  2016年10月22日
 * 管理平台用户提醒
 */
public class RemindBean {
	private  String menuName ;
	private boolean truckRemind = false;
	private Integer truckCount = 0;
	private boolean userRemind = false;
	private Integer userCount = 0;
	private boolean refundRemind = false;
	private Integer refundCount = 0;
	private boolean complaintRemind = false;
	private Integer complaintCount = 0;
	private boolean bonusRemind = false;
	private Integer bonusCount = 0;
	private Integer allCount = 0;
	private boolean allRemind = false;

	private boolean userRegisterRemind =  false;
	private Integer userRegisterCount = 0;
	private boolean userIDRemind =  false;
	private Integer userIDCount = 0;
	private boolean userTruckRemind =  false;
	private Integer userTruckCount = 0;
	private boolean frightFirstRemind =  false;
	private Integer frightFirstCount = 0;
	private boolean frightSecondRemind =  false;
	private Integer frightSecondCount = 0;
	private boolean agencyFirstRemind =  false;
	private Integer agencyFirstCount = 0;
	private boolean agencySecondRemind =  false;
	private Integer agencySecondCount = 0;
	private boolean recoFirstRemind =  false;
	private Integer recoFirstCount = 0;
	private boolean recoSecondRemind =  false;
	private Integer recoSecondCount = 0;

	private boolean userSearch = false;
	private boolean truckSearch = false;
	private boolean orderSearch = false;

	private boolean updateUserRemind = false;
	private Integer updateUserCount  = 0;


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}

	public boolean isAllRemind() {
		return allRemind;
	}

	public void setAllRemind(boolean allRemind) {
		this.allRemind = allRemind;
	}


	public boolean isTruckRemind() {
		return truckRemind;
	}

	public void setTruckRemind(boolean truckRemind) {
		this.truckRemind = truckRemind;
	}

	public Integer getTruckCount() {
		return truckCount;
	}

	public void setTruckCount(Integer truckCount) {
		this.truckCount = truckCount;
	}

	public boolean isUserRemind() {
		return userRemind;
	}

	public void setUserRemind(boolean userRemind) {
		this.userRemind = userRemind;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public boolean isRefundRemind() {
		return refundRemind;
	}

	public void setRefundRemind(boolean refundRemind) {
		this.refundRemind = refundRemind;
	}

	public Integer getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Integer refundCount) {
		this.refundCount = refundCount;
	}

	public boolean isComplaintRemind() {
		return complaintRemind;
	}

	public void setComplaintRemind(boolean complaintRemind) {
		this.complaintRemind = complaintRemind;
	}

	public Integer getComplaintCount() {
		return complaintCount;
	}

	public void setComplaintCount(Integer complaintCount) {
		this.complaintCount = complaintCount;
	}

	public boolean isBonusRemind() {
		return bonusRemind;
	}

	public void setBonusRemind(boolean bonusRemind) {
		this.bonusRemind = bonusRemind;
	}

	public Integer getBonusCount() {
		return bonusCount;
	}

	public void setBonusCount(Integer bonusCount) {
		this.bonusCount = bonusCount;
	}


	public boolean isOrderSearch() {
		return orderSearch;
	}

	public void setOrderSearch(boolean orderSearch) {
		this.orderSearch = orderSearch;
	}

	public boolean isTruckSearch() {
		return truckSearch;
	}

	public void setTruckSearch(boolean truckSearch) {
		this.truckSearch = truckSearch;
	}

	public boolean isUserSearch() {
		return userSearch;
	}

	public void setUserSearch(boolean userSearch) {
		this.userSearch = userSearch;
	}


	public Integer getUpdateUserCount() {
		return updateUserCount;
	}

	public void setUpdateUserCount(Integer updateUserCount) {
		this.updateUserCount = updateUserCount;
	}

	public boolean isUpdateUserRemind() {
		return updateUserRemind;
	}

	public void setUpdateUserRemind(boolean updateUserRemind) {
		this.updateUserRemind = updateUserRemind;
	}

	public boolean isUserRegisterRemind() {
		return userRegisterRemind;
	}

	public void setUserRegisterRemind(boolean userRegisterRemind) {
		this.userRegisterRemind = userRegisterRemind;
	}

	public Integer getUserRegisterCount() {
		return userRegisterCount;
	}

	public void setUserRegisterCount(Integer userRegisterCount) {
		this.userRegisterCount = userRegisterCount;
	}

	public boolean isUserIDRemind() {
		return userIDRemind;
	}

	public void setUserIDRemind(boolean userIDRemind) {
		this.userIDRemind = userIDRemind;
	}

	public Integer getUserIDCount() {
		return userIDCount;
	}

	public void setUserIDCount(Integer userIDCount) {
		this.userIDCount = userIDCount;
	}

	public boolean isUserTruckRemind() {
		return userTruckRemind;
	}

	public void setUserTruckRemind(boolean userTruckRemind) {
		this.userTruckRemind = userTruckRemind;
	}

	public Integer getUserTruckCount() {
		return userTruckCount;
	}

	public void setUserTruckCount(Integer userTruckCount) {
		this.userTruckCount = userTruckCount;
	}

	public boolean isFrightFirstRemind() {
		return frightFirstRemind;
	}

	public void setFrightFirstRemind(boolean frightFirstRemind) {
		this.frightFirstRemind = frightFirstRemind;
	}

	public Integer getFrightFirstCount() {
		return frightFirstCount;
	}

	public void setFrightFirstCount(Integer frightFirstCount) {
		this.frightFirstCount = frightFirstCount;
	}

	public boolean isFrightSecondRemind() {
		return frightSecondRemind;
	}

	public void setFrightSecondRemind(boolean frightSecondRemind) {
		this.frightSecondRemind = frightSecondRemind;
	}

	public Integer getFrightSecondCount() {
		return frightSecondCount;
	}

	public void setFrightSecondCount(Integer frightSecondCount) {
		this.frightSecondCount = frightSecondCount;
	}

	public boolean isAgencyFirstRemind() {
		return agencyFirstRemind;
	}

	public void setAgencyFirstRemind(boolean agencyFirstRemind) {
		this.agencyFirstRemind = agencyFirstRemind;
	}

	public Integer getAgencyFirstCount() {
		return agencyFirstCount;
	}

	public void setAgencyFirstCount(Integer agencyFirstCount) {
		this.agencyFirstCount = agencyFirstCount;
	}

	public boolean isAgencySecondRemind() {
		return agencySecondRemind;
	}

	public void setAgencySecondRemind(boolean agencySecondRemind) {
		this.agencySecondRemind = agencySecondRemind;
	}

	public Integer getAgencySecondCount() {
		return agencySecondCount;
	}

	public void setAgencySecondCount(Integer agencySecondCount) {
		this.agencySecondCount = agencySecondCount;
	}

	public boolean isRecoFirstRemind() {
		return recoFirstRemind;
	}

	public void setRecoFirstRemind(boolean recoFirstRemind) {
		this.recoFirstRemind = recoFirstRemind;
	}

	public Integer getRecoFirstCount() {
		return recoFirstCount;
	}

	public void setRecoFirstCount(Integer recoFirstCount) {
		this.recoFirstCount = recoFirstCount;
	}

	public boolean isRecoSecondRemind() {
		return recoSecondRemind;
	}

	public void setRecoSecondRemind(boolean recoSecondRemind) {
		this.recoSecondRemind = recoSecondRemind;
	}

	public Integer getRecoSecondCount() {
		return recoSecondCount;
	}

	public void setRecoSecondCount(Integer recoSecondCount) {
		this.recoSecondCount = recoSecondCount;
	}
}

