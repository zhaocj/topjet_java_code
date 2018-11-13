package com.topjet.manage.controller;

import com.topjet.common.util.CityUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.model.CityModel;
import com.topjet.manage.service.CityService;
import com.topjet.user.constant.CityConstant;
import com.topjet.util.FileExportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyj on 2017/9/18.
 */
@Controller
@RequestMapping("/cityExport/")
public class CityExportController extends BaseController {
    @Resource
    private CityService cityService;

    @ResponseBody
    @RequestMapping("cityExportJson")
    public Object cityExportJson(String type){
        String content="";
        String fileName="";
        CityModel cityModel = new CityModel();
       if(type.equals(CityConstant.CITY_JSON_TYPE_IOS)){
            List<CityModel> list1=cityService.findCityLevel1(cityModel);

            List<CityModel> list2=cityService.findCityLevel2(cityModel);

            List<CityModel> list3=cityService.findCityLevel3(cityModel);

           List<CityBeanProvIos> prov=new ArrayList<CityBeanProvIos>();

             for (CityModel bean : list1) {
                CityBeanProvIos provBean=new CityBeanProvIos();
                provBean.setAdcode(CityUtil.convertToString(bean.getAdcode()));
                provBean.setCityName(bean.getCityName());
               // provBean.setWeatherId(bean.getWeatherId());
                provBean.setCitycode(bean.getCitycode());
              //  provBean.setFlag(bean.getFlag()+"");
                provBean.setCityFullName(bean.getCityFullName());
                provBean.setLatitude(bean.getLatitude()+"");
                provBean.setLongitude(bean.getLongitude()+"");
                List<CityBeanCityIos> city=new ArrayList<CityBeanCityIos>();
               for (CityModel bean2 : list2) {
                    if(CityUtil.convertToString(bean2.getParentId()).equals(provBean.getAdcode())){
                        CityBeanCityIos cityBean=new CityBeanCityIos();
                        cityBean.setAdcode(CityUtil.convertToString((bean2.getAdcode())));
                        cityBean.setCityName(bean2.getCityName());
                       // cityBean.setWeatherId(bean2.getWeatherId());
                        cityBean.setCitycode(bean2.getCitycode());
                        //cityBean.setFlag(bean2.getFlag()+"");
                        cityBean.setCityFullName(bean2.getCityFullName());
                        cityBean.setLatitude(bean2.getLatitude()+"");
                        cityBean.setLongitude(bean2.getLongitude()+"");
                        List<CityBean> dist=new ArrayList<CityBean>();
                         for (CityModel bean3 : list3) {
                            if(CityUtil.convertToString(bean3.getParentId()).equals(cityBean.getAdcode())){
                                CityBean distBean=new CityBean();
                                distBean.setAdcode(bean3.getAdcode());
                                distBean.setCityName(bean3.getCityName());
                                //distBean.setWeatherId(bean3.getWeatherId());
                                distBean.setCitycode(bean3.getCitycode());
                                distBean.setFlag(Integer.valueOf(bean3.getFlag()+""));
                                distBean.setCityFullName(bean3.getCityFullName());
                                BigDecimal bg = new BigDecimal(bean3.getLatitude()+"");
                                bg = bg.setScale(2,BigDecimal.ROUND_HALF_UP);
                                distBean.setLatitude(bg);
                                BigDecimal bd = new BigDecimal(bean3.getLongitude()+"");
                                bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                                distBean.setLongitude(bd);
                                dist.add(distBean);
                            }
                        }
                       // cityBean.setDist(dist);
                        city.add(cityBean);
                    }
                }
                provBean.setCity(city);
                prov.add(provBean);

            }
           // content=JsonUtil.toJSON(prov);//JsonUtils.togJSOnString(prov);
            fileName=CityConstant.CITY_EXPORT_JSON_FILENAME_IOS;
        }else if(type.equals(CityConstant.CITY_JSON_TYPE_ANDROID)){
             List<CityBean> cityList=new ArrayList<CityBean>();

            List<CityModel> list=cityService.findCityList(cityModel);
           for (CityModel bean : list) {
                CityBean cityBean=new CityBean();
                cityBean.setAdcode(bean.getAdcode());
                cityBean.setCityName(bean.getCityName());
                //cityBean.setWeatherId(bean.getWeatherId());
                cityBean.setCitycode(bean.getCitycode());
                cityBean.setFlag(Integer.valueOf(bean.getFlag()+""));
                cityBean.setCityFullName(bean.getCityFullName());
                BigDecimal bg = new BigDecimal(bean.getLatitude()+"");
                bg = bg.setScale(2,BigDecimal.ROUND_HALF_UP);
                cityBean.setLatitude(bg);
                BigDecimal bd = new BigDecimal(bean.getLongitude()+"");
                bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                cityBean.setLongitude(bd);
                cityList.add(cityBean);
            }

           // content=JsonUtil.toJSON(cityList);//JsonUtils.togJSOnString(cityList);
            fileName=CityConstant.CITY_EXPORT_JSON_FILENAME_ANDROID;
        }else if(type.equals(CityConstant.CITY_JSON_TYPE_BACK)){
            List<CityModel>list1=cityService.findCityLevel1(cityModel);

            List<CityModel> list2=cityService.findCityLevel2(cityModel);

            List<CityModel> list3=cityService.findCityLevel3(cityModel);

         List<CityBeanProvBack> prov=new ArrayList<CityBeanProvBack>();

            CityBeanProvBack guo=new CityBeanProvBack();
            guo.setP("全国");
            guo.setAdcode("");
            prov.add(guo);
               for (CityModel bean : list1) {
                CityBeanProvBack provBean=new CityBeanProvBack();
                provBean.setP(bean.getCityName());
                provBean.setAdcode(CityUtil.convertToString(bean.getAdcode()));
                provBean.setCityFullName(bean.getCityFullName());
                provBean.setLongitude(bean.getLongitude()+"");
                provBean.setLatitude(bean.getLatitude()+"");
                List<CityBeanCityBack> city=new ArrayList<CityBeanCityBack>();
                CityBeanCityBack selectCity=new CityBeanCityBack();
                selectCity.setN("请选择");
                selectCity.setAdcode("");
                city.add(selectCity);
             for (CityModel bean2 : list2) {
                    if(CityUtil.convertToString(bean2.getParentId()).equals(provBean.getAdcode())){
                        CityBeanCityBack cityBean=new CityBeanCityBack();
                        cityBean.setN(bean2.getCityName());
                        cityBean.setCityFullName(bean2.getCityFullName());
                        cityBean.setLongitude(bean2.getLongitude()+"");
                        cityBean.setLatitude(bean2.getLatitude()+"");
                        cityBean.setAdcode(CityUtil.convertToString(bean2.getAdcode()));
                        List<CityBeanDistBack> dist=new ArrayList<CityBeanDistBack>();
                        CityBeanDistBack selectDist=new CityBeanDistBack();
                        selectDist.setS("请选择");
                        selectDist.setAdcode("");
                        dist.add(selectDist);
                         for (CityModel bean3 : list3) {
                            if(CityUtil.convertToString(bean3.getParentId()).equals(cityBean.getAdcode())){
                                CityBeanDistBack distBean=new CityBeanDistBack();
                                distBean.setCityFullName(bean3.getCityFullName());
                                distBean.setLongitude(bean3.getLongitude()+"");
                                distBean.setLatitude(bean3.getLatitude()+"");
                                distBean.setS(bean3.getCityName());
                                distBean.setAdcode(CityUtil.convertToString(bean3.getAdcode()));
                                dist.add(distBean);
                            }
                        }
                        cityBean.setA(dist);
                        city.add(cityBean);
                    }
                }
                provBean.setC(city);
                prov.add(provBean);
            }
            CityBeanListBack listBack=new CityBeanListBack();
            listBack.setCitylist(prov);
          //  content=JsonUtil.toJSON(listBack);//JsonUtils.togJSOnString(listBack);
            fileName=CityConstant.CITY_EXPORT_JSON_FILENAME_BACK;
            FileExportUtil.fileExport(CityConstant.CITY_EXPORT_JSON_PROJECT_PATH, CityConstant.CITY_EXPORT_JSON_FILENAME_BACK_PROJECT, content);

        }
        FileExportUtil.fileExport(CityConstant.CITY_EXPORT_JSON_PATH, fileName, content);
        return msg;
    }
}

