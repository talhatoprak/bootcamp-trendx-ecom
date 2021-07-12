package com.trendx.ecom.service;

import com.trendx.ecom.entity.Product;
import com.trendx.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return this.productRepository.findAll();
    }

    public Product findById(String productId){
        return this.productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Not found Product"));
    }

    public Product create(Product product){
        return this.productRepository.save(product);
    }

    public void deleteFindById(String productId){
        this.productRepository.deleteById(productId);
    }

    public void changeMobileSalesPrice(String productId, double mobilePrice){
        Product product=this.findById(productId);
        product.setMobileSalesPrice(mobilePrice);
        productRepository.save(product);
    }

    public void changeWebSalesPrice(String productId, double webPrice){
        Product product=this.findById(productId);
        product.setSalesPrice(webPrice);
        productRepository.save(product);
    }
}
