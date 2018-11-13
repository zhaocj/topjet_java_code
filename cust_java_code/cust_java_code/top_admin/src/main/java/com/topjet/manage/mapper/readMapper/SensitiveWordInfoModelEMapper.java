package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.SensitiveWordInfoBean;
import com.topjet.manage.domain.model.SensitiveWordInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/12/1.
 */
public interface SensitiveWordInfoModelEMapper {

    //显示敏感字列表
    @SelectProvider(type = SensitiveWordInfoModelEMapper.SubscribeLineProviderSql.class,method = "getSensitiveWordList")
    public List<SensitiveWordInfoBean> getSensitiveWordList(SensitiveWordInfoBean sensitiveWordInfoBean);
    //敏感字页数
    @SelectProvider(type = SensitiveWordInfoModelEMapper.SubscribeLineProviderSql.class,method = "getSensitiveWordCount")
    public Integer getSensitiveWordCount(SensitiveWordInfoBean sensitiveWordInfoBean);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 敏感字页数
         *
         */
        public String getSensitiveWordCount(final SensitiveWordInfoBean sensitiveWordInfoBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT s.id) ");
            sb.append(" FROM ");
            sb.append(" resourceDB.sensitiveWordInfo s  ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser sr on s.createBy = sr.id ");
            sb.append("  WHERE s.deleted = 0 ");
            if (!StringUtils.isEmpty(sensitiveWordInfoBean.getName())){
                sb.append(" AND s.name = #{name}");
            }

            return sb.toString();
        }

        /**
         * 显示敏感字列表
         */
        public String getSensitiveWordList(final SensitiveWordInfoBean sensitiveWordInfoBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" s.id,s.name,s.count,s.createTime, sr.nickName as createName ");
            sb.append(" FROM ");
            sb.append(" resourceDB.sensitiveWordInfo s  ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser sr on s.createBy = sr.id ");
            sb.append("  WHERE s.deleted = 0 ");
            if (!StringUtils.isEmpty(sensitiveWordInfoBean.getName())){
                sb.append(" AND s.name = #{name}");
            }
            sb.append(" GROUP BY s.id order by s.id DESC  ");
            if(sensitiveWordInfoBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }


    //根据敏感字id查询
    @Select("select s.id, s.name,s.count,s.createTime,sr.nickName as createName from resourceDB.sensitiveWordInfo s LEFT JOIN manageDB.sysUser sr on s.createBy = sr.id  where s.deleted = 0 and s.id = #{id} GROUP BY s.id")
    public SensitiveWordInfoBean getSensitiveWordById(@Param("id") Integer id);
    //根据敏感字查询
    public List<SensitiveWordInfoBean> getSenWordByName(String name);

}
