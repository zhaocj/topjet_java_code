package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TaskInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-04 10:26
 */


@Mapper
@Repository
public interface TaskInfoModelMapper extends BaseMapper<TaskInfoModel> {
    /**
     * 批量插入任务数据
     * @param taskInfoModelList
     */
    void insertTakskInfo(List<TaskInfoModel> taskInfoModelList);

}
