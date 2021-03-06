package com.trendx.ecom.product.controller;

import com.trendx.ecom.product.entity.Product;
import com.trendx.ecom.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/findbyid")
    public Product findById(@RequestParam String id){
        System.out.println(String.format("ProductController :: findById :: ProductId = %s",id));
        return this.productService.findById(id);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        System.out.println("ProductController :: createProduct :: "+product.toString());
        return this.productService.create(product);
    }

    @PostMapping("/delete")
    public  void deleteById(@RequestParam String id)
    {
        System.out.println(String.format("ProductController :: deleteById :: ProductId = %s",id));
        productService.deleteById(id);
    }
    @PutMapping("/changeMobilePrice")
    public  void changeMobileSalesPrice(@RequestParam String id,@RequestParam double price)
    {
        System.out.println(String.format("ProductController :: changeMobileSalesPrice :: ProductId = %s  price = %s",id,price));
        productService.changeMobileSalesPrice(id,price);
    }
    @PutMapping("/changeWebPrice")
    public  void changeWebSalesPrice(@RequestParam String id,@RequestParam double price)
    {
        System.out.println(String.format("ProductController :: changeWebSalesPrice :: ProductId = %s  price = %s",id,price));
        productService.changeWebSalesPrice(id,price);
    }

}
