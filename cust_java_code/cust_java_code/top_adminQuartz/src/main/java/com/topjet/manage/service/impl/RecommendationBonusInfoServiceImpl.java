package com.topjet.manage.service.impl;

import com.topjet.constants.CommonConstant;
import com.topjet.constants.SystemConfig;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.mapper.readMapper.*;
import com.topjet.manage.mapper.writeMapper.BlockedBonusInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.RecommendationBonusInfoModelMapper;
import com.topjet.manage.service.BlackListInfoService;
import com.topjet.manage.service.RecommendationBonusInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-04 16:53
 */

@Service
public class RecommendationBonusInfoServiceImpl implements RecommendationBonusInfoService {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationBonusInfoServiceImpl.class);

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private UserInfoModelEMapper userInfoModelMapper;

    @Autowired
    private UserOtherInfoModelEMapper userOtherInfoModelMapper;

    @Autowired
    private BlackListInfoService blackListInfoService;

    @Autowired
    private BlockedBonusInfoModelEMapper blockedBonusInfoModelEMapper;

    @Autowired
    private BlockedBonusInfoModelMapper blockedBonusInfoModelMapper;

    @Autowired
    private RecommendRelationshipModelEMapper recommendRelationshipModelEMapper;

    @Autowired
    private RecommendationBonusInfoModelEMapper recommendationBonusInfoModelEMapper;

    @Autowired
    private RecommendationBonusInfoModelMapper recommendationBonusInfoModelMapper;


    @Override
    public boolean verificationBinus(RecommendRelationshipModel model) {

        boolean b = false;


        // 获取被推荐人user
        UserInfoModel userModel = userInfoModelMapper.selectByPrimaryKey(model.getRecommendedUserId());

        // 推荐人
        UserInfoModel recommendUserModel = userInfoModelMapper.selectByPrimaryKey(model.getRecommendUserId());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userId",userModel.getId());
        List<UserOtherInfoModel> userOtherInfoModel = userOtherInfoModelMapper.selectListByParam(paramMap);

        if(userModel ==null){

            // 生成屏蔽信息
            BlockedBonusInfoModel blockedBonusInfoModel = new BlockedBonusInfoModel();
            blockedBonusInfoModel.setOrderId(0);
            blockedBonusInfoModel.setReason(1); //1: 黑名单
            blockedBonusInfoModel.setRemark("被推荐人不存在");
            blockedBonusInfoModel.setCreateTime(new Date());
            blockedBonusInfoModel.setUserId(model.getRecommendedUserId());
            blockedBonusInfoModel.setUserName("");
            blockedBonusInfoModel.setType(4); // 4: 推荐费补贴
            blockedBonusInfoModel.setDeleted(0);
            blockedBonusInfoModel.setVersion(1);
            blockedBonusInfoModel.setRecommendationId(model.getId());
            blockedBonusInfoModelMapper.insert(blockedBonusInfoModel);

            return false;
        }
        if(recommendUserModel == null){
            // 生成屏蔽信息
            BlockedBonusInfoModel blockedBonusInfoModel = new BlockedBonusInfoModel();
            blockedBonusInfoModel.setOrderId(0);
            blockedBonusInfoModel.setReason(1); //1: 黑名单
            blockedBonusInfoModel.setRemark("该推荐人不存在");
            blockedBonusInfoModel.setCreateTime(new Date());
            blockedBonusInfoModel.setUserId(model.getRecommendUserId());
            blockedBonusInfoModel.setUserName("");
            blockedBonusInfoModel.setType(4); // 4: 推荐费补贴
            blockedBonusInfoModel.setDeleted(0);
            blockedBonusInfoModel.setVersion(1);
            blockedBonusInfoModel.setRecommendationId(model.getId());
            blockedBonusInfoModelMapper.insert(blockedBonusInfoModel);
            return false;
        }

        //是否推荐人为被屏蔽用户
        {
            boolean isBlack = blackListInfoService.isBlackByType(recommendUserModel.getId(), CommonConstant.BLACK_LIST_TYPE_RECOMMENDATION_BONUS);
            if(isBlack){
                logger.info("该推荐人是被屏蔽用户userID: "+recommendUserModel.getId());
                // 生成屏蔽信息
                BlockedBonusInfoModel blockedBonusInfoModel = new BlockedBonusInfoModel();
                blockedBonusInfoModel.setOrderId(0);
                blockedBonusInfoModel.setReason(1); //1: 黑名单
                blockedBonusInfoModel.setRemark("该推荐人是被屏蔽用户");
                blockedBonusInfoModel.setCreateTime(new Date());
                blockedBonusInfoModel.setUserId(model.getRecommendUserId());
                blockedBonusInfoModel.setUserName(recommendUserModel.getUsername());
                blockedBonusInfoModel.setType(4); // 4: 推荐费补贴
                blockedBonusInfoModel.setDeleted(0);
                blockedBonusInfoModel.setVersion(1);
                blockedBonusInfoModel.setRecommendationId(model.getId());
                blockedBonusInfoModelMapper.insert(blockedBonusInfoModel);
                return false;
            }
        }
        // 校验被推荐人 IEMI 重复补贴
        {
            paramMap.clear();
            paramMap.put("idNo",userModel.getIdNo());
            paramMap.put("registeredIMEI",userOtherInfoModel.get(0).getRegisteredIMEI());

            List<UserOtherInfoModel> userList = userOtherInfoModelMapper.selectListByParams(paramMap);
            if(userList.size()>1){
                String remark = "该笔推荐补贴被推荐人所使用手机已发放过补贴: 被推荐人 手机号:" + userModel.getMobile() + "手机iemi:"
                        + userOtherInfoModel.get(0).getRegisteredIMEI();
                logger.info(remark);
                this.insertModel(recommendUserModel,model,remark);
                return false;
            }
            RecommendRelationshipModel relationshipModel = new RecommendRelationshipModel();
            List<RecommendationBonusInfoModel> recommendationBonusInfoModelList = new ArrayList<RecommendationBonusInfoModel>();

            for (UserOtherInfoModel userImeiModel : userList) {
                paramMap.clear();
                paramMap.put("recommendedUserId",userImeiModel.getUserId());
                List<RecommendRelationshipModel> recommendRelationshipModelList = recommendRelationshipModelEMapper.selectListByParam(paramMap);


                for (RecommendRelationshipModel recommendRelationshipImeiModel : recommendRelationshipModelList) {
                    paramMap.clear();
                    paramMap.put("recommendationId",recommendRelationshipImeiModel.getId());
                    recommendationBonusInfoModelList.addAll(recommendationBonusInfoModelEMapper.selectListByParam(paramMap));
                }
            }
            if (recommendationBonusInfoModelList.isEmpty()) {
                b = true;
            }else {

                // 生成屏蔽信息
                relationshipModel = recommendRelationshipModelEMapper.selectByPrimaryKey(recommendationBonusInfoModelList.get(0).getRecommendationId());

                // 与被推荐人相同 iemi 且已补贴过的用户

                UserInfoModel recommendedUser = userInfoModelMapper.selectByPrimaryKey(relationshipModel.getRecommendedUserId());
                paramMap.clear();
                paramMap.put("userId",relationshipModel.getRecommendedUserId());
                List<UserOtherInfoModel> userOtherInfoModels = userOtherInfoModelMapper.selectListByParams(paramMap);


                String remark = "该笔推荐补贴被推荐人所使用手机已发放过补贴: 被推荐人 手机号:" + userModel.getMobile() + "手机iemi:"
                        + userOtherInfoModel.get(0).getRegisteredIMEI() + "      已补贴用户:姓名:" + recommendedUser.getUsername() + "手机iemi:"
                        + userOtherInfoModels.get(0).getRegisteredIMEI();
                logger.info(remark);
                this.insertModel(recommendUserModel,model,remark);
                return false;
            }
        }

		/*
		// 校验推荐人是否有多个认证身份证
		{
			UserModelCriteria criteria = new UserModelCriteria();
			criteria.createCriteria().andIdNoEqualTo(recommendUserModel.getIdNo())
					.andIdNotEqualTo(recommendUserModel.getId());
			Integer userCount = userService.countByCriteria(criteria);
			if (userCount>0){
				b = true;
			}else {
				// 生成屏蔽信息
				String remark = "推荐人存在多个身份证号[" + recommendUserModel.getIdNo()+ "] 相同的用户";
				this.insertModel(recommendUserModel,recommendRelationshipModel,remark);
				return b;
			}
		} */

        // 校验 被推荐人 如果有多个身份证用户,则补贴记录只有一条
        {
            paramMap.clear();
            paramMap.put("idNo", userModel.getIdNo());
            paramMap.put("id",userModel.getId());
            List<UserInfoModel> userList = userInfoModelMapper.selectListByParams(paramMap);


            RecommendRelationshipModel relationshipModel = new RecommendRelationshipModel();
            List<RecommendationBonusInfoModel> recommendationBonusInfoModelList = new ArrayList<RecommendationBonusInfoModel>();

            for (UserInfoModel userIdNoModel : userList) {
                paramMap.clear();
                paramMap.put("recommendedUserId",userIdNoModel.getId());
                List<RecommendRelationshipModel> recommendRelationshipModelList = recommendRelationshipModelEMapper.selectListByParam(paramMap);

                for (RecommendRelationshipModel recommendRelationshipImeiModel : recommendRelationshipModelList) {
                    paramMap.clear();
                    paramMap.put("recommendationId",recommendRelationshipImeiModel.getId());
                    recommendationBonusInfoModelList.addAll(recommendationBonusInfoModelEMapper.selectListByParam(paramMap));
                }
            }

            if (recommendationBonusInfoModelList.isEmpty()){
                b = true;
            }else {
                // 生成屏蔽信息
                relationshipModel = recommendRelationshipModelEMapper.selectByPrimaryKey(recommendationBonusInfoModelList.get(0).getRecommendationId());

                UserInfoModel recommendedUser = userInfoModelMapper.selectByPrimaryKey(relationshipModel.getRecommendedUserId());
                String remark = "该笔推荐补贴被推荐人相同身份证号已发放过补贴: 被推荐人 手机号:" + userModel.getMobile() + "身份证号:"
                        + userModel.getIdNo() + "      已补贴用户:姓名:" + recommendedUser.getUsername() + "身份证号:"
                        + recommendedUser.getIdNo();
                logger.info(remark);
                this.insertModel(recommendUserModel,model,remark);
                return false;
            }
        }


        return b;
    }

    @Override
    public int insert(RecommendationBonusInfoModel recommendationBonusInfoModel) {
        return recommendationBonusInfoModelMapper.insert(recommendationBonusInfoModel);
    }

    @Override
    public List<RecommendRelationshipModel> getSuitableRecoBonusAllUser() {
        return recommendationBonusInfoModelEMapper.getSuitableRecoBonusAllUser();
    }

    /**
     * 推荐人
     * @param recommendUser
     * @param recommendRelationshipModel
     * @param remark
     */
    private void insertModel(UserInfoModel recommendUser,RecommendRelationshipModel recommendRelationshipModel,String remark){
        BlockedBonusInfoModel blockedBonusInfoModel = new BlockedBonusInfoModel();
        blockedBonusInfoModel.setOrderId(0);
        blockedBonusInfoModel.setReason(3); //3: 收货手机与注册地不符合
        blockedBonusInfoModel.setRemark(remark);
        blockedBonusInfoModel.setCreateTime(new Date());
        blockedBonusInfoModel.setUserId(recommendRelationshipModel.getRecommendUserId());
        blockedBonusInfoModel.setUserName(recommendUser.getUsername());
        blockedBonusInfoModel.setType(4); // 4: 推荐费补贴
        blockedBonusInfoModel.setDeleted(0);
        blockedBonusInfoModel.setVersion(1);
        blockedBonusInfoModel.setRecommendationId(recommendRelationshipModel.getId());
        blockedBonusInfoModelMapper.insert(blockedBonusInfoModel);
    }
}
