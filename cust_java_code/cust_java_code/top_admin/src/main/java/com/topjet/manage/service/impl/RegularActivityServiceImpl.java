package com.topjet.manage.service.impl;

import com.topjet.basic.BasicFeignService;
import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.RegularActivitiesResponseBean;
import com.topjet.manage.domain.model.ActivityCityInfoModel;
import com.topjet.manage.domain.model.ActivityPageInfoModel;
import com.topjet.manage.domain.model.RegularActivityModel;
import com.topjet.manage.mapper.readMapper.RegularActivityModelEMapper;
import com.topjet.manage.mapper.writeMapper.RegularActivityModelMapper;
import com.topjet.manage.service.CityService;
import com.topjet.manage.service.RegularActivityService;
import com.topjet.tool.common.util.CityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-08 14:51
 */

@Transactional
@Service
public class RegularActivityServiceImpl implements RegularActivityService{

    @Autowired
    private RegularActivityModelEMapper regularActivityModelEMapper;
    @Autowired
    private BasicFeignService basicFeignService;
    @Autowired
    private CityService cityService;

    @Autowired
    private RegularActivityModelMapper regularActivityModelMapper;

    private Map<Integer,String> pageMap = new HashMap<Integer,String>();

    //发货版
    private int pageFlag1 = 0;
    //接货版
    private int pageFlag2 = 0;

