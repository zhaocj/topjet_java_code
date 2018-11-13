package com.topjet.manage.service.impl;

import com.google.gson.Gson;
import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.TruckLength;
import com.topjet.manage.domain.bean.TruckType;
import com.topjet.manage.domain.model.ResourceFileKeyInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.domain.model.TruckLengthDictionaryModel;
import com.topjet.manage.domain.model.TruckTypeDictionaryModel;
import com.topjet.manage.mapper.readMapper.ResourceFileVersionInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.TruckLengthDictionaryModelEMapper;
import com.topjet.manage.mapper.readMapper.TruckTypeDictionaryModelEMapper;
import com.topjet.manage.mapper.writeMapper.ResourceFileVersionInfoModelMapper;
import com.topjet.manage.service.TruckTypeService;
import com.topjet.tool.common.util.Base64;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyj on 2017/9/14.
 */
@Service
public class TruckTypeServiceImpl implements TruckTypeService {
    @Autowired
    private TruckTypeDictionaryModelEMapper truckTypeDictionaryModelEMapper;
    @Autowired
    private TruckLengthDictionaryModelEMapper truckLengthDictionaryModelEMapper;
    @Autowired
    private ResourceFileVersionInfoModelEMapper resourceFileVersionInfoModelEMapper;
    @Autowired
    private ResourceFileVersionInfoModelMapper resourceFileVersionInfoModelMapper;
    @Autowired
    private BasicFeignService basicFeignService;
    private static List<TruckType> typeList;
    private static List<TruckLength> length_list;

    private final static Logger logger = LoggerFactory.getLogger(TruckTypeServiceImpl.class);

    @Override
    public List<TruckTypeDictionaryModel> getTruckTypeList(TruckTypeDictionaryModel truckTypeDictionaryModel) {
        return truckTypeDictionaryModelEMapper.getTruckTypeList(truckTypeDictionaryModel);
    }

    @Override
    public Integer queryTruckTypeCount(TruckTypeDictionaryModel truckTypeDictionaryModel) {
        return truckTypeDictionaryModelEMapper.queryTruckTypeCount(truckTypeDictionaryModel);
    }

    @Override
    public List<TruckTypeDictionaryModel> findTruckTypeByName(String displayName) {
        return truckTypeDictionaryModelEMapper.findTruckTypeByName(displayName);
    }

    @Override
    public List<TruckTypeDictionaryModel> findTruckTypeByCode(String code) {
        return truckTypeDictionaryModelEMapper.findTruckTypeByCode(code);
    }

    @Override
    public TruckTypeDictionaryModel findTruckTypeById(Integer id) {
        return truckTypeDictionaryModelEMapper.findTruckTypeById(id);
    }

    @Override
    public List<TruckTypeDictionaryModel> getTruckType(TruckTypeDictionaryModel truckTypeDictionaryModel) {
        return truckTypeDictionaryModelEMapper.getTruckType(truckTypeDictionaryModel);
    }

    @Override
    public List<TruckType> getTruckType() {
        return typeList;
    }

    @Override
    public void updateResourceFileVersionInfo(ResourceFileVersionInfoModel rfvModel) {
        List<ResourceFileVersionInfoModel> dataList = resourceFileVersionInfoModelEMapper.getResourceList(rfvModel.getResourceType());
        //根据资源文件类型  查询resourceFileKeyInfo表中是否有该类型记录
        List<ResourceFileKeyInfoModel> dataKeyList = resourceFileVersionInfoModelEMapper.getResourceKeyList(rfvModel.getResourceType());
        if(dataList.isEmpty() && dataKeyList.isEmpty()){//为空表示没有该类型
            rfvModel.setResourceType(rfvModel.getResourceType());
            rfvModel.setInnoVersion(3001001);
            rfvModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            resourceFileVersionInfoModelMapper.insertResource(rfvModel);

            //在点击确定生效之后
            ResourceFileKeyInfoModel resourceFileKeyInfoModel = new ResourceFileKeyInfoModel();
            resourceFileKeyInfoModel.setInnoVersion(3001001);
            resourceFileKeyInfoModel.setResourceType(rfvModel.getResourceType());
            resourceFileKeyInfoModel.setSystemType(0);
            UploadRTS uploadRTS = new UploadRTS();
            String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
            if(rfvModel.getResourceType() == 4){//车型
                //初始化车型
                this.initType();
                String pictureURL = Base64.encodeString(new Gson().toJson(typeList));
                uploadRTS.setPictureData(pictureURL);
                uploadRTS.setType(Integer.parseInt(type));
                resourceFileKeyInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
            }
            if(rfvModel.getResourceType() == 5){//车长
                //初始化车长
                this.initTruckLength();
                String pictureURL = Base64.encodeString(new Gson().toJson(length_list));
                uploadRTS.setPictureData(pictureURL);
                uploadRTS.setType(Integer.parseInt(type));
                resourceFileKeyInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());

            }
            resourceFileVersionInfoModelMapper.insertKeyResource(resourceFileKeyInfoModel);

        }else{
            ResourceFileVersionInfoModel resourceFileVersionInfomodel = dataList.get(0);
            resourceFileVersionInfomodel.setInnoVersion(resourceFileVersionInfomodel.getInnoVersion()+1);
            resourceFileVersionInfoModelMapper.updateResource(resourceFileVersionInfomodel);

            //如果dataKeyList为空
            if(dataKeyList.isEmpty()){
                //生成key
                //在点击确定生效之后
                ResourceFileKeyInfoModel resourceFileKeyInfoModel = new ResourceFileKeyInfoModel();
                resourceFileKeyInfoModel.setInnoVersion(dataList.get(0).getInnoVersion());
                resourceFileKeyInfoModel.setResourceType(rfvModel.getResourceType());
                resourceFileKeyInfoModel.setSystemType(0);
                UploadRTS uploadRTS = new UploadRTS();
                String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                if(rfvModel.getResourceType() == 4){//车型
                    //初始化车型
                    this.initType();
                    String pictureURL = Base64.encodeString(new Gson().toJson(typeList));
                    uploadRTS.setPictureData(pictureURL);
                    uploadRTS.setType(Integer.parseInt(type));
                    resourceFileKeyInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
                }
                if(rfvModel.getResourceType() == 5){//车长
                    //初始化车长
                    this.initTruckLength();
                    String pictureURL = Base64.encodeString(new Gson().toJson(length_list));
                    uploadRTS.setPictureData(pictureURL);
                    uploadRTS.setType(Integer.parseInt(type));
                    resourceFileKeyInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());

                }
                resourceFileVersionInfoModelMapper.insertKeyResource(resourceFileKeyInfoModel);
            }

