package com.topjet.manage.domain.cache;

import java.util.Map;

/**
 * Created by tusm on 17/2/19.
 */
public class ThreadRequest {

    @SuppressWarnings("unchecked")
    static ThreadLocal threadLocal = new ThreadLocal();

    @SuppressWarnings("unchecked")
    public static void setCacheMap(Map<String,Object> cacheMap) {
        threadLocal.set(cacheMap);
    }

    public static Map<String,Object> getCacheMap(){
        return (Map) threadLocal.get();
    }

}
