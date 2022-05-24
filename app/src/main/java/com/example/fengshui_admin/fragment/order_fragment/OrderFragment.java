package com.example.fengshui_admin.fragment.order_fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengshui_admin.R;
import com.example.fengshui_admin.databinding.FragmentOrderBinding;
import com.example.fengshui_admin.model.dto.OrderDTO;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.model.entity.Account;
import com.example.fengshui_admin.repository.room_database.AccountDatabase;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderFragment extends Fragment {

    OrderViewModel orderViewModel;


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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        loadToken();
        orderViewModel.lstOrder.observe(this.getViewLifecycleOwner(), new Observer<ArrayList<OrderDTO>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(ArrayList<OrderDTO> orderDTOS) {
                StringBuilder stringBuilder = new StringBuilder();
                orderDTOS.stream().forEach( orderDTO -> {
                            stringBuilder.append(orderDTO.toString());
                        }
                );
                binding.testText.setText(stringBuilder);
            }
        });
        return binding.getRoot();
    }

    private void loadToken(){
        Account account = AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount();
        orderViewModel.loadToken(new TokenDTO(
                account.accessToken, account.tokenType, account.refreshToken
        ));
    }
}