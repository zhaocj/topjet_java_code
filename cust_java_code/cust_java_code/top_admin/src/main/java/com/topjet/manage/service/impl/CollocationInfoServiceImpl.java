package com.topjet.manage.service.impl;

import com.google.gson.Gson;
import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.CollocationInfoBean;
import com.topjet.manage.domain.bean.NameBean;
import com.topjet.manage.domain.model.CollocationInfoModel;
import com.topjet.manage.domain.model.ResourceFileKeyInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.mapper.readMapper.CollocationInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.ResourceFileVersionInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.CollocationInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.ResourceFileVersionInfoModelMapper;
import com.topjet.manage.service.CollocationInfoService;
import com.topjet.tool.common.util.Base64;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyj on 2017/9/20.
 */
@Service
public class CollocationInfoServiceImpl implements CollocationInfoService {

    @Autowired
    private CollocationInfoModelEMapper collocationInfoModelEMapper;
    @Autowired
    private CollocationInfoModelMapper collocationInfoModelMapper;
    @Autowired
    private ResourceFileVersionInfoModelEMapper resourceFileVersionInfoModelEMapper;
    @Autowired
    private ResourceFileVersionInfoModelMapper resourceFileVersionInfoModelMapper;
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> collocationInfoModelRedisTemplate;
    @Autowired
    private BasicFeignService basicFeignService;
    private final static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
    private Map<Integer,List<NameBean>> nameBeanMap  = new HashMap<>();

    @Override
    public List<CollocationInfoBean> getCollocationList(CollocationInfoBean collocationInfoBean) {
        return collocationInfoModelEMapper.getCollocationList(collocationInfoBean);
    }

    @Override
    public Integer getCollocationCount(CollocationInfoBean collocationInfoBean) {
        return collocationInfoModelEMapper.getCollocationCount(collocationInfoBean);
    }

    @Override
    public Integer createCollocationInfo(CollocationInfoBean collocationInfoBean) {
        return collocationInfoModelMapper.createCollocationInfo(collocationInfoBean);
    }

    @Override
    public Integer updateCollocationInfo(CollocationInfoBean collocationInfoBean) {
        return collocationInfoModelMapper.updateCollocationInfo(collocationInfoBean);
    }

    @Override
    public Integer deleteCollocationInfo(CollocationInfoBean collocationInfoBean) {
        return collocationInfoModelMapper.deleteCollocationInfo(collocationInfoBean);
    }

    @Override
    public List<NameBean> getResourceByType(Integer resourceType) {
        return nameBeanMap.get(resourceType);
    }

