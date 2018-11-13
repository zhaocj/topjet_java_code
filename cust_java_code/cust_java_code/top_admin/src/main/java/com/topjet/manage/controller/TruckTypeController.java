package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.TopjetException;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.domain.model.TruckTypeDictionaryModel;
import com.topjet.manage.mapper.readMapper.TruckTypeDictionaryModelEMapper;
import com.topjet.manage.mapper.writeMapper.TruckTypeDictionaryModelMapper;
import com.topjet.manage.service.TruckTypeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liyj on 2017/9/14.
 */
@Controller
@RequestMapping("/truckType/")
public class TruckTypeController extends BaseController {
    private final static Logger log = Logger.getLogger(TruckTypeController.class);
    @Autowired
    private TruckTypeService truckTypeService;
    @Autowired
    private BasicFeignService basicFeignService;
    @Autowired
    private TruckTypeDictionaryModelEMapper truckTypeDictionaryModelEMapper;
    @Autowired
    private TruckTypeDictionaryModelMapper truckTypeDictionaryModelMapper;


    @ResponseBody
    @RequestMapping("list")
    public Object list(TruckTypeDictionaryModel ttdv) {
        PaginationBean beans = new PaginationBean();
        if (!StringUtils.isBlank(ttdv.getDisplayName())) {
           ttdv.setDisplayName(ttdv.getDisplayName());
        }
        if (!StringUtils.isBlank(ttdv.getCode())) {
            ttdv.setCode(ttdv.getCode());
        }
        if (ttdv.getValid() != null) {
            ttdv.setValid(ttdv.getValid());
        }
        List<TruckTypeDictionaryModel> dataList = truckTypeService.getTruckTypeList(ttdv);
        /*String type = PictureServerProperties.PHOTO_TYPE_TRUCK_PHOTO +"";
        for(TruckTypeDictionaryModel truckTypeDictionaryModel : dataList){
            truckTypeDictionaryModel.setIconUrl(getImgeUrl(truckTypeDictionaryModel.getIcon(),type));
        }*/
        int total = truckTypeService.queryTruckTypeCount(ttdv);
        beans.setRows(dataList);
        beans.setTotal(total);
        return beans;

    }

    @RequestMapping("getTruckType")
    @ResponseBody
    public Object getTruckType(TruckTypeDictionaryModel truckTypeDictionaryModel) {
        List<TruckTypeDictionaryModel> datelist  = truckTypeService.getTruckType(truckTypeDictionaryModel);
        return datelist ;
    }

    @RequestMapping("checkDisplayName")
    @ResponseBody
    public Object checkDisplayName(String displayName) {
        List<TruckTypeDictionaryModel> datelist  = truckTypeService.findTruckTypeByName(displayName);
        return datelist.isEmpty();
    }

    @RequestMapping("checkCode")
    @ResponseBody
    public Object checkCode(String code) {
        List<TruckTypeDictionaryModel> datelist  = truckTypeService.findTruckTypeByCode(code);
        return datelist.isEmpty();
    }

    /**
     * 保存内容
     * @param ttd
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultBaseMsg add(TruckTypeDictionaryModel ttd)throws Exception {
        //添加车型操作
        ttd.setDisplayName(ttd.getDisplayName());
        ttd.setCode(ttd.getCode());
        ttd.setTruckOrder(ttd.getTruckOrder());
        ttd.setValid(ttd.getValid());
        ttd.setUpdateTime(DateUtil.now());
        ttd.setCreateTime(DateUtil.now());
        ttd.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        ttd.setCreateTime(DateUtil.now());
       /* String type=PictureServerProperties.PHOTO_TYPE_TRUCK_PHOTO+"";
        String iconurl = ttd.getIcon().substring(ttd.getIcon().indexOf(",") + 1);
        ttd.setIcon(getImgeKey(iconurl, type));*/
        truckTypeDictionaryModelMapper.insertType(ttd);
        updateTypeSession();
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @RequestMapping("edit")
    public ModelAndView edit(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TruckTypeDictionaryModel td = truckTypeDictionaryModelEMapper.findTruckTypeById(id);
       /* String type=PictureServerProperties.PHOTO_TYPE_TRUCK_PHOTO+"";
        td.setIconUrl(getImgeUrl(td.getIcon(),type));*/
        mv.setViewName("view/truckManage/truckTypeEdit");
        if (td != null) {
            mv.addObject("td", td);
            return mv;
        } else {
            throw new TopjetExceptionHandler(ExceptionEnum.E_8.getStatus(), ExceptionEnum.E_8.getMsg());
        }

    }

    @RequestMapping("update")
    @ResponseBody
    public ResultBaseMsg updateTruckType(TruckTypeDictionaryModel ttd,HttpServletRequest request)throws Exception  {
        //修改车型的操作
      /*  if (ttd.getIconUrl().startsWith("http")) {
            ttd.setIcon(null);
        } else {
            String type=PictureServerProperties.PHOTO_TYPE_TRUCK_PHOTO+"";
            String iconurl = ttd.getIconUrl().substring(ttd.getIconUrl().indexOf(",") + 1);
            ttd.setIcon(getImgeKey(iconurl, type));
        }*/
        updateTypeSession();
        //修改车型表
        ttd.setDisplayName(ttd.getDisplayName());
        ttd.setValid(ttd.getValid());
        ttd.setCode(ttd.getCode());
        ttd.setTruckOrder(ttd.getTruckOrder());
        ttd.setUpdateTime(DateUtil.now());
        ttd.setId(ttd.getId());
        truckTypeDictionaryModelMapper.updateType(ttd);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
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
     * 获取图片Url
     * @param key
     * @param type
     * @return
     */
    private String getImgeUrl(String key, String type) {
        String url = "";
        try {
            GeturlRTS geturlRTS = new GeturlRTS();
            geturlRTS.setKey(key);
            geturlRTS.setType(Integer.valueOf(type));
            url = basicFeignService.getUrl(geturlRTS).toString();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (TopjetException e) {
            e.printStackTrace();
        }
        return url;
    }
    /**
     * 获取图片Key
     * @param imge
     * @param type
     * @return
     */
    private String getImgeKey(String imge, String type) {
        String key = "";
        try {
            UploadRTS uploadRTS = new UploadRTS();
            uploadRTS.setPictureData(imge);
            uploadRTS.setType(Integer.valueOf(type));
            key = basicFeignService.upload(uploadRTS).toString();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }catch (TopjetException e) {
            e.printStackTrace();
        }
        return key;
    }


    private void updateTypeSession(){
        List<TruckTypeDictionaryModel>  dataList1 = truckTypeDictionaryModelEMapper.findTruckType();
        SessionUtils.removeTypeSession();
        SessionUtils.setTruckType(dataList1);
    }

}
