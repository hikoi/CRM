package commons.utils;

import com.crm.core.wechat.utils.WechatMessageUtils;
import org.junit.Test;

public class WechatMessageUtilsTest{

    @Test
    public void emojiParse(){
//        String content = "wxid_zx2jsufjz9cf21:0:0:02d6afe43631df78b6d85667a12f6447:<msg><emoji fromusername = \"wxid_zx2jsufjz9cf21\" tousername = \"wxid_98m3ys9svjnu22\" type=\"2\" idbuffer=\"media*#*0_0\" md5=\"02d6afe43631df78b6d85667a12f6447\" len = \"262284\" productid=\"\" androidmd5=\"02d6afe43631df78b6d85667a12f6447\" androidlen=\"262284\" s60v3md5 = \"02d6afe43631df78b6d85667a12f6447\" s60v3len=\"262284\" s60v5md5 = \"02d6afe43631df78b6d85667a12f6447\" s60v5len=\"262284\" cdnurl = \"http*#*//emoji.qpic.cn/wx_emoji/dSIzgfekNeX2RrWrncphSr3LBjicfWzq3lXcGl8PQtbkn8dfu2zvbug/\" designerid = \"\" thumburl = \"\" encrypturl = \"http*#*//emoji.qpic.cn/wx_emoji/W18xn1SdhFMQUbjK8ic5PxVjibLCv1EvJQicD9fiawkp5m2kKKbmJVYShg/\" aeskey= \"b9fd52be14085ff0a638b647f6ee0fb9\" externurl = \"http*#*//emoji.qpic.cn/wx_emoji/O4icEW0h6q8LmU999k9ia8tcLYToDsKWAXZvF6ibv7jswPxUPaiaV9nKQtqCRSIVN9z5/\" externmd5 = \"a2248ad76a7fde0c2cf5cc96017331bd\" width= \"228\" height= \"216\" tpurl= \"\" tpauthkey= \"\" ></emoji> </msg>:0\n";

        String content = "wxid_98m3ys9svjnu22:1526372777865:0:2ad8d271eed5cf83c953da2b9fed81ae::0\n";

        content = WechatMessageUtils.emojiParse(content);
        System.out.println(content);
    }

    @Test
    public void transferParse(){
        String content = "<msg><appmsg appid=\"\" sdkver=\"\"><title><![CDATA[微信转账]]></title><des><![CDATA[收到转账0.01元。如需收钱，请点此升级至最新版本]]></des><action></action><type>2000</type><content><![CDATA[]]></content><url><![CDATA[https://support.weixin.qq.com/cgi-bin/mmsupport-bin/readtemplate?t=page/common_page__upgrade&text=text001&btn_text=btn_text_0]]></url><thumburl><![CDATA[https://support.weixin.qq.com/cgi-bin/mmsupport-bin/readtemplate?t=page/common_page__upgrade&text=text001&btn_text=btn_text_0]]></thumburl><lowurl></lowurl><extinfo></extinfo><wcpayinfo><paysubtype>1</paysubtype><feedesc><![CDATA[￥0.01]]></feedesc><transcationid><![CDATA[100005020118051500024221626146236933]]></transcationid><transferid><![CDATA[1000050201201805151000491402768]]></transferid><invalidtime><![CDATA[1526465681]]></invalidtime><begintransfertime><![CDATA[1526373881]]></begintransfertime><effectivedate><![CDATA[1]]></effectivedate><pay_memo><![CDATA[]]></pay_memo></wcpayinfo></appmsg></msg>";

        content = WechatMessageUtils.transferParse(content);
        System.out.println(content);
    }

