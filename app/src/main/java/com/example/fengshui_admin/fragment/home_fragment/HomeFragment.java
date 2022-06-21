package com.example.fengshui_admin.fragment.home_fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengshui_admin.R;
import com.example.fengshui_admin.adapter.ProductItemsAdapter;
import com.example.fengshui_admin.databinding.FragmentHomeBinding;
import com.example.fengshui_admin.fragment.order_fragment.OrderViewModel;
import com.example.fengshui_admin.fragment.product_detail_fragment.ProductDetailFragment;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.model.entity.Account;
import com.example.fengshui_admin.repository.room_database.AccountDatabase;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {


    FragmentHomeBinding binding;
    ProductViewModel productViewModel;
    ProductItemsAdapter adapter;
    HomeFragment thisFragment = this;
    private FragmentManager fragmentManager;

    private void loadToken(){
        Account account = AccountDatabase.getInstance(this.requireContext()).accountDao().getAccount();
        productViewModel.loadToken(new TokenDTO(
                account.accessToken, account.tokenType, account.refreshToken));
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        binding.setViewModel(productViewModel);
        loadToken();
        adapter = new ProductItemsAdapter(product -> fragmentManager.beginTransaction()
                .add(R.id.fragment_manager, new ProductDetailFragment(product, productViewModel))
                .commit());
        binding.products.setAdapter(adapter);
        binding.products.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        productViewModel.lstDisplay.observe(getViewLifecycleOwner(), products -> {
            products.stream().forEach(
                    product -> {
                        Log.d("product: ", product.toString());
                    }
            );
            adapter.submitList(null);
            adapter.submitList(new ArrayList<>(products));
        });



        return binding.getRoot();
    }
}