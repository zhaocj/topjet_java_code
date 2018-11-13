package com.topjet.cloud.user.serivce.impl;

import com.topjet.cloud.manage.custservice.user.bean.*;
import com.topjet.cloud.user.constant.UserConstant;
import com.topjet.cloud.user.dao.UserBlackListInfoDao;
import com.topjet.cloud.user.dao.UserInfoDao;
import com.topjet.cloud.user.dao.UserOtherInfoDao;
import com.topjet.cloud.user.dao.model.UserBlackListInfoModel;
import com.topjet.cloud.user.dao.model.UserInfoModel;
import com.topjet.cloud.user.dao.model.UserOtherInfoModel;
//import com.topjet.tool.redis.redisuser.service.RedisUserService;
import com.topjet.cloud.user.serivce.UserService;
import com.topjet.tool.common.constant.SystemConstant;
import com.topjet.tool.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.topjet.cloud.user.constant.WebUserConstant.*;


/**
 * Created by hongtaoer-win on 2017/8/4.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    //用户dao
    @Autowired
    private UserInfoDao userDao;

    @Autowired
    private UserOtherInfoDao userOtherInfoDao;

    @Autowired
    private UserBlackListInfoDao userBlackListInfoDao;

//    @Autowired
//    private RedisUserService redisUserService;

    /**
     * 根据用户ID【修改实名认证状态】
     * @param requestDTO
     */
    public  UpdateUserStatusByIdVRU updateUserStatusById(UpdateUserStatusByIdRTS requestDTO) {
        //检测传参是否有空值
        if (requestDTO.getUseStatus()==null || requestDTO.getUserId()==null){
            //请求参数为空
            return new UpdateUserStatusByIdVRU(WEB_MSG_4.getCode(), WEB_MSG_4.getMsg());
        }
        //TODO 用户的返回待定
        //冻结判断
        UserBlackListInfoModel userBlackListInfoModel = new UserBlackListInfoModel();
        userBlackListInfoModel.setUserId(requestDTO.getUserId());
        userBlackListInfoModel.setbType(UserConstant.USER_BLACK_TYPE_FREEZE);

        //查询用户
        userBlackListInfoModel = userBlackListInfoDao.getUserBlackByType(userBlackListInfoModel);
        if(userBlackListInfoModel != null  && userBlackListInfoModel.getDeleted().equals(Integer.parseInt(SystemConstant.DELETE_NO))){
            return new UpdateUserStatusByIdVRU(WEB_MSG_6.getCode(), WEB_MSG_6.getMsg());
        }


        //设置用户实名认证状态
        //查询用户当前实名认证状态
        UserInfoModel userInfoModel = userDao.selectUserStatusById(requestDTO.getUserId());
        //校验当前实名认证状态
        if(!userInfoModel.getUseStatus().equals(UserConstant.USER_STATUS_WAITAUCIT) && !userInfoModel.getUseStatus().equals(UserConstant.USER_STATUS_DATA_WAIT_REVIEWED)){
            //状态有误
            return new UpdateUserStatusByIdVRU(WEB_MSG_5.getCode(), WEB_MSG_5.getMsg());
        }
        if(!requestDTO.getUseStatus().equals(UserConstant.USER_STATUS_APPROVED) && !requestDTO.getUseStatus().equals(UserConstant.USER_STATUS_FAILURE)){
            //参数有误
            return new UpdateUserStatusByIdVRU(WEB_MSG_3.getCode(),WEB_MSG_3.getMsg());
        }
        //进行修改
        userInfoModel.setUseStatus(requestDTO.getUseStatus());
        userInfoModel.setUserType(requestDTO.getUserType());
        userInfoModel.setUsername(requestDTO.getUsername());
        userInfoModel.setIdNo(requestDTO.getIdNo());
       Integer flag = userDao.updateUserStatusById(userInfoModel);

       if(flag<1){
           //修改失败
           return new UpdateUserStatusByIdVRU(WEB_MSG_2.getCode(),WEB_MSG_2.getMsg());
       }
       //TODO  同步redis
//        redisUserService.syncUserRedis(userInfoModel.getMobile(),userInfoModel.getId(),userInfoModel.getUseStatus(),null);
        //修改成功
        return new UpdateUserStatusByIdVRU(WEB_MSG_1.getCode(),WEB_MSG_1.getMsg());

    }


    /**
     * 根据用户ID【修改身份认证状态】
     * @param requestDTO
     */
    public  UpdateUserAuthStatusByIdVRU updateUserAuthStatusById(UpdateUserAuthStatusByIdRTS requestDTO)  {

        if(requestDTO.getUserAuthStatus()==null || requestDTO.getUserId()==null) {
            //请求参数为空
            return new UpdateUserAuthStatusByIdVRU(WEB_MSG_4.getCode(), WEB_MSG_4.getMsg());
        }


        //冻结判断
        UserBlackListInfoModel userBlackListInfoModel = new UserBlackListInfoModel();
        userBlackListInfoModel.setUserId(requestDTO.getUserId());
        userBlackListInfoModel.setbType(UserConstant.USER_BLACK_TYPE_FREEZE);

        //查询用户
        userBlackListInfoModel = userBlackListInfoDao.getUserBlackByType(userBlackListInfoModel);
        if(userBlackListInfoModel != null  && userBlackListInfoModel.getDeleted().equals(Integer.parseInt(SystemConstant.DELETE_NO))){
            return new UpdateUserAuthStatusByIdVRU(WEB_MSG_6.getCode(), WEB_MSG_6.getMsg());
        }

        //查询当前状态
        UserInfoModel userInfoModel = userDao.selectUserAuthStatusById(requestDTO.getUserId());
            //目前认证状态为认证中
        if(!userInfoModel.getUserAuthStatus().equals(UserConstant.AUTH_STATUS_ONGOING)){
            //状态有误
            return new UpdateUserAuthStatusByIdVRU(WEB_MSG_5.getCode(), WEB_MSG_5.getMsg());
        }
        //且传入的参数为 通过或者失败
        if(!requestDTO.getUserAuthStatus().equals(UserConstant.AUTH_STATUS_FAILURE) && !requestDTO.getUserAuthStatus().equals(UserConstant.AUTH_STATUS_SUCCESSFUL)){
            //参数有误
            return new UpdateUserAuthStatusByIdVRU(WEB_MSG_3.getCode(),WEB_MSG_3.getMsg());
         }

         //修改
         userInfoModel.setUserAuthStatus(requestDTO.getUserAuthStatus());
       Integer flag =   userDao.updateUserAuthStatusById(userInfoModel);

        if(flag<1){
            //修改失败
            return new UpdateUserAuthStatusByIdVRU(WEB_MSG_2.getCode(),WEB_MSG_2.getMsg());
        };
        //修改成功
        return new UpdateUserAuthStatusByIdVRU(WEB_MSG_1.getCode(),WEB_MSG_1.getMsg());

    }

    /**
     * 根据用户ID修改【头像认证】状态
     * @param requestDTO
     */
    public UpdateIconStatusByIdVRU updateIconStatusById(UpdateIconStatusByIdRTS requestDTO)  {

        if(requestDTO.getUserHeadStatus() ==null || requestDTO.getUserId()==null) {
            //请求参数为空
            return new UpdateIconStatusByIdVRU(WEB_MSG_4.getCode(), WEB_MSG_4.getMsg());
        }


        //冻结判断
        UserBlackListInfoModel userBlackListInfoModel = new UserBlackListInfoModel();
        userBlackListInfoModel.setUserId(requestDTO.getUserId());
        userBlackListInfoModel.setbType(UserConstant.USER_BLACK_TYPE_FREEZE);

        //查询用户
        userBlackListInfoModel = userBlackListInfoDao.getUserBlackByType(userBlackListInfoModel);
        if(userBlackListInfoModel != null  && userBlackListInfoModel.getDeleted().equals(Integer.parseInt(SystemConstant.DELETE_NO))){
            return new UpdateIconStatusByIdVRU(WEB_MSG_6.getCode(), WEB_MSG_6.getMsg());
        }



        UserInfoModel userInfoModel = userDao.selectIconStatusById(requestDTO.getUserId());
         //不为空，且目前认证状态为认证中
        if(!userInfoModel.getIconStatus().equals(UserConstant.ICON_STATUS_ONGOING)){
            return new UpdateIconStatusByIdVRU(WEB_MSG_5.getCode(), WEB_MSG_5.getMsg());
         }

         //判断请求参数
        if(!requestDTO.getUserHeadStatus().equals(UserConstant.ICON_STATUS_FAILURE) && !requestDTO.getUserHeadStatus().equals(UserConstant.ICON_STATUS_SUCCESSFUL)){
            return new UpdateIconStatusByIdVRU(WEB_MSG_3.getCode(), WEB_MSG_3.getMsg());
        }

        userInfoModel.setIconStatus(requestDTO.getUserHeadStatus());
        Integer flag =  userDao.updateIconStatusById(userInfoModel);

        if(flag<1){
            //修改失败
            return new UpdateIconStatusByIdVRU(WEB_MSG_2.getCode(),WEB_MSG_2.getMsg());
        };

        return new UpdateIconStatusByIdVRU(WEB_MSG_1.getCode(),WEB_MSG_1.getMsg());
    }


    /**
     * 根据用户ID【修改用户信息】
     * @param requestDTO
     */
    public UpdateUserInformationByIdVRU updateUserInformationById(UpdateUserInformationByIdRTS requestDTO) {

        //检测用户是否被冻结

        UserBlackListInfoModel userBlackListInfoModel = new UserBlackListInfoModel();
        userBlackListInfoModel.setUserId(requestDTO.getUserId());
        userBlackListInfoModel.setbType(UserConstant.USER_BLACK_TYPE_FREEZE);

        //查询用户
        userBlackListInfoModel = userBlackListInfoDao.getUserBlackByType(userBlackListInfoModel);
        if(userBlackListInfoModel != null  && userBlackListInfoModel.getDeleted().equals(Integer.parseInt(SystemConstant.DELETE_NO))){
            return new UpdateUserInformationByIdVRU(WEB_MSG_6.getCode(), WEB_MSG_6.getMsg());
        }


        if(!requestDTO.getUserType().equals(UserConstant.USER_TYPE_DRIVER) && !requestDTO.getUserType().equals(UserConstant.USER_TYPE_OWNER)){
            //参数有误
            return new UpdateUserInformationByIdVRU(WEB_MSG_3.getCode(),WEB_MSG_3.getMsg());
        }

        //info表
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setId(requestDTO.getUserId());
        //用户ID
        userInfoModel.setUserType(requestDTO.getUserType());

        userDao.updateUserTypeById(userInfoModel);

        //other表
        UserOtherInfoModel model = new UserOtherInfoModel();
        //用户ID
        model.setUserId(requestDTO.getUserId());
        //手机号码
        model.setTelephone(requestDTO.getTelephone());
        //公司名称
        model.setCompanyName(requestDTO.getCompanyName());
        //经营地址ID
        model.setBusinessAddressCityId(requestDTO.getBusinessAddressCityId());
        //经营地址
        model.setBusinessAddress(requestDTO.getBusinessAddress());
        //常驻地 三级分地址
        model.setResident1(requestDTO.getResident1());
        model.setResident2(requestDTO.getResident2());
        model.setResident3(requestDTO.getResident3());
        //常住地址ID
        model.setResidentCityId(requestDTO.getResidentCityId());

        Integer flag =  userOtherInfoDao.updateTelephoneByUid(model);
        if(flag.equals(UserConstant.USER_OPERATION_SUCCESS)){
            return new UpdateUserInformationByIdVRU(WEB_MSG_1.getCode(),WEB_MSG_1.getMsg());
        }else{
            return new UpdateUserInformationByIdVRU(WEB_MSG_2.getCode(),WEB_MSG_2.getMsg());
        }
    }

    /**
     * 冻结用户
     * @param frostUserByIdRTS
     * @return
     */
    @Override
    public FrostUserByIdVRU frostUserById(FrostUserByIdRTS frostUserByIdRTS) {
        //1.查询用户是否已经被冻结（查询这个状态的数据）
        //2.如果这个用户存在 并且删除状态为0(没删除) 就抛错（已被冻结）


        UserBlackListInfoModel model = new UserBlackListInfoModel();
        model.setUserId(frostUserByIdRTS.getUserId());
        model.setbType(UserConstant.USER_BLACK_TYPE_FREEZE);

        //查询用户
        UserBlackListInfoModel userBlackListInfoModel = userBlackListInfoDao.getUserBlackByType(model);

        //新增冻结
        if(userBlackListInfoModel == null){
            Integer flag = userBlackListInfoDao.insertUserBlackList(model);
            return  new FrostUserByIdVRU(WEB_MSG_1.getCode(), WEB_MSG_1.getMsg());
        }

        //用户已被冻结
        if(userBlackListInfoModel.getDeleted().equals(Integer.parseInt(SystemConstant.DELETE_NO))){
            return new FrostUserByIdVRU(WEB_MSG_6.getCode(), WEB_MSG_6.getMsg());
        }

        //如果已被删除那就移除
        if (userBlackListInfoModel.getDeleted().equals(Integer.parseInt(SystemConstant.DELETE_YES))){

            userBlackListInfoModel.setDeleted(Integer.parseInt(SystemConstant.DELETE_NO));
            userBlackListInfoModel.setUpdateTime(DateUtil.now());
            userBlackListInfoDao.frostUserBlackById(userBlackListInfoModel);
            return  new FrostUserByIdVRU(WEB_MSG_1.getCode(), WEB_MSG_1.getMsg());
        }


        return  new FrostUserByIdVRU(WEB_MSG_2.getCode(), WEB_MSG_2.getMsg());
    }


    /**
     * 取消用户冻结
     * @param abolishFrostUserByIdRTS
     * @return
     */
    @Override
    public AbolishFrostUserByIdVRU abolishFrostUserById(AbolishFrostUserByIdRTS abolishFrostUserByIdRTS) {

        UserBlackListInfoModel model = new UserBlackListInfoModel();
        model.setUserId(abolishFrostUserByIdRTS.getUserId());
        model.setbType(UserConstant.USER_BLACK_TYPE_FREEZE);

        model = userBlackListInfoDao.getUserBlackByType(model);

        //没有被冻结
        if(model == null || model.getDeleted().equals(Integer.parseInt(SystemConstant.DELETE_NO))){
            return  new AbolishFrostUserByIdVRU(WEB_MSG_7.getCode(), WEB_MSG_7.getMsg());
        }

        //移除冻结
        if (model.getDeleted().equals(Integer.parseInt(SystemConstant.DELETE_YES))){

            model.setDeleted(Integer.parseInt(SystemConstant.DELETE_NO));
            model.setUpdateTime(DateUtil.now());
            userBlackListInfoDao.frostUserBlackById(model);
            return  new AbolishFrostUserByIdVRU(WEB_MSG_1.getCode(), WEB_MSG_1.getMsg());
        }

        return  new AbolishFrostUserByIdVRU(WEB_MSG_2.getCode(), WEB_MSG_2.getMsg());
    }



}
