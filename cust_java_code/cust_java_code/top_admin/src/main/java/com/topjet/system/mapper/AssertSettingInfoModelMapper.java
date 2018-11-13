package com.topjet.system.mapper;

import com.topjet.system.domain.model.AssertSettingInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AssertSettingInfoModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssertSettingInfoModel record);

    int insertSelective(AssertSettingInfoModel record);

    AssertSettingInfoModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssertSettingInfoModel record);

    int updateByPrimaryKey(AssertSettingInfoModel record);
    /**
     * 查询所有公告
     */
    public List<AssertSettingInfoModel> getAllAssertList();
    /**
     * 所有公告页数
     */
    public int getAssertCount();
}