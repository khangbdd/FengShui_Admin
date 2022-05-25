package com.example.fengshui_admin.repository;

import com.example.fengshui_admin.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kotlin.jvm.Synchronized;
import kotlin.jvm.Throws;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static RetrofitInstance instance;

    public Retrofit retrofit;

    private RetrofitInstance(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new MyInterceptor()).build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static RetrofitInstance getInstance(){
        if (instance == null){
            instance = new RetrofitInstance();
        }
        return instance;
    }

}
