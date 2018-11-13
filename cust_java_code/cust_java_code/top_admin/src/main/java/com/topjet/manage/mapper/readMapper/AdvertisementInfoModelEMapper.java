package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.AdvertisementBean;
import com.topjet.manage.domain.model.AdvertisementInfoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/10/12.
 */
public interface AdvertisementInfoModelEMapper {

    /**
     * 广告管理分页查询
     * @param adc
     * @return
     */
    @SelectProvider(type = AdvertisementInfoModelEMapper.SubscribeLineProviderSql.class,method = "getAdvertisementBeanList")
    public List<AdvertisementBean> getAdvertisementBeanList(AdvertisementBean adc);

    /**
     * 广告管理页数
     * @param adc
     * @return
     */
    @SelectProvider(type = AdvertisementInfoModelEMapper.SubscribeLineProviderSql.class,method = "getAdvertisementCount")
    public Integer getAdvertisementCount(AdvertisementBean adc);



    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 广告管理页数
         *
         */
        public String getAdvertisementCount(final AdvertisementBean advertisementBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT ad.id) ");
            sb.append(" FROM ");
            sb.append(" resourceDB.advertisementInfo ad ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser sy on ad.createBy = sy.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser su on ad.updateBy = su.id ");
            sb.append("  WHERE ad.deleted = 0 ");
            if (advertisementBean.getAppType() != null && advertisementBean.getAppType() > 0){
                sb.append(" AND ad.appType = #{appType}");
            }
            if (advertisementBean.getValid() != null && advertisementBean.getValid() > 0){
                sb.append(" AND ad.valid = #{valid}");

            }

            return sb.toString();
        }

        /**
         * 广告管理分页查询
         */
        public String getAdvertisementBeanList(final AdvertisementBean advertisementBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" ad.id, ad.pictureURL, ad.redirectURL,ad.webTitle, ad.content, ad.valid, ad.beginDate,");
            sb.append(" ad.endDate, ad.createTime, ad.updateTime, ad.appType,ad.adType, ad.rank,ad.deleted,");
            sb.append(" ad.version, ad.createBy, ad.updateBy, sy.nickName as createName ,su.nickName as updateName");
            sb.append(" FROM ");
            sb.append(" resourceDB.advertisementInfo ad ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser sy on ad.createBy = sy.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.sysUser su on ad.updateBy = su.id ");
            sb.append("  WHERE ad.deleted = 0 ");
            if (advertisementBean.getAppType() != null && advertisementBean.getAppType() > 0){
                sb.append(" AND ad.appType = #{appType}");
            }
            if (advertisementBean.getValid() != null && advertisementBean.getValid() > 0){
                sb.append(" AND ad.valid = #{valid}");

            }
            sb.append(" GROUP BY ad.id order by ad.rank DESC  ");
            if(advertisementBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    /**
     * 根据id查询广告
     */
    @Select("SELECT * from resourceDB.advertisementInfo a where a.id = #{id}")
    public AdvertisementInfoModel findAdvertisementById(@Param("id") Integer id);


}
