package commons.utils;

import com.crm.core.wechat.entity.WechatMessage;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.wah.doraemon.utils.GsonUtils;

import java.util.List;

public class GsonUtilsTest{

    @Test
    public void deserialize(){
        String json = "[{\"token\":\"1\"},{\"token\":\"2\"}]";

        List<WechatMessage> messages = GsonUtils.deserialize(json, new TypeToken<List<WechatMessage>>(){}.getType());

        for(WechatMessage message : messages){
            System.out.println(message.getWechatId());
        }
    }
}
