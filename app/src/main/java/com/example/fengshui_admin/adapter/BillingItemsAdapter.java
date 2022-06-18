package com.example.fengshui_admin.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.DifferCallback;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fengshui_admin.databinding.ItemOrderBillingBinding;
import com.example.fengshui_admin.databinding.ItemOrderBinding;
import com.example.fengshui_admin.model.OrderBillingItem;

import java.util.Objects;

public class BillingItemsAdapter extends ListAdapter<OrderBillingItem, BillingItemsAdapter.BillingItemViewHolder> {


    public BillingItemsAdapter() {
        super(DiffCallBack.getInstance());
    }

    public static class DiffCallBack extends DiffUtil.ItemCallback<OrderBillingItem>{

        private static DiffCallBack diffCallBack;

        public static DiffCallBack getInstance(){
            if (diffCallBack == null){
                diffCallBack = new DiffCallBack();
            }
            return diffCallBack;
        }

        @Override
        public boolean areItemsTheSame(@NonNull OrderBillingItem oldItem, @NonNull OrderBillingItem newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull OrderBillingItem oldItem, @NonNull OrderBillingItem newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }
    }

    @NonNull
    @Override
    public BillingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillingItemViewHolder(ItemOrderBillingBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull BillingItemViewHolder holder, int position) {
        OrderBillingItem currentOrder = getItem(position);
        holder.bind(currentOrder);
    }

    public class BillingItemViewHolder extends RecyclerView.ViewHolder{

        private ItemOrderBillingBinding binding;

        public BillingItemViewHolder(ItemOrderBillingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(OrderBillingItem orderBillingItem){
            binding.setBillingItem( orderBillingItem);
        }
    }
}