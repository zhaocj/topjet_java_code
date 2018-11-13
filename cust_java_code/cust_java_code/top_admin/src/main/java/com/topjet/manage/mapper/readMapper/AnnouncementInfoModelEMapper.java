package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.AnnouncementInfoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by liyj on 2017/11/13.
 */
public interface AnnouncementInfoModelEMapper {

    /**
     * 公告管理查询分页
     */
    @SelectProvider(type = AnnouncementInfoModelEMapper.SubscribeLineProviderSql.class,method = "getAnnounceList")
    public List<AnnouncementInfoModel> getAnnounceList(AnnouncementInfoModel announcementInfoModel);
    /**
     * 公告管理页数
     */
    @SelectProvider(type = AnnouncementInfoModelEMapper.SubscribeLineProviderSql.class,method = "getAnnounceCount")
    public Integer getAnnounceCount(AnnouncementInfoModel model);

    //内部sql
    class SubscribeLineProviderSql {

        /**
         * 公告管理页数
         *
         */
        public String getAnnounceCount(final AnnouncementInfoModel announcementInfoModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" COUNT(DISTINCT a.id) ");
            sb.append(" FROM ");
            sb.append(" resourceDB.announcementInfo a ");
            sb.append("  WHERE a.deleted = 0 ");
            if (announcementInfoModel.getType() != null && announcementInfoModel.getType() > 0){
                sb.append("  AND a.type = #{type}");
            }
            if (announcementInfoModel.getId() != null && announcementInfoModel.getId() > 0){
                sb.append(" AND a.id = #{id}");
            }
            return sb.toString();
        }

        /**
         * 公告管理查询分页
         */
        public String getAnnounceList(final AnnouncementInfoModel announcementInfoModel){
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");
            sb.append(" DISTINCT ");
            sb.append(" a.id,a.priority,a.title,a.content,a.type,a.valid,a.beginDate,a.endDate,a.createTime,a.createBy,");
            sb.append(" a.deleted,a.updateTime,a.updateBy,a.version,a.redirectURL,a.sendDate");
            sb.append(" FROM ");
            sb.append(" resourceDB.announcementInfo a ");
            sb.append("  WHERE a.deleted = 0 ");
            if (announcementInfoModel.getType() != null && announcementInfoModel.getType() > 0){
                sb.append("  AND a.type = #{type}");
            }
            if (announcementInfoModel.getId() != null && announcementInfoModel.getId() > 0){
                sb.append(" AND a.id = #{id}");
            }
            sb.append(" GROUP BY a.id");
            if(announcementInfoModel.getOffset() >= 0){
                sb.append(" LIMIT #{offset} , #{limit} ");
            }
            return sb.toString();
        }
    }

    /**
     * 根据id查询公告
     */
    @Select("select distinct a.id,a.priority,a.title,a.content,a.type,a.valid,a.beginDate,a.endDate,a.createTime,a.createBy,a.deleted,a.updateTime,a.updateBy,a.version,a.redirectURL,a.sendDate from resourceDB.announcementInfo a  where a.id = #{id}")
    public AnnouncementInfoModel getAnnounceById(@Param("id") Integer id);



}
