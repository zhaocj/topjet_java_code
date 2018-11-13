package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.HomeBottomProfileModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/12/1.
 */
public interface HomeBottomProfileModelMapper {

    /**
     * 添加
     */
    @InsertProvider(type = BottomProfileProviderSql.class,method = "insertBottomProfile")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer insertBottomProfile(HomeBottomProfileModel homeBottomProfileModel);
    /**
     * 修改
     */
    @UpdateProvider(type = BottomProfileProviderSql.class,method = "updateBottomProfile")
    public Integer updateBottomProfile(HomeBottomProfileModel homeBottomProfileModel);


    class BottomProfileProviderSql {
        public String insertBottomProfile(final HomeBottomProfileModel homeBottomProfileModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.homeBottomProfile");
                if (!StringUtils.isEmpty(homeBottomProfileModel.getTextNormalColor()))
                    VALUES("textNormalColor","#{textNormalColor}");
                if (!StringUtils.isEmpty(homeBottomProfileModel.getTextDownColor()))
                    VALUES("textDownColor","#{textDownColor}");
                if (!StringUtils.isEmpty(homeBottomProfileModel.getText()))
                    VALUES("text","#{text}");
                if(!StringUtils.isEmpty(homeBottomProfileModel.getIconNormalUrl()))
                    VALUES("iconNormalUrl","#{iconNormalUrl}");
                if(!StringUtils.isEmpty(homeBottomProfileModel.getIconDownUrl()))
                    VALUES("iconDownUrl","#{iconDownUrl}");
                if (homeBottomProfileModel.getOrderNum() != null)
                    VALUES("orderNum","#{orderNum}");
                if (homeBottomProfileModel.getAppType() != null)
                    VALUES("appType","#{appType}");
                if (homeBottomProfileModel.getDeleted()!=null)
                    VALUES("deleted","#{deleted}");
                if (homeBottomProfileModel.getBeginDate()!=null)
                    VALUES("beginDate","#{beginDate}");
                if (homeBottomProfileModel.getEndDate()!=null)
                    VALUES("endDate","#{endDate}");
            }}.toString();
        }

        public String updateBottomProfile(final HomeBottomProfileModel homeBottomProfileModel){
            return new SQL(){{
                UPDATE("resourceDB.homeBottomProfile");
                if (!StringUtils.isEmpty(homeBottomProfileModel.getTextNormalColor()))
                    SET("textNormalColor = #{textNormalColor}");
                if (!StringUtils.isEmpty(homeBottomProfileModel.getTextDownColor()))
                    SET("textDownColor = #{textDownColor}");
                if (!StringUtils.isEmpty(homeBottomProfileModel.getText()))
                    SET("text = #{text}");
                if(!StringUtils.isEmpty(homeBottomProfileModel.getIconNormalUrl()))
                    SET("iconNormalUrl = #{iconNormalUrl}");
                if(!StringUtils.isEmpty(homeBottomProfileModel.getIconDownUrl()))
                    SET("iconDownUrl = #{iconDownUrl}");
                if (homeBottomProfileModel.getOrderNum() != null)
                    SET("orderNum = #{orderNum}");
                if (homeBottomProfileModel.getAppType() != null)
                    SET("appType = #{appType}");
                if (homeBottomProfileModel.getDeleted()!=null)
                    SET("deleted = #{deleted}");
                if (homeBottomProfileModel.getBeginDate()!=null)
                    SET("beginDate = #{beginDate}");
                if (homeBottomProfileModel.getEndDate()!=null)
                    SET("endDate = #{endDate}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }



}


