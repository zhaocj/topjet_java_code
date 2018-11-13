package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.HomeAmongProfileModel;
import com.topjet.manage.domain.model.HomeBottomProfileModel;
import com.topjet.manage.mapper.readMapper.HomeAmongProfileModelEMapper;
import com.topjet.manage.mapper.readMapper.HomeBottomProfileModelEMapper;
import com.topjet.manage.mapper.writeMapper.HomeAmongProfileModelMapper;
import com.topjet.manage.mapper.writeMapper.HomeBottomProfileModelMapper;
import com.topjet.manage.service.AppMenuNavigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/12/1.
 */
@Service
public class AppMenuNavigateServiceImpl implements AppMenuNavigateService{

    @Autowired
    private HomeBottomProfileModelEMapper homeBottomProfileModelEMapper;
    @Autowired
    private HomeBottomProfileModelMapper homeBottomProfileModelMapper;
    @Autowired
    private HomeAmongProfileModelEMapper homeAmongProfileModelEMapper;
    @Autowired
    private HomeAmongProfileModelMapper homeAmongProfileModelMapper;

    @Override
    public List<HomeBottomProfileModel> getButtomProfileList(HomeBottomProfileModel homeBottomProfileModel) {
        return homeBottomProfileModelEMapper.getButtomProfileList(homeBottomProfileModel);
    }

    @Override
    public Integer getButtomProfileCount(HomeBottomProfileModel homeBottomProfileModel) {
        return homeBottomProfileModelEMapper.getButtomProfileCount(homeBottomProfileModel);
    }

    @Override
    public Integer insertBottomProfile(HomeBottomProfileModel homeBottomProfileModel) {
        return homeBottomProfileModelMapper.insertBottomProfile(homeBottomProfileModel);
    }

    @Override
    public HomeBottomProfileModel findBottomProfileById(Integer id) {
        return homeBottomProfileModelEMapper.findBottomProfileById(id);
    }

    @Override
    public Integer updateBottomProfile(HomeBottomProfileModel homeBottomProfileModel) {
        return homeBottomProfileModelMapper.updateBottomProfile(homeBottomProfileModel);
    }

    @Override
    public List<HomeAmongProfileModel> getCenterProfileList(HomeAmongProfileModel homeAmongProfileModel) {
        return homeAmongProfileModelEMapper.getCenterProfileList(homeAmongProfileModel);
    }

    @Override
    public Integer getCenterProfileCount(HomeAmongProfileModel homeAmongProfileModel) {
        return homeAmongProfileModelEMapper.getCenterProfileCount(homeAmongProfileModel);
    }

    @Override
    public Integer insertCenterProfile(HomeAmongProfileModel homeAmongProfileModel) {
        return homeAmongProfileModelMapper.insertCenterProfile(homeAmongProfileModel);
    }

    @Override
    public Integer updateCenterProfile(HomeAmongProfileModel homeAmongProfileModel) {
        return homeAmongProfileModelMapper.updateCenterProfile(homeAmongProfileModel);
    }

    @Override
    public HomeAmongProfileModel findCenterProfileById(Integer id) {
        return homeAmongProfileModelEMapper.findCenterProfileById(id);
    }
}
