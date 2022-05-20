package com.example.fengshui_admin.model;

import android.annotation.SuppressLint;

import com.example.fengshui_admin.utils.OrderStatus;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order  {
    private Long id;
    private Date orderTime;
    private OrderStatus status;
    private String address;
    private ArrayList<OrderBillingItem> billingItems;
    private int totalPrice;
    private String cancelReason  ="";

    public String totalPriceToString(){
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedNumber  = formatter.format(totalPrice);
        return formattedNumber + " VNĐ";
    }
    @SuppressLint("SimpleDateFormat")
    public String getTimeString(){
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return simpleDate.format(orderTime);
    }
    public String getStatusString() {
        return status.toString();
    }
    public String getCancelReason(){
        return cancelReason;
    }
    public void setCancelReason(String cancelReason){
        this.cancelReason = cancelReason;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<OrderBillingItem> getBillingItems() {
        return billingItems;
    }

    public void setBillingItems(ArrayList<OrderBillingItem> billingItems) {
        this.billingItems = billingItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
