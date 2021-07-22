package com.trendx.ecomm.subscriptonservice;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import com.trendx.ecomm.subscriptonservice.service.SubscriptionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    private final SubscriptionService subscriptionService;

    public Listener(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void listen(String msg) {
        String[] data = msg.split(":");
        Subscription savedSubscription = subscriptionService.followProduct(data[0], Long.parseLong(data[1]));
        System.out.println(msg);
        System.out.println(savedSubscription);
    }
}
