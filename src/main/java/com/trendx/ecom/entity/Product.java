package com.trendx.ecom.entity;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;


@Document
public class Product {
    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;
    @Field
    private String barcode;
    @Field
    private String description;
    @Field
    private double salesPrice;
    @Field
    private double mobileSalesPrice;

    public Product() {
    }

    public Product(String barcode, String description, double salesPrice, double mobileSalesPrice) {
        this.barcode = barcode;
        this.description = description;
        this.salesPrice = salesPrice;
        this.mobileSalesPrice = mobileSalesPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getMobileSalesPrice() {
        return mobileSalesPrice;
    }

    public void setMobileSalesPrice(double mobileSalesPrice) {
        this.mobileSalesPrice = mobileSalesPrice;
    }
}
