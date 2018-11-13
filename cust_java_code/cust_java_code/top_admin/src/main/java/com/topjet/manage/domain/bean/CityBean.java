package com.topjet.manage.domain.bean;


import com.topjet.manage.domain.model.CityModel;

public class CityBean extends CityModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617139978920023572L;

	private String province;
	
	private Integer cityPrimaryKey;
	
	private String cityDisplayName;
	
	private String city;

	private String dist;

	private Integer parentId;
	private Integer parentId1;
	private Integer cityId;

	@Override
	public Integer getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId1() {
		return parentId1;
	}

	public void setParentId1(Integer parentId1) {
		this.parentId1 = parentId1;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getDist() {return dist;}

	public void setDist(String dist) {this.dist = dist;	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityDisplayName() {
		return cityDisplayName;
	}

	public void setCityDisplayName(String cityDisplayName) {
		this.cityDisplayName = cityDisplayName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getCityPrimaryKey() {
		return cityPrimaryKey;
	}

	public void setCityPrimaryKey(Integer cityPrimaryKey) {
		this.cityPrimaryKey = cityPrimaryKey;
	}

	
	
	
	
	

}