    @Override
    public void updateResource(ResourceFileVersionInfoModel resourceFileVersionInfomodel) {
        List<ResourceFileVersionInfoModel> dataList = resourceFileVersionInfoModelEMapper.getResourceList(resourceFileVersionInfomodel.getResourceType());
        //根据资源文件类型  查询resourceFileKeyInfo表中是否有该类型记录
        List<ResourceFileKeyInfoModel> dataKeyList = resourceFileVersionInfoModelEMapper.getResourceKeyList(resourceFileVersionInfomodel.getResourceType());

        if(dataList.isEmpty() && dataKeyList.isEmpty()){//为空则没有该类型
            ResourceFileVersionInfoModel r = new ResourceFileVersionInfoModel();
            r.setResourceType(resourceFileVersionInfomodel.getResourceType());
            r.setInnoVersion(3001001);
            r.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            resourceFileVersionInfoModelMapper.insertResource(r);

            //在点击确定生效之后
            ResourceFileKeyInfoModel resourceFileKeyInfoModel = new ResourceFileKeyInfoModel();
            resourceFileKeyInfoModel.setInnoVersion(3001001);
            resourceFileKeyInfoModel.setResourceType(resourceFileVersionInfomodel.getResourceType());
            resourceFileKeyInfoModel.setSystemType(0);

            UploadRTS uploadRTS = new UploadRTS();
            String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
            //初始化数据
            initData(resourceFileVersionInfomodel.getResourceType());

            List<NameBean> list = getResourceByType(resourceFileVersionInfomodel.getResourceType());
            String pictureURL = Base64.encodeString(new Gson().toJson(list));
            uploadRTS.setPictureData(pictureURL);
            uploadRTS.setType(Integer.parseInt(type));
            resourceFileKeyInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
            resourceFileVersionInfoModelMapper.insertKeyResource(resourceFileKeyInfoModel);

        }else{
            ResourceFileVersionInfoModel re = dataList.get(0);
            re.setInnoVersion(re.getInnoVersion()+1);
            resourceFileVersionInfoModelMapper.updateResource(re);
            //如果dataKeyList为空
            if(dataKeyList.isEmpty()){
                //生成key
                //在点击确定生效之后
                ResourceFileKeyInfoModel resourceFileKeyInfoModel = new ResourceFileKeyInfoModel();
                resourceFileKeyInfoModel.setInnoVersion(dataList.get(0).getInnoVersion());
                resourceFileKeyInfoModel.setResourceType(resourceFileVersionInfomodel.getResourceType());
                resourceFileKeyInfoModel.setSystemType(0);

                //根据ResourceFileVersionInfoModel表新增的一条id查询数据
               // ResourceFileVersionInfoModel resourceFileVersion = resourceFileVersionInfoModelEMapper.getResourceById(re.getId());
                UploadRTS uploadRTS = new UploadRTS();
                String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                //初始化数据
                initData(resourceFileVersionInfomodel.getResourceType());

                List<NameBean> list = getResourceByType(resourceFileVersionInfomodel.getResourceType());
                String pictureURL = Base64.encodeString(new Gson().toJson(list));
                uploadRTS.setPictureData(pictureURL);
                uploadRTS.setType(Integer.parseInt(type));
                resourceFileKeyInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
                resourceFileVersionInfoModelMapper.insertKeyResource(resourceFileKeyInfoModel);
            }

            //如果dataKeyList不为空
            if(!dataKeyList.isEmpty()){
                ResourceFileKeyInfoModel resourceFileKeyUpdateInfoModel = dataKeyList.get(0);
                resourceFileKeyUpdateInfoModel.setInnoVersion(re.getInnoVersion());
                UploadRTS uploadRTS = new UploadRTS();
                String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                //初始化数据
                initData(resourceFileVersionInfomodel.getResourceType());

                List<NameBean> list = getResourceByType(resourceFileVersionInfomodel.getResourceType());
                String pictureURL = Base64.encodeString(new Gson().toJson(list));
                uploadRTS.setPictureData(pictureURL);
                uploadRTS.setType(Integer.parseInt(type));
                resourceFileKeyUpdateInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
                resourceFileVersionInfoModelMapper.updateKeyResource(resourceFileKeyUpdateInfoModel);
            }
        }
    }

    @Override
    public CollocationInfoModel getMatchCenterPhone() {
        CollocationInfoModel model = new CollocationInfoModel();
        ValueOperations opsForValue = this.collocationInfoModelRedisTemplate.opsForValue();
        model.setCollocationName(opsForValue.get(CommonConstant.ADMIN_MATCH_CENTER_PHONE).toString());
        return model;
    }

    @Override
    public ResultBaseMsg updateOrAdd(String mobile) {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        ValueOperations operations = this.collocationInfoModelRedisTemplate.opsForValue();
        //修改
        operations.set(CommonConstant.ADMIN_MATCH_CENTER_PHONE,mobile);
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }


    /**
     * 初始化 货物类型,包装方式,装卸方式,计量单位
     * @param resourceType
     */
    private void initData(Integer resourceType) {
        logger.info("初始化resourceType 数据 initData");
        List<NameBean> nameBeanList = new ArrayList<>();
        List<CollocationInfoModel> collocation = collocationInfoModelEMapper.getCollocation(resourceType);
        for (CollocationInfoModel model : collocation) {
            NameBean nameBean = new NameBean();
            nameBean.setName(model.getCollocationName());
            nameBean.setCategoryId(model.getCategoryId());
            nameBeanList.add(nameBean);
        }
        nameBeanMap.put(resourceType,nameBeanList);
    }
}
