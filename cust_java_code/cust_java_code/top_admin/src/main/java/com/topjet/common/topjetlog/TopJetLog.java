package com.topjet.common.topjetlog;

import com.topjet.common.constants.SystemConfig;
import com.topjet.common.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

/**
 * 系统日志记录对外接口
 * Created by pengtao on 5/9/16.
 */
public class TopJetLog {


    private static final Logger LOG = LoggerFactory.getLogger(TopJetLog.class);
    /*
    消息中心日志开关
     */
    private static final String logSwitchSetting = SystemConfig.logSwitch;

    /*
    log4j日志开关
     */
    private static final String log4JSwitchSetting = SystemConfig.log4jSwitch;

    /*
    消息中心日志级别
     */
    private static final String logLevel = SystemConfig.logLevel;

    /*
    log4j 日志级别
     */
    private static final String log4jLevel = SystemConfig.log4jLevel;

    private static final boolean logSwitch = logSwitchSetting == null ? false : logSwitchSetting.equals("1");

    private static final boolean log4jSwitch = log4JSwitchSetting == null ? false : log4JSwitchSetting.equals("1");


    public static void info(String message) {

        try {
            if (log4jSwitch && log4jLevel.compareTo(TopJetLogLevel.info) <= 0) {
                appendMessage(LogThreadLocal.log4jMessage, message);
            }

            if (logSwitch && logLevel.compareTo(TopJetLogLevel.info) <= 0) {
                appendMessage(LogThreadLocal.logMessage, message);
            }
        } catch (Exception e) {
            LOG.error("日志组件异常" + e.getMessage());
            e.printStackTrace();
        }

    }


    public static String getLogLevel() {
        return logLevel;
    }

    public static void error(String message) {
        try {
            if (log4jSwitch && log4jLevel.compareTo(TopJetLogLevel.error) <= 0)
                appendMessage(LogThreadLocal.log4jMessage, message);
            if (logSwitch && logLevel.compareTo(TopJetLogLevel.error) <= 0)
                appendMessage(LogThreadLocal.logMessage, message);

        } catch (Exception e) {
            LOG.error("日志组件异常" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void debug(String message) {
        try {
            if (log4jSwitch && log4jLevel.compareTo(TopJetLogLevel.debug) <= 0) {
                appendMessage(LogThreadLocal.log4jMessage, message);
            }
            if (logSwitch && logLevel.compareTo(TopJetLogLevel.debug) <= 0) {
                appendMessage(LogThreadLocal.logMessage, message);

            }
        } catch (Exception e) {
            LOG.error("日志组件异常" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void warn(String message) {
        try {
            if (log4jSwitch && log4jLevel.compareTo(TopJetLogLevel.warn) <= 0)
                appendMessage(LogThreadLocal.log4jMessage, message);
            if (logSwitch && log4jLevel.compareTo(TopJetLogLevel.warn) <= 0) {
                appendMessage(LogThreadLocal.logMessage, message);
            }
        } catch (Exception e) {
            LOG.error("日志组件异常" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void fatal(String message) {
        try {
            if (log4jSwitch && log4jLevel.compareTo(TopJetLogLevel.fatal) <= 0)
                appendMessage(LogThreadLocal.log4jMessage, message);
            if (logSwitch && log4jLevel.compareTo(TopJetLogLevel.fatal) <= 0) {
                appendMessage(LogThreadLocal.logMessage, message);
            }
        } catch (Exception e) {
            LOG.error("日志组件异常" + e.getMessage());
            e.printStackTrace();
        }
    }


    private static String[] getClazzAndMethod() {

        String clazzName = "";
        String method = "";

        try {
            StackTraceElement stack[] = (new Throwable()).getStackTrace();
            int ix = 0;
            while (ix < stack.length) {
                StackTraceElement frame = stack[ix];
                String cname = frame.getClassName();
                if (cname.equals("com.topjet.common.topjetlog.TopJetLog")) {
                    break;
                }
                ix++;
            }
            // Now search for the first frame before the "Logger" class.
            while (ix < stack.length) {
                StackTraceElement frame = stack[ix];
                String cname = frame.getClassName();
                if (!cname.equals("com.topjet.common.topjetlog.TopJetLog")) {
                    // We've found the relevant frame.
                    clazzName = cname;
                    method = frame.getMethodName();
                    String strings[] = {clazzName, method};
                    return strings;
                }
                ix++;
            }
        } catch (Exception e) {
            LOG.error("日志组件获取调用类与方法报错:" + e.getMessage());
            e.printStackTrace();
        }

        String strings[] = {clazzName, method};
        return strings;

    }


    private static void appendMessage(ThreadLocal<String> threadLocal, String message) {

        String[] clazzMethod = getClazzAndMethod();
        String clazzName = clazzMethod[0];
        String method = clazzMethod[1];
        threadLocal.set("\r\n");
        threadLocal.set("[" + DateUtil.now() + " " + Thread
                .currentThread().getName() +
                "-"
                + Thread.currentThread().getId() + "-" + clazzName + ":" + method + "]--" + message);
//        threadLocal.set("\r\n");
    }

    /**
     * append stack message
     *
     * @param logLevel
     * @return
     */
    private static String getStackTrace(String logLevel) {

        if (1 == 1) return StringUtils.EMPTY;
        StringBuilder builder = new StringBuilder("\n");

        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        int ix = 0;
        while (ix < stack.length) {
            StackTraceElement frame = stack[ix];
            String cname = frame.getClassName();
            if (cname.equals("com.topjet.common.topjetlog.TopJetLog")) {
                break;
            }
            ix++;
        }

        while (ix < stack.length) {
            StackTraceElement frame = stack[ix];
            String cname = frame.getClassName();

            /*
             时间
            */
            builder.append(Calendar.getInstance().getTime());
            /*
            线程名称
            */
            builder.append(" [");
            builder.append(Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
            builder.append("] [");
            /*
            类名
             */
            builder.append(cname);
            builder.append("] [");
            /*
            方法名称
            */
            builder.append(frame.getMethodName());
            builder.append("] [");
            /*
            日志级别
             */
            builder.append(logLevel.toString());
            builder.append("]");
            builder.append("\n");

            ix++;
        }

        return builder.toString();

    }
}
