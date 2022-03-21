package com.portfolio.crunchit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class accountScreen extends AppCompatActivity {
    private Button settingBtn;
    private Button orderBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);

        settingBtn = findViewById(R.id.checkSettingsBtn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingScreen();
            }
        });
    }
    public void openSettingScreen(){
        Intent intent= new Intent(this,settingScreen.class);
        startActivity(intent);


    }
}