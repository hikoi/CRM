package core.im.utils;

import com.crm.commons.consts.IMConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.wah.doraemon.utils.HttpClientUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IMUtilsTest{

    @Test
    public void addFriend(){
        String fromAccount = "9d1b86f5fdaf459b9dc773d8ef4663f1";
        String toAccount   = "621c62f470e94160a4f9417fe82966b2";

        Map<String, Object> friends = new HashMap<String, Object>();
        friends.put("To_Account", toAccount);
        friends.put("AddSource", "AddSource_Type_CRM");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("From_Account", fromAccount);
        params.put("AddType", "Add_Type_Both");
        params.put("AddFriendItem", Arrays.asList(friends));

        String url = "https://console.tim.qq.com/v4/sns/friend_add?usersig={0}&identifier={1}&sdkappid={2}&contenttype=json";

        CloseableHttpClient client = HttpClientUtils.createHttpsClient();
        HttpPost post = HttpClientUtils.post(MessageFormat.format(url, IMConfig.ADMINISTRATOR_SIG, IMConfig.ADMINISTRATOR, IMConfig.SDK_APPID), null, (Object) params);

        try{
            HttpResponse response = client.execute(post);
            System.out.println(EntityUtils.toString(response.getEntity()));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            HttpClientUtils.close(client);
        }
    }
}
