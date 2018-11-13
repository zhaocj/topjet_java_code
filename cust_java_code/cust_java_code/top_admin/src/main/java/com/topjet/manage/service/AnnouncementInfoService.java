package com.topjet.manage.service;

import com.topjet.manage.domain.model.AnnouncementInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/11/13.
 */
public interface AnnouncementInfoService {

    /**
     * 公告管理查询分页
     */
    public List<AnnouncementInfoModel> getAnnounceList(AnnouncementInfoModel model);
    /**
     * 公告管理页数
     */
    public Integer getAnnounceCount(AnnouncementInfoModel model);
    /**
     * 根据id查询公告
     */
    public AnnouncementInfoModel getAnnounceById(Integer id);
    /**
     * 修改公告
     */
    public Integer updateAnnounceInfo(AnnouncementInfoModel announcementInfoModel);
    /**
     * 添加公告
     */
    public Integer addAnnounceInfo(AnnouncementInfoModel announcementInfoModel);
    /**
     * 删除公告
     */
    public Integer deleteAnnounceInfo(Integer id);
    /**
     * 公告推送
     */
    void sendAnno(AnnouncementInfoModel model);

}
