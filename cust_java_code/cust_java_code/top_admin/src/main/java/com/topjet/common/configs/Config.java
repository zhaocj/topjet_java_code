package com.topjet.common.configs;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取带参数的配置文件
 * <p/>
 * Created by pengtao on 8/20/16.
 */
public abstract class Config {

    private static Logger log = LoggerFactory.getLogger(Config.class);

    PropertiesConfiguration propConfig;

    abstract PropertiesConfiguration getConfig();

    public String getValue(String key, String... parameters) {
        propConfig = getConfig();
        String value = propConfig.getString(key);

        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }

        int i = 0;

        for (String parameter : parameters) {

            if (value.contains(getParameter(i))) {
                value = value.replace(getParameter(i), parameter);
            }
        }

        return value;
    }

    private String getParameter(int order) {
        return "{" + order + "}";
    }

    public static void refreshConfig() {
        ConfigFactory.loadConfig();
    }

}
