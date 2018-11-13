package com.topjet.manage.mapper.readMapper;

import com.topjet.common.util.CityUtil;
import com.topjet.manage.domain.bean.CityBean;
import com.topjet.manage.mapper.writeMapper.BaseMapper;
import com.topjet.manage.domain.model.CityModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface CityModelEMapper extends BaseMapper<CityModel>{

    List<CityModel> selectListByParam(Map<String,Object> paramMap);

    /**
     * 分页查询所有城市
     */
    @SelectProvider(type = CityModelEMapper.SubscribeLineProviderSql.class,method = "getCityAll")
    public List<CityModel> getCityAll(CityBean cityBean);
    /**
     * 城市页数
     */
    @SelectProvider(type = CityModelEMapper.SubscribeLineProviderSql.class,method = "getCityCount")
    public Integer getCityCount(CityBean cityBean);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 查询车长的总数
         *
         */
        public String getCityCount(final CityBean cityBean){
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT COUNT(DISTINCT id) from resourceDB.city where deleted = 0");
            if (!StringUtils.isEmpty(cityBean.getCityName())){
                sb.append(" AND cityName = #{cityName}");
            }
            if ((!"".equals(cityBean.getProvince()) && !cityBean.getProvince().equals("0")) && (cityBean.getCity().equals("1") || cityBean.getCity().equals(""))){
                sb.append(" AND parentId = #{parentId}");
            }
            if (!cityBean.getCity().equals("0") && cityBean.getCity() != ""){
                if (!cityBean.getCity().equals("1") || !cityBean.getCity().equals("")) {
                    sb.append(" AND parentId = #{parentId1}");
                }
            }
            if (!cityBean.getDist().equals("0") && cityBean.getDist() != "") {
                if (!cityBean.getDist().equals("1") || !cityBean.getDist().equals("")) {
                    sb.append(" AND parentId = #{parentId1}");
                    sb.append(" AND adcode = #{cityId}");
                }
            }
            return sb.toString();
        }

        /**
         * 查询车长分页
         */
        public String getCityAll(final CityBean cityBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append("  id, adcode, citycode, cityName, cityFullName, parentId, level, longitude, latitude,formatName, flag, zip, createTime, updateTime, deleted");
            sb.append(" FROM ");
            sb.append(" resourceDB.city where deleted = 0");
            if (!StringUtils.isEmpty(cityBean.getCityName())){
                sb.append(" AND cityName = #{cityName}");
            }
            if ((!"".equals(cityBean.getProvince()) && !cityBean.getProvince().equals("0")) && (cityBean.getCity().equals("1") || cityBean.getCity().equals(""))){
                sb.append(" AND parentId = #{parentId}");
            }
            if (!cityBean.getCity().equals("0") && cityBean.getCity() != ""){
                if (!cityBean.getCity().equals("1") || !cityBean.getCity().equals("")) {
                    sb.append(" AND parentId = #{parentId1}");
                }
            }
            if (!cityBean.getDist().equals("0") && cityBean.getDist() != "") {
                if (!cityBean.getDist().equals("1") || !cityBean.getDist().equals("")) {
                    sb.append(" AND parentId = #{parentId1}");
                    sb.append(" AND adcode = #{cityId}");
                }
            }
            sb.append(" GROUP BY id ORDER BY id DESC  ");
            if(cityBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }



    /**
     * 根据级别获取省
     */
    List<CityModel> getProvince(Integer level);
    /**
     * 根据城市编号查询
     */
    List<CityModel> findCity(Integer adcode);
    /**
     *根据id查询city
     */
    public CityModel findCityById(Integer id);
    /**
     * 根据parentid查询
     */
    List<CityModel> findCityByCode(Integer adcode);
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

    @Select("SELECT id,adcode,citycode,cityName,cityFullName,parentId,level,longitude,latitude,formatName,flag FROM resourceDB.city " +
            "WHERE  deleted=0 order by adcode asc")
   public List<CityModel> getCityList();

}