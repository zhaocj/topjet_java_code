package com.topjet.manage.service;

import com.topjet.manage.domain.bean.MatchCenterDriverBean;

import java.util.List;

/**
 * Created by yuzh on 2017/8/23.
 */
public interface DriverOptionService {

    /**
     * 管理平台匹配中心 司机位置查询
     */
    public List<MatchCenterDriverBean> getDriverOptionList(MatchCenterDriverBean matchCenterDriverBean);
    /**
     * 司机位置查询页数
     */
    public int getDriverOptionCount(MatchCenterDriverBean matchCenterDriverBean);
}
