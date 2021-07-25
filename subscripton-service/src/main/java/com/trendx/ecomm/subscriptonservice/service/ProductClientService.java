package com.trendx.ecomm.subscriptonservice.service;

import com.trendx.ecomm.subscriptonservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClientService {
    private final RestTemplate template;
    @Value("${productApiAddress}")
    private String productServiceUrl;

    public ProductClientService(@Autowired RestTemplate template) {
        this.template = template;
    }

    public Product getProductById(String productId){
        String url=productServiceUrl+"/product/findbyid?id="+productId.toString();
        ResponseEntity<Product> responseEntity=template.getForEntity(url, Product.class);
        return responseEntity.getBody();
    }
}
