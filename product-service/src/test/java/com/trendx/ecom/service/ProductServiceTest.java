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
    private final ProductService productService;

    @Autowired
    public ProductServiceTest(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @Test
    public void findById() throws InterruptedException {
        //Given
        Product product = new Product();
        product.setBarcode("BR01");
        product.setDescription("Tshirt");
        product.setMobileSalesPrice(5.5);
        product.setSalesPrice(5);

        productRepository.save(product);
        System.out.println("userServiceTest:findById");
        //When
        Product product1 = productService.findById(product.getId());

        //Then
        assertEquals("BR01", product1.getBarcode());
    }
}
