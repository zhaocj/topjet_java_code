package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TaskInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-04 10:26
 */


@Mapper
@Repository
public interface TaskInfoModelEMapper extends BaseEMapper<TaskInfoModel> {

    /**
     * 查询符合实名认证审核条件用户
     * @return
     */
    @Select("select id as sourceId ,1 as type from userDB.userInfo where deleted = 0 and useStatus = 1 and id not in (select sourceId from manageDB.taskInfo where type = 1 and deleted = 0)")
    List<TaskInfoModel> createUserAssignTasks();

    /**
     * 查询符合身份认证审核条件用户
     * @return
     */
    @Select("select id as sourceId ,2 as type  from userDB.userInfo where deleted = 0 and useStatus = 2 and userAuthStatus = 1 and id not in (select sourceId from manageDB.taskInfo where type = 2  and deleted = 0)")
    List<TaskInfoModel> createUserStatusTasks();

    /**
     * 查询符合头像认证审核条件用户
     * @return
     */
    @Select("select id as sourceId ,10 as type from userDB.userInfo  where deleted = 0 AND iconStatus = 1 and id not in (select sourceId from manageDB.taskInfo where type = 10 and deleted = 0)")
    List<TaskInfoModel> createUserHeadTasks();

    /**
     * 查询符合推荐补贴一审认证审核条件用户
     * @return
     */
    @Select("select id as sourceId ,8 as type  from manageDB.recommendationBonusInfo  where deleted = 0 and auditStatus = 0  and id not in (select sourceId from manageDB.taskInfo where type = 8 and deleted = 0) ORDER BY createTime")
    List<TaskInfoModel> createRecoFirstTasks();

    /**
     * 查询符合推荐补贴二审条件用户
     * @return
     */
    @Select("SELECT a.id as sourceId,9 as type FROM manageDB.recommendationBonusInfo a LEFT JOIN  manageDB.taskInfo b ON a.id=b.sourceId AND b.type=9 AND b.deleted=0 WHERE a.auditStatus in (1,2,3) AND a.deleted=0 AND b.id IS NULL")
    List<TaskInfoModel> createRecoSecondTasks();

    /**
     * 查询符合车辆认证审核条件任务
     * @return
     */
    @Select("select dr.id as sourceId ,3 as type from  truckDB.driverTruckInfo dr, userDB.userInfo as us where dr.deleted = 0 and dr.auditStatus = 3 and dr.id not in (select sourceId from manageDB.taskInfo where type = 3 and deleted = 0) and dr.driverId=us.id and us.`useStatus`=2 and us.userAuthStatus=3")
    List<TaskInfoModel> createUserTruckTasks();

    /**
     * 运费补贴一审
     * @return
     */
    @Select("select id as sourceId ,4 as type from manageDB.orderBonusInfo  where deleted = 0 and type = 1 and auditStatus = 0 and id not in (select sourceId from manageDB.taskInfo where type = 4 and deleted = 0)")
    List<TaskInfoModel>createFrightFirstTasks();

    /**
     * 运费补贴二审
     * @return
     */
    @Select("select id as sourceId ,4 as type from manageDB.orderBonusInfo  where deleted = 0 and type = 1 and auditStatus in(1,2) and id not in (select sourceId from manageDB.taskInfo where type = 4 and deleted = 0)")
    List<TaskInfoModel>createFrightSecondTasks();

    @Select("SELECT * FROM taskInfo WHERE sourceId = #{sourceId} AND type = #{type} AND deleted = 0")
    List<TaskInfoModel> selectSameTask(@Param("sourceId")Integer sourceId, @Param("type") Integer type);



    /**
     * 查询符合企业认证审核条件用户
     * @return
     */
    @Select("select id as sourceId ,11 as type from userDB.userInfo  where deleted = 0 AND companyStatus = 1 AND userNature = 1 and id not in (select sourceId from manageDB.taskInfo where type = 11 and deleted = 0)")
    List<TaskInfoModel> createCompanyTasks();

    TaskBucketInfoModel getMinCountUserByType(Integer type);

}
