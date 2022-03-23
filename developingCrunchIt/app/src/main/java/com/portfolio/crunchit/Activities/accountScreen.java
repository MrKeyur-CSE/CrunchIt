package com.portfolio.crunchit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.portfolio.crunchit.R;


public class accountScreen extends AppCompatActivity {
    private Button settingBtn;
    private Button orderBtn;

    public BottomNavigationView accountScreenNavBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);

        settingBtn = findViewById(R.id.SettingsBtnAccountScreen);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingScreen();
            }
        });
        accountScreenNavBar = findViewById(R.id.accountScreenNavBar);
        accountScreenNavBar.setSelectedItemId(R.id.accountNavBar);
        accountScreenNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.homeNavBar:startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        break;
                    case R.id.searchNavBar:break;
                    case R.id.cartNavBar:break;
                    case R.id.accountNavBar:  break;

                }
                return false;
            }
        });


    }
    public void openSettingScreen(){
        Intent intent= new Intent(this, settingScreen.class);
        startActivity(intent);
        finish();


    }


}
