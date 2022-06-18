package com.example.fengshui_admin.fragment.order_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengshui_admin.R;
import com.example.fengshui_admin.adapter.OrderAdapter;
import com.example.fengshui_admin.databinding.FragmentOrderBinding;
import com.example.fengshui_admin.fragment.order_detail_fragment.OrderDetailFragment;
import com.example.fengshui_admin.model.Order;
import com.example.fengshui_admin.model.dto.OrderDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.model.entity.Account;
import com.example.fengshui_admin.repository.room_database.AccountDatabase;
import com.example.fengshui_admin.utils.OrderStatus;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderFragment extends Fragment implements OrderAdapter.OnChangeStatusClick {

    OrderViewModel orderViewModel;
    OrderAdapter orderAdapter;
    OrderFragment thisFragment = this;
    private FragmentManager fragmentManager;


    private FragmentOrderBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getParentFragmentManager();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        binding.setViewModel(orderViewModel);
        loadToken();
        orderAdapter = new OrderAdapter(new OrderAdapter.OnClickListener() {
            @Override
            public void onClick(Order order) {
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_manager, new OrderDetailFragment(order, orderViewModel))
                        .commit();
            }
        }, this);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .remove(thisFragment)
                        .commit();
            }
        });
        binding.orders.setAdapter(orderAdapter);
        binding.orders.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        orderViewModel.lstDisplay.observe(getViewLifecycleOwner(), new Observer<ArrayList<Order>>() {
            @SuppressLint("NotifyDataSetChanged")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(ArrayList<Order> orders) {
                orders.stream().forEach(
                        order -> {
                            Log.d("orders: ", order.toString());
                        }
                );
                orderAdapter.submitList(null);
                orderAdapter.submitList(new ArrayList<>(orders));
            }
        });
        initBottomBar();
        return binding.getRoot();
    }

    private void loadToken(){
        Account account = AccountDatabase.getInstance(this.requireContext()).accountDao().getAccount();
        orderViewModel.loadToken(new TokenDTO(
                account.accessToken, account.tokenType, account.refreshToken
        ));
    }

    private void initBottomBar(){
        binding.bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()) {
                    case "Pending":
                        orderViewModel.loadOrder(OrderStatus.Pending);
                        return  true;
                    case "Confirmed":
                        binding.bottomBar.animate();
                        orderViewModel.loadOrder(OrderStatus.Confirmed);
                        return  true;
                    case "Delivering":
                        orderViewModel.loadOrder(OrderStatus.Delivering);
                        return  true;
                    case "Success":
                        orderViewModel.loadOrder(OrderStatus.Success);
                        return  true;
                    case "Cancel":
                        orderViewModel.loadOrder(OrderStatus.Cancel);
                        return  true;
                }
                return false;
            }
        });
    }

    @Override
    public void onChangeStatusClick(long orderId, OrderStatus orderStatus) {
        orderViewModel.setOrderStatus(orderId, orderStatus);
    }
}