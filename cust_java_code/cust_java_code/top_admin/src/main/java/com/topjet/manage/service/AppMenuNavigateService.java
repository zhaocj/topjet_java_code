package com.topjet.manage.service;

import com.topjet.manage.domain.model.HomeAmongProfileModel;
import com.topjet.manage.domain.model.HomeBottomProfileModel;

import java.util.List;

/**
 * Created by liyj on 2017/12/1.
 */
public interface AppMenuNavigateService {
    /**
     * 首页底部配置分页查询
     */
    public List<HomeBottomProfileModel> getButtomProfileList(HomeBottomProfileModel homeBottomProfileModel);
    /**
     * 页数
     */
    public Integer getButtomProfileCount(HomeBottomProfileModel homeBottomProfileModel);
    /**
     * 根据id查询
     */
    public HomeBottomProfileModel findBottomProfileById(Integer id);
    /**
     * 添加底部配置
     */
    public Integer insertBottomProfile(HomeBottomProfileModel homeBottomProfileModel);
    /**
     * 修改底部配置
     */
    public Integer updateBottomProfile(HomeBottomProfileModel homeBottomProfileModel);
    /**
     * 首页中间配置分页查询
     */
    public List<HomeAmongProfileModel> getCenterProfileList(HomeAmongProfileModel homeAmongProfileModel);
    /**
     * 首页中间配置页数
     */
    public Integer getCenterProfileCount(HomeAmongProfileModel homeAmongProfileModel);
    /**
     * 根据id查询中间配置
     */
    public HomeAmongProfileModel findCenterProfileById(Integer id);
    /**
     * 添加中间配置
     */
    public Integer insertCenterProfile(HomeAmongProfileModel homeAmongProfileModel);
    /**
     * 修改中间配置
     */
    public Integer updateCenterProfile(HomeAmongProfileModel homeAmongProfileModel);
}