    /**
     * 获取红包定时服务列表
     * @param appType
     * @return
     */
    @Override
    public List<RegularActivitiesResponseBean> listRegularActivity(Integer appType) {

        List<RegularActivitiesResponseBean> resultList= new ArrayList<RegularActivitiesResponseBean>();
        List<RegularActivityModel> regularActivityModels = regularActivityModelEMapper.listRegularActivity(appType, CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        if (regularActivityModels != null && regularActivityModels.size()>0){
            for (RegularActivityModel regularActivityModel : regularActivityModels) {
                RegularActivitiesResponseBean responseBean = new RegularActivitiesResponseBean();
                responseBean.setId(regularActivityModel.getId());
                responseBean.setAppType(regularActivityModel.getAppType());
                responseBean.setBeginDate(regularActivityModel.getBeginDate());
                responseBean.setEndDate(regularActivityModel.getEndDate());
                responseBean.setRedirectURL(regularActivityModel.getRedirectURL());
                responseBean.setTitle(regularActivityModel.getTitle());
                responseBean.setIconBenginDate(regularActivityModel.getIconBenginDate());
                responseBean.setIconEndDate(regularActivityModel.getIconEndDate());
                responseBean.setPictureUrl(regularActivityModel.getPictureUrl());
                responseBean.setCity(getCity(regularActivityModel.getId()));
                responseBean.setShowPage(getShowPage(regularActivityModel.getId()));
                resultList.add(responseBean);
            }
        }

        return resultList;
    }

    @Override
    public int saveRegularActivity(RegularActivityModel regularActivityModel) {
        return regularActivityModelMapper.insert(regularActivityModel);
    }

    @Override
    public void deleteActivityCity(Integer id) {
        regularActivityModelMapper.updateActivityCityStatus(id,CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
    }

    /**
     * 获取活动页面
     * @param regularActivityId
     * @return
     */
    @Override
    public List<ActivityPageInfoModel> listShowPage(Integer regularActivityId) {
        return regularActivityModelEMapper.listActivityPageByActivityId(regularActivityId,CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
    }

    /**
     * 新增或修改活动页面
     * @param id
     * @param tid
     * @param deleteflag
     * @throws TopjetErrorCodeException
     */
    @Override
    public void updateOrInserShowPage(Integer id, Integer tid, Integer deleteflag) throws TopjetErrorCodeException {
        List<ActivityPageInfoModel> activityPageInfoModels = regularActivityModelEMapper.listActivityPageByPageId(tid, id);
        if(activityPageInfoModels != null && activityPageInfoModels.size()>0){
            ActivityPageInfoModel pageInfoModel = activityPageInfoModels.get(0);
            pageInfoModel.setDeleted(deleteflag);
            regularActivityModelMapper.updatePageByPrimaryKey(pageInfoModel);
        }else{
            ActivityPageInfoModel pageInfoModel = new ActivityPageInfoModel();
            pageInfoModel.setRegularActivityId(tid);
            pageInfoModel.setShowPage(id);
            pageInfoModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            regularActivityModelMapper.insertShowPage(pageInfoModel);
        }

    }

    @Override
    public List<ActivityCityInfoModel> listActivityCity(Integer regularActivityId) throws TopjetErrorCodeException {
        return regularActivityModelEMapper.listActivityCityByActivityId(regularActivityId,CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
    }

    @Override
    public void insertOrUpdateCity(String cityCode, Integer id, Integer tid) throws TopjetErrorCodeException {
        if ( id <= 0){

            List<ActivityCityInfoModel> list= regularActivityModelEMapper.listActivityCity(CityUtil.convertToInteger(CityUtil.convertToString(Integer.parseInt(cityCode))),tid);

            //如果存在相同的活动地区则更改其删除状态
            if(list.size()>0 && list !=null){

                ActivityCityInfoModel model1 = list.get(0);
                regularActivityModelMapper.updateActivityCityStatus(model1.getId(),CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            }else{
                //插入
                ActivityCityInfoModel   model = new ActivityCityInfoModel();
                model.setRegularActivityId(tid);
                model.setCityCode(cityCode);
                model.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
                regularActivityModelMapper.insertActivityCity(model);
            }
        }else{
            //修改
            ActivityCityInfoModel model = regularActivityModelEMapper.selectActivityCityByPrimaryKey(id);
            model.setId(id);
            model.setCityCode(cityCode);
            regularActivityModelMapper.updateCityByPrimaryKey(model);
        }
    }

    @Override
    public RegularActivitiesResponseBean queryRegularDetail(Integer id) throws TopjetErrorCodeException {
        RegularActivitiesResponseBean responseBean = new RegularActivitiesResponseBean();
        RegularActivityModel regularActivityModel = regularActivityModelEMapper.selectRegularActivityByPrimaryKey(id);
        responseBean.setAppType(regularActivityModel.getAppType());
        responseBean.setId(regularActivityModel.getId());
        responseBean.setTitle(regularActivityModel.getTitle());
        responseBean.setRedirectURL(regularActivityModel.getRedirectURL());
        responseBean.setPictureUrl(regularActivityModel.getPictureUrl());
        responseBean.setBeginDate(regularActivityModel.getBeginDate());
        responseBean.setEndDate(regularActivityModel.getEndDate());
        responseBean.setCity(getCity(regularActivityModel.getId()));
        responseBean.setShowPage(regularActivityModel.getId()+"");
//        responseBean.setIconBenginDate(regularActivityModel.getIconBenginDate());
//        responseBean.setIconEndDate(regularActivityModel.getIconEndDate());

        return responseBean;
    }

    @Override
    public void updateRegularActivity(RegularActivityModel regularActivityModel) {
        regularActivityModelMapper.update(regularActivityModel);
    }

    @Override
    public void deleteRegularActivity(Integer id) {
        regularActivityModelMapper.updateRegularActivity(id,CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
    }

    private String getCity(Integer regularActivityId){
        List<ActivityCityInfoModel> activityCityInfoModels = regularActivityModelEMapper.listActivityCityByActivityId(regularActivityId, CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        StringBuffer stringBuffer = new StringBuffer();
        for (ActivityCityInfoModel activityCityInfoModel : activityCityInfoModels) {
            stringBuffer.append(activityCityInfoModel.getCityCode().equals("0")?"全国、": cityLV(Integer.valueOf(activityCityInfoModel.getCityCode()))+"、");
        }
        return stringBuffer.toString();
    }


    private String getShowPage(Integer regularActivityId){
        List<ActivityPageInfoModel> activityPageInfoModels = regularActivityModelEMapper.listActivityPageByActivityId(regularActivityId, CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < activityPageInfoModels.size(); i++) {
            if(activityPageInfoModels.get(i).getShowPage()<=5){
                if(pageFlag1 == 0){
                    stringBuffer.append("【发货版：】"+"\n"+"  ");
                    pageFlag1 = 1;
                }
            }else{
                if(pageFlag2 == 0){
                    stringBuffer.append("\n");
                    stringBuffer.append("【接货版：】"+"\n"+"   ");
                    pageFlag2 = 1;
                }
            }
            stringBuffer.append(pageLV(activityPageInfoModels.get(i).getShowPage())+"、");
        }
        pageFlag1=0;
        pageFlag2=0;

        return stringBuffer.toString();

    }

    private String pageLV(Integer id){

        if(pageMap.size()<=0){
            pageMap.put(1,"首页");
            pageMap.put(2,"我的订单");
            pageMap.put(3,"附近车源（地图）");
            pageMap.put(4,"我要用车");
            pageMap.put(5,"个人中心页");
            pageMap.put(6,"首页");
            pageMap.put(7,"智能找货页");
            pageMap.put(8,"我的订单页");
            pageMap.put(9,"附近货源页（地图）");
            pageMap.put(10,"附近货源（列表）");
            pageMap.put(11,"个人中心页");
        }
//        请求类型 【发货版：】 1：首页、2：我的订单、3：附近货源（地图）、4：附近货源（列表）、5：个人中心 、【接货版：】6：首页、7：智能找货页、8：我的订单页、9：附近车源页（地图）、10附近货源（列表），11：个人中心页
        return pageMap.get(id);
    }

    /**
     * 城市code码转成地名
     * @param cityCode
     * @return
     */
    private String cityLV(Integer cityCode){
        //根据城市编码获取三级地名
        List<String> city = cityService.getCity(CityUtil.convertToString(cityCode));
        String registeredAddress1 = cityService.getCityAddress1(city);
        String registeredAddress2 = cityService.getCityAddress2(city);
        String registeredAddress3 = cityService.getCityAddress3(city);
        //转换地名
        return  CityUtil.cityNameFormat(registeredAddress1,registeredAddress2,registeredAddress3);
    }
}
