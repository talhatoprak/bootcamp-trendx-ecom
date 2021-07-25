package com.trendx.ecom.product.service;


import com.trendx.ecom.product.entity.Product;
import com.trendx.ecom.product.kafkamodel.PriceChangeModel;
import com.trendx.ecom.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Value("${kafka.topic-delete-product}")
    private String topicName;

    private final ProductRepository productRepository;
    private final KafkaService kafkaService;

    public ProductService(ProductRepository productRepository, KafkaService kafkaService) {
        this.productRepository = productRepository;
        this.kafkaService = kafkaService;
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product findById(String productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Not found Product"));
    }
    public Product findByBarcode(String barcode) {
        return this.productRepository.findByBarcode(barcode);
    }
    public Product create(Product product) {
        if (product.getBarcode() == null) {
            throw new RuntimeException(String.format("Barcode cannot be null"));
        }
        if (product.getBarcode().isEmpty()) {
            throw new RuntimeException(String.format("Barcode cannot be empty"));
        }
        if (productRepository.existsByBarcode(product.getBarcode())) {
            throw new RuntimeException(String.format("There is already a product barcode as: %s", product.getBarcode()));
        }
        return this.productRepository.save(product);
    }

    public void changeMobileSalesPrice(String productId, double mobilePrice) {
        Product product = this.findById(productId);
        double oldPrice=product.getMobileSalesPrice();
        double newPrice=mobilePrice;
        product.setMobileSalesPrice(mobilePrice);
        productRepository.save(product);
        PriceChangeModel changeModel=new PriceChangeModel(productId,"mobile",oldPrice,mobilePrice);
        kafkaService.sendChangePriceMessage(changeModel, "changeSalesPrice");
    }

    public void changeWebSalesPrice(String productId, double webPrice) {
        Product product = this.findById(productId);
        double oldPrice=product.getSalesPrice();
        double newPrice=webPrice;
        product.setSalesPrice(webPrice);
        productRepository.save(product);
        PriceChangeModel changeModel=new PriceChangeModel(productId,"web",oldPrice,webPrice);
        kafkaService.sendChangePriceMessage(changeModel, "changeSalesPrice");
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
        kafkaService.sendMessage(id, topicName);
    }

    public void deleteByBarcode(String barcode) {
        boolean existsByBarcode = productRepository.existsByBarcode(barcode);

        if(!existsByBarcode)
            return;
        Product product=productRepository.findByBarcode(barcode);
        productRepository.deleteByBarcode(barcode);
        kafkaService.sendMessage(product.getId(), topicName);
    }
}
