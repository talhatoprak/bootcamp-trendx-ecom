package com.trendx.ecomm.subscriptonservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import com.trendx.ecomm.subscriptonservice.model.PriceChangeModel;
import com.trendx.ecomm.subscriptonservice.service.SubscriptionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

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
    @org.springframework.kafka.annotation.KafkaListener(topics = "deleteUser")
    public void listenDeleteProduct(String msg)
    {
        System.out.format("deleteUser: %s\n", msg);
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "changeSalesPrice",groupId = "group-id")
    public void listenChangeSalesPrice(Object model) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        Field field=model.getClass().getDeclaredField("value");
        field.setAccessible(true);
        Object value = field.get(model);
        System.out.format("changePrice: %s\n", value);
       PriceChangeModel priceChangeModel=  new ObjectMapper().readValue(value.toString(), PriceChangeModel.class);

    }
}
