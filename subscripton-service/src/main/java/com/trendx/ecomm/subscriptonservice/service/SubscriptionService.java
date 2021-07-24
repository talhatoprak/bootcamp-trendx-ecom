package com.trendx.ecomm.subscriptonservice.service;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import com.trendx.ecomm.subscriptonservice.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public Subscription getSubscriptionByUserId(String userId) {
        return repository.findById(userId).orElseThrow(RuntimeException::new);
    }
    public Set<String> getUsersByProductId(String productId){
        Subscription subscription=repository.findById(productId).orElse(null);
        if(subscription==null)
            return null;
        return subscription.getFollowerUserIds();
    }
    public Subscription followProduct(String userId, String productId) {
        Subscription subscription = repository.findById(productId)
            .orElse(new Subscription(productId, new HashSet<>()));

        subscription.followProduct(userId);
        return repository.save(subscription);
    }

    public Subscription unfollowProduct(String userId, String productId) {
        Subscription subscription = getSubscriptionByUserId(productId);
        subscription.unfollowProduct(userId);
        if (subscription.getFollowerUserIds().isEmpty()) {
            repository.deleteById(productId);
            return subscription;
        }
        return repository.save(subscription);
    }
}
