package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.UserAuditHistoryModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserAuditHistoryModelMapper extends BaseMapper<UserAuditHistoryModel> {


}
