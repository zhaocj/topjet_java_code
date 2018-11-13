package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.domain.model.TruckLengthDictionaryModel;
import com.topjet.manage.mapper.readMapper.TruckLengthDictionaryModelEMapper;
import com.topjet.manage.mapper.writeMapper.TruckLengthDictionaryModelMapper;
import com.topjet.manage.service.TruckLengthService;
import com.topjet.manage.service.TruckTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyj on 2017/9/14.
 */
@Controller
@RequestMapping("/truckLength")
public class TruckLengthController extends BaseController {
    private final static Logger log = Logger.getLogger(TruckLengthController.class);

    @Autowired
    private TruckLengthService truckLengthService;
    @Autowired
    private TruckTypeService truckTypeService;
    @Autowired
    private TruckLengthDictionaryModelEMapper truckLengthDictionaryModelEMapper;
    @Autowired
    private TruckLengthDictionaryModelMapper truckLengthDictionaryModelMapper;

    ResultBaseMsg resultBaseMsg = new ResultBaseMsg();


    @RequestMapping("/list")
    @ResponseBody
    public Object list(TruckLengthDictionaryModel truckLengthDictionaryModel, HttpServletResponse response) throws Exception {
        List<TruckLengthDictionaryModel> dataList = truckLengthService.getTruckLengthList(truckLengthDictionaryModel);
        int total = truckLengthService.queryTruckLengthCount(truckLengthDictionaryModel);
        PaginationBean bean = new PaginationBean();
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    @RequestMapping("/validLength")
    @ResponseBody
    public Object validLength(String truckLength) throws Exception {
        BigDecimal bd = new BigDecimal(truckLength);
        bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        List<TruckLengthDictionaryModel> dataList = truckLengthService.findTruckLengthByLength(bd);
        return dataList.isEmpty();
    }

    @RequestMapping("/create")
    @ResponseBody
    public Object create(TruckLengthDictionaryModel truckLengthDictionaryModel, HttpServletResponse response, HttpServletRequest request) throws Exception {
        //添加车长的操作
        truckLengthDictionaryModel.setDisplayName(truckLengthDictionaryModel.getDisplayName());
        truckLengthDictionaryModel.setLength(truckLengthDictionaryModel.getLength());
        truckLengthDictionaryModel.setLengthOrder(truckLengthDictionaryModel.getLengthOrder());
        truckLengthDictionaryModel.setValid(truckLengthDictionaryModel.getValid());
        truckLengthDictionaryModel.setCreateTime(DateUtil.now());
        truckLengthDictionaryModel.setUpdateTime(DateUtil.now());
        truckLengthDictionaryModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        int rows = truckLengthDictionaryModelMapper.insertLength(truckLengthDictionaryModel);
//      truckService.updateResourceFileVersionInfo(rows);
        updateLengthSession();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TruckLengthDictionaryModel truckLengthModel = truckLengthService.findTruckLengthById(id);
        mv.setViewName("view/truckManage/truckLengthEdit");
        if (truckLengthModel != null) {
            mv.addObject("truckLengthBean", truckLengthModel);
            return mv;
        } else {
            throw new TopjetExceptionHandler(ExceptionEnum.E_8.getStatus(), ExceptionEnum.E_8.getMsg());
        }

    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(HttpServletRequest request, TruckLengthDictionaryModel truckLengthDictionaryModel) throws Exception {
            Integer id = Integer.parseInt(request.getParameter("id"));
            //修改车长的操作
            truckLengthDictionaryModel.setUpdateTime(DateUtil.now());
            truckLengthDictionaryModel.setDisplayName(truckLengthDictionaryModel.getDisplayName());
            truckLengthDictionaryModel.setLength(truckLengthDictionaryModel.getLength());
            truckLengthDictionaryModel.setValid(truckLengthDictionaryModel.getValid());
            truckLengthDictionaryModel.setId(id);
            //修改车长表
            truckLengthDictionaryModelMapper.updateByLength(truckLengthDictionaryModel);
            updateLengthSession();
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());

        return resultBaseMsg;
    }

    /**
     * 修改后更新resourceFileVersionInfo表
     * @param
     * @return
     */
    @RequestMapping("updateResource")
    @ResponseBody
    public ResultBaseMsg updateResource(ResourceFileVersionInfoModel rfvModel) {
        try{
            truckTypeService.updateResourceFileVersionInfo(rfvModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }


    /**
     * 获取车型车长方法
     *
     * @return
     */
    @RequestMapping("getTruckLength")
    @ResponseBody
    public List<Object> getTruckLength(TruckLengthDictionaryModel truckLengthDictionaryModel) {
        List<Object> dataList = new ArrayList<>();
        List<TruckLengthDictionaryModel> datalist2 = truckLengthService.getTruckLength(truckLengthDictionaryModel);
        if (!datalist2.isEmpty()) {
            dataList.add(datalist2);
        }
        return dataList;
    }


    private void updateLengthSession() {
        List<TruckLengthDictionaryModel> datalist = truckLengthDictionaryModelEMapper.findTruckLength();
        SessionUtils.removeLengthSession();
        SessionUtils.setTruckLength(datalist);
    }

}
