package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.common.ExceptionEnum;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.RegularActivitiesResponseBean;
import com.topjet.manage.domain.model.ActivityCityInfoModel;
import com.topjet.manage.domain.model.ActivityPageInfoModel;
import com.topjet.manage.domain.model.RegularActivityModel;
import com.topjet.manage.service.RegularActivityService;
import com.topjet.tool.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-08 14:54
 */

@Controller
@RequestMapping("/regularActivity")
public class RegularActivityController extends BaseController{

    @Autowired
    private BasicFeignService basicFeignService;

    @Autowired
    private RegularActivityService regularActivityService;

    /**
     * 获取定时活动列表
     * @param appType
     * @param request
     * @return
     */
    @RequestMapping("/listRegularActivity")
    @ResponseBody
    public Object listRegularActivity(Integer appType, HttpServletRequest request)throws TopjetErrorCodeException{
        PaginationBean page = new PaginationBean();
        List<RegularActivitiesResponseBean> regularActivitiesResponseBeans = regularActivityService.listRegularActivity(appType);
        page.setRows(regularActivitiesResponseBeans);
        page.setTotal(regularActivitiesResponseBeans.size());
        return page;
    }


    /**
     * 新增定时活动
     * @param regularActivityModel
     * @param typeFlag
     * @return
     */
    @RequestMapping("/addRegularActivities")
    @ResponseBody
    public ResultBaseMsg addRegularActivities(RegularActivityModel regularActivityModel,String typeFlag) throws TopjetErrorCodeException{
        regularActivityModel.setCreateBy(SessionUtils.getSysUserSession().getId());
        regularActivityModel.setCreateTime(DateUtil.now());

        //上传图片，并且获取图片URL
        UploadRTS rts = new UploadRTS();
        GeturlRTS urlRts = new GeturlRTS();
        rts.setPictureData(regularActivityModel.getPictureUrl().substring(regularActivityModel.getPictureUrl().indexOf(",")+1));
        rts.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
        String pictureKey = basicFeignService.upload(rts).getStrkey();
        urlRts.setKey(pictureKey);
        urlRts.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
        regularActivityModel.setPictureKey(pictureKey);
        regularActivityModel.setPictureUrl(basicFeignService.getUrl(urlRts).getObjurl());

        //循环保存福袋定时活动
        for (int i = 0; i< typeFlag.length(); i++) {
            regularActivityModel.setAppType(Integer.parseInt(typeFlag.charAt(i)+""));
            regularActivityService.saveRegularActivity(regularActivityModel);
        }
        return msg;
    }

    /**
     * 修改活动城市的删除状态
     * @param id
     * @return
     */
    @RequestMapping("/updateCityDeletByid")
    @ResponseBody
    public ResultBaseMsg deleteRegularActivityByid(Integer id){
        regularActivityService.deleteActivityCity(id);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setStatus(ExceptionEnum.E_0.getMsg());
        return  msg;
    }


    /**
     * 通过活动Id查询对应的活动页面
     */

    @ResponseBody
    @RequestMapping("/listShowPage")
    public PaginationBean listShowPage(Integer tid , HttpServletRequest request)throws TopjetErrorCodeException{
        List<ActivityPageInfoModel> activityPageInfoModels = regularActivityService.listShowPage(tid);
        PaginationBean page = new PaginationBean();
        page.setRows(activityPageInfoModels);
        page.setTotal(activityPageInfoModels.size());
        return page;

    }


    @RequestMapping("/updateOrInsertShowPage")
    @ResponseBody
    public Object updateOrInserShowPage(Integer id,Integer tid,Integer deleteFlag)throws TopjetErrorCodeException {
        regularActivityService.updateOrInserShowPage(id,tid,deleteFlag);
        return 1;
    }

    @RequestMapping("/listRegularCity")
    @ResponseBody
    public PaginationBean listRegularCity(Integer tid, HttpServletRequest request) throws TopjetErrorCodeException{
        List<ActivityCityInfoModel> activityCityInfoModels = regularActivityService.listActivityCity(tid);
        PaginationBean page = new PaginationBean();
        page.setRows(activityCityInfoModels);
        page.setTotal(activityCityInfoModels.size());
        return page;
    }

    @RequestMapping("insertOrUpdateCity")
    @ResponseBody
    public ResultBaseMsg insertOrUpdateCity(String cityFlag,Integer tid) throws TopjetErrorCodeException {

        String[] s = cityFlag.split(",");

        for (int i = 0; i < s.length-1; i++) {
            String[] s2 = s[i+1].split("_");
            String id =  s2[0];
            String city1 =  s2[1];

            //插入或者修改
            regularActivityService.insertOrUpdateCity(city1,Integer.parseInt(id),tid);
        }

        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());

        return msg;
    }

    /**
     * 通过活动ID查询活动详情
     * @param id
     * @return
     * @throws TopjetErrorCodeException
     */
    @RequestMapping("/queryActivityDetail")
    public ModelAndView queryActivityDetail(Integer id) throws TopjetErrorCodeException {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("view/regularActivities/DetailRegularactivities");

        RegularActivitiesResponseBean regularActivitiesResponseBean = regularActivityService.queryRegularDetail(id);

        mv.addObject("aim", regularActivitiesResponseBean);

        return mv;
    }

    /**
     * 修改
     * @param regularActivityModel
     * @throws TopjetErrorCodeException
     */
    @RequestMapping("/updateRegularActivities")
    @ResponseBody
    public ResultBaseMsg updateRegularActivities(RegularActivityModel regularActivityModel) throws TopjetErrorCodeException {

        //获取上传后的图片的url
        //上传图片，并且获取图片URL
        UploadRTS rts = new UploadRTS();
        GeturlRTS urlRts = new GeturlRTS();
        rts.setPictureData(regularActivityModel.getPictureUrl().substring(regularActivityModel.getPictureUrl().indexOf(",")+1));
        rts.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
        String pictureKey = basicFeignService.upload(rts).getStrkey();
        urlRts.setKey(pictureKey);
        urlRts.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
        regularActivityModel.setPictureKey(pictureKey);
        regularActivityModel.setPictureUrl(basicFeignService.getUrl(urlRts).getObjurl());

        //通过url拿去图片的key且设置key
        regularActivityService.updateRegularActivity(regularActivityModel);

        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());

        return msg;
    }

    /**
     * 修改活动城市的删除状态
     * @param id
     * @return
     */
    @RequestMapping("/updateRegularActivityDeletByid")
    @ResponseBody
    public ResultBaseMsg updateRegularActivityDeletByid(Integer id){
        regularActivityService.deleteRegularActivity(id);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setStatus(ExceptionEnum.E_0.getMsg());
        return  msg;
    }

}
