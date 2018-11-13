package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.MarqueeAdvertModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by liyj on 2017/11/7.
 */
public interface MarqueeAdvertModelEMapper {

    /**
     * 跑马灯广告查询分页
     */
    @SelectProvider(type = MarqueeAdvertModelEMapper.SubscribeLineProviderSql.class,method = "getMarqueeAdvertList")
    public List<MarqueeAdvertModel> getMarqueeAdvertList(MarqueeAdvertModel marqueeAdvertModel);
    /**
     * 跑马灯广告页数
     */
    @SelectProvider(type = MarqueeAdvertModelEMapper.SubscribeLineProviderSql.class,method = "getMarqueeAdvertCount")
    public Integer getMarqueeAdvertCount(MarqueeAdvertModel marqueeAdvertModel);



    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 跑马灯广告管理页数
         *
         */
        public String getMarqueeAdvertCount(final MarqueeAdvertModel marqueeAdvertModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT m.id) ");
            sb.append(" FROM ");
            sb.append(" resourceDB.marqueeAdvert m ");
            sb.append("  WHERE m.deleted = 0");
            if (marqueeAdvertModel.getAppType() != null && marqueeAdvertModel.getAppType() > 0){
                sb.append(" AND m.appType = #{appType}");
            }
            if (marqueeAdvertModel.getType() != null && marqueeAdvertModel.getType() > 0){
                sb.append(" AND m.type = #{type}");
            }
            if (marqueeAdvertModel.getRank() != null && marqueeAdvertModel.getRank() > 0){
                sb.append(" AND m.rank = #{rank}");
            }
            if (marqueeAdvertModel.getId() != null && marqueeAdvertModel.getId() > 0){
                sb.append(" AND m.id = #{id}");
            }

            return sb.toString();
        }

        /**
         * 跑马灯广告管理分页查询
         */
        public String getMarqueeAdvertList(final MarqueeAdvertModel marqueeAdvertModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" m.*");
            sb.append(" FROM ");
            sb.append(" resourceDB.marqueeAdvert m ");
            sb.append("  WHERE m.deleted = 0");
            if (marqueeAdvertModel.getAppType() != null && marqueeAdvertModel.getAppType() > 0){
                sb.append(" AND m.appType = #{appType}");
            }
            if (marqueeAdvertModel.getType() != null && marqueeAdvertModel.getType() > 0){
                sb.append(" AND m.type = #{type}");
            }
            if (marqueeAdvertModel.getRank() != null && marqueeAdvertModel.getRank() > 0){
                sb.append(" AND m.rank = #{rank}");
            }
            if (marqueeAdvertModel.getId() != null && marqueeAdvertModel.getId() > 0){
                sb.append(" AND m.id = #{id}");
            }
            sb.append(" GROUP BY m.id order by m.rank DESC  ");
            if(marqueeAdvertModel.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }
    /**
     * 根据id查询
     */
    @Select("SELECT * from resourceDB.marqueeAdvert m where m.id = #{id}")
    public MarqueeAdvertModel getMarqueeAdvertById(@Param("id") Integer id);



}
