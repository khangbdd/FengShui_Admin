package com.example.fengshui_admin.fragment.order_fragment;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fengshui_admin.model.dto.OrderDTO;
import com.example.fengshui_admin.repository.order_service.OrderRepository;
import com.example.fengshui_admin.utils.OrderStatus;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class OrderViewModel extends ViewModel {

    @Inject
    public OrderViewModel(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public MutableLiveData<ArrayList<OrderDTO>> lstOrder;
    private final OrderRepository orderRepository;

    @SuppressLint("CheckResult")
    private void loadTestOrder(){
        orderRepository.getAllOrderWithStatus(OrderStatus.Pending)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new Observer<ArrayList<OrderDTO>>(){
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ArrayList<OrderDTO> orderDTOS) {
                                    lstOrder.setValue(orderDTOS);
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
