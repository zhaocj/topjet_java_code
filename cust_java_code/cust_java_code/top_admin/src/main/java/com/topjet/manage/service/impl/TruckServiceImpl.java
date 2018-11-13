package com.topjet.manage.service.impl;

import com.topjet.basic.BasicFeignService;
import com.topjet.basic.TruckFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.custservice.truck.bean.UpdateTurckRTS;
import com.topjet.cloud.manage.custservice.truck.bean.UpdateTurckVRU;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.TruckEditResponseBean;
import com.topjet.manage.domain.vo.TruckListQuery;
import com.topjet.manage.domain.vo.TruckListVO;
import com.topjet.manage.domain.model.DriverTruckInfoModel;
import com.topjet.manage.domain.model.TruckAuditHistoryModel;
import com.topjet.manage.domain.model.TruckInfoModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.mapper.readMapper.TruckAuditHistoryModelEMapper;
import com.topjet.manage.mapper.readMapper.TruckInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.UserInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.TruckAuditHistoryModelMapper;
import com.topjet.manage.service.TruckService;
import com.topjet.user.constant.AuditHistoryConstant;
import com.topjet.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/9/11.
 */
@Service
public class TruckServiceImpl implements TruckService {

    @Autowired
    private TruckInfoModelEMapper truckInfoModelEMapper;
    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;
    @Autowired
    private TruckAuditHistoryModelEMapper truckAuditHistoryModelEMapper;
    @Autowired
    private TruckAuditHistoryModelMapper truckAuditHistoryModelMapper;
    @Autowired
    private BasicFeignService basicFeignService;
    @Autowired
    private TruckFeignService truckFeignService;


    @Override
    public List<TruckListVO> getTruckList(TruckListQuery truckListQuery) {
        return truckInfoModelEMapper.getTruckList(truckListQuery);
    }

    @Override
    public Integer getTruckCount(TruckListQuery truckListQuery) {
        return truckInfoModelEMapper.getTruckCount(truckListQuery);
    }

    @Override
    public List<TruckInfoModel> getTruckInfoByPlatNo(TruckInfoModel truckInfoModel) {
        return truckInfoModelEMapper.getTruckInfoByPlatNo(truckInfoModel);
    }

    @Override
    public TruckInfoModel getTruckById(Integer id) {
        return truckInfoModelEMapper.getTruckById(id);
    }

    @Override
    public List<DriverTruckInfoModel> findTruckInfoById(Integer truckId) {
        return truckInfoModelEMapper.findTruckInfoById(truckId);
    }

