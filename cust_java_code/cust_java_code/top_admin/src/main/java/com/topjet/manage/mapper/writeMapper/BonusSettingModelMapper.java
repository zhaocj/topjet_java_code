package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.BonusSettingModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/10/17.
 */
public interface BonusSettingModelMapper {
    /**
     * 添加补贴配置
     */
    @InsertProvider(type = BonusSettingProviderSql.class,method = "insertBonusSetting")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    Integer insertBonusSetting(BonusSettingModel bonusSettingModel);

    /**
     *修改补贴配置
     */
    @UpdateProvider(type = BonusSettingProviderSql.class,method = "updateBonusSetting")
    Integer updateBonusSetting(BonusSettingModel bonusSettingModel);


    class BonusSettingProviderSql {
        public String insertBonusSetting(final BonusSettingModel bonusSettingModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.bonusSetting");
                if (bonusSettingModel.getBonusType() != null)
                    VALUES("bonusType","#{bonusType}");
                if (bonusSettingModel.getBonusTarget()!=null)
                    VALUES("bonusTarget","#{bonusTarget}");
                if (bonusSettingModel.getEveryTimeAmount()!=null)
                    VALUES("everyTimeAmount","#{everyTimeAmount}");
                if (bonusSettingModel.getAmountType()!=null)
                    VALUES("amountType","#{amountType}");
                if (bonusSettingModel.getBonusScopeMin()!=null)
                    VALUES("bonusScopeMin","#{bonusScopeMin}");
                if (bonusSettingModel.getBonusScopeMax()!=null)
                    VALUES("bonusScopeMax","#{bonusScopeMax}");
                if (bonusSettingModel.getDeleted()!=null)
                    VALUES("deleted","#{deleted}");
                if (bonusSettingModel.getCreateBy()!=null)
                    VALUES("createBy","#{createBy}");
                if (bonusSettingModel.getCreateTime()!=null)
                    VALUES("createTime","#{createTime}");
                if (bonusSettingModel.getVersion()!=null)
                    VALUES("version","#{version}");
            }}.toString();
        }

        public String updateBonusSetting(final BonusSettingModel bonusSettingModel){
            return new SQL(){{
                UPDATE("resourceDB.bonusSetting");
                if (bonusSettingModel.getBonusType() != null)
                    SET("bonusType = #{bonusType}");
                if (bonusSettingModel.getBonusTarget()!=null)
                    SET("bonusTarget = #{bonusTarget}");
                if (bonusSettingModel.getEveryTimeAmount()!=null)
                    SET("everyTimeAmount = #{everyTimeAmount}");
                if (bonusSettingModel.getAmountType()!=null)
                    SET("amountType = #{amountType}");
                if (bonusSettingModel.getBonusScopeMin()!=null)
                    SET("bonusScopeMin = #{bonusScopeMin}");
                if (bonusSettingModel.getBonusScopeMax()!=null)
                    SET("bonusScopeMax = #{bonusScopeMax}");
                if (bonusSettingModel.getCreateTime()!=null)
                    SET("createTime = #{createTime}");
                if (bonusSettingModel.getDeleted()!=null)
                    SET("deleted = #{deleted}");
                if (bonusSettingModel.getCreateBy()!=null)
                    SET("createBy = #{createBy}");
                if (bonusSettingModel.getVersion()!=null)
                    SET("version = #{version}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }


}
