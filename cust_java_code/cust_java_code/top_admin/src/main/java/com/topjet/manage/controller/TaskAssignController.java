package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.TaskInfoModel;
import com.topjet.manage.mapper.readMapper.TaskBucketInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.TaskBucketInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.TaskInfoModelMapper;
import com.topjet.manage.service.TaskAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liyj on 2017/8/10.
 */
@Controller
@RequestMapping("/taskAssignAction/")
public class TaskAssignController extends BaseController {
    @Autowired
    private TaskAssignService taskAssignService;
    @Autowired
    private TaskBucketInfoModelMapper taskBucketInfoModelMapper;
    @Autowired
    private TaskBucketInfoModelEMapper taskBucketInfoModelEMapper;
    @Autowired
    private TaskInfoModelMapper taskInfoModelMapper;

    @ResponseBody
    @RequestMapping("list")
    public Object list(TaskBucketInfoModel taskBucketInfoModel){
        List<TaskBucketInfoModel> dataList = taskAssignService.getTaskList(taskBucketInfoModel);
        int total = taskAssignService.getTaskCount(taskBucketInfoModel);
        PaginationBean beans = new PaginationBean();;
        beans.setRows(dataList);
        beans.setTotal(total);
        return beans;
    }
    /**
     * 查询所有SysUser
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("assignAdd")
    public Object assignAdd(TaskBucketInfoModel taskBucketInfoModel){
        return taskAssignService.queryUser(taskBucketInfoModel);
    }

    /**
     * 更新内容
     *
     * @param tb
     * @return
     */
    @RequestMapping("updateOrAdd")
    @ResponseBody
    public ResultBaseMsg updateOrAdd(TaskBucketInfoModel tb, HttpServletRequest rq) {
        SysUserModel su = SessionUtils.getSysUserSession();
        if (tb.getId() == null) {
            String[] custList = rq.getParameter("cust").split(",");
            for (String cust : custList) {
                String[] names = cust.split(":");
                tb.setSysUserId(Integer.parseInt(names[1]));
                tb.setName(names[0]);
                tb.setCreateBy(su.getId());
                tb.setUpdateBy(su.getId());
                tb.setUpdateTime(DateUtil.now());
                tb.setCreateTime(DateUtil.now());
                tb.setType(tb.getType());
                List<TaskBucketInfoModel> tbList = taskAssignService.findByTypeAndUserId(tb);
                if (tbList.isEmpty()) {
                    taskBucketInfoModelMapper.insert(tb);
                }
            }
        }
        else{
            String flag = rq.getParameter("flag");
            Integer bucketId = tb.getId();
            if(flag.equals("1")){//flag=1 显示删除
                tb.setVersion(tb.getVersion() + 1);
                tb.setUpdateBy(su.getId());
                tb.setUpdateTime(DateUtil.now());
                tb.setDeleted(1);
                tb.setTaskCount(0);
                taskBucketInfoModelMapper.update(tb);
                List<TaskInfoModel> tiList = taskBucketInfoModelEMapper.findByBuckId(bucketId);
                for (TaskInfoModel ti : tiList) {
                    ti.setVersion(ti.getVersion() + 1);
                    ti.setBucketId(0);
                    ti.setUpdateTime(DateUtil.now());
                    ti.setUpdateBy(su.getId());
                    taskInfoModelMapper.update(ti);
                }
            }else if(flag.equals("3")){//显示开启按钮  则该状态是关闭
                tb.setVersion(tb.getVersion() + 1);
                tb.setUpdateBy(su.getId());
                tb.setUpdateTime(DateUtil.now());
                tb.setInvalid(1);
                taskBucketInfoModelMapper.update(tb);
            }else if(flag.equals("2")){//显示关闭按钮  则该状态是开启
                tb.setVersion(tb.getVersion() + 1);
                tb.setUpdateBy(su.getId());
                tb.setUpdateTime(DateUtil.now());
                tb.setInvalid(2);
                tb.setTaskCount(0);
                taskBucketInfoModelMapper.update(tb);
                List<TaskInfoModel> tiList = taskBucketInfoModelEMapper.findByBuckId(bucketId);
                for (TaskInfoModel ti : tiList) {
                    ti.setVersion(ti.getVersion() + 1);
                    ti.setBucketId(0);
                    ti.setUpdateTime(DateUtil.now());
                    ti.setUpdateBy(su.getId());
                    taskInfoModelMapper.update(ti);
                }
            }
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }


}
