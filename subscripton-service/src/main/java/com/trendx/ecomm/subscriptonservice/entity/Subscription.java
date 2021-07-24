package com.trendx.ecomm.subscriptonservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.index.QueryIndexed;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.Set;

@Document
public class Subscription {
    @Id
    private final String productId;

    @Field
    private final Set<String> followerUserIds;
    @Field
    @QueryIndexed
    private boolean isDeleted;

    public Subscription(String productId, Set<String> followerUserIds) {
        this.productId = productId;
        this.followerUserIds = followerUserIds;
        this.isDeleted=false;
    }

    public String getProductId() {
        return productId;
    }

    public Set<String> getFollowerUserIds() {
        return followerUserIds;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void followProduct(String userId) {
        followerUserIds.add(userId);
    }

    public void unfollowProduct(String userId) {
        followerUserIds.remove(userId);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "productId='" + productId + '\'' +
                ", followerUserIds=" + followerUserIds +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
