package com.bootcamptrendxecom.notificationservice;

import org.springframework.kafka.annotation.KafkaListener;

public class Listener {

    @KafkaListener(topics = "${kafka.topic}")
    public void listen(Object msg) {
        //user-api call
        //platforma gore gonderecegiz
        System.out.println(msg);
    }
    @org.springframework.kafka.annotation.KafkaListener(topics = "deleteUser")
    public void listenDeleteProduct(String msg)
    {
        System.out.format("deleteUser: %s\n", msg);
    }
}
