package com.portfolio.crunchit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class settingScreen extends AppCompatActivity {

    private Button signOutBtn;
    private  Button editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        signOutBtn = findViewById(R.id.signOutBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoEditprofile();
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signout();
            }
        });
    }
    public void gotoEditprofile(){
        Intent intent= new Intent(this,editAccountScreen.class);
        startActivity(intent);
    }
    public void Signout(){
//        FirebaseAuth.getInstance().signOut();
        Intent intent= new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(intent);
        finish();
    }
}
