package com.topjet.common.auth.service.impl;

import com.google.gson.Gson;
import com.topjet.common.auth.service.IDCheckResult;
import com.topjet.common.auth.service.IDCheckService;
import com.topjet.common.auth.service.dto.IDCheckRequestDTO;
import com.topjet.common.auth.service.dto.IDCheckResponseDTO;
import com.topjet.common.constants.SystemConfig;
import com.topjet.common.exception.ErrorCode;
import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.common.exception.TopjetException;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.bean.UserInfoVerifiedReturn;
import com.topjet.manage.domain.dto.Request;
import com.topjet.manage.domain.dto.Sign;
import com.topjet.manage.domain.model.IdCardInfoModel;
import com.topjet.manage.mapper.readMapper.IdCardInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.IdCardInfoModelMapper;
import com.topjet.tool.common.util.JsonUtil;
import com.topjet.util.HttpUtils;
import com.topjet.util.MethodUtil;
import com.ztzx.zcxyws.QueryValidatorServices;
import com.ztzx.zcxyws.QueryValidatorServicesProxy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ssh.common.Des2.decodeValue;
import static org.ssh.common.Des2.encode;


/**
 * @author pengtao
 */
@Service
public class IDCheckServiceImpl implements IDCheckService {

    private static final Logger LOG = LoggerFactory.getLogger(IDCheckServiceImpl.class);

    @Autowired
    private IdCardInfoModelEMapper idCardInfoModelEMapper;

    @Autowired
    private IdCardInfoModelMapper idCardInfoModelMapper;

    @Autowired
    private SystemConfig systemConfig ;




    /**
     * 身份证认证方法
     *
     * @param idCard 身份证号
     * @param name   身份认证名称
     * @return 身份认证结果
     * @throws TopjetException
     */

    public IDCheckResult validateIdentityCard(String idCard, String name, String idAddress)
            throws TopjetErrorCodeException, TopjetException {
        idCard=idNoReplaceX(idCard);
        IDCheckResult result = null;
        /**
         *   validateFromNative（）方法     通过idNo  name 去IdCardInfo表中查询 是否有该条数据
         */
        if ((result = validateFromNative(idCard, name)) != null) {
            return result;
        }
        /**
         *  调用接口去验证身份证号 和name
         */
        if ((result = validateFromWS(idCard, name, idAddress)) == null) {
            LOG.error("调用身份认证接口返回结果为空");
            return IDCheckResult.AUTH_ID_FAIL;
        }


        return result;
    }

    private IDCheckResult validateFromWS(String idCard, String name, String idAddress) throws TopjetErrorCodeException, TopjetException {

        if ("0".equals(SystemConfig.idCheckWSSwitch)) {
            return IDCheckResult.AUTH_ID_NA;
        }

        //替换为调用消息中心提供的接口
        IDCheckResult result = invokeWebservice(name + IDCheckWSProperties.DELIMITER + idCard,
                IDCheckWSProperties.TYPE);



        if (result == null) {
            LOG.error("调用身份认证接口返回结果为空");
            IDCheckResult.CHECKID_ERROR.setPath(IDCheckResult.PATH_WS);
            return IDCheckResult.CHECKID_ERROR;
        }
        if (result.getCode().equals(IDCheckResult.AUTH_ID_SUCC.getCode())) {
            save(idCard, name, idAddress);
        }
        result.setPath(IDCheckResult.PATH_WS);
        return result;
    }



