package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.common.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.HomeAmongProfileModel;
import com.topjet.manage.domain.model.HomeBottomProfileModel;
import com.topjet.manage.service.AppMenuNavigateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liyj on 2017/11/30.
 */
@Controller
@RequestMapping("/appMenuNavigate/")
public class AppMenuNavigateController extends BaseController{

    @Autowired
    private AppMenuNavigateService appMenuNavigateService;
    @Autowired
    private BasicFeignService basicFeignService;
    ModelAndView modelAndView = new ModelAndView();

    @ResponseBody
    @RequestMapping("ButtomProfileList")
    public Object ButtomProfileList(HomeBottomProfileModel homeBottomProfileModel){
        PaginationBean bean = new PaginationBean();
        String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT + "";
        List<HomeBottomProfileModel> dataList = appMenuNavigateService.getButtomProfileList(homeBottomProfileModel);
        try {
            if(dataList != null && dataList.size() != 0){
                for(HomeBottomProfileModel hpm : dataList){
                    GeturlRTS geturlRTS = new GeturlRTS();
                    geturlRTS.setKey(hpm.getIconDownUrl());
                    geturlRTS.setType(Integer.parseInt(type));
                    hpm.setIconDownUrl(basicFeignService.getUrl(geturlRTS).getObjurl());

                    geturlRTS.setKey(hpm.getIconNormalUrl());
                    geturlRTS.setType(Integer.parseInt(type));
                    hpm.setIconNormalUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Integer total = appMenuNavigateService.getButtomProfileCount(homeBottomProfileModel);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    @ResponseBody
    @RequestMapping("add")
    public Object add(HomeBottomProfileModel homeBottomProfileModel,String typeFlag){
        homeBottomProfileModel.setDeleted(0);
        UploadRTS uploadRTS = new UploadRTS();
        String type=PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT+"";

        if(StringUtils.isNotBlank(homeBottomProfileModel.getIconDownUrl())){
            String pictureURL = homeBottomProfileModel.getIconDownUrl().substring(homeBottomProfileModel.getIconDownUrl().indexOf(",") + 1);
            uploadRTS.setPictureData(pictureURL);
            uploadRTS.setType(Integer.parseInt(type));
            homeBottomProfileModel.setIconDownUrl(basicFeignService.upload(uploadRTS).getStrkey());
        }

        if(StringUtils.isNotBlank(homeBottomProfileModel.getIconNormalUrl())){
            String picture = homeBottomProfileModel.getIconNormalUrl().substring(homeBottomProfileModel.getIconNormalUrl().indexOf(",")+1);
            uploadRTS.setPictureData(picture);
            uploadRTS.setType(Integer.parseInt(type));
            homeBottomProfileModel.setIconNormalUrl(basicFeignService.upload(uploadRTS).getStrkey());
        }

        for(int i=0;i <typeFlag.length();i++){
            homeBottomProfileModel.setAppType(Integer.valueOf(typeFlag.charAt(i)+""));
            appMenuNavigateService.insertBottomProfile(homeBottomProfileModel);
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;

    }

    @RequestMapping("edit")
    public ModelAndView edit(Integer id) throws Exception {
        //ID  为关联表ID
       HomeBottomProfileModel homeBottomProfileModel = appMenuNavigateService.findBottomProfileById(id);
        String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT+"";
        if(homeBottomProfileModel != null){
            GeturlRTS geturlRTS = new GeturlRTS();
            geturlRTS.setKey(homeBottomProfileModel.getIconNormalUrl());
            geturlRTS.setType(Integer.parseInt(type));
            homeBottomProfileModel.setIconNormalUrl(basicFeignService.getUrl(geturlRTS).getObjurl());

            geturlRTS.setKey(homeBottomProfileModel.getIconDownUrl());
            geturlRTS.setType(Integer.parseInt(type));
            homeBottomProfileModel.setIconDownUrl(basicFeignService.getUrl(geturlRTS).getObjurl());

        }
        modelAndView.setViewName("view/appMenuNavigate/buttomProfileEdit");
        if(homeBottomProfileModel != null){
            modelAndView.addObject("buttomProfile",homeBottomProfileModel);
            return modelAndView;
        }else{
            throw new TopjetExceptionHandler(com.topjet.common.exception.pms.ExceptionEnum.E_8.getStatus(), com.topjet.common.exception.pms.ExceptionEnum.E_8.getMsg());
        }
    }

    @ResponseBody
    @RequestMapping("update")
    public Object update(HomeBottomProfileModel homeBottomProfileModel) {
        HomeBottomProfileModel model = appMenuNavigateService.findBottomProfileById(homeBottomProfileModel.getId());
        model.setId(homeBottomProfileModel.getId());
        model.setText(homeBottomProfileModel.getText());
        model.setAppType(homeBottomProfileModel.getAppType());
        model.setTextDownColor(homeBottomProfileModel.getTextDownColor());
        model.setTextNormalColor(homeBottomProfileModel.getTextNormalColor());
        model.setOrderNum(homeBottomProfileModel.getOrderNum());
        model.setBeginDate(homeBottomProfileModel.getBeginDate());
        model.setEndDate(homeBottomProfileModel.getEndDate());
        model.setDeleted(0);
        String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT+"";
        if(homeBottomProfileModel.getIconDownUrl().startsWith("http")){
            model.setIconDownUrl(null);
        }else{
            String IconDownUrl = homeBottomProfileModel.getIconDownUrl().substring(homeBottomProfileModel.getIconDownUrl().indexOf(",")+1);
            UploadRTS uploadRTS = new UploadRTS();
            uploadRTS.setPictureData(IconDownUrl);
            uploadRTS.setType(Integer.parseInt(type));
            model.setIconDownUrl(basicFeignService.upload(uploadRTS).getStrkey());
        }
        if(homeBottomProfileModel.getIconNormalUrl().startsWith("http")){
            model.setIconNormalUrl(null);
        }else{
            String IconNormalUrl = homeBottomProfileModel.getIconNormalUrl().substring(homeBottomProfileModel.getIconNormalUrl().indexOf(",")+1);
            UploadRTS uploadRTS = new UploadRTS();
            uploadRTS.setPictureData(IconNormalUrl);
            uploadRTS.setType(Integer.parseInt(type));
            model.setIconNormalUrl(basicFeignService.upload(uploadRTS).getStrkey());
        }
        appMenuNavigateService.updateBottomProfile(model);
        msg.setStatus(com.topjet.common.exception.pms.ExceptionEnum.E_0.getStatus());
        msg.setMsg(com.topjet.common.exception.pms.ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @ResponseBody
    @RequestMapping("CenterProfileList")
    public Object CenterProfileList(HomeAmongProfileModel homeAmongProfileModel){
        PaginationBean bean = new PaginationBean();
        String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT + "";
        List<HomeAmongProfileModel> dataList = appMenuNavigateService.getCenterProfileList(homeAmongProfileModel);
        try {
            if(dataList != null && dataList.size() != 0){
                for(HomeAmongProfileModel hpm : dataList){
                    if(StringUtils.isNotBlank(hpm.getIconUrl())){
                        GeturlRTS geturlRTS = new GeturlRTS();
                        geturlRTS.setKey(hpm.getIconUrl());
                        geturlRTS.setType(Integer.parseInt(type));
                        hpm.setIconUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Integer total = appMenuNavigateService.getCenterProfileCount(homeAmongProfileModel);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    @ResponseBody
    @RequestMapping("addCenterPrifile")
    public Object addCenterPrifile(HomeAmongProfileModel homeAmongProfileModel,String typeFlag){
        homeAmongProfileModel.setDeleted(0);
        homeAmongProfileModel.setIconDownUrl("");
        if(StringUtils.isNotBlank(homeAmongProfileModel.getIconUrl())){
            UploadRTS uploadRTS = new UploadRTS();
            String type=PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT+"";
            String pictureURL = homeAmongProfileModel.getIconUrl().substring(homeAmongProfileModel.getIconUrl().indexOf(",") + 1);
            uploadRTS.setPictureData(pictureURL);
            uploadRTS.setType(Integer.parseInt(type));
            homeAmongProfileModel.setIconUrl(basicFeignService.upload(uploadRTS).getStrkey());
        }

        for(int i=0;i <typeFlag.length();i++){
            homeAmongProfileModel.setAppType(Integer.valueOf(typeFlag.charAt(i)+""));
            appMenuNavigateService.insertCenterProfile(homeAmongProfileModel);
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;

    }

    @RequestMapping("editCenterProfile")
    public ModelAndView editCenterProfile(Integer id)throws Exception {
        HomeAmongProfileModel homeAmongProfileModel = appMenuNavigateService.findCenterProfileById(id);
        String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT+"";
        if(homeAmongProfileModel != null && StringUtils.isNotBlank(homeAmongProfileModel.getIconUrl())){
            GeturlRTS geturlRTS = new GeturlRTS();
            geturlRTS.setKey(homeAmongProfileModel.getIconUrl());
            geturlRTS.setType(Integer.parseInt(type));
            homeAmongProfileModel.setIconUrl(basicFeignService.getUrl(geturlRTS).getObjurl());

        }
        modelAndView.setViewName("view/appMenuNavigate/centerProfileEdit");
        if(homeAmongProfileModel != null){
            modelAndView.addObject("centerProfile",homeAmongProfileModel);
            return modelAndView;
        }else{
            throw new TopjetExceptionHandler(com.topjet.common.exception.pms.ExceptionEnum.E_8.getStatus(), com.topjet.common.exception.pms.ExceptionEnum.E_8.getMsg());
        }

    }

    @ResponseBody
    @RequestMapping("updateCenterProfile")
    public Object updateCenterProfile(HomeAmongProfileModel homeAmongProfileModel) {
        HomeAmongProfileModel model = appMenuNavigateService.findCenterProfileById(homeAmongProfileModel.getId());
        model.setId(homeAmongProfileModel.getId());
        model.setTitle(homeAmongProfileModel.getTitle());
        model.setAppType(homeAmongProfileModel.getAppType());
        model.setLink(homeAmongProfileModel.getLink());
        model.setContent(homeAmongProfileModel.getContent());
        model.setOrderNum(homeAmongProfileModel.getOrderNum());
        model.setBeginDate(homeAmongProfileModel.getBeginDate());
        model.setEndDate(homeAmongProfileModel.getEndDate());
        model.setDeleted(0);

      String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT+"";
        if(homeAmongProfileModel.getIconUrl().startsWith("http")){
            model.setIconUrl(null);
        }else if(StringUtils.isNotBlank(homeAmongProfileModel.getIconUrl())){
            String IconUrl = homeAmongProfileModel.getIconUrl().substring(homeAmongProfileModel.getIconUrl().indexOf(",")+1);
            UploadRTS uploadRTS = new UploadRTS();
            uploadRTS.setPictureData(IconUrl);
            uploadRTS.setType(Integer.parseInt(type));
            model.setIconUrl(basicFeignService.upload(uploadRTS).getStrkey());
        }

        appMenuNavigateService.updateCenterProfile(model);
        msg.setStatus(com.topjet.common.exception.pms.ExceptionEnum.E_0.getStatus());
        msg.setMsg(com.topjet.common.exception.pms.ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @ResponseBody
    @RequestMapping("deleteCenter")
    public Object deleteCenter(HomeAmongProfileModel homeAmongProfileModel){
        homeAmongProfileModel.setId(homeAmongProfileModel.getId());
        homeAmongProfileModel.setDeleted(1);
        appMenuNavigateService.updateCenterProfile(homeAmongProfileModel);
        msg.setStatus(com.topjet.common.exception.pms.ExceptionEnum.E_0.getStatus());
        msg.setMsg(com.topjet.common.exception.pms.ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @ResponseBody
    @RequestMapping("deleteButtom")
    public Object deleteButtom(HomeBottomProfileModel homeBottomProfileModel){
        homeBottomProfileModel.setId(homeBottomProfileModel.getId());
        homeBottomProfileModel.setDeleted(1);
        appMenuNavigateService.updateBottomProfile(homeBottomProfileModel);
        msg.setStatus(com.topjet.common.exception.pms.ExceptionEnum.E_0.getStatus());
        msg.setMsg(com.topjet.common.exception.pms.ExceptionEnum.E_0.getMsg());
        return msg;
    }

}
