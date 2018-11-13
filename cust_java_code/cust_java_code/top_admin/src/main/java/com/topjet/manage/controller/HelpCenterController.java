package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.common.ExceptionEnum;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.bean.HelpCenterBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.HelpCategoryModel;
import com.topjet.manage.domain.model.HelpQuestionModel;
import com.topjet.manage.service.HelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liyj on 2017/12/6.
 */
@Controller
@RequestMapping("/helpCenter/")
public class HelpCenterController {

    @Autowired
    private HelpCenterService helpCenterService;
    @Autowired
    private BasicFeignService basicFeignService;

    @ResponseBody
    @RequestMapping("list")
    public Object list(HelpCenterBean helpCenterBean){
        PaginationBean bean= new PaginationBean();
        String nameType = "";
        if(helpCenterBean.getVersion() == 1){//发货版
            if(helpCenterBean.getName().equals("1")){
                nameType = "账号问题";
                helpCenterBean.setName(nameType);
            }else if(helpCenterBean.getName().equals("2")){
                nameType = "找车问题";
                helpCenterBean.setName(nameType);
            }else if(helpCenterBean.getName().equals("3")){
                nameType = "发货问题";
                helpCenterBean.setName(nameType);
            }else if(helpCenterBean.getName().equals("4")){
                nameType = "钱包问题";
                helpCenterBean.setName(nameType);
            }
        }
        if(helpCenterBean.getVersion() == 2){//接货版
            if(helpCenterBean.getName().equals("1")){
                nameType = "账号问题";
                helpCenterBean.setName(nameType);
            }else if(helpCenterBean.getName().equals("2")){
                nameType = "找货问题";
                helpCenterBean.setName(nameType);
            }else if(helpCenterBean.getName().equals("3")){
                nameType = "承运问题";
                helpCenterBean.setName(nameType);
            }else if(helpCenterBean.getName().equals("4")){
                nameType = "钱包问题";
                helpCenterBean.setName(nameType);
            }
        }
        List<HelpCenterBean> dataList = helpCenterService.getHelpCategoryList(helpCenterBean);
        Integer total = helpCenterService.getHelpCategoryCount(helpCenterBean);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    @RequestMapping("/addQuestion")
    @ResponseBody
    public ResultBaseMsg addQuestion(HelpCenterBean helpCenterBean, HttpServletRequest request){
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        String name = request.getParameter("name");
        Integer nameId = Integer.valueOf(name);
        String version = request.getParameter("version");
        Integer versionType = Integer.valueOf(version);
        String nameType = "";
        if(versionType == 1){//发货版
            if(nameId == 1){
                nameType = "账号问题";
                helpCenterBean.setName(nameType);
            }
            if(nameId == 2){
                nameType = "找车问题";
                helpCenterBean.setName(nameType);
            }
            if(nameId == 3){
                nameType = "发货问题";
                helpCenterBean.setName(nameType);
            }
            if(nameId == 4){
                nameType = "钱包问题";
                helpCenterBean.setName(nameType);
            }
        }
        if(versionType == 2){
            if(nameId == 1){
                nameType = "账号问题";
                helpCenterBean.setName(nameType);
            }
            if(nameId == 2){
                nameType = "找货问题";
                helpCenterBean.setName(nameType);
            }
            if(nameId == 3){
                nameType = "承运问题";
                helpCenterBean.setName(nameType);
            }
            if(nameId == 4){
                nameType = "钱包问题";
                helpCenterBean.setName(nameType);
            }
        }
        //根据问题类型，版本类型 查询 数据
        HelpCategoryModel model = helpCenterService.findByNameAndVersion(nameType,versionType);
    /*    helpCenterBean.setVersion(helpCenterBean.getVersion());
        helpCenterBean.setSortNo(helpCenterBean.getSortNo());
        //开始上次图片
        String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT + "";
        UploadRTS uploadRTS = new UploadRTS();
        String pictureURL = helpCenterBean.getIcon().substring(helpCenterBean.getIcon().indexOf(",") + 1);
        uploadRTS.setType(Integer.parseInt(type));
        uploadRTS.setPictureData(pictureURL);
        helpCenterBean.setIcon(basicFeignService.upload(uploadRTS).getStrkey());
        Integer result = helpCenterService.insertQuestion(helpCenterBean);
*/
        //添加附表
        HelpCenterBean bean = new HelpCenterBean();
        bean.setTitle(helpCenterBean.getTitle());
        bean.setContent(helpCenterBean.getContent());
        bean.setHelpCategoryID(model.getHelpCategoryID());
        bean.setSortNo(helpCenterBean.getSortNo());
        bean.setCreateTime(DateUtil.now());
        bean.setLastUpdateTime(DateUtil.now());
        bean.setVersion(helpCenterBean.getVersion());
        bean.setViewCount(0);
        Integer resultQuestion = helpCenterService.insertQuestionContent(bean);
        if(resultQuestion > 0){
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_3.getMsg());
        }
        return resultBaseMsg;

    }

