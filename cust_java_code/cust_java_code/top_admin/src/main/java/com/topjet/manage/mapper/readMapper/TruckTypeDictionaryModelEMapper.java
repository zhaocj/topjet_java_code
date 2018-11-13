package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.TruckTypeDictionaryModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TruckTypeDictionaryModelEMapper {

    @Select("SELECT *  FROM resourceDB.truckTypeDictionary WHERE deleted = 0  and valid = 1 ")
    List<TruckTypeDictionaryModel> listTruckType();
    /**
     * 查询所有车型分页
     */
    @SelectProvider(type = TruckTypeDictionaryModelEMapper.SubscribeLineProviderSql.class,method = "getTruckTypeList")
    public List<TruckTypeDictionaryModel> getTruckTypeList(TruckTypeDictionaryModel truckTypeDictionaryModel);
    /**
     * 所有车型页数
     */
    @SelectProvider(type = TruckTypeDictionaryModelEMapper.SubscribeLineProviderSql.class,method = "queryTruckTypeCount")
    public Integer queryTruckTypeCount(TruckTypeDictionaryModel truckTypeDictionaryModel);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 查询车型的总数
         *
         */
        public String queryTruckTypeCount(final TruckTypeDictionaryModel truckTypeDictionaryModel){
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT COUNT(DISTINCT td.id) from resourceDB.truckTypeDictionary td where td.deleted = 0");
            if (!StringUtils.isEmpty(truckTypeDictionaryModel.getDisplayName())){
                sb.append(" AND td.displayName = #{displayName}");
            }
            if (truckTypeDictionaryModel.getValid() != null){
                sb.append(" AND td.valid = #{valid}");
            }

            return sb.toString();
        }

        /**
         * 查询车型分页
         */
        public String getTruckTypeList(final TruckTypeDictionaryModel truckTypeDictionaryModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" td.id,td.displayName,td.code,td.truckOrder,td.valid,td.updateTime,td.createTime,td.deleted");
            sb.append(" FROM ");
            sb.append(" resourceDB.truckTypeDictionary td where td.deleted = 0");
            if (!StringUtils.isEmpty(truckTypeDictionaryModel.getDisplayName())){
                sb.append(" AND td.displayName = #{displayName}");
            }
            if (truckTypeDictionaryModel.getValid() != null){
                sb.append(" AND td.valid = #{valid}");
            }
            sb.append(" GROUP BY td.id ORDER BY td.id DESC  ");
            sb.append(" LIMIT #{offset} , #{limit} ");
            return sb.toString();
        }
    }



    /**
     * 根据车型名称查询
     */
    @Select(" select DISTINCT td.id,td.displayName,td.code,td.truckOrder,td.valid,td.updateTime,td.createTime,td.deleted\n" +
            " from resourceDB.truckTypeDictionary td where td.deleted = 0 and td.displayName = #{displayName}")
    public List<TruckTypeDictionaryModel> findTruckTypeByName(@Param("displayName") String displayName);
    /**
     * 根据车型名称查询
     */
    @Select(" select DISTINCT td.id,td.displayName,td.code,td.truckOrder,td.valid,td.updateTime,td.createTime,td.deleted\n" +
            " from resourceDB.truckTypeDictionary td where td.deleted = 0 and td.code = #{code}")
    public List<TruckTypeDictionaryModel> findTruckTypeByCode(@Param("code") String code);
    /**
     * 根据车型id查询
     */
    @Select(" select DISTINCT td.id,td.displayName,td.code,td.truckOrder,td.valid,td.updateTime,td.createTime,td.deleted\n" +
            " from resourceDB.truckTypeDictionary td where td.deleted = 0 and td.id = #{id}")
    public TruckTypeDictionaryModel findTruckTypeById(@Param("id") Integer id);

    @Select("SELECT *  FROM resourceDB.truckTypeDictionary WHERE deleted = 0  and valid = 1 ")
    List<TruckTypeDictionaryModel> findTruckType();

    @Select(" select DISTINCT td.id,td.displayName,td.code,td.truckOrder,td.valid,td.updateTime,td.createTime,td.deleted\n" +
            " from resourceDB.truckTypeDictionary td where td.deleted = 0")
    public List<TruckTypeDictionaryModel> getTruckType(TruckTypeDictionaryModel truckTypeDictionaryModel);

}