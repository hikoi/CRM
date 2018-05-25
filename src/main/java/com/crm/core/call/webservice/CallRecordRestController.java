package com.crm.core.call.webservice;

import com.crm.core.call.consts.CallStatus;
import com.crm.core.call.entity.CallRecord;
import com.crm.core.call.service.CallRecordService;
import com.crm.core.call.utils.HttpClientUtil;
import com.crm.core.call.utils.TencentCloud;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by miku03 on 2018/5/11.
 */
@RestController
@RequestMapping(value = "/api/1.0/call")
public class CallRecordRestController {

    private static Logger logger = LoggerFactory.getLogger(CallRecordRestController.class);

    @Autowired
    private CallRecordService callRecordService;

    @RequestMapping(value = "/call", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed call(String appId, String caller, String called, String data, String callerId, String calledId, String callerName, String calledName) {
        Responsed responsed = new Responsed();
        try {
            System.out.println("appId: " + appId);
            System.out.println("caller: " + caller);
            System.out.println("callerId: " + callerId);
            System.out.println("callerName: " + callerName);
            System.out.println("called: " + called);
            System.out.println("calledId: " + calledId);
            System.out.println("calledName: " + calledName);
            System.out.println("data: " + data);
            if (StringUtils.isBlank(appId)) {
                responsed.setSuccess(false);
                responsed.setMsg("appId 不能为空");
                return responsed;
            }
            if (StringUtils.isBlank(caller)) {
                responsed.setSuccess(false);
                responsed.setMsg("caller 不能为空");
                return responsed;
            }
            if (StringUtils.isBlank(called)) {
                responsed.setSuccess(false);
                responsed.setMsg("called 不能为空");
                return responsed;
            }
            if (StringUtils.isBlank(callerId)) {
                responsed.setSuccess(false);
                responsed.setMsg("callerId 不能为空");
                return responsed;
            }
            if (StringUtils.isBlank(calledId)) {
                responsed.setSuccess(false);
                responsed.setMsg("calledId 不能为空");
                return responsed;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appId", appId);
            map.put("caller", caller);
            map.put("called", called);
            map.put("data", data);
            map.put("Timeout", 40);
            String outCallResult = HttpClientUtil.postRequest(TencentCloud.TEST_IP + TencentCloud.OUT_CALL, map);
            JSONObject jsonObject = JSONObject.fromObject(outCallResult);
            CallRecord callRecord = new CallRecord();
            if ("000000".equals(jsonObject.get("code").toString())) {
                callRecord.setCallId(jsonObject.get("callId").toString());
            } else {
                callRecord.setTcFailCode(jsonObject.get("code").toString());
                responsed.setSuccess(false);
                responsed.setMsg("拨号异常: " + jsonObject.get("code").toString());
                return responsed;
            }
            callRecord.setAppId(appId);
            callRecord.setCaller(caller);
            callRecord.setCalled(called);
            callRecord.setCallerId(callerId);
            callRecord.setCalledId(calledId);
            callRecord.setStartTime(new Date());
            callRecord.setStatus(CallStatus.CALLER_OUT);
            callRecordService.save(callRecord);
            responsed.setSuccess(true);
            responsed.setMsg("拨号成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responsed;
    }


    /**
     * 外呼
     */
    @RequestMapping(value = "/outCall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String outCall(String appId, String caller, String called, String data) {
        String result = "";
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appId", appId);
            map.put("caller", caller);
            map.put("called", called);
            map.put("data", data);
            map.put("Timeout", 40);
            result = HttpClientUtil.postRequest(TencentCloud.TEST_IP + TencentCloud.OUT_CALL, map);
            CallRecord callRecord = new CallRecord();
            callRecord.setAppId(appId);
            callRecord.setCaller(caller);
            callRecord.setCalled(called);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 转接
     */
    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String transfer(String appId, String callId, String called, String fileName, String data) {
        String result = "";
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appId", appId);
            map.put("callId", callId);
            map.put("called", called);
            map.put("data", data);
            result = HttpClientUtil.postRequest(TencentCloud.TEST_IP + TencentCloud.TRANSFER, map);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 挂断
     */
    @RequestMapping(value = "/disConnect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String disConnect(String appId, String callId) {
        String result = "";
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appId", appId);
            map.put("callId", callId);
            result = HttpClientUtil.postRequest(TencentCloud.TEST_IP + TencentCloud.DISCONNECT, map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 播放语音
     */
    @RequestMapping(value = "/play", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String play(String appId, String callId, int getkey, int playFlag, String voiceStr, int playTime, int maxRevCnt, String key2End, int spaceTime, int totalTime, String data) {
        String result = "";
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appId", appId);
            map.put("callId", callId);
            map.put("getkey", getkey);
            map.put("playFlag", playFlag);
            map.put("voiceStr", voiceStr);
            map.put("playTime", playTime);
            map.put("maxRevCnt", maxRevCnt);
            map.put("key2End", key2End);
            map.put("spaceTime", spaceTime);
            map.put("totalTime", totalTime);
            map.put("data", data);
            result = HttpClientUtil.postRequest(TencentCloud.TEST_IP + TencentCloud.PLAY, map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 回调
     */
    @RequestMapping(value = "/callback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String callback(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = null;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            jsonObject = JSONObject.fromObject(sb.toString());
            String callId = jsonObject.get("callId").toString();
            CallRecord callRecord = callRecordService.getByCallId(callId);
            if ("000000".equals(jsonObject.get("code").toString())) {
                if (CallStatus.CALLER_OUT.equals(callRecord.getStatus())) {
                    if ("2".equals(jsonObject.get("ansCode").toString())) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("appId", callRecord.getAppId());
                        map.put("callId", callRecord.getCallId());
                        map.put("called", callRecord.getCalled());
                        map.put("data", callRecord.getData());
                        String result = HttpClientUtil.postRequest(TencentCloud.TEST_IP + TencentCloud.TRANSFER, map);
                        jsonObject = JSONObject.fromObject(result);
                        if ("000000".equals(jsonObject.get("code").toString())) {
                            callRecord.setStatus(CallStatus.CALLED_OUT);
                        } else {
                            callRecord.setTcFailCode(jsonObject.get("code").toString());
                        }
                    }
                }
            } else {
                callRecord.setTcFailCode(jsonObject.get("code").toString());
            }
            callRecordService.update(callRecord);
            jsonObject.put("code", "0");
            jsonObject.put("msg", "0");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonObject.toString();
    }


    /**
     * 播放语音
     */
    @RequestMapping(value = "/testBean", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CallRecord testBean(String callId, String appId, String caller, String callerId, String callerName, String called, String calledId, String calledName, String TotalMins,
                               String recordUrl, String callType, String status, String data, String dtmfCode, String dtmfType, String tcFailCode) {
        String result = "";
        try {
//            CallRecord callRecord = new CallRecord();
//            callRecord.setAppId("appId");
//            callRecord.setTcFailCode("tcFailCode2");
//            callRecord.setCalled("called");
//            callRecord.setCalledId("calledId");
//            callRecord.setCalledName("calledName");
//            callRecord.setCaller("caller");
//            callRecord.setCallerId("callerId");
//            callRecord.setCallerName("callerName");
//            callRecord.setCallType("callType2");
//            callRecord.setData("data2");
//            callRecord.setDtmfCode("dtmfCode2");
//            callRecord.setDtmfType("dtmfType2");
//            callRecord.setEndTime(new Date());
//            callRecord.setRecordUrl("recordUrl2");
//            callRecord.setCallId("callId");
//            callRecord.setStartTime(new Date());
//            callRecord.setStatus("status2");
//            callRecord.setTotalMins(Integer.parseInt("1112"));
//            callRecordService.update(callRecord);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return callRecordService.getByCallId("callId");
    }
}
