package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.TruckTypeDictionaryModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

public interface TruckTypeDictionaryModelMapper {

    @Insert("INSERT INTO resourceDB.truckTypeDictionary(displayName,code,truckOrder,icon) VALUES(${displayName},#{code},${truckOrder},${icon})")
    int insert(TruckTypeDictionaryModel truckTypeDictionaryModel);

    @Update("UPDATE resourceDB.truckTypeDictionary SET displayName= #{displayName}, code= #{code},truckOrder= #{truckOrder},icon= #{icon},updateTime= #{updateTime} where id = ${id}")
    Integer update(TruckTypeDictionaryModel truckTypeDictionaryModel);

    @InsertProvider(type = GoodsDetailInfoProviderSql.class,method = "insertType")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertType(TruckTypeDictionaryModel truckTypeDictionaryModel);

    @UpdateProvider(type = GoodsDetailInfoProviderSql.class,method = "updateType")
    Integer updateType(TruckTypeDictionaryModel truckTypeDictionaryModel);
    class GoodsDetailInfoProviderSql {
        public String insertType(final TruckTypeDictionaryModel truckTypeDictionaryModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.truckTypeDictionary");
                if (!StringUtils.isEmpty(truckTypeDictionaryModel.getDisplayName()))
                    VALUES("displayName","#{displayName}");
                if (!StringUtils.isEmpty(truckTypeDictionaryModel.getCode()))
                    VALUES("code","#{code}");
                if (truckTypeDictionaryModel.getTruckOrder() != null)
                    VALUES("truckOrder","#{truckOrder}");
                if (truckTypeDictionaryModel.getValid()!=null)
                    VALUES("valid","#{valid}");
            }}.toString();
        }

        public String updateType(final TruckTypeDictionaryModel truckTypeDictionaryModel){
            return new SQL(){{
                UPDATE("resourceDB.truckTypeDictionary");
                if (!StringUtils.isEmpty(truckTypeDictionaryModel.getDisplayName()))
                    SET("displayName = #{displayName}");
                if (truckTypeDictionaryModel.getValid() != null)
                    SET("valid = #{valid}");
                if (truckTypeDictionaryModel.getCode() != null)
                    SET("code = #{code}");
                if (truckTypeDictionaryModel.getTruckOrder() != null)
                    SET("truckOrder = #{truckOrder}");
                if (truckTypeDictionaryModel.getUpdateTime() != null)
                    SET("updateTime = #{updateTime}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }

}