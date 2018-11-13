package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.CallLogModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CallLogModelEMapper extends BaseEMapper<CallLogModelEMapper> {

    //根据手机号查询联系记录
    @Select("SELECT id,mobile,remark,createTime,createBy,createByName,updateTime,updateBy,version,deleted from callLog where mobile = #{mobile}")
    public List<CallLogModel> getCallLogList(String mobile);

    //根据手机号查询页数
    @Select("SELECT count(DISTINCT id) from callLog where mobile = #{mobile}")
    public int getCallLogCount(String mobile);

}