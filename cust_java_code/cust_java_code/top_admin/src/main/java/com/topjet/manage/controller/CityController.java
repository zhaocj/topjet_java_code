package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.util.CityUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.CityBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.CityModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.mapper.readMapper.CityModelEMapper;
import com.topjet.manage.service.CityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liyj on 2017/9/15.
 */
@Controller
@RequestMapping("/cityAction")
public class CityController extends BaseController{
    private final static Logger log = Logger.getLogger(CityController.class);

    @Autowired
    private CityService cityService;
    @Autowired
    private CityModelEMapper cityModelEMapper;
   /* @Autowired
    private CityInfoService cityInfoService;*/

    ResultBaseMsg resultBaseMsg = new ResultBaseMsg();

    @RequestMapping("/list")
    @ResponseBody
    public Object dataList(CityBean cityBean, HttpServletResponse response) throws Exception {
        log.error("============================================");
        PaginationBean bean = new PaginationBean();
        Integer parentId = CityUtil.convertToInteger(cityBean.getProvince());
        Integer parentId1 = CityUtil.convertToInteger(cityBean.getCity());;
        Integer adcode = CityUtil.convertToInteger(cityBean.getDist());
        cityBean.setParentId(parentId);
        cityBean.setParentId1(parentId1);
        cityBean.setCityId(adcode);
        List<CityModel> dataList = cityService.getCityAll(cityBean);
        Integer total = cityService.getCityCount(cityBean);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    @RequestMapping("/create")
    @ResponseBody
    public Object create(CityBean cityBean, HttpServletResponse response, HttpServletRequest request) throws Exception {
        int rows = cityService.createCity(cityBean);
        //cityInfoService.updateResourceFileVersionInfo(rows);
        //updateResource();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;

    }

    @RequestMapping("/getProvince")
    @ResponseBody
    public Object getProvince(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String level = request.getParameter("level");
        Integer IntLevel = Integer.valueOf(level);
        List<CityModel> cityList = cityService.getProvince(IntLevel);
        return cityList;
    }

    @RequestMapping("/getCity")
    @ResponseBody
    public Object getCity(HttpServletResponse response, HttpServletRequest request) throws Exception {
        log.error("============================================");
        String adcode = request.getParameter("adcode");
        Integer IntAdcode = Integer.valueOf(adcode);
        List<CityModel> cityList = cityService.findCity(IntAdcode);
        return cityList;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        Integer id = Integer.parseInt(request.getParameter("id"));
        CityBean cityBean = cityService.getCityInfo(id);
        mv.setViewName("view/city/cityEdit");
        if (cityBean != null) {
            mv.addObject("cityBean", cityBean);
            return mv;
        } else {
            throw new TopjetExceptionHandler(ExceptionEnum.E_8.getStatus(), ExceptionEnum.E_8.getMsg());
        }

    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(CityBean cityBean) throws Exception {
        int rows=cityService.update(cityBean);
        //cityInfoService.updateResourceFileVersionInfo(rows);
        // updateResource();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }

    @RequestMapping("updateResource")
    @ResponseBody
    public ResultBaseMsg updateResource(ResourceFileVersionInfoModel rfvModel) {
        try{
            cityService.updateResourceFileVersionInfo(rfvModel.getResourceType());
        }catch (Exception e){
            e.printStackTrace();
        }
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }



}
