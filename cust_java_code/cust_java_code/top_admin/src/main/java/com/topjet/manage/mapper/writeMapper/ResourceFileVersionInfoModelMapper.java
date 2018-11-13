package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.ResourceFileKeyInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/9/19.
 */
public interface ResourceFileVersionInfoModelMapper {

    @InsertProvider(type = ResourceInfoProviderSql.class,method = "insertResource")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    Integer insertResource(ResourceFileVersionInfoModel resourceFileVersionInfomodel);

    @UpdateProvider(type = ResourceInfoProviderSql.class,method = "updateResource")
    Integer updateResource(ResourceFileVersionInfoModel resourceFileVersionInfomodel);


    @InsertProvider(type = ResourceInfoProviderSql.class,method = "insertKeyResource")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    Integer insertKeyResource(ResourceFileKeyInfoModel resourceFileKeyInfoModel);

    @UpdateProvider(type = ResourceInfoProviderSql.class,method = "updateKeyResource")
    Integer updateKeyResource(ResourceFileKeyInfoModel resourceFileKeyInfoModel);

    class ResourceInfoProviderSql {
        public String insertResource(final ResourceFileVersionInfoModel resourceFileVersionInfomodel){
            return new SQL(){{
                INSERT_INTO("resourceDB.resourceFileVersionInfo");
                if (resourceFileVersionInfomodel.getResourceType() != null)
                    VALUES("resourceType","#{resourceType}");
                if (resourceFileVersionInfomodel.getSystemType() != null)
                    VALUES("systemType","#{systemType}");
                if (resourceFileVersionInfomodel.getInnoVersion() != null)
                    VALUES("innoVersion","#{innoVersion}");
                if (resourceFileVersionInfomodel.getDeleted()!=null)
                    VALUES("deleted","#{deleted}");
                if (resourceFileVersionInfomodel.getStartTime()!=null)
                    VALUES("startTime","#{startTime}");
                if (resourceFileVersionInfomodel.getEndTime()!=null)
                    VALUES("endTime","#{endTime}");
            }}.toString();
        }

        public String updateResource(final ResourceFileVersionInfoModel resourceFileVersionInfomodel){
            return new SQL(){{
                UPDATE("resourceDB.resourceFileVersionInfo");
                if (resourceFileVersionInfomodel.getStartTime() != null)
                    SET("startTime = #{startTime}");
                if (resourceFileVersionInfomodel.getInnoVersion() != null)
                    SET("innoVersion = #{innoVersion}");
                WHERE(" id = #{id}");
            }}.toString();
        }

        public String insertKeyResource(final ResourceFileKeyInfoModel resourceFileKeyInfoModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.resourceFileKeyInfo");
                if (resourceFileKeyInfoModel.getResourceType() != null)
                    VALUES("resourceType","#{resourceType}");
                if (resourceFileKeyInfoModel.getSystemType() != null)
                    VALUES("systemType","#{systemType}");
                if (resourceFileKeyInfoModel.getInnoVersion() != null)
                    VALUES("innoVersion","#{innoVersion}");
                if (resourceFileKeyInfoModel.getResourceKey() != null)
                    VALUES("resourceKey","#{resourceKey}");
            }}.toString();
        }

        public String updateKeyResource(final ResourceFileKeyInfoModel resourceFileKeyInfoModel){
            return new SQL(){{
                UPDATE("resourceDB.resourceFileKeyInfo");
                if (resourceFileKeyInfoModel.getInnoVersion() != null)
                    SET("innoVersion = #{innoVersion}");
                if (resourceFileKeyInfoModel.getResourceKey() != null)
                    SET("resourceKey = #{resourceKey}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }

}
