package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.CollocationInfoBean;
import com.topjet.manage.domain.model.CollocationInfoModel;
import com.topjet.manage.mapper.writeMapper.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/9/20.
 */
public interface CollocationInfoModelEMapper extends BaseMapper<CollocationInfoModel> {
    /**
     * 分页货源配置列表
     */
    @SelectProvider(type = CollocationInfoModelEMapper.SubscribeLineProviderSql.class,method = "getCollocationList")
    public List<CollocationInfoBean> getCollocationList(CollocationInfoBean collocationInfoBean);

    /**
     * 货源配置页数
     */
    @SelectProvider(type = CollocationInfoModelEMapper.SubscribeLineProviderSql.class,method = "getCollocationCount")
    public Integer getCollocationCount(CollocationInfoBean collocationInfoBean);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 货源配置的总数
         *
         */
        public String getCollocationCount(final CollocationInfoBean collocationInfoBean){
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT COUNT(DISTINCT c.id) from resourceDB.collocationInfo c where c.deleted = 0");
            if (collocationInfoBean.getId() != null && collocationInfoBean.getId() > 0){
                sb.append(" AND c.id = #{id}");
            }
            if (collocationInfoBean.getCollocationType() != null && collocationInfoBean.getCollocationType() > 0){
                sb.append(" AND c.collocationType = #{collocationType}");
            }

            return sb.toString();
        }

        /**
         * 查询货源配置分页
         */
        public String getCollocationList(final CollocationInfoBean collocationInfoBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" c.id,c.collocationName,c.collocationType,c.categoryId,c.createTime,c.updateTime,c.deleted");
            sb.append(" FROM ");
            sb.append(" resourceDB.collocationInfo c where c.deleted = 0");
            if (collocationInfoBean.getId() != null && collocationInfoBean.getId() > 0){
                sb.append(" AND c.id = #{id}");
            }
            if (collocationInfoBean.getCollocationType() != null && collocationInfoBean.getCollocationType() > 0){
                sb.append(" AND c.collocationType = #{collocationType}");
            }
            sb.append(" GROUP BY c.id ORDER BY c.id DESC  ");
            if(collocationInfoBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    @Select(value = "SELECT id,collocationName,collocationType,categoryId FROM resourceDB.collocationInfo u WHERE u.deleted=0 AND u.collocationType=#{type} ")
    List<CollocationInfoModel> getCollocation(@Param("type") Integer type);

    //根据类型type = 5 deleted= 0 查询匹配中心
    @Select("select * from resourceDB.collocationInfo c where c.collocationType = 5 and c.deleted = 0")
    public List<CollocationInfoModel>  getCollectionByType();

    //根据id查询
    @Select("select * from resourceDB.collocationInfo c where c.id = #{id} and c.deleted = 0")
    public List<CollocationInfoBean> getCollectionById(@Param("id") Integer id);

    //保存redis
    void save(CollocationInfoModel collocationInfoModel);
    //获取redis
    CollocationInfoModel get(String mobile);



}
