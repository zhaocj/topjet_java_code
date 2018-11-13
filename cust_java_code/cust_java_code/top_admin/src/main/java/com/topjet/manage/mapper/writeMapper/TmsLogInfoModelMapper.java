package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.TmsLogInfoModel;

/**
 * Created by tsj010 on 2018/5/21.
 */
public interface TmsLogInfoModelMapper {

    /**
     * tms日志记录
     */
    public Integer insertTmsLog(TmsLogInfoModel tmsLogInfoModel);

}