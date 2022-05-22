package com.example.fengshui_admin.repository.order_service;

import com.example.fengshui_admin.model.dto.OrderDTO;
import com.example.fengshui_admin.repository.RetrofitInstance;
import com.example.fengshui_admin.utils.OrderStatus;

import java.util.ArrayList;


import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import io.reactivex.Observable;

public class OrderRepository {

    @Inject
    public OrderRepository(){}

    private OrderService orderService  =  RetrofitInstance.retrofit.create(OrderService.class);

    public Observable<ArrayList<OrderDTO>> getAllOrderWithStatus(OrderStatus orderStatus){
        return orderService.getAllOrderWithStatus(orderStatus);
    }
}
