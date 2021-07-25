package com.trendx.ecomm.subscriptonservice.controller;

import com.trendx.ecomm.subscriptonservice.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subscription/")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController (SubscriptionService userService) {
        this.subscriptionService = userService;
    }
    @PostMapping("/follow")
    public void followProduct(@RequestParam String userId,@RequestParam String productId) {
        System.out.printf("subscriptionController :: followProduct :: %s --> %s\n", userId,productId);
        subscriptionService.followProduct(userId,productId);
    }
}