    /**
     * 问题详情
     */
    @RequestMapping("questionDetail")
    public ModelAndView questionDetail(Integer helpQuestionID,Integer helpCategoryID){
        ModelAndView mv = new ModelAndView("view/helpCenter/questionEdit");
        //根据主表id查询主表信息
        HelpCategoryModel helpCategoryModel = helpCenterService.findCategoryByhelpCategoryID(helpCategoryID);
        //根据附表id查询附表信息
        HelpQuestionModel helpQuestionModel = helpCenterService.findQuestionByhelpQuestionID(helpQuestionID);
        mv.addObject("helpCategoryModel",helpCategoryModel);
        mv.addObject("helpQuestionModel",helpQuestionModel);
        return mv;
    }

    @RequestMapping("updateQuestion")
    @ResponseBody
    public Object updateQuestion(HelpCenterBean helpCenterBean,HttpServletRequest request){
        ResultBaseMsg msg = new ResultBaseMsg();
        if(helpCenterBean.getHelpCategoryID() != null && helpCenterBean.getHelpQuestionID() != null){
            /*//修改主表
            helpCenterBean.setSortNo(helpCenterBean.getSortNo());
            helpCenterBean.setName(helpCenterBean.getName());
            helpCenterBean.setHelpCategoryID(helpCenterBean.getHelpCategoryID());
            helpCenterBean.setIsVisible(0);
            //修改图片
            if (helpCenterBean.getIcon().startsWith("http")) {
                helpCenterBean.setIcon(null);
            } else {
                HelpCategoryModel model = helpCenterService.findCategoryByhelpCategoryID(helpCenterBean.getHelpCategoryID());
                String type = PictureServerProperties.PHOTO_TYPE_ADVERTISEMENT + "";
                if(!helpCenterBean.getIcon().equals(model.getIcon())){
                    UploadRTS uploadRTS = new UploadRTS();
                    String pictureURL = helpCenterBean.getIcon().substring(helpCenterBean.getIcon().indexOf(",") + 1);
                    uploadRTS.setType(Integer.parseInt(type));
                    uploadRTS.setPictureData(pictureURL);
                    helpCenterBean.setIcon(basicFeignService.upload(uploadRTS).getStrkey());

                    GeturlRTS geturlRTS = new GeturlRTS();
                    geturlRTS.setKey(helpCenterBean.getIcon());
                    geturlRTS.setType(Integer.parseInt(type));
                    helpCenterBean.setIcon(basicFeignService.getUrl(geturlRTS).getObjurl());
                }
                else{
                    GeturlRTS geturlRTS = new GeturlRTS();
                    geturlRTS.setKey(helpCenterBean.getIcon());
                    geturlRTS.setType(Integer.parseInt(type));
                    helpCenterBean.setIcon(basicFeignService.getUrl(geturlRTS).getObjurl());
                }

            }
            Integer result = helpCenterService.updateQuestion(helpCenterBean);*/
            //修改附表
            HelpCenterBean bean = new HelpCenterBean();
            bean.setTitle(helpCenterBean.getTitle());
            bean.setContent(helpCenterBean.getContent());
            bean.setCreateTime(DateUtil.now());
            bean.setLastUpdateTime(DateUtil.now());
            bean.setSortNo(helpCenterBean.getSortNo());
            bean.setHelpQuestionID(helpCenterBean.getHelpQuestionID());
            bean.setVersion(helpCenterBean.getVersion());

            Integer resultQuestion = helpCenterService.updateQuestionContent(bean);
            if(resultQuestion > 0){
                msg.setMsg(ExceptionEnum.E_0.getMsg());
                msg.setStatus(ExceptionEnum.E_0.getStatus());
            }
            else{
                msg.setStatus(ExceptionEnum.E_3.getStatus());
                msg.setMsg(ExceptionEnum.E_3.getMsg());
            }
        }
        return msg;
    }

    @RequestMapping("removeAdv")
    @ResponseBody
    public Object removeAdv(Integer helpQuestionID){
        ResultBaseMsg msg = new ResultBaseMsg();
        if(helpQuestionID == null){
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
        }
        else{
            Integer result =  helpCenterService.deleteQuestion(helpQuestionID);
            if(result > 0){
                msg.setStatus(ExceptionEnum.E_0.getStatus());
                msg.setMsg(ExceptionEnum.E_0.getMsg());
            }
            else{
                msg.setStatus(ExceptionEnum.E_3.getStatus());
                msg.setMsg(ExceptionEnum.E_3.getMsg());
            }
        }
        return msg;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(Integer helpQuestionID,Integer hpSortNo,HttpServletRequest request){
        ResultBaseMsg msg = new ResultBaseMsg();
        HelpQuestionModel helpQuestionModel = new HelpQuestionModel();
        String flag = request.getParameter("flag");
        if(flag.equals("1")){//flag = 1 开启状态  则显示关闭按钮  hpSortNo 置为0
            helpQuestionModel.setSortNo(0);
            hpSortNo = helpQuestionModel.getSortNo();
            helpCenterService.update(hpSortNo,helpQuestionID);
        }
        if(flag.equals("0")){//flag = 0 关闭状态  则显示开启按钮  hpSortNo 置为1
            helpQuestionModel.setSortNo(1);
            hpSortNo = helpQuestionModel.getSortNo();
            helpCenterService.update(hpSortNo,helpQuestionID);
        }
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }


}