    /**
     * 此方法 直接调用第三方实名认证提供的接口  请注意：3.0 改为管理后台调用消息中心提供的接口   不直接对接第三方
     * @param parameter
     * @param type
     * @return
     * @throws TopjetErrorCodeException
     */
    private IDCheckResult invokeWebservice(String parameter, String type) throws TopjetErrorCodeException {
        LOG.debug("start invokeWebservice {} {}", parameter, type);

        try {
            QueryValidatorServicesProxy proxy = new QueryValidatorServicesProxy();
            proxy.setEndpoint(IDCheckWSProperties.ID_WS_TEST_URL);
            QueryValidatorServices service = proxy.getQueryValidatorServices();
            System.setProperty("javax.net.ssl.trustStore", "keystore");
            String userName = encode(IDCheckWSProperties.KEY, IDCheckWSProperties.ID_WS_ACCOUNT);
            String password = encode(IDCheckWSProperties.KEY, IDCheckWSProperties.ID_WS_PWD);

            String resultXML = StringUtils.EMPTY;
            String datasource = encode(IDCheckWSProperties.KEY, type);
            resultXML = service.querySingle(userName, password, datasource,
                    encode(IDCheckWSProperties.KEY, parameter));
            resultXML = decodeValue(IDCheckWSProperties.KEY, resultXML);

            LOG.debug("resultXML = {}", resultXML);
            return parseCheckIdResult(resultXML);
        } catch (Exception e) {
            LOG.error("[" + parameter + "]" + "身份认证失败!", e);
            return IDCheckResult.CHECKID_ERROR;
        }

    }




    private IDCheckResult validateFromNative(String idCard, String name) {

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("idNo",idCard);
        List<IdCardInfoModel> nativeDatas = idCardInfoModelEMapper.selectListByParam(paramMap);

        if (nativeDatas != null && nativeDatas.size() > 0) {

            IdCardInfoModel cardInfo = nativeDatas.get(0);
            if (name.equals(cardInfo.getUsername())) {
                IDCheckResult.AUTH_ID_SUCC.setPath(IDCheckResult.PATH_LOCAL);
                return IDCheckResult.AUTH_ID_SUCC;
            } else {
                return IDCheckResult.AUTH_ID_FAIL;
            }

        }

        return null;
    }




    /*
     * 此方法为    解析调用第三方接口后 返回的参数
     * <?xml version="1.0" encoding="UTF-8"?> <data> <message>
     * <status>-9998</status> <value>您的用户信息错误(用户名不存在)</value>
     * <querySeq>c102e000-8864-47c5-abe9-d156fedc1da8</querySeq> </message>
     * </data>
     */
    private IDCheckResult parseCheckIdResult(String idCheckResult) throws TopjetErrorCodeException, TopjetException {

        try {
            StringReader read = new StringReader(idCheckResult);

            SAXReader reader = new SAXReader();
            Document doc = reader.read(read);


            // 获得根节点
            Element element = doc.getRootElement();
            String result = element.selectSingleNode("message/status").getText();

            if (!"0".equals(result)) {
                throw new TopjetException("认证失败，系统返回错误代码:" + result);
            }

            List<Element> checkInfos = element.selectNodes("policeCheckInfos/policeCheckInfo");
            Element checkInfo = checkInfos.get(0);
            String code = checkInfo.selectSingleNode("compStatus").getText();


            if (IDCheckResult.AUTH_ID_SUCC.getCode().equals(code)) {
                return IDCheckResult.AUTH_ID_SUCC;
            } else if (IDCheckResult.AUTH_ID_FAIL.getCode().equals(code)) {
                return IDCheckResult.AUTH_ID_FAIL;
            } else if (IDCheckResult.AUTH_ID_NA.getCode().equals(code)) {
                return IDCheckResult.AUTH_ID_NA;
            }
            LOG.error(result);
            return null;
        } catch (DocumentException e) {
            LOG.error(e.getMessage(), e);
            throw new TopjetErrorCodeException(ErrorCode.E_AUTH_1);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new TopjetErrorCodeException(ErrorCode.E_AUTH_1);
        }

    }


    /**
     * 调用实名认证接口  校验通过后  保存记录 到IdCardInfo 表中
     * @param idNo
     * @param name
     * @param idAddress
     * @throws TopjetException
     */
    @Override
    public void save(String idNo, String name, String idAddress) throws TopjetException {

        try {
            IdCardInfoModel idCardInfo = new IdCardInfoModel();
            idCardInfo.setIdNo(idNoReplaceX(idNo));
            idCardInfo.setUsername(name);
            idCardInfo.setCreateTime(DateUtil.now());
            idCardInfo.setRemark("实名认证");
            idCardInfoModelMapper.insert(idCardInfo);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new TopjetException(e);
        }

    }

    /**
     * 身份证尾号为x的转换成大写X
     * @return
     */
    private static String idNoReplaceX(String idNo){
        if(StringUtils.isBlank(idNo)) return "";
        if(idNo.indexOf("x")==-1)return idNo;
        return idNo.replace("x","X");
    }


