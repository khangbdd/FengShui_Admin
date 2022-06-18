package com.example.fengshui_admin.fragment.order_detail_fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengshui_admin.R;
import com.example.fengshui_admin.adapter.BillingItemsAdapter;
import com.example.fengshui_admin.databinding.FragmentOrderDetailBinding;
import com.example.fengshui_admin.fragment.order_fragment.OrderViewModel;
import com.example.fengshui_admin.model.Order;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.model.entity.Account;
import com.example.fengshui_admin.repository.room_database.AccountDatabase;
import com.example.fengshui_admin.utils.OrderStatus;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.HiltAndroidApp;

@AndroidEntryPoint
public class OrderDetailFragment extends Fragment {

    public OrderDetailFragment(Order currentOrder, OrderViewModel orderViewModel)
    {
        this.currentOrder = currentOrder;
        this.orderViewModel = orderViewModel;
    }

    private FragmentOrderDetailBinding binding;
    private Order currentOrder;
    private OrderDetailViewModel viewModel;
    private OrderViewModel orderViewModel;
    private BillingItemsAdapter orderDetailBillingItem;
    private FragmentManager fragmentManager;
    private OrderDetailFragment thisFragment = this;
    //private lateinit var dialog: YesNoOrderSuccessStatusDialog

    //private lateinit var orderDeliveringStateAdapter: OrderDeliveringStateAdapter

    private void loadToken(){
        Account account = AccountDatabase.getInstance(this.requireContext()).accountDao().getAccount();
        viewModel.token = new TokenDTO(
                account.accessToken, account.tokenType, account.refreshToken
        );
    }

    private void setShowButtonByStatus(OrderStatus status){
        if(status == OrderStatus.Success) {
            binding.ratingNow.setVisibility(View.GONE);
//            binding.cancel.setVisibility(View.GONE);
        } else if(status == OrderStatus.Pending){
            binding.ratingNow.setText("Confirmed");
            binding.ratingNow.setVisibility(View.VISIBLE);
//            binding.cancel.setVisibility(View.VISIBLE);
        }else if (status == OrderStatus.Confirmed){
            binding.ratingNow.setText("Delivered");
//            binding.cancel.setVisibility(View.VISIBLE);
            binding.ratingNow.setVisibility(View.VISIBLE);
        } else if (status == OrderStatus.Delivering)
        {
            binding.ratingNow.setVisibility(View.GONE);
//            binding.cancel.setVisibility(View.GONE);
        }else if (status == OrderStatus.Cancel){
            binding.ratingNow.setVisibility(View.GONE);
//            binding.cancel.setVisibility(View.GONE);
        }

    }

    private void setStatusValue(OrderStatus orderStatus)
    {
        binding.statusString.setText(orderStatus.toString());
        if (orderStatus == OrderStatus.Pending)
        {
            binding.statusIcon.setImageResource(R.drawable.ic_order_pending);
        } else if (orderStatus == OrderStatus.Confirmed)
        {
            binding.statusIcon.setImageResource(R.drawable.ic_order_confirm);
        } else if (orderStatus == OrderStatus.Delivering)
        {
            binding.statusIcon.setImageResource(R.drawable.ic_order_delivering);
        }else if (orderStatus == OrderStatus.Cancel)
        {
            binding.statusIcon.setImageResource(R.drawable.ic_order_cancel);
        } else {
            binding.statusIcon.setImageResource(R.drawable.ic_order_pass);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderDetailViewModel.class);
        viewModel.detailOrder.setValue(currentOrder);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getParentFragmentManager();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_detail, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        orderDetailBillingItem = new BillingItemsAdapter();
        binding.billingItems.setAdapter(orderDetailBillingItem);
        //orderDeliveringStateAdapter = OrderDeliveringStateAdapter()
        //binding.deliveringState.adapter = orderDeliveringStateAdapter
        setStatusValue(currentOrder.getStatus());
        setShowButtonByStatus(currentOrder.getStatus());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .remove(thisFragment)
                        .commit();
            }
        });

        binding.ratingNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentOrder.getStatus() == OrderStatus.Pending)
                {
                    viewModel.setOrderStatus(currentOrder.getId(), OrderStatus.Confirmed);
                    setStatusValue(OrderStatus.Confirmed);
                    setShowButtonByStatus(OrderStatus.Confirmed);
                    orderViewModel.loadOrder(OrderStatus.Pending);
                }
                if (currentOrder.getStatus() == OrderStatus.Confirmed)
                {
                    viewModel.setOrderStatus(currentOrder.getId(), OrderStatus.Delivering);
                    setStatusValue(OrderStatus.Delivering);
                    setShowButtonByStatus(OrderStatus.Delivering);
                    orderViewModel.loadOrder(OrderStatus.Confirmed);
                }
            }
        });
        loadToken();
        return binding.getRoot();
    }


}