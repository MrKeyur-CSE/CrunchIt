package com.portfolio.crunchit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.portfolio.crunchit.R;

public class Orders extends AppCompatActivity {


    public BottomNavigationView accountScreenNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        accountScreenNavBar = findViewById(R.id.accountScreenNavBar);
        accountScreenNavBar.setSelectedItemId(R.id.accountNavBar);
        accountScreenNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.homeNavBar: break;
                    case R.id.searchNavBar:startActivity(new Intent(getApplicationContext(), SearchScreen.class)); break;
                    case R.id.cartNavBar:break;
                    case R.id.accountNavBar: startActivity(new Intent(getApplicationContext(), accountScreen.class)); break;
                }
                return false;
            }
        });

    }




}