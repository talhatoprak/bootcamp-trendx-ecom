package com.trendx.ecomm.subscriptonservice.model;

public class PriceChangeModel {
    private String productId;
    private String platform;
    private double oldPrice;
    private double newPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return "PriceChangeModel{" +
            "productId='" + productId + '\'' +
            ", platform='" + platform + '\'' +
            ", oldPrice=" + oldPrice +
            ", newPrice=" + newPrice +
            '}';
    }
}
