package com.example.fengshui_admin.model.dto;

public class ProductImageDTO {
    private String imageURL;
    private int priority;

    public ProductImageDTO(String imageURL, int priority) {
        this.imageURL = imageURL;
        this.priority = priority;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
