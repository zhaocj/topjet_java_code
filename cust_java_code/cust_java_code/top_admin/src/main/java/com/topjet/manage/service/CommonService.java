package com.topjet.manage.service;

import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
public interface CommonService {

    /**
     * 更新资源文件版本号
     * @param rfvModel
     * @return
     */
    public void updateResourceFileVersionInfo(ResourceFileVersionInfoModel rfvModel,String beginDate) throws Exception;

}
