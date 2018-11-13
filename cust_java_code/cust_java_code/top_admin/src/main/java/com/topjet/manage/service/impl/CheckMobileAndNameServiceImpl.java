package com.topjet.manage.service.impl;

import com.google.gson.Gson;
import com.topjet.common.constants.SystemConfig;
import com.topjet.manage.domain.bean.CheckMobileAndNameRequestDTO;
import com.topjet.manage.domain.bean.CheckMobileAndNameResponseDTO;
import com.topjet.manage.domain.dto.Request;
import com.topjet.manage.domain.dto.Sign;
import com.topjet.manage.service.CheckMobileAndNameService;
import com.topjet.tool.common.util.DateUtil;
import com.topjet.tool.common.util.JsonUtil;
import com.topjet.util.HttpUtils;
import com.topjet.util.MethodUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyj on 2017/11/15.
 */
@Service
public class CheckMobileAndNameServiceImpl implements CheckMobileAndNameService {

    @Autowired
    private SystemConfig systemConfig;


    @Override
    public CheckMobileAndNameResponseDTO checkMobileAndName(String name, String mobile, String pid) {
        CheckMobileAndNameRequestDTO dto = new CheckMobileAndNameRequestDTO();
        dto.setName(name);
        dto.setMobile(mobile);
        dto.setPid(pid);
        String requestUrl = systemConfig.getBaseServiceUrl();
        Request request = new Request();
        request.setAppid(systemConfig.getAppId());
        request.setVersion(systemConfig.getVersion());
        request.setMessagetype(systemConfig.getMessageType());
        request.setActionname(systemConfig.getActionName());
        request.setTimestamp(DateUtil.now().getTime()+"");
        request.setMachinecode("");
        Gson gson  = new Gson();
        String body = gson.toJson(dto);
        request.setBody(body);
        Sign sign = new Sign();
        sign.setSignmethod("Md5");
        sign.setSignstr(MethodUtil.MD5(request.getActionname()+request.getAppid()+request.getBody()+StringUtils.trimToEmpty(request.getIp())
                +StringUtils.trimToEmpty(request.getMachinecode())+request.getMessagetype()+request.getTimestamp()+request.getVersion()+systemConfig.getKey()));
        request.setSign(sign);
        String jsonData = JsonUtil.toJSON(request);
        // 发送 POST 请求
        String stringObject = HttpUtils.httpPost(requestUrl, jsonData);
        System.out.println("返回结果是："+stringObject);
        CheckMobileAndNameResponseDTO responseDTO = new CheckMobileAndNameResponseDTO();
        if(!org.apache.commons.lang3.StringUtils.isEmpty(stringObject)){
            JSONObject jsons = JSONObject.fromObject(stringObject);
            Map<String, Object> map = new HashMap<String, Object>();
            String  a= jsons.getString("Body");
            if(a == "" || a == null){
                return responseDTO;
            }
            else{
                map = JSONObject.fromObject(a);
                responseDTO.setMatchcode(map.get("matchcode").toString());
                responseDTO.setOperatortext(map.get("operatortext").toString());
                return responseDTO;
            }
        }
        return null;

    }

}
