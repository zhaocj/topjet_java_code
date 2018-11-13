package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.TruckLengthDictionaryModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface TruckLengthDictionaryModelEMapper {

    @Select("SELECT *  FROM resourceDB.truckLengthDictionary WHERE deleted = 0  and valid = 1 ")
    List<TruckLengthDictionaryModel> listTruckLength();
    /**
     * 查询所有车长
     */
    @SelectProvider(type = TruckLengthDictionaryModelEMapper.SubscribeLineProviderSql.class,method = "getTruckLengthList")
    public List<TruckLengthDictionaryModel> getTruckLengthList(TruckLengthDictionaryModel truckTypeDictionaryModel);

    /**
     * 所有车长页数
     */
    @SelectProvider(type = TruckLengthDictionaryModelEMapper.SubscribeLineProviderSql.class,method = "queryTruckLengthCount")
    public Integer queryTruckLengthCount(TruckLengthDictionaryModel truckTypeDictionaryModel);


    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 查询车长的总数
         *
         */
        public String queryTruckLengthCount(final TruckLengthDictionaryModel truckLengthDictionaryModel){
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT COUNT(DISTINCT td.id) from resourceDB.truckLengthDictionary td where td.deleted = 0");
            if (!StringUtils.isEmpty(truckLengthDictionaryModel.getDisplayName())){
                sb.append(" AND td.displayName = #{displayName}");
            }
            if (truckLengthDictionaryModel.getValid() != null){
                sb.append(" AND td.valid = #{valid}");
            }

            return sb.toString();
        }

        /**
         * 查询车长分页
         */
        public String getTruckLengthList(final TruckLengthDictionaryModel truckLengthDictionaryModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" td.id,td.displayName,td.length,td.lengthOrder,td.valid,td.updateTime,td.createTime,td.deleted");
            sb.append(" FROM ");
            sb.append(" resourceDB.truckLengthDictionary td where td.deleted = 0");
            if (!StringUtils.isEmpty(truckLengthDictionaryModel.getDisplayName())){
                sb.append(" AND td.displayName = #{displayName}");
            }
            if (truckLengthDictionaryModel.getValid() != null){
                sb.append(" AND td.valid = #{valid}");
            }

            sb.append(" GROUP BY td.id ORDER BY td.id DESC  ");
            sb.append(" LIMIT #{offset} , #{limit} ");
            return sb.toString();
        }
    }

    /**
     * 根据车长查询
     */
    @Select(" select td.id,td.displayName,td.length,td.lengthOrder,td.valid,td.updateTime,td.createTime,td.deleted\n" +
            " from resourceDB.truckLengthDictionary td where td.deleted = 0 and td.length = #{length}")
    public List<TruckLengthDictionaryModel> findTruckLengthByLength(@Param("length") BigDecimal length);
    /**
     * 根据车长id查询
     */
    @Select(" select td.id,td.displayName,td.length,td.lengthOrder,td.valid,td.updateTime,td.createTime,td.deleted\n" +
            " from resourceDB.truckLengthDictionary td where td.deleted = 0 and td.id = #{id}")
    public TruckLengthDictionaryModel findTruckLengthById(@Param("id") Integer id);

    @Select("SELECT *  FROM resourceDB.truckLengthDictionary WHERE deleted = 0  and valid = 1 ")
    List<TruckLengthDictionaryModel> findTruckLength();

    @Select(" select td.id,td.displayName,td.length,td.lengthOrder,td.valid,td.updateTime,td.createTime,td.deleted\n" +
            " from resourceDB.truckLengthDictionary td where td.deleted = 0")
    public List<TruckLengthDictionaryModel> getTruckLength(TruckLengthDictionaryModel truckTypeDictionaryModel);

}