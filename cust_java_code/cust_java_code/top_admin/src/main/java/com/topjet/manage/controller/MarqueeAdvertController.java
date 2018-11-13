package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.GeturlVRU;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.cloud.manage.service.basic.bean.UploadVRU;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.AdvertisementInfoModel;
import com.topjet.manage.domain.model.MarqueeAdvertModel;
import com.topjet.manage.service.MarqueeAdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyj on 2017/11/7.
 */
@Controller
@RequestMapping("/marqueeAdvert/")
public class MarqueeAdvertController extends BaseController{

    private static Logger log = LoggerFactory.getLogger(MarqueeAdvertController.class);

    @Autowired
    private BasicFeignService basicFeignService;

    @Autowired
    private MarqueeAdvertService marqueeAdvertService;

    @RequestMapping("list")
    @ResponseBody
    public Object list(MarqueeAdvertModel marqueeAdvertModel){
        PaginationBean bean = new PaginationBean();
        List<MarqueeAdvertModel> dataList = marqueeAdvertService.getMarqueeAdvertList(marqueeAdvertModel);
        Integer total = marqueeAdvertService.getMarqueeAdvertCount(marqueeAdvertModel);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    @ResponseBody
    @RequestMapping("delete")
    public Object delete(MarqueeAdvertModel model) {
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        if (model.getId() != null) {
            MarqueeAdvertModel model2 = marqueeAdvertService.getMarqueeAdvertById(model.getId());
            if (model.getVersion().intValue() == model2.getVersion().intValue()) {
                marqueeAdvertService.deleteMarqueeAdvert(model.getId());
            } else {
                msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
                msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            }
        }
        return msg;
    }

    @RequestMapping("edit")
    public ModelAndView edit(Integer id, Integer type) {

        MarqueeAdvertModel model = marqueeAdvertService.getMarqueeAdvertById(id);
        ModelAndView view;
        if(type==2){
            view = new ModelAndView("view/marqueeAdvert/editAnno");
        }else{
            view = new ModelAndView("stationAnnoucement/editAnno.ftl");
        }
        view.addObject("model", model);
        return view;
    }

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public Object saveOrUpdate(MarqueeAdvertModel model,String typeFlag) {
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        if (model.getId() != null) {
            MarqueeAdvertModel model2 = marqueeAdvertService.getMarqueeAdvertById(model.getId());
            if (model2.getVersion() != model.getVersion()) {
                msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
                msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            } else {
                model2.setContent(model.getContent());
//                model2.setType(model.getType());
                model2.setAppType(model.getAppType());
                model2.setBeginDate(model.getBeginDate());
                model2.setEndDate(model.getEndDate());
                model2.setTitle(model.getTitle());
                model2.setValid(model.getValid());
                model2.setRank(model.getRank());
                model2.setUpdateTime(DateUtil.now());
                marqueeAdvertService.reRank(model.getAppType(),model.getRank(),model.getId());
                marqueeAdvertService.updateMarqueeAdvert(model2);
            }
        } else {
            MarqueeAdvertModel model2 = new MarqueeAdvertModel();
            model2.setContent(model.getContent());
            model2.setType(model.getType());
            model2.setUpdateTime(DateUtil.now());
            model2.setTitle(model.getTitle());
            model2.setValid(model.getValid());
            model2.setRank(model.getRank());
            model2.setCreateBy(SessionUtils.getSysUserSession().getId());
            model2.setCreateTime(DateUtil.now());
            model2.setBeginDate(model.getBeginDate());
            model2.setEndDate(model.getEndDate());
            model2.setVersion(0);
            model2.setDeleted(0);
            for(int i=0;i <typeFlag.length();i++){
                model2.setType(Integer.valueOf(typeFlag.charAt(i)+"")==2 || Integer.valueOf(typeFlag.charAt(i)+"")==4?1:2);
                model2.setAppType(Integer.valueOf(typeFlag.charAt(i)+""));
                marqueeAdvertService.reRank(model2.getAppType(),model.getRank(),model.getId());
                marqueeAdvertService.insertMarqueeAdvert(model2);
            }
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping("uploadPic")
    public Map<String,Object> uploadPic(@RequestBody AdvertisementInfoModel model){
        Map <String,Object> resultMap = new HashMap<String,Object>();

        UploadRTS rts = new UploadRTS();
        rts.setType(PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT);
        rts.setPictureData(model.getPictureURL().substring(model.getPictureURL().indexOf(",") + 1));

        GeturlRTS geturlRTS = new GeturlRTS();
        geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT);
        GeturlVRU url = null;

        try{
            UploadVRU upload = basicFeignService.upload(rts);
            geturlRTS.setKey(upload.getStrkey());
            url = basicFeignService.getUrl(geturlRTS);
        }catch (Exception e){
            log.error("图片上传失败！！！");
        }
        if(url != null ){
            resultMap.put("pic",url.getObjurl());
            resultMap.put("success","true");
        }
        return resultMap;
    }

}
