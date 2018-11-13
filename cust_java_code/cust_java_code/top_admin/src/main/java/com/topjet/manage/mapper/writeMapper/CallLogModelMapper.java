package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.CallLogModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CallLogModelMapper extends BaseMapper<CallLogModelMapper> {

    //插入联系记录
    @Insert("insert into callLog (mobile, remark,createTime, createBy, createByName,updateTime, updateBy, version,deleted)\n" +
            "values (#{mobile}, #{remark},\n" +
            "#{createTime},#{createBy},#{createByName},\n" +
            "#{updateTime},#{updateBy},#{version}, \n" +
            "#{deleted})")
    Integer insertCallLog(CallLogModel callLogModel);

}