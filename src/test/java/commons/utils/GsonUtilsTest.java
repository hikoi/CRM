package commons.utils;

import com.crm.core.permission.entity.Permission;
import com.crm.core.wechat.entity.WechatMessage;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.utils.GsonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GsonUtilsTest{

    @Test
    public void deserialize(){
        String json = "[{\"token\":\"1\"},{\"token\":\"2\"}]";

        List<WechatMessage> messages = GsonUtils.deserialize(json, new TypeToken<List<WechatMessage>>(){}.getType());

        for(WechatMessage message : messages){
            System.out.println(message.getWechatId());
        }
    }

    @Test
    public void lucky(){
        String json = "{\"content\":\"\\u003cmsg\\u003e\\n\\t\\u003cappmsg appid\\u003d\\\"\\\" sdkver\\u003d\\\"\\\"\\u003e\\n\\t\\t\\u003cdes\\u003e\\u003c![CDATA[我给你发了一个红包，赶紧去拆!]]\\u003e\\u003c/des\\u003e\\n\\t\\t\\u003curl\\u003e\\u003c![CDATA[https://wxapp.tenpay.com/mmpayhb/wxhb_personalreceive?showwxpaytitle\\u003d1\\u0026msgtype\\u003d1\\u0026channelid\\u003d1\\u0026sendid\\u003d1000039401201805157015511890301\\u0026ver\\u003d6\\u0026sign\\u003d91cb5bba4d6d5d59468377c75dffb09ff9fbeb9f1e10e4973d9ae0fcaa2b2e0dae941b3fd03cedadd0a4c93070c3a860a383900d133bc18489cd18984a24d1d019b1a4ebde0eb375d059165cfa259f7c]]\\u003e\\u003c/url\\u003e\\n\\t\\t\\u003ctype\\u003e\\u003c![CDATA[2001]]\\u003e\\u003c/type\\u003e\\n\\t\\t\\u003ctitle\\u003e\\u003c![CDATA[微信红包]]\\u003e\\u003c/title\\u003e\\n\\t\\t\\u003cthumburl\\u003e\\u003c![CDATA[https://wx.gtimg.com/hongbao/1800/hb.png]]\\u003e\\u003c/thumburl\\u003e\\n\\t\\t\\u003cwcpayinfo\\u003e\\n\\t\\t\\t\\u003ctemplateid\\u003e\\u003c![CDATA[7a2a165d31da7fce6dd77e05c300028a]]\\u003e\\u003c/templateid\\u003e\\n\\t\\t\\t\\u003curl\\u003e\\u003c![CDATA[https://wxapp.tenpay.com/mmpayhb/wxhb_personalreceive?showwxpaytitle\\u003d1\\u0026msgtype\\u003d1\\u0026channelid\\u003d1\\u0026sendid\\u003d1000039401201805157015511890301\\u0026ver\\u003d6\\u0026sign\\u003d91cb5bba4d6d5d59468377c75dffb09ff9fbeb9f1e10e4973d9ae0fcaa2b2e0dae941b3fd03cedadd0a4c93070c3a860a383900d133bc18489cd18984a24d1d019b1a4ebde0eb375d059165cfa259f7c]]\\u003e\\u003c/url\\u003e\\n\\t\\t\\t\\u003ciconurl\\u003e\\u003c![CDATA[https://wx.gtimg.com/hongbao/1800/hb.png]]\\u003e\\u003c/iconurl\\u003e\\n\\t\\t\\t\\u003creceivertitle\\u003e\\u003c![CDATA[恭喜发财，大吉大利]]\\u003e\\u003c/receivertitle\\u003e\\n\\t\\t\\t\\u003csendertitle\\u003e\\u003c![CDATA[恭喜发财，大吉大利]]\\u003e\\u003c/sendertitle\\u003e\\n\\t\\t\\t\\u003cscenetext\\u003e\\u003c![CDATA[微信红包]]\\u003e\\u003c/scenetext\\u003e\\n\\t\\t\\t\\u003csenderdes\\u003e\\u003c![CDATA[查看红包]]\\u003e\\u003c/senderdes\\u003e\\n\\t\\t\\t\\u003creceiverdes\\u003e\\u003c![CDATA[领取红包]]\\u003e\\u003c/receiverdes\\u003e\\n\\t\\t\\t\\u003cnativeurl\\u003e\\u003c![CDATA[wxpay://c2cbizmessagehandler/hongbao/receivehongbao?msgtype\\u003d1\\u0026channelid\\u003d1\\u0026sendid\\u003d1000039401201805157015511890301\\u0026sendusername\\u003dwxid_6j7bhfxp4fry22\\u0026ver\\u003d6\\u0026sign\\u003d91cb5bba4d6d5d59468377c75dffb09ff9fbeb9f1e10e4973d9ae0fcaa2b2e0dae941b3fd03cedadd0a4c93070c3a860a383900d133bc18489cd18984a24d1d019b1a4ebde0eb375d059165cfa259f7c]]\\u003e\\u003c/nativeurl\\u003e\\n\\t\\t\\t\\u003csceneid\\u003e\\u003c![CDATA[1002]]\\u003e\\u003c/sceneid\\u003e\\n\\t\\t\\t\\u003cinnertype\\u003e\\u003c![CDATA[0]]\\u003e\\u003c/innertype\\u003e\\n\\t\\t\\t\\u003cpaymsgid\\u003e\\u003c![CDATA[1000039401201805157015511890301]]\\u003e\\u003c/paymsgid\\u003e\\n\\t\\t\\t\\u003cscenetext\\u003e微信红包\\u003c/scenetext\\u003e\\n\\t\\t\\t\\u003clocallogoicon\\u003e\\u003c![CDATA[c2c_hongbao_icon_cn]]\\u003e\\u003c/locallogoicon\\u003e\\n\\t\\t\\t\\u003cinvalidtime\\u003e\\u003c![CDATA[1526460858]]\\u003e\\u003c/invalidtime\\u003e\\n\\t\\t\\u003c/wcpayinfo\\u003e\\n\\t\\u003c/appmsg\\u003e\\n\\t\\u003cfromusername\\u003e\\u003c![CDATA[wxid_6j7bhfxp4fry22]]\\u003e\\u003c/fromusername\\u003e\\n\\u003c/msg\\u003e\\n\",\"conversationTime\":1526374458000,\"msgType\":\"436207665\",\"status\":3,\"token\":\"3a96fff95e6c4f41b930ffb48b688a75\",\"username\":\"wxid_6j7bhfxp4fry22\"}";
        WechatMessage message = GsonUtils.deserialize(json, WechatMessage.class);

        System.out.println(message);
    }

    @Test
    public void test(){
        Permission permission = new Permission();
        permission.setCreateTime(new Date());

        List<Entity> list = new ArrayList<Entity>();
        list.add(permission);
        list.add(permission);

        System.out.println(GsonUtils.serialize(list));
    }

    @Test
    public void test2(){
        String json2 = "{\"id\": 3,\"name\": \"邮箱产品部\",\"parentid\": 2,\"order\": 40}";
        Map map = GsonUtils.deserialize(json2, new TypeToken<Map>(){}.getType());
        System.out.println(map);
    }
}
