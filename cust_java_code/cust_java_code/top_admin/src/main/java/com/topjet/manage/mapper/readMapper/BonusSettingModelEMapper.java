package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.BonusSettingModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/10/17.
 */
public interface BonusSettingModelEMapper {

    /**
     *查询有效的所有补贴设值
     */
    @SelectProvider(type = BonusSettingModelEMapper.SubscribeLineProviderSql.class,method = "getBounsSettingList")
    public List<BonusSettingModel> getBounsSettingList(BonusSettingModel bonusSettingModel);

    //内部sql
    class SubscribeLineProviderSql {
        /**
         * 查询有效的所有补贴设值
         */
        public String getBounsSettingList(final BonusSettingModel bonusSettingModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" b.id, b.bonusType, b.bonusTarget,b.everyTimeAmount, b.amountType,");
            sb.append(" b.bonusScopeMin,b.bonusScopeMax, b.createBy, b.createTime,b.deleted ,b.version");
            sb.append(" FROM ");
            sb.append(" resourceDB.bonusSetting  b ");
//            sb.append("  INNER JOIN ");
//            sb.append("  (select bonusType,MAX(id) as id from resourceDB.bonusSetting where 1 = 1 GROUP BY bonusType) a on b.id = a.id ");
            if (bonusSettingModel.getDeleted() != null ){
                sb.append(" WHERE deleted = #{deleted}");
            }
            if (bonusSettingModel.getBonusType() != null && bonusSettingModel.getBonusType() > 0){
                sb.append(" AND bonusType = #{bonusType}");

            }
            return sb.toString();
        }
    }

    /**
     * 根据bonusType查询补贴配置deleted = 2 的数据
     */
    @Select("SELECT  DISTINCT b.id, b.bonusType, b.bonusTarget,b.everyTimeAmount, b.amountType, b.bonusScopeMin,b.bonusScopeMax," +
            " b.createBy, b.createTime,b.deleted ,b.version FROM resourceDB.bonusSetting b where b.deleted = 2 and b.bonusType = #{bonusType} GROUP BY b.id")
    public List<BonusSettingModel> getBonusSettingByBonusType(@Param("bonusType") Integer bonusType);

    /**
     * 根据id查询补贴配置
     */
    @Select("SELECT  DISTINCT b.id, b.bonusType, b.bonusTarget,b.everyTimeAmount, b.amountType, b.bonusScopeMin,b.bonusScopeMax," +
            " b.createBy, b.createTime,b.deleted ,b.version FROM resourceDB.bonusSetting b where b.id = #{id} GROUP BY b.id")
    public BonusSettingModel findBonusSettingById(@Param("id") Integer id);

}
