package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.bean.HelpCenterBean;
import com.topjet.manage.domain.model.HelpCategoryModel;
import com.topjet.manage.domain.model.HelpQuestionModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/12/6.
 */
public interface HelpCategoryModelMapper {

    /**
     * 添加问题类型
     */
    @InsertProvider(type = QuestionInfoProviderSql.class,method = "insertQuestion")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="helpCategoryID", before=false, resultType=Integer.class)
    public Integer insertQuestion(HelpCenterBean helpCenterBean);
    /**
     * 修改问题类型
     */
    @UpdateProvider(type = QuestionInfoProviderSql.class,method = "updateQuestion")
    public Integer updateQuestion(HelpCenterBean helpCenterBean);
    /**
     * 附表添加问题
     */
    @InsertProvider(type = QuestionInfoProviderSql.class,method = "insertQuestionContent")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="helpQuestionID", before=false, resultType=Integer.class)
    public Integer insertQuestionContent(HelpCenterBean helpCenterBean);
    /**
     * 附表修改问题
     */
    @UpdateProvider(type = QuestionInfoProviderSql.class,method = "updateQuestionContent")
    public Integer updateQuestionContent(HelpCenterBean helpCenterBean);
    /**
     * 附表修改问题
     */
    @Update("update resourceDB.helpQuestion set sortNo = #{sortNo} where helpQuestionID = #{helpQuestionID}")
    public Integer update(@Param("sortNo") Integer sortNo,@Param("helpQuestionID") Integer helpQuestionID);
    /**
     * 删除问题附表
     */
    @Delete("delete from resourceDB.helpQuestion where helpQuestionID = #{helpQuestionID}")
    public Integer deleteQuestion(@Param("helpQuestionID") Integer helpQuestionID);

    class QuestionInfoProviderSql {
        public String insertQuestion(final HelpCenterBean helpCenterBean){
            return new SQL(){{
                INSERT_INTO("resourceDB.helpCategory");
                if (!StringUtils.isEmpty(helpCenterBean.getName()))
                    VALUES("name","#{name}");
                if (helpCenterBean.getVersion() != null)
                    VALUES("version","#{version}");
                if (helpCenterBean.getSortNo() != null)
                    VALUES("sortNo","#{sortNo}");
                if (helpCenterBean.getIsVisible() != null)
                    VALUES("isVisible","#{isVisible}");
                if (helpCenterBean.getIcon() != null)
                    VALUES("icon","#{icon}");
            }}.toString();
        }

        public String updateQuestion(final HelpCenterBean helpCenterBean){
            return new SQL(){{
                UPDATE("resourceDB.helpCategory");
                if (!StringUtils.isEmpty(helpCenterBean.getName()))
                    SET("name = #{name}");
                if (helpCenterBean.getVersion() != null)
                    SET("version = #{version}");
                if (helpCenterBean.getIsVisible() != null)
                    SET("isVisible = #{isVisible}");
                if (helpCenterBean.getIcon() != null)
                    SET("icon = #{icon}");
                WHERE(" helpCategoryID = #{helpCategoryID}");
            }}.toString();
        }

        //开始添加附表
        public String insertQuestionContent(final HelpCenterBean helpCenterBean){
            return new SQL(){{
                INSERT_INTO("resourceDB.helpQuestion");
                if (helpCenterBean.getHelpCategoryID() != null)
                    VALUES("helpCategoryID","#{helpCategoryID}");
                if (!StringUtils.isEmpty(helpCenterBean.getTitle()))
                    VALUES("title","#{title}");
                if (helpCenterBean.getContent() != null)
                    VALUES("content","#{content}");
                if (helpCenterBean.getCreateTime() != null)
                    VALUES("createTime","#{createTime}");
                if (helpCenterBean.getLastUpdateTime() != null)
                    VALUES("lastUpdateTime","#{lastUpdateTime}");
                if (helpCenterBean.getViewCount() != null)
                    VALUES("viewCount","#{viewCount}");
                if (helpCenterBean.getSortNo() != null)
                    VALUES("sortNo","#{sortNo}");
                if (helpCenterBean.getVersion() != null)
                    VALUES("version","#{version}");
            }}.toString();
        }

        public String updateQuestionContent(final HelpCenterBean helpCenterBean){
            return new SQL(){{
                UPDATE("resourceDB.helpQuestion");
                if (helpCenterBean.getHelpCategoryID() != null)
                    SET("helpCategoryID = #{helpCategoryID}");
                if (!StringUtils.isEmpty(helpCenterBean.getTitle()))
                    SET("title = #{title}");
                if (helpCenterBean.getContent() != null)
                    SET("content = #{content}");
                if (helpCenterBean.getSortNo() != null)
                    SET("sortNo = #{sortNo}");
                if (helpCenterBean.getCreateTime() != null)
                    SET("createTime = #{createTime}");
                if (helpCenterBean.getLastUpdateTime() != null)
                    SET("lastUpdateTime = #{lastUpdateTime}");
                if (helpCenterBean.getViewCount() != null)
                    SET("viewCount = #{viewCount}");
                if (helpCenterBean.getVersion() != null)
                    SET("version = #{version}");
                WHERE(" helpQuestionID = #{helpQuestionID}");
            }}.toString();
        }


    }


}
