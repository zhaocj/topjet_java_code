package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.AdvertisementInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/10/13.
 */
public interface AdvertisementInfoModelMapper {

    /**
     * 添加广告
     * @param advertisementInfoModel
     * @return
     */
    @InsertProvider(type = AdvertisementProviderSql.class,method = "insertAdvertisement")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    Integer insertAdvertisement(AdvertisementInfoModel advertisementInfoModel);

    /**
     * 修改广告
     * @param advertisementInfoModel
     * @return
     */
    @UpdateProvider(type = AdvertisementProviderSql.class,method = "updateAdvertisement")
    Integer updateAdvertisement(AdvertisementInfoModel advertisementInfoModel);

    class AdvertisementProviderSql {
        public String insertAdvertisement(final AdvertisementInfoModel advertisementInfoModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.advertisementInfo");
                if (!StringUtils.isEmpty(advertisementInfoModel.getPictureURL()))
                    VALUES("pictureURL","#{pictureURL}");
                if (!StringUtils.isEmpty(advertisementInfoModel.getPictureKey()))
                    VALUES("pictureKey","#{pictureKey}");
                if (!StringUtils.isEmpty(advertisementInfoModel.getRedirectURL()))
                    VALUES("redirectURL","#{redirectURL}");
                if(!StringUtils.isEmpty(advertisementInfoModel.getWebTitle()))
                    VALUES("webTitle","#{webTitle}");
                if(!StringUtils.isEmpty(advertisementInfoModel.getContent()))
                    VALUES("content","#{content}");
                if (advertisementInfoModel.getValid() != null)
                    VALUES("valid","#{valid}");
                if (advertisementInfoModel.getBeginDate()!=null)
                    VALUES("beginDate","#{beginDate}");
                if (advertisementInfoModel.getEndDate()!=null)
                    VALUES("endDate","#{endDate}");
                if (advertisementInfoModel.getCreateTime()!=null)
                    VALUES("createTime","#{createTime}");
                if (advertisementInfoModel.getUpdateTime()!=null)
                    VALUES("updateTime","#{updateTime}");
                if (advertisementInfoModel.getAppType()!=null)
                    VALUES("appType","#{appType}");
                if (advertisementInfoModel.getAdType()!=null)
                    VALUES("adType","#{adType}");
                if (advertisementInfoModel.getRank()!=null)
                    VALUES("rank","#{rank}");
                if (advertisementInfoModel.getDeleted()!=null)
                    VALUES("deleted","#{deleted}");
                if (advertisementInfoModel.getCreateBy()!=null)
                    VALUES("createBy","#{createBy}");
                if (advertisementInfoModel.getUpdateBy()!=null)
                    VALUES("updateBy","#{updateBy}");
            }}.toString();
        }

        public String updateAdvertisement(final AdvertisementInfoModel advertisementInfoModel){
            return new SQL(){{
                UPDATE("resourceDB.advertisementInfo");
                if (!StringUtils.isEmpty(advertisementInfoModel.getPictureURL()))
                    SET("pictureURL = #{pictureURL}");
                if (!StringUtils.isEmpty(advertisementInfoModel.getPictureKey()))
                    SET("pictureKey = #{pictureKey}");
                if (!StringUtils.isEmpty(advertisementInfoModel.getRedirectURL()))
                    SET("redirectURL = #{redirectURL}");
                if (advertisementInfoModel.getRedirectURL() == null || advertisementInfoModel.getRedirectURL() == "")
                    SET("redirectURL = ''");
                if(!StringUtils.isEmpty(advertisementInfoModel.getWebTitle()))
                    SET("webTitle = #{webTitle}");
                if(!StringUtils.isEmpty(advertisementInfoModel.getContent()))
                    SET("content = #{content}");
                if (advertisementInfoModel.getValid() != null)
                    SET("valid = #{valid}");
                if (advertisementInfoModel.getBeginDate()!=null)
                    SET("beginDate = #{beginDate}");
                if (advertisementInfoModel.getEndDate()!=null)
                    SET("endDate = #{endDate}");
                if (advertisementInfoModel.getCreateTime()!=null)
                    SET("createTime = #{createTime}");
                if (advertisementInfoModel.getUpdateTime()!=null)
                    SET("updateTime = #{updateTime}");
                if (advertisementInfoModel.getAppType()!=null)
                    SET("appType = #{appType}");
                if (advertisementInfoModel.getAdType()!=null)
                    SET("adType = #{adType}");
                if (advertisementInfoModel.getRank()!=null)
                    SET("rank = #{rank}");
                if (advertisementInfoModel.getDeleted()!=null)
                    SET("deleted = #{deleted}");
                if (advertisementInfoModel.getCreateBy()!=null)
                    SET("createBy = #{createBy}");
                if (advertisementInfoModel.getUpdateBy()!=null)
                    SET("updateBy = #{updateBy}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }


}
