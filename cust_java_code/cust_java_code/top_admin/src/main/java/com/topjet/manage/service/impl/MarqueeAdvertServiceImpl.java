package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.MarqueeAdvertModel;
import com.topjet.manage.mapper.readMapper.MarqueeAdvertModelEMapper;
import com.topjet.manage.mapper.writeMapper.MarqueeAdvertModelMapper;
import com.topjet.manage.service.MarqueeAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/11/7.
 */
@Service
public class MarqueeAdvertServiceImpl implements MarqueeAdvertService {

    @Autowired
    private MarqueeAdvertModelEMapper marqueeAdvertModelEMapper;
    @Autowired
    private MarqueeAdvertModelMapper marqueeAdvertModelMapper;

    @Override
    public List<MarqueeAdvertModel> getMarqueeAdvertList(MarqueeAdvertModel marqueeAdvertModel) {
        return marqueeAdvertModelEMapper.getMarqueeAdvertList(marqueeAdvertModel);
    }

    @Override
    public Integer getMarqueeAdvertCount(MarqueeAdvertModel marqueeAdvertModel) {
        return marqueeAdvertModelEMapper.getMarqueeAdvertCount(marqueeAdvertModel);
    }

    @Override
    public MarqueeAdvertModel getMarqueeAdvertById(Integer id) {
        return marqueeAdvertModelEMapper.getMarqueeAdvertById(id);
    }

    @Override
    public Integer deleteMarqueeAdvert(Integer id) {
        return marqueeAdvertModelMapper.deleteMarqueeAdvert(id);
    }

    @Override
    public Integer insertMarqueeAdvert(MarqueeAdvertModel marqueeAdvertModel) {
        return marqueeAdvertModelMapper.insertMarqueeAdvert(marqueeAdvertModel);
    }

    @Override
    public Integer updateMarqueeAdvert(MarqueeAdvertModel marqueeAdvertModel) {
        return marqueeAdvertModelMapper.updateMarqueeAdvert(marqueeAdvertModel);
    }

    @Override
    public void reRank(Integer type, Integer rank, Integer id) {
        MarqueeAdvertModel marqueeAdvertModel = new MarqueeAdvertModel();
        if(id != null){
            marqueeAdvertModel.setRank(rank);
            marqueeAdvertModel.setType(type);
            marqueeAdvertModel.setId(id);
        }
        else{
            marqueeAdvertModel.setRank(rank);
            marqueeAdvertModel.setAppType(type);
        }
        List<MarqueeAdvertModel> list = marqueeAdvertModelEMapper.getMarqueeAdvertList(marqueeAdvertModel);
        if(list != null && list.size() > 0){
            MarqueeAdvertModel model = list.get(0);
            model.setRank(rank + 1);
            Integer total = marqueeAdvertModelMapper.updateMarqueeAdvert(model);
            reRank(type,rank+1,model.getId());
        }
    }
}
