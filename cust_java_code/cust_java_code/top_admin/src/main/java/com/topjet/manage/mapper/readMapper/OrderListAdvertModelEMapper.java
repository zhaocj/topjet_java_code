package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.OrderListAdvertBean;
import com.topjet.manage.domain.model.OrderListAdvertModel;
import com.topjet.manage.mapper.writeMapper.BaseMapper;
import org.apache.bcel.generic.INEG;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/10/23.
 */
public interface OrderListAdvertModelEMapper extends BaseMapper<OrderListAdvertBean> {

    /**
     *货源列表广告查询
     */
    @SelectProvider(type = OrderListAdvertModelEMapper.SubscribeLineProviderSql.class,method = "getAdvertList")
    public List<OrderListAdvertBean> getAdvertList(OrderListAdvertBean orderListAdvertBean);
    /**
     * 货源列表广告页数
     */
    @SelectProvider(type = OrderListAdvertModelEMapper.SubscribeLineProviderSql.class,method = "getAdvertCount")
    public Integer getAdvertCount(OrderListAdvertBean orderListAdvertBean);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 货源列表广告页数
         *
         */
        public String getAdvertCount(final OrderListAdvertBean orderListAdvertBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT ad.id) ");
            sb.append(" FROM ");
            sb.append(" resourceDB.orderListAdvert ad");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser sy on ad.createBy = sy.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser su on ad.updateBy = su.id ");
            sb.append("  WHERE ad.deleted = 0 ");
            if (orderListAdvertBean.getAppType() != null && orderListAdvertBean.getAppType() > 0){
                sb.append(" AND ad.appType = #{appType}");
            }
            if (orderListAdvertBean.getValid() != null && orderListAdvertBean.getValid() > 0){
                sb.append(" AND ad.valid = #{valid}");
            }
            return sb.toString();
        }

        /**
         * 货源列表广告查询
         */
        public String getAdvertList(final OrderListAdvertBean orderListAdvertBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" ad.id, ad.webTitle, ad.text, ad.indexNumber, ad.reorder, ad.pictureUrl,ad.pictureKey, ad.turnURL, ad.valid, ad.beginDate,");
            sb.append(" ad.endDate, ad.createTime, ad.updateTime, ad.appType,ad.deleted, ad.version, ad.createBy, ad.updateBy,");
            sb.append(" sy.nickName as createName ,su.nickName as updateName");
            sb.append(" FROM ");
            sb.append(" resourceDB.orderListAdvert ad");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser sy on ad.createBy = sy.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser su on ad.updateBy = su.id ");
            sb.append("  WHERE ad.deleted = 0 ");
            if (orderListAdvertBean.getAppType() != null && orderListAdvertBean.getAppType() > 0){
                sb.append(" AND ad.appType = #{appType}");
            }
            if (orderListAdvertBean.getValid() != null && orderListAdvertBean.getValid() > 0){
                sb.append(" AND ad.valid = #{valid}");
            }
            sb.append(" GROUP BY ad.id  ORDER BY ad.id  DESC  ");
            if(orderListAdvertBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    /**
     * 根据appType valid = 1 查询数据
     */
    @Select("SELECT * from resourceDB.orderListAdvert o where o.appType = #{appType} and o.valid = 1")
    public List<OrderListAdvertModel> findAdvertByAppType(@Param("appType") Integer appType);

    /**
     * 根据id查询
     */
    @Select("SELECT * from resourceDB.orderListAdvert o where o.id = #{id} and o.deleted = 0")
    public OrderListAdvertModel findOrderListAdvertById(@Param("id") Integer id);

}
