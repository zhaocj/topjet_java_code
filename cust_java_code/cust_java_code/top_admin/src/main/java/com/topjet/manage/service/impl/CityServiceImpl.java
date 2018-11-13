package com.topjet.manage.service.impl;

import com.google.gson.Gson;
import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.service.basic.bean.UploadRTS;
import com.topjet.common.SessionUtils;
import com.topjet.common.util.CityUtil;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.constants.ResourceConfigConstant;
import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.model.CityModel;
import com.topjet.manage.domain.model.ResourceFileKeyInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.mapper.readMapper.CityModelEMapper;
import com.topjet.manage.mapper.readMapper.ResourceFileVersionInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.CityModelMapper;
import com.topjet.manage.mapper.writeMapper.ResourceFileVersionInfoModelMapper;
import com.topjet.manage.service.CityService;
import com.topjet.tool.common.util.Base64;
import com.topjet.tool.common.util.FormatUtil;
import com.topjet.tool.common.util.JsonUtil;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengtao on 10/19/15.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityModelEMapper cityModelEMapper;
    @Autowired
    private CityModelMapper cityModelMapper;
    @Autowired
    private ResourceFileVersionInfoModelEMapper resourceFileVersionInfoModelEMapper;
    @Autowired
    private ResourceFileVersionInfoModelMapper resourceFileVersionInfoModelMapper;
    @Autowired
    private BasicFeignService basicFeignService;

    private final static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private static String android = "";

    private static String ios = "";

    private static List<CityModel> cityList = null;

    @Override
    public String getAndroid() {
        return android;
    }

    @Override
    public String getIos() {
        return ios;
    }

    @Override
    public List<CityModel> getList() {
        return cityList;
    }

    // 根据code 获取上级对象
    public CityModel getParent(String parentId) {
        CityModel cityModel = new CityModel();
        if (!StringUtils.isEmpty(parentId)) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("adcode",CityUtil.convertToInteger(parentId));
            map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            map.put("offset",cityModel.getOffset());
            map.put("limit",cityModel.getRows());
            List<CityModel> cityList = cityModelEMapper.selectListByParam(map);
            if (cityList != null && cityList.size() > 0) {
                return cityList.get(0);
            }
        }
        return null;
    }

    @Override
    public List<String> getCity(CityModel cityModel) {
        List<String> cityList = new ArrayList<String>();
        if (cityModel != null) {
            return this.getCity(CityUtil.convertToString(cityModel.getAdcode()), cityList);
        }
        return cityList;
    }

    @Override
    public List<String> getCity(String cityCode) {
        List<String> cityList = new ArrayList<String>();
        if (cityCode != null && !"".equals(cityCode)) {
            return this.getCity(cityCode, cityList);
        }
        return cityList;
    }

    @Override
    public String getCityAddress1(List<String> cityList) {
        if (null == cityList || cityList.size() < 1)
            return "";
        return cityList.get(0);
    }

    @Override
    public String getCityAddress2(List<String> cityList) {
        if (null == cityList || cityList.size() < 2)
            return "";
        return cityList.get(1);
    }

    @Override
    public String getCityAddress3(List<String> cityList) {
        if (null == cityList || cityList.size() < 3)
            return "";
        return cityList.get(2);
    }

    private List<String> getCity(String adcode, List<String> cityList) {
        CityModel cityModel = new CityModel();
        if (cityList == null)
            cityList = new ArrayList<String>();

        if (!StringUtils.isEmpty(adcode)) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("adcode",CityUtil.convertToInteger(adcode));
            map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            map.put("offset",cityModel.getOffset());
            map.put("limit",cityModel.getRows());
            List<CityModel> cityModelList = cityModelEMapper.selectListByParam(map);

            if (cityModelList != null && cityModelList.size() > 0 && cityModelList.get(0).getLevel() != 0) {
                 cityModel = cityModelList.get(0);
                this.getCity(CityUtil.convertToString(cityModel.getParentId()), cityList);
                if (StringUtils.isEmpty(cityModel.getCityFullName())) {
                    cityList.add(cityModel.getCityName());
                } else {
                    cityList.add(cityModel.getCityFullName());
                }
                return cityList;
            }
        }
        return null;
    }

    @Override
    public CityModel getCityModel(String cityCode) {
        CityModel cityModel = new CityModel();
        if (StringUtils.isEmpty(cityCode)) {
            return null;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("adcode",CityUtil.convertToInteger(cityCode));
        map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        map.put("offset",cityModel.getOffset());
        map.put("limit",cityModel.getRows());
        List<CityModel> cityModelList = cityModelEMapper.selectListByParam(map);

        if (cityModelList != null && cityModelList.size() > 0) {
            return cityModelList.get(0);
        }
        return null;
    }

    @Override
    public CityModel getCityModelByName(String cityName, String cityLevel) {
        CityModel cityModel = new CityModel();
        if (StringUtils.isEmpty(cityName) || StringUtils.isEmpty(cityLevel))
            return null;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityName","%"+cityName+"%");
        map.put("level",Integer.parseInt(cityLevel));
        map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        map.put("offset",cityModel.getOffset());
        map.put("limit",cityModel.getRows());
        List<CityModel> cityModelList = cityModelEMapper.selectListByParam(map);

        if (cityModelList != null && cityModelList.size() > 0) {
            return cityModelList.get(0);
        }
        return null;
    }

    @Override
    public String getCityId(String cityName) {

        CityModel cityModel = new CityModel();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityName",cityName);
        map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        map.put("offset",cityModel.getOffset());
        map.put("limit",cityModel.getRows());
        List<CityModel> cityModelList = cityModelEMapper.selectListByParam(map);
        if (cityModelList != null && cityModelList.size() > 0) {
            return CityUtil.convertToString(cityModelList.get(0).getAdcode());
        }
        return null;
    }

    @Override
    public String getCityIdByFullName(String cityFullName) {
        CityModel cityModel = new CityModel();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cityFullName",cityFullName);
        map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        map.put("offset",cityModel.getOffset());
        map.put("limit",cityModel.getRows());
        List<CityModel> cityModelList = cityModelEMapper.selectListByParam(map);
        if (cityModelList != null && cityModelList.size() > 0) {
            return CityUtil.convertToString(cityModelList.get(0).getAdcode());
        }
        return null;
    }

    @Override
    public CityModel getFirstCity(String childCityCode) {
        StringBuffer sbBuffer = new StringBuffer();
        String code = sbBuffer.append(childCityCode.substring(0, 2)).append("0000").toString();
        CityModel cityModel = getCityModel(code);
        if (cityModel != null) {
            return cityModel;
        }
        return null;
    }

    @Override
    public CityModel getSecondCity(String childCityCode) {
        StringBuffer sbBuffer = new StringBuffer();
        String code = sbBuffer.append(childCityCode.substring(0, 4)).append("00").toString();
        CityModel cityModel = getCityModel(code);
        if (cityModel != null) {
            return cityModel;
        }
        return null;
    }

    @Override
    public List<String> getCityIdList(String adcode) {
        List<String> cityList = new ArrayList<String>();
        if (adcode != null && !"".equals(adcode)) {
            return this.getCityId(adcode, cityList);
        }
        return cityList;
    }

    @Override
    public String getFullName(String adcode) {

        List<String> names = getCity(adcode);
        if (names != null && names.size() > 0) {
            if (names.size() == 1) {
                return names.get(0);
            } else if (names.size() == 2) {
                return CityUtil.filterLevel2CityList(names.get(0), names.get(1), "");
            } else if (names.size() == 3) {
                return CityUtil.filterLevel2CityList(names.get(0), names.get(1), names.get(2));
            }
        }
        return null;
    }

    @Override
    public String getOrderNotificationSettingName(String adcode) {
        List<String> names = getCity(adcode);
        if (names.size() == 1) {
            return names.get(0);
        } else if (names.size() == 2) {
            return names.get(1);
        } else if (names.size() == 3) {
            return names.get(1) + names.get(2);
        }
        return "";
    }

    @Override
    public String getSecondCityCode(Integer adcode) {
        CityModel cityModel = new CityModel();
        Integer cityCode = 0;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("adcode",adcode);
        map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        map.put("offset",cityModel.getOffset());
        map.put("limit",cityModel.getRows());
        List<CityModel> cityModelList = cityModelEMapper.selectListByParam(map);

        if (cityModelList != null && cityModelList.size() > 0) {
            CityModel model = cityModelList.get(0);
            switch (model.getLevel()) {
                case 2:
                    cityCode = model.getAdcode();
                case 3:
                    cityCode = model.getParentId();
            }
        }
        return CityUtil.convertToString(cityCode);
    }

    @Override
    public String getCityName(String adcode) {
        CityModel cityModel = new CityModel();
        if (!StringUtils.isBlank(adcode)) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("adcode",Integer.parseInt(adcode));
            map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            map.put("offset",cityModel.getOffset());
            map.put("limit",cityModel.getRows());
            List<CityModel> cityModelList = cityModelEMapper.selectListByParam(map);
            if (cityModelList != null && cityModelList.size() > 0) {
                CityModel model = cityModelList.get(0);
                return model.getCityName();
            }
        }
        return "";
    }


    private List<String> getCityId(String adcode, List<String> cityList) {
        CityModel cityModel = new CityModel();
        if (cityList == null)
            cityList = new ArrayList<String>();

        if (!StringUtils.isEmpty(adcode)) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("adcode",CityUtil.convertToInteger(adcode));
            map.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            map.put("offset",cityModel.getOffset());
            map.put("limit",cityModel.getRows());
            List<CityModel> cityModelList = cityModelEMapper.selectListByParam(map);
            if (cityModelList != null && cityModelList.size() > 0) {
                 cityModel = cityModelList.get(0);
                if(cityModel.getAdcode() == 100000){return cityList;}
                this.getCityId(CityUtil.convertToString(cityModel.getParentId()), cityList);
                cityList.add(CityUtil.convertToString(cityModel.getAdcode()));
                return cityList;
            }
        }
        return null;
    }

    @Override
    public List<CityModel> getCityAll(CityBean cityBean) {
        return cityModelEMapper.getCityAll(cityBean);
    }

    @Override
    public Integer getCityCount(CityBean cityBean) {
        return cityModelEMapper.getCityCount(cityBean);
    }

    @Override
    public int createCity(CityBean cityBean) {
        CityModel cityModel = new CityModel();
        if (!org.apache.commons.lang.StringUtils.isEmpty(cityBean.getCity()) || cityBean.equals("1")) {
            cityModel.setParentId(CityUtil.convertToInteger(cityBean.getCity()));
        } else {
            cityModel.setParentId(CityUtil.convertToInteger(cityBean.getProvince()));
        }
        cityModel.setLevel(cityBean.getLevel());
        cityModel.setAdcode(cityBean.getAdcode());
        cityModel.setFlag(cityBean.getFlag());
        cityModel.setCityName(cityBean.getCityName());
        //cityModel.setWeatherId(cityBean.getWeatherId());
        if(cityBean.getZip() == null || cityBean.getZip() == ""){
            cityModel.setZip("");
        }
        else{
            cityModel.setZip(cityBean.getZip());
        }
        cityModel.setCitycode(cityBean.getCitycode());
        cityModel.setLongitude(cityBean.getLongitude());
        cityModel.setLatitude(cityBean.getLatitude());
        cityModel.setCreateTime(DateUtil.now());
        //cityModel.setCreateBy(SessionUtils.getSysUserSession().getId());
        //cityModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
        cityModel.setUpdateTime(DateUtil.now());
        cityModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        int rows=cityModelMapper.insert(cityModel);
        return rows;
    }

    @Override
    public List<CityModel> getProvince(Integer level) {
        return cityModelEMapper.getProvince(level);
    }

    @Override
    public List<CityModel> findCity(Integer adcode) {
       // Integer parentId = CityUtil.convertToInteger(adcode);
        List<CityModel> cityModels = cityModelEMapper.findCity(adcode);
        if (cityModels != null && cityModels.size() > 0) {
            if (cityModels != null && cityModels.size() > 0) {
                return cityModels;
            }
        }
        return null;
    }

    @Override
    public CityBean getCityInfo(int id) throws IllegalAccessException, InvocationTargetException {
        CityBean cityBean = new CityBean();
        CityModel cityModel = cityModelEMapper.findCityById(id);
        if (!org.apache.commons.lang.StringUtils.isEmpty(cityModel.getCitycode())) {
            cityBean.setCitycode(cityModel.getCitycode());
        }
        if (!org.apache.commons.lang.StringUtils.isEmpty(cityModel.getZip())) {
            cityBean.setZip(cityModel.getZip());
        }
        cityBean.setCityName(cityModel.getCityName());
        cityBean.setFlag(cityModel.getFlag());
        cityBean.setLevel(cityModel.getLevel());
        cityBean.setAdcode(cityModel.getAdcode());
        cityBean.setId(cityModel.getId());
        //cityBean.setWeatherId(cityModel.getWeatherId());
        cityBean.setParentId(cityModel.getParentId());
       //cityBean.setAdcode(cityModel.getAdcode());
        cityBean.setLongitude(cityModel.getLongitude());
        cityBean.setLatitude(cityModel.getLatitude());
        List<CityModel> cityModels = cityModelEMapper.findCityByCode(cityModel.getParentId());
        if (cityModels != null && cityModels.size() > 0) {
            if (cityModel.getLevel().equals(2)) {
                cityBean.setProvince(cityModels.get(0).getCityName());
            }
            CityModel city1 = null;
            if (cityModel.getLevel().equals(3)) {
                city1 = cityModels.get(0);
                cityBean.setCityPrimaryKey(city1.getId());
                cityBean.setCityDisplayName(city1.getCityName());
                List<CityModel> cityModels1 = cityModelEMapper.findCityByCode(cityModel.getParentId());
                if (cityModels1 != null && cityModels1.size() > 0) {
                    cityModels1.get(0);
                    cityBean.setProvince(cityModels1.get(0).getCityName());
                }
            }
        }
        return cityBean;
    }

    @Override
    public CityModel findCityById(Integer id) {
        return cityModelEMapper.findCityById(id);
    }

    @Override
    public List<CityModel> findCityByCode(Integer adcode) {
        return cityModelEMapper.findCityByCode(adcode);
    }

    @Override
    public int update(CityBean cityBean) {
        CityModel cityModel = cityModelEMapper.findCityById(cityBean.getId());
        cityModel.setCityName(cityBean.getCityName());
        cityModel.setCitycode(cityBean.getCitycode());
        cityModel.setLongitude(cityBean.getLongitude());
        cityModel.setLatitude(cityBean.getLatitude());
        cityModel.setFlag(cityBean.getFlag());
        //cityModel.setWeatherId(cityBean.getWeatherId());
        cityModel.setZip(cityBean.getZip());
        cityModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
        cityModel.setUpdateTime(DateUtil.now());
        int rows= cityModelMapper.update(cityModel);
        return rows;
    }

    @Override
    public List<CityModel> findCityLevel1(CityModel cityModel) {
        return cityModelEMapper.findCityLevel1(cityModel);
    }

    @Override
    public List<CityModel> findCityLevel2(CityModel cityModel) {
        return cityModelEMapper.findCityLevel2(cityModel);
    }

    @Override
    public List<CityModel> findCityLevel3(CityModel cityModel) {
        return cityModelEMapper.findCityLevel3(cityModel);
    }

    @Override
    public List<CityModel> findCityList(CityModel cityModel) {
        return cityModelEMapper.findCityList(cityModel);
    }

    @Override
    public void updateResourceFileVersionInfo(int resourceType) throws Exception {
            ResourceFileVersionInfoModel resourceFileVersionInfomodel = new ResourceFileVersionInfoModel();
            List<ResourceFileVersionInfoModel> dataList = resourceFileVersionInfoModelEMapper.getResourceList(resourceType);
            //根据资源文件类型  查询resourceFileKeyInfo表中是否有该类型记录
            List<ResourceFileKeyInfoModel> dataKeyList = resourceFileVersionInfoModelEMapper.getResourceFileKeyList(resourceType);

            if(dataList.isEmpty() && dataKeyList.isEmpty()){//为空则没该类型 插入
                resourceFileVersionInfomodel.setResourceType(ResourceConfigConstant.RESOURCE_TYPE_CITY);
                resourceFileVersionInfomodel.setInnoVersion(3001001);
                resourceFileVersionInfomodel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
                resourceFileVersionInfoModelMapper.insertResource(resourceFileVersionInfomodel);

                //生成androidkey
                ResourceFileKeyInfoModel resourceFileKeyInfoModel = new ResourceFileKeyInfoModel();
                resourceFileKeyInfoModel.setInnoVersion(3001001);
                resourceFileKeyInfoModel.setResourceType(ResourceConfigConstant.RESOURCE_TYPE_CITY);
                resourceFileKeyInfoModel.setSystemType(1);
                UploadRTS uploadRTS = new UploadRTS();
                String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                String pictureURL = Base64.encodeString(this.initAndroid());
                uploadRTS.setPictureData(pictureURL);
                uploadRTS.setType(Integer.parseInt(type));
                resourceFileKeyInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
                resourceFileVersionInfoModelMapper.insertKeyResource(resourceFileKeyInfoModel);

                //生成ioskey
                ResourceFileKeyInfoModel resourceFileKeyInfoModelIos = new ResourceFileKeyInfoModel();
                resourceFileKeyInfoModelIos.setInnoVersion(3001001);
                resourceFileKeyInfoModelIos.setResourceType(ResourceConfigConstant.RESOURCE_TYPE_CITY);
                resourceFileKeyInfoModelIos.setSystemType(2);
                UploadRTS uploadRTSIos = new UploadRTS();
                String typeIos= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                String pictureURLIos = Base64.encodeString(this.initIos());
                uploadRTSIos.setPictureData(pictureURLIos);
                uploadRTSIos.setType(Integer.parseInt(typeIos));
                resourceFileKeyInfoModelIos.setResourceKey(basicFeignService.upload(uploadRTSIos).getStrkey());
                resourceFileVersionInfoModelMapper.insertKeyResource(resourceFileKeyInfoModelIos);

            }else{
                ResourceFileVersionInfoModel resoure = dataList.get(0);
                resoure.setInnoVersion(resoure.getInnoVersion()+1);
                resourceFileVersionInfoModelMapper.updateResource(resoure);

                //如果dataKeyList为空
                if(dataKeyList.isEmpty()){
                    //生成androidkey
                    ResourceFileKeyInfoModel resourceFileKeyInfoModel = new ResourceFileKeyInfoModel();
                    resourceFileKeyInfoModel.setInnoVersion(dataList.get(0).getInnoVersion());
                    resourceFileKeyInfoModel.setResourceType(ResourceConfigConstant.RESOURCE_TYPE_CITY);
                    resourceFileKeyInfoModel.setSystemType(1);
                    UploadRTS uploadRTS = new UploadRTS();
                    String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                    String pictureURL = Base64.encodeString(this.initAndroid());
                    uploadRTS.setPictureData(pictureURL);
                    uploadRTS.setType(Integer.parseInt(type));
                    resourceFileKeyInfoModel.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
                    resourceFileVersionInfoModelMapper.insertKeyResource(resourceFileKeyInfoModel);

                    //生成ioskey
                    ResourceFileKeyInfoModel resourceFileKeyInfoModelIos = new ResourceFileKeyInfoModel();
                    resourceFileKeyInfoModelIos.setInnoVersion(dataList.get(0).getInnoVersion());
                    resourceFileKeyInfoModelIos.setResourceType(ResourceConfigConstant.RESOURCE_TYPE_CITY);
                    resourceFileKeyInfoModelIos.setSystemType(2);
                    UploadRTS uploadRTSIos = new UploadRTS();
                    String typeIos= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                    String pictureURLIos = Base64.encodeString(this.initIos());
                    uploadRTSIos.setPictureData(pictureURLIos);
                    uploadRTSIos.setType(Integer.parseInt(typeIos));
                    resourceFileKeyInfoModelIos.setResourceKey(basicFeignService.upload(uploadRTSIos).getStrkey());
                    resourceFileVersionInfoModelMapper.insertKeyResource(resourceFileKeyInfoModelIos);

                }

                //如果dataKeyList不为空
                if(!dataKeyList.isEmpty()){

                    for(ResourceFileKeyInfoModel resourceFileKeyUpdateInfoModel : dataKeyList){
                        ResourceFileKeyInfoModel rr = new ResourceFileKeyInfoModel();
                        rr.setId(resourceFileKeyUpdateInfoModel.getId());
                        rr.setInnoVersion(resoure.getInnoVersion());
                        if(resourceFileKeyUpdateInfoModel.getSystemType() == 1){
                            //修改androidKey
                            UploadRTS uploadRTS = new UploadRTS();
                            String type= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                            String pictureURL = Base64.encodeString(this.initAndroid());
                            uploadRTS.setPictureData(pictureURL);
                            uploadRTS.setType(Integer.parseInt(type));
                            rr.setResourceKey(basicFeignService.upload(uploadRTS).getStrkey());
                            resourceFileVersionInfoModelMapper.updateKeyResource(rr);
                        }
                        if(resourceFileKeyUpdateInfoModel.getSystemType() == 2){
                            //修改iosKey
                            UploadRTS uploadRTSIos = new UploadRTS();
                            String typeIos= PictureServerProperties.TEXT_TYPE_RESOURCE+"";//桶要改
                            String pictureURLIos = Base64.encodeString(this.initIos());
                            uploadRTSIos.setPictureData(pictureURLIos);
                            uploadRTSIos.setType(Integer.parseInt(typeIos));
                            rr.setResourceKey(basicFeignService.upload(uploadRTSIos).getStrkey());
                            resourceFileVersionInfoModelMapper.updateKeyResource(rr);
                        }
                    }

                }
            }
    }

    private CityResourceBean construct(CityModel mode) {

        CityResourceBean cityBean=new CityResourceBean();
        cityBean.setAdcode(com.topjet.tool.common.util.CityUtil.convertToString(mode.getAdcode()));
        cityBean.setCitycode(mode.getCitycode());
        cityBean.setCityName(mode.getCityName());
        cityBean.setCityFullName(mode.getCityFullName());
        cityBean.setParentId(com.topjet.tool.common.util.CityUtil.convertToString(mode.getParentId()));
        cityBean.setLatitude(FormatUtil.convertBigDecimalToString(mode.getLatitude(),6));
        cityBean.setLongitude(FormatUtil.convertBigDecimalToString(mode.getLongitude(),6));
        return cityBean;
    }


    /**
     * 初始化Android
     */
    private String initAndroid() {

        logger.info("初始化Android版城市参数");

        List<CityResourceBean> cityList = new ArrayList<CityResourceBean>();
        List<CityModel> list = cityModelEMapper.getCityList();
        for (CityModel model : list) {
            CityResourceBean cityBean = this.construct(model);
            cityList.add(cityBean);
        }

        android = new Gson().toJson(cityList);
        return android;
    }

    /**
     * 初始化ios
     */
    private String initIos() {
        logger.info("初始化Ios版城市参数");
        CityModel cityModel = new CityModel();
        List<CityModel> list1=cityModelEMapper.findCityLevel1(cityModel);
        List<CityModel> list2=cityModelEMapper.findCityLevel2(cityModel);
        List<CityModel> list3=cityModelEMapper.findCityLevel3(cityModel);

        List<CityBeanProvIos> prov=new ArrayList<CityBeanProvIos>();

        for (CityModel bean : list1) {
            CityBeanProvIos provBean=new CityBeanProvIos();
            provBean.setAdcode(CityUtil.convertToString(bean.getAdcode()));
            provBean.setCitycode(bean.getCitycode());
            provBean.setCityName(bean.getCityName());
            provBean.setCityFullName(bean.getCityFullName());
            provBean.setParentId(CityUtil.convertToString(bean.getParentId()));
            provBean.setLatitude(FormatUtil.convertBigDecimalToString(bean.getLatitude(),6));
            provBean.setLongitude(FormatUtil.convertBigDecimalToString(bean.getLongitude(),6));
            List<CityBeanCityIos> city=new ArrayList<CityBeanCityIos>();
            for (CityModel bean2 : list2) {
                if(com.topjet.tool.common.util.CityUtil.convertToString(bean2.getParentId()).equals(provBean.getAdcode())){
                    CityBeanCityIos cityBean=new CityBeanCityIos();
                    cityBean.setAdcode(CityUtil.convertToString((bean2.getAdcode())));
                    cityBean.setCitycode(bean2.getCitycode());
                    cityBean.setCityName(bean2.getCityName());
                    cityBean.setCityFullName(bean2.getCityFullName());
                    cityBean.setParentId(CityUtil.convertToString(bean2.getParentId()));
                    cityBean.setLatitude(FormatUtil.convertBigDecimalToString(bean2.getLatitude(),6));
                    cityBean.setLongitude(FormatUtil.convertBigDecimalToString(bean2.getLongitude(),6));
                    List<CityResourceBean> dist=new ArrayList<CityResourceBean>();
                    for (CityModel bean3 : list3) {
                        if(CityUtil.convertToString(bean3.getParentId()).equals(cityBean.getAdcode())){
                            CityResourceBean distBean=new CityResourceBean();
                            distBean.setAdcode(CityUtil.convertToString(bean3.getAdcode()));
                            distBean.setCitycode(bean3.getCitycode());
                            distBean.setCityName(bean3.getCityName());
                            distBean.setParentId(CityUtil.convertToString(bean3.getParentId()));
                            distBean.setCityFullName(bean3.getCityFullName());
                            distBean.setLatitude(FormatUtil.convertBigDecimalToString(bean3.getLatitude(),6));
                            distBean.setLongitude(FormatUtil.convertBigDecimalToString(bean3.getLongitude(),6));
                            dist.add(distBean);
                        }
                    }
                    cityBean.setDist(dist);
                    city.add(cityBean);
                }
            }
            provBean.setCity(city);
            prov.add(provBean);

        }
        ios = JsonUtil.toJSON(prov);//JsonUtils.togJSOnString(prov);
        return ios;
    }

}
