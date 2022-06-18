package com.example.fengshui_admin.fragment.order_fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.security.AppUriAuthenticationPolicy;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fengshui_admin.model.Image;
import com.example.fengshui_admin.model.Order;
import com.example.fengshui_admin.model.OrderBillingItem;
import com.example.fengshui_admin.model.dto.OrderDTO;
import com.example.fengshui_admin.model.dto.OrderItemDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.repository.order_service.OrderRepository;
import com.example.fengshui_admin.repository.room_database.AccountDatabase;
import com.example.fengshui_admin.utils.OrderStatus;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
    public TokenDTO token = null;
    public MutableLiveData<ArrayList<Order>> lstDisplay = new MutableLiveData<>();
    @Inject
    public OrderViewModel(OrderRepository orderRepository) {
        lstDisplay.setValue(new ArrayList<>());
        this.orderRepository = orderRepository;
    }

    public void loadToken(TokenDTO token)
    {
        this.token =token;
        loadOrder(OrderStatus.Pending);
    }

    public MutableLiveData<ArrayList<OrderDTO>> lstOrder = new MutableLiveData<>();
    private final OrderRepository orderRepository;

    @SuppressLint("CheckResult")
    public void loadOrder(OrderStatus orderStatus){
        lstDisplay.setValue(new ArrayList<>());
        lstOrder.setValue(new ArrayList<>());
        orderRepository.getAllOrderWithStatus(orderStatus, token.getTokenType()+" "+token.getAccessToken())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new Observer<ArrayList<OrderDTO>>(){
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onNext(ArrayList<OrderDTO> orderDTOS) {
                                    lstOrder.setValue(orderDTOS);
                                    ArrayList<Order> listAttempt = new ArrayList<>();
                                    lstOrder.getValue().stream().forEach(orderDTO ->
                                    {
                                        listAttempt.add(convertToOrder(orderDTO));
                                    });
                                lstDisplay.setValue(listAttempt);
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
                        Log.d("ChangeOrderStatus: ", s);
                        if (orderStatus == OrderStatus.Confirmed) {
                            loadOrder(OrderStatus.Pending);
                        }
                        if (orderStatus == OrderStatus.Delivering) {
                            loadOrder(OrderStatus.Confirmed);
                        }
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
    private Order convertToOrder(OrderDTO orderDTO)
    {
        return new Order(
                orderDTO.getId(),
                orderDTO.getOrderTime(),
                orderDTO.getOrderStatus(),
                orderDTO.getAddress(),
                new ArrayList(orderDTO.getOrderItemDTOList().stream().map(orderItemDTO -> {
                    return convertToOrderItem(orderItemDTO);
                }).collect(Collectors.toList())),
                orderDTO.getTotalPrice()
        );
    }

    private OrderBillingItem convertToOrderItem(OrderItemDTO orderItemDTO)
    {
        return new OrderBillingItem(
                orderItemDTO.getId(),
                orderItemDTO.getProduct(),
                new Image(orderItemDTO.getImageUrl()),
                orderItemDTO.getName(),
                orderItemDTO.getPrice(),
                orderItemDTO.getQuantity(),
                orderItemDTO.getProperty()
        );
    }

}
