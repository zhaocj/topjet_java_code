package com.topjet.manage.mapper.readMapper;

/**
 * @author zhaocj
 * @create 2017-08-02 16:23
 **/
import java.util.List;
import java.util.Map;


public interface BaseEMapper<T> {

    T selectByPrimaryKey(Integer id);

    List<T> selectListByParam(Map<String, Object> paramMap);

    List<T> selectPageListByParam(Map<String, Object> paramMap);

    Integer getCountByParam(Map<String, Object> paramMap);
}
