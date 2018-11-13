package com.topjet.manage.controller;

import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.model.CallCenterLogModel;
import com.topjet.manage.mapper.writeMapper.CallCenterLogModelMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by liyj on 2017/12/12.
 */

@Controller
@RequestMapping("/callCenterLog/")
public class CallCenterLogController {

    private static final Logger logger = LoggerFactory.getLogger(CallCenterLogController.class);

    @Autowired
    private CallCenterLogModelMapper callCenterLogModelMapper;

    @RequestMapping(value = "callCenterLog", method = RequestMethod.GET)
    @ResponseBody
    public void log(HttpServletRequest rq) {
        logger.info("*********进入通话记录保存接口*************:" + rq.getParameter(Parameter.CallSheetID));
        logger.info("##########进入通话记录保存接口##########:" + rq.getParameter(Parameter.Province));
        logger.info("!!!!!!!!!!!进入通话记录保存接口!!!!!!!!!!!:" + rq.getParameter(Parameter.District));
        try {
         /*   rq.setCharacterEncoding("UTF-8");*/
            String callNo = rq.getParameter(Parameter.CallNo);
            String calledNo = rq.getParameter(Parameter.CalledNo);
            String callSheetID = rq.getParameter(Parameter.CallSheetID);
            String callType = rq.getParameter(Parameter.CallType);
            String ring = rq.getParameter(Parameter.Ring);
            String begin = rq.getParameter(Parameter.Begin);
            String end = rq.getParameter(Parameter.End);
            String queueTime = rq.getParameter(Parameter.QueueTime);
            String agent = rq.getParameter(Parameter.Agent);
            String agentName = rq.getParameter(Parameter.AgentName);
            String queue = rq.getParameter(Parameter.Queue);
            String state = rq.getParameter(Parameter.State);
            String callState = rq.getParameter(Parameter.CallState);
            String actionID = rq.getParameter(Parameter.ActionID);
            String webcallActionID = rq.getParameter(Parameter.WebcallActionID);
            String recordFile = rq.getParameter(Parameter.RecordFile);
            String fileServer = rq.getParameter(Parameter.FileServer);
            String province = rq.getParameter(Parameter.Province);
            String district = rq.getParameter(Parameter.District);
            String callID = rq.getParameter(Parameter.CallID);
            String IVRKEY = rq.getParameter(Parameter.IVRKEY);
            String exten = rq.getParameter(Parameter.Exten);
            String queueName = rq.getParameter(Parameter.QueueName);

            CallCenterLogModel callCenterLogModel = new CallCenterLogModel();

            callCenterLogModel.setActionID(actionID);
            callCenterLogModel.setAgent(agent);
            callCenterLogModel.setAgentName(agentName);
            callCenterLogModel.setCallID(callID);
            try {
                if (!StringUtils.isEmpty(begin)) {
                    callCenterLogModel.setBegin(DateUtil.StringToDate(begin));
                }
            } catch (Exception e) {

                logger.error("*********begin 日期转化错误*********:" + begin);
            }
            callCenterLogModel.setCalledNo(calledNo);
            callCenterLogModel.setCallNo(callNo);
            callCenterLogModel.setCallSheetId(callSheetID);
            callCenterLogModel.setCallState(callState);
            callCenterLogModel.setCallType(callType);
            callCenterLogModel.setDistrict(district);

            try {
                if (!StringUtils.isEmpty(end)) {
                    callCenterLogModel.setEnd(DateUtil.StringToDate(end));
                }
            } catch (Exception e) {

                logger.error("*********end 日期转化错误*********:" + end);
            }

            callCenterLogModel.setExten(exten);
            callCenterLogModel.setFileServer(fileServer);
            callCenterLogModel.setIvrkey(IVRKEY);
            callCenterLogModel.setProvince(province);
            callCenterLogModel.setQueue(queue);
            callCenterLogModel.setQueueName(queueName);

            try {
                if (!StringUtils.isEmpty(queueTime)) {
                    callCenterLogModel.setQueueTime(DateUtil.StringToDate(queueTime));
                }
            } catch (Exception e) {

                logger.error("queueTime 日期转化错误:" + queueTime);
            }

            try {
                if (!StringUtils.isEmpty(ring)) {
                    callCenterLogModel.setRing(DateUtil.StringToDate(ring));
                }
            } catch (Exception e) {

                logger.error("ring 日期转化错误:" + ring);
            }
            callCenterLogModel.setRecordFile(recordFile);
            callCenterLogModel.setState(state);
            callCenterLogModel.setWebcallActionID(webcallActionID);

            callCenterLogModelMapper.insertSelective(callCenterLogModel);
            logger.info("*********通话记录保存成功*********:" + callCenterLogModel.getCallNo());
        } catch (Exception e) {

            logger.error("*********通话记录保存异常*********" + e.getMessage());
        }

    }


