package com.example.crunchit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final EditText mEmail = findViewById(R.id.email);
        final EditText mPass = findViewById(R.id.pass);
        final Button mLoginBtn = findViewById(R.id.loginBtn);
        final TextView mNotRegister = findViewById(R.id.notRegister);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String emailTxt = mEmail.getText().toString().trim();
                final String passwordTxt = mPass.getText().toString().trim();

                if (TextUtils.isEmpty(emailTxt)) {
                    mEmail.setError("Email Address is Require.");
                    return;
                }

                if (TextUtils.isEmpty(passwordTxt)) {
                    mPass.setError("Password is Require.");
                    return;
                }

                if (passwordTxt.length() < 6) {
                    mPass.setError("Password must be grater than 6 digit.");
                    return;
                }

            }
        });
    }
}