            //如果dataKeyList不为空
            if(!dataKeyList.isEmpty()){
                ResourceFileKeyInfoModel resourceFileKeyUpdateInfoModel = dataKeyList.get(0);
                resourceFileKeyUpdateInfoModel.setInnoVersion(resourceFileVersionInfomodel.getInnoVersion());
                UploadRTS uploadRTS = new UploadRTS();
                String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                if(rfvModel.getResourceType() == 4){//车型
                    //初始化车型
                    this.initType();
                    String pictureURL = Base64.encodeString(new Gson().toJson(typeList));
                    uploadRTS.setPictureData(pictureURL);
                    uploadRTS.setType(Integer.parseInt(type));
                    resourceFileKeyUpdateInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
                }
                if(rfvModel.getResourceType() == 5){//车长
                    //初始化车长
                    this.initTruckLength();
                    String pictureURL = Base64.encodeString(new Gson().toJson(length_list));
                    uploadRTS.setPictureData(pictureURL);
                    uploadRTS.setType(Integer.parseInt(type));
                    resourceFileKeyUpdateInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());

                }
                resourceFileVersionInfoModelMapper.updateKeyResource(resourceFileKeyUpdateInfoModel);
            }
        }
    }
    //初始化车型
    private void initType(){
        logger.info("初始化车型数据 initTruckType");
        List<TruckTypeDictionaryModel> typeModel=truckTypeDictionaryModelEMapper.findTruckType();

        typeList=new ArrayList<>();

        TruckTypeDictionaryModel noLimitTypeModel = null;
        for (TruckTypeDictionaryModel model: typeModel) {
            if(model.getDisplayName().equals("不限")){
                noLimitTypeModel = model;
            }else{
                TruckType typeb=new TruckType();
                typeb.setCode(model.getCode());
                typeb.setDisplayName(model.getDisplayName());
                typeb.setId(model.getId()+"");
                typeList.add(typeb);
            }
        }

        if(noLimitTypeModel!=null){
            TruckType typeb=new TruckType();
            typeb.setCode(noLimitTypeModel.getCode());
            typeb.setDisplayName(noLimitTypeModel.getDisplayName());
            typeb.setId(noLimitTypeModel.getId()+"");
            typeList.add(typeb);
        }
    }

    //初始化车长
    private void initTruckLength() {
        logger.info("初始化车长数据 initTruckLength");

        length_list = new ArrayList<>();

        List<TruckLengthDictionaryModel> lengthModel=truckLengthDictionaryModelEMapper.findTruckLength();

        TruckLengthDictionaryModel noLimitLengthModel = null;
        for (TruckLengthDictionaryModel model: lengthModel) {
            if(model.getDisplayName().equals("不限")){
                noLimitLengthModel = model;
            }else{
                TruckLength lengthb=new TruckLength();
                lengthb.setDisplayName(model.getDisplayName());
                lengthb.setId(model.getId()+"");
                lengthb.setLength(model.getLength()+"");
                length_list.add(lengthb);
            }
        }

        if(noLimitLengthModel!=null){
            TruckLength lengthb=new TruckLength();
            lengthb.setDisplayName(noLimitLengthModel.getDisplayName());
            lengthb.setId(noLimitLengthModel.getId()+"");
            lengthb.setLength(noLimitLengthModel.getLength()+"");
            length_list.add(lengthb);
        }

    }
}
