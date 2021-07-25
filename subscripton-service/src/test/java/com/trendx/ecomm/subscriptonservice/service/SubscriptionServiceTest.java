package com.trendx.ecomm.subscriptonservice.service;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import com.trendx.ecomm.subscriptonservice.model.PriceChangeModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SubscriptionServiceTest {
    private final SubscriptionService subscriptionService;
    @Autowired
    public SubscriptionServiceTest(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Test
    public void findByFollowedProductId(){
        subscriptionService.followProduct("1","ffa3bed8-e9b1-48ec-9383-8f451767f14d");
        subscriptionService.followProduct("3","ffa3bed8-e9b1-48ec-9383-8f451767f14d");

        subscriptionService.followProduct("3","884c01af-eda6-41b8-adff-13074d1200ed");

        Set<String> users=subscriptionService.getUsersByProductId("ffa3bed8-e9b1-48ec-9383-8f451767f14d");
        assertEquals(users.size(), 2);
    }
    @Test
    public void getSubscriptionByUserId(){
        Subscription subscription= subscriptionService.getSubscriptionByUserId("user1");
    }

    @Test
    public void listenChangeSalesPrice(){
        PriceChangeModel model=new PriceChangeModel();
        model.setNewPrice(7);
        model.setOldPrice(8);
        model.setPlatform("mobil");
        model.setProductId("ffa3bed8-e9b1-48ec-9383-8f451767f14d");

        subscriptionService.listenChangeSalesPrice(model);
    }
}
