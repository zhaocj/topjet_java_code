package com.topjet.manage.service;

import com.topjet.manage.domain.model.MarqueeAdvertModel;

import java.util.List;

/**
 * Created by liyj on 2017/11/7.
 */
public interface MarqueeAdvertService {

    /**
     * 跑马灯广告查询分页
     */
    public List<MarqueeAdvertModel> getMarqueeAdvertList(MarqueeAdvertModel marqueeAdvertModel);
    /**
     * 跑马灯广告页数
     */
    public Integer getMarqueeAdvertCount(MarqueeAdvertModel marqueeAdvertModel);
    /**
     * 根据id查询
     */
    public MarqueeAdvertModel getMarqueeAdvertById(Integer id);
    /**
     * 根据id删除
     */
    public Integer deleteMarqueeAdvert(Integer id);
    /**
     * 添加
     */
    public Integer insertMarqueeAdvert(MarqueeAdvertModel marqueeAdvertModel);
    /**
     * 修改
     */
    public Integer updateMarqueeAdvert(MarqueeAdvertModel marqueeAdvertModel);
    /**
     * 修改或者添加操作后 重现赋值rank
     * @param type 公告类型
     * @param rank 新排序
     */
    public void  reRank(Integer type, Integer rank, Integer id);

}
