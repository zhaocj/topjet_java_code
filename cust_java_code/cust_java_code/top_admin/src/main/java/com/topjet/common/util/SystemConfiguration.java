/**
 * Copyright(c) 2005 zjhcsoft Techonologies, Ltd.
 * <p/>
 * History:
 * 2010-3-4 14:10:33 Created by Tiwen
 */
package com.topjet.common.util;


import com.topjet.common.configs.Config;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统配置文件参获取类
 */
public class SystemConfiguration {
    private static Logger log = LoggerFactory.getLogger(SystemConfiguration.class);


    private static PropertiesConfiguration propConfig;

    public static final String CONFIG_PATH = "C:/usr/local/tomcat/560properties/560system.properties";
//    public static final String CONFIG_PATH = "\\usr\\local\\tomcat\\560properties\\560system.properties";
//    public static final String CONFIG_PATH = "/Users/tusm/Desktop/test.properties";

    static {
        try {
            propConfig = new PropertiesConfiguration(CONFIG_PATH);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            log.error("SystemConfiguration 加载全局配置文件出错: " + e.getMessage());
        }
    }


//	public  SystemConfiguration () {
//		try{
//			propConfig = new PropertiesConfiguration(CONFIG_PATH);
//		} catch (ConfigurationException e) {
//			e.printStackTrace();
//			log.error("SystemConfiguration 加载全局配置文件出错: " + e.getMessage());
//		}
//	}

    /**
     * 根据Key获得对应的value
     *
     * @param key
     * @return
     * @see
     */
    public static String getValueFromPropFile(String key) {
        return propConfig.getString(key);
    }


    public static void setValueFromProFile(String key,String value){
        propConfig.setProperty(key,value);
        try {
            propConfig.save();
        } catch (ConfigurationException e) {
            log.error("保存配置文件出错" + e.getClass() + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 刷新prop文件
     *
     * @see
     */
    public static void toRefleshPropFile() {
        try {
            Config.refreshConfig();
            propConfig.refresh();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }


    /**
     * 推送模式
     */
    public static final String PUSH_PRODUCTIONMODE = "productionMode";

    /**
     * 安卓货主版
     */
    public static final String ANDROID_OWNER_APPKEY = "android_owner_appkey";
    public static final String ANDROID_OWNER_MASTERSECRET = "android_owner_mastersecret";

    /**
     * 安卓司机版    测试
     */
    public static final String ANDROID_DRIVER_APPKEY = "android_driver_appkey";
    public static final String ANDROID_DRIVER_MASTERSECRET = "android_driver_mastersecret";


    /**
     * 钱包服务器地址
     */
    public static final String REQUESTADDRESS = "requestaddress";
    /**
     * 钱包 身份证认证接口 请求url
     */
    public static final String HTTP_IDCARDRECOG_URL = "http_idcardrecog_url";

    /**
     * 身份认证接口调用开关
     */
    public static final String ID_CHECK_WS__SWITCH = "id_check_ws_switch";

    /**
     * 短信接口发送开关
     */
    public static final String MOBILE_MESSAGE_SEND__SWITCH = "mobile_message_send_switch";

    /**
     * APP手机消息推送开关
     */
    public static final String APP_NOTIFICATION__SWITCH = "app_notification_switch";

    /*
     * 动态密码访问地址
     */
    public static final String DYNAMICPASSWORD_URL = "dynamicpassword_url";
      
    /*
     * 动态密码开关
     */

    public static final String DYNAMICPASSWORD_SWITCH = "dynamicpassword_switch";

    /**
     * 消息中心请求地址
     */
    public static final String MSG_CENTER_REQUESTADDRESS = "msgcenter_request_address";

    /**
     * 日志组件服务开关
     */
    public static final String TOPJET_LOG_SWITCH = "topjet_log_switch";

    /**
     * apache日志组件服务开关
     */
    public static final String TOPJET_LOG4J_SWITCH = "topjet_log4j_switch";


    /**
     * apache日志组件级别
     */
    public static final String TOPJET_LOG4J_LEVEL = "topjet_log4j_level";

    /**
     * apache日志组件级别
     */
    public static final String TOPJET_LOG_LEVEL = "topjet_log_level";


    /**
     * 日志组件服务redis地址
     */
    public static final String TOPJET_LOG_HOTS = "topjetlog_redis_host";

    /**
     * 日志组件服务redis端口
     */
    public static final String TOPJET_LOG_PORT = "topjetlog_redis_port";

    /**
     * 推荐补贴同一手机注册补贴开关 1 开 0 关
     */
    public static final String RECOMMENDATION_BONUS_SWITCH = "recommend_bonus_switch";

    /**
     * 用户行为日志服务redis地址
     */
    public static final String USER_ACTION_LOG_REDIS_HOTS = "user_action_log_redis_host";

    /**
     * 用户行为日志服务redis端口
     */
    public static final String USER_ACTION_LOG_REDIS_PORT = "user_action_log_redis_port";


    /**
     * 心跳机制请求间隔
     */
    public static final String TOPJET_APP_HEARTBEAT_INTERVAL = "topjet_app_heartbeat_interval";


    /**
     * 资源服务器请求地址
     */
    public static final String TOPJET_RESOURCE_URL = "topjet_resource_url";

    /**
     * APP AES加密key AES_偏移值
     */
    public static final String APP_AES_KEY = "app_aes_key";

    public static final String APP_AES_IVCODE = "app_aes_ivcode";

    /**系统拨打电话调查时间选项配置
     */
    public static final String  CALL_SURVEY_DURATION= "call_survey_duration";


}