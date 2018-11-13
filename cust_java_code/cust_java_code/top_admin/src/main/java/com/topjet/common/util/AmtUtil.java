
package com.topjet.common.util;

import java.math.BigDecimal;

/**
 * @Filename      :  AmtUtil.java
 * @Package       :  com.topjet.common.util
 * @version       :  1.0
 * @Description   :  后台管理框架1.0版本
 * @Copyright     :  Copyright (c)2015-2015
 * @Company       :  上海拓景信息科技有限公司  http://www.566560.com
 * @author        :  唐永梦（silence）
 * @Create Date   :  2015年9月6日
 * @Modified By   :  唐永梦（silence）
 * @Modified Date :  2015年9月6日
 * @Description	:金额处理帮助类
 */
public class AmtUtil {
	
	
	
	/**
	 * BigDecimal保留两位小数转换成String(为null返回"")
	 * @param bd
	 * @return
	 */
	public static String BigDecimalToString(BigDecimal bd){
		if(bd==null){
			return "";
		}
		return bd.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	
	/**
	 * 把两个String值转成bigdecimal进行加保留两位小数返回String
	 * @param str
	 * @param str1
	 * @return
	 */
	public static String stingToBigDecimalAdd(String str,String str1){
		if(isBlank(str)){
			str="0";
		}
		if (isBlank(str1)) {
			str1="0";
		}
		BigDecimal result=new BigDecimal(str).add(new BigDecimal(str1)).setScale(2,BigDecimal.ROUND_DOWN);
		return result.toString();
	}
	
	/**
	 * 把两个String值转成integer进行加保留两位小数返回String
	 * @param str
	 * @param str1
	 * @return
	 */
	public static String stingToIntegerAdd(String str,String str1){
		if(isBlank(str)){
			str="0";
		}
		if (isBlank(str1)) {
			str1="0";
		}
		Integer result=Integer.parseInt(str)+Integer.parseInt(str1);
		return result.toString();
	}
	
	/**
	 * String转成BigDecimal保留两位小数返回
	 * @param str
	 * @return
	 */
	public static BigDecimal stingToBigDecimal(String str){
		if(isBlank(str)){
			str="0";
		}
		BigDecimal result=new BigDecimal(str).setScale(2,BigDecimal.ROUND_DOWN);
		return result;
	}
	
	/**
	 * 判断字符串是否为null或者空字符串
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (null == str)
			return true;
		if ("".equals(str.replaceAll("　"," ").trim()))
			return true;
		return false;
	}
	
}

