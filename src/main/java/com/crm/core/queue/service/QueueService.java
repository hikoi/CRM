package com.crm.core.queue.service;

public interface QueueService{

    void pushSensitive(String wxno);

    void saveWechatMessage(String messageString);

    void saveWechatMessages(String messagesString);
}
