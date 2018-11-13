package com.topjet.manage.service.impl;

import com.topjet.common.SessionUtils;
import com.topjet.common.constants.SystemConfig;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.AppSlideModel;
import com.topjet.manage.mapper.readMapper.AppSlideModelEMapper;
import com.topjet.manage.mapper.writeMapper.AppSlideModelMapper;
import com.topjet.manage.service.AppSlideService;
import com.topjet.tool.redis.pool.RedisDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.ShardedJedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-12-04 15:12
 */

@Service
@Transactional
public class AppSlideServiceImpl implements AppSlideService{

    @Autowired
    private AppSlideModelEMapper appSlideModelEMapper;

    @Autowired
    private AppSlideModelMapper appSlideModelMapper;

    @Autowired
    private RedisDataSource redisDataSource;

    @Autowired
    private SystemConfig systemConfig;



    @Override
    public PaginationBean listAppSlide(AppSlideModel appSlideModel) {
        PaginationBean page = new PaginationBean();

        Map<String,Object> paraMap = new HashMap<String,Object>();
        paraMap.put("isValid", CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
        paraMap.put("offset",appSlideModel.getOffset());
        paraMap.put("limit",appSlideModel.getLimit());
        paraMap.put("type",appSlideModel.getType());
        if(StringUtils.isBlank(appSlideModel.getTitle())){
            paraMap.put("title",appSlideModel.getTitle());
        }
        page.setRows(appSlideModelEMapper.selectPageListByParam(paraMap));
        page.setTotal(appSlideModelEMapper.getCountByParam(paraMap));
        return page;
    }

    @Override
    public Integer saveAppSlide(AppSlideModel appSlideModel) {
        appSlideModel.setAppSlideId(this.querySeqByRedis());
        appSlideModel.setCreateBy(SessionUtils.getSysUserSession().getId());
        appSlideModel.setCreateTime(DateUtil.now());
        appSlideModel.setIsValid(1);
        appSlideModel.setLink(systemConfig.getAppSlideUrl()+appSlideModel.getAppSlideId());
        return appSlideModelMapper.insert(appSlideModel);
    }

    @Override
    public AppSlideModel getAppSlideModelById(Integer id) {
        return appSlideModelEMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateAppSlide(AppSlideModel appSlideModel) {
        appSlideModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
        appSlideModel.setUpdateTime(DateUtil.now());
        return appSlideModelMapper.update(appSlideModel);
    }


    /**
     * 通过redis生成自增号
     * @return
     */
    private Integer querySeqByRedis(){
        ShardedJedis shardedJedis = null;
        Long number = 0L;
        try {
            shardedJedis = redisDataSource.getRedisClient();
            number = shardedJedis.incr("S_ADMIN_SYSTEM");
            if(number>=999999){
                shardedJedis.del("S_ADMIN_SYSTEM");
            }
        } finally {
            //使用后一定关闭，还给连接池
            if(shardedJedis!=null) {
                shardedJedis.close();
            }
        }
//        DecimalFormat df=new DecimalFormat("000000");
        return number.intValue();
    }
}
