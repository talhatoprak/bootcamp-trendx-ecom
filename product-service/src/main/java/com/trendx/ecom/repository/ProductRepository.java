package com.trendx.ecom.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.trendx.ecom.entity.Product;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CouchbaseRepository<Product,String> {
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<Product> findById(String s);
}
