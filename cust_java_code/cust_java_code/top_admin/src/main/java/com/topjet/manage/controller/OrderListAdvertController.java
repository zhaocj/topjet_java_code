package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.OrderListAdvertBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.OrderListAdvertModel;
import com.topjet.manage.mapper.writeMapper.OrderListAdvertModelMapper;
import com.topjet.manage.service.OrderListAdvertService;
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
 * Created by liyj on 2017/10/23.
 */
@Controller
@RequestMapping("/orderListAdvertAction/")
public class OrderListAdvertController extends BaseController{

    private final static Logger log = Logger.getLogger(OrderListAdvertController.class);

    @Autowired
    private OrderListAdvertService orderListAdvertService;
    @Autowired
    private BasicFeignService basicFeignService;
    @Autowired
    private OrderListAdvertModelMapper orderListAdvertModelMapper;


    /**
     * 货源列表广告页面
     */
    @RequestMapping("list")
    @ResponseBody
    public Object list(OrderListAdvertBean adm) {
        List<OrderListAdvertBean> dataList = orderListAdvertService.getAdvertList(adm);
        PaginationBean beans = new PaginationBean();
        int total = orderListAdvertService.getAdvertCount(adm);
        beans.setRows(dataList);
        beans.setTotal(total);
        return beans;
    }

    /**
     * 保存内容
     * @param adm
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultBaseMsg add(OrderListAdvertModel adm, String typeFlag) {
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
        adm.setPictureUrl(basicFeignService.getUrl(geturlRTS).getObjurl());

        for(int i=0;i <typeFlag.length();i++){
            List<OrderListAdvertModel> orderListAdvertModels = orderListAdvertService.findAdvertByAppType(Integer.valueOf(typeFlag.charAt(i)+""));
            int openCount=0;
            for (OrderListAdvertModel orderListAdvertModel : orderListAdvertModels) {
                if(orderListAdvertModel.getBeginDate().getTime()<DateUtil.now().getTime() && orderListAdvertModel.getEndDate().getTime()>DateUtil.now().getTime()){
                    openCount++;
                }
            }
            //若新添加广告是关闭状态，或者是有效开启广告数量小于3，或者是非有效期状态为开启的广告，则可以添加
            if(orderListAdvertModels != null && openCount<3 || adm.getValid()==0 || !(adm.getBeginDate().getTime()<DateUtil.now().getTime() && adm.getEndDate().getTime()>DateUtil.now().getTime())){
                adm.setAppType(Integer.valueOf(typeFlag.charAt(i)+""));
               orderListAdvertModelMapper.insertSelective(adm);
            }else{
                msg.setStatus(ExceptionEnum.E_33.getStatus());
                msg.setMsg(ExceptionEnum.E_33.getMsg());
                return msg;
            }
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
        mv.setViewName("view/orderListAdvert/advertisingDetail");
        OrderListAdvertModel orderListAdvertModel = orderListAdvertModelMapper.selectByPrimaryKey(id);
        mv.addObject("aim", orderListAdvertModel);
        return mv;
    }

    /**
     * 更新内容
     * @param adm
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultBaseMsg update(OrderListAdvertModel adm) {
        List<OrderListAdvertModel> orderListAdvertModels = orderListAdvertService.findAdvertByAppType(adm.getAppType());
        int openCount = 0;
        for (OrderListAdvertModel orderListAdvertModel : orderListAdvertModels) {
            if(orderListAdvertModel.getBeginDate().getTime()<DateUtil.now().getTime() && orderListAdvertModel.getEndDate().getTime()>DateUtil.now().getTime() &&orderListAdvertModel.getId()!=adm.getId()
                    && adm.getBeginDate().getTime()<DateUtil.now().getTime() && adm.getEndDate().getTime()>DateUtil.now().getTime()){
                openCount++;
            }
        }

        //广告可直接关闭，但是若想开启广告，只有当有效广告数量小于3，则可将广告开启；否则开启失败；
        if(orderListAdvertModels != null && openCount<3 || adm.getValid()==0 || !(adm.getBeginDate().getTime()<DateUtil.now().getTime() && adm.getEndDate().getTime()>DateUtil.now().getTime())){
            adm.setUpdateBy(SessionUtils.getSysUserSession().getId());
            adm.setUpdateTime(DateUtil.now());
            if (adm.getPictureKey().startsWith("http")) {
                adm.setPictureKey(null);
            } else {
                String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT + "";
                OrderListAdvertModel model  = orderListAdvertService.findOrderListAdvertById(adm.getId());
                if(!adm.getPictureKey().equals(model.getPictureKey())){
                    UploadRTS uploadRTS = new UploadRTS();
                    String pictureURL = adm.getPictureKey().substring(adm.getPictureKey().indexOf(",") + 1);
                    uploadRTS.setPictureData(pictureURL);
                    uploadRTS.setType(Integer.parseInt(type));
                    adm.setPictureKey(basicFeignService.upload(uploadRTS).getStrkey());

                    GeturlRTS geturlRTS = new GeturlRTS();
                    geturlRTS.setKey(adm.getPictureKey());
                    geturlRTS.setType(Integer.parseInt(type));
                    adm.setPictureUrl(basicFeignService.getUrl(geturlRTS).getObjurl());

                }
                else{
                    GeturlRTS geturlRTS = new GeturlRTS();
                    geturlRTS.setKey(adm.getPictureKey());
                    geturlRTS.setType(Integer.parseInt(type));
                    adm.setPictureUrl(basicFeignService.getUrl(geturlRTS).getObjurl());

                }

            }

            orderListAdvertModelMapper.updateByPrimaryKeySelective(adm);
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            msg.setStatus(ExceptionEnum.E_33.getStatus());
            msg.setMsg(ExceptionEnum.E_33.getMsg());
        }
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
        OrderListAdvertModel adm = new OrderListAdvertModel();
        adm.setId(id);
        adm.setDeleted(1);
        adm.setValid(0);
        orderListAdvertModelMapper.updateByPrimaryKeySelective(adm);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }




}
