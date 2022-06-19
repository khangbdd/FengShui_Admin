package com.example.fengshui_admin.model.dto;

import com.example.fengshui_admin.model.Image;

import java.util.ArrayList;

public class ProductDTO {
    private Long id;
    private Long kindID;
    private String description;
    private String name;
    private int price;
    private boolean purchased;
    private int quantity;
    private float rating;
    private ArrayList<Image> images;

    public ProductDTO(Long id, Long kindID, String description, String name, int price, boolean purchased, int quantity, float rating, ArrayList<Image> images) {
        this.id = id;
        this.kindID = kindID;
        this.description = description;
        this.name = name;
        this.price = price;
        this.purchased = purchased;
        this.quantity = quantity;
        this.rating = rating;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKindID() {
        return kindID;
    }

    public void setKindID(Long kindID) {
        this.kindID = kindID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", kindID=" + kindID +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", purchased=" + purchased +
                ", quantity=" + quantity +
                ", rating=" + rating +
                ", images=" + images +
                '}';
    }
}
