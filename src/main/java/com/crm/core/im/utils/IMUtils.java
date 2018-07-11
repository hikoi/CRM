package com.crm.core.im.utils;

import com.crm.core.im.entity.IMUser;
import com.crm.core.im.utils.response.IMResponse;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.wah.doraemon.security.exception.UtilsException;
import org.wah.doraemon.utils.HttpClientUtils;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class IMUtils{

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private static final String  SUCCESS         = "OK";
    private static final String  FAIL            = "FAIL";

    //账号导入
    private static final String ACCOUNT_IMPORT = "https://console.tim.qq.com/v4/im_open_login_svc/account_import?usersig={0}&identifier={1}&sdkappid={2}&contenttype=json";

    public static void accountImport(String sdkAppId, String administrator, String sig, IMUser user){
        if(StringUtils.isBlank(sig)){
            throw new UtilsException("IM用户签名不能为空");
        }
        if(StringUtils.isBlank(sdkAppId)){
            throw new UtilsException("IM应用ID不能为空");
        }
        if(StringUtils.isBlank(administrator)){
            throw new UtilsException("IM应用管理员不能为空");
        }
        if(user == null || StringUtils.isBlank(user.getName())){
            throw new UtilsException("IM用户信息不能为空");
        }

        //API
        String url = MessageFormat.format(ACCOUNT_IMPORT, sig, administrator, sdkAppId);
        //参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("Identifier", user.getName());
        params.put("Nick", user.getName());
        params.put("FaceUrl", user.getHeadImgUrl());

        CloseableHttpClient client = HttpClientUtils.createHttpsClient();

        try{
            HttpPost post = HttpClientUtils.post(url, null, params);
            HttpResponse response = client.execute(post);

            if(!HttpClientUtils.isOk(response)){
                throw new UtilsException(EntityUtils.toString(response.getEntity()));
            }

            IMResponse imResponse = HttpClientUtils.parse(response, IMResponse.class);
            //验证请求结果
            if(imResponse.getStatus().equalsIgnoreCase(FAIL)){
                throw new UtilsException(imResponse.getErrorInfo());
            }
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }finally{
            HttpClientUtils.close(client);
        }
    }
}
