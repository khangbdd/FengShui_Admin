package com.example.fengshui_admin.model.dto;

import com.example.fengshui_admin.model.Image;

public class ProductDTO {
    private Long id;
    private String name;
    private Image image;
    private int price;
    private float rating;
    private int purchased;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public ProductDTO(Long id, String name, Image image, int price, float rating, int purchased) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.rating = rating;
        this.purchased = purchased;
    }
}


