package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.MarqueeAdvertModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/11/7.
 */
public interface MarqueeAdvertModelMapper {

    /**
     * 根据id删除
     */
    @Update("UPDATE resourceDB.marqueeAdvert set deleted = 1 where id = #{id}")
    public Integer deleteMarqueeAdvert(@Param("id") Integer id);

    /**
     * 添加
     */
    @InsertProvider(type = MarqueeAdvertProviderSql.class,method = "insertMarqueeAdvert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer insertMarqueeAdvert(MarqueeAdvertModel marqueeAdvertModel);
    /**
     * 修改
     */
    @UpdateProvider(type = MarqueeAdvertProviderSql.class,method = "updateMarqueeAdvert")
    public Integer updateMarqueeAdvert(MarqueeAdvertModel marqueeAdvertModel);


    class MarqueeAdvertProviderSql {
        public String insertMarqueeAdvert(final MarqueeAdvertModel marqueeAdvertModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.marqueeAdvert");
                if (!StringUtils.isEmpty(marqueeAdvertModel.getTitle()))
                    VALUES("title","#{title}");
                if(!StringUtils.isEmpty(marqueeAdvertModel.getContent()))
                    VALUES("content","#{content}");
                if (marqueeAdvertModel.getType() != null)
                    VALUES("type","#{type}");
                if (marqueeAdvertModel.getBeginDate()!=null)
                    VALUES("beginDate","#{beginDate}");
                if (marqueeAdvertModel.getEndDate()!=null)
                    VALUES("endDate","#{endDate}");
                if (marqueeAdvertModel.getCreateTime()!=null)
                    VALUES("createTime","#{createTime}");
                if (marqueeAdvertModel.getUpdateTime()!=null)
                    VALUES("updateTime","#{updateTime}");
                if (marqueeAdvertModel.getValid()!=null)
                    VALUES("valid","#{valid}");
                if (marqueeAdvertModel.getAppType()!=null)
                    VALUES("appType","#{appType}");
                if (marqueeAdvertModel.getRank()!=null)
                    VALUES("rank","#{rank}");
                if (marqueeAdvertModel.getDeleted()!=null)
                    VALUES("deleted","#{deleted}");
                if (marqueeAdvertModel.getVersion()!=null)
                    VALUES("version","#{version}");
                if (marqueeAdvertModel.getCreateBy()!=null)
                    VALUES("createBy","#{createBy}");
            }}.toString();
        }

        public String updateMarqueeAdvert(final MarqueeAdvertModel marqueeAdvertModel){
            return new SQL(){{
                UPDATE("resourceDB.marqueeAdvert");
                if (!StringUtils.isEmpty(marqueeAdvertModel.getTitle()))
                    SET("title = #{title}");
                if(!StringUtils.isEmpty(marqueeAdvertModel.getContent()))
                    SET("content = #{content}");
                if (marqueeAdvertModel.getValid() != null)
                    SET("valid = #{valid}");
                if (marqueeAdvertModel.getBeginDate()!=null)
                    SET("beginDate = #{beginDate}");
                if (marqueeAdvertModel.getEndDate()!=null)
                    SET("endDate = #{endDate}");
                if (marqueeAdvertModel.getCreateTime()!=null)
                    SET("createTime = #{createTime}");
                if (marqueeAdvertModel.getUpdateTime()!=null)
                    SET("updateTime = #{updateTime}");
                if (marqueeAdvertModel.getAppType()!=null)
                    SET("appType = #{appType}");
                if (marqueeAdvertModel.getType()!=null)
                    SET("type = #{type}");
                if (marqueeAdvertModel.getRank()!=null)
                    SET("rank = #{rank}");
                if (marqueeAdvertModel.getDeleted()!=null)
                    SET("deleted = #{deleted}");
                if (marqueeAdvertModel.getCreateBy()!=null)
                    SET("createBy = #{createBy}");
                if (marqueeAdvertModel.getVersion()!=null)
                    SET("version = #{version}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }


}
