package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.ActivityCityInfoModel;
import com.topjet.manage.domain.model.ActivityPageInfoModel;
import com.topjet.manage.domain.model.RegularActivityModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-08 14:47
 */

@Mapper
@Repository
public interface RegularActivityModelEMapper extends BaseEMapper<RegularActivityModel>{

    @Select("SELECT * FROM resourceDB.regularActivity WHERE appType = #{appType} AND deleted = #{deleted}")
    List<RegularActivityModel> listRegularActivity(@Param("appType")Integer appType, @Param("deleted")Integer deleted);

    @Select("SELECT * FROM resourceDB.activityPageInfo WHERE regularActivityId = #{regularActivityId} AND deleted = #{deleted}")
    List<ActivityPageInfoModel> listActivityPageByActivityId(@Param("regularActivityId") Integer regularActivityId, @Param("deleted")Integer deleted);

    @Select("SELECT * FROM resourceDB.activityCityInfo WHERE regularActivityId = #{regularActivityId} AND deleted = #{deleted}")
    List<ActivityCityInfoModel> listActivityCityByActivityId(@Param("regularActivityId") Integer regularActivityId,@Param("deleted")Integer deleted);

    @Select("SELECT * FROM resourceDB.activityPageInfo WHERE regularActivityId = #{regularActivityId} AND showPage = #{showPage} AND deleted = 0")
    List<ActivityPageInfoModel> listActivityPageByPageId(@Param("regularActivityId") Integer regularActivityId,  @Param("showPage") Integer showPage);

    @Select("SELECT * FROM resourceDB.activityCityInfo WHERE regularActivityId = #{regularActivityId} AND cityCode = #{cityCode} AND deleted = 0")
    List<ActivityCityInfoModel> listActivityCity(@Param("cityCode") Integer cityCode, @Param("regularActivityId") Integer regularActivityId);

    @Select("SELECT * FROM resourceDB.activityCityInfo WHERE id = #{id}")
    ActivityCityInfoModel selectActivityCityByPrimaryKey(@Param("id") Integer id);

    @Select("SELECT * FROM resourceDB.regularActivity WHERE id = #{id}")
    RegularActivityModel selectRegularActivityByPrimaryKey(@Param("id") Integer id);

}
