package com.topjet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat DF_2YMD = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat DF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String HHmmss = " 00:00:00";
	
	
	public static String getDisplay2YMD(Date date) {
		try {
			if (null == date) {
				return null;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return ((SimpleDateFormat) DF_2YMD.clone()).format(calendar
					.getTime());
		} catch (Exception e) {
			return null;
		}	
	}
	
	/**
	 * 获得date时间
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDisplayYMDHMS(Date date) {
		try {
			if (null == date) {
				return null;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return ((SimpleDateFormat) DF_YMDHMS.clone()).format(calendar
					.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取当前日期前一天的日期
	 * @return
	 */
	public static String getPreDay2Ymd(){
		Calendar calendar = Calendar.getInstance();		
        calendar.add(Calendar.DATE, -1);    //得到前一天
        return getDisplay2YMD(calendar.getTime());
	}

	/**
	 * String转换成DATE
	 * @param str yyyy-MM-dd HH:mm:ss
	 * @return
     */
	public static Date StringToDate(String str){
		try {
			if(str.length()<11){
				str=str+HHmmss;
			}
			return DF_YMDHMS.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

//	public static void main(String[] agrs){
//
//		System.out.println(StringToDate("2015-08-11"));
//
//
//	}




}
