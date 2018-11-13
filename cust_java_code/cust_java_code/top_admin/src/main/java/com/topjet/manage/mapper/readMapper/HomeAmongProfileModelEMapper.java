package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.HomeAmongProfileModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/12/4.
 */
public interface HomeAmongProfileModelEMapper {
    /**
     * 首页中间配置分页查询
     */
    @SelectProvider(type = HomeAmongProfileModelEMapper.SubscribeLineProviderSql.class,method = "getCenterProfileList")
    public List<HomeAmongProfileModel> getCenterProfileList(HomeAmongProfileModel homeAmongProfileModel);
    /**
     * 首页中间配置页数
     */
    @SelectProvider(type = HomeAmongProfileModelEMapper.SubscribeLineProviderSql.class,method = "getCenterProfileCount")
    public Integer getCenterProfileCount(HomeAmongProfileModel homeAmongProfileModel);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 中间页数
         *
         */
        public String getCenterProfileCount(final HomeAmongProfileModel homeAmongProfileModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT h.id) ");
            sb.append(" FROM ");
            sb.append(" resourceDB.homeAmongProfile h  ");
            sb.append("  WHERE h.deleted = 0 ");
            if (homeAmongProfileModel.getAppType() != null && homeAmongProfileModel.getAppType() > 0){
                sb.append(" AND h.appType = #{appType}");
            }
            return sb.toString();
        }

        /**
         * 首页中间配置分页查询
         */
        public String getCenterProfileList(final HomeAmongProfileModel homeAmongProfileModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" h.id,h.appType,h.title,h.content,h.iconUrl,h.link,h.orderNum,h.beginDate,h.deleted ");
            sb.append(" FROM ");
            sb.append(" resourceDB.homeAmongProfile h  ");
            sb.append("  WHERE h.deleted = 0 ");
            if (homeAmongProfileModel.getAppType() != null && homeAmongProfileModel.getAppType() > 0){
                sb.append(" AND h.appType = #{appType}");
            }
            sb.append(" GROUP BY h.id order by h.orderNum DESC  ");
            if(homeAmongProfileModel.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    /**
     * 根据id查询中间配置
     */
    @Select("SELECT DISTINCT h.id,h.appType,h.title,h.content,h.iconUrl,h.link,h.orderNum,h.beginDate,h.endDate,h.deleted FROM resourceDB.homeAmongProfile h WHERE h.deleted = 0 and h.id = #{id} group by h.id")
    public HomeAmongProfileModel findCenterProfileById(@Param("id") Integer id);


}
