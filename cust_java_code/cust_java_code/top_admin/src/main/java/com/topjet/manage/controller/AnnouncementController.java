package com.topjet.manage.controller;

import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.AnnoucementConstant;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.AnnouncementInfoModel;
import com.topjet.manage.service.AnnouncementInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by liyj on 2017/11/9.
 */
@Controller
@RequestMapping("/announcementInfo/")
public class AnnouncementController extends BaseController{

    @Autowired
    private AnnouncementInfoService announcementInfoService;

    @RequestMapping("list")
    @ResponseBody
    public Object list(AnnouncementInfoModel announcementInfoModel){
        PaginationBean bean = new PaginationBean();
        List<AnnouncementInfoModel> dataList = announcementInfoService.getAnnounceList(announcementInfoModel);
        Integer total = announcementInfoService.getAnnounceCount(announcementInfoModel);
        bean.setRows(dataList);
        bean.setTotal(total);
        return  bean;
    }


    @RequestMapping("edit")
    public ModelAndView edit(Integer id) {
        AnnouncementInfoModel model = announcementInfoService.getAnnounceById(id);
        ModelAndView view = new ModelAndView("view/announcement/editAnno");
        view.addObject("model", model);
        return view;
    }

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public Object saveOrUpdate(AnnouncementInfoModel model, String typeFlag) {
        if (model.getId() != null) {
            AnnouncementInfoModel model2 = announcementInfoService.getAnnounceById(model.getId());
            if (model2.getVersion() != model.getVersion()) {
                msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
                msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            } else {
                model2.setContent(model.getContent());
                model2.setType(model.getType());
                model2.setPriority(model.getPriority());
                model2.setRedirectURL(model.getRedirectURL());
                model2.setBeginDate(model.getBeginDate());
                model2.setEndDate(model.getEndDate());
                model2.setTitle(model.getTitle());
                model2.setValid(model.getValid());
                model2.setUpdateTime(DateUtil.now());
                model2.setUpdateBy(SessionUtils.getSysUserSession().getId());
                announcementInfoService.updateAnnounceInfo(model2);
            }
        } else {
            AnnouncementInfoModel model2 = new AnnouncementInfoModel();
            model2.setContent(model.getContent());
            model2.setPriority(model.getPriority());
            model2.setRedirectURL(model.getRedirectURL());
            model2.setUpdateTime(DateUtil.now());
            model2.setTitle(model.getTitle());
            model2.setValid(model.getValid());
            model2.setUpdateBy(SessionUtils.getSysUserSession().getId());
            model2.setCreateBy(SessionUtils.getSysUserSession().getId());
            model2.setCreateTime(DateUtil.now());
            model2.setBeginDate(model.getBeginDate());
            model2.setEndDate(model.getEndDate());
            model2.setVersion(0);
            model2.setDeleted(0);
            for(int i=0;i <typeFlag.length();i++){
                model2.setType(Integer.valueOf(typeFlag.charAt(i)+""));
                announcementInfoService.addAnnounceInfo(model2);
            }
        }
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        return msg;
    }

    @ResponseBody
    @RequestMapping("delete")
    public Object delete(AnnouncementInfoModel model) {
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        if (model.getId() != null) {
            AnnouncementInfoModel model2 = announcementInfoService.getAnnounceById(model.getId());
            if (model.getVersion().intValue() == model2.getVersion().intValue()) {
                announcementInfoService.deleteAnnounceInfo(model.getId());
            } else {
                msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
                msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            }
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping("send")
    public Object send(AnnouncementInfoModel model) {
        int type=model.getType();
        Long lastTriggerTime = AnnoucementConstant.ANNOUCEMENT_SEND_FILTER[type];

        Calendar c = Calendar.getInstance();
        Long currentTime = c.getTimeInMillis();
        BigDecimal duration = (new BigDecimal(currentTime).subtract(new BigDecimal(lastTriggerTime)))
                .divide(new BigDecimal(1000 * 60 * 60 ), 2, BigDecimal.ROUND_HALF_UP);
        if (duration.compareTo(BigDecimal.ONE) >= 0 ) {
            AnnoucementConstant.ANNOUCEMENT_SEND_FILTER[type] = currentTime;
        } else {
            msg.setMsg(ExceptionEnum.E_27.getMsg());
            msg.setStatus(ExceptionEnum.E_27.getStatus());
            return msg;
        }

        if (model.getId() != null) {
            AnnouncementInfoModel model2 = announcementInfoService.getAnnounceById(model.getId());
            if (model.getVersion().intValue() == model2.getVersion().intValue()) {
                announcementInfoService.sendAnno(model2);
            } else {
                msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
                msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            }
            msg.setMsg(ExceptionEnum.E_0.getMsg());
            msg.setStatus(ExceptionEnum.E_0.getStatus());
        }
        return msg;
    }



}
