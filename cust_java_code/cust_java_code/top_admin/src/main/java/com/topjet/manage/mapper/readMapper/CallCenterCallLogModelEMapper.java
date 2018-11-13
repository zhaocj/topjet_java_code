package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.CallCenterCallLogBean;
import com.topjet.manage.domain.model.CallCenterCallLogModel;
import com.topjet.manage.domain.model.CallCenterLogModel;
import com.topjet.manage.domain.model.CallCenterUserInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CallCenterCallLogModelEMapper extends BaseEMapper<CallCenterCallLogModel>{

    @Select("SELECT *  FROM callCenterCallLog WHERE deleted = 0  AND createBy = #{createBy} ")
    List<CallCenterCallLogModel> listCallCenterCallLog(Integer createBy);

    @Select("SELECT COUNT(id) FROM callCenterCallLog WHERE deleted = 0 AND createBy = #{createBy}")
    int countCallCenterCallLog(Integer createBy);

    List<CallCenterCallLogModel> getCallLogList(CallCenterCallLogBean callCenterCallLogBean);

    int getCallLogCount(CallCenterCallLogBean callCenterCallLogBean);
    /**
     * 获取客服列表
     */
    @Select("SELECT c.id,c.account,c.name,c.pwd,c.sysUserId from manageDB.callCenterUserInfo c")
    public  List<CallCenterUserInfoModel> getSysUser();

    @Select("SELECT *  FROM callCenterCallLog WHERE deleted = 0  AND  id = #{id}  AND  version = #{version} ")
    CallCenterCallLogModel  getCallCenterCallLogById(CallCenterCallLogBean callCenterCallLogBean);

    @Select("SELECT *  FROM callCenterLog WHERE  callSheetId = #{callSheetId} ")
    CallCenterLogModel getCallCenterLog(CallCenterCallLogBean callCenterCallLogBean);
}