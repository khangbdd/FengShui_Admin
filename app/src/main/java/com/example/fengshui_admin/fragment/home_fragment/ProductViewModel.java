package com.example.fengshui_admin.fragment.home_fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fengshui_admin.model.Product;
import com.example.fengshui_admin.model.dto.ProductDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.repository.product_services.ProductRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class ProductViewModel extends ViewModel {
    public TokenDTO token = null;

    private final ProductRepository productRepository;
    public MutableLiveData<ArrayList<Product>> lstDisplay = new MutableLiveData<>();
    public MutableLiveData<ArrayList<ProductDTO>> lstProducts = new MutableLiveData<>();

    @Inject
    public ProductViewModel(ProductRepository productRepository) {
        lstDisplay.setValue(new ArrayList<>());
        this.productRepository = productRepository;
    }

    public void loadToken(TokenDTO token)
    {
        this.token =token;
        loadProducts();
    }


    @SuppressLint("CheckResult")
    public void loadProducts(){
        lstDisplay.setValue(new ArrayList<>());
        lstProducts.setValue(new ArrayList<>());
        productRepository.getAllProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ArrayList<ProductDTO>>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onNext(ArrayList<ProductDTO> productDTOS) {
                        lstProducts.setValue(productDTOS);
                        ArrayList<Product> listAttempt = new ArrayList<>();
                        lstProducts.getValue().stream().forEach(productDTO ->
                        {
                            listAttempt.add(convertToProduct(productDTO));
                        });
                        lstDisplay.setValue(listAttempt);
                        Log.d("dsdsf..............", String.valueOf(lstDisplay.getValue().size()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Product convertToProduct(ProductDTO productDTO)
    {
        return new Product(
                productDTO.getId(),
                productDTO.getImage(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getRating(),
                productDTO.getPurchased()
        );
    }
}
