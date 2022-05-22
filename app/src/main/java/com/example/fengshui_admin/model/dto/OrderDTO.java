package com.example.fengshui_admin.model.dto;


import com.example.fengshui_admin.utils.OrderStatus;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public
class OrderDTO{
    private Long id;
    private Date orderTime;
    private OrderStatus orderStatus;
    private String address;
    private int totalPrice;
    private String cancelReason;
    private ArrayList<OrderItemDTO> orderItemDTOList;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                ", address='" + address + '\'' +
                ", totalPrice=" + totalPrice +
                ", cancelReason='" + cancelReason + '\'' +
                ", orderItemDTOList=" + orderItemDTOList +
                '}';
    }
}
