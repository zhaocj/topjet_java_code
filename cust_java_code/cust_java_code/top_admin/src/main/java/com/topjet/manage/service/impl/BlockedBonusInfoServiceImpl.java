package com.topjet.manage.service.impl;

import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.model.BlockedBonusInfoModel;
import com.topjet.manage.domain.model.OrderInfoModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.mapper.readMapper.UserInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.BlockedBonusInfoModelMapper;
import com.topjet.manage.service.BlockedBonusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-27 10:53
 */

@Transactional
@Service
public class BlockedBonusInfoServiceImpl implements BlockedBonusInfoService{

    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;

    @Autowired
    private BlockedBonusInfoModelMapper blockedBonusInfoModelMapper;

    @Override
    public BlockedBonusInfoModel insterBlockedBonus(OrderInfoModel orderInfoModel, Integer userId, String reason, String type, String remark) {
        UserInfoModel userModel = userInfoModelEMapper.selectByPrimaryKey(userId);
        BlockedBonusInfoModel blockedBonusInfoModel = new BlockedBonusInfoModel();
        blockedBonusInfoModel.setOrderId(orderInfoModel.getId());
        blockedBonusInfoModel.setReason(Integer.parseInt(reason));
        blockedBonusInfoModel.setRemark(remark);
        blockedBonusInfoModel.setCreateTime(DateUtil.now());
        blockedBonusInfoModel.setUserId(userId);
        blockedBonusInfoModel.setUserName(userModel.getUsername());
        blockedBonusInfoModel.setType(Integer.parseInt(type));
        blockedBonusInfoModel.setDeleted(0);
        blockedBonusInfoModel.setVersion(1);
        blockedBonusInfoModelMapper.insert(blockedBonusInfoModel);
        return blockedBonusInfoModel;
    }
}
