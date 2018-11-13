package com.topjet.manage.service;

import com.topjet.common.ResultBaseMsg;
import com.topjet.manage.domain.bean.CollocationInfoBean;
import com.topjet.manage.domain.bean.NameBean;
import com.topjet.manage.domain.model.CollocationInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/9/20.
 */
public interface CollocationInfoService {

    /**
     * 货源配置列表
     */
    public List<CollocationInfoBean> getCollocationList(CollocationInfoBean collocationInfoBean);

    /**
     * 货源配置页数
     */
    public Integer getCollocationCount(CollocationInfoBean collocationInfoBean);
    /**
     * 新增货源配置
     */
    public Integer createCollocationInfo(CollocationInfoBean collocationInfoBean);
    /**
     * 修改货源配置
     */
    public Integer updateCollocationInfo(CollocationInfoBean collocationInfoBean);
    /**
     * 删除货源配置
     */
    public Integer deleteCollocationInfo(CollocationInfoBean collocationInfoBean);
    /**
     * 更新资源配置文件
     */
    public void updateResource(ResourceFileVersionInfoModel resourceFileVersionInfomodel);
    /** 获取该类型资源 */
    List<NameBean> getResourceByType(Integer resourceType);
    /**
     * 获取匹配中心电话
     */
    public CollocationInfoModel getMatchCenterPhone();
    /**
     * 编辑匹配中心电话
     */
    public ResultBaseMsg updateOrAdd(String mobile);
}
