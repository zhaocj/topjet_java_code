package com.topjet.manage.service;

import com.topjet.common.exception.pms.TopjetExceptionHandler;

/**
 * Created by liyj on 2017/8/25.
 */
public interface MatchOrderInfoService {

    /**
     * 货源隐藏
     */
    public Object hideOrder(Integer id) throws TopjetExceptionHandler;
}
