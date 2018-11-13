package com.topjet.manage.common.dynamicpassword.request;

import com.topjet.manage.common.dynamicpassword.response.DynamicPasswordResponse;
import com.topjet.manage.common.dynamicpassword.response.DynamicPasswordResponseData;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;


public class DynamicPasswordRequest {
	public static DynamicPasswordResponse doPost(String address) throws Exception {
		DynamicPasswordResponse dynamicPasswordResponse = new DynamicPasswordResponse();
		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod(address);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);
		int code = httpClient.executeMethod(method);
		if (200 == code) {
			String response = method.getResponseBodyAsString();
			if(response.equals("0")||response.equals("1")||response.equals("999")){
				dynamicPasswordResponse.setRetcode(Integer.parseInt(response));
				return dynamicPasswordResponse;
			}
			JSONObject object = JSONObject.fromObject(response);
			Integer retcode = (Integer) object.get("retcode");
			dynamicPasswordResponse.setRetcode(retcode);
			if (retcode == 0) {
				dynamicPasswordResponse.setRetmsg((String) object.get("retmsg"));
				Object data = object.get("data");
				if (data != null) {
					JSONObject dataObject = JSONObject.fromObject(data);
					DynamicPasswordResponseData dynamicPasswordResponseData = new DynamicPasswordResponseData();
					dynamicPasswordResponseData.setInitdata((String) dataObject.get("initdata"));
					dynamicPasswordResponseData.setNewUIDInOTP((Integer) dataObject.get("newUIDInOTP"));
					dynamicPasswordResponse.setDynamicPasswordResponseData(dynamicPasswordResponseData);
				}
			}
		}
		return dynamicPasswordResponse;
	}

}
