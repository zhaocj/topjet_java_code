package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.ScoreBean;
import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.model.ScoreInfoModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.mapper.writeMapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/10/16.
 */
public interface ScoreInfoModelEMapper extends BaseMapper<ScoreInfoModel> {
    /**
     * 积分人工发放列表
     */
    @SelectProvider(type = ScoreInfoModelEMapper.SubscribeLineProviderSql.class,method = "getScoreList")
    public List<ScoreBean> getScoreList(ScoreBean scoreBean);
    /**
     * 积分人工发放页数
     */
    @SelectProvider(type = ScoreInfoModelEMapper.SubscribeLineProviderSql.class,method = "getScoreCount")
    public Integer getScoreCount(ScoreBean scoreBean);


    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 积分人工发放页数
         *
         */
        public String getScoreCount(final ScoreBean scoreBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT s.id) ");
            sb.append(" FROM ");
            sb.append(" userDB.scoreInfo s ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo  u on s.userId = u.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userOtherInfo ui on ui.userId = u.id ");
            sb.append("  LEFT JOIN manageDB.sysUser uc on s.userId = uc.id ");
            sb.append("  WHERE s.type = 13 ");
            if (scoreBean.getMobile() != null && scoreBean.getMobile() != ""){
                sb.append("  and u.mobile = #{mobile}");
            }
            if (scoreBean.getCreateTime() != null){
                sb.append(" and DATE_FORMAT(s.createTime,'%Y/%m/%d') = DATE_FORMAT(#{createTime},'%Y/%m/%d')");
            }

            return sb.toString();
        }

        /**
         *积分人工发放列表
         */
        public String getScoreList(final ScoreBean scoreBean){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" s.id,  s.cause ,s.value,s.userId, s.balance, s.type, s.version,");
            sb.append(" s.createTime, u.mobile,u.username as name,uc.nickName as auditName");
            sb.append(" FROM ");
            sb.append(" userDB.scoreInfo s ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userInfo  u on s.userId = u.id ");
            sb.append("  LEFT JOIN ");
            sb.append("  userDB.userOtherInfo ui on ui.userId = u.id ");
            sb.append("  LEFT JOIN manageDB.sysUser uc on s.userId = uc.id ");
            sb.append("  WHERE s.type = 13 ");
            if (scoreBean.getMobile() != null && scoreBean.getMobile() != ""){
                sb.append("  and u.mobile = #{mobile}");
            }
            if (scoreBean.getCreateTime() != null){
                sb.append(" and DATE_FORMAT(s.createTime,'%Y/%m/%d') = DATE_FORMAT(#{createTime},'%Y/%m/%d')");
            }
            sb.append(" GROUP BY s.id ORDER BY s.id DESC  ");
            if(scoreBean.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    /**
     * 根据手机号查询
     */
    @Select("SELECT u.*,uo.* from userDB.userInfo u LEFT JOIN userDB.userOtherInfo uo on u.id = uo.userId where u.deleted = 0 and u.mobile = #{mobile}")
    public UserInfoBean findByMoblie(String mobile);


}
