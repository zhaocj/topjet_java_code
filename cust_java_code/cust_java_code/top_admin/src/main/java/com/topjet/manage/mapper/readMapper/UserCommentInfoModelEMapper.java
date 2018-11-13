package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.UserCommentBean;
import com.topjet.manage.domain.model.UserCommentInfoModel;
import com.topjet.manage.mapper.writeMapper.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/11/3.
 */
public interface UserCommentInfoModelEMapper extends BaseMapper<UserCommentBean> {

    /**
     * 查看全部评价信息分页
     */
    @SelectProvider(type = UserCommentInfoModelEMapper.SubscribeLineProviderSql.class,method = "getCommentList")
    public List<UserCommentBean> getCommentList(UserCommentBean userCommentBean);
    /**
     * 评价信息页数
     */
    @SelectProvider(type = UserCommentInfoModelEMapper.SubscribeLineProviderSql.class,method = "getCommentCount")
    public Integer getCommentCount(UserCommentBean userCommentBean);


    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 评价信息页数
         *
         */
        public String getCommentCount(final UserCommentBean userCommentBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT uci.id) ");
            sb.append(" FROM ");
            sb.append(" userDB.userCommentInfo uci ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userCommentPhotoInfo ucp on uci.id = ucp.commentId ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo ud ON uci.commentUser=ud.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.userAuditHistory ush ON ud.id = ush.sourceId ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo ub ON uci.commentedUser=ub.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  orderDB.orderInfo oi ON uci.orderId=oi.id ");
            sb.append("  WHERE 1=1 and uci.deleted=0 AND ud.deleted=0 AND ub.deleted=0 ");
            if (userCommentBean.getStartTime() != null){
                sb.append(" and DATE_FORMAT(uci.createTime,'%Y/%m/%d') >= DATE_FORMAT(#{startTime},'%Y/%m/%d')");
            }
            if (userCommentBean.getEndTime() != null){
                sb.append(" and DATE_FORMAT(uci.createTime,'%Y/%m/%d') <= DATE_FORMAT(#{endTime},'%Y/%m/%d')");
            }
            if (!StringUtils.isEmpty(userCommentBean.getSerialNo())){
                sb.append(" AND oi.orderNo = #{serialNo}");
            }
            if (!StringUtils.isEmpty(userCommentBean.getCommentUserMobile())){
                sb.append(" AND ud.mobile = #{commentUserMobile}");
            }
            if (!StringUtils.isEmpty(userCommentBean.getCommentUserType())){
                sb.append(" AND ud.userType = #{commentUserType}");
            }

            return sb.toString();
        }

        /**
         *查看全部评价信息分页
         */
        public String getCommentList(final UserCommentBean userCommentBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" uci.id AS id,uci.createTime as createTime,uci.content AS content,uci.total AS total,uci.type AS commentedUserType,uci.version as version,ud.username AS commentUserName,");
            sb.append(" ud.mobile AS commentUserMobile,ud.userType AS commentUserType,ud.id AS commentId,ub.id AS commentedId,");
            sb.append(" ub.username AS commentedUserName,ub.mobile AS commentedUserMobile,oi.orderNo as serialNo,oi.id as orderId,IFNULL(ush.remark,'')as reditRemark,ucp.pictureUrl");
            sb.append(" FROM ");
            sb.append(" userDB.userCommentInfo uci ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userCommentPhotoInfo ucp on uci.id = ucp.commentId ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo ud ON uci.commentUser=ud.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.userAuditHistory ush ON ud.id = ush.sourceId ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo ub ON uci.commentedUser=ub.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  orderDB.orderInfo oi ON uci.orderId=oi.id ");
            sb.append("  WHERE 1=1 and uci.deleted=0 AND ud.deleted=0 AND ub.deleted=0 ");
            if (userCommentBean.getStartTime() != null){
                sb.append(" and DATE_FORMAT(uci.createTime,'%Y/%m/%d') >= DATE_FORMAT(#{startTime},'%Y/%m/%d')");
            }
            if (userCommentBean.getEndTime() != null){
                sb.append(" and DATE_FORMAT(uci.createTime,'%Y/%m/%d') <= DATE_FORMAT(#{endTime},'%Y/%m/%d')");
            }
            if (!StringUtils.isEmpty(userCommentBean.getSerialNo())){
                sb.append(" AND oi.orderNo = #{serialNo}");
            }
            if (!StringUtils.isEmpty(userCommentBean.getCommentUserMobile())){
                sb.append(" AND ud.mobile = #{commentUserMobile}");
            }
            if (!StringUtils.isEmpty(userCommentBean.getCommentUserType())){
                sb.append(" AND ud.userType = #{commentUserType}");
            }
            sb.append(" GROUP BY uci.id order by uci.id DESC  ");
            if(userCommentBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    /**
     * 根据id查询
     */
    @Select("SELECT DISTINCT ui.*,ucp.*,oi.orderNo from userDB.userCommentInfo ui LEFT JOIN userDB.userCommentPhotoInfo ucp on ui.id = ucp.commentId LEFT JOIN orderDB.orderInfo oi ON ui.orderId = oi.id where ui.id = #{id} GROUP BY ui.id")
    public UserCommentInfoModel findCommentById(@Param("id") Integer id);

    @Select("SELECT DISTINCT ui.*,ucp.*,oi.orderNo from userDB.userCommentInfo ui LEFT JOIN userDB.userCommentPhotoInfo ucp on ui.id = ucp.commentId LEFT JOIN orderDB.orderInfo oi ON ui.orderId = oi.id where ucp.commentId = #{id} GROUP BY ucp.id")
    public List<UserCommentInfoModel> findById(Integer id);



}
