package com.example.fengshui_admin.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fengshui_admin.databinding.ItemOrderBinding;
import com.example.fengshui_admin.model.Order;
import com.example.fengshui_admin.model.OrderBillingItem;

import java.util.Objects;

public class OrderAdapter extends ListAdapter<Order, OrderAdapter.OrderViewHolder> {

        private OnClickListener onClickListener;

    public OrderAdapter(OnClickListener onClickListener) {
        super(DiffCallBack.getInstance());
        this.onClickListener = onClickListener;
    }
    public static class DiffCallBack extends DiffUtil.ItemCallback<Order>{

        private static OrderAdapter.DiffCallBack diffCallBack;

        public static OrderAdapter.DiffCallBack getInstance(){
            if (diffCallBack == null){
                diffCallBack = new OrderAdapter.DiffCallBack();
            }
            return diffCallBack;
        }


        @Override
        public boolean areItemsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order currentOrder = getItem(position);
        holder.bind(currentOrder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(currentOrder);
            }
        });
    }

    public interface OnClickListener {
         void onClick(Order order);
        }

        public class OrderViewHolder extends RecyclerView.ViewHolder {

            public ItemOrderBinding binding;

            public OrderViewHolder(ItemOrderBinding binding){
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bind(Order order) {
                binding.setOrder(order);
                BillingItemsAdapter adapter = new BillingItemsAdapter();
                binding.billingItems.setAdapter(adapter);
            }

        }
}
