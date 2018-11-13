package com.topjet.manage.controller;

import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.ScoreNewSettingBean;
import com.topjet.manage.domain.model.ScoreNewSettingModel;
import com.topjet.manage.service.IntegralRuleInfoService;
import com.topjet.manage.service.ScoreNewSettingService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 *
 * @Date: 2017-10-13 9:59
 */

@Controller
@RequestMapping("/scoreNewSetting")
public class ScoreNewSettingController extends BaseController{
    private static Logger log = LoggerFactory.getLogger(ScoreNewSettingController.class);

    @Autowired
    private ScoreNewSettingService scoreNewSettingService;

    @Autowired
    private IntegralRuleInfoService integralRuleInfoService;

    @RequestMapping("/listScoreNewSetting")
    public ModelAndView listScoreNewSetting(){
        ModelAndView mv = new ModelAndView("view/scoreManage/scoreSettingList");
        mv.addObject("bsmValidList",scoreNewSettingService.listScoreNewSetting(0));
        mv.addObject("bsmInvalidList",scoreNewSettingService.listScoreNewSetting(2));
        return mv;
    }

    /**
     * 积分设置修改
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateScoreSetting")
    public Object updateScoreSetting(HttpServletRequest rq) {
        String dataList = rq.getParameter("dataList");
        JSONArray jsonArray = JSONArray.fromObject(dataList);
         for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            int id = jsonObj.getInt("id");
            int everyTimeScore = jsonObj.getInt("value");
            int scoreMax = jsonObj.getInt("topLimit");
            int controlCount = jsonObj.getInt("controlCount");
            int scoreType  = jsonObj.getInt("type");
            String ruleName = jsonObj.getString("ruleName");
            String ruleDescription = jsonObj.getString("ruleDescription");
            List<ScoreNewSettingBean> bsmList =scoreNewSettingService.listScoreNewSettings(2,scoreType);

            if(!bsmList.isEmpty()){
                // deleted=2 有记录
                ScoreNewSettingBean scoreNewSettingBean = bsmList.get(0);
                ScoreNewSettingModel scoreNewSettingModel = new ScoreNewSettingModel();
                scoreNewSettingModel.setEveryTimeScore(everyTimeScore);
                scoreNewSettingModel.setScoreMax(scoreMax);
                scoreNewSettingModel.setControlCount(controlCount);
                scoreNewSettingModel.setUpdateTime(DateUtil.now());
                scoreNewSettingModel.setVersion(scoreNewSettingBean.getVersion()+1);
                scoreNewSettingModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
                scoreNewSettingService.updateScoreNewSetting(scoreNewSettingModel);
                integralRuleInfoService.updateIntegralRuleInfo(scoreNewSettingBean.getRuleId(),ruleName,ruleDescription);

            }else {
                ScoreNewSettingModel scoreNewSettingModel = scoreNewSettingService.selectScoreNewSettingById(id);
                scoreNewSettingModel.setEveryTimeScore(everyTimeScore);
                scoreNewSettingModel.setScoreMax(scoreMax);
                scoreNewSettingModel.setControlCount(controlCount);
                scoreNewSettingModel.setCreateTime(DateUtil.now());
                scoreNewSettingModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                scoreNewSettingModel.setUpdateTime(DateUtil.now());
                scoreNewSettingModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
                scoreNewSettingModel.setVersion(1);
                scoreNewSettingModel.setDeleted(2);
                scoreNewSettingModel.setId(null);
                Integer integer = scoreNewSettingService.insertSelective(scoreNewSettingModel);
                integralRuleInfoService.updateIntegralRuleInfo(scoreNewSettingModel.getRuleId(),ruleName,ruleDescription);
            }
        }
        msg.setMsg(ExceptionEnum.E_7.getMsg());
        msg.setStatus(ExceptionEnum.E_7.getStatus());
        return msg;
    }


}
