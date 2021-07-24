package com.trendx.ecomm.subscriptonservice;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import com.trendx.ecomm.subscriptonservice.model.PriceChangeModel;
import com.trendx.ecomm.subscriptonservice.service.SubscriptionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    private final SubscriptionService subscriptionService;

    public Listener(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "group-id", containerFactory = "kafkaListenerContainerFactoryString")
    public void listen(String msg) {
        System.out.println(msg);
        String[] data = msg.split(":");
        Subscription savedSubscription = subscriptionService.followProduct(data[0], Long.parseLong(data[1]));
        System.out.println(msg);
        System.out.println(savedSubscription);
    }

    @KafkaListener(topics = "deleteUser", groupId = "group-id", containerFactory = "kafkaListenerContainerFactoryString")
    public void listenDeleteProduct(String msg) {
        System.out.format("deleteUser: %s\n", msg);
    }

    @KafkaListener(topics = "changeSalesPrice", groupId = "group-id", containerFactory = "kafkaListenerContainerFactory")
    public void listenChangeSalesPrice(PriceChangeModel model) {
        System.out.println(model);
    }
}
