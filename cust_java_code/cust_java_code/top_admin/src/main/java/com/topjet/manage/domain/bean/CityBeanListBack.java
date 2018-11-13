
package com.topjet.manage.domain.bean;

import java.util.List;

/**
 * @Filename      :  CityBeanListBack.java
 * @Package       :  com.topjet.admin.city.bean
 * @version       :  1.0
 * @Description   :  后台管理框架1.0版本
 * @Copyright     :  Copyright (c)2015-2015
 * @Company       :  上海拓景信息科技有限公司  http://www.566560.com
 * @author        :  唐永梦（silence）
 * @Create Date   :  2015年10月27日
 * @Modified By   :  唐永梦（silence）
 * @Modified Date :  2015年10月27日
 * @DescriptionClass   :  TODO(说明类的作用) 
 */
public class CityBeanListBack {
	private List<CityBeanProvBack> citylist;

	public List<CityBeanProvBack> getCitylist() {
		return citylist;
	}

	public void setCitylist(List<CityBeanProvBack> citylist) {
		this.citylist = citylist;
	}

}

