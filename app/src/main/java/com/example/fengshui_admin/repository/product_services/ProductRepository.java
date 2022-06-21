package com.example.fengshui_admin.repository.product_services;

import com.example.fengshui_admin.model.dto.ProductDTO;
import com.example.fengshui_admin.model.dto.ProductDetailDTO;
import com.example.fengshui_admin.model.dto.ProductImageDTO;
import com.example.fengshui_admin.model.dto.ProductNewDTO;
import com.example.fengshui_admin.repository.RetrofitInstance;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ProductRepository {
    private ProductService ProductService;
    @Inject
    public ProductRepository(){
        ProductService  =  RetrofitInstance.getInstance().retrofit.create(ProductService.class);
    }

    public Observable<ArrayList<ProductDTO>> getAllProduct(){
        return ProductService.getAllProducts();
    }

    public Observable<ArrayList<ProductImageDTO>> getProductImageWithProductID(Long id){
        return ProductService.getProductImageWithProductID(id);
    }

    public Observable<ProductDetailDTO> getProductWithProductID(Long id){

        return ProductService.getProductWithProductID(id);
    }

    public Observable<ProductNewDTO> addProduct(ProductNewDTO productNewDTO, String accessToken){
        return ProductService.addProduct(productNewDTO, accessToken);
    }
}
