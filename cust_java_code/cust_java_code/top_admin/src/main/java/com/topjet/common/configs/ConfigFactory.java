package com.topjet.common.configs;

import com.topjet.common.topjetlog.TopJetLog;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pengtao on 8/20/16.
 */
class ConfigFactory {
    private static Logger log = LoggerFactory.getLogger(Config.class);

    static PropertiesConfiguration mobileMessageConfig;

    static PropertiesConfiguration walletConfig;

    static {
        loadConfig();
    }

    static void loadConfig() {

        try {

            walletConfig = new PropertiesConfiguration(ConfigPath.WalletConfig_PATH);
            TopJetLog.info("加载钱包全局配置文件完毕");
        } catch (ConfigurationException e) {
            TopJetLog.error(" 加载钱包全局配置文件出错: " + ConfigPath.WalletConfig_PATH + ":" + e.getMessage());
        }


        try {
            mobileMessageConfig = new PropertiesConfiguration(ConfigPath.MobileMessageConfig_PATH);
            TopJetLog.info("加载短信全局配置文件完毕");
        } catch (ConfigurationException e) {
            TopJetLog.error(" 加载短信全局配置文件出错: " + ConfigPath.WalletConfig_PATH + ":" + e.getMessage());
        }
    }
}