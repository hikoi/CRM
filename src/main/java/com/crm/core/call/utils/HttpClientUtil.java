package com.crm.core.call.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.crm.core.call.consts.TencentCloud;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class HttpClientUtil {

    private final static String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static String postRequest(String url, Map<String, Object> param) throws ClientProtocolException, IOException{

        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

        Gson gson = new Gson();
        String parameter = gson.toJson(param);

        StringEntity se = new StringEntity(parameter);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        httpPost.setEntity(se);

        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");

        return result;
    }


//  发起呼叫
    public static String outCAll(String appId, String caller, String called, String data, int Timeout){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appId",appId);
        map.put("caller",caller);
        map.put("called",called);
        map.put("data",data);
        map.put("Timeout",Timeout);
        try {
            System.out.println(postRequest(TencentCloud.TEST_IP + TencentCloud.OUT_CALL,map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //  播放语音
    public static String play(String appId, String callId, int getkey, int playFlag, String voiceStr, int playTime, int maxRevCnt, String key2End, int spaceTime, int totalTime, String data){

        return "";
    }


//  留言
    public static String message(String appId, String callId, int playFlag, String voiceStr, String key2Stop, String data){
        return "";
    }

//  呼叫转接
    public static String transfer(String appId, String callId, String called, String fileName, String data){
        return "";
    }

//  呼叫应答
    public static String reply(String appId, String callId, int ansCode){
        return "";
    }

//  结束呼叫
    public static String disConnect(String appId, String callId){
        return "";
    }

    public static void main(String[] args) {

    }

}
