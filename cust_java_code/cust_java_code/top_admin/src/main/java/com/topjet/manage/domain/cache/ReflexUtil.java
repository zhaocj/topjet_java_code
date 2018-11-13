package com.topjet.manage.domain.cache;

import java.lang.reflect.Field;

/**
 * Created by tusm on 17/2/20.
 */
public class ReflexUtil {

    public static Integer getObjectFieldId(Object obj){
        Field declaredField = null;
        try {
            declaredField = obj.getClass().getDeclaredField("id");
            declaredField.setAccessible(true);
            return (Integer) declaredField.get(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
