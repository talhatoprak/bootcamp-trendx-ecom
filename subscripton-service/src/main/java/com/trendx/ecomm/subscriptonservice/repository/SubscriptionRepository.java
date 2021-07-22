package com.trendx.ecomm.subscriptonservice.repository;

import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface SubscriptionRepository extends CouchbaseRepository<Subscription, String> {
}
