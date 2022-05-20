package com.example.fengshui_admin.utils;

import android.net.Uri;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fengshui_admin.R;
import com.example.fengshui_admin.model.OrderBillingItem;

import java.util.ArrayList;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("billingItemData")
    public static void bindBillingItemRecyclerView(RecyclerView recyclerView, ArrayList<OrderBillingItem> data){
    }

    @androidx.databinding.BindingAdapter("image")
    public static void bindImage(ImageView imageView, Uri image) {
        Glide.with(imageView.getContext())
                .load(image)
                .apply(
                        new RequestOptions().placeholder(R.drawable.animation_loading)
                )
                .into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @androidx.databinding.BindingAdapter("statusIconData")
    public static void bindStatusIcon(ImageView imageView, OrderStatus data){
        if(data!=null){
            switch (data){
                case Success: {
                    imageView.setImageResource(R.drawable.ic_order_pass);
                    break;
                }
                case Pending: {
                    imageView.setImageResource(R.drawable.ic_order_pending);
                    break;
                }
                case Cancel: {
                    imageView.setImageResource(R.drawable.ic_order_cancel);
                    break;
                }
                case Confirmed: {
                    imageView.setImageResource(R.drawable.ic_order_confirm);
                    break;
                }
                case Delivering: {
                    imageView.setImageResource(R.drawable.ic_order_delivering);
                    break;
                }
            }
        }
    }
}
