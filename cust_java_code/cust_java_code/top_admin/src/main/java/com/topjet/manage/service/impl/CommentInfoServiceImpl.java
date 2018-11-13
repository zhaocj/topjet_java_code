package com.topjet.manage.service.impl;

import com.topjet.basic.BasicFeignService;
import com.topjet.basic.UserFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.custservice.user.bean.DeleteUserCommentInfoRTS;
import com.topjet.cloud.manage.custservice.user.bean.UpdateUserCommentInfoRTS;
import com.topjet.cloud.manage.custservice.user.bean.UpdateUserCommentInfoVRU;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.domain.bean.CommentEditResponseBean;
import com.topjet.manage.domain.bean.UserCommentBean;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.domain.model.UserCommentInfoModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.mapper.readMapper.UserAuditHistoryModelEMapper;
import com.topjet.manage.mapper.readMapper.UserCommentInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.UserInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.UserAuditHistoryModelMapper;
import com.topjet.manage.service.CommentInfoService;
import com.topjet.tool.common.util.FormatUtil;
import com.topjet.user.constant.AuditHistoryConstant;
import com.topjet.util.DateUtils;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by yuzh on 2017/11/3.
 */
@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    @Autowired
    private UserCommentInfoModelEMapper commentInfoModelEMapper;
    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;
    @Autowired
    private BasicFeignService basicFeignService;
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private UserAuditHistoryModelEMapper userAuditHistoryModelEMapper;
    @Autowired
    private UserAuditHistoryModelMapper userAuditHistoryModelMapper;

    @Override
    public List<UserCommentBean> getCommentList(UserCommentBean userCommentBean) {
        return commentInfoModelEMapper.getCommentList(userCommentBean);
    }

    @Override
    public Integer getCommentCount(UserCommentBean userCommentBean) {
        return commentInfoModelEMapper.getCommentCount(userCommentBean);
    }

    @Override
    public UserCommentInfoModel findCommentById(Integer id) {
        return commentInfoModelEMapper.findCommentById(id);
    }

    @Override
    public List<UserCommentInfoModel> findById(Integer id) {
        return null;
    }

    @Override
    public Object edit(Integer id) {
        CommentEditResponseBean result=new CommentEditResponseBean();
        UserCommentInfoModel userCommentInfoModel = commentInfoModelEMapper.findCommentById(id);
        List<UserCommentInfoModel> dataList = commentInfoModelEMapper.findById(id);
        if(userCommentInfoModel != null && dataList != null){
            UserInfoModel userInfoModel = userInfoModelEMapper.findById(userCommentInfoModel.getCommentUser());
            UserInfoModel userdInfoModel = userInfoModelEMapper.findById(userCommentInfoModel.getCommentedUser());
            result.setId(userCommentInfoModel.getId()+"");
            result.setOrderNo(userCommentInfoModel.getOrderNo());
            result.setCommentType(userInfoModel.getUserType()+"");
            result.setCommentedType(userdInfoModel.getUserType()+"");
            result.setCreateTime(DateUtils.getDisplayYMDHMS(userCommentInfoModel.getCreateTime()));
            result.setTotal(FormatUtil.convertBigDecimalToString(userCommentInfoModel.getTotal(),1));
            result.setContent(userCommentInfoModel.getContent());
            result.setVersion(userCommentInfoModel.getVersion());
            if(userInfoModel != null){
                result.setCommentName(userInfoModel.getUsername());
                result.setCommentMobile(userInfoModel.getMobile());
            }
            if(userdInfoModel != null){
                result.setCommentedName(userdInfoModel.getUsername());
                result.setCommentedMobile(userdInfoModel.getMobile());
            }
            GeturlRTS rts = new GeturlRTS();
            rts.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
            if(dataList != null && dataList.size()>0){
                int i = 0;
                for (UserCommentInfoModel commentInfoModel : dataList) {
                    rts.setKey(dataList.get(i).getPictureUrl());
                    switch (i){
                        case 0:
                            result.setPicture1(basicFeignService.getUrl(rts).getObjurl());
                            break;
                        case 1:
                            result.setPicture2(basicFeignService.getUrl(rts).getObjurl());
                            break;
                        case 2:
                            result.setPicture3(basicFeignService.getUrl(rts).getObjurl());
                            break;
                        case 3:
                            result.setPicture4(basicFeignService.getUrl(rts).getObjurl());
                            break;
                    }
                    i++;
                }

            }

            UserAuditHistoryModel auditHistoryModel = new UserAuditHistoryModel();
            auditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_COMMENT_REMARK);
            auditHistoryModel.setSourceId(userCommentInfoModel.getId());
            auditHistoryModel.setStatusAfter(AuditHistoryConstant.AUDIT_COMMENT_REMARK_UPDATE);
            List<UserAuditHistoryModel> userAuditHistoryModels = userAuditHistoryModelEMapper.selectListByEntity(auditHistoryModel);
            if(userAuditHistoryModels != null && userAuditHistoryModels.size()>0){
                result.setOperatorRemark(userAuditHistoryModels.get(0).getRemark());
            }
        }
        return result;
    }

    @Override
    public Object update(Integer id, Integer version, String content, String operatorRemark) {
        ResultBaseMsg msg=new ResultBaseMsg();
        UserCommentInfoModel model= commentInfoModelEMapper.findCommentById(id);
        if(model==null){
            msg.setStatus(ExceptionEnum.E_2.getStatus());
            msg.setMsg(ExceptionEnum.E_2.getMsg());
            return msg;
        }
        if(model.getVersion().equals(version)){
            UpdateUserCommentInfoRTS updateUserCommentInfoRTS = new UpdateUserCommentInfoRTS();
            updateUserCommentInfoRTS.setId(id);
            updateUserCommentInfoRTS.setUserId(SessionUtils.getSysUserSession().getId());
            updateUserCommentInfoRTS.setContent(content);
            updateUserCommentInfoRTS.setVersion(version);
            UpdateUserCommentInfoVRU updateUserCommentInfoVRU = userFeignService.updateUserCommentInfo(updateUserCommentInfoRTS);
            if(!StringUtils.isBlank(operatorRemark)){
                UserAuditHistoryModel auditHistoryModel=new UserAuditHistoryModel();
                auditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
                auditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                auditHistoryModel.setCreateTime(com.topjet.common.util.DateUtil.now());
                auditHistoryModel.setRemark(operatorRemark);
                auditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_COMMENT_REMARK);
                auditHistoryModel.setSourceId(model.getId());
                auditHistoryModel.setStatusAfter(AuditHistoryConstant.AUDIT_COMMENT_REMARK_UPDATE);
                userAuditHistoryModelMapper.insert(auditHistoryModel);
            }
        }else {
            msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
        }
        return msg;
    }

    @Override
    public Object delete(Integer id) {
        ResultBaseMsg msg=new ResultBaseMsg();
        try {
            DeleteUserCommentInfoRTS deleteUserCommentInfoRTS = new DeleteUserCommentInfoRTS();
            deleteUserCommentInfoRTS.setId(id);
            deleteUserCommentInfoRTS.setUserId(SessionUtils.getSysUserSession().getId());
            userFeignService.deleteUserCommentInfo(deleteUserCommentInfoRTS);
        } catch (Exception e) {
            msg.setStatus(ExceptionEnum.E_3.getStatus());
            msg.setMsg(ExceptionEnum.E_3.getMsg());
        }
        return msg;
    }
}
