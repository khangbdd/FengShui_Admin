package com.example.fengshui_admin.fragment.order_fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengshui_admin.R;
import com.example.fengshui_admin.databinding.FragmentOrderBinding;
import com.example.fengshui_admin.model.dto.OrderDTO;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderFragment extends Fragment {

    @Inject
    public OrderViewModel orderViewModel;


    private FragmentOrderBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);

        orderViewModel.lstOrder.observe(getViewLifecycleOwner(), new Observer<ArrayList<OrderDTO>>() {
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
}