    @Override
    public Object edit(Integer id) {
        TruckEditResponseBean truckEditResponseBean = new TruckEditResponseBean();
        TruckInfoModel truckInfoModel = truckInfoModelEMapper.getTruckById(id);
        if(truckInfoModel != null){
            truckEditResponseBean.setId(truckInfoModel.getId());
            UserInfoModel user = userInfoModelEMapper.selectByPrimaryKey(truckInfoModel.getOwnerId());
            if(user != null){
                truckEditResponseBean.setUsername(user.getUsername());
                truckEditResponseBean.setMobile(user.getMobile());
                truckEditResponseBean.setUserCreateTime(DateUtils.getDisplayYMDHMS(user.getCreateTime()));
            }
            List<DriverTruckInfoModel> list = truckInfoModelEMapper.findTruckInfoById(truckInfoModel.getId());
            if(list != null && list.size() >= 0){
                DriverTruckInfoModel driverTruckInfoModel = list.get(0);
                truckEditResponseBean.setAuditStatus(driverTruckInfoModel.getAuditStatus());
            }
            truckEditResponseBean.setTruckLength(truckInfoModel.getTruckLength());
            truckEditResponseBean.setTruckType(truckInfoModel.getTruckType());
            truckEditResponseBean.setTruckCreateTime(DateUtils.getDisplayYMDHMS(truckInfoModel.getCreateTime()));
            truckEditResponseBean.setPlateNo1(truckInfoModel.getPlateNo1());
            truckEditResponseBean.setPlateNo2(truckInfoModel.getPlateNo2());
            truckEditResponseBean.setPlateNo3(truckInfoModel.getPlateNo3());
            truckEditResponseBean.setTruckColor(truckInfoModel.getPlateColor().toString());
           // truckEditResponseBean.setTruckTypeId(truckInfoModel);
            TruckAuditHistoryModel truckAuditHistoryModel = truckAuditHistoryModelEMapper.getAuditHistoryRemark(truckInfoModel.getId());
            if(truckAuditHistoryModel != null){
                truckEditResponseBean.setOperatorRemark(truckAuditHistoryModel.getRemark());
            }else{
                truckEditResponseBean.setOperatorRemark("");
            }
            try {
                GeturlRTS geturlRTS = new GeturlRTS();
                geturlRTS.setKey(truckInfoModel.getTruckHeadImg());
                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_TRUCK_PHOTO);
                truckEditResponseBean.setTruckPhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());

                geturlRTS.setKey(truckInfoModel.getDriverLicenseImg());
                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_DRIVING_LICENSE);
                truckEditResponseBean.setDrivingLicensePhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return truckEditResponseBean;
    }

    @Override
    public TruckAuditHistoryModel getAuditHistoryRemark(Integer sourceId) {
        return truckAuditHistoryModelEMapper.getAuditHistoryRemark(sourceId);
    }

    @Override
    public Object updateTruckInfo(Integer id, String plateNo1,String plateNo2,String plateNo3, Integer truckColor, Integer truckType,Integer truckLength,String operatorRemark){
        ResultBaseMsg msg = new ResultBaseMsg();
        TruckInfoModel model = truckInfoModelEMapper.getTruckById(id);
        if(model == null){
            msg.setStatus(ExceptionEnum.E_2.getStatus());
            msg.setMsg(ExceptionEnum.E_2.getMsg());
            return msg;
        }
        //开始进行修改操作
        UpdateTurckRTS updateTurckRTS = new UpdateTurckRTS();
        updateTurckRTS.setTruckId(id);
        updateTurckRTS.setDriverId(model.getOwnerId());
        updateTurckRTS.setUserId(SessionUtils.getSysUserSession().getId());
        updateTurckRTS.setPlateColor(truckColor);
        updateTurckRTS.setPlateNo1(plateNo1);
        updateTurckRTS.setPlateNo2(plateNo2);
        updateTurckRTS.setPlateNo3(plateNo3);
        updateTurckRTS.setTruckLength(truckLength);
        updateTurckRTS.setTruckType(truckType);
        UpdateTurckVRU updateTurckVRU = truckFeignService.updateTruck(updateTurckRTS);
        //在车辆审核表中插入日志
        if(!StringUtils.isBlank(operatorRemark)){
            TruckAuditHistoryModel truckAuditHistoryModel = new TruckAuditHistoryModel();
            truckAuditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
            truckAuditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
            truckAuditHistoryModel.setCreateTime(com.topjet.common.util.DateUtil.now());
            truckAuditHistoryModel.setRemark(operatorRemark);
            truckAuditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_TRUCK_REMARK);
            truckAuditHistoryModel.setSourceId(model.getId());
            truckAuditHistoryModel.setStatusAfter(AuditHistoryConstant.AUDIT_TRUCK_REMARK_UPDATE);
            truckAuditHistoryModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            truckAuditHistoryModelMapper.insert(truckAuditHistoryModel);
        }
        /*TruckInfoModel model = truckInfoService.selectByPrimaryKey(id);
        if (model == null) {
            msg.setStatus(ExceptionEnum.E_2.getStatus());
            msg.setMsg(ExceptionEnum.E_2.getMsg());
            return msg;
        }
        if (model.getVersion().equals(version)) {
            model.setPlateNo(plateNo);
            model.setPlateColor(truckColor);
            model.setTruckAge(truckAge);
            model.setTruckLength(truckLength);
            model.setTruckType(truckType);
            model.setVersion(version + 1);
            model.setUpdateTime(com.topjet.common.util.DateUtil.now());
            truckInfoService.updateByPrimaryKeySelective(model);

            if(!StringUtils.isBlank(operatorRemark)){
                AuditHistoryModel auditHistoryModel=new AuditHistoryModel();
                auditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
                auditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                auditHistoryModel.setCreateTime(com.topjet.common.util.DateUtil.now());
                auditHistoryModel.setRemark(operatorRemark);
                auditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_TRUCK_REMARK);
                auditHistoryModel.setSourceId(model.getId());
                auditHistoryModel.setStatusAfter(AuditHistoryConstant.AUDIT_TRUCK_REMARK_UPDATE);
                auditHistoryService.insertAH(auditHistoryModel);
            }
        } else {
            msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
        }*/
        return msg;
    }
}
