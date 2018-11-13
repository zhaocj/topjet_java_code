package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.ActivityCityInfoModel;
import com.topjet.manage.domain.model.ActivityPageInfoModel;
import com.topjet.manage.domain.model.RegularActivityModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RegularActivityModelMapper extends BaseMapper<RegularActivityModel>{

    @Update("UPDATE resourceDB.activityCityInfo SET deleted = #{deleted} WHERE id = #{id}")
    void updateActivityCityStatus(@Param("id") Integer id, @Param("deleted") Integer deleted);

    @Update("UPDATE resourceDB.activityPageInfo SET deleted = #{deleted} WHERE id = #{id}")
    void deleteActivityPage(@Param("id") Integer id, @Param("deleted") Integer deleted);

    @Update("UPDATE resourceDB.activityPageInfo SET deleted = #{deleted} WHERE id = #{id}")
    int updatePageByPrimaryKey(ActivityPageInfoModel activityPageInfoModel);

    @Insert("INSERT INTO resourceDB.activityPageInfo(showPage,regularActivityId,deleted) VALUES(#{showPage},#{regularActivityId},#{deleted})")
    int insertShowPage(ActivityPageInfoModel activityPageInfoModel);

    @Insert("INSERT INTO resourceDB.activityCityInfo(cityCode,regularActivityId,deleted) VALUES(#{cityCode},#{regularActivityId},#{deleted})")
    int insertActivityCity(ActivityCityInfoModel activityCityInfoModel);

    @Update("UPDATE resourceDB.activityCityInfo SET cityCode = #{cityCode} WHERE id = #{id}")
    int updateCityByPrimaryKey(ActivityCityInfoModel activityCityInfoModel );

    @Update("UPDATE resourceDB.regularActivity SET deleted = #{deleted} WHERE id = #{id}")
    void updateRegularActivity(@Param("id") Integer id, @Param("deleted") Integer deleted);


}