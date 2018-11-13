package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.domain.vo.UserPushTokenVO;
import com.topjet.tool.common.constant.TopJetDBConstant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserInfoModelEMapper extends BaseEMapper<UserInfoModel> {

    public List<MemberAuditListResponseBean> getMemberAuditList(MemberAuditListRequestBean memberAuditListRequestBean);

    public int getMemberAuditListCount(MemberAuditListRequestBean memberAuditListRequestBean);

    public List<UserInfoModel> selectUserInfoByIdNo(Map<String, Object> paramMap);
    //会员查询列表
    public List<MemberListResponseBean> getMemberList(MemberListRequestBean memberListRequestBean);
    //会员查询页数
    public int getMemberCount(MemberListRequestBean memberListRequestBean);
    //会员冻结日志
    public List<UserAuditHistoryModel> getUserOperationLog(UserAuditHistoryModel userAuditHistoryModel);
    //根据手机号码查询
    public List<UserInfoModel> getMobile(String mobile);
    //根据userid 查询用户
    public UserInfoModel findUserById(Integer id);

    public UserInfoModel findById(Integer id);
    /*管理平台用户详情里的所有Count*/
    public UserInfoBean getAllCountById(Integer id);

    //头像认证审核查询列表
    public List<MemberAuditListResponseBean> getHeadList(MemberAuditListRequestBean memberAuditListRequestBean);
    //头像认证审核查询列表页数
    public int getHeadCount(MemberAuditListRequestBean memberAuditListRequestBean);
    //详情
    public UserInfoBean getUserInfo(Integer id);

    @Select("SELECT * FROM userDB.userInfo WHERE deleted = 0 AND mobile = #{mobile}")
    UserInfoModel selectUserByMobile (@Param("mobile") String mobile);
    /**
     * 根据userid version查询
     */
    @Select("SELECT * FROM userDB.userInfo WHERE deleted = 0 AND id = #{userId} and version = #{version}")
    public UserInfoModel findUserByVersion(Integer userId, Integer version);


    //通过uid 修改诚信值
    @Select(" CALL "+ TopJetDBConstant.DB_NAME_ORDER+". proc_credit_for_user(#{uid}) ")
    public BigDecimal getCreditByUserid(@Param("uid") Integer uid);

    /**
     * 根据userid 查询冻结状态
     */
    @Select("SELECT IFNULL(ub.deleted,1)as deleted  from userDB.userInfo u LEFT JOIN userDB.userBlackListInfo ub on u.id = ub.userId WHERE u.deleted = 0 and u.id = #{id}")
    public Integer findDeletedByUserId(Integer id);

    /**
     * 根据手机号查询  返回userInfo
     */
    @Select("SELECT u.* from userDB.userInfo u where u.mobile = #{mobile}  and deleted = 0")
    public List<UserInfoModel> getListByMobile(String mobile);

    @Select("SELECT id,ownerToken,driverToken FROM "+ TopJetDBConstant.DB_NAME_USER+".userPushTokenInfo WHERE userId = #{userId}")
    UserPushTokenVO getUserPushToken(@Param("userId") Integer userId);


    //企业认证审核查询列表
    public List<CompanyAuditBean> companyAuditList(CompanyAuditBean companyAuditBean);
    //企业认证审核查询列表页数
    public int getCompanyAuditCount(CompanyAuditBean companyAuditBean);


    public UserInfoBean getTmsUserInfo(Integer id);
}