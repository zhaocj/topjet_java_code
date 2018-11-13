package com.topjet.manage.mapper.writeMapper;

import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.domain.model.MatchOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MatchOrderInfoMapper extends BaseMapper<MatchOrderInfo> {

}