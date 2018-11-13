package com.topjet.manage.service.impl;

import com.topjet.common.SessionUtils;
import com.topjet.common.constants.BillConstant;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.BonusAuditStatus;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.domain.bean.RecommendationBonusBean;
import com.topjet.manage.domain.bean.RecommendationBonusResponseBean;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.mapper.readMapper.RecommendationBonusInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.RecommendationBonusInfoModelMapper;
import com.topjet.manage.service.*;
import com.topjet.user.constant.AuditHistoryConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:推荐补贴service
 * @Date: 2017-08-28 10:38
 */

@Service
@Transactional
public class RecommendationBonusInfoServiceImpl implements RecommendationBonusInfoService{

    private static Logger log = LoggerFactory.getLogger(RecommendationBonusInfoServiceImpl.class);

    @Autowired
    private RecommendationBonusInfoModelMapper recommendationBonusInfoModelMapper;

    @Autowired
    private RecommendationBonusInfoModelEMapper recommendationBonusInfoModelEMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserAuditHistoryService userAuditHistoryService;

    @Autowired
    private RecommendationBonusInfoSettingService recommendationBonusInfoSettingService;

    @Autowired
    private BonusBillInfoService bonusBillInfoService;

    @Autowired
    private TaskAssignService taskAssignService;

    @Override
    public List<RecommendationBonusBean> listRecommendationBonusInfo(RecommendationBonusBean recommendationBonusBean) {
        if(recommendationBonusBean.getAuditCycle()==1){
            return recommendationBonusInfoModelEMapper.listRecommendationBonusInfo(recommendationBonusBean);
        }else{
            return recommendationBonusInfoModelEMapper.listRecommendationBonusInfos(recommendationBonusBean);
        }

    }

    @Override
    public int queryByCount(RecommendationBonusBean recommendationBonusBean) {
        if(recommendationBonusBean.getAuditCycle()==1){
            return recommendationBonusInfoModelEMapper.countRecommendationBonusInfo(recommendationBonusBean);
        }else{
            return recommendationBonusInfoModelEMapper.countRecommendationBonusInfos(recommendationBonusBean);
        }
    }

