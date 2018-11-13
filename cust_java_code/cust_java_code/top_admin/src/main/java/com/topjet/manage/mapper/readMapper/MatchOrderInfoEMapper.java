package com.topjet.manage.mapper.readMapper;

import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.domain.model.MatchOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MatchOrderInfoEMapper extends BaseEMapper<MatchOrderInfo> {

}