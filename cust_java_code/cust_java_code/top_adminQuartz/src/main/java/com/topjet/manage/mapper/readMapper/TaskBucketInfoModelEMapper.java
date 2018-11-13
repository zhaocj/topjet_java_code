package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.TaskBucketInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-04 11:24
 */

@Mapper
@Repository
public interface TaskBucketInfoModelEMapper extends BaseEMapper<TaskBucketInfoModel> {

    @Select("SELECT COUNT(id) FROM taskBucketInfo WHERE type = #{type} AND invalid = 1 AND deleted = 0")
    Integer countBucketInfo(@Param("type") Integer type);

}
