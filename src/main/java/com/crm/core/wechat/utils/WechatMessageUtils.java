package com.crm.core.wechat.utils;

import com.crm.core.wechat.entity.WechatMessage;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.wah.doraemon.security.exception.UtilsException;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class WechatMessageUtils{

    public static String extract(WechatMessage message){
        if(message == null){
            return "";
        }

        String content = message.getContent();

        switch(message.getType()){
            case TEXT:
                return content;
            case EMOTICONS:
                return emojiParse(content);
            case TRANSFER:
                return transferParse(content);
            case LUCKY_PACKAGE:
                return luckyPackageParse(content);
            case SYSTEM:
                return systemParse(content);
            default:
                return "";
        }
    }

    public static String emojiParse(String content){
        if(StringUtils.isBlank(content)){
            return "";
        }

        try{
            Pattern pattern = Pattern.compile("<msg>[\\s\\S]*</msg>");
            Matcher matcher = pattern.matcher(content);

            if(matcher.find()){
                //匹配需要的内容
                content = matcher.group();
                //解释xml
                Document document = DocumentHelper.parseText(content);
                //根节点
                Element root = document.getRootElement();
                //正文
                Element emoji = root.element("emoji");
                //图片路径
                String cdnurl = emoji.attributeValue("cdnurl");

                if(StringUtils.isNotBlank(cdnurl)){
                    cdnurl = cdnurl.replace("http*#*", "http:");
                }

                return cdnurl;
            }

            return "";
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    public static String transferParse(String content){
        if(StringUtils.isBlank(content)){
            return "";
        }

        try{
            //解释xml
            Document document = DocumentHelper.parseText(content);
            //根节点
            Element root   = document.getRootElement();
            Element appmsg = root.element("appmsg");
            //正文
            Element des = appmsg.element("des");
            //截取内容
            content = des.getTextTrim();

            Pattern pattern = Pattern.compile("收到转账([1-9]\\d*|0)(\\.\\d{1,2})?元。");
            Matcher matcher = pattern.matcher(content);

            if(matcher.find()){
                return matcher.group();
            }

            return content;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    public static String luckyPackageParse(String content){
        if(StringUtils.isBlank(content)){
            return "";
        }

        try{
            //解释xml
            Document document = DocumentHelper.parseText(content);
            //根节点
            Element root   = document.getRootElement();
            Element appmsg = root.element("appmsg");
            //正文
            Element title       = appmsg.element("title");
            Element wcpayinfo   = appmsg.element("wcpayinfo");

            Element senderTitle = wcpayinfo.element("sendertitle");

            return MessageFormat.format("{0}[{1}]", title.getTextTrim(), senderTitle.getTextTrim());
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    public static String systemParse(String content){
        if(StringUtils.isBlank(content)){
            return "";
        }

        try{
            //非好友消息
            Pattern pattern = Pattern.compile("[\\s\\S]*开启了朋友验证，你还不是他（她）朋友。请先发送朋友验证请求，对方验证通过后，才能聊天。");
            Matcher matcher = pattern.matcher(content);
            if(matcher.find()){
                return matcher.group();
            }

            //好友添加消息
            pattern = Pattern.compile("你已添加了[\\s\\S]*，现在可以开始聊天了。");
            matcher = pattern.matcher(content);
            if(matcher.find()){
                return matcher.group();
            }

            return "";
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }
}
