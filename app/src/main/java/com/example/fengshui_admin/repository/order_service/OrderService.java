package com.example.fengshui_admin.repository.order_service;

import com.example.fengshui_admin.model.dto.OrderDTO;
import com.example.fengshui_admin.utils.OrderStatus;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderService {

    @GET("order/order-by-status")
    public Observable<ArrayList<OrderDTO>> getAllOrderWithStatus(
            @Query("status") OrderStatus status,
            @Header("Authorization")String accessToken
            );

    @PUT("order/change-order-status/{id}")
    public Observable<String> setOrderStatus(
            @Path("id") Long id,
            @Query("status") OrderStatus orderStatus,
            @Header("Authorization")String accessToken
    );
}
