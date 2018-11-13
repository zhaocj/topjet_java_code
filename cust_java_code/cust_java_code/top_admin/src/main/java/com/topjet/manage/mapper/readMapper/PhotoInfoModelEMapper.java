package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.PhotoInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PhotoInfoModelEMapper extends BaseEMapper<PhotoInfoModel> {

    @Select("select p.id,p.userId,p.url,p.urlTobe,p.type,p.createTime,p.updateTime,p.deleted from userDB.photoInfo p where p.type = 3  and p.userId = #{userId} and p.deleted = 0 GROUP BY p.id ORDER BY p.id DESC")
    public List<PhotoInfoModel> getPhotoList(@Param("userId") Integer userId);

}