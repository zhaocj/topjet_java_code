package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.HomeBottomProfileModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/12/1.
 */
public interface HomeBottomProfileModelEMapper {

    /**
     * 首页底部配置分页查询
     */
    @SelectProvider(type = HomeBottomProfileModelEMapper.SubscribeLineProviderSql.class,method = "getButtomProfileList")
    public List<HomeBottomProfileModel> getButtomProfileList(HomeBottomProfileModel homeBottomProfileModel);
    /**
     * 页数
     */
    @SelectProvider(type = HomeBottomProfileModelEMapper.SubscribeLineProviderSql.class,method = "getButtomProfileCount")
    public Integer getButtomProfileCount(HomeBottomProfileModel homeBottomProfileModel);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 页数
         *
         */
        public String getButtomProfileCount(final HomeBottomProfileModel homeBottomProfileModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT h.id) ");
            sb.append(" FROM ");
            sb.append(" resourceDB.homeBottomProfile h  ");
            sb.append("  WHERE h.deleted = 0 ");
            if (homeBottomProfileModel.getAppType() != null && homeBottomProfileModel.getAppType() > 0){
                sb.append(" AND h.appType = #{appType}");
            }
            return sb.toString();
        }

        /**
         * 首页底部配置分页查询
         */
        public String getButtomProfileList(final HomeBottomProfileModel homeBottomProfileModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" h.id,h.appType,h.text,h.textDownColor,h.textNormalColor,h.iconDownUrl,h.iconNormalUrl,h.orderNum,h.beginDate,h.endDate,h.deleted ");
            sb.append(" FROM ");
            sb.append(" resourceDB.homeBottomProfile h  ");
            sb.append("  WHERE h.deleted = 0 ");
            if (homeBottomProfileModel.getAppType() != null && homeBottomProfileModel.getAppType() > 0){
                sb.append(" AND h.appType = #{appType}");
            }
            sb.append(" GROUP BY h.id order by h.orderNum DESC  ");
            if(homeBottomProfileModel.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    /**
     * 根据id查询
     */
    @Select("select DISTINCT h.id,h.appType,h.text,h.textDownColor,h.textNormalColor,h.iconDownUrl,h.iconNormalUrl,h.orderNum,h.beginDate,h.endDate,h.deleted FROM resourceDB.homeBottomProfile h where h.deleted = 0 and h.id = #{id} group by h.id")
    public HomeBottomProfileModel findBottomProfileById(@Param("id") Integer id);


}
