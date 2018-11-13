package com.topjet.manage.service;



import com.topjet.manage.domain.bean.CityBean;
import com.topjet.manage.domain.model.CityModel;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by pengtao on 10/19/15.
 */
public interface CityService {
    
    
    /**
     * 获取城市 层级的list
     * @param cityCode 城市对象
     * @return list中存放城市的等级,依次为一二三级
     */
    public CityModel getCityModel(String cityCode);
    
    /*
     * 取得子城市code 获得一级城市
     */
    public CityModel  getFirstCity(String childCityCode);
    
    /*
     * 取得子城市code 获得二级城市
     */
    public CityModel  getSecondCity(String childCityCode);
    
    /**
     * 获取城市 层级的list
     * @param cityModel 城市对象
     * @return list中存放城市的等级,依次为一二三级
     */
    public List<String> getCity(CityModel cityModel);
    
    /**
     * 获取城市 层级的list
     * @param cityCode 城市对象
     * @return list中存放城市的等级,依次为一二三级
     */
    public List<String> getCity(String cityCode);
    
    /**
     * 解析List 获取一级地址
     * @param cityList 调用 getCity 返回的list
     * @return
     */
    public String getCityAddress1(List<String> cityList);
    
    /**
     * 解析List 获取二级地址
     * @param cityList 调用 getCity 返回的list
     * @return
     */
    public String getCityAddress2(List<String> cityList);
    
    /**
     * 解析List 获取三级地址
     * @param cityList 调用 getCity 返回的list
     * @return
     */
    public String getCityAddress3(List<String> cityList);

    /**
     * 通过城市名和城市级别获取 CityId
     * @param cityName 城市名
     * @param cityLevel2 城市级别
     * @return
     */
    public CityModel getCityModelByName(String cityName, String cityLevel2);
    
    /*
     * 根据城市名称获得cityId
     */
    public String  getCityId(String cityName);
    /*
     * 根据城市名称获得cityId
     */
    public String  getCityIdByFullName(String cityFullName);
    
    /**
     * 根据cityId获取三级所有cityId
     * @param cityId
     * @return
     */
    public List<String>  getCityIdList(String cityId);

    /**
     * 根据城市ID,获取城市名称的省市区全称
     * @param cityId
     * @return
     */
    public String getFullName(String cityId);

    /**
     * 根据城市ID,获取听单设置城市区域显示名称
     * 存在区显示为’市+区‘；不存在区显示’市‘
     * @param cityId
     * @return
     *
     *
     */
    public String getOrderNotificationSettingName(String cityId);

    /**
     * 根据城市id获取父级二级城市的城市cityId（）
     * @param cityId
     * @return
     */
    public String getSecondCityCode(Integer cityId);

    /**
     * 根据cityId获取城市name
     * @param cityId
     * @return
     */
    public String getCityName(String cityId);

    /**
     * 查询所有城市
     */
    public List<CityModel> getCityAll(CityBean cityBean);
    /**
     * 城市页数
     */
    public Integer getCityCount(CityBean cityBean);

    /**
     * 添加城市
     * @param cityBean
     * @return
     */
    public int createCity(CityBean cityBean);

    /**
     * 根据级别获取省
     */
    List<CityModel> getProvince(Integer level);


    List<CityModel> findCity(Integer adcode);

    /**
     * 根据parentid查询
     */
    List<CityModel> findCityByCode(Integer adcode);

    /**
     *根据id查询city
     */
    public CityModel findCityById(Integer id);


    CityBean getCityInfo(int id) throws IllegalAccessException, InvocationTargetException;
    /**
     * 修改城市
     */
    public int update(CityBean cityBean);
    /**
     * 查询level=1的数据 根据城市编号排序
     */
    public List<CityModel> findCityLevel1(CityModel cityModel);
    /**
     * 查询level=2的数据 根据城市编号排序
     */
    public List<CityModel> findCityLevel2(CityModel cityModel);
    /**
     * 查询level=3的数据 根据城市编号排序
     */
    public List<CityModel> findCityLevel3(CityModel cityModel);
    /**
     * 查询city的数据 根据城市编号排序
     */
    public List<CityModel> findCityList(CityModel cityModel);
    /**
     * 更新资源文件
     */
    public void updateResourceFileVersionInfo(int rows) throws Exception;
    // 获取安卓city
    String getAndroid();

    // 获取ios city
    String getIos();

    // 获取城市List
    List<CityModel> getList();

}
