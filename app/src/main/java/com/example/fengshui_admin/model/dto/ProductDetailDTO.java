package com.example.fengshui_admin.model.dto;

public class ProductDetailDTO {
    private Long id;
    private String description;
    private Long kindId;
    private String kindName;
    private String name;
    private int price;
    private int purchase;
    private int quantity;
    private float rating;
    private int totalReview;

    public ProductDetailDTO(Long id, String description, Long kindId, String kindName, String name, int price, int purchase, int quantity, float rating, int totalReview) {
        this.id = id;
        this.description = description;
        this.kindId = kindId;
        this.kindName = kindName;
        this.name = name;
        this.price = price;
        this.purchase = purchase;
        this.quantity = quantity;
        this.rating = rating;
        this.totalReview = totalReview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getKindId() {
        return kindId;
    }

    public void setKindId(Long kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
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

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
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

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }
}
