package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.UserAuditHistoryModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserAuditHistoryModelEMapper extends BaseEMapper<UserAuditHistoryModel> {

    List<UserAuditHistoryModel> listHistory(Map<String, Object> paramMap);

    List<UserAuditHistoryModel> selectListByEntity(UserAuditHistoryModel userAuditHistoryModel);

    UserAuditHistoryModel getAuditHistoryRemark(UserAuditHistoryModel userAuditHistoryModel);
}
