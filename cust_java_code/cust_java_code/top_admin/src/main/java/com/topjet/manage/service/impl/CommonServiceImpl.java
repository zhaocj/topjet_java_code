package com.topjet.manage.service.impl;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.model.ResourceFileKeyInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.mapper.readMapper.ResourceFileVersionInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.ResourceFileVersionInfoModelMapper;
import com.topjet.manage.service.CommonService;
import com.topjet.tool.common.util.Base64;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liyj on 2017/10/12.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private ResourceFileVersionInfoModelMapper resourceFileVersionInfoModelMapper;
    @Autowired
    private ResourceFileVersionInfoModelEMapper resourceFileVersionInfoModelEMapper;
    @Autowired
    private BasicFeignService basicFeignService;

    @Override
    public void updateResourceFileVersionInfo(ResourceFileVersionInfoModel rfvModel,String beginDate) throws Exception {

       //根据资源文件类型  以及systemType  查询ResourceFileVersionInfo表中是否有该类型记录
        List<ResourceFileVersionInfoModel> dataList = resourceFileVersionInfoModelEMapper.getResourceHomeList(rfvModel.getResourceType(),rfvModel.getSystemType());
        if(dataList.isEmpty()){//表中无该类型记录
            ResourceFileVersionInfoModel rfvm = new ResourceFileVersionInfoModel();
            rfvm.setResourceType(rfvModel.getResourceType());
            rfvm.setSystemType(rfvModel.getSystemType());
            rfvm.setEndTime(DateUtil.addYear(DateUtil.now(),20));
            rfvm.setStartTime(new Date(Long.parseLong(beginDate)));
            rfvm.setInnoVersion(3001001);
            rfvm.setDeleted(0);
            resourceFileVersionInfoModelMapper.insertResource(rfvm);

        }else {
            ResourceFileVersionInfoModel rfvm = dataList.get(0);
            rfvm.setInnoVersion(rfvm.getInnoVersion() + 1);
            rfvm.setStartTime(new Date(Long.parseLong(beginDate)));
            resourceFileVersionInfoModelMapper.updateResource(rfvm);

        }
    }

}
