
package com.topjet.manage.domain.bean;

import java.util.List;


/**
 * @Filename      :  CityBeanCity.java
 * @Package       :  com.topjet.admin.test
 * @version       :  1.0
 * @Description   :  后台管理框架1.0版本
 * @Copyright     :  Copyright (c)2015-2015
 * @Company       :  上海拓景信息科技有限公司  http://www.566560.com
 * @author        :  唐永梦（silence）
 * @Create Date   :  2015年10月23日
 * @Modified By   :  唐永梦（silence）
 * @Modified Date :  2015年10月23日
 * @DescriptionClass   :  TODO(说明类的作用) 
 */
public class CityBeanCityIos {

	private String adcode;
	private String cityName;
	private String citycode;
	/*private String weatherId;
	private String flag;*/
	private String cityFullName;
	private String longitude;//百度经度
	private String latitude;//百度纬度
	//private List<CityBean> dist;
	private String parentId;
	private List<CityResourceBean> dist;

	public List<CityResourceBean> getDist() {
		return dist;
	}

	public void setDist(List<CityResourceBean> dist) {
		this.dist = dist;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
/*	public String getWeatherId() {
		return weatherId;
	}
	public void setWeatherId(String weatherId) {
		this.weatherId = weatherId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List<CityBean> getDist() {
		return dist;
	}
	public void setDist(List<CityBean> dist) {
		this.dist = dist;
	}*/
	public String getCityFullName() {
		return cityFullName;
	}
	public void setCityFullName(String cityFullName) {
		this.cityFullName = cityFullName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
}

