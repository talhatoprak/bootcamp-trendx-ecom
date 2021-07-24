package com.trendx.ecomm.subscriptonservice.service;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
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
        subscriptionService.followProduct("user1","prd1");
        subscriptionService.followProduct("user1","prd2");
        subscriptionService.followProduct("user1","prd3");

        subscriptionService.followProduct("user2","prd1");
        subscriptionService.followProduct("user2","prd2");
        subscriptionService.followProduct("user2","prd4");

        subscriptionService.followProduct("user3","prd1");
        subscriptionService.followProduct("user3","prd3");
        subscriptionService.followProduct("user3","prd4");

        Set<String> users=subscriptionService.getUsersByProductId("prd2");
        assertEquals(users.size(), 2);
    }
    @Test
    public void getSubscriptionByUserId(){
        Subscription subscription= subscriptionService.getSubscriptionByUserId("user1");
    }
}
