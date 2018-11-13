package com.topjet.manage.domain.cache;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tusm on 17/2/18.
 */
public class StageCache {



    /**
     * 添加缓存对象
     * @param obj
     */
    public static void addCacheObj(Object obj) {
        try {
            if (obj == null) return;
            Map<String, Object> map = ThreadRequest.getCacheMap();
            if (map == null) {
                map = new HashMap<>();
            }
            Object obj1 = obj.getClass().newInstance();
            String objName = obj.getClass().getName();
            Integer id = ReflexUtil.getObjectFieldId(obj);
            BeanUtils.copyProperties(obj, obj1);
            map.put(objName + id, obj1);
            ThreadRequest.setCacheMap(map);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验缓存,重复字段设置为null
     *
     * @param obj1
     */
    public static Object checkCacheObj(Object obj1) {
        try {
            if (obj1 == null) return obj1;
            Object newObj = obj1.getClass().newInstance();
            BeanUtils.copyProperties(obj1, newObj);
            Map<String, Object> map = ThreadRequest.getCacheMap();
            if (map == null) return obj1;
//            Object id = newObj.getClass().getField("id").get(newObj);

//            Field declaredField = newObj.getClass().getDeclaredField("id");
//            declaredField.setAccessible(true);
//            Object id = declaredField.get(newObj);

            Integer id = ReflexUtil.getObjectFieldId(newObj);
            String objName = obj1.getClass().getName();
            Object oldObj = map.get(objName + id);
            if (oldObj == null) return obj1;
            // 相同字段设置为null, 如果两个对象完全相同,则返回null
            if (StageCache.filterNull(newObj, oldObj)) return null;
            return newObj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj1;
    }



    /**
     * @param obj
     * @param obj1
     * @return true : 两个对象中字段完全相同   false: 两个对象中有不同字段
     */
    private static boolean filterNull(Object obj, Object obj1) {
        boolean vr = true;
        try {
            Field[] fs = obj.getClass().getDeclaredFields();
            for (Field f : fs) {
                f.setAccessible(true);
                Object o = f.get(obj);
                Object o1 = f.get(obj1);
                if (!"serialVersionUID".equals(f.getName()) && !"id".equals(f.getName()) && o != null && o.equals(o1)) {
                    f.set(obj, null);
                }
                if( (o!=null && o1 ==null) || (o!=null && !o.equals(o1)) ){
                    vr = false;
                }
            }
            return vr;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return vr;
    }

}
