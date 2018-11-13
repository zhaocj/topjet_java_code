package com.topjet.system.mapper;

import com.topjet.manage.mapper.writeMapper.BaseMapper;
import com.topjet.system.domain.model.AppUpgradeHistoryModel;
import com.topjet.system.domain.model.AppUpgradeInfoModel;
import org.apache.axis.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AppUpgradeInfoModelMapper extends BaseMapper<AppUpgradeInfoModel>{

    /** limit #{offset} , #{limit}
     *查询版本列表
     */
    @SelectProvider(type = AppUpgradeInfoModelMapper.SubscribeLineProviderSql.class,method = "getAppUpGradeList")
    public List<AppUpgradeInfoModel> getAppUpGradeList(AppUpgradeInfoModel appUpgradeInfoModel);
    /**
     * 版本页数
     */
    @SelectProvider(type = AppUpgradeInfoModelMapper.SubscribeLineProviderSql.class,method = "getCount")
    public Integer getCount(AppUpgradeInfoModel appUpgradeInfoModel);
    /**
     * 根据id查询版本
     */
    @Select("SELECT DISTINCT id,systemVersion,downloadAddress,isForced,type,description,createTime,updateTime,deleted,isNotified,innoVersion  FROM appUpgradeInfo WHERE deleted = 0 and id = #{id}")
    public AppUpgradeInfoModel findAppById(@Param("id") Integer id);

    /**
     * 版本历史
     */
    @SelectProvider(type = AppUpgradeInfoModelMapper.SubscribeLineProviderSql.class,method = "getAppHistoryList")
    public List<AppUpgradeHistoryModel>  getAppHistoryList(AppUpgradeHistoryModel appUpgradeHistoryModel);
    /**
     * 版本历史页数
     */
    @SelectProvider(type = AppUpgradeInfoModelMapper.SubscribeLineProviderSql.class,method = "getHistoryCount")
    public Integer getHistoryCount(AppUpgradeHistoryModel appUpgradeHistoryModel);

    /**
     * 根据类型查询版本
     */
    @Select("select distinct id,systemVersion,downloadAddress,isForced,type,description,createTime,updateTime,deleted,isNotified,innoVersion \n" +
            "from appUpgradeInfo\n" +
            "WHERE deleted = 0 and  type = #{type}\n" +
            "GROUP BY id\n" +
            "ORDER BY createTime DESC")
    public List<AppUpgradeInfoModel> findAppByType(@Param("type") String type);
    /**
     * 推送
     */
    public int send(AppUpgradeInfoModel appUpgradeInfoModel);


    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 版本管理页数
         *
         */
        public String getCount(final AppUpgradeInfoModel appUpgradeInfoModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT id) ");
            sb.append(" FROM ");
            sb.append(" appUpgradeInfo ");
            sb.append(" WHERE deleted = 0 ");
            if (appUpgradeInfoModel.getId() != null && appUpgradeInfoModel.getId() > 0){
                sb.append(" AND id = #{id}");
            }
            return sb.toString();
        }

        /**
         * 版本管理分页查询
         */
        public String getAppUpGradeList(final AppUpgradeInfoModel appUpgradeInfoModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" id,systemVersion,downloadAddress,isForced,type,description,createTime,");
            sb.append(" updateTime,deleted,isNotified,innoVersion");
            sb.append(" FROM ");
            sb.append(" appUpgradeInfo ");
            sb.append(" WHERE deleted = 0 ");
            if (appUpgradeInfoModel.getId() != null && appUpgradeInfoModel.getId() > 0){
                sb.append(" AND id = #{id}");
            }
            sb.append(" GROUP BY id ORDER BY createTime DESC  ");
            if(appUpgradeInfoModel.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }


        /**
         * 版本历史管理页数
         *
         */
        public String getHistoryCount(final AppUpgradeHistoryModel appUpgradeHistoryModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT id) ");
            sb.append(" FROM ");
            sb.append(" appUpgradeHistory ");
            sb.append(" WHERE deleted = 0 ");
            if (!StringUtils.isEmpty(appUpgradeHistoryModel.getType())){
                sb.append(" AND type = #{type}");
            }
            return sb.toString();
        }

        /**
         * 版本历史管理分页查询
         */
        public String getAppHistoryList(final AppUpgradeHistoryModel appUpgradeHistoryModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" id,systemVersion,downloadAddress,isForced,type,description,createTime,deleted,isNotified,innoVersion");
            sb.append(" FROM ");
            sb.append(" appUpgradeHistory ");
            sb.append(" WHERE deleted = 0 ");
            if (!StringUtils.isEmpty(appUpgradeHistoryModel.getType())){
                sb.append(" AND type = #{type}");
            }
            sb.append(" GROUP BY id ORDER BY createTime DESC  ");
            if(appUpgradeHistoryModel.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }


    }


}