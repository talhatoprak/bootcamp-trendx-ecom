package com.trendx.ecom.service;

import com.trendx.ecom.entity.Product;
import com.trendx.ecom.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceTest {

    private final ProductRepository productRepository;

    public ProductServiceTest(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    public void findById() throws InterruptedException {
        //Given
        Product product= new Product();
        product.setBarcode("BR01");
        product.setDescription("Tshirt");
        product.setMobileSalesPrice(5.5);
        product.setSalesPrice(5);

        productRepository.save(product);
        Thread.sleep(50);
        System.out.println("userServiceTest:findById");
        //When
        Product product1 = productRepository.findById(product.getId()).orElse(null);

        //Then
        assertEquals("BR01", product1.getBarcode());
    }
}
