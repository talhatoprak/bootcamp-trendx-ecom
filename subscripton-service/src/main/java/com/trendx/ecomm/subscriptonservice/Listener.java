package com.trendx.ecomm.subscriptonservice;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import com.trendx.ecomm.subscriptonservice.model.PriceChangeModel;
import com.trendx.ecomm.subscriptonservice.model.Product;
import com.trendx.ecomm.subscriptonservice.model.SendEmailModel;
import com.trendx.ecomm.subscriptonservice.model.User;
import com.trendx.ecomm.subscriptonservice.service.SubscriptionService;
import com.trendx.ecomm.subscriptonservice.service.UserClientService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Listener {
    private final SubscriptionService subscriptionService;

    public Listener(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    @KafkaListener(topics = "deleteProduct", groupId = "group-id", containerFactory = "kafkaListenerContainerFactoryString")
    public void listenDeleteProduct(String msg) {
        subscriptionService.deleteProduct(msg);
        System.out.format("deleteUser: %s\n", msg);
    }

    @KafkaListener(topics = "changeSalesPrice", groupId = "group-id", containerFactory = "kafkaListenerContainerFactory")
    public void listenChangeSalesPrice(PriceChangeModel model) {
        subscriptionService.listenChangeSalesPrice(model);
        System.out.println(model);
    }

    @KafkaListener(topics = "deleteUser", groupId = "group-id", containerFactory = "kafkaListenerContainerFactoryString")
    public void listenDeleteUser(String msg) {
        System.out.format("deleteUser: %s\n", msg);
        StringBuffer sb = new StringBuffer(msg);
        sb.delete(msg.length() - 1, msg.length());
        sb.delete(0, 1);

        subscriptionService.deleteUserFromSubscriptionsSet(sb.toString());
    }
}
