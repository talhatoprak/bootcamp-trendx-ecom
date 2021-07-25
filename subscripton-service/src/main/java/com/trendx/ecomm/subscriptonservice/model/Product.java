package com.trendx.ecomm.subscriptonservice.model;


public class Product {
    private String id;
    private String barcode;
    private String description;
    private double salesPrice;
    private double mobileSalesPrice;

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
