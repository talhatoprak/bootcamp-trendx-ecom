package com.trendx.ecomm.subscriptonservice.service;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import com.trendx.ecomm.subscriptonservice.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public Subscription getSubscriptionByUserId(String userId) {
        return repository.findById(userId).orElseThrow(RuntimeException::new);
    }

    public Subscription followProduct(String userId, Long productId) {
        Subscription subscription = repository.findById(userId)
            .orElse(new Subscription(userId, new HashSet<>()));

        subscription.followProduct(productId);
        return repository.save(subscription);
    }

    public Subscription unfollowProduct(String userId, Long productId) {
        Subscription subscription = getSubscriptionByUserId(userId);
        subscription.unfollowProduct(productId);
        if (subscription.getFollowedProductIds().isEmpty()) {
            repository.deleteById(userId);
            return subscription;
        }
        return repository.save(subscription);
    }
}