    /**
     *
     * @param recommendationBonusBean
     * @return
     * @throws TopjetExceptionHandler
     * @description 推荐费补贴审核service方法
     */
    @Override
    public RecommendationBonusResponseBean audit(RecommendationBonusBean recommendationBonusBean) throws TopjetExceptionHandler {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        RecommendationBonusResponseBean returnRecommendationBonusBean = new RecommendationBonusResponseBean();
        SysUserModel sysUserModel = sysUserService.selectByPrimaryKey(SessionUtils.getSysUserSession().getId());

        RecommendationBonusInfoModel recommendationBonusInfoModel = this.selectByPrimaryKey(recommendationBonusBean.getId());
        if (recommendationBonusInfoModel != null) {
            int recoId = recommendationBonusInfoModel.getSettingId();
            recommendationBonusInfoModel.setVersion(recommendationBonusInfoModel.getVersion() + 1);

            //firstAuditStatus不为空时，为推荐补贴一审，此时修改审核进程为2
            if (!StringUtils.isEmpty(recommendationBonusBean.getFirstAuditStatus())) {
                recommendationBonusInfoModel.setAuditProcess(2);
                recommendationBonusInfoModel.setAuditStatus(Integer.valueOf(recommendationBonusBean.getFirstAuditStatus()));
                returnRecommendationBonusBean.setRow(this.updateByPrimaryKey(recommendationBonusInfoModel));
                try {
                    List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
                    if (!taskUser.isEmpty()) {
                        taskAssignService.updateTasks(taskUser.get(0).getSysUserId(), TaskConstants.RECO_FIRST_AUDIT, recommendationBonusInfoModel.getId());
                    } else {
                        taskAssignService.updateTasks(0, TaskConstants.RECO_FIRST_AUDIT, recommendationBonusInfoModel.getId());
                    }
                } catch (Exception e) {
                    log.error("更新推荐补贴一审审核任务数量异常:" + e.getMessage() + e.getClass().getName() + "recommendationBonusInfoModel ID:" + recommendationBonusInfoModel.getId());
                }

                //保存审核记录到userAuditHistory表中
                UserAuditHistoryModel userAuditHistoryModel = new UserAuditHistoryModel();
                userAuditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_FIRST_RECOMMENDATION_BONUS);
                userAuditHistoryModel.setSourceId(recommendationBonusBean.getId());
                if(Integer.valueOf(recommendationBonusBean.getFirstAuditStatus())==1){
                    userAuditHistoryModel.setStatusAfter(AuditHistoryConstant.RECOMMENDATION_PASS);//一审通过
                }else if(Integer.valueOf(recommendationBonusBean.getFirstAuditStatus())==3){
                    userAuditHistoryModel.setStatusAfter(AuditHistoryConstant.RECOMMENDATION_USELES);//无效推荐
                }else{
                    userAuditHistoryModel.setStatusAfter(AuditHistoryConstant.RECOMMENDATION_PAY_THREE);// 假一罚三
                }
                userAuditHistoryModel.setCreateTime(DateUtil.now());
                userAuditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
                userAuditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                userAuditHistoryModel.setRemark(recommendationBonusBean.getFirstAuditRemark());
                userAuditHistoryService.insert(userAuditHistoryModel);
                return returnRecommendationBonusBean;
            }
            //推荐补贴二审
            if (!StringUtils.isEmpty(recommendationBonusBean.getSecondAuditStatus())) {
                try {
                    recommendationBonusInfoModel.setAuditProcess(3);
                    recommendationBonusInfoModel.setAuditStatus(Integer.valueOf(recommendationBonusBean.getSecondAuditStatus()));
                    returnRecommendationBonusBean.setRow(this.updateByPrimaryKey(recommendationBonusInfoModel));


                    //审核历史记录UserAuditHistory表
                    UserAuditHistoryModel userAuditHistoryModel = new UserAuditHistoryModel();
                    userAuditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_SECOND_RECOMMENDATION_BONUS);
                    userAuditHistoryModel.setSourceId(recommendationBonusBean.getId());
                    if(Integer.valueOf(recommendationBonusBean.getSecondAuditStatus())==4){
                        userAuditHistoryModel.setStatusAfter(AuditHistoryConstant.RECOMMENDATION_PASS);//二审通过
                    }else if(Integer.valueOf(recommendationBonusBean.getSecondAuditStatus())==5){
                        userAuditHistoryModel.setStatusAfter(AuditHistoryConstant.RECOMMENDATION_NOT_CONFIRM);//待定
                    }
                    userAuditHistoryModel.setCreateTime(DateUtil.now());
                    userAuditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
                    userAuditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                    userAuditHistoryModel.setRemark(recommendationBonusBean.getSecondAuditRemark());
                    userAuditHistoryService.insert(userAuditHistoryModel);
                    try {
                        List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
                        if (!taskUser.isEmpty()) {
                            taskAssignService.updateTasks(taskUser.get(0).getSysUserId(), TaskConstants.RECO_SECOND_AUDIT, recommendationBonusInfoModel.getId());
                        } else {
                            taskAssignService.updateTasks(0, TaskConstants.RECO_SECOND_AUDIT, recommendationBonusInfoModel.getId());
                        }
                    } catch (Exception e) {
                        log.error("更新推荐补贴二审审核任务数量异常:" + e.getMessage() + e.getClass().getName() + "recommendationBonusInfoModel ID:" + recommendationBonusInfoModel.getId());
                    }

                    // 推荐费补贴二审生成账单
                    if (recommendationBonusBean.getSecondAuditStatus().equals(BonusAuditStatus.BONUSSAUDITSTATUS_SECOND_PASS.toString())) {
//                        BillInfoModel billInfoModel = billInfoService.getBillInfoModelInfo(recommendationBonusInfoModel);
                        //查询当天推荐补贴账单数据，判断当天有没有生成账单，若已生成账单，则修改账单金额，否则新建当天推荐补贴子账单及父账单
                        paramMap.put("payeeId",recommendationBonusInfoModel.getUserId());
                        paramMap.put("sourceId",recommendationBonusInfoModel.getId());
                        BonusBillInfoModel billInfoModelInfo = bonusBillInfoService.getBonusBillInfo(paramMap);
                        RecommendationBonusSettingModel reco = recommendationBonusInfoSettingService.selectByPrimaryKey(recoId);

                        BigDecimal amountPerPerson = reco.getAmountPerPerson();

                        if (billInfoModelInfo != null) {
                            billInfoModelInfo.setUpdateTime(DateUtil.now());
                            billInfoModelInfo.setUpdateBy(SessionUtils.getSysUserSession().getId());
                            BigDecimal currAllNum = billInfoModelInfo.getAmount();
                            billInfoModelInfo.setAmount(currAllNum.add(amountPerPerson));
                            //修改子账单金额
                            bonusBillInfoService.updateBillInfoForRecommendation(billInfoModelInfo);

                            //修改父账单金额
                            BonusBillInfoModel bonusBillInfoModel = bonusBillInfoService.selectByPrimaryKey(billInfoModelInfo.getParentId());
                            bonusBillInfoModel.setAmount(bonusBillInfoModel.getAmount().add(amountPerPerson));
                            bonusBillInfoService.updateBillInfoForRecommendation(bonusBillInfoModel);
                        } else {
                            BonusBillInfoModel billInfoModelInfos = new BonusBillInfoModel();
                            billInfoModelInfos.setUpdateTime(DateUtil.now());
                            billInfoModelInfos.setUpdateBy(SessionUtils.getSysUserSession().getId());
                            billInfoModelInfos.setBillNo(bonusBillInfoService.getBillNo(1));
                            billInfoModelInfos.setStatus(BillConstant.BILL_STATUS_NOT_PAID);
                            billInfoModelInfos.setSourceId(recommendationBonusInfoModel.getId());
                            billInfoModelInfos.setCreateTime(DateUtil.now());
                            billInfoModelInfos.setDeleted(0);
                            billInfoModelInfos.setCreateBy(SessionUtils.getSysUserSession().getId());
                            billInfoModelInfos.setBillType(BillConstant.BILL_TYPE_RECOMMAND_BONUS);
                            billInfoModelInfos.setPayeeId(recommendationBonusInfoModel.getUserId());
                            billInfoModelInfos.setAmount(amountPerPerson);
                            billInfoModelInfos.setParentId(0);
//                            logRedisService.saveBillInfoLog(billInfoModel, LogConstant.OPT_ADD);
                            int i = bonusBillInfoService.insertSelective(billInfoModelInfos);
                            billInfoModelInfos.setParentId(i);
                            billInfoModelInfos.setBillNo(bonusBillInfoService.getBillNo(2));
                            billInfoModelInfos.setId(null);
                            bonusBillInfoService.insertSelective(billInfoModelInfos);
                        }
                    }
                    return returnRecommendationBonusBean;
               } catch (Exception e) {
                    log.error(e.getMessage());
                    returnRecommendationBonusBean.setFailCount(1);
                    e.printStackTrace();
                    return returnRecommendationBonusBean;
                }

            }
            //推荐补贴三审
            if (!StringUtils.isEmpty(recommendationBonusBean.getThirdAuditStatus())) {
                try {
                    recommendationBonusInfoModel.setAuditProcess(3);
                    recommendationBonusInfoModel.setAuditStatus(Integer.valueOf(recommendationBonusBean.getThirdAuditStatus()));
                    returnRecommendationBonusBean.setRow(this.updateByPrimaryKey(recommendationBonusInfoModel));


                    //推荐补贴三审记入审核历史表中
                    UserAuditHistoryModel userAuditHistoryModel = new UserAuditHistoryModel();
                    userAuditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_THIRD_RECOMMENDATION_BONUS);
                    userAuditHistoryModel.setSourceId(recommendationBonusBean.getId());
                    if(Integer.valueOf(recommendationBonusBean.getThirdAuditStatus())==6){
                        userAuditHistoryModel.setStatusAfter(AuditHistoryConstant.RECOMMENDATION_PASS);//二审通过
                    }else if(Integer.valueOf(recommendationBonusBean.getThirdAuditStatus())==7){
                        userAuditHistoryModel.setStatusAfter(AuditHistoryConstant.RECOMMENDATION_USELES);//驳回
                    }else{
                        userAuditHistoryModel.setStatusAfter(AuditHistoryConstant.RECOMMENDATION_PAY_THREE);//假一罚三
                    }
                    userAuditHistoryModel.setCreateTime(DateUtil.now());
                    userAuditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
                    userAuditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                    userAuditHistoryModel.setRemark(recommendationBonusBean.getThirdAuditRemark());
                    userAuditHistoryService.insert(userAuditHistoryModel);

                    // 推荐费补贴三通过后更新账单或者创建账单
                    if (recommendationBonusBean.getThirdAuditStatus().equals(BonusAuditStatus.BONUSAUDITSTATUS_THIRD_JYFS.toString()) || recommendationBonusBean.getThirdAuditStatus().equals(BonusAuditStatus.BONUSAUDITSTATUS_THIRD_PASS.toString())) {
                        RecommendationBonusSettingModel reco = recommendationBonusInfoSettingService.selectByPrimaryKey(recoId);
                        BonusBillInfoModel billInfoModelInfo;
                        //查询当天推荐补贴账单数据，判断当天有没有生成账单，若已生成账单，则修改账单金额，否则新建当天推荐补贴子账单及父账单
                        paramMap.put("payeeId",recommendationBonusInfoModel.getUserId());
                        paramMap.put("sourceId",recommendationBonusInfoModel.getId());
                        billInfoModelInfo = bonusBillInfoService.getBonusBillInfo(paramMap);
                        BigDecimal amountPerPerson = reco.getAmountPerPerson();
                        if (billInfoModelInfo != null) {
                            BonusBillInfoModel bonusBillInfoModel = bonusBillInfoService.selectByPrimaryKey(billInfoModelInfo.getParentId());
                            BigDecimal currAllNum = billInfoModelInfo.getAmount();
                            billInfoModelInfo.setAmount(currAllNum.add(amountPerPerson));
                            bonusBillInfoModel.setAmount(currAllNum.add(amountPerPerson));
                            billInfoModelInfo.setUpdateTime(DateUtil.now());
                            billInfoModelInfo.setUpdateBy(SessionUtils.getSysUserSession().getId());
//                            logRedisService.saveBillInfoLog(billInfoModel, LogConstant.OPT_UPDATE);
                            bonusBillInfoService.updateBillInfoForRecommendation(billInfoModelInfo);

                            //修改父账单金额
                            bonusBillInfoService.updateBillInfoForRecommendation(bonusBillInfoModel);

                        } else {
                            billInfoModelInfo = new BonusBillInfoModel();
                            billInfoModelInfo.setBillNo(bonusBillInfoService.getBillNo(recommendationBonusInfoModel.getUserId()));
                            billInfoModelInfo.setStatus(BillConstant.BILL_STATUS_NOT_PAID);
                            billInfoModelInfo.setSourceId(recommendationBonusInfoModel.getId());
                            billInfoModelInfo.setCreateTime(DateUtil.now());
                            billInfoModelInfo.setDeleted(0);
                            billInfoModelInfo.setCreateBy(SessionUtils.getSysUserSession().getId());
                            billInfoModelInfo.setBillType(BillConstant.BILL_TYPE_RECOMMAND_BONUS);
                            billInfoModelInfo.setPayeeId(recommendationBonusInfoModel.getUserId());
                            billInfoModelInfo.setAmount(amountPerPerson);
                            billInfoModelInfo.setUpdateTime(DateUtil.now());
                            billInfoModelInfo.setUpdateBy(SessionUtils.getSysUserSession().getId());
                            if (recommendationBonusBean.getThirdAuditStatus().equals(BonusAuditStatus.BONUSAUDITSTATUS_THIRD_JYFS.toString())) {
                                billInfoModelInfo.setAmount(amountPerPerson.multiply(new BigDecimal(3)).negate());
                            }
                            bonusBillInfoService.insertSelective(billInfoModelInfo);
                        }
                    }
                    return returnRecommendationBonusBean;
                } catch (Exception e) {
                    log.error(e.getMessage());
                    returnRecommendationBonusBean.setFailCount(1);
                    e.printStackTrace();
                    return returnRecommendationBonusBean;
                }

            }

        }
        throw new TopjetExceptionHandler(ExceptionEnum.E_DATA_EXPIRED.getStatus(), ExceptionEnum.E_DATA_EXPIRED.getMsg());
    }

    @Override
    public List<RecommendationBonusInfoModel> selectRecommendationBonusInfo(RecommendationBonusInfoModel recommendationBonusInfoModel) {
        return recommendationBonusInfoModelEMapper.selectListByEntity(recommendationBonusInfoModel);
    }

    @Override
    public RecommendationBonusInfoModel selectByPrimaryKey(Integer id) {
        return recommendationBonusInfoModelEMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(RecommendationBonusInfoModel recommendationBonusInfoModel) {
        return recommendationBonusInfoModelMapper.update(recommendationBonusInfoModel);
    }

    @Override
    public List<RecommendationBonusBean> queryByPendingList(RecommendationBonusBean recommendationBonusBean) {
        return recommendationBonusInfoModelEMapper.queryByPendingList(recommendationBonusBean);
    }

    @Override
    public int queryByPendingCount(RecommendationBonusBean recommendationBonusBean) {
        return recommendationBonusInfoModelEMapper.queryByPendingCount(recommendationBonusBean);
    }


}
