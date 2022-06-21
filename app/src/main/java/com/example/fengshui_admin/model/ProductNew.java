package com.example.fengshui_admin.model;

public class ProductNew {
    private String description;
    private String name;
    private int price;
    private Long kindId;

    public ProductNew(String description, String name, int price, Long kindId) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.kindId = kindId;
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

    public Long getKindId() {
        return kindId;
    }

    public void setKindId(Long kindId) {
        this.kindId = kindId;
    }
}