    @Test
    public void luckyPackageParse(){
        String content = "<msg>\n" +
                "\t<appmsg appid=\"\" sdkver=\"\">\n" +
                "\t\t<des><![CDATA[我给你发了一个红包，赶紧去拆!]]></des>\n" +
                "\t\t<url><![CDATA[https://wxapp.tenpay.com/mmpayhb/wxhb_personalreceive?showwxpaytitle=1&msgtype=1&channelid=1&sendid=1000039401201805157016110721158&ver=6&sign=f7543e999bcb1a09b3d795dee8685666a98e2d5806105d540f7f71a87a63ebe8d111927291fa4f8fa34c08f0a95b00092bada114da0a8f41d744dc35ecd01d382c6d767a2184453c5513c449731bd1eb]]></url>\n" +
                "\t\t<type><![CDATA[2001]]></type>\n" +
                "\t\t<title><![CDATA[微信红包]]></title>\n" +
                "\t\t<thumburl><![CDATA[https://wx.gtimg.com/hongbao/1800/hb.png]]></thumburl>\n" +
                "\t\t<wcpayinfo>\n" +
                "\t\t\t<templateid><![CDATA[7a2a165d31da7fce6dd77e05c300028a]]></templateid>\n" +
                "\t\t\t<url><![CDATA[https://wxapp.tenpay.com/mmpayhb/wxhb_personalreceive?showwxpaytitle=1&msgtype=1&channelid=1&sendid=1000039401201805157016110721158&ver=6&sign=f7543e999bcb1a09b3d795dee8685666a98e2d5806105d540f7f71a87a63ebe8d111927291fa4f8fa34c08f0a95b00092bada114da0a8f41d744dc35ecd01d382c6d767a2184453c5513c449731bd1eb]]></url>\n" +
                "\t\t\t<iconurl><![CDATA[https://wx.gtimg.com/hongbao/1800/hb.png]]></iconurl>\n" +
                "\t\t\t<receivertitle><![CDATA[收钱拉]]></receivertitle>\n" +
                "\t\t\t<sendertitle><![CDATA[收钱拉]]></sendertitle>\n" +
                "\t\t\t<scenetext><![CDATA[微信红包]]></scenetext>\n" +
                "\t\t\t<senderdes><![CDATA[查看红包]]></senderdes>\n" +
                "\t\t\t<receiverdes><![CDATA[领取红包]]></receiverdes>\n" +
                "\t\t\t<nativeurl><![CDATA[wxpay://c2cbizmessagehandler/hongbao/receivehongbao?msgtype=1&channelid=1&sendid=1000039401201805157016110721158&sendusername=wxid_6j7bhfxp4fry22&ver=6&sign=f7543e999bcb1a09b3d795dee8685666a98e2d5806105d540f7f71a87a63ebe8d111927291fa4f8fa34c08f0a95b00092bada114da0a8f41d744dc35ecd01d382c6d767a2184453c5513c449731bd1eb]]></nativeurl>\n" +
                "\t\t\t<sceneid><![CDATA[1002]]></sceneid>\n" +
                "\t\t\t<innertype><![CDATA[0]]></innertype>\n" +
                "\t\t\t<paymsgid><![CDATA[1000039401201805157016110721158]]></paymsgid>\n" +
                "\t\t\t<scenetext>微信红包</scenetext>\n" +
                "\t\t\t<locallogoicon><![CDATA[c2c_hongbao_icon_cn]]></locallogoicon>\n" +
                "\t\t\t<invalidtime><![CDATA[1526462189]]></invalidtime>\n" +
                "\t\t</wcpayinfo>\n" +
                "\t</appmsg>\n" +
                "\t<fromusername><![CDATA[wxid_6j7bhfxp4fry22]]></fromusername>\n" +
                "</msg>";

        content = WechatMessageUtils.luckyPackageParse(content);
        System.out.println(content);
    }

    @Test
    public void system(){
//        String content = "黎海桦开启了朋友验证，你还不是他（她）朋友。请先发送朋友验证请求，对方验证通过后，才能聊天。<a href=\"weixin://findfriend/verifycontact\">发送朋友验证</a>";
        String content = "你已添加了黎海桦，现在可以开始聊天了。";

        content = WechatMessageUtils.systemParse(content);
        System.out.println(content);
    }
}
