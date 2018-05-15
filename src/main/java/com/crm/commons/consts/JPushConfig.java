package com.crm.commons.consts;

import org.apache.commons.codec.binary.Base64;

public class JPushConfig{

    public static final Base64 base64 = new Base64();

    public static final String PUSH_API = "https://bjapi.push.jiguang.cn/v3/push";

    public static final String APP_KEY = "447d3c125d1f667ace40d169";
    public static final String SECRET  = "c69a4d2b91a73be8f48f2494";

    public static final String PLATFORM_ANDROID  = "android";
    public static final String PLATFORM_IOS      = "ios";
    public static final String PLATFORM_WINPHONE = "winphone";

    public static final String AUTH_STRING(){
        return "Basic " + new String(base64.encode(new String(APP_KEY + ":" + SECRET).getBytes()));
    }
}
