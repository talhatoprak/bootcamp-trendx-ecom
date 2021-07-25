package com.bootcamptrendxecom.notificationservice;

import com.bootcamptrendxecom.notificationservice.model.SendEmailModel;
import com.bootcamptrendxecom.notificationservice.model.SendMobileNotificationModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @KafkaListener(topics = "${kafka.topic}")
    public void listen(Object msg) {
        //user-api call
        //platforma gore gonderecegiz
        System.out.println(msg);
    }
    @KafkaListener(topics = "sendEmailNotification", groupId = "group-id", containerFactory = "kafkaListenerContainerFactorySendEmailModel")
    public void listenSendEmailNotification(SendEmailModel msg)
    {
        System.out.format("sendEmailNotification: %s\n", msg.toString());
    }


    @KafkaListener(topics = "sendMobileNotification", groupId = "group-id", containerFactory = "kafkaListenerContainerFactorySendMobileNotificationModel")
    public void listenSendMobileNotification(SendMobileNotificationModel msg)
    {
        System.out.format("sendMobileNotification: %s\n", msg.toString());
    }
}
