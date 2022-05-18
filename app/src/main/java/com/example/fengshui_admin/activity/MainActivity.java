package com.example.fengshui_admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fengshui_admin.R;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.HiltAndroidApp;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}