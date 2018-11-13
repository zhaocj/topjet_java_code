package com.topjet.manage.framwork.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.topjetlog.TopJetLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-02 12:19
 */

@ControllerAdvice(
        basePackages = {"com.topjet"}
)
public class HandlerException implements HandlerExceptionResolver{
    private static final Logger logger = LoggerFactory.getLogger(HandlerException.class);

    public HandlerException() {
    }


    @Override
    @ExceptionHandler({Exception.class})
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,Object handler,Exception ex){
        String xRequestedWith = request.getHeader("X-Requested-With");
        if(!StringUtils.isBlank(xRequestedWith)){
            ExceptionResultData(response, ex);
            return null;
        }else{
            return new ModelAndView("view/main/err",ExceptionResultView(response,ex));
        }
    }




    private  void ExceptionResultData(HttpServletResponse response,Exception ex) {
        response.setContentType("application/json;charset=UTF-8");
        ResultBaseMsg msg=new ResultBaseMsg();
        logger.error(ex.getMessage());
        if(ex instanceof TopjetExceptionHandler) {
            if(StringUtils.isBlank(((TopjetExceptionHandler) ex).getStatus()) || StringUtils.isBlank(((TopjetExceptionHandler) ex).getMsg())){
                msg.setMsg(ExceptionEnum.E_BUSINESS_MSG.getMsg());
                msg.setStatus(ExceptionEnum.E_BUSINESS_MSG.getStatus());
            }else{
                msg.setStatus(((TopjetExceptionHandler) ex).getStatus());
                msg.setMsg(((TopjetExceptionHandler) ex).getMsg());
            }
        }else{
            StringWriter sw=new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            System.out.println(pw.toString());
            logger.error("系统异常："+ pw.toString());
            //ExceptionSend(sw.toString());
            pw.flush();
            pw.close();
            msg.setMsg(ExceptionEnum.E_SYS_MSG.getMsg());
            msg.setStatus(ExceptionEnum.E_SYS_MSG.getStatus());
        }
        logger.debug(ex.getMessage());
        try {
            PrintWriter out = response.getWriter();
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(out, msg);
            //out.print();
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        }
    }

    private  Map<String, Object> ExceptionResultView(HttpServletResponse response,Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        logger.error(ex.getMessage());
        if(ex instanceof TopjetExceptionHandler) {
            if(StringUtils.isBlank(((TopjetExceptionHandler) ex).getStatus()) || StringUtils.isBlank(((TopjetExceptionHandler) ex).getMsg())){
                model.put("status",ExceptionEnum.E_BUSINESS_MSG.getStatus());
                model.put("msg",ExceptionEnum.E_BUSINESS_MSG.getMsg());
            }else{
                model.put("status",((TopjetExceptionHandler) ex).getStatus());
                model.put("msg",((TopjetExceptionHandler) ex).getMsg());
            }
        }else{
            StringWriter sw=new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            System.out.println(pw.toString());
            logger.error("系统异常："+ pw.toString());
            pw.flush();
            pw.close();
            model.put("status",ExceptionEnum.E_SYS_MSG.getStatus());
            model.put("msg",ExceptionEnum.E_SYS_MSG.getMsg());
        }

        return model;
    }

}