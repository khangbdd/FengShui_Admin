package com.example.fengshui_admin.repository.order_service;

import com.example.fengshui_admin.model.dto.OrderDTO;
import com.example.fengshui_admin.utils.OrderStatus;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderService {

    @GET("/order/all-order-by")
    public Observable<ArrayList<OrderDTO>> getAllOrderWithStatus(
            @Path("status") OrderStatus status
    );
}
