package com.example.crunchit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class CrunchItResgisterScreen extends AppCompatActivity {

    EditText uFullName,uEmail,uPhone,uPassword,uRePassword;
    Button uRegisterBtn;
    TextView uLoginBtn;
    FirebaseAuth uAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crunch_it_resgister_screen);

        uFullName = findViewById(R.id.fullName);
        uEmail = findViewById(R.id.emailAddress);
        uPhone = findViewById(R.id.phoneNumber);
        uPassword = findViewById(R.id.password);
        uRePassword = findViewById(R.id.rePassword);
        uLoginBtn = findViewById(R.id.notRegisteredLogin);
        uRegisterBtn = findViewById(R.id.registerBtn);

        uAuth = FirebaseAuth.getInstance();

        uRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CrunchItResgisterScreen.this, MainActivity.class));

                String password = uPassword.getText().toString().trim();
                String email = uEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    uEmail.setError("Email Address is Require.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    uPassword.setError("Password is Require.");
                    return;
                }

                if (password.length() < 6) {
                    uPassword.setError("Password must be grater than 6 digit.");
                    return;
                }

                // Register User in FireBase

                uAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CrunchItResgisterScreen.this,"User Registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(CrunchItResgisterScreen.this,"Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}