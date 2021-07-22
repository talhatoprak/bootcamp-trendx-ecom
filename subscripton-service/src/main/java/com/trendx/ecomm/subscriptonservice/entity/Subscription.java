package com.trendx.ecomm.subscriptonservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.Set;

@Document
public class Subscription {
    @Id
    private final String userId;

    @Field
    private final Set<Long> followedProductIds;

    public Subscription(String userId, Set<Long> followedProductIds) {
        this.userId = userId;
        this.followedProductIds = followedProductIds;
    }

    public String getUserId() {
        return userId;
    }

    public Set<Long> getFollowedProductIds() {
        return followedProductIds;
    }

    public void followProduct(Long productId) {
        followedProductIds.add(productId);
    }

    public void unfollowProduct(Long productId) {
        followedProductIds.remove(productId);
    }

    @Override
    public String toString() {
        return "Subscription{" +
            "userId='" + userId + '\'' +
            ", followedProductIds=" + followedProductIds +
            '}';
    }
}
