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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public ArrayList<OrderItemDTO> getOrderItemDTOList() {
        return orderItemDTOList;
    }

    public void setOrderItemDTOList(ArrayList<OrderItemDTO> orderItemDTOList) {
        this.orderItemDTOList = orderItemDTOList;
    }
}
