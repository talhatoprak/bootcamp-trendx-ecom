package com.trendx.ecomm.subscriptonservice.service;

import com.trendx.ecomm.subscriptonservice.model.SendEmailModel;
import com.trendx.ecomm.subscriptonservice.model.SendNotificationModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, SendEmailModel> kafkaTemplateSendEmailModel;
    private final KafkaTemplate<String, SendNotificationModel> kafkaTemplateSendNotificationModel;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, SendEmailModel> kafkaTemplateSendEmailModel, KafkaTemplate<String, SendNotificationModel> kafkaTemplateSendNotificationModel) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateSendEmailModel = kafkaTemplateSendEmailModel;
        this.kafkaTemplateSendNotificationModel = kafkaTemplateSendNotificationModel;
    }


    public void sendMessage(String msg,String topic){
        kafkaTemplate.send(topic, msg);
    }

    public void sendMessage(SendEmailModel msg,String topic){
        kafkaTemplateSendEmailModel.send(topic, msg);
    }
    public void sendMessage(SendNotificationModel msg,String topic){
        kafkaTemplateSendNotificationModel.send(topic, msg);
    }
}