    String changeEncode(String s) {

        String result = "";
        try {
            result = new String(s.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return result;
    }

}

class Parameter {
    public static final String CallNo = "CallNo";//主叫号码
    public static final String CalledNo = "CalledNo";//被叫号码
    public static final String CallSheetID = "CallSheetID";//通话记录ID,CallSheetID 是这条通话记录再DB中的唯一id
    public static final String CallType = "CallType";//通话类型：dialout外呼通话,normal普通来电,transfer转接电话,dialTransfer外呼转接
    public static final String Ring = "Ring";//通话振铃时间（话务进入呼叫中心系统的时间）
    public static final String Begin = "Begin";//通话接通时间（呼入是按座席接起的时间,外呼按客户接起的时间,如果没接听的话为空）
    public static final String End = "End";//通话结束时间
    public static final String QueueTime = "QueueTime";//来电进入技能组时间
    public static final String Agent = "Agent";//处理坐席的工号
    public static final String Exten = "Exten";//处理坐席的工号,历史原因该字段与Agent相同
    public static final String AgentName = "AgentName";//处理坐席的姓名
    public static final String QueueName = "QueueName";//处理坐席的姓名
    public static final String Queue = "Queue";//通话进入的技能组名称
    public static final String State = "State";//接听状态：dealing（已接）,notDeal（振铃未接听）,leak（ivr放弃）,queueLeak（排队放弃）,blackList（黑名单）,voicemail（留言）
    public static final String CallState = "CallState";//事件状态：Ring, Ringing, Link, Hangup(Unlink也当成Hangup处理)
    public static final String ActionID = "ActionID";//通过外呼接口调用时,该字段会保存请求的actionID,其它情况下该字段为空
    public static final String WebcallActionID = "WebcallActionID";//通过调用webcall接口,该字段会保存请求的actionID,其它情况下该字段为空
    public static final String RecordFile = "RecordFile";//通话录音文件名：用户要访问录音时,在该文件名前面加上服务路径即可,如：FileServer/RecordFile
    public static final String FileServer = "FileServer";//通过FileServer中指定的地址加上RecordFile的值可以获取录音
    public static final String Province = "Province";//目标号码的省,例如北京市。呼入为来电号码,呼出为去电号码
    public static final String District = "District";//目标号码的市,例如北京市。呼入为来电号码,呼出为去电号码
    public static final String CallID = "CallID";//通话ID,通话连接的在系统中的唯一标识。CallID 是在通话进行中channel的id,可以用这个id来挂断通话之类的操作。一个call有一个CallID,但一个call可能会出现在多个通话中,比如转接。
    public static final String IVRKEY = "IVRKEY";//通话在系统中选择的按键菜单,10004@0。格式为：按键菜单的节点编号@选择的菜单按键。如果为多级菜单则为：10004@0-10005@1。
    public static final String AccountId = "AccountId";//账户编号字段,默认不推送有需求的客户对接时联系七陌技术支持人员进行开通
    public static final String AccountName = "AccountName";//账户名称字段,默认不推送有需求的客户对接时联系七陌技术支持人员进行开通
}
