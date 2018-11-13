package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.CallCenterLogModel;

public interface CallCenterLogModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CallCenterLogModel record);

    int insertSelective(CallCenterLogModel record);

    CallCenterLogModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CallCenterLogModel record);

    int updateByPrimaryKey(CallCenterLogModel record);
}