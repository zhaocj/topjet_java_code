package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.bean.CollocationInfoBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/9/20.
 */
public interface CollocationInfoModelMapper {
    /**
     * 新增货源配置
     */
    @InsertProvider(type = CollocationInfoProviderSql.class,method = "createCollocationInfo")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer createCollocationInfo(CollocationInfoBean collocationInfoBean);
    /**
     * 修改货源配置
     */
    @UpdateProvider(type = CollocationInfoProviderSql.class,method = "updateCollocationInfo")
    public Integer updateCollocationInfo(CollocationInfoBean collocationInfoBean);
    /**
     * 删除货源配置
     */
    @UpdateProvider(type = CollocationInfoProviderSql.class,method = "deleteCollocationInfo")
    public Integer deleteCollocationInfo(CollocationInfoBean collocationInfoBean);

    class CollocationInfoProviderSql {
        public String createCollocationInfo(final CollocationInfoBean collocationInfoBean){
            return new SQL(){{
                INSERT_INTO("resourceDB.collocationInfo");
                if (!StringUtils.isEmpty(collocationInfoBean.getCollocationName()))
                    VALUES("collocationName","#{collocationName}");
                if (collocationInfoBean.getCollocationType() != null)
                    VALUES("collocationType","#{collocationType}");
                if (collocationInfoBean.getCategoryId() != null)
                    VALUES("categoryId","#{categoryId}");
                if (collocationInfoBean.getCreateTime() != null)
                    VALUES("createTime","#{createTime}");
            }}.toString();
        }

        public String updateCollocationInfo(final CollocationInfoBean collocationInfoBean){
            return new SQL(){{
                UPDATE("resourceDB.collocationInfo");
                if (!StringUtils.isEmpty(collocationInfoBean.getCollocationName()))
                    SET("collocationName = #{collocationName}");
                if (collocationInfoBean.getUpdateTime() != null)
                    SET("updateTime = #{updateTime}");
                WHERE(" id = #{id}");
            }}.toString();
        }

        public String deleteCollocationInfo(final CollocationInfoBean collocationInfoBean){
            return new SQL(){{
                UPDATE("resourceDB.collocationInfo");
                    SET("deleted = 1");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }

}
