package com.trendx.ecomm.subscriptonservice.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.trendx.ecomm.subscriptonservice.entity.Subscription;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ScanConsistency;

import java.util.List;
import java.util.Set;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "subscription",viewName = "all")
public interface SubscriptionRepository extends CouchbaseRepository<Subscription, String> {
   // Set<String> getSubscriptionUserIdByFollowedProductIds(String productId);
    /*@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<Subscription> findAllByFollowedProductIds(String productId);*/

    List<Subscription> findAllByIsDeleted(boolean isDeleted);

}
