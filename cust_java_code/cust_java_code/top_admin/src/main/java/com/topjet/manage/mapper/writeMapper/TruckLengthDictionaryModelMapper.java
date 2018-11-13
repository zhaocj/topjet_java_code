package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.TruckLengthDictionaryModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

public interface TruckLengthDictionaryModelMapper {

    @Insert("INSERT INTO truckDB.truckLengthDictionary(displayName,length,lengthOrder,createTime) VALUES(${displayName},#{length},${lengthOrder},${createTime})")
    int insert(TruckLengthDictionaryModel record);

    @Update("UPDATE truckDB.truckLengthDictionary SET displayName= #{displayName}, length= #{length},lengthOrder= #{lengthOrder},updateTime= #{updateTime} where id = ${id}")
    int updateBy(TruckLengthDictionaryModel record);

    @InsertProvider(type = GoodsDetailInfoProviderSql.class,method = "insertLength")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertLength(TruckLengthDictionaryModel record);

    @UpdateProvider(type = GoodsDetailInfoProviderSql.class,method = "updateByLength")
    int updateByLength(TruckLengthDictionaryModel record);

    class GoodsDetailInfoProviderSql {
        public String insertLength(final TruckLengthDictionaryModel truckLengthDictionaryModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.truckLengthDictionary");
                if (!StringUtils.isEmpty(truckLengthDictionaryModel.getDisplayName()))
                    VALUES("displayName","#{displayName}");
                if (truckLengthDictionaryModel.getLength() != null)
                    VALUES("length","#{length}");
                if (truckLengthDictionaryModel.getLengthOrder()!=null)
                    VALUES("lengthOrder","#{lengthOrder}");
                if (truckLengthDictionaryModel.getValid() != null)
                    VALUES("valid","#{valid}");
            }}.toString();
        }


        public String updateByLength(final TruckLengthDictionaryModel truckLengthDictionaryModel){
            return new SQL(){{
                UPDATE("resourceDB.truckLengthDictionary");
                if (!StringUtils.isEmpty(truckLengthDictionaryModel.getDisplayName()))
                    SET("displayName = #{displayName}");
                if (truckLengthDictionaryModel.getLength() != null)
                    SET("length = #{length}");
                if (truckLengthDictionaryModel.getValid() != null)
                    SET("valid = #{valid}");
                if (truckLengthDictionaryModel.getUpdateTime() != null)
                    SET("updateTime = #{updateTime}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }

}