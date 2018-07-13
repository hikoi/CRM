package com.crm.core.queue.service;

public interface QueueService{

    void saveWechatMessage(String messageString);

    void saveWechatMessages(String messagesString);
}
