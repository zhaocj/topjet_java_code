package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.PhotoInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/12/8.
 */
public interface PhotoInfoModelMapper {
    /**
     * 修改头像
     */
    @UpdateProvider(type = PhotoInfoProviderSql.class,method = "updatePhoto")
    public Integer updatePhoto(PhotoInfoModel photoInfoModel);


    class PhotoInfoProviderSql {
        public String updatePhoto(final PhotoInfoModel photoInfoModel){
            return new SQL(){{
                UPDATE("userDB.photoInfo");
                if (!StringUtils.isEmpty(photoInfoModel.getUrl()))
                    SET("url = #{url}");
                if (!StringUtils.isEmpty(photoInfoModel.getUrlTobe()))
                    SET("urlTobe = #{urlTobe}");
                if (photoInfoModel.getUserId() != null)
                    SET("userId = #{userId}");
                if (photoInfoModel.getType()!=null)
                    SET("type = #{type}");
                if (photoInfoModel.getCreateTime()!=null)
                    SET("createTime = #{createTime}");
                if (photoInfoModel.getUpdateTime()!=null)
                    SET("updateTime = #{updateTime}");
                if (photoInfoModel.getDeleted()!=null)
                    SET("deleted = #{deleted}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }


}
