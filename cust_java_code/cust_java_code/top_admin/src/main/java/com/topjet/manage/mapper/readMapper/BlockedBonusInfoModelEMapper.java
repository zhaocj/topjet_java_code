package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.BlockedBonusBean;
import com.topjet.manage.mapper.writeMapper.BaseMapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/10/12.
 */
public interface BlockedBonusInfoModelEMapper extends BaseMapper<BlockedBonusBean> {
    /**
     * 被屏蔽补贴管理
     */
    @SelectProvider(type = BlockedBonusInfoModelEMapper.SubscribeLineProviderSql.class,method = "getBlockedList")
    public List<BlockedBonusBean> getBlockedList(BlockedBonusBean blockedBonusBean);
    /**
     * 被屏蔽补贴管理页数
     */
    @SelectProvider(type = BlockedBonusInfoModelEMapper.SubscribeLineProviderSql.class,method = "getBlockedCount")
    public Integer getBlockedCount(BlockedBonusBean blockedBonusBean);


    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 货被屏蔽补贴管理的总数
         *
         */
        public String getBlockedCount(final BlockedBonusBean blockedBonusBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT bi.id) ");
            sb.append(" FROM ");
            sb.append(" blockedBonusInfo bi ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo u1 on bi.userId = u1.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userOtherInfo uo on uo.userId = u1.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.recommendRelationship rr on bi.recommendationId = rr.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo u2 on rr.recommendedUserId = u2.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userOtherInfo ui on ui.userId = u2.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.userAuditHistory ush ON u2.id = ush.sourceId ");
            sb.append("  LEFT JOIN ");
            sb.append("  orderDB.orderInfo oi on bi.orderId = oi.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  orderDB.goodsInfo gi on oi.goodsId = gi.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  orderDB.goodsDetailInfo gd on gd.goodsId = gi.id ");
            sb.append("  WHERE bi.deleted = 0 ");

            if (blockedBonusBean.getResource() != null && blockedBonusBean.getResource() != ""){
                sb.append(" AND (u1.mobile = #{resource} or oi.orderNo = #{resource})");
            }
            if (blockedBonusBean.getBlockMobile() != null && blockedBonusBean.getBlockMobile() != ""){
                sb.append(" AND u1.mobile = #{blockMobile}");
            }
            if (blockedBonusBean.getStartDate() != null){
                sb.append(" and DATE_FORMAT(bi.createTime,'%Y/%m/%d') >= DATE_FORMAT(#{startDate},'%Y/%m/%d')");
            }
            if (blockedBonusBean.getEndDate() != null){
                sb.append(" and DATE_FORMAT(bi.createTime,'%Y/%m/%d') <= DATE_FORMAT(#{endDate},'%Y/%m/%d')");
            }
            if (blockedBonusBean.getType() != null && blockedBonusBean.getType() > 0){
                sb.append("  AND bi.type = #{type}");
            }
            if (blockedBonusBean.getReason() != null && blockedBonusBean.getReason() > 0){
                sb.append("  AND bi.reason = #{reason}");
            }
            if (blockedBonusBean.getRecoMobile() != null && blockedBonusBean.getRecoMobile() != ""){
                sb.append("  AND u2.mobile = #{recoMobile}");
            }

            return sb.toString();
        }

        /**
         * 查询被屏蔽补贴管理分页
         */
        public String getBlockedList(final BlockedBonusBean blockedBonusBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" bi.id, bi.orderId, bi.reason, bi.remark, bi.status, bi.createTime,");
            sb.append(" bi.deleted, bi.version, bi.updateTime, bi.updateBy,IFNULL(ush.remark,'')as reditRemark,");
            sb.append(" bi.amount, bi.userId, bi.userName, bi.type, u2.id as recommendationId,oi.orderNo,");
            sb.append(" u1.mobile as blockMobile,u2.mobile as recoMobile,IFNULL(u2.username,u1.username) AS recoName,uo.registeredIMEI as blockIMEI,");
            sb.append(" ui.registeredIMEI as recoIMEI,gd.depart1,gd.depart2,gd.depart3,gd.destination1,gd.destination2,gd.destination3");
            sb.append(" FROM ");
            sb.append(" blockedBonusInfo bi ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo u1 on bi.userId = u1.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userOtherInfo uo on uo.userId = u1.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.recommendRelationship rr on bi.recommendationId = rr.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo u2 on rr.recommendedUserId = u2.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userOtherInfo ui on ui.userId = u2.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  manageDB.userAuditHistory ush ON u2.id = ush.sourceId ");
            sb.append("  LEFT JOIN ");
            sb.append("  orderDB.orderInfo oi on bi.orderId = oi.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  orderDB.goodsInfo gi on oi.goodsId = gi.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  orderDB.goodsDetailInfo gd on gd.goodsId = gi.id ");
            sb.append("  WHERE bi.deleted = 0 ");

            if (blockedBonusBean.getResource() != null && blockedBonusBean.getResource() != ""){
                sb.append(" AND (u1.mobile = #{resource} or oi.orderNo = #{resource})");
            }
            if (blockedBonusBean.getBlockMobile() != null && blockedBonusBean.getBlockMobile() != ""){
                sb.append(" AND u1.mobile = #{blockMobile}");
            }
            if (blockedBonusBean.getStartDate() != null){
                sb.append(" and DATE_FORMAT(bi.createTime,'%Y/%m/%d') >= DATE_FORMAT(#{startDate},'%Y/%m/%d')");
            }
            if (blockedBonusBean.getEndDate() != null){
                sb.append(" and DATE_FORMAT(bi.createTime,'%Y/%m/%d') <= DATE_FORMAT(#{endDate},'%Y/%m/%d')");
            }
            if (blockedBonusBean.getType() != null && blockedBonusBean.getType() > 0){
                sb.append("  AND bi.type = #{type}");
            }
            if (blockedBonusBean.getReason() != null && blockedBonusBean.getReason() > 0){
                sb.append("  AND bi.reason = #{reason}");
            }
            if (blockedBonusBean.getRecoMobile() != null && blockedBonusBean.getRecoMobile() != ""){
                sb.append("  AND u2.mobile = #{recoMobile}");
            }
            sb.append(" GROUP BY bi.id ORDER BY bi.id DESC  ");
            if(blockedBonusBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

}
