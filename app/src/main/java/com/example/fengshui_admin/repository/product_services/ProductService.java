package com.example.fengshui_admin.repository.product_services;

import com.example.fengshui_admin.model.dto.ProductDTO;
import com.example.fengshui_admin.model.dto.ProductDetailDTO;
import com.example.fengshui_admin.model.dto.ProductImageDTO;
import com.example.fengshui_admin.model.dto.ProductNewDTO;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {

    @GET("products")
    public Observable<ArrayList<ProductDTO>> getAllProducts(
    );

    @GET("products/{id}/images")
    public Observable<ArrayList<ProductImageDTO>> getProductImageWithProductID(
            @Path("id") Long id
    );

    @GET("products/{id}")
    public Observable<ProductDetailDTO> getProductWithProductID(
            @Path("id") Long id
    );

    @POST("products")
    public Observable<ProductNewDTO> addProduct(
            @Body ProductNewDTO productNewDTO,
            @Header("Authorization")String accessToken
    );
}
