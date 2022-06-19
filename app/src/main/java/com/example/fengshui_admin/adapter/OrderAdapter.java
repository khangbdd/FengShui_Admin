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
import com.example.fengshui_admin.utils.OrderStatus;

import java.util.Objects;

public class OrderAdapter extends ListAdapter<Order, OrderAdapter.OrderViewHolder> {

        private OnClickListener onClickListener;
        private OnChangeStatusClick onChangeStatusClick;

        public interface OnChangeStatusClick{
            void onChangeStatusClick(long orderId, OrderStatus orderStatus);
        }

    public OrderAdapter(OnClickListener onClickListener, OnChangeStatusClick onChangeStatusClick) {
        super(DiffCallBack.getInstance());
        this.onClickListener = onClickListener;
        this.onChangeStatusClick = onChangeStatusClick;
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
        return new OrderViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext())), onChangeStatusClick);
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
            public OnChangeStatusClick onChangeStatusClick;

            public OrderViewHolder(ItemOrderBinding binding, OnChangeStatusClick onChangeStatusClick){
                super(binding.getRoot());
                this.binding = binding;
                this.onChangeStatusClick = onChangeStatusClick;
            }

            public void bind(Order order) {
                binding.setOrder(order);
                BillingItemsAdapter adapter = new BillingItemsAdapter();
                binding.billingItems.setAdapter(adapter);
                if (order.getStatus() == OrderStatus.Pending) {
                    binding.ratingNow.setVisibility(View.VISIBLE);
                    binding.ratingNow.setText("Confirm");
                } else if (order.getStatus() == OrderStatus.Confirmed) {
                    binding.ratingNow.setVisibility(View.VISIBLE);
                    binding.ratingNow.setText("Delivered");
                } else if (order.getStatus() == OrderStatus.Delivering) {
                    binding.ratingNow.setVisibility(View.GONE);
                } else if (order.getStatus() == OrderStatus.Success) {
                    binding.ratingNow.setVisibility(View.GONE);
                } else if (order.getStatus() == OrderStatus.Cancel) {
                    binding.ratingNow.setVisibility(View.GONE);
                }
                binding.ratingNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (order.getStatus() == OrderStatus.Pending) {
                            onChangeStatusClick.onChangeStatusClick(order.getId(), OrderStatus.Confirmed);
                        } else if (order.getStatus() == OrderStatus.Confirmed) {
                            onChangeStatusClick.onChangeStatusClick(order.getId(), OrderStatus.Delivering);
                        }
                    }
                });
            }

        }
}
