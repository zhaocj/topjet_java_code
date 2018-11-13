package com.topjet.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by pengtao on 9/8/15.
 */
public class CityUtil {

    public static String filterCityName(String cityName) {
        return CityUtil.firstLevelCity(StringUtils.trimToEmpty(cityName)) ? "" : StringUtils.trimToEmpty(cityName);
    }

    /**
     * 是否是直辖市
     *
     * @param city
     * @return
     */
    public static boolean firstLevelCity(String city) {

        if (StringUtils.isEmpty(city))
            return false;

        if (city.contains("上海") || city.contains("北京") || city.contains("重庆") || city.contains("天津"))
            return true;
        return false;
    }

    public static boolean firstLevelCityId(String cityId) {

        if (StringUtils.isEmpty(cityId))
            return false;
        // 010100 北京二级 020100 上海二级 040100 重庆 030100 天津
        if (cityId.contains("020100") || cityId.contains("010100") || cityId.contains("040100") || cityId.contains("030100"))
            return true;
        return false;
    }

    /**
     * 判断二级地址是否相同 (如果是直辖市则直接判断一级)
     *
     * @param cityId
     * @return 相同返回true 不同返回false
     */
    public static boolean filterLevel2(String cityId, String cityId2) {

        if (StringUtils.isEmpty(cityId) || StringUtils.isEmpty(cityId2)) {
            return false;
        }

        if (CityUtil.firstLevelCityId(cityId)) { // 直辖市
            if (cityId.substring(0, 2).equals(cityId2.substring(0, 2))) {
                return true;
            }

        } else {
            if (cityId.substring(0, 4).equals(cityId2.substring(0, 4))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 若cityName 为直辖市三级地址,则在列表中显示,否则不显示,货源查询列表
     *
     * @param cityName   三级地址名称
     * @param level1Name 一级地址名称
     * @return
     */
    public static String filterLevel3CityName4List(String cityName, String level1Name) {

        if (CityUtil.firstLevelCity(level1Name)) {
            if (StringUtils.isEmpty(cityName))
                return "";
            return cityName;
        } else {
            return "";
        }
    }


    /**
     * 如果二级为直辖市,则二级不显示  拼接成串返回
     *
     * @param cityName1
     * @param cityName2
     * @param cityName3
     * @return
     */
    public static String filterLevel2CityList(String cityName1, String cityName2, String cityName3) {

        if (CityUtil.firstLevelCity(cityName1)) {
            if (StringUtils.isEmpty(cityName3))
                return cityName1;
            return StringUtils.trimToEmpty(cityName1 + " " + cityName3);
        } else {
            return StringUtils.trimToEmpty(cityName1 + " " + cityName2 + " " + cityName3);
        }
    }

    /**
     * 取得一级城市最小值
     */
    public static String getFirstCityMinCode(String childCityCode) {
        StringBuffer sbBuffer = new StringBuffer();
        return sbBuffer.append(childCityCode.substring(0, 2)).append("0000").toString();
    }

    /**
     * 取得一级城市最大值
     */
    public static String getFirstCityMaxCode(String childCityCode) {
        StringBuffer sbBuffer = new StringBuffer();
        return sbBuffer.append(childCityCode.substring(0, 2)).append("9999").toString();
    }

    /**
     * 取得二级城市最小值
     */
    public static String getSecondCityMinCode(String childCityCode) {
        StringBuffer sbBuffer = new StringBuffer();
        return sbBuffer.append(childCityCode.substring(0, 4)).append("00").toString();
    }

    /**
     * 取得二级城市最大值
     */
    public static String getSecondCityMaxCode(String childCityCode) {
        StringBuffer sbBuffer = new StringBuffer();
        return sbBuffer.append(childCityCode.substring(0, 4)).append("99").toString();
    }


    /**
     * 计算城市的最大最大值
     *
     * @param cityId
     */
    public static String convertCityMax(String cityId) {
        if (StringUtils.isEmpty(cityId)) {
            return "";
        }
        if (cityId.substring(cityId.length() - 4).equals("0000")) { // 一级地址
            return CityUtil.getFirstCityMaxCode(cityId);
        } else if (cityId.substring(cityId.length() - 2).equals("00")) { // 二级地址
            return CityUtil.getSecondCityMaxCode(cityId);
        } else { // 三级地址
            return cityId;
        }
    }

    /**
     * 计算城市的最大最小值
     *
     * @param cityId
     */
    public static String convertCityMin(String cityId) {
        if (StringUtils.isEmpty(cityId)) {
            return "";
        }
        if (cityId.substring(cityId.length() - 4).equals("0000")) { // 一级地址
            return CityUtil.getFirstCityMinCode(cityId);
        } else if (cityId.substring(cityId.length() - 2).equals("00")) { // 二级地址
            return CityUtil.getSecondCityMinCode(cityId);
        } else { // 三级地址
            return cityId;
        }
    }

    public static String convertToString(Integer cityId) {
        if (cityId.intValue() == 0) return "";
        return String.format("%06d", cityId);
    }

    public static Integer convertToInteger(String cityId) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(cityId)) return 0;

        return Integer.parseInt(cityId);
    }

    /**
     * 根据城市显示名称删除省市标识
     *
     * @param cityName
     * @return
     */
    public static String cityNameFormat(String cityName) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(cityName)) {
            if (cityName.equals("市辖区")) {
                return cityName;
            }
            return cityName.trim().replace("省", "").replace("市", "");
        }
        return "";
    }

    /**
     * 出发地目的地城市拼接（直辖市只显示一级和三级，其他只显示后两级名称）
     *
     * @param cityName1
     * @param cityName2
     * @param cityName3
     * @return
     */
    public static String cityNameFormat(String cityName1, String cityName2, String cityName3) {
        cityName1 = cityNameFormat(cityName1);
        cityName2 = cityNameFormat(cityName2);
        cityName3 = cityNameFormat(cityName3);
        if (CityUtil.firstLevelCity(cityName1)) {
            if (StringUtils.isEmpty(cityName3))
                return cityName1;
            return StringUtils.trimToEmpty(cityName1 + " " + cityName3);
        } else if (StringUtils.isBlank(cityName3)) {
            return StringUtils.trimToEmpty(cityName1 + " " + cityName2);
        } else {
            return StringUtils.trimToEmpty(cityName2 + " " + cityName3);
        }
    }

    /**
     * 根据城市id返回对应的一级城市id
     *
     * @param cityId
     * @return
     */
    public static String getFirstLevelCityId(Integer cityId) {

        String result = convertToString(cityId);
        if (StringUtils.isEmpty(result))
            return "";
        return result.substring(0, 2) + "0000";
    }

    /**
     * 根据城市id返回对应的二级城市id
     *
     * @param cityId
     * @return
     */
    public static String getSecondLevelCityId(Integer cityId) {
        String result = convertToString(cityId);
        if (StringUtils.isEmpty(result))
            return "";
        if (result.endsWith("0000"))
            return "";
        return result.substring(0, 4) + "00";

    }

    /**
     * 根据城市id返回对应的三级城市id
     *
     * @param cityId
     * @return
     */
    public static String getThirdLevelCityId(Integer cityId) {
        String result = convertToString(cityId);
        if (StringUtils.isEmpty(result))
            return "";
        if (result.endsWith("00"))
            return "";
        return result;
    }

}
