package com.example.fengshui_admin.fragment.product_detail_fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengshui_admin.R;
import com.example.fengshui_admin.databinding.FragmentProductDetailBinding;
import com.example.fengshui_admin.fragment.home_fragment.ProductViewModel;
import com.example.fengshui_admin.model.Product;
import com.example.fengshui_admin.model.dto.TokenDTO;
import com.example.fengshui_admin.model.entity.Account;
import com.example.fengshui_admin.repository.room_database.AccountDatabase;


public class ProductDetailFragment extends Fragment {

    public ProductDetailFragment(Product currentProduct, ProductViewModel productViewModel) {
        this.currentProduct = currentProduct;
        this.productViewModel = productViewModel;
    }

    private FragmentProductDetailBinding binding;
    private Product currentProduct;
    private ProductDetailViewModel viewModel;
    private ProductViewModel productViewModel;
    private FragmentManager fragmentManager;
    private ProductDetailFragment thisFragment = this;

    private void loadToken(){
        Account account = AccountDatabase.getInstance(this.requireContext()).accountDao().getAccount();
        viewModel.token = new TokenDTO(
                account.accessToken, account.tokenType, account.refreshToken
        );
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        viewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
//        viewModel.newProduct.setValue(currentProduct);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }
}