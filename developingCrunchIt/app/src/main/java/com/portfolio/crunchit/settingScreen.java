package com.portfolio.crunchit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settingScreen extends AppCompatActivity {
    private Button signOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        signOutBtn = findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signout();

            }
        });
    }
    public void Signout(){
        Intent intent= new Intent(this,LoginScreen.class);
        startActivity(intent);
        finish();


    }
}