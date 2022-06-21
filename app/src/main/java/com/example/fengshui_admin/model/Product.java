package com.example.fengshui_admin.model;

import java.text.DecimalFormat;

public class Product {
    private Long id;
    private Image image;
    private String name;
    private int price;
    private float rating;
    private int purchased;

    public String priceToString() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(price);
        return formattedNumber + " VNĐ";
    }

    public Product(Long id, Image image, String name, int price, float rating, int purchased) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.purchased = purchased;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", purchased=" + purchased +
                '}';
    }
}

