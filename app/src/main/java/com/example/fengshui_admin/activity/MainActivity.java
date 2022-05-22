package com.example.fengshui_admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.fengshui_admin.R;
import com.example.fengshui_admin.databinding.ActivityMainBinding;
import com.example.fengshui_admin.fragment.home_fragment.HomeFragment;
import com.example.fengshui_admin.fragment.order_fragment.OrderFragment;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpFirstFragment();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);
        setContentView(binding.getRoot());
        binding.navView.inflateMenu(R.menu.side_menu);
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return onMenuItemClick(item);
            }
        });

        initListener();
    }

    @SuppressLint("SetTextI18n")
    public void initListener(){
        binding.imgOpenSide.setOnClickListener(view -> {
            binding.collapsingToolbarLayout.bringToFront();
            binding.drawerLayout.openDrawer(Gravity.LEFT);
        });

    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home: {
                System.out.println("nav to home");
                navigateTo(new HomeFragment());
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
            case R.id.order: {
                System.out.println("nav to Order");
                navigateTo(new OrderFragment());
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
            case R.id.industry: {
                System.out.println("nav to industry");
                navigateTo(null);
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
            case R.id.logout: {
                System.out.println("nav to logout");
                navigateTo(null);
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
        }
        return false;
    }

    private void navigateTo(Fragment desFragment){
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_manager, desFragment)
                    .commit();
    }

    private void setUpFirstFragment(){
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_manager);
        if (fragment == null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_manager, new OrderFragment())
                    .commit();
        }
    }
}