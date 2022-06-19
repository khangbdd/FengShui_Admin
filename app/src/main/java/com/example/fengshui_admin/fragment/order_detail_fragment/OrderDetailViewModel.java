package com.example.fengshui_admin.fragment.order_detail_fragment;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fengshui_admin.model.Order;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.repository.order_service.OrderRepository;
import com.example.fengshui_admin.utils.OrderStatus;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class OrderDetailViewModel extends ViewModel {
    public TokenDTO token = null;

    private OrderRepository orderRepository;
    public MutableLiveData<Order> detailOrder = new MutableLiveData<>();

    @Inject
    public OrderDetailViewModel(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @SuppressLint("CheckResult")
    public void setOrderStatus(long id, OrderStatus orderStatus)
    {
        orderRepository.setOrderStatus(id, orderStatus, token.getTokenType()+" "+token.getAccessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                            Order newStatusOrder = detailOrder.getValue();
                            newStatusOrder.setStatus(orderStatus);
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
}
