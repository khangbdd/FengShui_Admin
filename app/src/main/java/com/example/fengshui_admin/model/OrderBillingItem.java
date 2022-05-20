package com.example.fengshui_admin.model;

import java.io.Serializable;
import java.text.DecimalFormat;


public class OrderBillingItem {
    private Long id;
    private Long product;
    private Image image;
    private String name;
    private int price;
    private int amount;
    private String property;

    public String priceToString(){
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(price);
        return  formattedNumber + " VNĐ";
    }
    public String amountToString(){
        return amount + "x";
    }

    public int getTotalPrice(){
        return amount*price;
    }
    public String getTotalPriceToString(){
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(getTotalPrice());
        return formattedNumber + " VNĐ";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
