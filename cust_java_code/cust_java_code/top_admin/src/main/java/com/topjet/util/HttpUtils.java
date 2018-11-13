package com.topjet.util;

import net.sf.json.JSONObject;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;





/**
 * Created by liyj on 2017/11/15.
 */
public class HttpUtils {
    /**
     * 发送post请求--用于接口接收的参数为JSON字符串
     * @param url 请求地址
     * @param params json格式的字符串
     * @return
     */
    public static String httpPost(String url, String params){
        String result = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            /*
             * 发送json字符串，这两句需要设置
             */
            httpPost.addHeader("Content-type","application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");

            httpPost.setEntity(new StringEntity(params, "UTF-8"));

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                // Read the response body
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

