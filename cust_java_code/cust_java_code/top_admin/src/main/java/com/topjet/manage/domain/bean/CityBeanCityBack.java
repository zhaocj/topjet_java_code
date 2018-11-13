
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
public class CityBeanCityBack {
	private String longitude;//经度
	private String latitude;//纬度
	private String cityFullName;//全称
	private String adcode;
	private String n;
	private List<CityBeanDistBack> a;

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public List<CityBeanDistBack> getA() {
		return a;
	}
	public void setA(List<CityBeanDistBack> a) {
		this.a = a;
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

	public String getCityFullName() {
		return cityFullName;
	}

	public void setCityFullName(String cityFullName) {
		this.cityFullName = cityFullName;
	}



}

