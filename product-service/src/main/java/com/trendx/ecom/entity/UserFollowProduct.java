package com.trendx.ecom.entity;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class UserFollowProduct {
    @Id
    private long id;
    @Field
    private long userId;
    @Field
    private long productId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
