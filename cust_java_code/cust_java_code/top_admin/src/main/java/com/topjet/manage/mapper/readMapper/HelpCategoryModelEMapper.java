package com.topjet.manage.mapper.readMapper;


import com.topjet.manage.domain.bean.HelpCenterBean;
import com.topjet.manage.domain.model.HelpCategoryModel;
import com.topjet.manage.domain.model.HelpQuestionModel;
import org.apache.axis.utils.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/12/6.
 */
public interface HelpCategoryModelEMapper {
    /**
     * 查询所有分类问题
     */
    @SelectProvider(type = HelpCategoryModelEMapper.SubscribeLineProviderSql.class,method = "getHelpCategoryList")
    public List<HelpCenterBean> getHelpCategoryList(HelpCenterBean helpCategoryModel);
   /* *
     * 所有分类问题的页数
     */
    @SelectProvider(type = HelpCategoryModelEMapper.SubscribeLineProviderSql.class,method = "getHelpCategoryCount")
    public Integer getHelpCategoryCount(HelpCenterBean helpCenterBean);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 所有分类问题的页数
         *
         */
        public String getHelpCategoryCount(final HelpCenterBean helpCenterBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT hq.helpQuestionID) ");
            sb.append(" FROM ");
            sb.append(" resourceDB.helpQuestion hq ");
            sb.append("  LEFT JOIN ");
            sb.append("  resourceDB.helpCategory hc  on hc.helpCategoryID = hq.helpCategoryID  ");
            sb.append("  WHERE hc.isVisible = 1 ");
            if (helpCenterBean.getVersion() != null && helpCenterBean.getVersion() > 0){
                sb.append(" AND hc.version = #{version}");
            }
            if (!StringUtils.isEmpty(helpCenterBean.getName())){
                sb.append(" AND hc.name = #{name}");
            }
            if (helpCenterBean.getStartDate() != null){
                sb.append(" and DATE_FORMAT(hq.createTime,'%Y/%m/%d') >= DATE_FORMAT(#{startDate},'%Y/%m/%d')");
            }
            if (helpCenterBean.getEndDate() != null){
                sb.append(" and DATE_FORMAT(hq.createTime,'%Y/%m/%d') <= DATE_FORMAT(#{endDate},'%Y/%m/%d')");
            }

            return sb.toString();
        }

     /*   *
         *所有分类问题分页查询
         */
        public String getHelpCategoryList(final HelpCenterBean helpCenterBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" hc.helpCategoryID,hc.version,hc.name,hc.sortNo as hcSortNo,hc.icon,hc.isVisible,hq.helpQuestionID,");
            sb.append(" hq.content,hq.title,hq.createTime,hq.lastUpdateTime,hq.viewCount,hq.sortNo as hpSortNo,hq.version as versionJie");
            sb.append(" FROM ");
            sb.append(" resourceDB.helpQuestion hq ");
            sb.append("  LEFT JOIN ");
            sb.append("  resourceDB.helpCategory hc  on hc.helpCategoryID = hq.helpCategoryID  ");
            sb.append("  WHERE hc.isVisible = 1 ");
            if (helpCenterBean.getVersion() != null && helpCenterBean.getVersion() > 0){
                sb.append(" AND hc.version = #{version}");
            }
            if (!StringUtils.isEmpty(helpCenterBean.getName())){
                sb.append(" AND hc.name = #{name}");
            }
            if (helpCenterBean.getStartDate() != null){
                sb.append(" and DATE_FORMAT(hq.createTime,'%Y/%m/%d') >= DATE_FORMAT(#{startDate},'%Y/%m/%d')");
            }
            if (helpCenterBean.getEndDate() != null){
                sb.append(" and DATE_FORMAT(hq.createTime,'%Y/%m/%d') <= DATE_FORMAT(#{endDate},'%Y/%m/%d')");
            }
            sb.append(" GROUP BY hq.helpQuestionID ORDER BY hq.helpQuestionID DESC  ");
            if(helpCenterBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    /**
     * 根据主表id查询主表信息
     */
    @Select("select DISTINCT hc.helpCategoryID,hc.version,hc.name,hc.sortNo,hc.icon,hc.isVisible from resourceDB.helpCategory hc where hc.helpCategoryID = #{helpCategoryID} and hc.isVisible = 1 group by hc.helpCategoryID")
    public HelpCategoryModel findCategoryByhelpCategoryID(@Param("helpCategoryID") Integer helpCategoryID);
    /**
     * 根据附表id查询附表信息
     */
    @Select("select DISTINCT hq.helpQuestionID,hq.helpCategoryID, hq.content,hq.title,hq.createTime,hq.lastUpdateTime,hq.viewCount,hq.sortNo,hq.version from resourceDB.helpQuestion hq where hq.helpQuestionID = #{helpQuestionID} group by hq.helpQuestionID")
    public HelpQuestionModel findQuestionByhelpQuestionID(@Param("helpQuestionID") Integer helpQuestionID);
    /**
     * 根据版本类型，问题类型查询数据
     */
    @Select("select DISTINCT hc.helpCategoryID,hc.version,hc.name,hc.sortNo,hc.icon,hc.isVisible from resourceDB.helpCategory hc where hc.name = #{name} and hc.version = #{version} group by hc.helpCategoryID")
    public HelpCategoryModel findByNameAndVersion(@Param("name") String name,@Param("version") Integer version);





}
