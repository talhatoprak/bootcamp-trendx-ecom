package com.trendx.ecom.entity;

import org.springframework.data.couchbase.core.index.QueryIndexed;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.util.ArrayList;
import java.util.List;

@Document
public class User {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    @QueryIndexed
    @Field
    private String email;
    @Field
    private String fullName;
    @Field
    private List<String> followedProducts = new ArrayList<>();

    public User(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }
    public User() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getFollowedProducts() {
        return followedProducts;
    }

    public void setFollowedProducts(List<String> followedProducts) {
        this.followedProducts = followedProducts;
    }
    public void followProduct(String productId) {
        this.followedProducts.add(productId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", followedProducts=" + followedProducts +
                '}';
    }
}
