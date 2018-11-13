package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.cloud.manage.service.basic.bean.UploadVRU;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.AdvertisementBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.AdvertisementInfoModel;
import com.topjet.manage.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liyj on 2017/10/13.
 */
@Controller
@RequestMapping("/advertisingAction/")
public class AdvertisingController {

    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private BasicFeignService basicFeignService;

    ResultBaseMsg msg = new ResultBaseMsg();

    @RequestMapping("list")
    @ResponseBody
    public Object list(AdvertisementBean advertisementBean){
        PaginationBean bean = new PaginationBean();
        List<AdvertisementBean> dataList = advertisementService.getAdvertisementBeanList(advertisementBean);
        int total = advertisementService.getAdvertisementCount(advertisementBean);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    /**
     * 保存内容
     * @param adm
     * @return
     */


    @RequestMapping("add")
    @ResponseBody
    public ResultBaseMsg add(AdvertisementInfoModel adm, String typeFlag) {
        adm.setRedirectURL(adm.getRedirectURL());
        adm.setValid(adm.getValid());
        adm.setAdType(adm.getAdType());
        adm.setContent(adm.getContent());
        adm.setRank(adm.getRank());
        adm.setWebTitle(adm.getWebTitle());
        adm.setBeginDate(adm.getBeginDate());
        adm.setEndDate(adm.getEndDate());
        adm.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        adm.setCreateBy(SessionUtils.getSysUserSession().getId());
        adm.setCreateTime(DateUtil.now());
        UploadRTS uploadRTS = new UploadRTS();
        String type=PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT+"";
        String pictureURL = adm.getPictureKey().substring(adm.getPictureKey().indexOf(",") + 1);
        uploadRTS.setPictureData(pictureURL);
        uploadRTS.setType(Integer.parseInt(type));
        adm.setPictureKey(basicFeignService.upload(uploadRTS).getStrkey());

        GeturlRTS geturlRTS = new GeturlRTS();
        geturlRTS.setKey(adm.getPictureKey());
        geturlRTS.setType(Integer.parseInt(type));
        adm.setPictureURL(basicFeignService.getUrl(geturlRTS).getObjurl());

        for(int i=0;i <typeFlag.length();i++){
            adm.setAppType(Integer.valueOf(typeFlag.charAt(i)+""));
            advertisementService.insertAdvertisement(adm);
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    /**
     * 展示广告详情
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("advertisingDetail")
    public ModelAndView modifiedInfo(Integer id, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view/advertising/advertisingDetail");
        AdvertisementInfoModel advertisementInfoModel = advertisementService.findAdvertisementById(id);
        mv.addObject("aim", advertisementInfoModel);

        return mv;
    }

    /**
     * 更新内容
     * @param adm
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultBaseMsg update(AdvertisementInfoModel adm) {
        if(adm.getRedirectURL() == null || adm.getRedirectURL() == ""){
            adm.setRedirectURL("");
        }
        else{
            adm.setRedirectURL(adm.getRedirectURL());
        }
        adm.setValid(adm.getValid());
        adm.setContent(adm.getContent());
        adm.setRank(adm.getRank());
        adm.setWebTitle(adm.getWebTitle());
        adm.setBeginDate(adm.getBeginDate());
        adm.setEndDate(adm.getEndDate());
        adm.setUpdateBy(SessionUtils.getSysUserSession().getId());
        adm.setUpdateTime(DateUtil.now());
        adm.setId(adm.getId());
        if (adm.getPictureKey().startsWith("http")) {
            adm.setPictureKey(null);
        } else {
            AdvertisementInfoModel model = advertisementService.findAdvertisementById(adm.getId());
            String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT + "";
            if(!adm.getPictureKey().equals(model.getPictureKey())){
                UploadRTS uploadRTS = new UploadRTS();
                String pictureURL = adm.getPictureKey().substring(adm.getPictureKey().indexOf(",") + 1);
                uploadRTS.setType(Integer.parseInt(type));
                uploadRTS.setPictureData(pictureURL);
                adm.setPictureKey(basicFeignService.upload(uploadRTS).getStrkey());

                GeturlRTS geturlRTS = new GeturlRTS();
                geturlRTS.setKey(adm.getPictureKey());
                geturlRTS.setType(Integer.parseInt(type));
                adm.setPictureURL(basicFeignService.getUrl(geturlRTS).getObjurl());
            }
            else{
                GeturlRTS geturlRTS = new GeturlRTS();
                geturlRTS.setKey(adm.getPictureKey());
                geturlRTS.setType(Integer.parseInt(type));
                adm.setPictureURL(basicFeignService.getUrl(geturlRTS).getObjurl());
            }

        }
        advertisementService.updateAdvertisement(adm);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    /**
     * 删除广告
     * @param id
     * @return
     */
    @RequestMapping("removeAdv")
    @ResponseBody
    public Object removeAdv(Integer id) throws Exception {
        AdvertisementInfoModel adm = new AdvertisementInfoModel();
        adm.setId(id);
        adm.setDeleted(1);
        adm.setValid(0);
        advertisementService.updateAdvertisement(adm);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }


}
