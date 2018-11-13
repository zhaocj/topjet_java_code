package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.CallCenterUserInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CallCenterUserInfoModelEMapper extends BaseEMapper<CallCenterUserInfoModel> {

    /**
     * 查询话机绑定的客服
     * @return
     */
    @Select("SELECT id,account,name,pwd,sysUserId FROM callCenterUserInfo ")
    public  List<CallCenterUserInfoModel>  getSysUser();
}