    @Override
    public UserInfoVerifiedReturn checkAuthInfo(String idCard, String name, String idAddress) {
        LOG.info("*******************进入调用消息中心接口***********************"+idCard+"**************"+name);
        idCard=idNoReplaceX(idCard);
        UserInfoVerifiedReturn userInfoVerifiedReturn = new UserInfoVerifiedReturn();
        IDCheckResponseDTO responseDTO =new IDCheckResponseDTO();
        IDCheckResult result = null;
        if ("0".equals(SystemConfig.idCheckWSSwitch)) {
            LOG.info("*******************消息中心开关关闭***********************");
            responseDTO.setStatus(Integer.parseInt(IDCheckResult.CHECKID_idCheckWSSwitch.getCode()));
            responseDTO.setDescription(IDCheckResult.CHECKID_idCheckWSSwitch.getValue());
            userInfoVerifiedReturn.setResponseDTO(responseDTO);
            return userInfoVerifiedReturn;
        }

        //查询库里 是否有该条数据
        if ((result = validateFromNative(idCard, name)) != null) {
            userInfoVerifiedReturn.setIdCheckResult(result);
            return userInfoVerifiedReturn;
        }

        //调用消息中心接口
        Request request = new Request();
        request.setAppid(systemConfig.getAppId());
        request.setVersion(systemConfig.getVersion());
        request.setMessagetype(systemConfig.getCheckAuthMethod());//controllerName
        request.setActionname(systemConfig.getCheckAuthActionName());
        request.setTimestamp(com.topjet.tool.common.util.DateUtil.now().getTime()+"");
        request.setMachinecode("");
        String requestUrl = systemConfig.getBaseServiceUrl();
        LOG.info("*******************请求地址为***********************"+requestUrl);
        IDCheckRequestDTO  requestDTO =new IDCheckRequestDTO();
        requestDTO.setQueryType(1);
        requestDTO.setIdentityInfo(name + IDCheckWSProperties.DELIMITER + idCard);
        Gson gson  = new Gson();
        String body = gson.toJson(requestDTO);
        request.setBody(body);
        //md5加密
        Sign sign = new Sign();
        sign.setSignmethod("Md5");
        sign.setSignstr(MethodUtil.MD5(request.getActionname()+request.getAppid()+request.getBody()+StringUtils.trimToEmpty(request.getIp())
                +StringUtils.trimToEmpty(request.getMachinecode())+request.getMessagetype()+request.getTimestamp()+request.getVersion()+systemConfig.getKey()));
        request.setSign(sign);



        String jsonData = JsonUtil.toJSON(request);
        LOG.info("*******************调用接口参数为***********************"+jsonData);
        // 发送 POST 请求
        String stringObject = HttpUtils.httpPost(requestUrl, jsonData);
        System.out.println("返回结果是："+stringObject);
        LOG.info("*******************调用接口返回值***********************"+stringObject);
        if(!org.apache.commons.lang3.StringUtils.isEmpty(stringObject)){
            JSONObject jsons = JSONObject.fromObject(stringObject);
            Map<String, Object> map;
            String  a= jsons.getString("Body");
            if(a == "" || a == null){
                return userInfoVerifiedReturn;
            } else{
                map = JSONObject.fromObject(a);
                responseDTO.setDescription(map.get("value").toString());
                responseDTO.setStatus(Integer.parseInt(map.get("status").toString()));
                JSONArray array =(JSONArray)map.get("list");
                if(array.size()>0){
                    JSONObject ob  = (JSONObject) array.get(0);//得到json对象
                    responseDTO.setCompResult(ob.get("compresult").toString());
                    responseDTO.setCompStatus(ob.get("compstatus").toString());
                }
                userInfoVerifiedReturn.setResponseDTO(responseDTO);
                 //校验成功后   save一条记录
                if (responseDTO.getCompStatus().equals(IDCheckResult.AUTH_ID_SUCC.getCode())) {
                    save(idCard, name, idAddress);
                    LOG.info("*******************校验成功后 save一条记录 ***********************");
                }
                return userInfoVerifiedReturn;
            }
        }
        return null;

    }



}
