package com.topjet.manage.mapper.writeMapper;

/**
 * @author zhaocj
 * @create 2017-08-02 16:23
 **/
import java.util.List;
import java.util.Map;


public interface BaseMapper<T> {

    Integer insert(T entity);

    Integer update(T entity);

}
