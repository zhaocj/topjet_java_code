package com.topjet.manage.controller;

import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.common.util.SystemConfiguration;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.model.BonusSettingModel;
import com.topjet.manage.service.BonusSettingService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liyj on 2017/10/17.
 */
@Controller
@RequestMapping("/bonusSetting/")
public class BonusSettingController extends BaseController{
    private final static Logger log = Logger.getLogger(BonusSettingController.class);

    @Autowired
    private BonusSettingService bonusSettingService;

    /**
     * 补贴设置首页
     */
    @RequestMapping("bonusSettingList")
    public ModelAndView bonusSettingList() {
        ModelAndView view = new ModelAndView("view/bonusManage/bonusSettingList");
        BonusSettingModel bsm = new BonusSettingModel();
        bsm.setDeleted(0);
        List<BonusSettingModel> bsmValidList = bonusSettingService.getBounsSettingList(bsm);
        bsm.setDeleted(2);
        List<BonusSettingModel> bsmInvalidList = bonusSettingService.getBounsSettingList(bsm);
        view.addObject("bsmValidList", bsmValidList);
        view.addObject("bsmInvalidList", bsmInvalidList);
        return view;
       // SystemConfiguration.getValueFromPropFile(SystemConfiguration.CALL_SURVEY_DURATION);
    }

    /**
     * 补贴设置
     */
    @ResponseBody
    @RequestMapping("addOrUpdateBonusSetting")
    public Object updateBonusSetting(HttpServletRequest request) {
        String dataList = request.getParameter("dataList");
        JSONArray jsonArray = JSONArray.fromObject(dataList);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            int id = jsonObj.getInt("id");
            int bonusType = jsonObj.getInt("bonusType");
            List<BonusSettingModel> bsmList = bonusSettingService.getBonusSettingByBonusType(bonusType);
            if(!bsmList.isEmpty()){
                BonusSettingModel bsm = bsmList.get(0);
                BonusSettingModel bsmUpd = new BonusSettingModel();
                bsmUpd.setId(bsm.getId());
                if(jsonObj.has("everyTimeAmount")){
                    BigDecimal bg = new BigDecimal(jsonObj.getString("everyTimeAmount"));
                    bsmUpd.setEveryTimeAmount(bg);
                }
                if(jsonObj.has("bonusScopeMin")){
                    BigDecimal bg = new BigDecimal(jsonObj.getString("bonusScopeMin"));
                    bsmUpd.setBonusScopeMin(bg);
                }
                if(jsonObj.has("bonusScopeMax")){
                    BigDecimal bg = new BigDecimal(jsonObj.getString("bonusScopeMax"));
                    bsmUpd.setBonusScopeMax(bg);
                }
                bsmUpd.setVersion(bsm.getVersion()+1);
                bonusSettingService.updateBonusSetting(bsmUpd);
            }else {
                BonusSettingModel bsmNew = bonusSettingService.findBonusSettingById(id);
                if(jsonObj.has("everyTimeAmount")){
                    BigDecimal bg = new BigDecimal(jsonObj.getString("everyTimeAmount"));
                    bsmNew.setEveryTimeAmount(bg);
                }
                if(jsonObj.has("bonusScopeMin")){
                    BigDecimal bg = new BigDecimal(jsonObj.getString("bonusScopeMin"));
                    bsmNew.setBonusScopeMin(bg);
                }
                if(jsonObj.has("bonusScopeMax")){
                    BigDecimal bg = new BigDecimal(jsonObj.getString("bonusScopeMax"));
                    bsmNew.setBonusScopeMax(bg);
                }
                bsmNew.setDeleted(2);
                bsmNew.setVersion(1);
                bsmNew.setCreateTime(DateUtil.now());
                bsmNew.setCreateBy(SessionUtils.getSysUserSession().getId());
                bonusSettingService.insertBonusSetting(bsmNew);
            }
        }
        msg.setMsg(ExceptionEnum.E_7.getMsg());
        msg.setStatus(ExceptionEnum.E_7.getStatus());
        return msg;
    }


}
