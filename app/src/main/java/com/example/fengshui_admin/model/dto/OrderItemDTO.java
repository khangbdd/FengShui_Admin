package com.example.fengshui_admin.model.dto;

import lombok.Data;

@Data class OrderItemDTO{
    private Long id;
    private Long product;
    private String imageUrl;
    private String name;
    private int price;
    private int quantity;
    private String property;
}
