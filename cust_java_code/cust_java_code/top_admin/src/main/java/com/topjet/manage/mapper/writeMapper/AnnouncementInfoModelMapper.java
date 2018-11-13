package com.topjet.manage.mapper.writeMapper;

import com.topjet.manage.domain.model.AnnouncementInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by liyj on 2017/11/13.
 */
public interface AnnouncementInfoModelMapper {

    /**
     * 删除公告
     */
    @Update("UPDATE resourceDB.announcementInfo set deleted = 1 where id = #{id}")
    public Integer deleteAnnounceInfo(@Param("id") Integer id);
    /**
     * 修改公告
     */
    @UpdateProvider(type = AnnounceInfoProviderSql.class,method = "updateAnnounceInfo")
    public Integer updateAnnounceInfo(AnnouncementInfoModel announcementInfoModel);
    /**
     * 添加公告
     */
    @InsertProvider(type = AnnounceInfoProviderSql.class,method = "addAnnounceInfo")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer addAnnounceInfo(AnnouncementInfoModel announcementInfoModel);

    class AnnounceInfoProviderSql {
        public String addAnnounceInfo(final AnnouncementInfoModel announcementInfoModel){
            return new SQL(){{
                INSERT_INTO("resourceDB.announcementInfo");
                if (announcementInfoModel.getPriority() != null)
                    VALUES("priority","#{priority}");
                if (!StringUtils.isEmpty(announcementInfoModel.getTitle()))
                    VALUES("title","#{title}");
                if(!StringUtils.isEmpty(announcementInfoModel.getContent()))
                    VALUES("content","#{content}");
                if (announcementInfoModel.getType() != null)
                    VALUES("type","#{type}");
                if (announcementInfoModel.getBeginDate()!=null)
                    VALUES("beginDate","#{beginDate}");
                if (announcementInfoModel.getEndDate()!=null)
                    VALUES("endDate","#{endDate}");
                if (announcementInfoModel.getCreateTime()!=null)
                    VALUES("createTime","#{createTime}");
                if (announcementInfoModel.getUpdateTime()!=null)
                    VALUES("updateTime","#{updateTime}");
                if (announcementInfoModel.getValid()!=null)
                    VALUES("valid","#{valid}");
                if (announcementInfoModel.getDeleted()!=null)
                    VALUES("deleted","#{deleted}");
                if (announcementInfoModel.getVersion()!=null)
                    VALUES("version","#{version}");
                if (announcementInfoModel.getCreateBy()!=null)
                    VALUES("createBy","#{createBy}");
                if (announcementInfoModel.getUpdateBy()!=null)
                    VALUES("updateBy","#{updateBy}");
                if (!StringUtils.isEmpty(announcementInfoModel.getRedirectURL()))
                    VALUES("redirectURL","#{redirectURL}");
                if (!StringUtils.isEmpty(announcementInfoModel.getSendDate()))
                    VALUES("sendDate","#{sendDate}");
            }}.toString();
        }

        public String updateAnnounceInfo(final AnnouncementInfoModel announcementInfoModel){
            return new SQL(){{
                UPDATE("resourceDB.announcementInfo");
                if (announcementInfoModel.getPriority() != null)
                    SET("priority = #{priority}");
                if (!StringUtils.isEmpty(announcementInfoModel.getTitle()))
                    SET("title = #{title}");
                if(!StringUtils.isEmpty(announcementInfoModel.getContent()))
                    SET("content = #{content}");
                if (announcementInfoModel.getValid() != null)
                    SET("valid = #{valid}");
                if (announcementInfoModel.getBeginDate()!=null)
                    SET("beginDate = #{beginDate}");
                if (announcementInfoModel.getEndDate()!=null)
                    SET("endDate = #{endDate}");
                if (announcementInfoModel.getCreateTime()!=null)
                    SET("createTime = #{createTime}");
                if (announcementInfoModel.getUpdateTime()!=null)
                    SET("updateTime = #{updateTime}");
                if (announcementInfoModel.getType()!=null)
                    SET("type = #{type}");
                if (announcementInfoModel.getDeleted()!=null)
                    SET("deleted = #{deleted}");
                if (announcementInfoModel.getCreateBy()!=null)
                    SET("createBy = #{createBy}");
                if (announcementInfoModel.getVersion()!=null)
                    SET("version = #{version}");
                if (announcementInfoModel.getUpdateBy()!=null)
                    SET("updateBy = #{updateBy}");
                if (!StringUtils.isEmpty(announcementInfoModel.getRedirectURL()))
                    SET("redirectURL = #{redirectURL}");
                if (!StringUtils.isEmpty(announcementInfoModel.getSendDate()))
                    SET("sendDate = #{sendDate}");
                WHERE(" id = #{id}");
            }}.toString();
        }

    }



}
