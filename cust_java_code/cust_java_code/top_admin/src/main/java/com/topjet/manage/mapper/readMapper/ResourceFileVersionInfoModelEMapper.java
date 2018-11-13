package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.ResourceFileKeyInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liyj on 2017/9/19.
 */
@Mapper
@Repository
public interface ResourceFileVersionInfoModelEMapper {

    //根据类型  以及systemType查询 ResourceFileVersionInfo表
    @Select("select r.id,r.resourceType,r.systemType,r.systemVersion,r.innoVersion,r.description,r.startTime,r.endTime,r.deleted\n" +
            "from resourceDB.resourceFileVersionInfo r where r.deleted = 0 and r.resourceType = #{resourceType}  and r.systemType = #{systemType} ")
    public List<ResourceFileVersionInfoModel> getResourceHomeList(@Param("resourceType") Integer resourceType,@Param("systemType")Integer systemType);


    //根据类型查询
    @Select("select r.id,r.resourceType,r.systemType,r.systemVersion,r.innoVersion,r.description,r.startTime,r.endTime,r.deleted\n" +
            "from resourceDB.resourceFileVersionInfo r where r.deleted = 0 and r.resourceType = #{resourceType}")
    public List<ResourceFileVersionInfoModel> getResourceList(@Param("resourceType") Integer resourceType);

    //根据id查询
    @Select("select r.id,r.resourceType,r.systemType,r.systemVersion,r.innoVersion,r.description,r.startTime,r.endTime,r.deleted\n" +
            "from resourceDB.resourceFileVersionInfo r where r.deleted = 0 and r.id = #{id}")
    public ResourceFileVersionInfoModel getResourceById(@Param("id") Integer id);

    //根据类型  以及systemType查询 resourceFileKeyInfo表
    @Select("select r.id,r.resourceType,r.systemType,r.systemVersion,r.innoVersion,r.resourceKey\n" +
            "from resourceDB.resourceFileKeyInfo r where r.resourceType = #{resourceType}  and r.systemType = 0")
    public List<ResourceFileKeyInfoModel> getResourceKeyList(@Param("resourceType") Integer resourceType);

    //根据类型查询resourceFileKeyInfo表  城市
    @Select("select r.id,r.resourceType,r.systemType,r.systemVersion,r.innoVersion,r.resourceKey\n" +
            "from resourceDB.resourceFileKeyInfo r where  r.resourceType = #{resourceType}")
    public List<ResourceFileKeyInfoModel> getResourceFileKeyList(@Param("resourceType") Integer resourceType);
}
