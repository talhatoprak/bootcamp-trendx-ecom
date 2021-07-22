package com.trendx.ecom.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.trendx.ecom.entity.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CouchbaseRepository<User,String> {
    boolean existsByEmail(String email);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<User> findById(String s);
}
