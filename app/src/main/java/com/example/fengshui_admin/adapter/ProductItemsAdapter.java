package com.example.fengshui_admin.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fengshui_admin.databinding.ItemProductBinding;
import com.example.fengshui_admin.model.Product;

import java.util.Objects;

public class ProductItemsAdapter extends ListAdapter<Product, ProductItemsAdapter.ProductItemViewHolder> {

    private ProductItemsAdapter.OnClickListener onClickListener;
    public ProductItemsAdapter(OnClickListener onClickListener) {
        super(ProductItemsAdapter.DiffCallBack.getInstance());
    }

    public static class DiffCallBack extends DiffUtil.ItemCallback<Product>{

        private static ProductItemsAdapter.DiffCallBack diffCallBack;

        public static ProductItemsAdapter.DiffCallBack getInstance(){
            if (diffCallBack == null){
                diffCallBack = new ProductItemsAdapter.DiffCallBack();
            }
            return diffCallBack;
        }

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }
    }

    @NonNull
    @Override
    public ProductItemsAdapter.ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductItemsAdapter.ProductItemViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemsAdapter.ProductItemViewHolder holder, int position) {
        Product currentProduct = getItem(position);
        holder.bind(currentProduct);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(currentProduct);
            }
        });
    }

    public interface OnClickListener {
        void onClick(Product product);
    }
    public class ProductItemViewHolder extends RecyclerView.ViewHolder{

        private ItemProductBinding binding;

        public ProductItemViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product){
            binding.setProduct(product);
        }
    }
}
