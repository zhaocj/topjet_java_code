package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.bean.SensitiveWordInfoBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/12/1.
 */
public interface SensitiveWordInfoModelMapper {

    //添加敏感字
    @InsertProvider(type = SensitiveWordProviderSql.class,method = "addSensitiveWord")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer addSensitiveWord(SensitiveWordInfoBean sensitiveWordInfoBean);
    //修改敏感字
    @UpdateProvider(type = SensitiveWordProviderSql.class,method = "updateSensiticeWord")
    public Integer updateSensiticeWord(SensitiveWordInfoBean sensitiveWordInfoBean);


    class SensitiveWordProviderSql {
        public String addSensitiveWord(final SensitiveWordInfoBean sensitiveWordInfoBean){
            return new SQL(){{
                INSERT_INTO("resourceDB.sensitiveWordInfo");
                if (!StringUtils.isEmpty(sensitiveWordInfoBean.getName()))
                    VALUES("name","#{name}");
                if (sensitiveWordInfoBean.getCount()!=null)
                    VALUES("count","#{count}");
                if (sensitiveWordInfoBean.getCreateTime()!=null)
                    VALUES("createTime","#{createTime}");
                if (sensitiveWordInfoBean.getUpdateTime()!=null)
                    VALUES("updateTime","#{updateTime}");
                if (sensitiveWordInfoBean.getDeleted()!=null)
                    VALUES("deleted","#{deleted}");
                if (sensitiveWordInfoBean.getVersion()!=null)
                    VALUES("version","#{version}");
                if (sensitiveWordInfoBean.getUpdateBy()!=null)
                    VALUES("updateBy","#{updateBy}");
                if (sensitiveWordInfoBean.getCreateBy()!=null)
                    VALUES("createBy","#{createBy}");
            }}.toString();
        }

        public String updateSensiticeWord(final SensitiveWordInfoBean sensitiveWordInfoBean){
            return new SQL(){{
                UPDATE("resourceDB.sensitiveWordInfo");
                if (!StringUtils.isEmpty(sensitiveWordInfoBean.getName()))
                    SET("name = #{name}");
                if (sensitiveWordInfoBean.getCount()!=null)
                    SET("count = #{count}");
                if (sensitiveWordInfoBean.getCreateTime()!=null)
                    SET("createTime = #{createTime}");
                if (sensitiveWordInfoBean.getUpdateTime()!=null)
                    SET("updateTime = #{updateTime}");
                if (sensitiveWordInfoBean.getDeleted()!=null)
                    SET("deleted = #{deleted}");
                if (sensitiveWordInfoBean.getCreateBy()!=null)
                    SET("createBy = #{createBy}");
                if (sensitiveWordInfoBean.getUpdateBy()!=null)
                    SET("updateBy = #{updateBy}");
                if (sensitiveWordInfoBean.getVersion()!=null)
                    SET("version = #{version}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }



}
