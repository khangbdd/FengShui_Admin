package com.example.fengshui_admin.fragment.product_detail_fragment;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.example.fengshui_admin.model.ProductDetail;
import com.example.fengshui_admin.model.ProductNew;
import com.example.fengshui_admin.model.dto.ProductDetailDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.repository.product_services.ProductRepository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailViewModel {
    public TokenDTO token = null;

    private ProductRepository productRepository;
    public MutableLiveData<ProductNew> newProduct = new MutableLiveData<>();
    public MutableLiveData<ProductDetail> productDetail = new MutableLiveData<>();

    public ProductDetailViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SuppressLint("CheckResult")
    public void loadProductsWithProductID(Long id){
        productRepository.getProductWithProductID(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ProductDetailDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onNext(ProductDetailDTO productDetailDTO) {
                        productDetail.setValue(convertToProductDetail(id, productDetailDTO));
                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
//    @SuppressLint("CheckResult")
//    public void addProduct(ProductNew productNew){
//        productRepository.addProduct(productNew, token).subscribeOn(Schedulers.io()).subscribeWith(new Observer<ProductNewDTO>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(ProductNewDTO productNewDTO) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private ProductDetail convertToProductDetail(Long id, ProductDetailDTO productDTO)
    {
        return new ProductDetail(
                id,
                productDTO.getDescription(),
                productDTO.getKindId(),
                productDTO.getKindName(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getPurchase(),
                productDTO.getQuantity(),
                productDTO.getRating(),
                productDTO.getTotalReview()
        );
    }
}
