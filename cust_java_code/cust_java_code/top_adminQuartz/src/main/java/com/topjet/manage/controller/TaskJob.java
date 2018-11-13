package com.topjet.manage.controller;

import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TaskInfoModel;
import com.topjet.manage.mapper.readMapper.TaskBucketInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.TaskInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.TaskBucketInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.TaskInfoModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-04 11:03
 */


@Component
@Configurable
@EnableScheduling
public class TaskJob {

    private static final Logger logger = LoggerFactory.getLogger(TaskJob.class);

    @Autowired
    private TaskInfoModelMapper taskInfoModelMapper;

    @Autowired
    private TaskInfoModelEMapper taskInfoModelEMapper;

    @Autowired
    private TaskBucketInfoModelMapper taskBucketInfoModelMapper;

    @Autowired
    private TaskBucketInfoModelEMapper taskBucketInfoModelEMapper;



    /**
     * 创建任务  分配任务
     */
    @Scheduled(cron ="0 */1 * * * ?")
    public void createAndAssignTasks(){
        //创建任务
        logger.info("开始执行创建任务");
        createTasks();
        assignTasks();
        logger.info("结束任务分配结束");
    }




    public void createTasks(){

        //创建用户实名审核任务
        Integer count1 = taskBucketInfoModelEMapper.countBucketInfo(1);
        if(count1 > 0){
            List<TaskInfoModel> userAssignTasks = taskInfoModelEMapper.createUserAssignTasks();
            if(userAssignTasks != null && userAssignTasks.size()>0){
                for (TaskInfoModel userAssignTask : userAssignTasks) {
                    taskInfoModelMapper.insert(userAssignTask);
                }
            }
        }


        //创建用户身份认证审核任务
        Integer count2 = taskBucketInfoModelEMapper.countBucketInfo(2);
        if(count2 >0){
            List<TaskInfoModel> userStatusTasks = taskInfoModelEMapper.createUserStatusTasks();
            if(userStatusTasks != null && userStatusTasks.size() >0){
                for (TaskInfoModel userStatusTask : userStatusTasks) {
                    taskInfoModelMapper.insert(userStatusTask);
                }
            }
        }



        //创建用户头像审核任务
        Integer count10 = taskBucketInfoModelEMapper.countBucketInfo(10);
        if(count10 > 0){
            List<TaskInfoModel> userHeadTasks = taskInfoModelEMapper.createUserHeadTasks();
            if (userHeadTasks != null && userHeadTasks.size() > 0) {
                for (TaskInfoModel userHeadTask : userHeadTasks) {
                    taskInfoModelMapper.insert(userHeadTask);
                }
            }
        }


        //创建企业认证审核任务
        Integer count11 = taskBucketInfoModelEMapper.countBucketInfo(11);
        if(count11 > 0){
            List<TaskInfoModel> companyTasks = taskInfoModelEMapper.createCompanyTasks();
            if (companyTasks != null && companyTasks.size() > 0) {
                for (TaskInfoModel companyTask : companyTasks) {
                    taskInfoModelMapper.insert(companyTask);
                }
            }
        }


        //创建推荐补贴一审任务
//        Integer count8 = taskBucketInfoModelEMapper.countBucketInfo(8);
//        if(count8 > 0) {
//            List<TaskInfoModel> recoFirstTasks = taskInfoModelEMapper.createRecoFirstTasks();
//            if (recoFirstTasks != null && recoFirstTasks.size() > 0) {
//                for (TaskInfoModel recoFirstTask : recoFirstTasks) {
//                    taskInfoModelMapper.insert(recoFirstTask);
//                }
//            }
//        }

        //创建推荐补贴二审任务
//        Integer count9 = taskBucketInfoModelEMapper.countBucketInfo(9);
//        if(count9>0) {
//            List<TaskInfoModel> recoSecondTasks = taskInfoModelEMapper.createRecoSecondTasks();
//            if (recoSecondTasks != null && recoSecondTasks.size() > 0) {
//                for (TaskInfoModel recoSecondTask : recoSecondTasks) {
//                    taskInfoModelMapper.insert(recoSecondTask);
//                }
//            }
//        }
//        车辆认证审核
        Integer count3 = taskBucketInfoModelEMapper.countBucketInfo(3);
        if(count3 > 0){
            List<TaskInfoModel> userTruckTasks = taskInfoModelEMapper.createUserTruckTasks();
            if(userTruckTasks != null && userTruckTasks.size()>0){
                for (TaskInfoModel userTruckTask : userTruckTasks) {
                    taskInfoModelMapper.insert(userTruckTask);
                }
            }
        }


        //运费补贴一审
//        Integer count4 = taskBucketInfoModelEMapper.countBucketInfo(4);
//        if(count4>0){
//            List<TaskInfoModel> frightFirstTasks = taskInfoModelEMapper.createFrightFirstTasks();
//            if(frightFirstTasks != null && frightFirstTasks.size()>0){
//                for (TaskInfoModel frightFirstTask : frightFirstTasks) {
//                    taskInfoModelMapper.insert(frightFirstTask);
//                }
//            }
//        }
        //运费补贴二审
//        Integer count5 = taskBucketInfoModelEMapper.countBucketInfo(5);
//        if(count5>0){
//            List<TaskInfoModel> frightSecondTasks = taskInfoModelEMapper.createFrightSecondTasks();
//            if(frightSecondTasks !=null && frightSecondTasks.size()>0){
//                for (TaskInfoModel frightSecondTask : frightSecondTasks) {
//                    taskInfoModelMapper.insert(frightSecondTask);
//                }
//            }
//        }

    }


    public void assignTasks() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted",0);
        paramMap.put("bucketId",0);
        List<TaskInfoModel> taskList = taskInfoModelEMapper.selectListByParam(paramMap);
        if(!taskList.isEmpty()){
            for(TaskInfoModel tim : taskList){
                Integer integer = taskBucketInfoModelEMapper.countBucketInfo(tim.getType());
                if(integer > 0){
                    TaskBucketInfoModel tb =  taskInfoModelEMapper.getMinCountUserByType(tim.getType());
                    if(tb != null){
                        tim.setBucketId(tb.getId());
                        tim.setUpdateTime(new Date());
                        tim.setVersion(tim.getVersion()+1);
                        taskInfoModelMapper.update(tim);
                        tb.setTaskCount(tb.getTaskCount()+1);
                        tb.setUpdateTime(new Date());
                        tb.setVersion(tb.getVersion()+1);
                        taskBucketInfoModelMapper.update(tb);
                    }
                }
            }
        }
    }
}
