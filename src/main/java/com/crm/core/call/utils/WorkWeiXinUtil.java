package com.crm.core.call.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by miku03 on 2018/5/24.
 */
public class WorkWeiXinUtil {
    private static String corpid = "wwdb02562cf1f43144";
    private static String corpsecret = "5lDhXJzPCW31fdtuNDWkuo2Yffm2cw3jxbvcU_wnmCo";

    /**
     * geToken
     */
    public static String geToken() {
        CloseableHttpClient httpClient = HttpSSLClient.createDefault();
        String result = "";
        try {
            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret);

            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //响应成功
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                System.out.println("result: " + result);
                JSONObject jsonObject = JSONObject.fromObject(result);
                System.out.println(jsonObject.get("access_token"));
                result = jsonObject.get("access_token").toString();

            }
        } catch (Exception e) {
            throw new RuntimeException("失败");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * getUserId
     */
    public static String getUserId(String code, String access_token) {
        CloseableHttpClient httpClient = HttpSSLClient.createDefault();
        String result = "";
        try {
            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=" + access_token + "&code=" + code);

            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //响应成功
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                JSONObject jsonObject = JSONObject.fromObject(result);
                System.out.println(jsonObject.get("UserId"));
                result = jsonObject.get("UserId").toString();

            }
        } catch (Exception e) {
            throw new RuntimeException("失败");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * getUserList
     */
    public static String getUserList(String department_id, String access_token) {
        CloseableHttpClient httpClient = HttpSSLClient.createDefault();
        String result = "";
        try {
            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + access_token + "&department_id=" + department_id + "&fetch_child=0");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //响应成功
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
//                System.out.println("result: " + result);
            }
        } catch (Exception e) {
            throw new RuntimeException("失败");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * getUserInfo
     */
    public static String getUserInfo(String userId, String access_token) {
        CloseableHttpClient httpClient = HttpSSLClient.createDefault();
        String result = "";
        try {
            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + access_token + "&userid=" + userId);

            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //响应成功
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("失败");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取部门列表
     */
    public static String getDepartmentList(String department_id, String access_token) {
        CloseableHttpClient httpClient = HttpSSLClient.createDefault();
        String result = "";
        try {
            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + access_token + "&id=" + department_id);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("失败");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
