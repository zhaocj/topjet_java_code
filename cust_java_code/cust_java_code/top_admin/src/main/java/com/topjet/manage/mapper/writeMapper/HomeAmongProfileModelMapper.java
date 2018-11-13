package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.HomeAmongProfileModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/12/4.
 */
public interface HomeAmongProfileModelMapper {
    /**
     * 添加中间配置
     */
    @InsertProvider(type = CenterProfileProviderSql.class,method = "insertCenterProfile")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer insertCenterProfile(HomeAmongProfileModel homeAmongProfileModel);
    /**
     * 修改中间配置
     */
    @UpdateProvider(type = CenterProfileProviderSql.class,method = "updateCenterProfile")
    public Integer updateCenterProfile(HomeAmongProfileModel homeAmongProfileModel);

    class CenterProfileProviderSql {
        public String insertCenterProfile(final HomeAmongProfileModel homeAmongProfileModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.homeAmongProfile");
                if (!StringUtils.isEmpty(homeAmongProfileModel.getTitle()))
                    VALUES("title","#{title}");
                if (!StringUtils.isEmpty(homeAmongProfileModel.getContent()))
                    VALUES("content","#{content}");
                if (!StringUtils.isEmpty(homeAmongProfileModel.getLink()))
                    VALUES("link","#{link}");
                if(!StringUtils.isEmpty(homeAmongProfileModel.getIconUrl()))
                    VALUES("iconUrl","#{iconUrl}");
                if(StringUtils.isEmpty(homeAmongProfileModel.getIconDownUrl()))
                    VALUES("iconDownUrl","#{iconDownUrl}");
                if (homeAmongProfileModel.getOrderNum() != null)
                    VALUES("orderNum","#{orderNum}");
                if (homeAmongProfileModel.getAppType() != null)
                    VALUES("appType","#{appType}");
                if (homeAmongProfileModel.getBeginDate() != null)
                    VALUES("beginDate","#{beginDate}");
                if (homeAmongProfileModel.getEndDate() != null)
                    VALUES("endDate","#{endDate}");
                if (homeAmongProfileModel.getDeleted()!=null)
                    VALUES("deleted","#{deleted}");
            }}.toString();
        }

        public String updateCenterProfile(final HomeAmongProfileModel homeAmongProfileModel){
            return new SQL(){{
                UPDATE("resourceDB.homeAmongProfile");
                if (!StringUtils.isEmpty(homeAmongProfileModel.getTitle()))
                    SET("title = #{title}");
                if (!StringUtils.isEmpty(homeAmongProfileModel.getContent()))
                    SET("content = #{content}");
                if (!StringUtils.isEmpty(homeAmongProfileModel.getLink()))
                    SET("link = #{link}");
                if(!StringUtils.isEmpty(homeAmongProfileModel.getIconUrl()))
                    SET("iconUrl = #{iconUrl}");
                if(!StringUtils.isEmpty(homeAmongProfileModel.getIconDownUrl()))
                    SET("iconDownUrl = #{iconDownUrl}");
                if (homeAmongProfileModel.getOrderNum() != null)
                    SET("orderNum = #{orderNum}");
                if (homeAmongProfileModel.getAppType() != null)
                    SET("appType = #{appType}");
                if (homeAmongProfileModel.getBeginDate() != null)
                    SET("beginDate = #{beginDate}");
                if (homeAmongProfileModel.getBeginDate() != null)
                    SET("endDate = #{endDate}");
                if (homeAmongProfileModel.getDeleted()!=null)
                    SET("deleted = #{deleted}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }

}
