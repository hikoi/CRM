package com.crm.core.im.utils;

import com.crm.core.im.consts.IMMessageType;
import com.crm.core.im.entity.IMMsgBody;
import com.crm.core.im.entity.IMMessage;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;
import org.wah.doraemon.utils.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @link https://cloud.tencent.com/document/product/269/2282
 */
@NoArgsConstructor
public class IMMessageUtils{

    public static IMMessage createTextMsg(String fromAccount, String toAccount, String... contents){
        if(StringUtils.isBlank(toAccount)){
            throw new UtilsException("IM信息接收人不能为空");
        }

        //创建消息体
        List<IMMsgBody> bodys = new ArrayList<IMMsgBody>();

        for(String content : contents){
            IMMsgBody body = new IMMsgBody();

            Map<String, Object> msgContent = new HashMap<String, Object>();
            msgContent.put("Text", content);

            body.setMsgType(IMMessageType.TIM_TEXT_ELEM);
            body.setMsgContent(msgContent);
            bodys.add(body);
        }

        //时间戳
        Long timestamp = DateUtils.timestamp(true);

        IMMessage message = new IMMessage();
        message.setFromAccount(fromAccount);
        message.setToAccount(toAccount);
        message.setSyncOtherMachine(1);
        message.setMsgRandom(timestamp);
        message.setMsgTimeStamp(timestamp);
        message.setType(IMMessageType.TIM_TEXT_ELEM);
        message.setMsgBody(bodys);

        return message;
    }

    public static IMMessage createCustomMsg(String fromAccount, String toAccount, IMMessageType type, String description,
                                            String ext, String... contents){
        if(StringUtils.isBlank(toAccount)){
            throw new UtilsException("IM信息接收人不能为空");
        }

        //创建消息正文
        List<IMMsgBody> bodys = new ArrayList<IMMsgBody>();

        //文本消息
        for(String content : contents){
            IMMsgBody body = new IMMsgBody();

            Map<String, Object> msgContent = new HashMap<String, Object>();
            msgContent.put("Text", content);

            body.setMsgType(IMMessageType.TIM_TEXT_ELEM);
            body.setMsgContent(msgContent);
            bodys.add(body);
        }

        //自定义消息
        IMMsgBody body = new IMMsgBody();

        Map<String, Object> msgContent = new HashMap<String, Object>();
        msgContent.put("Data", type);
        msgContent.put("Desc", description);
        msgContent.put("Ext", ext);

        body.setMsgType(IMMessageType.TIM_CUSTOM_ELEM);
        body.setMsgContent(msgContent);
        bodys.add(body);

        //时间戳
        Long timestamp = DateUtils.timestamp(true);

        //创建消息体
        IMMessage message = new IMMessage();
        message.setFromAccount(fromAccount);
        message.setToAccount(toAccount);
        message.setSyncOtherMachine(1);
        message.setMsgRandom(timestamp);
        message.setMsgTimeStamp(timestamp);
        message.setType(IMMessageType.TIM_CUSTOM_ELEM);
        message.setMsgBody(bodys);

        return message;
    }
}
