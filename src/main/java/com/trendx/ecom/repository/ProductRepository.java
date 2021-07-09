package com.trendx.ecom.repository;

import com.trendx.ecom.entity.Product;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CouchbaseRepository<Product,String> {
}
